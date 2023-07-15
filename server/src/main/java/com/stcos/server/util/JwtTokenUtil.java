package com.stcos.server.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token 工具类，用于生成和验证 JSON Web Tokens (JWT)
 */
@UtilityClass
public class JwtTokenUtil {

    private final String CLAIM_KEY_USERNAME = "sub";
    private final String CLAIM_KEY_USERTYPE = "userType";
    private final String CLAIM_KEY_CREATED = "created";

    private String secret;

    public void setSecret(String secret) {
        JwtTokenUtil.secret = secret;
    }

    public void setExpiration(Long expiration) {
        JwtTokenUtil.expiration = expiration;
    }

    private Long expiration;

    /**
     * 根据用户信息生成 JWT token
     * @param userDetails
     * @param usertype
     * @return
     */
    public String generateToken(UserDetails userDetails,  String usertype) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_USERTYPE, usertype);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 从 token 中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static String getUserTypeFromToken(String token) {
        String usertype;
        try {
            Claims claims = getClaimsFromToken(token);
            usertype = (String) claims.get(CLAIM_KEY_USERTYPE);
        } catch (Exception e) {
            usertype = null;
        }
        return usertype;
    }

    /**
     * 验证 token 是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断 token 是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新 token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断 token 是否过期
     * @param token
     * @return
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    private Date getExpiredDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
//                    .parseClaimsJwt(token) // 用于解析没有签名的 token
                    .parseClaimsJws(token) // 用于解析经过签名的 token
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成 JWT token
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成 token 失效时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


}

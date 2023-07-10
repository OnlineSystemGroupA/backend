package com.stcos.server.util.dto;

import com.stcos.server.entity.dto.ClientDetailsDto;
import com.stcos.server.entity.user.Client;
import lombok.experimental.UtilityClass;

/**
 * ClientDetailsDto 映射工具类
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/9 20:47
 */
@UtilityClass
public class ClientDetailsMapper {

    public ClientDetailsDto toClientDetailsDto(Client client) {
        return new ClientDetailsDto(
                client.getUid(),
                client.getUsername(),               // 用户名
                client.getCreatedDate().toString(), // 账号创建时间
                client.getRealName(),               // 用户的真实姓名
                client.getEmail(),                  // 邮箱
                client.getPhone(),                  // 联系电话
                client.getGender(),                 // 性别
                client.getCompany(),                // 公司名称
                client.getCompanyTelephone(),       // 公司电话号
                client.getCompanyFax(),             // 公司传真
                client.getCompanyAddress(),         // 公司地址
                client.getCompanyPostcode(),        // 公司邮编
                client.getCompanyWebsite(),         // 公司网址
                client.getCompanyEmail(),           // 公司邮箱
                client.getCompanyPhone(),           // 公司手机号
                client.isAccountNonLocked()
        );
    }

}

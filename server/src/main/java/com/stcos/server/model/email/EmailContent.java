package com.stcos.server.model.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/19 17:17
 */
@Getter
@AllArgsConstructor
public class EmailContent {

    /**
     * 邮件标题
     */
    private final String emailSubject;

    /**
     * 邮件正文模板
     */
    private final String emailTemplate;

    /**
     * 传入参数，替换模板中的占位字符串
     *
     * @param paramMap 参数
     * @return 模板中站位字符串被替换后的字符串
     */
    public String getEmailText(Map<String, String> paramMap) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(paramMap);
        return stringSubstitutor.replace(emailTemplate);
    }

}

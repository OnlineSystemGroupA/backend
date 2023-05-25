package com.stcos.server.entity.form;

import java.lang.annotation.*;

/**
 * 定义一个主键自动生成策略注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoId {
}
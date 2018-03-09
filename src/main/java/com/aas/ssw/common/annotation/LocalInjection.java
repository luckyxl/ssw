package com.aas.ssw.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解表示方法中的request参数中
 * 需要注入语言环境信息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LocalInjection {
    //注入request中的key
    String name() default "local";
}

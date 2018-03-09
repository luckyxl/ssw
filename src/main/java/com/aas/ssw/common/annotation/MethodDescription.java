package com.aas.ssw.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用来标注方法的作用，方便记录用户行为,
 * 在controller中的增/删/改方法中使用，不要使用在其他地方.
 * Created by xl on 2017/8/21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MethodDescription {

    String description() default "";//方法描述
}

package com.lidong.spring.ioc.overview.annotation;

import java.lang.annotation.*;

/**
 * @author ls J
 * @date 2020/5/8 9:36
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomValid {

}

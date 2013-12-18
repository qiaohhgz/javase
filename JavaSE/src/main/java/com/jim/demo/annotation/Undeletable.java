package com.jim.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/12/13
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Undeletable {
    String status() default "status";
}

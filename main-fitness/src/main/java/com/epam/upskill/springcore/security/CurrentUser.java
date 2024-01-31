package com.epam.upskill.springcore.security;

import java.lang.annotation.*;

/**
 * @description: TODO to get principal user
 * @date: 29 December 2023 $
 * @time: 8:45 PM 17 $
 * @author: Qudratjon Komilov
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
package net.androidwing.aspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hash on 2017/7/4.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface CheckAuth {
}

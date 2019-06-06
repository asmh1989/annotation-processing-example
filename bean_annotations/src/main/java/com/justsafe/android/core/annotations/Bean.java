package com.justsafe.android.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** @author sun */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Bean {

    /**
     * bean名称
     *
     * @return
     */
    String name() default "";
}

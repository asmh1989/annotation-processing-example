package com.justsafe.android.core.bean.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sun
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanImp {

    /**
     * 最小支持版本
     *
     * @return
     */
    int minVersion() default -1;

    /**
     * 最大支持版本
     *
     * @return
     */
    int maxVersion() default -1;

    /**
     * 支持版本
     *
     * @return
     */
    int[] versions() default {};

    /**
     * 支持型号
     *
     * @return
     */
    String[] model() default {};

    /**
     * 支持厂商
     *
     * @return
     */
    String[] brand() default {};

    /**
     * 支持rom
     *
     * @return
     */
    String[] rom() default {};

    /**
     * 优先级
     *
     * @return
     */
    int priority() default 0;

    /**
     * 标记，特殊目的的标签，如不同项目设定不通tag
     *
     * @return
     */
    String tag() default "";

}
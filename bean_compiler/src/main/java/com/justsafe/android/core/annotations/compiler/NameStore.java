package com.justsafe.android.core.annotations.compiler;

import com.justsafe.android.core.annotations.internal.BindingSuffix;

/**
 * 常量
 *
 * @author sun
 */
public final class NameStore {

    private NameStore() {
        // not to be instantiated in public
    }

    public static String getGeneratedClassName(String clsName) {
        return clsName + BindingSuffix.GENERATED_CLASS_SUFFIX;
    }

    public static class Method {
        // Binder
        public static final String ADD_BEAN = "addBean__";
        public static final String ADD_BEAN_IMP = "addBeanImp__";
    }

    public static class Variable {
        public static final String ANNOTATION_MANAGER = "manager";
    }
}

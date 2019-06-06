package com.mindorks.annotation.processing.example;

import com.justsafe.android.core.bean.BaseBeanManager;
import com.justsafe.android.core.bean.annotations.BeanManager;
import com.justsafe.android.core.bean.internal.BindingSuffix;

/** @author sun */
@BeanManager
public class BeanAnnotationManager extends BaseBeanManager {

    public  BeanAnnotationManager() {
        instantiateBinder(this, BindingSuffix.GENERATED_CLASS_SUFFIX);
    }
}

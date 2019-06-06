package com.mindorks.annotation.processing.example;

import com.justsafe.android.core.annotations.BaseBeanManager;
import com.justsafe.android.core.annotations.BeanManager;
import com.justsafe.android.core.annotations.internal.BindingSuffix;

/** @author sun */
@BeanManager
public class BeanAnnotationManager extends BaseBeanManager {

    public  BeanAnnotationManager() {
        instantiateBinder(this, BindingSuffix.GENERATED_CLASS_SUFFIX);
    }
}

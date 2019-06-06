package com.mindorks.annotation.processing.example;

import android.util.Log;

import com.justsafe.android.core.bean.annotations.BeanImp;

/**
 * @author sun
 **/

@BeanImp
public class BaseImp implements IBase {
    @Override
    public void test() {
        Log.d("SUN", "BaseImp test....");
    }
}

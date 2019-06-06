package com.example.tttt.beantest;

import android.util.Log;

import com.google.auto.service.AutoService;
import com.justsafe.android.core.bean.annotations.BeanImp;

/**
 * @author sun
 **/
@AutoService(IBase33.class)
@BeanImp
public class Base33Imp implements IBase33 {
    @Override
    public void test3() {
        Log.d("SUN", "Base33Imp test33...");

    }
}

package com.test.bean;

import com.example.tttt.beantest.IBase33;
import com.google.auto.service.AutoService;
import com.justsafe.android.core.bean.annotations.BeanImp;

@BeanImp(tag = "test333")
@AutoService(IBase33.class)
public class Base3Imp implements IBase33 {
    @Override
    public void test3() {

    }
}

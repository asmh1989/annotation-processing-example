package com.test.bean;

import com.google.auto.service.AutoService;
import com.justsafe.android.core.bean.annotations.BeanImp;

@BeanImp(tag = "BaseImp2")
@AutoService(IBase2.class)
public class Base2Imp2 implements IBase2 {
    @Override
    public void test2() {

    }
}

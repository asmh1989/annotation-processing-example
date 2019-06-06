package com.test.bean;

import android.util.Log;

import com.google.auto.service.AutoService;
import com.justsafe.android.core.bean.annotations.BeanImp;

/** @author sun */

@AutoService(IBase2.class)
@BeanImp(
        tag = "test2",
        rom = {"rom1", "rom2"},
        versions = {1, 2})
public class IBase2Imp implements IBase2 {
    @Override
    public void test2() {

        Log.d("SUN", "IBase2Imp test2...");
    }
}

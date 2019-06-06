package com.test.bean;

import com.justsafe.android.core.annotations.BeanImp;

/** @author sun */
@BeanImp(
        tag = "test2",
        rom = {"rom1", "rom2"},
        versions = {1, 2})
public class IBase2Imp implements IBase2 {
    @Override
    public void test2() {}
}

package com.justsafe.android.core.annotations;

/** @author sun */
public class ImpBean {
    /** 最小支持版本 */
    public int minVersion = -1;

    /** 最大支持版本 */
    public int maxVersion = -1;

    /** 支持版本 */
    public int[] versions = null;

    /** 支持型号 */
    public String[] model = null;

    /** 支持厂商 */
    public String[] brand = null;

    /** 支持rom */
    public String[] rom = null;

    /** 优先级 */
    public int priority = 0;

    /** 标记，特殊目的的标签，如不同项目设定不通tag */
    public String tag = "";
}

package com.mindorks.annotation.processing.example;

import androidx.annotation.NonNull;

import java.util.Map;

/**
 *
 * @Author: zhouwc
 * @CreateDate: 2019/4/12 16:15
 * @UpdateDate: 2019/4/12 16:15
 * @Version: 1.0
 */
public interface IBeanManager {

    /**
     * 根据名称判断bean是否存在
     *
     * @param name bean名称
     * @return 是否存在
     */
    boolean exist(@NonNull String name);

    /**
     * 根据类型判断bean是否存在
     *
     * @param cls bean类型
     * @return 是否存在
     */
    boolean exist(@NonNull Class cls);

    /**
     * 添加bean
     *
     * @param cls  类型
     * @param bean bean对象
     * @return 是否成功
     */
    boolean add(@NonNull Class cls, @NonNull Object bean);

    /**
     * 添加bean
     *
     * @param name bean名称
     * @param bean bean对象
     * @return 是否成功
     */
    boolean add(@NonNull String name, @NonNull Object bean);

    /**
     * 根据名称查找bean
     *
     * @param name bean名称
     * @return bean对象
     */
    Object find(@NonNull String name);

    /**
     * 根据类型查找bean
     *
     * @param cls bean 类型
     * @param <T> bean 类型
     * @return bean 对象
     */
    <T> T find(@NonNull Class<T> cls);

    /**
     * 根据类型、名称查找bean
     *
     * @param name bean名称
     * @param cls  bean 类型
     * @param <T>  bean 类型
     * @return bean 对象
     */
    <T> T find(@NonNull String name, @NonNull Class<T> cls);

    /**
     * 返回bean列表
     *
     * @return
     */
    Map<String, Object> findAll();

    /**
     * 移除bean
     *
     * @param name 名称
     * @return 是否成功
     */
    boolean remove(@NonNull String name);

    /**
     * 移除bean
     *
     * @param cls 类型
     * @return 是否成功
     */
    boolean remove(@NonNull Class cls);

    /**
     * 更新bean
     *
     * @param name 名称
     * @param bean bean
     * @return 是否成功
     */
    boolean update(@NonNull String name, @NonNull Object bean);

    /**
     * 更新bean
     *
     * @param cls  类型
     * @param bean bean对象
     * @return 是否成功
     */
    boolean update(@NonNull Class cls, @NonNull Object bean);

    /**
     * 清空所有
     *
     * @return 是否成功
     */
    boolean clear();

}
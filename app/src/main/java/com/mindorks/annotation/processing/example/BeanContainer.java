package com.mindorks.annotation.processing.example;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sun
 **/
public class BeanContainer implements IBeanManager{
    private Map<String, Object> beans;

    public BeanContainer() {
        beans = new HashMap<>();
    }

    @Override
    public boolean exist(@NonNull String name) {
        return beans.containsKey(name);
    }

    @Override
    public boolean exist(@NonNull Class cls) {
        return beans.containsKey(cls.getName());
    }

    @Override
    public boolean add(@NonNull Class cls, @NonNull Object bean) {
        return add(cls.getName(), bean);
    }

    @Override
    public boolean add(@NonNull String name, @NonNull Object bean) {

        if (beans.containsKey(name)) {
            return false;
        }

        beans.put(name, bean);

        return true;
    }

    @Override
    public Object find(@NonNull String name) {
        return beans.get(name);
    }

    @Override
    public <T> T find(@NonNull Class<T> cls) {
        return find(cls.getName(), cls);
    }

    @Override
    public <T> T find(@NonNull String name, @NonNull Class<T> cls) {
        try {
            return (T) beans.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Map<String, Object> findAll() {
        return beans;
    }

    @Override
    public boolean remove(@NonNull String name) {
        beans.remove(name);

        return true;
    }

    @Override
    public boolean remove(@NonNull Class cls) {

        beans.remove(cls.getName());

        return true;
    }

    @Override
    public boolean update(@NonNull String name, @NonNull Object bean) {

        if (!beans.containsKey(name)) {
            return false;
        }

        beans.put(name, bean);

        return true;
    }

    @Override
    public boolean update(@NonNull Class cls, @NonNull Object bean) {
        return update(cls.getName(), bean);
    }

    @Override
    public boolean clear() {
        beans.clear();

        return true;
    }


}

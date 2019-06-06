package com.justsafe.android.core.annotations;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sun
 **/
public class BaseBeanManager {
    List<Class> beans = new ArrayList<>();
    List<Class> beanImps = new ArrayList<>();
    Map<Class, ImpBean> beanImpParam = new HashMap<>();
    Map<Class, String> beanParam = new HashMap<>();
    private Map<Class, Class> target = new HashMap<>();

    public BaseBeanManager() {
    }

    public void addBean(Class className, String name) {
        beans.add(className);
        beanParam.put(className, name == null ? className.getName() : name);
    }

    public void addBeanImp(Class className, ImpBean bean){
        beanImps.add(className);
        beanImpParam.put(className, bean);
    }


    protected static<T extends BaseBeanManager> void instantiateBinder(T target, String suffix) {
        Class<?> targetClass = target.getClass();
        String className = targetClass.getName();
        try {
            Class<?> bindingClass = targetClass.getClassLoader().loadClass(className + suffix);
            Constructor<?> classConstructor = bindingClass.getConstructor(targetClass);
            try {
                classConstructor.newInstance(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to invoke " + classConstructor, e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Unable to invoke " + classConstructor, e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                if (cause instanceof Error) {
                    throw (Error) cause;
                }
                throw new RuntimeException("Unable to create instance.", cause);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find Class for " + className + suffix, e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find constructor for " + className + suffix, e);
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("@Bean size = " + beans.size());
        builder.append("@BeanImp size = " + beanImps.size());

        return builder.toString();
    }
}

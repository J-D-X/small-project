package com.spring;

public interface BeanPostProcessor {


    public Object postProcessBeforeInitialzation(Object bean,String beanName);

    public Object postProcessAfterInitialzation(Object bean,String beanName);
}

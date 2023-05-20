package com.zhouyu.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;
import com.spring.ZhouyuApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//@Component("zhouyuBeanPostProcessor")
public class ZhouyuBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialzation(Object bean, String beanName) {
//        if(beanName.equals("userService")){
//            System.out.println("初始化前");
//            ((UserServiceImpl)bean).setName("周瑜好帅");
//        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialzation(Object bean, String beanName) {
        System.out.println("初始化后");
        if(beanName.equals("userService")) {
            Object proxyInstance = Proxy.newProxyInstance(ZhouyuBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理逻辑");
                    return method.invoke(bean,args);
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}

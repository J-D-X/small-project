package com.zhouyu.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

@Component("zhouyuBeanPostProcessor")
public class ZhouyuBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialzation(Object bean, String beanName) {
        if(beanName.equals("userService")){
            System.out.println("初始化前");
            ((UserService)bean).setName("周瑜好帅");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialzation(Object bean, String beanName) {

        if(beanName.equals("userService")) {
            System.out.println("初始化后");
        }
        return bean;
    }
}

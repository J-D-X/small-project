package com.zhouyu;

import com.spring.ZhouyuApplicationContext;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.zhouyu.service.OrderService;
import com.zhouyu.service.UserService;

public class Test {

    public static void main(String[] args) {
        ZhouyuApplicationContext applicationContext = new ZhouyuApplicationContext(AppConfig.class);
//        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
//        System.out.println(orderService);
        UserService userService = (UserService) applicationContext.getBean("userService");
//        System.out.println(userService);
        userService.test();
    }
}

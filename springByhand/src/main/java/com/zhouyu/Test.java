package com.zhouyu;

import com.spring.ZhouyuApplicationContext;
import com.zhouyu.service.*;

public class Test {
    public static void main(String[] args) {
        ZhouyuApplicationContext applicationContext = new ZhouyuApplicationContext(AppConfig.class);
        CService cservice = (CService) applicationContext.getBean("cService");
        cservice.test();
//        AService aService = (AService) applicationContext.getBean("aService");
//        UserService userService = (UserService) applicationContext.getBean("userService");
//        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
//        System.out.println(aService);
//        System.out.println(orderService);
//        System.out.println(userService);
    }
}

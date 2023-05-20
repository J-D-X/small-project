package com.zhouyu.service;

import com.spring.Autowired;
import com.spring.Component;

@Component("cService")
public class CService {

    @Autowired
    OrderService orderService;

    public void test(){
        System.out.println(orderService);
    }
}

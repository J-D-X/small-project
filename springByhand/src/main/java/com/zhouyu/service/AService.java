package com.zhouyu.service;

import com.spring.Autowired;
import com.spring.Component;

@Component("aService")
public class AService {

    @Autowired
    OrderService orderService;
}

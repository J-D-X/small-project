package com.zhouyu.service;

import com.spring.Autowired;
import com.spring.Component;

@Component("bService")
public class BService {

    @Autowired
    OrderService orderService;
}

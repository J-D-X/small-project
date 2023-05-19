package com.zhouyu.service;

import com.spring.*;

@Component("userService")
//@Scope("singleton")
public class UserService implements BeanNameAware,InitializingBean{

    @Autowired
    private OrderService orderService;

    private String beanName;

    private String name;

    public void test(){
        System.out.println(orderService);
        System.out.println(beanName);
        System.out.println(name);
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.lsj.service;

import com.lsj.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create(){
        //Integer aid 收货地址, Integer uid, String username, Integer[] cids
        Integer[] cids={6,7};
        Order order = orderService.create(26, 5, "111", cids);
        System.out.println(order);
    }


}

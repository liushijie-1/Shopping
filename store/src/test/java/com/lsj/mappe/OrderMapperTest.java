package com.lsj.mappe;

import com.lsj.domain.Order;
import com.lsj.domain.OrderItem;
import com.lsj.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order =new Order();
        order.setUid(5);
        order.setRecvName("管理员");
        order.setRecvPhone("11111111");

        Integer integer = orderMapper.insertOrder(order);
        System.out.println(integer);
    }

    @Test
    public void insertOrderItem(){
        OrderItem orderItem =new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000014);
        orderItem.setTitle("戴尔(DELL)XPS13-9360-R1609 13.3标准版金色");
        Integer integer = orderMapper.insertOrderItem(orderItem);
        System.out.println(integer);

    }

}

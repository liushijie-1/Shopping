package com.lsj.service;

import com.lsj.domain.Order;
import com.lsj.domain.OrderItem;

import java.util.List;

public interface IOrderService {

    /*
     * 根据收货地址、用户的ID、购物车数据获取订单列表
     * @param aid 收货地址ID
     * @param uid 用户ID
     * @param username 用户名
     * @param cids 购物车数据
     * @return 订单列表
     * @Date 21:39 2023/3/24
     **/
    Order create(Integer aid, Integer uid, String username, Integer[] cids);

    List<OrderItem> selectOrderItem(String username);

    OrderItem seleteStatus(Integer id);

    void deleteOrderItem(Integer id);

}

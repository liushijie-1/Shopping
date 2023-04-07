package com.lsj.mapper;

import com.lsj.domain.Order;
import com.lsj.domain.OrderItem;

import java.util.List;

/*订单的额持久层接口*/
public interface OrderMapper {

    /*
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     * @Date 15:27 2023/3/24
     **/
    Integer insertOrder(Order order);

    /*
     * 插入订单项的数据
     * @param orderItem 订单项数据
     * @return 受影响的行数
     * @Date 15:28 2023/3/24
     **/
    Integer insertOrderItem(OrderItem orderItem);

    /*
     * 根据用户名查询订单中的商品
     * @param username
     * @return
     * @Date 17:51 2023/3/28
     **/
    List<OrderItem> selectOrderItem(String username);

    /*
     * 根据id查询订单中的商品信息，用于前端展示当前商品的状态
     * @param id
     * @return
     * @Date 17:52 2023/3/28
     **/
    OrderItem seleteStatus(Integer id);

    OrderItem selectOrderById(Integer id);

    /*
     * 点击确认收货之后删除对应的订单商品
     * @param id
     * @return
     * @return java.lang.Integer
     * @Date 17:52 2023/3/28
     **/
    Integer deleteOrderItem(Integer id);
}

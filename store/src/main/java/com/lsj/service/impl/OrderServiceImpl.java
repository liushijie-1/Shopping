package com.lsj.service.impl;

import com.lsj.domain.Address;
import com.lsj.domain.Order;
import com.lsj.domain.OrderItem;
import com.lsj.mapper.OrderMapper;
import com.lsj.service.AddressService;
import com.lsj.service.ICartService;
import com.lsj.service.IOrderService;
import com.lsj.service.ex.DeleteException;
import com.lsj.service.ex.InsterException;
import com.lsj.service.ex.OrderNotFoundException;
import com.lsj.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        //即将要下单的列表
        List<CartVO> list = cartService.getVOByCid(uid, cids);
        //计算产品总价
        Long totalPrice =0L;
        for (CartVO c : list) {
            totalPrice += c.getRealPrice() * c.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        Order order =new Order();
        order.setUid(uid);
        //收货地址数据
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityCode());
        order.setRecvArea(address.getAreaCode());
        order.setRecvAddress(address.getAddress());
        //支付、总价、时间
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        //补全四个日志信息
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        //插入数据
        Integer rows = orderMapper.insertOrder(order);
        if (rows!=1){
            throw new InsterException("插入数据异常");
        }
        //创建列表的详细数据
        for (CartVO c : list) {
            //创建一个订单项数据对象
            OrderItem orderItem =new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getRealPrice());
            orderItem.setNum(c.getNum());
            //日志字段补充
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            rows = orderMapper.insertOrderItem(orderItem);
            if (rows!=1){
                throw new InsterException("插入数据异常");
            }
        }
        return order;
    }

    @Override
    public List<OrderItem> selectOrderItem(String username) {
        List<OrderItem> item = orderMapper.selectOrderItem(username);
        if (item == null) {
            throw new OrderNotFoundException("未找到订单信息");
        }
        return item;
    }

    @Override
    public OrderItem seleteStatus(Integer id) {
        OrderItem order = orderMapper.seleteStatus(id);

        return order;
    }

    @Override
    public void deleteOrderItem(Integer id) {
        Integer integer = orderMapper.deleteOrderItem(id);
        if (integer != 1) {
            throw new DeleteException("删除订单商品失败");
        }
    }
}

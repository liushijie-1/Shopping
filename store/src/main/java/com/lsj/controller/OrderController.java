package com.lsj.controller;

import com.lsj.domain.Order;
import com.lsj.domain.OrderItem;
import com.lsj.service.IOrderService;
import com.lsj.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        Order data = orderService.create(aid, getuidFromSession(session), getUsernameFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<OrderItem>> selectOrder(HttpSession session) {
        List<OrderItem> orderItems = orderService.selectOrderItem(getUsernameFromSession(session));
        return new JsonResult<>(OK, orderItems);
    }

    @RequestMapping("/status")
    public JsonResult<OrderItem> status(Integer id) {
        OrderItem order = orderService.seleteStatus(id);
        return new JsonResult<>(OK, order);
    }

    @RequestMapping("{id}/deleteorderitem")
    public JsonResult<Void> deleteOrderItem(@PathVariable("id") Integer id) {
        orderService.deleteOrderItem(id);
        return new JsonResult<>(OK);
    }


}

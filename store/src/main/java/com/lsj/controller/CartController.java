package com.lsj.controller;

import com.lsj.service.ICartService;
import com.lsj.util.JsonResult;
import com.lsj.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer number, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid,pid,number,username);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<CartVO> data = cartService.getVOByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer data = cartService.addNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer data = cartService.reduceNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data = cartService.getVOByCid(getuidFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }
}

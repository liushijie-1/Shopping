package com.lsj.service;

import com.lsj.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart(){
        cartService.addToCart(5,10000014,3,"张三");
    }

    @Test
    public void getVOByUid(){
        List<CartVO> voByUid = cartService.getVOByUid(5);
        System.out.println(voByUid);
    }

    @Test
    public void addNum(){
        Integer ls = cartService.addNum(4, 5, "ls");
        System.out.println(ls);
    }

}

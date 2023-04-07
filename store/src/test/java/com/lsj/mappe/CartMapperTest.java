package com.lsj.mappe;

import com.lsj.domain.Cart;
import com.lsj.mapper.CartMapper;
import com.lsj.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTest{
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart =new Cart();
        cart.setUid(5);
        cart.setPid(10000009);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,5,"管理员",new Date());
    }

    @Test
    public void findByUidAndPid(){
        Cart byUidAndPid = cartMapper.findByUidAndPid(5, 10000009);
        System.out.println(byUidAndPid);
    }

    @Test
    public void findVOByUid(){
        List<CartVO> voByUid = cartMapper.findVOByUid(5);
        System.out.println(voByUid);
    }

    @Test
    public void findByCid(){
        Cart byCid = cartMapper.findByCid(4);
        System.out.println(byCid);
    }

    @Test
    public void findVOByCid(){
        Integer[] cids={2,4,6};
        List<CartVO> voByCid = cartMapper.findVoByCid(cids);
        System.out.println(voByCid);
    }

}

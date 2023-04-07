package com.lsj.service.impl;

import com.lsj.domain.Cart;
import com.lsj.domain.Product;
import com.lsj.mapper.CartMapper;
import com.lsj.mapper.ProductMapper;
import com.lsj.service.ICartService;
import com.lsj.service.ex.AccessDeniedException;
import com.lsj.service.ex.CartNotFoundException;
import com.lsj.service.ex.InsterException;
import com.lsj.service.ex.UpdateException;
import com.lsj.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.stream.IIOByteBuffer;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    /*购物车的业务层依赖于购物车的持久层和商品的持久层*/
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer number, String username) {
        //查询当前要添加的商品是否在购物车中存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if (result==null){
            //表示这个商品从来没有加载到购物车中，进行新增操作
            Product product = productMapper.findById(pid);
            Cart cart =new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(number);
            cart.setPrice(product.getPrice());
            //补全日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            //执行数据插入操作
            Integer rows = cartMapper.insert(cart);
            if (rows!=1){
                throw new InsterException("插入数据产生异常");
            }
        }else{
            //表示当前商品在购物车中已经存在，只需更新这条数据的num值
            Integer num = result.getNum() +number;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, new Date());
            if (rows!=1){
                throw new UpdateException("更新数据时产生异常");
            }
        }

    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        List<CartVO> cartVOS = cartMapper.findVOByUid(uid);
        return cartVOS;
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getCid().equals(cid)){
            throw new AccessDeniedException("数据非法访问");
        }

        int num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车数据数量
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getCid().equals(cid)){
            throw new AccessDeniedException("数据非法访问");
        }

        int num = result.getNum() - 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车数据数量
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVoByCid(cids);
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()){
            CartVO cartVO = it.next();
            if (!cartVO.getUid().equals(uid)){
                //当前数据不属于当前用户
                list.remove(cartVO);
            }
        }
        return list;
    }


}

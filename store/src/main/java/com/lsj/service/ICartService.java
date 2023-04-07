package com.lsj.service;


import com.lsj.vo.CartVO;

import java.util.List;

public interface ICartService {

    /*
     * 将商品添加到购物车
     * @param uid 用户ID
     * @param pid 商品ID
     * @param number 新增数量
     * @param username 修改者用户名
     * @return
     * @Date 14:48 2023/3/23
     **/
    void addToCart(Integer uid,Integer pid,Integer number,String username);

    /*
     * 查询购物车数据
     * @param uid 用户ID
     * @return 购物车展示数据
     * @Date 17:06 2023/3/23
     **/
    List<CartVO> getVOByUid(Integer uid);

    /*
     * 更新用户购物车数据的数量——增加
     * @param cid 购物车ID
     * @param uid 用户ID
     * @param username 用户名
     * @return 返回受影响的行数
     * @Date 22:14 2023/3/23
     **/
    Integer addNum(Integer cid,Integer uid,String username);

    /*
     * 更新用户购物车数据的数量——减少
     * @param cid 购物车ID
     * @param uid 用户ID
     * @param username 用户名
     * @return  返回受影响的行数
     * @Date 22:58 2023/3/23
     **/
    Integer reduceNum(Integer cid,Integer uid,String username);

    List<CartVO> getVOByCid(Integer uid,Integer[] cids);
}

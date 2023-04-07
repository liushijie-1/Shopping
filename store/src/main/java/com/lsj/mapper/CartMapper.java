package com.lsj.mapper;

import com.lsj.domain.Cart;
import com.lsj.vo.CartVO;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    /*
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响行数
     * @Date 23:25 2023/3/22
     **/
    Integer insert(Cart cart);

    /*
     * 更新购物车某件商品的数量
     * @param cid 购物车ID
     * @param num 更新的数量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return  受影响的行数
     * @Date 23:27 2023/3/22
     **/
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /*
     * 根据用户的ID和购物车的ID来查询购物车数据
     * @param uid 用户ID
     * @param pid 购物车ID
     * @return 购物车数据
     * @Date 23:29 2023/3/22
     **/
    Cart findByUidAndPid(Integer uid,Integer pid);

    /*
     * 查询购物车数据
     * @param uid 用户ID
     * @return 购物车展示数据
     * @Date 16:54 2023/3/23
     **/
    List<CartVO> findVOByUid(Integer uid);

    /*
     * 根据cid查询购物车数据
     * @param cid 购物车ID
     * @return 购物车数据
     * @Date 22:03 2023/3/23
     * */
    Cart findByCid(Integer cid);

    List<CartVO> findVoByCid(Integer[] cids);
}

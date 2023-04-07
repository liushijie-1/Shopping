package com.lsj.mapper;

import com.lsj.domain.Product;

import java.util.List;

public interface ProductMapper {

    /*
     * 查询热销前四名
     * @return 热销前四名的集合
     * @Date 19:06 2023/3/22
     **/
    List<Product> findHotList();

    /*
     * 根据ID查询商品详细信息
     * @param id 热销产品ID
     * @return 热销产品信息
     * @Date 19:07 2023/3/22
     **/
    Product findById(Integer id);
}

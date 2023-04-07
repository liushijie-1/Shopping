package com.lsj.service;

import com.lsj.domain.Product;

import java.util.List;

public interface IProductService {

    /*
     * 查询热销产品的前4名
     * @return 热销产品前4名
     * @Date 18:37 2023/3/22
     **/
    List<Product> findHotList();

    /*
     * 根据ID查询商品详细信息
     * @param id 热销商品ID
     * @return 商品详细信息
     * @Date 19:11 2023/3/22
     **/
    Product findById(Integer id);

}

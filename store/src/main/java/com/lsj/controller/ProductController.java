package com.lsj.controller;

import com.lsj.domain.Product;
import com.lsj.service.IProductService;
import com.lsj.service.ex.ProductNotFoundException;
import com.lsj.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK,data);
    }

    @RequestMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id){
        Product data = productService.findById(id);
        if (data==null){
            throw new ProductNotFoundException("尝试查询商品信息不存");
        }
        return new JsonResult<>(OK,data);
    }
}

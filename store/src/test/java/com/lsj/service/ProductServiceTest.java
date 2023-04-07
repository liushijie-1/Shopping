package com.lsj.service;

import com.lsj.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private IProductService productService;

    @Test
    public void findHotList(){
        List<Product> hotList = productService.findHotList();
        System.out.println(hotList);
    }

    @Test
    public void findById(){
        Product product = productService.findById(10000003);
        System.out.println(product);
    }

}

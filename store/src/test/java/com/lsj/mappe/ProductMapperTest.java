package com.lsj.mappe;

import com.lsj.domain.Product;
import com.lsj.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList(){
        List<Product> hotList = productMapper.findHotList();
        System.out.println(hotList);
    }

    @Test
    public void findById(){
        Product product = productMapper.findById(10000002);
        System.out.println(product);
    }

}

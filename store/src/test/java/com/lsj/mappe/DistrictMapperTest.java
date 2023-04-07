package com.lsj.mappe;

import com.lsj.domain.District;
import com.lsj.mapper.DistrictMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictMapperTest {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for (District district : list) {
            System.out.println(district);
        }
    }

    @Test
    public void findByCode(){
        String name = districtMapper.findByCode("620000");
        System.out.println(name);
    }
}

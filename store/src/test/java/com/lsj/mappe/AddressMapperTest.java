package com.lsj.mappe;

import com.lsj.domain.Address;
import com.lsj.domain.District;
import com.lsj.domain.User;
import com.lsj.mapper.AddressMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(5);
        address.setPhone("111223311");
        address.setName("sss");

        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(5);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> address = addressMapper.findByUid(5);
        System.out.println(address);
    }

    @Test
    public void findByAid(){
        Address address = addressMapper.findByAid(6);
        System.out.println(address);
    }

    @Test
    public void updateNonDefault(){
        Integer integer = addressMapper.updateNonDefault(5);
        System.out.println(integer);
    }

    @Test
    public void updateDefaultByAid(){
        Integer integer = addressMapper.updateDefaultByAid(6, "管理员", new Date());
        System.out.println(integer);
    }

    @Test
    public void deleteByAid(){
        Integer integer = addressMapper.deleteByAid(5);
        System.out.println(integer);
    }

    @Test
    public void findLastModified(){
        Address address = addressMapper.findLastModified(5);
        System.out.println(address);
    }
}

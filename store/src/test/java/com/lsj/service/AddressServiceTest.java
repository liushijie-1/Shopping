package com.lsj.service;

import com.lsj.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("11999");
        address.setName("zzz");

       addressService.addNewAddress(5,"管理员",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(6,5,"刘世杰");
    }

    @Test
    public void delete(){
        addressService.delete(4,5,"管理员");
    }

}

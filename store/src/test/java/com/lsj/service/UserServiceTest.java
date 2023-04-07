package com.lsj.service;

import com.lsj.domain.User;
import com.lsj.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void regTest(){
        try {
            User user =new User();
            user.setUsername("张三002");
            user.setPassword("aaa");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            //获取类对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = userService.login("张三002", "aaa");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(1,"管理员","aaa","123");
    }

    @Test
    public void getByUid(){
        User user = userService.getByUid(1);
        System.out.println(user);
    }

    @Test
    public void changeInfo(){
        User user =new User();
        user.setPhone("13211111111");
        user.setEmail("aaa@qq.com");
        user.setGender(0);
        userService.changeInfo(5,"管理员",user);
    }


    @Test
    public void changeAvatar(){
        userService.changeAvatar(5,"aaa.png","小李");
    }
}

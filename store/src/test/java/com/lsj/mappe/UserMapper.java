package com.lsj.mappe;

import ch.qos.logback.core.util.SystemInfo;
import com.lsj.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.Date;

@SpringBootTest
public class UserMapper {

    @Autowired
    private com.lsj.mapper.UserMapper userMapper;

    @Test
    public void inster() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");

        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(3, "123", "aaa",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(3));
    }

    @Test
    public void updateInfoByUid(){
        User user =new User();
        user.setUid(5);
        user.setPhone("137000000000");
        user.setEmail("aaa@aa.com");
        user.setGender(1);

        Integer integer = userMapper.updateInfoByUid(user);
        System.out.println(integer);

    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(5,"static/images/index/404_img1.png","管理员",new Date());
    }
}
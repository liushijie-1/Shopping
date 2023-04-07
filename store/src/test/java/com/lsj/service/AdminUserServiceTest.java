package com.lsj.service;

import com.lsj.domain.AdminUser;
import com.lsj.domain.OrderItem;
import com.lsj.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.nio.cs.ext.SJIS_0213;

import java.util.List;

@SpringBootTest
public class AdminUserServiceTest {
    @Autowired
    private IAdminUserService adminUserService;

    @Test
    public void findByUserName(){
        AdminUser adminUser = adminUserService.findByUserName("123", "111");
        System.out.println(adminUser);
    }

    @Test
    public void select(){
        List<UserVO> userVOS = adminUserService.selectAll();
        System.out.println(userVOS);
    }

    @Test
    public void selet(){
        List<OrderItem> select = adminUserService.select();
        System.out.println(select);
    }

    @Test
    public void selectById(){
        AdminUser adminUser = adminUserService.selectById(2);
        System.out.println(adminUser);
    }
}

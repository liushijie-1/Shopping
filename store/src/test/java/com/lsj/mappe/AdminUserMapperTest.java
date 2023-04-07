package com.lsj.mappe;

import com.lsj.domain.Address;
import com.lsj.domain.AdminUser;
import com.lsj.domain.OrderItem;
import com.lsj.domain.User;
import com.lsj.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdminUserMapperTest {
    @Autowired
    private com.lsj.mapper.AdminUserMapper adminUserMapper;

    @Test
    public void ByUsername(){
        AdminUser byUserName = adminUserMapper.findByUserName("123");
        System.out.println(byUserName);
    }

    @Test
    public void selectAll(){
        List<UserVO> userVOS = adminUserMapper.selectAll();
        System.out.println(userVOS);
    }

    @Test
    public void selectByUid(){
        List<Address> list = adminUserMapper.selectByUid(5);
        System.out.println(list);
    }

    @Test
    public void select(){
        List<OrderItem> select = adminUserMapper.select();
        System.out.println(select);
    }

    @Test
    public void selectById(){
        AdminUser adminUser = adminUserMapper.selectById(2);
        System.out.println(adminUser);
    }

}

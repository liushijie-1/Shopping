package com.lsj.service.impl;

import com.lsj.domain.Address;
import com.lsj.domain.AdminUser;
import com.lsj.domain.OrderItem;
import com.lsj.domain.User;
import com.lsj.mapper.AdminUserMapper;
import com.lsj.service.IAdminUserService;
import com.lsj.service.ex.DeleteException;
import com.lsj.service.ex.PasswordNotMatchException;
import com.lsj.service.ex.UpdateException;
import com.lsj.service.ex.UserNotFoundException;
import com.lsj.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;


    @Override
    public AdminUser findByUserName(String username, String password) {
        AdminUser result = adminUserMapper.findByUserName(username);
        if (result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        if (!result.getPassword().equals(password)){
            throw new PasswordNotMatchException("密码验证失败");
        }
        AdminUser adminUser=new AdminUser();
        adminUser.setUid(result.getUid());
        adminUser.setUsername(result.getUsername());

        return adminUser;
    }

    @Override
    public List<UserVO> selectAll() {
        List<UserVO> userVOS = adminUserMapper.selectAll();
        for (UserVO userVO : userVOS) {
            List<Address> addresses = adminUserMapper.selectByUid(userVO.getUid());
            for (Address address : addresses) {
                userVO.setAid(address.getAid());
                userVO.setProvinceName(address.getProvinceName());
                userVO.setCityName(address.getCityName());
                userVO.setAreaName(address.getAreaName());
                userVO.setZip(address.getZip());
            }
        }
        return userVOS;
    }

    @Override
    public Integer deleteByUid(Integer uid) {
        Integer num = adminUserMapper.delByUid(uid);
        if (num!=1){
            throw new DeleteException("删除用户数据失败");
        }
        return num;
    }

    @Override
    public List<OrderItem> select() {
        List<OrderItem> orderItems = adminUserMapper.select();
        return orderItems;
    }

    /*根据用户ID获取用户信息*/
    @Override
    public AdminUser selectById(Integer uid) {
        AdminUser adminUser = adminUserMapper.selectById(uid);
        return adminUser;
    }

    /*修改用户信息*/
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        AdminUser result = adminUserMapper.selectById(uid);
        if (result ==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = adminUserMapper.updateInfoByUid(user);
        if (rows!=1){
            throw new UpdateException("更新数据时产生的未知异常");
        }
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        Integer rows = adminUserMapper.updatePasswordByUid(uid, username, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据是产生的异常");
        }
    }
}

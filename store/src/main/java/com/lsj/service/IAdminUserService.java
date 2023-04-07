package com.lsj.service;

import com.lsj.domain.AdminUser;
import com.lsj.domain.OrderItem;
import com.lsj.domain.User;
import com.lsj.vo.UserVO;

import java.util.List;

public interface IAdminUserService {

    /*
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return 管理员数据
     * @Date 17:18 2023/3/27
     **/
    AdminUser findByUserName(String username,String password);

    List<UserVO> selectAll();

    Integer deleteByUid(Integer uid);

    List<OrderItem> select();

    /*查询管理员信息*/
    AdminUser selectById(Integer uid);

    /*
     * @Param 修改用户资料
     * @param uid 用户ID
     * @param username 用户名
     * @param user 用户数据
     * @Date 21:34 2023/3/28
     **/
    void changeInfo(Integer uid, String username, User user);

    /*
     * 修改用户密码
     * @Param  uid 用户UID
     * @Param  username 修改者用户名
     * @Param  oldPassword 旧密码
     * @Param  newPassword 新密码
     * @return 影响行数
     * @Date 21:44 2023/3/28
     **/
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

}

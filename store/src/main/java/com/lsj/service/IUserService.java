package com.lsj.service;

import com.lsj.domain.User;

public interface IUserService {

    /*
    * 用户注册
    * */
    void reg(User user);

    /*
    * 用户登录
     * @Param username 用户名
     * @Param password 密码
     * @return 当前匹配的用户数据，如果没有则返回null值
     * @Date 19:43 2023/3/20
    * */
    User login(String username,String password);

    /*
    *修改用户密码
     * @Param  uid 用户UID
     * @Param  username 修改者用户名
     * @Param  oldPassword 旧密码
     * @Param  newPassword 新密码
     * @return 影响行数
     * @Date 21:31 2023/3/20
     **/
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /*
    * *根据用户ID查询用户信息
     * @Param uid 用户ID
     * @return 用户的数据
     * @Date 21:36 2023/3/20
     **/
    User getByUid(Integer uid);

    /*
    * *
     * @Param 修改用户资料
     * @param uid 用户ID
     * @param username 用户名
     * @param user 用户数据
     * @Date 22:25 2023/3/20
     **/
    void changeInfo(Integer uid,String username,User user);

    /*
     * @Param 修改用户的头像
     * @param uid 用户的ID
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     * @Date 15:09 2023/3/21
     **/
    void changeAvatar(Integer uid,String avatar,String username);
}

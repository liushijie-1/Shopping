package com.lsj.mapper;

import com.lsj.domain.Address;
import com.lsj.domain.AdminUser;
import com.lsj.domain.OrderItem;
import com.lsj.domain.User;
import com.lsj.vo.UserVO;

import java.util.Date;
import java.util.List;

/*管理员持久层*/
public interface AdminUserMapper {

    /*管理员登录*/
    AdminUser findByUserName(String username);

    /*查看管理员信息*/
    AdminUser selectById(Integer uid);

    /*管理员查询所有用户信息*/
    List<UserVO> selectAll();

    /*根据查询到的用户信息UID查询用户的默认收货地址*/
    List<Address> selectByUid(Integer uid);

    /*根据UID删除用户信息*/
    Integer delByUid(Integer uid);

    /*查询所有订单数据*/
    List<OrderItem> select();

    /*
     * 更新用户信息
     * @param user
     * @return 影响的行数
     * @Date 21:31 2023/3/28
     **/
    Integer updateInfoByUid(User user);

    /*
     * 根据用户名UID来修改密码
     * @Param uid 用户的ID
     * @param password 用户输入的新密码
     * @param modifiedUser 表示修改的执行者
     * @param  modifiedTime 表示修改数据的时间
     * @return 返回值为受影响的行数
     * @Date 21:43 2023/3/28
     **/
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

}

package com.lsj.mapper;

import com.lsj.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;


public interface UserMapper {

    Integer insert(User user);

    User findByUserName(String username);

    /*
     *根据用户名UID来修改密码
     * @Param uid 用户的ID
     * @param password 用户输入的新密码
     * @param modifiedUser 表示修改的执行者
     * @param  modifiedTime 表示修改数据的时间
     * @return 返回值为受影响的行数
     * @Date 17:25 2023/3/20
     **/
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /*
    * 根据用户ID查询信息
     * @Param [uid]用户的ID
     * @return 如果找到则返回对象，反之返回null
     * @Date 17:30 2023/3/20
     **/
    User findByUid(Integer uid);

    /*
     * 更新用户信息
     * @param user
     * @return 影响的行数
     * @Date 14:53 2023/3/21
     **/
    Integer updateInfoByUid(User user);

    /*
     * @Param 根据用户的UID修改用户的头像
     * @param uid 用户UID
     * @param avatar 图片
     * @param modifiedUser
     * @param modifiedTime
     * @return
     * @return java.lang.Integer
     * @Date 14:56 2023/3/21
     **/
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}

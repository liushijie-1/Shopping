package com.lsj.service.impl;

import com.lsj.domain.User;
import com.lsj.mapper.UserMapper;
import com.lsj.service.IUserService;
import com.lsj.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /*
    * 注册功能
    * */
    @Override
    public void reg(User user) {
        //通过user参数获取传递过来的username
        String username =user.getUsername();
        //调用find方法判断用户是否被注册过
        User byUserName = userMapper.findByUserName(username);

        if (byUserName!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //对密码进行加密处理 md5算法形式
        //串+password+串---md5加密算法，连续加载5次
        //盐值+password+盐值---随机字符串
        String oldpassword = user.getPassword();
        //获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //记录盐值
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理
        String md5password = getMD5password(oldpassword, salt);
        //将加密后的密码补全到user对象中
        user.setPassword(md5password);
        //补全数据：is_delete=0
        user.setIsDelete(0);
        //补全数据：日志字段
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date =new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //执行注册业务功能
        Integer rows = userMapper.insert(user);
        if (rows!=1){
            throw new InsterException("用户在注册过程中出现未知异常");
        }
    }

    /*
    * 登录
    * */
    @Override
    public User login(String username, String password) {
        //根据用户名查询用户数据是否存在，不存在则抛出异常
        User result = userMapper.findByUserName(username);
        if (result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否匹配
        //获取数据库中存储的密码
        String password1 = result.getPassword();
        //获取上一次注册生成的盐值
        String salt = result.getSalt();
        //将用户输入的密码用md5算法进行加密
        String md5password = getMD5password(password, salt);
        //将密码进行比较
        if(!md5password.equals(password1)){
            throw new PasswordNotMatchException("用户名密码错误");
        }
        //判断is_delete字段的值是否为1，表示被标记为删除
        if (result.getIsDelete()==1&&result.getIsDelete()!=null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //仅返回所需的数据，提高系统性能
        User user =new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    //修改密码
    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户名数据不存在");
        }
        //原始密码和数据库密码进行比较
        String oldMd5password = getMD5password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5password)){
            throw new PasswordNotMatchException("密码错误");
        }
        //将新的密码加密然后保存到数据库中
        String newMd5password = getMD5password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5password, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据是产生的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user =new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;
    }

    /*
    * user对象中的数据phone、email、gender，手动的将UID、username封装到对象中
    * */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows!=1){
            throw new UpdateException("更新数据时产生的未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
         //查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if (result ==null ||result.getIsDelete()==1){
            throw new UserNotFoundException("用户信息不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新用户头像产生未知的异常");
        }
    }

    /*
    * 定义一个md5加密算法加密处理
    * */
    private String getMD5password(String password,String salt){
        //md5加密算法调用
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //返回加密后的密码
        return password;
    }
}

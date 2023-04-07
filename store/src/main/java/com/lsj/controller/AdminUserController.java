package com.lsj.controller;

import com.lsj.domain.AdminUser;
import com.lsj.domain.OrderItem;
import com.lsj.domain.User;
import com.lsj.service.IAdminUserService;
import com.lsj.service.ex.DeleteException;
import com.lsj.util.JsonResult;
import com.lsj.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("admins")
@CrossOrigin
public class AdminUserController extends BaseController{
    @Autowired
    private IAdminUserService adminUserService;

    @RequestMapping("adminlonge")
    public JsonResult<AdminUser> findByUserName(String username, String password, HttpSession session){
        AdminUser data = adminUserService.findByUserName(username, password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("selectUser")
    public JsonResult<List<UserVO>> selectUser(){
        List<UserVO> data = adminUserService.selectAll();
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{uid}/delete")
    public JsonResult<Void> deleteByUid(@PathVariable("uid") Integer uid){
        Integer rows = adminUserService.deleteByUid(uid);
        if (rows!=1){
            throw new DeleteException("删除用户数据失败");
        }
        return new JsonResult<>(OK);
    }

    @RequestMapping("orders")
    public JsonResult<List<OrderItem>> selectOrder(){
        List<OrderItem> data = adminUserService.select();
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<AdminUser> selectById(HttpSession session){
        Integer uid = getuidFromSession(session);
        AdminUser data = adminUserService.selectById(uid);
        return new JsonResult<>(OK,data);
    }

    /*修改管理员信息*/
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        //user对象有4部分的数据：username，phone，email，gender
        //UID数据需要封装到user对象
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        adminUserService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changPassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        adminUserService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
}

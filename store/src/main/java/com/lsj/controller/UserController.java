package com.lsj.controller;

import com.lsj.controller.ex.*;
import com.lsj.domain.User;
import com.lsj.service.IUserService;
import com.lsj.service.ex.InsterException;
import com.lsj.service.ex.UsernameDuplicatedException;
import com.lsj.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        //想session对象中完成数据的绑定
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        //获取session中绑定的数据
        /*System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));*/
        return new JsonResult<>(OK, data);
    }

    //修改密码
    @RequestMapping("change_password")
    public JsonResult<Void> changPassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        //user对象有4部分的数据：username，phone，email，gender
        //UID数据需要封装到user对象
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /*设置上传文件的最大值*/
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /*限制上传文件类型*/
    public static final List<String> AVATAR_TYPE =new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /*
     * MultipartFile接口是springMVC提供的一个接口，这个接口包装了获取文件的类型的数据
     * 在Springboot只需在处理请求的方法参数上声明一个参数类型为 MultipartFile 的参数
     * 然后springboot自动将传递给服务的文件数据赋值给这个参数
     * @param session
     * @param file
     * @return
     * @return com.lsj.util.JsonResult<java.lang.String>
     * @Date 15:51 2023/3/21
     **/
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()){
            throw  new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出异常");
        }
        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        //文件夹上传的文件 ../upload/**.png
        String parent = session.getServletContext().getRealPath("upload");
        //file指向这个路径，file是否存在
        File dir =new File(parent);
        if (!dir.exists()){//检测目录是否存在
            dir.mkdirs(); //创建当前目录
        }
        //获取到这个文件名称，UUID工具将生成一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;

        File dest =new File(dir,filename);//是一个空文件
        //将参数file中的数据写入到这个空文件中
        try {
            file.transferTo(dest);//将file文件中的数据写入到dest中
        } catch (IOException e) {
            throw new FileUploadIOException("文件读异常");
        } catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路/upload/test.png
        String avatar ="/upload/"+filename;
        userService.changeAvatar(uid,avatar,username);
        //返回用户头像的路径给前端页面，用于头像的展示使用
        return new JsonResult<>(OK,avatar);
    }

   /* @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result =new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
           result.setState(4000);
           result.setMessage("用户名被占用");
        }catch (InsterException e){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }
        return result;
    }*/

}

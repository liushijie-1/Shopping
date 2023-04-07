package com.lsj.controller;

import com.lsj.controller.ex.*;
import com.lsj.service.ex.*;
import com.lsj.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.security.Security;

/*控制层类的基类*/
public class BaseController {
    /*操作成功状态码*/
    public static final int OK =200;
    //请求处理发方法，这个方法的返回值就是要传递到前端的数据
    //自动将异常对象传递给此方法参数列表
    //当项目中产生了异常，将统一拦截到此方法中，这个方法就充当是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler({ServiceException.class,UpdateException.class})//统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result =new JsonResult<>();
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if (e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户数据不存在产生");
        }else if (e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("用户名密码错误");
        }else if (e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限");
        }else if (e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户收货地址数据不存在");
        }else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("收货地址数据非法访问异常");
        }else if (e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("尝试查询商品信息不存");
        }else if (e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("购物车数据不存在");
        }else if (e instanceof InsterException){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据是产生异常");
        }else if (e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除数据异常");
        }else if (e instanceof FileEmptyException){
            result.setState(6000);
            result.setMessage("文件为空的异常");
        }else if (e instanceof FileSizeException){
            result.setState(6001);
            result.setMessage("文件大小异常");
        }else if (e instanceof FileTypeException){
            result.setState(6000);
            result.setMessage("文件格式异常");
        }else if (e instanceof FileStateException){
            result.setState(6000);
            result.setMessage("文件状态异常");
        }else if (e instanceof FileUploadIOException){
            result.setState(6000);
            result.setMessage("读取文件异常");
        }
        return result;
    }

    /*
    * 获取session对象中的UID
    * */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /*
    * 获取当前登录对象的username
    * */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}

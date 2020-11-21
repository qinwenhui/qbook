package cn.qinwh.qbooksystem.utils;

import cn.qinwh.qbooksystem.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUserUtils {

    public static User getLoginUser(){
        //获取session
        HttpSession session = getRequest().getSession();
        //获取session里的token信息
        User user = (User) session.getAttribute("user");
        //根据token从redis获取数据
        return user;
    }

    public static void setLoginUser(User user){
        //获取session
        HttpSession session = getRequest().getSession();
        //设置用户信息到session
        session.setAttribute("user", user);
    }

    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    private static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }
}

package cn.qinwh.qbooksystem.interceptor;

import cn.qinwh.qbookcommon.utils.ReturnMsg;
import cn.qinwh.qbooksystem.annotation.manager.NoLoginStoreManager;
import cn.qinwh.qbooksystem.constant.RedisConst;
import cn.qinwh.qbooksystem.entity.SysPermission;
import cn.qinwh.qbooksystem.entity.SysUser;
import cn.qinwh.qbooksystem.service.SysPermissionService;
import cn.qinwh.qbooksystem.utils.LoginUserUtils;
import cn.qinwh.qbooksystem.utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@DependsOn
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private NoLoginStoreManager noLoginStoreManager;
    @Autowired
    @Qualifier("sysPermissionServiceImpl")
    private SysPermissionService sysPermissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("权限拦截器");
        String url = request.getServletPath();
        //免登录的接口地址
        List<String> noLoginPaths = noLoginStoreManager.getNoLoginPaths();
        for (String noLoginUrl : noLoginPaths){
            if(noLoginUrl.equals(url)){
                //该url属于免登录的，直接放行
                return true;
            }
        }
        //获取用户请求发送过来的token
        String userToken = request.getHeader("Token");
        System.out.println("11111"+request.getServletPath()+" "+userToken);
        if(userToken == null){
            //token无效,拒绝访问
            loginFalse(response);
            return false;
        }
        //通过与redis中保存的token比较
        SysUser user = RedisUtils.get(userToken, SysUser.class);
        if(user == null){
            //token失效,拒绝访问
            loginFalse(response);
            return false;
        }
        //获取该用户的所有权限地址
        List<SysPermission> permissionList = sysPermissionService.getUserPermission(user.getId());
        //验证
        for(SysPermission permission : permissionList){
            if(url.equals(permission.getUrl())){
                //可以访问,更新Redis中保存的token时效
                RedisUtils.set(userToken, user, RedisConst.TOKEN_SAVE_TIME);
                //验证通过，将用户信息从redis拿出放到session方便后续使用
                LoginUserUtils.setLoginUser(userToken, user);
                return true;
            }
        }
        access(response);
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
            throws Exception {
        //执行handler之后
        System.out.println("权限拦截器,执行handler之后");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        //执行
        System.out.println("权限拦截器---");
    }

    /**
     * 拒绝访问
     * @param response
     * @throws Exception
     */
    private void access(HttpServletResponse response) throws Exception {
        response.setStatus(200);
        ReturnMsg json = new ReturnMsg(ReturnMsg.ACCESS, "没有权限", null);
        ObjectMapper mapper=new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(json);
        response.getWriter().write(jsonStr);
    }

    /**
     * 未登录
     * @param response
     * @throws Exception
     */
    private void loginFalse(HttpServletResponse response) throws Exception {
        response.setStatus(200);
        ReturnMsg json = new ReturnMsg(ReturnMsg.LOGIN_FALSE, "登录已过期", null);
        ObjectMapper mapper=new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(json);
        response.getWriter().write(jsonStr);
    }
}

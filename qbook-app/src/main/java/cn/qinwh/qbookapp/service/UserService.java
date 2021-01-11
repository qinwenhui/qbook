package cn.qinwh.qbookapp.service;

import cn.qinwh.mybatis.qservice.common.BaseService;
import cn.qinwh.qbookapp.entity.User;
import cn.qinwh.qbookcommon.utils.ReturnMsg;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends BaseService<User> {

    /**
    * @Description: 登录
    * @Param: [username, password]
    * @return: boolean
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    ReturnMsg login(HttpServletRequest request, String username, String password);

    /**
     * @Description: 注册
     * @Param: [username, password]
     * @return: boolean
     * @Author: qinwh
     * @Date: 2021/1/11
     */
    ReturnMsg register(HttpServletRequest request, String username, String password);
}
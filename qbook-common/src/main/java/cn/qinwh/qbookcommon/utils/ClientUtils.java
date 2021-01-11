package cn.qinwh.qbookcommon.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: qbook
 * @description: 客户端信息工具
 * @author: qinwh
 * @create: 2021-01-11 14:53
 **/
public class ClientUtils {

    /**
    * @Description: 获取客户端IP
    * @Param: [request]
    * @return: java.lang.String
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
    * @Description: 获取用户客户端信息
    * @Param: [request]
    * @return: void
    * @Author: qinwh
    * @Date: 2021/1/11
    */
    public static String getPlatform(HttpServletRequest request){

        /**User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器
         能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等*/
        String agent= request.getHeader("user-agent");
        //客户端类型常量
        String type = "";
        if(agent.contains("iPhone")||agent.contains("iPod")||agent.contains("iPad")){
            type = "ios";
        } else if(agent.contains("Android") || agent.contains("Linux")) {
            type = "apk";
        } else if(agent.indexOf("micromessenger") > 0){
            type = "wx";
        }else {
            type = "pc";
        }
        return type;
    }
    
}

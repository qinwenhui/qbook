package cn.qinwh.qbookcommon.utils;

/**
 * @program: qbook
 * @description: 字符串工具类
 * @author: qinwh
 * @create: 2020-12-30 15:37
 **/
public class StringUtils {

    public static boolean isEmpty(String str){
        if(str == null || str.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str){
        if(str == null || str.isEmpty()){
            return false;
        }
        return true;
    }
}

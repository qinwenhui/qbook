package cn.qinwh.qbookcommon.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: qbook
 * @description: 属性操作工具
 * @author: qinwh
 * @create: 2021-01-16 14:36
 **/
@Slf4j
public class PropertiesUtils {

    public static <T> T copy(Object src, T target, Class<T> targetType) {
        //默认属性名称相同时复制
        try {
            target = targetType.newInstance();
            Class clazz = src.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields){
                //直接执行目标对象的set方法，如果不存在这个方法，则不复制
                try{
                    //set方法
                    Method method = targetType.getMethod("set"+upperCase(field.getName()), field.getType());
                    //参数
                    field.setAccessible(true);
                    Object param = field.get(src);
                    //执行
                    method.invoke(target, param);
                } catch (NoSuchMethodException e) {
                    log.info("目标没有该属性的set方法：" + field.getName());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return target;
    }

    public static <T> T copy(Object src, T target, Class<T> targetType, Map<String, String> converter) {
        //默认属性名称相同时复制
        try {
            target = targetType.newInstance();
            Class clazz = src.getClass();
            Field[] fields = clazz.getDeclaredFields();
            //经过转换器转换过的属性，后面再次出现时不再按默认同名方式转换，这里记录一下
            Map<String, String> noConverProperty = new HashMap<>();
            for (Field field: fields){
                //直接执行目标对象的set方法，如果不存在这个方法，则不复制
                try{
                    field.setAccessible(true);
                    //根据转换器获取目标属性名
                    String targetPropertyName = converter.get(field.getName());
                    if(targetPropertyName != null){
                        targetType.getMethod("set"+upperCase(targetPropertyName), field.getType()).invoke(target, field.get(src));
                        noConverProperty.put(targetPropertyName, field.getName());
                    }else{
                        if(noConverProperty.get(field.getName()) == null){
                            targetType.getMethod("set"+upperCase(field.getName()), field.getType()).invoke(target, field.get(src));
                        }else{
                            log.info(field.getName() + "属性已经在转换器中转换，不进行同名属性的复制操作");
                        }
                    }
                } catch (NoSuchMethodException e) {
                    log.info("目标没有该属性的set方法：" + field.getName());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return target;
    }

    /**
    * @Description: 首字母大写
    * @Param: [str]
    * @return: java.lang.String
    * @Author: qinwh
    * @Date: 2021/1/16
    */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}

package cn.qinwh.qbooksystem.annotation.config;

import cn.qinwh.qbooksystem.annotation.NoLogin;
import cn.qinwh.qbooksystem.annotation.manager.NoLoginStoreManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@Component
public class NoLoginHandler implements BeanPostProcessor {

    private NoLoginStoreManager noLoginStoreManager = new NoLoginStoreManager();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //再创建NoLoginStoreManagerd的bean之前先赋值
        if("noLoginStoreManager".equals(beanName)){
            return noLoginStoreManager;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=============>" + beanName);
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        //检查该类是否存在Controller注解
        Controller controller = AnnotationUtils.findAnnotation(bean.getClass(), Controller.class);
        if(controller != null){
            if (methods != null) {
                //获取类Controller的RequestMapping注解的值
                RequestMapping requestMapping = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
                if(requestMapping == null || requestMapping.value().length == 0){
                    return bean;
                }
                String controllerValue = requestMapping.value()[0];
                for (Method method : methods) {
                    //获取该方法mapping映射的值
                    String requestValue = getRequestMappingAnnotationValueByMethod(method);
                    if(requestValue != null){
                        //再检查该方法是否存在NoLogin注解
                        NoLogin noLogin = AnnotationUtils.findAnnotation(method, NoLogin.class);
                        if (noLogin != null) {
                            //获取带有该注解的接口地址
                            String noLoginPath = controllerValue + requestValue;
                            //将接口地址保存到不需要验证接口管理器中
                            noLoginStoreManager.addNoLoginPath(noLoginPath);
                        }else{
                            //该接口不存在NoLogin注解，那判断控制器是否存在NoLogin注解
                            NoLogin classNoLogin = AnnotationUtils.findAnnotation(bean.getClass(), NoLogin.class);
                            if(classNoLogin != null){
                                //控制器层存在该注解，将接口地址保存到不需要验证接口管理器中
                                String noLoginPath = controllerValue + requestValue;
                                noLoginStoreManager.addNoLoginPath(noLoginPath);
                            }
                        }
                    }

                }
            }
        }

        return bean;
    }

    private String getRequestMappingAnnotationValueByMethod(Method method){
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        if(getMapping != null && getMapping.value().length != 0){
            return getMapping.value()[0];
        }
        if(postMapping != null && postMapping.value().length != 0){
            return postMapping.value()[0];
        }
        if(putMapping != null && putMapping.value().length != 0){
            return putMapping.value()[0];
        }
        if(deleteMapping != null && deleteMapping.value().length != 0){
            return deleteMapping.value()[0];
        }
        if(requestMapping != null && requestMapping.value().length != 0){
            return requestMapping.value()[0];
        }
        return null;
    }
}

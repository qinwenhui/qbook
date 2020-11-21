package cn.qinwh.qbooksystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
    //拦截器
    @Autowired
    @Qualifier(value="globalHandlerInterceptor")
    private HandlerInterceptor globalInterceptor;
    @Autowired
    @Qualifier(value="permissionInterceptor")
    private HandlerInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor);
        registry.addInterceptor(permissionInterceptor);
    }
}

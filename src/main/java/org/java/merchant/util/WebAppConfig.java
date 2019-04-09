package org.java.merchant.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String>Actioncondition=new ArrayList<>();
        Actioncondition.add("/login/exit");
        Actioncondition.add("/login/instalLoad");
        Actioncondition.add("/login/personalLoad");
        Actioncondition.add("/gocar/carList");
        Actioncondition.add("/insue/issueLoad");
        Actioncondition.add("/gocar/carDetails");
        Actioncondition.add("/insue/carDetails");
        Actioncondition.add("login/instalLoad");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(Actioncondition);
    }
}

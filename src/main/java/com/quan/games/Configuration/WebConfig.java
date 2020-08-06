package com.quan.games.Configuration;

import com.thetransactioncompany.cors.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.FilterRegistration;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 拦截器配置类
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }

    /**
     * 跨域CORS配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET","DELETE", "PUT","PATCH")
                .allowedOrigins("http://...")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 视图控制器配置
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("admin_login.html");
    }

    /**
     * 处理请求参数带有日期格式
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            @Override
            public String print(Date date, Locale locale) {
                return Long.valueOf(date.getTime()).toString();
            }

            @Override
            public Date parse(String date, Locale locale) throws ParseException {
                return new Date(Long.parseLong(date));
            }
        });
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("views/**");
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CORSFilter());
        registrationBean.setName("corsFilter");
        registrationBean.setOrder(1);
        return  registrationBean;
    }
}

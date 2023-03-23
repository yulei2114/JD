package com.jd.servlet.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.TemplateResolution;

import java.nio.charset.StandardCharsets;

@Configuration
//注解配置处理器映射器和处理器适配器
@EnableWebMvc
//注册Controler
@ComponentScan(basePackages = {"com.jd.servlet.controller"})
//继承
public class ConfigWebContext implements WebMvcConfigurer {
    //配置视图解析器
//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//
//        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
//        viewResolver.setPrefix("/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//
//    }
    /*
        使用ThymeLeaf模板，替换原有的视图解析器

     */
    @Bean
    //视图解析处理
    public SpringResourceTemplateResolver templateResolver(){

        SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        //要解析的模板，thymeleaf默认为html
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //设置缓存，thymeleaf默认开启缓存
        templateResolver.setCacheable(true);
        return templateResolver;

    }
    @Bean
    //模板引擎
    public SpringTemplateEngine templateEngine(){

        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;

    }
    @Bean
    public ViewResolver viewResolver (){

        ThymeleafViewResolver thymeleafViewResolver=new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return thymeleafViewResolver;

    }

    //处理静态资源
    //方式一：启用服务器默认处理静态资源的Servlet
    //相当于在DispatcherServlet配置文件中配置<mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

       configurer.enable();

    }

    //方式二：重写addResourceHandlers()方法，在spring3.0.4版本后，spring已经有了处理静态资源的能力
    //相当于在DispatcherServlet配置文件中配置<mvc:resources mapping="/static/**" location="/static/,/aa/"/>
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("/static/");

    }
     */
}

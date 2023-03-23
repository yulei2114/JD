package com.jd.servlet;

import com.jd.DaoAndService.SpringAndMybatisContext;
import com.jd.servlet.config.ConfigWebContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ServletContainerInit implements WebApplicationInitializer {
    //利用spi机制自定义类来代替web.xml配置servlet
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //创建前端控制器所需的容器，可以是基于注解类，也可以是基于配置文件
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.register(ConfigWebContext.class);
        //创建DispatcherServlet,将容器传给DispatcherServlet
        DispatcherServlet dispatcherServlet=new DispatcherServlet(context);
        //将DispatcherServlet添加到服务器创建的ServletContext中
        ServletRegistration.Dynamic dispatcher= servletContext.addServlet("dispatcher",dispatcherServlet);
        //设置映射路径
        dispatcher.addMapping("/");
        //设置启动时机
        dispatcher.setLoadOnStartup(1);
        //将集成的mybatis容器绑定到ServletContext上
        servletContext.setAttribute("mybatis-context", SpringAndMybatisContext.getSpringAndMybatisContext().getApplicationContext());
    }
}

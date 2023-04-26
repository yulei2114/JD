package com.jd.servlet.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jd.dao.CustomerMapper;
import com.jd.entity.Customer;
import com.jd.servlet.interceptor.LoginHandlerInterceptor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.TemplateResolution;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
//开启事务管理
@EnableTransactionManagement
//注解配置处理器映射器和处理器适配器
@EnableWebMvc//当在DispatcherServlet容器中配置HandlerInterceptor（拦截器），可以采用API配置，这里采用了配置类
//注册Controler
@ComponentScan(basePackages = {"com.jd.servlet.controller","com.jd.service.serviceImpl"})
@MapperScan("com.jd.dao")
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
        templateResolver.setPrefix("/dynamic/");
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
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver){

        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;

    }
    @Bean
    //thymeleaf视图解析器
    public ViewResolver viewResolver (SpringTemplateEngine templateEngine){

        ThymeleafViewResolver thymeleafViewResolver=new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine);
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
    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/JD/**").addResourceLocations("/JD/");

    }

     */

    //配置拦截器,直接注册bean
    /*
    @Bean
    @Order(2)指定拦截器的注册顺序，将决定拦截器的拦截顺序
    public MappedInterceptor loginInterceptor(){
        //这个将适配到所有的Hnadler,也就是拦截所有请求
        return new MappedInterceptor(null,new LoginHandlerInterceptor());
    }
     */
    /*
    @Bean
    public MappedInterceptor loginInterceptor2(){

        //这个指定拦截路径，一般也是这样用
        MappedInterceptor interceptor=new MappedInterceptor(new String[]{"/login"},new String[]{"/notclude"},new LoginHandlerInterceptor());
        //第一个字符串数组将指定拦截的路径，第二字符串数组将指定不拦截的路径
        return interceptor;

    }
    */
    //除了直接注册为bean,还可以像配置静态资源处理器那样，实现WebMvcConfigurer接口，并重写addInterceptors()方法

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/jumpLogin").excludePathPatterns("/notclude");

    }
    /**
     * mybatis和spring集成，映射dao层接口生成代理对象注入到Service对象，将Service层对象租入到Controler
     */
    @Bean
    public DataSource dataSource () throws Exception {

        Properties properties=new Properties();
        InputStream inputStream= Resources.getResourceAsStream("druid.properties");
        properties.load(inputStream);
        DataSource dataSource= DruidDataSourceFactory.createDataSource(properties);
        return dataSource;

    }
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("classpath:com.jd.entity");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();

    }

//    @Bean
//    public CustomerMapper customerMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
//        MapperFactoryBean<CustomerMapper> customerMapper=new MapperFactoryBean<>();
//        customerMapper.setMapperInterface(com.jd.dao.CustomerMapper.class);
//        customerMapper.setSqlSessionFactory(sqlSessionFactory);
//        CustomerMapper mapper=customerMapper.getObject();
//        System.out.println("生成的代理类为："+mapper);
//        return mapper;
//    }
    /**
     * 配置spring事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {

        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;

    }
}

package com.jd.DaoAndService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAndMybatisContext {
    //将这个工具类对象，绑定到到服务器创建的ServletContext上，定义一个方法，单例模式去生成spring容器，这个容器将用于完成后台数据库操作
    //私有化构造器
    private SpringAndMybatisContext(){

    }
    //通过私有化构造其构造本身
    private static SpringAndMybatisContext springAndMybatisContext;
    //对外提供获取该对象的方法
    public static SpringAndMybatisContext getSpringAndMybatisContext(){
        if (springAndMybatisContext==null){
            springAndMybatisContext=new SpringAndMybatisContext();
        }
        return springAndMybatisContext;
    }
    //私有化容易，对外提供获取内置spring容器的方法
    private ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-mybatis.xml");
    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    /*
       内置一个spring容器，单例模式下，获得的属性也是唯一
    */

}

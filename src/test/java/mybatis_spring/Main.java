package mybatis_spring;

import mybatis_spring.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用 MyBatis-Spring 的其中一个主要原因是它允许 MyBatis 参与到 Spring 的事务管理中。
 * 而不是给 MyBatis 创建一个新的专用事务管理器，MyBatis-Spring 借助了 Spring 中的 DataSourceTransactionManager 来实现事务管理
 */
public class Main {
    public static void main(String[] args) {
        //创建IOC容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(
                "mybatis_spring_config/mybatis_spring.xml");
        Service service= (Service) applicationContext.getBean("serviceImpl");
        service.insertOne(100);

    }
}

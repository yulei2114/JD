package mybatis;

import org.apache.ibatis.io.Resources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 *
 * 1:spring定义了支持事务的接口PlatformTransactionManager,用于管理事务（事务的开启，回滚，提交），基于不同的操作数据库方式
 * 常用实现类：DataSourceTransactionManager,用于管理基于数据源的连接
 * 可以看懂接口定义的方法：分被用于开启事务，回滚，提交
 * public interface PLatfromTransactionManager{
 *      TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
 *
 *     void commit(TransactionStatus status) throws TransactionException;
 *
 *     void rollback(TransactionStatus status) throws TransactionException;
 *  }
 *2:TransactionDefinition接口:定义了事务的一些配置信息，比如事务隔离级别、事务超时时间、事务传播方式、是否是只读事务等等
 * 有一个常用子类：DefaultTransactionDefinition，也就是默认配置
 *3：TransactionStatus:定义了事务的状态
 * public interface TransactionStatus extends TransactionExecution, SavepointManager, Flushable {
 *     boolean hasSavepoint();
 *
 *     void flush();
 * }
 */
/*spring框架对于事务的支持，准确说是对事务管理的实现，spring-mybatis集成中，mybatis利用了DataSourceTrancactionManager事务管理器，
实现了对于mybatis本身默认，为SqlSession创建的事务的管理，也就是说我们不用在使用SqlSession时手动去调用commit()或者rollback()方法
        *      下面展示一个自己实现的事务管理器，加强理解

 */
public class MainSpringMybatis {
    //编程式事务管理,spring提供了事务管理，每一个从DataSource中获取的连接（），已经在，
    public static void main(String[] args) throws Exception {
        /*
            ！！！！重点：在spring提供的org.springframework.jdbc.core.JdbcTemplate数据库操作模板类中
            提供了对于spring事务的支持，在这个类中，本质上是spring提供的DataSourceUtils.getConnection(DataSource)这个静态方法
            spring官方文档来看：
                Helper class that provides static methods for obtaining JDBC Connections from a DataSource.
                Includes special support for Spring-managed transactional Connections, e.g. managed by DataSourceTransactionManager or JtaTransactionManager.
            意思就是说提供了spring事务管理的支持，所以直接从DataSource中获取连接，dataSource.getConnection()，获取的连接是不在spring事务管理之下
            要用spring提供的JdbcTemplate,或者DataSourceUtils.getConnection()

         */
        //提供数据源，这里用alibab的德鲁伊数据源
        Properties properties=new Properties();
        InputStream inputStream= Resources.getResourceAsStream("mybatis_config/druid/druid.properties");
        properties.load(inputStream);
        DataSource dataSource=com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(properties);
        //①：创建一个事务管理器,将获取连接的数据源通过构造器传给DataSourceTransactionManager实例
        PlatformTransactionManager transactionManager=new DataSourceTransactionManager(dataSource);
        //定义事务属性
        TransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
        //开启事务
        TransactionStatus transactionStatus= transactionManager.getTransaction(transactionDefinition);
        //获取连接，获取的连接已经在spring事务管理器的管理之下
        Connection connection= DataSourceUtils.getConnection(dataSource);
        //创建语句对象
        PreparedStatement preparedStatement=connection.prepareStatement("insert into test values (8) ");
        //更新后进行提交或者回滚，完全由开发人员实现
        preparedStatement.executeUpdate();
        transactionManager.commit(transactionStatus);
    }

}

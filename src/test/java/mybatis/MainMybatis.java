package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * ORM框架-mybatis框架中三种数据源
 * mybatis创建SqlSessionFactory时，对象之间存在以下关系：
 * Configuration--->(包括)Environment-->(包括)
 *                                          1:TransactionFactory
 *                                              根据<transactionManager type="JDBC"></transactionManager>
 *                                                  对应JdbcTransactiveFactory
 *                                                  <transactionManager type="MANAGED"></transactionManager>
 *                                                  对应ManagedTransactiveFactory
 *                                          2:DataSource
 * 1：POOLED->对应工厂-->PooledDataSourceFactory(工厂创建连接池数据源)->PooledDataSource：带连接池，mybatis提供了了PooledDataSource接口，继承了java。sql.Datasource接口
 *    并内置了一个UnpooledDataSource引用，在通过PooledDataSource获取连接时，本质上是通过UnpooledDataSourec
 *    来获取，获取到地Connection实例将被包装成PooledConnection实例存放到mybatis中的PoolState容器中
 *    ！！这个容器中的PooledConnection分为两种状态，1：一种是空闲（Idle）,被放在idelConnections->List集合中
 *                                             2：一种是活跃（Active）被放在activeConnections->List集合中
 *     在通过DataSource.getConnection()方法，方法体为：
 *     popConnection(dataSource.getUsername(),dataSourec.getPassword()).getProxyConnection();
 *          1.  先看是否有空闲(idle)状态下的PooledConnection对象，
 *  *      如果有，就直接返回一个可用的PooledConnection对象；否则进行第2步。
 *  *
 *  *      2.  查看活动状态的PooledConnection池activeConnections是否已满；
 *  *      如果没有满，则创建一个新的PooledConnection对象，然后放到activeConnections池中，
 *  *      然后返回此PooledConnection对象；否则进行第三步；
 *  *
 *  *      3.  看最先进入activeConnections池中的PooledConnection对象是否已经过期：
 *  *      如果已经过期，从activeConnections池中移除此对象，然后创建一个新的PooledConnection对象，
 *  *      添加到activeConnections中，然后将此对象返回；否则进行第4步。
 *  *
 *  *      4.  线程等待，循环2步
 *     ！！！会返回一个Connection实例的代理对象，用于拦截Connection实例调用close()方法时，将根据连接池具体情况（最大空闲连接）
 *     具体实现为“还连接”，
 *     也就是放到idelConnection集合中，后续调用
 *
 * 2：UNPOOLED->对应工厂->UnpooledDataSourceFactory->UnpooledDataSource,
 * 每次调用dataSource.getConnection()获取的就是java.sql.Connection实例，
 * 调用close()方法也是直接关闭连接，每次直接向数据库获取连接存在巨大的系统性能开销，
 * 如果用完就关闭连接，存在巨大的资源浪费，连接池数据源解决的就是这个问题
 * 3：JNDI
 */
public class MainMybatis {
    public static void main(String[] args) throws IOException {
        InputStream is= Resources.getResourceAsStream("mybatis_config/config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
        //mybatis对于每一个SqlSession对象的事务管理，默认不会自动提交，执行若干个DML语句后，1：需要手动提交2:在获取SqlSession时，在openSession()方法中传入一个true

        //这里的try块写法相当于finally{}作用，用于在连接使用完毕后关闭连接（当然具体是直接关闭连接还是“还连接”，取决于数据源,或者更确切的说是有没有连接池）
        try(SqlSession sqlSession=sqlSessionFactory.openSession(/*true*/)) {
            System.out.println(sqlSession.getMapper(Test.class).getCustomerById(1));
            sqlSession.getMapper(Test.class).insertOne(5);
            sqlSession.commit();
        }
    }


}

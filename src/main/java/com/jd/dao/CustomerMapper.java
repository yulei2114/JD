package com.jd.dao;

import com.jd.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 不集成spring框架时，
 * 1：mybatis在创建SqlSessionFactory时，通过调用SqlSessionFactoryBuider.buid(InputStream is)来创建实例
 * 内部会调用三个参数的构造方法，也就是SqlSessionFactoryBuider.buid（InputStream is,String Environment,Properties）
 * 后面两个参数用于替代主配置文件的Environment标签和Properties标签，
 * 2：这个三个参数的重载方法内部会创建一个XmlConfigBuider对象，用于解析config.xml文件
 *              XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
 *             var5 = this.build(parser.parse());var5将作为返回值返回
 * 3：那么在parse()方法中，又做了那些处理呢
 *              public Configuration parse() {
 *         if (this.parsed) {
 *             throw new BuilderException("Each XMLConfigBuilder can only be used once.");
 *         } else {
 *             this.parsed = true;
 *             this.parseConfiguration(this.parser.evalNode("/configuration"));
 *             return this.configuration;
 *         }
 *     }
 *     可以看到，最终会调用XmlConfigBUilder.parseConfiguration（）方法
 * 4：那么在parseConfiguration（）方法中，才是真正对各个标签的处理
 *           private void parseConfiguration(XNode root) {
 *         try {
 *             this.propertiesElement(root.evalNode("properties"));
 *             Properties settings = this.settingsAsProperties(root.evalNode("settings"));
 *             this.loadCustomVfs(settings);
 *             this.loadCustomLogImpl(settings);
 *             this.typeAliasesElement(root.evalNode("typeAliases"));
 *             this.pluginElement(root.evalNode("plugins"));
 *             this.objectFactoryElement(root.evalNode("objectFactory"));
 *             this.objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
 *             this.reflectorFactoryElement(root.evalNode("reflectorFactory"));
 *             this.settingsElement(settings);
 *             this.environmentsElement(root.evalNode("environments"));
 *             this.databaseIdProviderElement(root.evalNode("databaseIdProvider"));
 *             this.typeHandlerElement(root.evalNode("typeHandlers"));
 *             this.mapperElement(root.evalNode("mappers"));
 *         } catch (Exception var3) {
 *             throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + var3, var3);
 *         }
 *     }
 *     到最后的mapper映射文件具体映射到接口方法，所有的信息都将被保存到Configuration对象中，最终返回一个DefaultSqlSessionFactory对象
 *     后期程序中通过SqlSessionFactory获取SqlSession
 *     SqlSessionFactory.openSession(),
 *     再到SqlSeesion.getMapper(接口.class),mybatis将通过JDK动态代理创建代理对象并返回，每次调用该方法，就会返回一个新的代理类
 *     获取的代理对象在调用方法时，会对具体方法进行拦截，这时候将根据接口类型和方法名，匹配mapper.xml中的sql，从而确定sql类型
 *     比如INSERT、UPDATE、DELETE、SELECT
 *     确定了类型再通过SqlSession调用具体的方法，比如，如insert、delete、update、selectOne、selectList等。
 *     像这样：SqlSession.selectOne(接口的全限定类名,接口方法参数);
 *     ！！！这就是为什么mapper.xml文件中select标签id要与接口方法名一致
 *     从Mybatis 3.0开始，引入了Mapper映射器接口，也就是上面的分析，我们可以直接通过一个接口来引用需要使用的sql。只要这个接口满足以下条件，即可以引用xml配置文件中的sql：
 *
 *          1：接口的全路径就是映射文件的namespace属性值
 *          2：接口中定义的方法，方法名与映射文件中<insert>、<select>、<delete>、<update>等元素的id属性值相同
 *          至此，mybatis的ORM框架真正实现了将sql映射成对象，我们不用显式的在程序中书写sql,而是更贴合java的面向对象思想，
 *          在次强调，生成的是JDK动态代理对象,内部通过方法拦截，获取映射sql，最终还是要回到SqlSession.selectOne()等方法中
 *
 */

public interface CustomerMapper {
    //注册用户，将来使用mybatis框架映射一个insert语句
    public void insertCustomer(@Param("customer") Customer customer);
    //登录用户，将来接受用户信息，并返回一个用户完整信息
    public Customer checkCustomer(@Param("customer") Customer customer);
}

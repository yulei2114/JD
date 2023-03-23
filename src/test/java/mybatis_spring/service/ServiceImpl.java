package mybatis_spring.service;

import mybatis_spring.UserMapper.SelectCustomers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = java.lang.Exception.class)
@org.springframework.stereotype.Service(value = "serviceImpl")
public class ServiceImpl implements Service{
    @Resource
    SelectCustomers userMapper;
    public ServiceImpl(SelectCustomers userMapper){
        this.userMapper=userMapper;
    }
    @Override
    public int findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public void insertOne(int id) {
        userMapper.insertOne(id);
//        System.out.println(1/0);
    }
}

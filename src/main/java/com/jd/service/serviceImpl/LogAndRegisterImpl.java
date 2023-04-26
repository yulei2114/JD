package com.jd.service.serviceImpl;

import com.jd.dao.CustomerMapper;
import com.jd.entity.Customer;
import com.jd.service.LogAndRegister;
import email.SendEmail;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.GeneralSecurityException;

@Service(value="logAndRegisterImpl" )
@Transactional(rollbackFor = java.lang.Exception.class)
public class LogAndRegisterImpl implements LogAndRegister {
    //一个属性用于存放Dao对象
    @Autowired
    private CustomerMapper customerMapper;
    public LogAndRegisterImpl (){

    }
    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        if (checkByAccount(customer.getAccount())){
            return false;
        }
        customerMapper.insertCustomer(customer);
        return true;
    }

    @Override
    public Customer checkByCustomer(Customer customer) {
        Customer customerFind;
        if ((customerFind= customerMapper.checkByCustomer(customer))!=null){
            return customerFind;
        }
        return null;
    }

    @Override
    public boolean checkByAccount(String account) {
        return customerMapper.checkByAccount(account)!=null;
    }

    @Override
    public Customer checkEmail(Customer customer) throws GeneralSecurityException {
        Customer customerFind= customerMapper.checkEmail(customer);
        return customerFind;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        //更新用户信息
        customerMapper.updateCustomer(customer);
        //查询用户完整信息
        return customerMapper.checkByAccount(customer.getAccount());
    }
}

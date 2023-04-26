package com.jd.servlet.controller;

import com.alibaba.fastjson2.JSON;
import com.jd.dao.CustomerMapper;
import com.jd.entity.Customer;
import com.jd.entity.Status;
import com.jd.entity.User;
import com.jd.service.LogAndRegister;
import com.jd.service.serviceImpl.LogAndRegisterImpl;
import email.SendEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring6.processor.SpringObjectTagProcessor;

import javax.annotation.Resource;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;

@Controller
public class LoginController {
    /*
        1：Resource注解会西安根据属性名匹配bean，如果匹配到的bean类型不匹配，则抛异常
        如果属性名没有匹配到，则匹配参数类型，如果是接口，可能匹配到多个bean，这时候会抛异常
        2：如果用在setter方法上，则匹配的是方法参数的类型，不是set属性的类型，匹配规则同上
     */
    @Autowired
    private LogAndRegister logAndRegisterImpl;
    public LoginController(){

    }

    @PostMapping(value = {"/login"})//value属性是一个字符串数组，可以指定多个匹配路径,和path属性互为别名，可替换使用
    @ResponseBody
//    @ResponseBody：直接将返回值写入响应体，不会再走视图解析器
    public String checkCustomer( Customer customer,HttpServletRequest request){

            Customer customerFind=null;
            if ((customerFind=logAndRegisterImpl.checkByCustomer(customer))!=null){
                //将查询到的Customer绑定到session作用域
                HttpSession session=request.getSession();
                session.setAttribute("customer",customerFind);
                System.out.println(JSON.toJSONString(customerFind));
                return JSON.toJSONString(new Status("success"));

            }
            return JSON.toJSONString(new Status("defeat"));

    }
    @GetMapping("/loginview")
    public String viewName(HttpSession session){

        System.out.println(session.getAttribute("customer"));
        return "JD-first-page-login";

    }
    //用户验证邮箱
    @PostMapping("/checkEmail")
    @ResponseBody
    public Status emailStatus(@RequestBody Customer customer,HttpSession session) throws GeneralSecurityException {

        Customer customerFind=logAndRegisterImpl.checkEmail(customer);
        if (customerFind!=null){

            //如果根据用户账号和邮箱查询到的用户不为空，将继续给用户邮箱发送验证码，并返回
                SendEmail sendEmail=new SendEmail();
                String verifyCode= sendEmail.sendMail(customer.getEmail());
                if (verifyCode!=null){
                    session.setAttribute("verifyCode",verifyCode);
                    session.setAttribute("customer",customerFind);
                    return new Status("success");
                }
        }
        return new Status("defeat");

    }
    //根据用户提交的验证码修改密码
    @PostMapping("/checkVerify")
    @ResponseBody
    public Status verifyStatus(HttpSession session, @RequestParam(value = "verifyCode") String verifyCode,@RequestParam(value = "password") String password){

        if (session.getAttribute("verifyCode").equals(verifyCode)){
            Customer customerFind= (Customer) session.getAttribute("customer");
            customerFind.setPassword(password);
            Customer finalCustomer= logAndRegisterImpl.updateCustomer(customerFind);
            session.setAttribute("customer",finalCustomer);
            return new Status("success");
        }
        return new Status("defeat");
    }

}

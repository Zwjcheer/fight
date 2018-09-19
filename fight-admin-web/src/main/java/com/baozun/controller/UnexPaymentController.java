package com.baozun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baozun.common.ResultResponse;
import com.baozun.dao.entity.Student;
import com.baozun.service.IPaymentStudentService;

@RestController
@RequestMapping("/student")
public class UnexPaymentController {

    @Autowired
    private IPaymentStudentService paymentStudentService;

    @Autowired
    //用于发送邮件
    private JavaMailSender jms;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResultResponse save(@RequestBody Student student) {

        ResultResponse resultResponse = new ResultResponse();
        Boolean save = paymentStudentService.save(student);
        resultResponse.setData(save);
        return resultResponse;

    }

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public ResultResponse query(@RequestBody Student student) {

        ResultResponse resultResponse = new ResultResponse();
        List<Student> list = paymentStudentService.query(student);
        resultResponse.setData(list);
        return resultResponse;
    }

    @RequestMapping(value = "send",method = RequestMethod.POST)
    public String send() {

        //用于封装邮件信息的实例
        SimpleMailMessage smm = new SimpleMailMessage();
        //由谁来发送邮件
        smm.setFrom("13611869514@163.com");
        //邮件主题
        smm.setSubject("Hello");
        //邮件内容
        smm.setText("杨胜文，黄山真的很好好玩吗？");
        //接受邮件
        smm.setTo("shengwen.yang@baozun.com");
        try {
            jms.send(smm);
            return "发送成功";
        } catch (Exception e) {
            return "发送失败///" + e.getMessage();
        }

    }

}
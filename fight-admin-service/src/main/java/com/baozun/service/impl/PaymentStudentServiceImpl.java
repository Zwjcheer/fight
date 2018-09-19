package com.baozun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baozun.dao.entity.Student;
import com.baozun.dao.mapper.StudentMapper;
import com.baozun.service.IPaymentStudentService;

@Component
public class PaymentStudentServiceImpl implements IPaymentStudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Boolean save(Student student) {

        Boolean aBoolean = studentMapper.save(student);

        return aBoolean;
    }

    @Override
    public List<Student> query(Student student) {

        List<Student> list = studentMapper.queryStudent(student);
        return list;
    }

}

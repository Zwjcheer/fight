package com.baozun.service;

import java.util.List;

import com.baozun.dao.entity.Student;

public interface IPaymentStudentService {

    Boolean save(Student student);

    List<Student> query(Student student);
}

package com.baozun.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baozun.dao.entity.Student;

@Mapper
public interface StudentMapper {

    List<Student> query(Map<String, Object> parameters);

    int count(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    void insertBatch(List<Student> list);

    Boolean insertStudent(Student student);

    Boolean save(Student student);

    List<Student> queryStudent(Student student);
}
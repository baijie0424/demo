package com.cetc15s.dao;

import com.cetc15s.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @Author bj
 * @Description
 * @Date 2020/7/4 21:18
 * @Param 
 * @Return 
 */
public interface StudentMapper {

    
    Student findById(Integer id);

    int saveStudent(Student student);

    int updateStudent(Student student);

    int deleteById(Integer id);

    List<Student> findByParam(Map paramMap);

    List findAll();
}

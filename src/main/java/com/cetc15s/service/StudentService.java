package com.cetc15s.service;

        import com.cetc15s.pojo.Student;
        import com.github.pagehelper.PageInfo;

        import java.util.Map;

public interface StudentService {
    Student findById(Integer id);

    int save(Student student);

    int update(Student student);

    int delete(Integer id);

    PageInfo<Student> findByParam(Map paramMap, Integer pageNo, Integer pageSize);

    PageInfo findAll(int pageNo,int pageSize);
}

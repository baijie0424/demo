package com.cetc15s.controller;

import com.cetc15s.pojo.PageBean;
import com.cetc15s.pojo.Student;
import com.cetc15s.service.StudentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName StudentController
 * @Description
 * @Author bj
 * @Date 2020/7/2 23:37
 * @Version 1.0
 */
@Slf4j
@RestController
public class StudentController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService stuService;

    /**
     *  @GetMapping用于将HTTP get请求映射到特定处理程序的方法注解
     * 是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
     *
     *  @PostMapping用于将HTTP post请求映射到特定处理程序的方法注解
     * 是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
     */
    @GetMapping("/student/{id}")
    public Student findById(@PathVariable("id") Integer id){
        Student result = stuService.findById(id);
//        LOGGER.debug("stu 接口调用findById成功" + result.toString());

        //使用lombok简化创建logger
        log.debug("stu 接口调用findById成功" + result.toString());
        return result;
    }

    @GetMapping("/student/findAll")
    public PageInfo findAll(int pageNo,int pageSize) {
        PageInfo pageInfo = stuService.findAll(pageNo,pageSize);
        return pageInfo;
    }

    @PostMapping("/student")
    public int save(@RequestBody Student student) {
        return stuService.save(student);
    }

    @PutMapping("/student")
    public int update(@RequestBody Student student) {
        return stuService.update(student);
    }

    @DeleteMapping("/student/{id}")
    public int delete(@PathVariable("id") Integer id) {
        return stuService.delete(id);
    }


    @GetMapping("/students")
    public PageInfo<Student> findByPage(PageBean pageBean) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        int pageNo = pageBean.getPageNo();
        int pageSize = pageBean.getPageSize();
        String birthday = pageBean.getBirthday();
        paramMap.put("birthday",birthday);
        PageInfo<Student> pageResult = stuService.findByParam(paramMap, pageNo, pageSize);
        return pageResult;
    }

    @RequestMapping("/test")
    public String testGetParam(Integer id, String name, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time) {
        return id + "\t" + name + time;
    }

}

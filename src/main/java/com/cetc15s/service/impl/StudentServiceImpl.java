package com.cetc15s.service.impl;

import com.cetc15s.dao.StudentMapper;
import com.cetc15s.pojo.Student;
import com.cetc15s.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentServiceImpl
 * @Description
 * @Author bj
 * @Date 2020/7/2 23:39
 * @Version 1.0
 */
@CacheConfig(cacheNames = "student", cacheManager = "studentCacheManager") //统一指定
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate; //k-v都是字符串的
    @Autowired
    private RedisTemplate redisTemplate; //k-v都是对象的
    @Autowired
    private RedisTemplate<Object,Student> studentRedisTemplate;
    @Qualifier("studentCacheManager")
    @Autowired
    private RedisCacheManager studentCacheManager;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    //通过注解的方式使用缓存可以直接查询数据库的方式进行springboot会自动帮助我们放入缓存和从缓存中取出数据
    @Cacheable(cacheNames = "student", cacheManager = "studentCacheManager") //在方法上指定
    @Override
    public Student findById(Integer id) {

//        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
//        String value = stringStringValueOperations.get(id.toString());
//
//        if (value != null && !"".equals(value)) {
//            return new Gson().fromJson(value, Student.class);
//        }
//        Student student = studentMapper.findById(id);
//        stringStringValueOperations.append(id.toString(), new Gson().toJson(student));
//        return student;

//        ValueOperations<Object, Student> objectStudentValueOperations = studentRedisTemplate.opsForValue();
//        Student student = objectStudentValueOperations.get(id);
//        if (student != null) {
//            return student;
//        }
//        student = studentMapper.findById(id);
//        objectStudentValueOperations.set(id, student);
//        return student;

        //通过代码的方式操作缓存
        Student student = studentMapper.findById(id);
        //获取某个缓存
        Cache stu = studentCacheManager.getCache("student");
        stu.put("student:1",student);
        return student;
    }

    @Override
    public int save(Student student) {
        rabbitTemplate.convertAndSend("exchange.direct", "baijie", student);
        rabbitTemplate.convertAndSend("exchange.direct", "baijie.student", student);
        return studentMapper.saveStudent(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int delete(Integer id) {
        return studentMapper.deleteById(id);
    }

    @Override
    public PageInfo<Student> findByParam(Map paramMap, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Student> data = studentMapper.findByParam(paramMap);
        PageInfo<Student> pageResult = new PageInfo<>(data);
        return pageResult;
    }

    @Override
    public PageInfo findAll(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List list = studentMapper.findAll();
        PageInfo pageInfo = new PageInfo(list);
        System.out.println(list);
        return pageInfo;
    }

    /**
     * @Author bj
     * @Description 监听队列
     * @Date 2020/8/3 0:08
     * @Param []
     * @Return void
     */
    @RabbitListener(queues = "baijie")
    public void receive(Student student) {
        System.out.println("收到消息" + student);
    }

    @RabbitListener(queues = "baijie.student")
    public void receiveMsg(Message msg) {
        System.out.println(msg.getBody());
        System.out.println(msg.getMessageProperties());
    }

}

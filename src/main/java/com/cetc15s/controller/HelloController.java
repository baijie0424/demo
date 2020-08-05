package com.cetc15s.controller;

import com.cetc15s.pojo.Dog;
import com.cetc15s.pojo.Student;
import com.cetc15s.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author bj
 * @Date 2020/7/2 0:04
 * @Version 1.0
 */
@RestController
public class HelloController {

    /**
     * 如果只是在某个业务逻辑中需要获取配置文件中的某项值使用@Value(${xx.xx})，也可以使用spring表达式@Value(#{10*10})。
     * 如果专门编写了一个javaBean来和配置文件进行映射就使用@ConfigurationProperties(prefix = "XXX"),同时需要将javaBean
     * 添加到容器中。
     */
    @Value("${person.name}")
    private String name;

    @Value("#{${person.age}*${person.index}}")
    private int result;

    @Autowired
    private Dog dog;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/sayhello")
    public String hello() {
        return "hello\t" + name + result + result + result;
    }

    @RequestMapping("/helloDog")
    public String helloDog() {
        return dog.toString();
    }

    @RequestMapping("/config")
    public boolean configRegistFlag() {
        return applicationContext.containsBean("myConfig");
    }

    @RequestMapping("/createBean")
    public Object createBean() {
        //获取context
        ApplicationContext ctx = SpringContextUtils.getApplicationContext();
        //过去BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)ctx.getAutowireCapableBeanFactory();
        //创建bean信息
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Student.class);
        beanDefinitionBuilder.addPropertyValue("name","tom");
        //动态注册bean
        defaultListableBeanFactory.registerBeanDefinition("student",beanDefinitionBuilder.getBeanDefinition());
        //获取bean
        Student student = (Student) ctx.getBean("student");
        Student bean = ctx.getBean(Student.class);
        return bean;
    }

    @RequestMapping("/getBean")
    public Object getBean() {
        ApplicationContext ctx = SpringContextUtils.getApplicationContext();
        Student bean = ctx.getBean(Student.class);
        return bean;
    }

    @RequestMapping("/destroyBean")
    public void destroyBean() {
        //获取context
        ApplicationContext ctx = SpringContextUtils.getApplicationContext();
        //过去BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)ctx.getAutowireCapableBeanFactory();
        //动态删除
        defaultListableBeanFactory.removeBeanDefinition("student");
    }
}

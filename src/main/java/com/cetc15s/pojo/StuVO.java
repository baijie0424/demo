package com.cetc15s.pojo;

/**
 * @ClassName StuVO
 * @Description
 * @Author bj
 * @Date 2020/7/8 0:57
 * @Version 1.0
 */
public class StuVO {
    private Integer pageNo;
    private Integer pageSize;
    private Student stu;

    public StuVO() {
    }

    public StuVO(Integer pageNo, Integer pageSize, Student stu) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.stu = stu;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }
}

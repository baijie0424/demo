package com.cetc15s.pojo;

/**
 * @ClassName PageBean
 * @Description
 * @Author bj
 * @Date 2020/7/9 23:44
 * @Version 1.0
 */
public class PageBean {
    private int pageNo;
    private int pageSize;
    private int id;
    private String name;
    private String gender;
    private String birthday;
    private double socore;

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", socore=" + socore +
                '}';
    }

    public PageBean() {
    }

    public PageBean(int pageNo, int pageSize, int id, String name, String gender, String birthday, double socore) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.socore = socore;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getSocore() {
        return socore;
    }

    public void setSocore(double socore) {
        this.socore = socore;
    }
}

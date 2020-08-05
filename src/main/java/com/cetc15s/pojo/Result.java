package com.cetc15s.pojo;

/**
 * @ClassName Result
 * @Description
 * @Author bj
 * @Date 2020/7/7 1:35
 * @Version 1.0
 */
public class Result {

    private static final long serialVersionUID = 1L;

    private boolean flag;
    private String message;
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

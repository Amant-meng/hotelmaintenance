package com.chinahotelhelp.shm.operational.module.sys.entity;

/**
 * 描述: json格式数据返回对象，使用CustomJsonResultSerializer 来序列化
 * @author : libin
 */
public class TeamResult<T> {
  
    private Integer code= 0;
    private String msg="请求成功";
    private T data;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "code=" + code + " message=" + msg + " data=" + data;
    }
}

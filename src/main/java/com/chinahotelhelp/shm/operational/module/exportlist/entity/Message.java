package com.chinahotelhelp.shm.operational.module.exportlist.entity;

/**
 * @author Huan.Xia
 * @Title: Message
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/03010:43
 */
public class Message {
    private boolean success;
    private String message;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Message success(Object data) {
        Message message = new Message();
        message.success = true;
        message.data = data;
        return message;
    }

    public static Message success() {
        return success(null);
    }

    public static Message N() {
        return new Message();
    }
}

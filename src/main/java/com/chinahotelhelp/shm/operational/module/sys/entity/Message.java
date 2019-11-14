package com.chinahotelhelp.shm.operational.module.sys.entity;

/**
 * @author Huan.Xia
 * @Title: Message
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/03010:43
 */
public class Message {
    /**
     * 成功标识
     */
    private boolean success;
    /**
     * 返回消息内容
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
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

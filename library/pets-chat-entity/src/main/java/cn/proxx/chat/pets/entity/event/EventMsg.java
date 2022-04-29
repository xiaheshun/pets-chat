package cn.proxx.chat.pets.entity.event;

import java.io.Serializable;

/**
 * 消息总线事件
 *
 * @author XiaHeShun
 * @since 2022年4月12日15:31:08
 */
public class EventMsg<T> implements Serializable {

    /**
     * 新消息
     */
    public static final int RECEIVE = 0;

    private int code;
    private String msg;
    private T data;

    public EventMsg() {
    }

    public int getCode() {
        return code;
    }

    public EventMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public EventMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public EventMsg setData(T data) {
        this.data = data;
        return this;
    }

}

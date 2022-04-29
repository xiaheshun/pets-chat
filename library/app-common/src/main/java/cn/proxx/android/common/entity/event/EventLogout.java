package cn.proxx.android.common.entity.event;

import java.io.Serializable;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class EventLogout<T> implements Serializable {
    /**
     * 重新登录
     */
    public static final int LOGIN = 0;
    /**
     * 重启
     */
    public static final int REBOOT = 1;
    /**
     * 退出
     */
    public static final int EXIT = 2;

    private int code;
    private String msg;
    private T data;

    public EventLogout() {
    }

    public int getCode() {
        return code;
    }

    public EventLogout setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public EventLogout setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public EventLogout setData(T data) {
        this.data = data;
        return this;
    }
}

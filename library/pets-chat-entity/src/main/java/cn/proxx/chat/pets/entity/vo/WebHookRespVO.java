package cn.proxx.chat.pets.entity.vo;

import java.io.Serializable;

public class WebHookRespVO implements Serializable {
    private int errcode;

    private String errmsg;

    public WebHookRespVO() {
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}

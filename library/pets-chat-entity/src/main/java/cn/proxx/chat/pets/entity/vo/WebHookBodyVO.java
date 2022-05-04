package cn.proxx.chat.pets.entity.vo;

import java.io.Serializable;

public class WebHookBodyVO implements Serializable {
    private String msgtype;

    private WebHookTextVO text;

    private WebHookAtVO at;

    public WebHookBodyVO() {
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public WebHookTextVO getText() {
        return text;
    }

    public void setText(WebHookTextVO text) {
        this.text = text;
    }

    public WebHookAtVO getAt() {
        return at;
    }

    public void setAt(WebHookAtVO at) {
        this.at = at;
    }
}

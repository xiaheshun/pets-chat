package cn.proxx.chat.pets.entity.vo;

import java.io.Serializable;

public class WebHookTextVO implements Serializable {
    private String content;

    public WebHookTextVO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

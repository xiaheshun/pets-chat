package cn.proxx.chat.pets.entity.vo;

import java.io.Serializable;
import java.util.List;

public class WebHookAtVO implements Serializable {
    private List<String> atMobiles;

    public WebHookAtVO() {
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }
}

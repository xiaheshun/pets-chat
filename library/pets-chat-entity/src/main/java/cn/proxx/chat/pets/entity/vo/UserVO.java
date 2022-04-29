package cn.proxx.chat.pets.entity.vo;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author XiaHeShun
 * @since 2022年4月29日16:12:28
 */
public class UserVO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 令牌
     */
    private String token;


    public UserVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
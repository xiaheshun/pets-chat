package cn.proxx.chat.pets.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@DatabaseTable(tableName = "user")
public class UserDO implements Serializable {
    /**
     * 用户ID
     */
    @DatabaseField(columnName = "id", unique = true, id = true, index = true)
    private String id;

    /**
     * 用户名
     */
    @DatabaseField(columnName = "username")
    private String username;

    /**
     * 秘钥
     */
    @DatabaseField(columnName = "token")
    private String token;

    public UserDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

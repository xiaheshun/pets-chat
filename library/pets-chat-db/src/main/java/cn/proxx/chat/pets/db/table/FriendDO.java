package cn.proxx.chat.pets.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@DatabaseTable(tableName = "friend")
public class FriendDO implements Serializable {
    /**
     * ID
     */
    @DatabaseField(columnName = "id", unique = true, id = true, index = true)
    private String id;

    /**
     * 名字
     */
    @DatabaseField(columnName = "name")
    private String name;

    /**
     * 类型 0钉钉 1企业微信 2飞书
     */
    @DatabaseField(columnName = "type")
    private Integer type;

    /**
     * web_hook地址
     */
    @DatabaseField(columnName = "web_hook")
    private String webHook;

    /**
     * 加签
     */
    @DatabaseField(columnName = "secret")
    private String secret;

    /**
     * 更新时间
     */
    @DatabaseField(columnName = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @DatabaseField(columnName = "create_time")
    private Date createTime;

    public FriendDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

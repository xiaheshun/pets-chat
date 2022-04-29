package cn.proxx.chat.pets.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@DatabaseTable(tableName = "base_version")
public class BaseVersionDO implements Serializable {
    //表名
    @DatabaseField(columnName = "tableName", unique = true, id = true, index = true)
    private String tableName;
    //版本
    @DatabaseField(columnName = "version")
    private String version;

    public BaseVersionDO() {
    }

    public BaseVersionDO(String tableName, String version) {
        this.tableName = tableName;
        this.version = version;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

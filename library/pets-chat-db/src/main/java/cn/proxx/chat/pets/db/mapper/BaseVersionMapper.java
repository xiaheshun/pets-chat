package cn.proxx.chat.pets.db.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.proxx.chat.pets.db.table.BaseVersionDO;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class BaseVersionMapper extends BaseMapper<BaseVersionDO, String> {

    private static BaseVersionMapper instance;

    public BaseVersionMapper() {
        super(BaseVersionDO.class);
    }

    public static BaseVersionMapper getInstance() {
        if (null == instance) {
            instance = new BaseVersionMapper();
        }
        return instance;
    }

    // 通过id删除指定数据
    public void delete(String id) {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 批量插入或更新
    public void insertOrUpdate(List<BaseVersionDO> dataList) {
        // 需要添加的
        List<BaseVersionDO> insertList = new ArrayList<>();
        List<BaseVersionDO> updateList = new ArrayList<>();
        for (BaseVersionDO t : dataList) {
            if (null == queryById(t.getTableName())) {
                insertList.add(t);
            } else {
                updateList.add(t);
            }
        }
        // 插入
        create(insertList);
        // 更新
        for (BaseVersionDO c : updateList) {
            update(c);
        }
    }
}

package cn.proxx.chat.pets.db.mapper;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.proxx.chat.pets.db.DatabaseHelper;
import cn.proxx.chat.pets.db.table.UserDO;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class UserMapper extends BaseMapper<UserDO, String> {

    private static UserMapper instance;

    public UserMapper() {
        super(UserDO.class);
    }

    public static UserMapper getInstance() {
        if (null == instance) {
            instance = new UserMapper();
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
    public void insertOrUpdate(UserDO user) {
        if (null == queryById(user.getId())) {
            create(user);
        } else {
            update(user);
        }
    }

    // 批量插入或更新
    public void insertOrUpdate(List<UserDO> dataList) {
        // 需要添加的
        List<UserDO> insertList = new ArrayList<>();
        List<UserDO> updateList = new ArrayList<>();
        for (UserDO t : dataList) {
            if (null == queryById(t.getId())) {
                insertList.add(t);
            } else {
                updateList.add(t);
            }
        }
        // 插入
        create(insertList);
        // 更新
        for (UserDO c : updateList) {
            update(c);
        }
    }

    @Override
    public void deleteAll() {
        try {
            ConnectionSource connectionSource = DatabaseHelper.getInstance().getConnectionSource();
            TableUtils.dropTable(connectionSource, UserDO.class, true);
            TableUtils.createTable(connectionSource, UserDO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

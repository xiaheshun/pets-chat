package cn.proxx.chat.pets.db.mapper;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.proxx.chat.pets.db.DatabaseHelper;
import cn.proxx.chat.pets.db.table.FriendDO;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class FriendMapper extends BaseMapper<FriendDO, String> {

    private static FriendMapper instance;

    public FriendMapper() {
        super(FriendDO.class);
    }

    public static FriendMapper getInstance() {
        if (null == instance) {
            instance = new FriendMapper();
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
    public void insertOrUpdate(FriendDO user) {
        if (null == queryById(user.getId())) {
            create(user);
        } else {
            update(user);
        }
    }

    // 批量插入或更新
    public void insertOrUpdate(List<FriendDO> dataList) {
        // 需要添加的
        List<FriendDO> insertList = new ArrayList<>();
        List<FriendDO> updateList = new ArrayList<>();
        for (FriendDO t : dataList) {
            if (null == queryById(t.getId())) {
                insertList.add(t);
            } else {
                updateList.add(t);
            }
        }
        // 插入
        create(insertList);
        // 更新
        for (FriendDO c : updateList) {
            update(c);
        }
    }

    @Override
    public void deleteAll() {
        try {
            ConnectionSource connectionSource = DatabaseHelper.getInstance().getConnectionSource();
            TableUtils.dropTable(connectionSource, FriendDO.class, true);
            TableUtils.createTable(connectionSource, FriendDO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查是否存在
     *
     * @param friend {@link FriendDO}
     * @return {@link FriendDO}
     */
    public FriendDO checkIsExists(FriendDO friend) {
        try {
            FriendDO friendDO = dao.queryBuilder().where().eq("name", friend.getName()).and().eq("web_hook", friend.getWebHook()).queryForFirst();
            return friendDO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

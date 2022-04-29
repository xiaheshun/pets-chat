package cn.proxx.chat.pets.db.mapper;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.proxx.chat.pets.db.DatabaseHelper;
import cn.proxx.android.common.constant.TagConstant;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class BaseMapper<T, V> {
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    protected Dao<T, V> dao;

    public BaseMapper(Class clazz) {
        try {
            this.dao = DatabaseHelper.getInstance().getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据
     *
     * @param data
     */
    public void create(T data) {
        try {
            dao.createOrUpdate(data);
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 create(T data)", e);
        }
    }

    /**
     * 创建数据集合
     *
     * @param dataList
     */
    public void create(List<T> dataList) {
        try {
            dao.create(dataList);
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 create(List<T> dataList)", e);
        }
    }

    /**
     * 向user表中添加一条数据
     * <p>
     * create:插入一条数据或集合
     * <p>
     * createIfNotExists:如果不存在则插入
     * <p>
     * createOrUpdate:如果指定id则更新
     *
     * @param data
     */
    public void insert(T data) {
        try {
            dao.createIfNotExists(data);
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 insert(T data)", e);
        }
    }

    // 通过id删除指定数据
//    public void delete(V id) {
//        try {
//            dao.deleteById(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // 删除表中的一条数据
    public void delete(T data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 delete(T data)", e);
        }
    }

    // 删除数据集合
    public void delete(List<T> dataList) {
        try {
            dao.delete(dataList);
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 delete(List<T> dataList)", e);
        }
    }

    //清空数据
    public void deleteAll() {
        try {
            dao.delete(dao.queryForAll());
        } catch (Exception e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 deleteAll()", e);
        }
    }

    // 修改表中的一条数据
    public void update(T data) {
        try {
            dao.update(data);
        } catch (SQLException | NullPointerException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 update(T data)", e);
        }
    }

    // 查询表中的所有数据
    public List<T> queryAll() {
        List<T> result = new ArrayList<>();
        try {
            result = dao.queryForAll();
        } catch (SQLException | NullPointerException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 queryAll()", e);
        }
        return result;
    }

    // 根据ID取出数据
    public T queryById(V id) {
        T result = null;
        try {
            result = dao.queryForId(id);
        } catch (SQLException | NullPointerException e) {
            Log.e(TagConstant.TAG_DB, "数据库表操作 queryById(V id)", e);
        }
        return result;
    }
}

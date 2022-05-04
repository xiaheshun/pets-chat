package cn.proxx.chat.pets.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.proxx.android.common.constant.TagConstant;
import cn.proxx.chat.pets.db.mapper.BaseVersionMapper;
import cn.proxx.chat.pets.db.table.BaseVersionDO;
import cn.proxx.chat.pets.db.table.FriendDO;
import cn.proxx.chat.pets.db.table.UserDO;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    /**
     * 数据库名称 DATABASE_NAME
     * 字典版本 DATABASE_VERSION
     * 说明：任何通过APP直接打包的字典，都需要对 DATABASE_VERSION 进行+1
     */
    public static final String DATABASE_NAME = "pets_chat";
    public static final Integer DATABASE_VERSION = 1;
    public static final String V_USER = "1.0";
    public static final String V_FRIEND = "1.0";

    // 表名
    public static final String BASE_VERSION = "db_version";
    public static final String T_USER = "user";
    public static final String T_FRIEND = "friend";

    // 本类的单例实例
    private static DatabaseHelper instance;

    // 存储APP中所有的DAO对象的Map集合
    private ArrayMap<String, Dao> daoArr = new ArrayMap<>();

    // 初始化工具类，必须先初始化再使用
    public static void init(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
    }

    // 获取对象的实例
    public static synchronized DatabaseHelper getInstance() {
        return instance;
    }

    // 私有的构造方法
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 需要提前加载二级缓存的数据
    public static void initCache() {
        // 输出表版本
        List<BaseVersionDO> baseVersionDOList = BaseVersionMapper.getInstance().queryAll();
        for (BaseVersionDO base : baseVersionDOList) {
            Log.i(TagConstant.TAG_DB, "数据表/数据库名：" + base.getTableName()
                    + "，版本号：" + base.getVersion());
        }
        // 加载缓存

    }

    // 根据传入的DAO的路径获取到这个DAO的单例对象（要么从daoArr这个Map中获取，要么新创建一个并存入daoArr）
    @Override
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daoArr.containsKey(className)) {
            dao = daoArr.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daoArr.put(className, dao);
        }
        return dao;
    }

    @Override // 创建数据库时调用的方法
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BaseVersionDO.class);
            TableUtils.createTable(connectionSource, UserDO.class);
            TableUtils.createTable(connectionSource, FriendDO.class);
            initDataBase();
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "创建数据库时调用的方法", e);
        }
    }

    @Override // 数据库版本更新时调用的方法
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BaseVersionDO.class, true);
            TableUtils.dropTable(connectionSource, UserDO.class, true);
            TableUtils.dropTable(connectionSource, FriendDO.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(TagConstant.TAG_DB, "数据库版本更新时调用的方法", e);
        }
    }

    // 释放资源
    @Override
    public void close() {
        super.close();
        for (String key : daoArr.keySet()) {
            daoArr.get(key);
        }
    }

    // 初始化数据库导入本地JSON
    private void initDataBase() {
        // 控制版本
        List<BaseVersionDO> baseVersionDOList = new ArrayList<>();
        baseVersionDOList.add(new BaseVersionDO(BASE_VERSION, Integer.toString(DATABASE_VERSION)));
        baseVersionDOList.add(new BaseVersionDO(T_USER, V_USER));
        baseVersionDOList.add(new BaseVersionDO(T_FRIEND, V_FRIEND));
        // 写入数据
        BaseVersionMapper.getInstance().create(baseVersionDOList);
    }

}

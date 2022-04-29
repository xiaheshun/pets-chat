package cn.proxx.chat.pets.db;

import android.app.Application;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class DatabaseInit {

    private DatabaseInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化数据库SDK
     */
    public static void init(Application application) {
        // 初始化数据库
        DatabaseHelper.init(application);
        // 初始化数据库二级缓存
        DatabaseHelper.initCache();
    }
}

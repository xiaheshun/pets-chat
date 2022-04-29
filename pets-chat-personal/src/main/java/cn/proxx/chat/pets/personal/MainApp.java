package cn.proxx.chat.pets.personal;

import cn.proxx.chat.pets.db.DatabaseInit;
import cn.proxx.android.common.base.BaseApplication;
import cn.proxx.android.common.sdkinit.BasicLibInit;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class MainApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void initDB() {
        DatabaseInit.init(this);
    }

    @Override
    public void initHttp() {
        BasicLibInit.initXHttp2(this, BuildConfig.HOST_URL, "/mock", 200);
    }
}

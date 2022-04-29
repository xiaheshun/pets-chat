package cn.proxx.chat.pets.app;

import cn.proxx.android.common.base.BaseApplication;
import cn.proxx.android.common.sdkinit.BasicLibInit;
import cn.proxx.chat.pets.app.exception.CrashHandler;
import cn.proxx.chat.pets.db.DatabaseInit;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class MainApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }

    @Override
    public void initDB() {
        DatabaseInit.init(this);
    }

    @Override
    public void initHttp() {
        String prefix;
        if (BuildConfig.DEBUG) {
            prefix = "";
        } else {
            prefix = "/proxy/transparent";
        }
        BasicLibInit.initXHttp2(this, BuildConfig.HOST_URL, prefix, 200);
    }
}

package cn.proxx.android.common.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.xuexiang.xui.XUI;

import cn.proxx.android.common.constant.TagConstant;
import cn.proxx.android.common.sdkinit.BasicLibInit;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BasicLibInit.init(this);
        initDesign(this);
        initDB();
        initHttp();
    }

    public void initDB() {
        Log.e(TagConstant.TAG_DB, "由于没有@Override initDB()方法，未设置任何数据库，框架不会初始化数据库");
    }

    public void initHttp() {
        Log.e(TagConstant.TAG_DB, "由于没有@Override initHttp()方法，未设置任何网络请求地址，框架不会初始化网络请求");
    }

    public static void initDesign(Context context) {
        if (XUI.isTablet()) {
            AutoSizeConfig.getInstance().setDesignWidthInDp(540).setDesignHeightInDp(720);
        } else {
            AutoSizeConfig.getInstance().setDesignWidthInDp(360).setDesignHeightInDp(640);
        }
    }
}

package cn.proxx.android.common.sdkinit;

import android.app.Application;

import com.tencent.mmkv.MMKV;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.XHttpSDK;
import com.xuexiang.xpage.AutoPageConfiguration;
import com.xuexiang.xpage.PageConfig;
import com.xuexiang.xui.XUI;
import com.xuexiang.xutil.XUtil;

import org.xutils.x;

import cn.proxx.android.common.BuildConfig;
import cn.proxx.android.common.base.BaseActivity;
import cn.proxx.android.common.net.http.HttpConfig;
import cn.proxx.android.common.util.mmkv.MMKVUtils;

/**
 * X系列基础库初始化
 *
 * @author xuexiang
 * @since 2019-06-30 23:54
 */
public final class BasicLibInit {

    private BasicLibInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化基础库SDK
     */
    public static void init(Application application) {
        //工具类
        initXUtil(application);

        //页面框架
        initXPage(application);

        //UI框架
        initXUI(application);

        // 更新和网络请求
        UpdateInit.init(application);

        // 初始化XUtils3
        initXUtils3(application);
    }

    /**
     * 初始化XUtil工具类
     */
    private static void initXUtil(Application application) {
        XUtil.init(application);
        XUtil.debug(BuildConfig.DEBUG);
        MMKV.initialize(application.getApplicationContext());
        MMKVUtils.init(application);
    }

    /**
     * 初始化XHttp2
     */
    public static void initXHttp2(Application application, String baseUrl, String prefix, int successCode) {
        // 初始化网络请求框架，必须首先执行
        XHttpSDK.init(application);
        // 需要调试的时候执行
        if (BuildConfig.DEBUG) {
            XHttpSDK.debug();
        }
        // 设置网络请求的全局基础地址
        XHttpSDK.setBaseUrl(baseUrl);
        // 设置网络成功响应码
        XHttpSDK.setSuccessCode(successCode);
        // 设置相关属性
        XHttp.getInstance().setReadTimeOut(3000);// 设置全局读取超时时间
        XHttp.getInstance().setWriteTimeOut(2000);// 设置全局写入超时时间
        XHttp.getInstance().setConnectTimeout(5000);// 设置全局连接超时时间
        XHttp.getInstance().setTimeout(10000);// 设置全局超时时间
        // 设置Http请求的固定前缀
        XHttpSDK.setSubUrl(prefix);
        HttpConfig.PREFIX = prefix;
    }

    /**
     * 初始化XPage页面框架
     */
    private static void initXPage(Application application) {
        PageConfig.getInstance()
                .setPageConfiguration(new AutoPageConfiguration())
                .debug(BuildConfig.DEBUG ? "PageLog" : null)
                .setContainActivityClazz(BaseActivity.class)
                .init(application);
    }

    /**
     * 初始化XUI框架
     */
    private static void initXUI(Application application) {
        XUI.init(application);
        XUI.debug(BuildConfig.DEBUG);
    }

    /**
     * 初始化xUtils
     *
     * @param application
     */
    private static void initXUtils3(Application application) {
        x.Ext.init(application);
        if (BuildConfig.DEBUG) {
            x.Ext.setDebug(true); //是否输出debug日志，开启debug会影响性能。
        }
    }
}

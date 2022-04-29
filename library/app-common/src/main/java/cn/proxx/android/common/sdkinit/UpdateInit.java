package cn.proxx.android.common.sdkinit;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.utils.UpdateUtils;

import cn.proxx.android.common.BuildConfig;
import cn.proxx.android.common.R;
import cn.proxx.android.common.constant.ConfigConstant;
import cn.proxx.android.common.net.update.CustomUpdateDownloader;
import cn.proxx.android.common.net.update.CustomUpdateFailureListener;
import cn.proxx.android.common.net.update.CustomUpdateParser;
import cn.proxx.android.common.net.update.XHttpUpdateHttpServiceImpl;

/**
 * XUpdate 版本更新 SDK 初始化
 *
 * @author XiaHeshun
 */
public final class UpdateInit {

    private UpdateInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 应用版本更新的检查地址
     */
    private static final String KEY_UPDATE_URL = ConfigConstant.getUpdateUrl();

    public static void init(Application application) {
        XUpdate.get()
                .debug(BuildConfig.DEBUG)
                //默认设置只在wifi下检查版本更新
                .isWifiOnly(false)
                //默认设置使用get请求检查版本
                .isGet(false)
                //默认设置非自动模式，可根据具体使用配置
                .isAutoMode(false)
                // 设置自定义的JSON解析器
                .setIUpdateParser(new CustomUpdateParser())
                //设置默认公共请求参数
                .param("versionCode", UpdateUtils.getVersionCode(application))
                .param("appKey", application.getPackageName())
                .supportSilentInstall(false)
                //这个必须设置！实现网络请求功能。
                .setIUpdateHttpService(new XHttpUpdateHttpServiceImpl())
                .setIUpdateDownLoader(new CustomUpdateDownloader())
                .setOnUpdateFailureListener(new CustomUpdateFailureListener(true))
                //这个必须初始化
                .init(application);
    }

    /**
     * 进行版本更新检查
     *
     * @param context
     */
    public static void checkUpdate(Context context, boolean needErrorTip) {
        XUpdate.newBuild(context)
                .updateUrl(KEY_UPDATE_URL)
                // 弹窗的标题背景的资源图片
                .promptTopResId(R.drawable.update_dialog_bg)
                // 设置主题颜色（升级/安装按钮的背景色）
                .promptThemeColor(Color.parseColor("#3A68AF"))
                // 支持后台更新
                .supportBackgroundUpdate(true)
                // 升级框占屏比（宽）
                .promptWidthRatio(0.8F)
                .update();
    }

}

package cn.proxx.android.common.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.core.CoreSwitchBean;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.slideback.SlideBack;
import com.xuexiang.xutil.app.AppUtils;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import cn.proxx.android.common.util.XToastUtils;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class BaseActivity<Binding extends ViewBinding> extends XPageActivity {

    /**
     * 是否支持侧滑返回
     */
    public static final String KEY_SUPPORT_SLIDE_BACK = "key_support_slide_back";
    /**
     * ViewBinding
     */
    protected Binding binding;

    @Override
    protected void attachBaseContext(Context context) {
        //注入字体
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    protected View getCustomRootView() {
        binding = viewBindingInflate(getLayoutInflater());
        return binding != null ? binding.getRoot() : null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initStatusBarStyle();
        super.onCreate(savedInstanceState);
        registerSlideBack();
    }

    /**
     * 构建ViewBinding
     *
     * @param inflater inflater
     * @return ViewBinding
     */
    @Nullable
    protected Binding viewBindingInflate(LayoutInflater inflater) {
        return null;
    }

    /**
     * 获取Binding
     *
     * @return Binding
     */
    public Binding getBinding() {
        return binding;
    }

    /**
     * 打开fragment
     *
     * @param clazz          页面类
     * @param addToBackStack 是否添加到栈中
     * @return 打开的fragment对象
     */
    public <T extends XPageFragment> T openPage(Class<T> clazz, boolean addToBackStack) {
        CoreSwitchBean page = new CoreSwitchBean(clazz)
                .setAddToBackStack(addToBackStack);
        return (T) openPage(page);
    }

    /**
     * 打开fragment
     *
     * @return 打开的fragment对象
     */
    public <T extends XPageFragment> T openNewPage(Class<T> clazz) {
        CoreSwitchBean page = new CoreSwitchBean(clazz)
                .setNewActivity(true);
        return (T) openPage(page);
    }

    /**
     * 切换fragment
     *
     * @param clazz 页面类
     * @return 打开的fragment对象
     */
    public <T extends XPageFragment> T switchPage(Class<T> clazz) {
        return openPage(clazz, false);
    }

    @Override
    protected void onRelease() {
        unregisterSlideBack();
        super.onRelease();
    }

    /**
     * 初始化状态栏的样式
     */
    protected void initStatusBarStyle() {

    }

    /**
     * 注册侧滑回调
     */
    protected void registerSlideBack() {
        if (isSupportSlideBack()) {
            SlideBack.with(this)
                    .haveScroll(true)
                    .edgeMode(ResUtils.isRtl() ? SlideBack.EDGE_RIGHT : SlideBack.EDGE_LEFT)
                    .callBack(this::popPage)
                    .register();
        }
    }

    /**
     * 注销侧滑回调
     */
    protected void unregisterSlideBack() {
        if (isSupportSlideBack()) {
            SlideBack.unregister(this);
        }
    }

    /**
     * @return 是否支持侧滑返回
     */
    protected boolean isSupportSlideBack() {
        CoreSwitchBean page = getIntent().getParcelableExtra(CoreSwitchBean.KEY_SWITCH_BEAN);
        return page == null || page.getBundle() == null || page.getBundle().getBoolean(KEY_SUPPORT_SLIDE_BACK, true);
    }

    /**
     * 强制锁定竖屏
     */
    public void lockPort() {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//锁定竖屏
    }

    /**
     * 强制锁定横屏
     */
    public void lockLand() {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//锁定横屏
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (AppUtils.isAppForeground()) {
            // 在前台
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (AppUtils.isAppForeground()) {
            // 在前台
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            // 在后台
            XToastUtils.toast("应用进入后台", Toast.LENGTH_SHORT);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}
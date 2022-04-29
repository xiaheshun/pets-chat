package cn.proxx.chat.pets.app.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xutil.display.Colors;

import cn.proxx.android.common.base.BaseActivity;
import cn.proxx.chat.pets.app.fragment.LoginFragment;

/**
 * 登录页面
 *
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPage(LoginFragment.class, getIntent().getExtras());
    }

    @Override
    protected boolean isSupportSlideBack() {
        return false;
    }

    @Override
    protected void initStatusBarStyle() {
        StatusBarUtils.initStatusBarStyle(this, false, Colors.WHITE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }

}

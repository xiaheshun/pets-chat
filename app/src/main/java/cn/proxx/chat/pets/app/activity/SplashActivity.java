package cn.proxx.chat.pets.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.widget.activity.BaseSplashActivity;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.app.AppUtils;

import java.util.List;

import cn.proxx.android.common.constant.DataConstant;
import cn.proxx.android.common.constant.TagConstant;
import cn.proxx.android.common.util.TextUtils;
import cn.proxx.android.common.util.XToastUtils;
import cn.proxx.android.common.util.mmkv.MMKVUtils;
import me.jessyan.autosize.internal.CancelAdapt;

/**
 * 启动页
 * implements CancelAdapt 不进行适配底图
 *
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class SplashActivity extends BaseSplashActivity implements CancelAdapt {

    SplashActivity activity = this;

    @Override
    protected long getSplashDurationMillis() {
        return 200;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateActivity() {
        startSplash(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 启动页结束后的动作
     */
    @Override
    protected void onSplashFinished() {
        // 权限检测
        XXPermissions.with(activity)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                // 存储
                .permission(Permission.Group.STORAGE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        if (all) {
                            if (TextUtils.isNotEmpty(MMKVUtils.getString(DataConstant.TOKEN, null))) {
                                ActivityUtils.startActivity(MainActivity.class);
                            } else {
                                ActivityUtils.startActivity(LoginActivity.class);
                            }
                            finish();
                        } else {
                            Log.i(TagConstant.TAG_PHONE, "获取权限成功，部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean never) {
                        if (never) {
                            XToastUtils.error("被永久拒绝授权，请手动授予权限", Toast.LENGTH_LONG);
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(SplashActivity.this, true);
                            AppUtils.exitApp();
                        } else {
                            Log.i(TagConstant.TAG_PHONE, "获取权限失败，应用需要允许申请的全部权限");
                            XToastUtils.error("获取权限失败，应用需要允许申请的全部权限", Toast.LENGTH_LONG);
                        }
                    }
                });
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }

}

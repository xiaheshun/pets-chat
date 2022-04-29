package cn.proxx.chat.pets.personal.debug;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xutil.app.AppUtils;
import com.xuexiang.xutil.display.Colors;

import java.util.List;

import cn.proxx.android.common.base.BaseActivity;
import cn.proxx.android.common.util.XToastUtils;
import cn.proxx.chat.pets.personal.PersonalFragment;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 权限检测
        XXPermissions.with(this)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                .constantRequest()
                // 相机
                .permission(Permission.CAMERA)
                // 存储
                .permission(Permission.Group.STORAGE)
                // 位置
                .permission(Permission.Group.LOCATION)
                // 手机信息
                .permission(Permission.READ_PHONE_STATE)
                // 录音
                .permission(Permission.RECORD_AUDIO)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        if (all) {
                            openNewPage(PersonalFragment.class);
                        } else {
                            XToastUtils.success("部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean never) {
                        if (never) {
                            XToastUtils.error("被永久拒绝授权，请手动授予权限", Toast.LENGTH_LONG);
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(MainActivity.this, true);
                            AppUtils.exitApp();
                        } else {
                            XToastUtils.error("获取权限失败，应用需要允许申请的全部权限", Toast.LENGTH_LONG);
                        }
                    }
                });
    }

    @Override
    protected void initStatusBarStyle() {
        StatusBarUtils.initStatusBarStyle(this, false, Colors.TRANSPARENT);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }

}

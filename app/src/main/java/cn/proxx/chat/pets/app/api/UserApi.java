package cn.proxx.chat.pets.app.api;

import com.xuexiang.xutil.app.ActivityUtils;

import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.android.common.constant.DataConstant;
import cn.proxx.android.common.util.XToastUtils;
import cn.proxx.android.common.util.mmkv.MMKVUtils;
import cn.proxx.chat.pets.app.activity.MainActivity;

/**
 * @Author XiaHeShun
 * @Date 2021/1/18 14:17
 * @Version 1.0
 * @Description
 */
public class UserApi {

    /**
     * 通过用户名和密码登录
     *
     * @param userName 用户名
     * @param password 密码
     */
    public static void login(String userName, String password, BaseFragment fragment) {
        if (userName.equals("18338644204") && password.equals("123456")) {
            String token = "token123";
            // 设置全局TOKEN
            MMKVUtils.put(DataConstant.TOKEN, token);
            MMKVUtils.put(DataConstant.USER_ID, userName);
            // 打开主页面
            ActivityUtils.startActivity(MainActivity.class);
        } else {
            XToastUtils.error("用户名或密码错误");
        }
    }

}

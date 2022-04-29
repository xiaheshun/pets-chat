package cn.proxx.chat.pets.app.exception;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.xuexiang.xutil.app.AppUtils;

import java.util.List;

import cn.proxx.android.common.base.BaseActivity;
import cn.proxx.android.common.constant.TagConstant;
import cn.proxx.android.common.util.TextUtils;
import cn.proxx.android.common.util.XToastUtils;
import cn.proxx.android.common.util.data.CacheUtils;
import cn.proxx.android.common.util.mmkv.MMKVUtils;
import cn.proxx.chat.pets.app.databinding.ActivityCrashDialogBinding;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class CrashDialog extends BaseActivity<ActivityCrashDialogBinding> {

    BaseActivity activity = this;

    // 上一个页面传递进来的错误信息
    String msg;

    @Override
    protected ActivityCrashDialogBinding viewBindingInflate(LayoutInflater inflater) {
        return ActivityCrashDialogBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashDialog.this.setFinishOnTouchOutside(false);
        new Thread(upLog).start();
        initListener();
        msg = getIntent().getStringExtra("msg");
        binding.cashMsg.setText(getMemory());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initListener() {
        binding.cashExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.cashRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MMKVUtils.getsMMKV().clearAll();
                CacheUtils.clear();
                restart();
            }
        });
        binding.checkVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 检查更新
                XToastUtils.toast("检查更新，待开发");
            }
        });
    }

    // 上传错误信息
    Runnable upLog = new Runnable() {
        @Override
        public void run() {
            try {
                Log.i(TagConstant.TAG_ERROR, getMemory());
                // TODO 可以在这调你自己的接口上传信息
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppUtils.exitApp();
    }


    // 获取内存
    public String getMemory() {
        String Mobile = Build.MODEL;
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();//存放内存信息的对象
        activityManager.getMemoryInfo(memInfo);//传入参数，将获得数据保存在memInfo对象中
        long availMem = memInfo.availMem / 1024 / 1024;//可用内存
        long threshold = memInfo.threshold / 1024 / 1024;//临界值，达到这个值，进程就要被杀死
        long totalMem = memInfo.totalMem / 1024 / 1024;//总内存
        boolean isLowMem = memInfo.lowMemory;//是否达到最低内存
        String eMessage = "未获取到错误信息";
        if (TextUtils.isNotEmpty(msg)) {
            eMessage = msg;
        }
        String version = AppUtils.getAppVersionName();
        return "应用版本: " + version
                + "\n登录信息: （ 测试 ）"
                + "\n当前时间: " + ""
                + "\n手机型号: " + Mobile
                + "\n可用内存: " + availMem
                + "MB\n临界值: " + threshold
                + "MB\n总内存: " + totalMem
                + "MB\n是否达到最低内存: " + (isLowMem == true ? "是" : "否")
                + "\n错误信息: " + eMessage;
    }

    /**
     * 退出APP
     */
    private void exit() {
        killAllProcess();
        close();
    }

    /**
     * 重启APP
     */
    private void restart() {
        killAllProcess();
        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        close();
    }

    private void killAllProcess() {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        if (null != appTaskList) {
            for (ActivityManager.AppTask appTask : appTaskList) {
                appTask.finishAndRemoveTask();
            }
        }
    }

    private void close() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}

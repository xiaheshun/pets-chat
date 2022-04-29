package cn.proxx.chat.pets.app.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.app.AppUtils;
import com.xuexiang.xutil.common.CollectionUtils;
import com.xuexiang.xutil.tip.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import cn.proxx.android.common.base.BaseActivity;
import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.android.common.entity.event.EventLogout;
import cn.proxx.android.common.util.data.CacheUtils;
import cn.proxx.android.common.util.mmkv.MMKVUtils;
import cn.proxx.chat.pets.app.R;
import cn.proxx.chat.pets.app.databinding.ActivityMainBinding;
import cn.proxx.chat.pets.message.MessageFragment;
import cn.proxx.chat.pets.personal.PersonalFragment;

/**
 * 空壳
 *
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * 双击退出函数
     */
    private static boolean sIsExit = false;

    private String[] mTitles;

    // 语音朗读
    boolean isSupportTts;
    private TextToSpeech tts;

    @Override
    protected ActivityMainBinding viewBindingInflate(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
        // 注册订阅者
        EventBus.getDefault().register(this);

        // 初始化语音朗读包
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.CHINESE);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        isSupportTts = false;
                    } else {
                        isSupportTts = true;
                    }
                }
            }
        });
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {//开始播放
            }

            @Override
            public void onDone(String s) {//完成之后
            }

            @Override
            public void onError(String s) {//播放错误的处理

            }
        });
    }

    private void initViews() {
        // 主页内容填充
        BaseFragment[] fragments;
        binding.navigation.inflateMenu(R.menu.navigation);
        mTitles = ResUtils.getStringArray(R.array.home_titles);
        fragments = new BaseFragment[]{
                new MessageFragment(),
                new PersonalFragment()
        };

        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        binding.viewPager.setOffscreenPageLimit(mTitles.length - 1);
        binding.viewPager.setAdapter(adapter);
        // 设置默认打开页面
        onPageSelected(1);
        binding.viewPager.setCurrentItem(1, false);
    }

    protected void initListeners() {
        //主页事件监听
        binding.viewPager.addOnPageChangeListener(this);
        binding.navigation.setOnNavigationItemSelectedListener(this);
    }

    //=============binding.viewPager===================//

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        MenuItem item = binding.navigation.getMenu().getItem(position);
        item.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * 底部导航栏点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            binding.viewPager.setCurrentItem(index, false);
            return true;
        }
        return false;
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!sIsExit) {
                sIsExit = true;
                // 准备退出
                ToastUtils.toast("再按一次返回桌面", Toast.LENGTH_SHORT);
                Timer tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // 取消退出
                        sIsExit = false;
                    }
                    // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
                }, 2000);
            } else {
                moveTaskToBack(true);
            }
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealEventBus(EventLogout logout) {
        if (EventLogout.LOGIN == logout.getCode()) {
            MMKVUtils.getsMMKV().clearAll();
            finish();
            ActivityUtils.startActivity(LoginActivity.class);
        } else if (EventLogout.EXIT == logout.getCode()) {
            MMKVUtils.getsMMKV().clearAll();
            AppUtils.exitApp();
        } else if (EventLogout.REBOOT == logout.getCode()) {
            MMKVUtils.getsMMKV().clearAll();
            AppUtils.rebootApp(500);
        }
        // 清除工具类的二级缓存
        CacheUtils.clear();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
        // 注销注册
        EventBus.getDefault().unregister(this);
    }
}
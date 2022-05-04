package cn.proxx.chat.pets.message.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.chat.pets.entity.event.EventMsg;
import cn.proxx.chat.pets.message.databinding.FragmentFriendBinding;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Page(name = "通讯录")
public class FriendFragment extends BaseFragment<FragmentFriendBinding> {

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected FragmentFriendBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentFriendBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealEventBus(EventMsg event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

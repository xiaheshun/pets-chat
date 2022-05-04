package cn.proxx.chat.pets.message.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.proxx.android.common.annotation.SingleClick;
import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.chat.pets.db.mapper.FriendMapper;
import cn.proxx.chat.pets.db.table.FriendDO;
import cn.proxx.chat.pets.entity.event.EventMsg;
import cn.proxx.chat.pets.message.R;
import cn.proxx.chat.pets.message.atapter.FriendAdapter;
import cn.proxx.chat.pets.message.databinding.FragmentFriendBinding;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Page(name = "通讯录")
public class FriendFragment extends BaseFragment<FragmentFriendBinding> {

    FriendAdapter friendListAdapter;
    LinearLayoutManager friendListManager;

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.disableLeftView();
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_user_add) {
            @Override
            @SingleClick
            public void performAction(View view) {
                openNewPage(FriendAddFragment.class);
            }
        });
        return titleBar;
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
        // 初始化列表
        friendListAdapter = new FriendAdapter();
        friendListManager = new LinearLayoutManager(getContext());
        binding.rvFriendList.setLayoutManager(friendListManager);
        binding.rvFriendList.setItemAnimator(new DefaultItemAnimator());
        binding.rvFriendList.setAdapter(friendListAdapter);
        // 加载数据
        initFriendList();
    }

    @Override
    protected void initListeners() {
        friendListAdapter.setOnItemClickListener((itemView, item, position) -> {
            openNewPage(SendMailFragment.class, "friendId", item.getId());
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealEventBus(EventMsg event) {
        if (EventMsg.FRIEND_NEW == event.getCode()) {
            // 刷新通讯录
            initFriendList();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化通讯录列表
     */
    private void initFriendList() {
        List<FriendDO> list = FriendMapper.getInstance().queryAll();
        if (null != list) {
            friendListAdapter.refresh(list);
            friendListAdapter.notifyDataSetChanged();
        }
    }
}

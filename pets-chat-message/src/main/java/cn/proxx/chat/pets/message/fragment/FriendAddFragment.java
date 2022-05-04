package cn.proxx.chat.pets.message.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.UUID;

import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.android.common.util.XToastUtils;
import cn.proxx.chat.pets.db.mapper.FriendMapper;
import cn.proxx.chat.pets.db.table.FriendDO;
import cn.proxx.chat.pets.entity.event.EventMsg;
import cn.proxx.chat.pets.message.R;
import cn.proxx.chat.pets.message.databinding.FragmentFriendAddBinding;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Page(name = "添加联系人")
public class FriendAddFragment extends BaseFragment<FragmentFriendAddBinding> implements View.OnClickListener {

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        return titleBar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected FragmentFriendAddBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentFriendAddBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        binding.btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            // 组装数据
            String name = binding.etName.getText().toString();
            String webHook = binding.etWebHook.getText().toString();
            String secret = binding.etSecret.getText().toString();
            FriendDO friend = new FriendDO();
            friend.setId(UUID.randomUUID().toString());
            friend.setName(name);
            friend.setWebHook(webHook);
            friend.setSecret(secret);
            friend.setType(0);
            friend.setCreateTime(new Date());
            friend.setUpdateTime(new Date());
            // 查找是否存在
            FriendDO isExists = FriendMapper.getInstance().checkIsExists(friend);
            if (null == isExists) {
                FriendMapper.getInstance().insert(friend);
                XToastUtils.toast("添加成功");
                EventBus.getDefault().post(new EventMsg<>().setCode(EventMsg.FRIEND_NEW));
            } else {
                XToastUtils.error("已存在，无需添加");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealEventBus(EventMsg event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

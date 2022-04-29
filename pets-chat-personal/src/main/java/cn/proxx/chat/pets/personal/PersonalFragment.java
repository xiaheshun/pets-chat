package cn.proxx.chat.pets.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import cn.proxx.android.common.annotation.SingleClick;
import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.android.common.constant.DataConstant;
import cn.proxx.android.common.entity.event.EventLogout;
import cn.proxx.android.common.util.mmkv.MMKVUtils;
import cn.proxx.chat.pets.db.mapper.UserMapper;
import cn.proxx.chat.pets.db.table.UserDO;
import cn.proxx.chat.pets.personal.databinding.FragmentPersonalBinding;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Page(name = "用户")
public class PersonalFragment extends BaseFragment<FragmentPersonalBinding> implements View.OnClickListener {

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
    protected FragmentPersonalBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentPersonalBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        // 初始化用户信息
        UserDO user = UserMapper.getInstance().queryById(MMKVUtils.getString(DataConstant.USER_ID, ""));
        binding.userDm.setText("账号：测试");
        binding.userXm.setText("用户名：测试");
    }

    @Override
    protected void initListeners() {
        binding.logout.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logout) {
            EventBus.getDefault().post(new EventLogout().setCode(EventLogout.LOGIN));
        }
    }
}

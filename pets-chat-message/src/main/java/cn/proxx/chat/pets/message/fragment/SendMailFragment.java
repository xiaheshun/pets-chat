package cn.proxx.chat.pets.message.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xutil.tip.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.chat.pets.db.mapper.FriendMapper;
import cn.proxx.chat.pets.db.table.FriendDO;
import cn.proxx.chat.pets.entity.event.EventMsg;
import cn.proxx.chat.pets.message.R;
import cn.proxx.chat.pets.message.databinding.FragmentSendMailBinding;
import cn.proxx.chat.pets.message.util.WebHookUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Page(name = "发送信件")
public class SendMailFragment extends BaseFragment<FragmentSendMailBinding> implements View.OnClickListener {

    private FriendDO friend;

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
    protected FragmentSendMailBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentSendMailBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initArgs() {
        super.initArgs();
        Bundle bundle = getArguments();
        String friendId = bundle.getString("friendId");
        friend = FriendMapper.getInstance().queryById(friendId);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        binding.btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_send) {
            String content = binding.etContent.getText().toString();
            Callback callback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtils.toast("失败 " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ToastUtils.toast(response.body().string());
                }
            };
            WebHookUtils.sendDingDingText(friend.getWebHook(), friend.getSecret(), content, callback);
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

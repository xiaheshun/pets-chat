package cn.proxx.chat.pets.app.fragment;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xutil.app.AppUtils;

import androidx.annotation.NonNull;
import cn.proxx.android.common.annotation.SingleClick;
import cn.proxx.android.common.base.BaseFragment;
import cn.proxx.android.common.util.XToastUtils;
import cn.proxx.chat.pets.app.BuildConfig;
import cn.proxx.chat.pets.app.R;
import cn.proxx.chat.pets.app.api.UserApi;
import cn.proxx.chat.pets.app.databinding.FragmentLoginBinding;

/**
 * @author xiaheshun
 * @since 2022年4月29日 16:35
 */
@Page(name = "账号登录", anim = CoreAnim.none)
public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements View.OnClickListener {

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @NonNull
    @Override
    protected FragmentLoginBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        // 设置账号输入框默认为数字键盘
        binding.etUsername.setRawInputType(Configuration.KEYBOARD_QWERTY);
        // 设置软件版本号
        String str = "";
        if (BuildConfig.DEBUG) {
            str = "（内测）";
        }
        binding.tvVersion.setText(String.format(str + "版本号：%s", AppUtils.getAppVersionName()));
    }

    @Override
    protected void initListeners() {
        binding.btnLogin.setOnClickListener(this);
        binding.tvCompany.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (binding.etUsername.getEditValue().isEmpty()) {
                    XToastUtils.info("手机号不可为空");
                } else {
                    UserApi.login(binding.etUsername.getEditValue(), binding.etPassword.getEditValue(), this);
                }
                break;
            case R.id.tv_company:
                XToastUtils.toast("业余开发者");
                break;
            default:
                break;
        }
    }
}

package cn.proxx.android.common.net.update;

import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;

import cn.proxx.android.common.util.XToastUtils;

/**
 * 自定义版本更新提示
 *
 * @author XiaHeshun
 */
public class CustomUpdateFailureListener implements OnUpdateFailureListener {

    /**
     * 是否需要错误提示
     */
    private boolean mNeedErrorTip;

    public CustomUpdateFailureListener() {
        this(true);
    }

    public CustomUpdateFailureListener(boolean needErrorTip) {
        mNeedErrorTip = needErrorTip;
    }

    /**
     * 更新失败
     *
     * @param error 错误
     */
    @Override
    public void onFailure(UpdateError error) {
        if (mNeedErrorTip) {
            XToastUtils.toast(error.getMessage());
        }
    }
}

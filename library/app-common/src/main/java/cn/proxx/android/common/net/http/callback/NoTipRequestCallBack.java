package cn.proxx.android.common.net.http.callback;

import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xhttp2.model.XHttpRequest;
import com.xuexiang.xutil.common.logger.Logger;

import cn.proxx.android.common.util.TextUtils;

/**
 * @author XiaHeShun
 * @since 2021年5月18日13:15:42
 */
public abstract class NoTipRequestCallBack<T> extends SimpleCallBack<T> {
    /**
     * 记录一下请求的url,确定出错的请求是哪个请求
     */
    private String mUrl;

    public NoTipRequestCallBack() {

    }

    public NoTipRequestCallBack(XHttpRequest req) {
        this(req.getUrl());
    }

    public NoTipRequestCallBack(String url) {
        mUrl = url;
    }

    @Override
    public void onError(ApiException e) {
        if (TextUtils.isNotEmpty(mUrl)) {
            Logger.e("网络请求的url:" + mUrl, e);
        } else {
            Logger.e(e);
        }
    }
}

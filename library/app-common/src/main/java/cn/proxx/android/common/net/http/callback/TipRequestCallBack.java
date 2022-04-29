package cn.proxx.android.common.net.http.callback;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xhttp2.model.XHttpRequest;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xutil.common.logger.Logger;

import cn.proxx.android.common.util.TextUtils;
import cn.proxx.android.common.util.XToastUtils;

/**
 * @author XiaHeShun
 * @since 2021年5月18日13:15:42
 */
public abstract class TipRequestCallBack<T> extends SimpleCallBack<T> {

    Context context;
    Dialog dialog;

    /**
     * 记录一下请求的url,确定出错的请求是哪个请求
     */
    private String mUrl;

    public TipRequestCallBack(Context context) {
        this.context = context;
    }

    public TipRequestCallBack(@NonNull XHttpRequest req) {
        this(req.getUrl());
    }

    public TipRequestCallBack(String url) {
        mUrl = url;
    }

    @Override
    public void onStart() {
        dialog = WidgetUtils.getMiniLoadingDialog(context);
        dialog.show();
    }

    @Override
    public void onCompleted() {
        dialog.dismiss();
    }

    @Override
    public void onError(ApiException e) {
        dialog.dismiss();
        XToastUtils.error(e.getDisplayMessage());
        if (TextUtils.isNotEmpty(mUrl)) {
            Logger.e("网络请求的url:" + mUrl, e);
        } else {
            Logger.e(e);
        }
    }
}

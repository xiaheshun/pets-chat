package cn.proxx.android.common.net.http;

import com.xuexiang.xhttp2.model.ApiResult;

public class HttpApiResult<T> extends ApiResult<T> {

    @Override
    public boolean isSuccess() {
        return getCode() == 200;
    }

}

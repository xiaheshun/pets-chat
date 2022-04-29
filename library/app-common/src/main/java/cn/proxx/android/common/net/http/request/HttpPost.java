package cn.proxx.android.common.net.http.request;

import com.xuexiang.xhttp2.callback.CallBack;
import com.xuexiang.xhttp2.callback.CallBackProxy;
import com.xuexiang.xhttp2.callback.CallClazzProxy;
import com.xuexiang.xhttp2.request.PostRequest;

import java.lang.reflect.Type;

import cn.proxx.android.common.net.http.HttpApiResult;
import cn.proxx.android.common.net.http.HttpConfig;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author XiaHeShun
 * @since 2021年5月18日13:15:42
 */
public class HttpPost extends PostRequest {
    public HttpPost(String url) {
        super(HttpConfig.PREFIX + url);
    }

    @Override
    public <T> Observable<T> execute(Type type) {
        return execute(new CallClazzProxy<HttpApiResult<T>, T>(type) {
        });
    }

    @Override
    public <T> Disposable execute(CallBack<T> callBack) {
        return execute(new CallBackProxy<HttpApiResult<T>, T>(callBack) {
        });
    }
}

package cn.proxx.android.common.net.update;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.impl.DefaultUpdateDownloader;
import com.xuexiang.xupdate.service.OnFileDownloadListener;

/**
 * 重写DefaultUpdateDownloader，在取消下载时，弹出提示
 *
 * @author XiaHeshun
 */
public class CustomUpdateDownloader extends DefaultUpdateDownloader {

    private boolean mIsStartDownload;

    @Override
    public void startDownload(@NonNull UpdateEntity updateEntity, @Nullable OnFileDownloadListener downloadListener) {
        super.startDownload(updateEntity, downloadListener);
        mIsStartDownload = true;
    }

    @Override
    public void cancelDownload() {
        super.cancelDownload();
        if (mIsStartDownload) {
            mIsStartDownload = false;
        }
    }

}

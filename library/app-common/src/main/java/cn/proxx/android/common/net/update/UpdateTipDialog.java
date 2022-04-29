package cn.proxx.android.common.net.update;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 版本更新提示弹窗
 *
 * @author XiaHeshun
 */
public class UpdateTipDialog extends AppCompatActivity implements DialogInterface.OnDismissListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        this.finish();
    }

}

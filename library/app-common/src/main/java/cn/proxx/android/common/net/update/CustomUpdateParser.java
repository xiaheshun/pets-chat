package cn.proxx.android.common.net.update;

import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.impl.AbstractUpdateParser;

/**
 * 版本更新信息自定义json解析器
 *
 * @author XiaHeshun
 */
public class CustomUpdateParser extends AbstractUpdateParser {

    @Override
    public UpdateEntity parseJson(String json) throws Exception {
//        Result<AppVersionInfo> result = JSON.parseObject(json, new TypeReference<Result<AppVersionInfo>>() {
//        });
//        AppVersionInfo data = result.getData();
//        if (null != result && null != result.getData()) {
//            // updateStatus =  0 无版本更新， 1 有版本更新，不强制， 2 有版本更新，强制
//            if (data.getUpdateStatus() == 0){
//                return new UpdateEntity()
//                        .setHasUpdate(false);
//            }else{
//                return new UpdateEntity()
//                        .setHasUpdate(true)
//                        .setForce(data.getUpdateStatus() == 2 ? true : false)
//                        .setIsIgnorable(false)
//                        .setVersionCode(data.getVersionCode())
//                        .setVersionName(data.getVersionName())
//                        .setUpdateContent(data.getModifyContent())
//                        .setDownloadUrl(data.getDownloadUrl())
//                        .setSize(data.getApkSize());
//            }
//
//        }
        return null;
    }

}

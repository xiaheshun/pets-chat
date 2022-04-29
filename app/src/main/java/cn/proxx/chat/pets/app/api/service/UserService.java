package cn.proxx.chat.pets.app.api.service;

import com.xuexiang.xhttp2.annotation.NetMethod;

import cn.proxx.chat.pets.entity.vo.UserVO;
import io.reactivex.Observable;

import static com.xuexiang.xhttp2.annotation.NetMethod.GET;

public class UserService {

    public interface IGetService {
        // 获取服务器状态

        /**
         * 获取服务器状态
         *
         * @return 服务启动信息
         */
        @NetMethod(action = GET, url = "/arch/info", accessToken = false)
        Observable<String> archInfo();
    }

    public interface IPostService {
        /**
         * 用户登录
         *
         * @param username 用户名
         * @param password 密码
         * @return 用户信息
         */
        @NetMethod(parameterNames = {"username", "password"}, url = "/login", accessToken = false)
        Observable<UserVO> login(String username, String password);
    }

}

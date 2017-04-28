package com.iflytek.dailysentence.model.http;
import com.iflytek.dailysentence.model.bean.UserBean;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public interface ApiService {

    //生产环境
    //String BASE_URL = "http://60.174.196.102:3080/api/";
    //测试环境
    String BASE_URL = "http://60.174.196.102:3081/api/";
    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("users/login")
    Observable<CommonHttpRsp<UserBean>> doLogin(@Field("account") String account, @Field("password") String password);


}

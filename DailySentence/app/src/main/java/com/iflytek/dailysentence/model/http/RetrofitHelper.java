package com.iflytek.dailysentence.model.http;

import android.content.Context;
import android.net.ConnectivityManager;

import com.iflytek.dailysentence.constants.Constants;
import com.iflytek.dailysentence.model.bean.UserBean;
import com.iflytek.dailysentence.util.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public class RetrofitHelper {
    private OkHttpClient okHttpClient = null;
    private ApiService apiService = null;
    private static final int PAGE_SIZE = 20;
    private Context mContext;

    public RetrofitHelper(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        initOkHttp();
        apiService = getApiService();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                boolean isNetworkConnect = NetworkUtil.isNetworkAvailable(connectivityManager);
                if (!isNetworkConnect) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (isNetworkConnect) {
                    int maxAge = 0;
                    //有网络时，不缓存，最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    //无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public,only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }


    private ApiService getApiService() {
        Retrofit apiServiceRetrofit =
                new Retrofit.Builder()
                        .baseUrl(ApiService.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
        return apiServiceRetrofit.create(ApiService.class);
    }

    public Observable<CommonHttpRsp<UserBean>> doLogin(String username, String password) {
        return apiService.doLogin(username, password);
    }
}

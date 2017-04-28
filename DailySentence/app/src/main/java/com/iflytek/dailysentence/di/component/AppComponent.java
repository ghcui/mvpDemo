package com.iflytek.dailysentence.di.component;

import com.iflytek.dailysentence.app.App;
import com.iflytek.dailysentence.di.ContextLife;
import com.iflytek.dailysentence.di.module.AppModule;
import com.iflytek.dailysentence.model.db.RealmHelper;
import com.iflytek.dailysentence.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author ghcui
 * @time 2017/1/11
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();//提供App的Context

    RetrofitHelper  retrofitHelper(); //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类
}

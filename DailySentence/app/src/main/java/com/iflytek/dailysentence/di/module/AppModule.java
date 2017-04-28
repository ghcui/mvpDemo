package com.iflytek.dailysentence.di.module;

import com.iflytek.dailysentence.app.App;
import com.iflytek.dailysentence.di.ContextLife;
import com.iflytek.dailysentence.model.db.RealmHelper;
import com.iflytek.dailysentence.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author ghcui
 * @time 2017/1/11
 */
@Module
public class AppModule {
    private App application;

    public AppModule(App application){
        this.application=application;
    }
    @Provides
    @Singleton
    @ContextLife("Application")
    public App provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    public RetrofitHelper provideRetrofitHelper(){
        return new RetrofitHelper(application);
    }
    @Provides
    @Singleton
    public RealmHelper provideRealmHelper(){
        return new RealmHelper(application);
    }

}

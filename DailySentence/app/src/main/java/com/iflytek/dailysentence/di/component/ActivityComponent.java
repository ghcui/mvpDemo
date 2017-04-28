package com.iflytek.dailysentence.di.component;

import android.app.Activity;

import com.iflytek.dailysentence.ui.activity.LoginActivity;
import com.iflytek.dailysentence.di.ActivityScope;
import com.iflytek.dailysentence.di.module.ActivityModule;
import com.iflytek.dailysentence.ui.activity.MainActivity;


import dagger.Component;

/**
 * @author ghcui
 * @time 2017/1/11
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    void inject(MainActivity activity);
    void inject(LoginActivity activity);
}

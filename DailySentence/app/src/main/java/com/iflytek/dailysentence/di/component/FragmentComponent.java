package com.iflytek.dailysentence.di.component;

import android.app.Activity;

import com.iflytek.dailysentence.di.FragmentScope;
import com.iflytek.dailysentence.di.module.FragmentModule;
import com.iflytek.dailysentence.ui.fragment.RegionalRankingFragment;

import dagger.Component;

/**
 * @author ghcui
 * @time 2017/1/11
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
    void inject(RegionalRankingFragment fragment);
}

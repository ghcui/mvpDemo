package com.iflytek.dailysentence.presenter;


import com.iflytek.dailysentence.base.RxPresenter;
import com.iflytek.dailysentence.model.http.CommonHttpRsp;
import com.iflytek.dailysentence.model.http.RetrofitHelper;
import com.iflytek.dailysentence.presenter.contract.MainContract;
import com.iflytek.dailysentence.rx.ExSubscriber;
import com.iflytek.dailysentence.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }




}

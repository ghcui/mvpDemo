package com.iflytek.dailysentence.rx;


import android.text.TextUtils;

import com.iflytek.dailysentence.base.BaseView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;


/**
 * @author ghcui
 * @time 2017/1/13
 */
public abstract class ExSubscriber<T> extends Subscriber<T> {

    public static final String TAG = "ExSubscriber";
    private BaseView view;
    //操作标记,用于多个操作的区分
    private int tag = 0;

    /**
     * 界面上单个操作可不传tag，默认为0
     * @param view
     */
    public ExSubscriber(BaseView view) {
        this(view,0);
    }
    /**
     * 界面上多个操作需传tag，以便区分操作
     * @param view
     */
    public ExSubscriber(BaseView view,int tag) {
        this.view = view;
        this.tag = tag;
    }

    @Override
    public void onStart() {
        showLoading(tag);
    }

    @Override
    public void onCompleted() {
        cancelLoading(tag);
    }

    @Override
    public void onError(Throwable e) {
        cancelLoading(tag);
        e.printStackTrace();
        if (e instanceof retrofit2.HttpException) {
            this.view.showError("连接服务器异常!");
        } else if (e instanceof ConnectException) {
            this.view.showError("网络断开,请检查网络!");
        } else if (e instanceof SocketTimeoutException) {
            this.view.showError("网络连接超时!");
        } else {
            String errormsg = e.getMessage();
            if (TextUtils.isEmpty(errormsg)) {
                errormsg = "请求失败!";
            }
            this.view.showError(errormsg);
        }
    }


    @Override
    public void onNext(T t) {
        if (t == null) {
            cancelLoading(tag);
            this.view.showError("请求失败!");
            return;
        }
        onSuccess(t,tag);
    }

    public void cancelLoading(int tag){

    }
    public void showLoading(int tag){

    }

    protected abstract void onSuccess(T t,int tag);


}

package com.iflytek.dailysentence.base;

/**
 * Created by codeest on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showLoading(int tag);

    void cancelLoading(int tag);

    void showError(String msg);

    void useNightMode(boolean isNight);

}

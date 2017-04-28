package com.iflytek.dailysentence.presenter.contract;

import com.iflytek.dailysentence.base.BasePresenter;
import com.iflytek.dailysentence.base.BaseView;

import java.util.List;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public interface MainContract {

    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View> {

    }
}

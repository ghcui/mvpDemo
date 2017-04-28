package com.iflytek.dailysentence.presenter.contract;

import com.iflytek.dailysentence.base.BasePresenter;
import com.iflytek.dailysentence.base.BaseView;
import com.iflytek.dailysentence.model.bean.UserBean;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public interface LoginContract {

    interface View extends BaseView{
        void jump2Main(UserBean userBean);
    }
    interface Presenter extends BasePresenter<View> {
        void doLogin(String username,String password);
    }
}

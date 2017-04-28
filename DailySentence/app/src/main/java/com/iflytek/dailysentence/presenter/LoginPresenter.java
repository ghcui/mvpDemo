package com.iflytek.dailysentence.presenter;


import com.iflytek.dailysentence.app.App;
import com.iflytek.dailysentence.base.RxPresenter;
import com.iflytek.dailysentence.model.bean.UserBean;
import com.iflytek.dailysentence.model.db.RealmHelper;
import com.iflytek.dailysentence.model.http.CommonHttpRsp;
import com.iflytek.dailysentence.model.http.RetrofitHelper;
import com.iflytek.dailysentence.presenter.contract.LoginContract;
import com.iflytek.dailysentence.rx.ExSubscriber;
import com.iflytek.dailysentence.util.RxUtil;



import javax.inject.Inject;

import rx.Subscription;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public LoginPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void doLogin(final String username, final String password) {
        Subscription rxSubscription = mRetrofitHelper.doLogin(username, password)
                .compose(RxUtil.<CommonHttpRsp<UserBean>>rxSchedulerHelper())
                .compose(RxUtil.<UserBean>handleResult())
                .subscribe(new ExSubscriber<UserBean>(mView) {
                    @Override
                    protected void onSuccess(UserBean userBean) {
                        App.getInstance().saveUserInfo(userBean);
                        realmHelper.addUser(userBean);
                        mView.jump2Main(userBean);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}

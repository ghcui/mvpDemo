package com.iflytek.dailysentence.app;

import android.support.v7.app.AppCompatDelegate;

import com.tencent.bugly.crashreport.CrashReport;
import com.iflytek.dailysentence.base.BaseApplication;
import com.iflytek.dailysentence.di.component.AppComponent;
import com.iflytek.dailysentence.di.component.DaggerAppComponent;
import com.iflytek.dailysentence.di.module.AppModule;
import com.iflytek.dailysentence.model.bean.UserBean;
import com.iflytek.dailysentence.model.db.RealmHelper;
import com.iflytek.dailysentence.util.ToastUtil;


/**
 * @author ghcui
 * @time 2017/1/11
 */
public class App extends BaseApplication {
    private static App instance;
    private UserBean userBean;

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initBugly();
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "72f85baf3e", false);
    }


    public static synchronized App getInstance() {
        return instance;
    }


    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }


    public void saveUserInfo(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserBean getUserInfo() {
        if (userBean == null) {
            ToastUtil.showNoticeToast(this, "系统内存,程序重新启动！");
            userBean = new RealmHelper(this).getUserBean();
        }
        return userBean;
    }
}

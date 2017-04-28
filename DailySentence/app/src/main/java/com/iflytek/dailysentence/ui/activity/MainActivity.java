package com.iflytek.dailysentence.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.iflytek.dailysentence.R;
import com.iflytek.dailysentence.app.App;
import com.iflytek.dailysentence.base.BaseActivity;
import com.iflytek.dailysentence.presenter.MainPresenter;
import com.iflytek.dailysentence.presenter.contract.MainContract;
import com.iflytek.dailysentence.util.ToastUtil;



public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    /**
     * 定义一个变量，来标识是否退出
     */
    private  boolean isExit = false;
    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
    }



    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, this.getResources().getString(R.string.warming_doubleclick_logout), Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            App.getInstance().killAllActivities();
            super.onBackPressedSupport();
        }
    }

    @Override
    public void onBackPressedSupport() {
        exit();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void showLoading(int tag) {

    }

    @Override
    public void cancelLoading(int tag) {

    }

    @Override
    public void showError(String msg) {
        ToastUtil.showErrorToast(this,msg);
    }




}


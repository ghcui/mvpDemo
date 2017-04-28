package com.iflytek.dailysentence.rx;


import android.text.TextUtils;

import com.iflytek.dailysentence.base.BaseView;
import com.iflytek.dailysentence.model.http.BaseHttpRsp;

/**
 * @author ghcui
 * @time 2017/3/16
 */
public abstract class BaseSubscriber extends ExSubscriber<BaseHttpRsp> {

    public BaseSubscriber(BaseView view) {
        super(view);
    }
    public BaseSubscriber(BaseView view,int tag) {
      super(view,tag);
    }

    @Override
    protected void onSuccess(BaseHttpRsp httpRsp,int tag) {
        if (httpRsp.getCode() == 200) {
            onSuccess();
        } else {
            int errorCode = httpRsp.getCode();
            String errorMsg = httpRsp.getMessage();
            if(TextUtils.isEmpty(errorMsg)){
                errorMsg="请求失败";
            }
            onFailure(errorCode, errorMsg);
        }
    }

    protected abstract void onSuccess();

    protected abstract void onFailure(int errorCode, String msg);
}

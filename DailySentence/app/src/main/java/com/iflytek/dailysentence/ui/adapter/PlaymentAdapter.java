package com.iflytek.dailysentence.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iflytek.dailysentence.R;
import com.iflytek.dailysentence.model.bean.UserBean;

import java.util.List;



public class PlaymentAdapter extends BaseQuickAdapter<UserBean,BaseViewHolder> {
    public PlaymentAdapter(List<UserBean> data) {
        super(R.layout.item_playment,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        TextView txtCustomerName=helper.getView(R.id.customer_name);
        TextView txtTime=helper.getView(R.id.time);
        txtCustomerName.setText(item.name);
    }
}

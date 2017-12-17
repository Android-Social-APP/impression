package com.bdxw.impression.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Select_Login_Activity extends BaseActivity {


    @BindView(R.id.login_wechat)
    TextView mLoginWechat;
    @BindView(R.id.login_qq)
    TextView mLoginQq;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_select__login_;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.login_wechat, R.id.login_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_wechat:
                break;
            case R.id.login_qq:
                break;
        }
    }
}

package com.bdxw.impression.activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;


import butterknife.BindView;
import butterknife.OnClick;

public class Select_Login_Activity extends BaseActivity {


    @BindView(R.id.login_wechat)
    TextView mLoginWechat;
    @BindView(R.id.login_qq)
    TextView mLoginQq;
    private boolean mBoolean;
    private boolean isBoolean;
    private SharedPreferences mSharedPreferences1;
    private SharedPreferences mSharedPreferences;
    private String mUid;

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
                startActivity(new Intent(Select_Login_Activity.this, QQLoginActivity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom);
                finish();
                break;
        }
    }

    //设置 手机返回键的动画
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.zoom_out);
    }
}

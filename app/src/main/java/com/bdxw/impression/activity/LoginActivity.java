package com.bdxw.impression.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_user_setting)
    ImageView mLoginUserSetting;
    @BindView(R.id.login_user_img)
    ImageView mLoginUserImg;
    @BindView(R.id.login_user_name)
    TextView mLoginUserName;
    @BindView(R.id.login_text_fabulous)
    TextView mLoginTextFabulous;
    @BindView(R.id.login_text_fensi)
    TextView mLoginTextFensi;
    @BindView(R.id.login_message)
    TextView mLoginMessage;
    @BindView(R.id.login_topic)
    TextView mLoginTopic;
    @BindView(R.id.login_see_hear)
    TextView mLoginSeeHear;
    @BindView(R.id.login_self_attention)
    TextView mLoginSelfAttention;
    @BindView(R.id.login_share_drawable_left)
    TextView mLoginShareDrawableLeft;
    @BindView(R.id.login_exit)
    ImageView mLoginExit;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    //设置 手机返回键的动画
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.zoom_out);
    }


    @OnClick({R.id.login_user_setting, R.id.login_user_img, R.id.login_user_name, R.id.login_text_fabulous, R.id.login_text_fensi, R.id.login_message, R.id.login_topic, R.id.login_see_hear, R.id.login_self_attention, R.id.login_share_drawable_left,R.id.login_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 登陆界面的设置按钮
            case R.id.login_user_setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            // 登陆界面的用户头像
            case R.id.login_user_img:
                break;
            //登陆界面的用户名字
            case R.id.login_user_name:
                break;
            //登陆界面的赞赏
            case R.id.login_text_fabulous:
                break;
            //登陆界面的粉丝
            case R.id.login_text_fensi:
                break;
            //登陆界面的 我的消息
            case R.id.login_message:
                break;
            // 登陆界面的 全部话题
            case R.id.login_topic:
                break;
            // 登陆界面的全部文章
            case R.id.login_see_hear:
                break;
            // 登陆界面的我的关注
            case R.id.login_self_attention:
                break;
            //登陆界面的分享APP
            case R.id.login_share_drawable_left:
                break;
            //登陆界面的退出
            case R.id.login_exit:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                overridePendingTransition(0, R.anim.zoom_out);
                break;
        }
    }
}

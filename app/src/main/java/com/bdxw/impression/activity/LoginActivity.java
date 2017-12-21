package com.bdxw.impression.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

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

    //调用QQ接口的方法获取QQ信息
    private List<String> mList = new ArrayList<String>();
    private boolean zt1;
    private SharedPreferences qq2;
    private boolean qq1;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginUserName = findViewById(R.id.login_user_name);
        mLoginUserImg = findViewById(R.id.login_user_img);
    }

    @Override
    protected void initData() {
        //回调用户信息 完成QQ登陆
        SharedPreferences zt = LoginActivity.this.getSharedPreferences("ZT", MODE_PRIVATE);
        zt1 = zt.getBoolean("zt", false);
        qq2 = LoginActivity.this.getSharedPreferences("QQ", MODE_PRIVATE);
        qq1 = qq2.getBoolean("状态", false);
        if (qq1 == true) {
            String touxiang = qq2.getString("头像", null);
            //获取QQ头像并设置成圆形头像
            Glide.with(this).load(touxiang)
                    .bitmapTransform(new CropCircleTransformation(this))
                    .into(mLoginUserImg);
            //获取QQ昵称
            String nc = qq2.getString("昵称", null);
            //获取昵称并显示在TextView上
            mLoginUserName.setText(nc);
            mLoginUserName.setBackgroundColor(Color.parseColor("#00000000"));
        } else if (zt1 == true) {
            String sj = zt.getString("sj", null);
            mLoginUserName.setText(sj);
        }
    }

    //设置 手机返回键的动画
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.zoom_out);
    }

    @OnClick({R.id.login_user_setting, R.id.login_user_name, R.id.login_text_fabulous, R.id.login_text_fensi, R.id.login_message, R.id.login_topic, R.id.login_see_hear, R.id.login_self_attention, R.id.login_share_drawable_left, R.id.login_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 登陆界面的设置按钮
            case R.id.login_user_setting:
                startActivity(new Intent(LoginActivity.this, SignOutActivity.class));
                finish();
                break;
            //登陆界面的用户名字 登陆按钮
            case R.id.login_user_name:
                startActivity(new Intent(this, Select_Login_Activity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom);
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
                startActivity(new Intent(LoginActivity.this, All_topicsActivity.class));
                finish();
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

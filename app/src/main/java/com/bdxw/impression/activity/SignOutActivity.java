package com.bdxw.impression.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class SignOutActivity extends BaseActivity {

    private TextView mSignOut;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_sign_out;
    }

    @Override
    protected void initView() {
        mSignOut = findViewById(R.id.sign_out);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出QQ登录
                UMShareAPI.get(SignOutActivity.this).deleteOauth(SignOutActivity.this, SHARE_MEDIA.QQ, null);
                SharedPreferences zt = getSharedPreferences("ZT", MODE_PRIVATE);
                zt.edit().putBoolean("zt", false).putString("sj", null).commit();
                SharedPreferences qq = getSharedPreferences("QQ", MODE_PRIVATE);
                qq.edit().putBoolean("状态", false).putString("头像", null).putString("昵称", null).commit();
                Intent intent = new Intent(SignOutActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

}

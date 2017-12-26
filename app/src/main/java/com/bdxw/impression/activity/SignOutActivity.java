package com.bdxw.impression.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class SignOutActivity extends BaseActivity {

    private TextView mSignOut;
    private boolean mBoolean;
    private boolean isBoolean;
    private SharedPreferences mSharedPreferences1;
    private SharedPreferences mSharedPreferences;
    private String mUid;

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
                /**
                 * 判断登陆状态..........
                 * 拿取SharedPreferences里面存取的QQ用户的uid
                 * 通过uid判断登陆状态
                 */
                mSharedPreferences = getSharedPreferences("ZT", MODE_PRIVATE);
                mBoolean = mSharedPreferences.getBoolean("zt", false);
                mSharedPreferences1 = getSharedPreferences("QQ", MODE_PRIVATE);
                isBoolean = mSharedPreferences1.getBoolean("状态", false);
                if (isBoolean == true) {
                    mUid = mSharedPreferences1.getString("uid", null);
                    Log.e("SignOutActivity", "uid - - - - - - - - - - -" + mUid);
                }
                // 使用QQ用户的uid判断登陆状态
                if (mUid == null) {
                    Toast.makeText(SignOutActivity.this, "您还没有登陆", Toast.LENGTH_SHORT).show();
                } else {
                    //退出QQ登录
                    UMShareAPI.get(SignOutActivity.this).deleteOauth(SignOutActivity.this, SHARE_MEDIA.QQ, null);
                    mSharedPreferences.edit().putBoolean("zt", false).putString("sj", null).commit();
                    mSharedPreferences1.edit().putBoolean("状态", false).putString("头像", null).putString("昵称", null).commit();
                    Intent intent = new Intent(SignOutActivity.this, HomeActivity.class);
                    overridePendingTransition(R.anim.zoom_in, R.anim.zoom);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}

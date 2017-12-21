package com.bdxw.impression.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

/**
 * Name: QQLoginActivity
 * Author: 王兵洋的Computer
 * Comment: //TODO
 * Date: 2017-12-21 14:04
 */
public class QQLoginActivity extends AppCompatActivity {

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Set<String> set = data.keySet();
            SharedPreferences qq = getSharedPreferences("QQ", MODE_PRIVATE);
            SharedPreferences.Editor edit = qq.edit();
            for (String string : set) {
                String str = data.get(string);
                // 设置头像
                String touxiang = data.get("iconurl");
                edit.putString("头像", touxiang);
                // 设置昵称
                String nicheng = data.get("name");
                edit.putString("昵称", nicheng);
                edit.putBoolean("状态", true);
                edit.commit();
            }
            Intent intentqq = new Intent(QQLoginActivity.this, HomeActivity.class);
            startActivity(intentqq);
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            //失败跳转到主界面
            Toast.makeText(QQLoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(QQLoginActivity.this, LoginActivity.class));
            finish();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //失败跳转到主界面
            Toast.makeText(QQLoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(QQLoginActivity.this, LoginActivity.class));
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

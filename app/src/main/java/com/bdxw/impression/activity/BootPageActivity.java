package com.bdxw.impression.activity;

import android.content.Intent;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class BootPageActivity extends BaseActivity {

    private int num = 3;
    private Timer mTimer;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_boot_page;
    }

    @Override
    protected void initView() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                num--;
                if (num == 1) {
                    startActivity(new Intent(BootPageActivity.this, HomeActivity.class));
                    overridePendingTransition(R.anim.zoom_in, R.anim.zoom);
                    mTimer.cancel();
                    finish();
                }
            }
        }, 0, 1000);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer = null;
        }
    }
}

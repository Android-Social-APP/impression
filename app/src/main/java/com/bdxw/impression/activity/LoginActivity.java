package com.bdxw.impression.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private ImageView mLoginExit;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        mLoginExit=findViewById(R.id.login_exit);
        mLoginExit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_exit:
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                overridePendingTransition(0, R.anim.zoom_out);
                break;
        }

    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,R.anim.zoom_out);
    }

}

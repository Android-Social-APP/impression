package com.bdxw.impression.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;

import java.net.URL;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Name: BaseActivity
 * Author: 王兵洋的Computer
 * Comment: //TODO  BaseActivity基类
 * Date: 2017-12-13 15:36
 */
public abstract class BaseActivity extends FragmentActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(bindLayoutView());
        mBind = ButterKnife.bind(this);
        initView();
        initData();
        //设置沉浸式
        ImmersionBar.with(this).
                fitsSystemWindows(true)  //解决了 状态栏与布局重叠的问题
                .barAlpha(0.3f)
                .init();
    }
    //绑定视图
    protected abstract int bindLayoutView();
    //初始化控件
    protected abstract void initView();
    //写数据
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑ButterKnife
        mBind.unbind();
    }
}

package com.bdxw.impression.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xxz on 17-12-13.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(fragmentLayoutId(), null);
        mBind = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
        return view;
    }

    protected abstract int fragmentLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解绑ButterKnife
        mBind.unbind();
    }
}

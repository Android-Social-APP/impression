package com.bdxw.impression.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bdxw.impression.AppConstants;
import com.bdxw.impression.R;
import com.bdxw.impression.adapter.AllTopicsAdapter;
import com.bdxw.impression.base.BaseActivity;
import com.bdxw.impression.bean.MoretopicsBean;
import com.bdxw.impression.utils.OkHttpUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

public class All_topicsActivity extends BaseActivity {

    private RecyclerView mTopicsRecyclerview;
    private List<MoretopicsBean.DataBean.TopicBean> mTopicBeans = new ArrayList<>();
    private AllTopicsAdapter mAllTopicsAdapter;
    private LinearLayoutManager mManager;
    private TextView mAllTopicBack;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int page = 1;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_all_topics;
    }

    @Override
    protected void initView() {
        mTopicsRecyclerview = findViewById(R.id.topics_recyclerview);
        mAllTopicBack = findViewById(R.id.all_topic_back);
        mSwipeRefreshLayout = findViewById(R.id.all_topic_swiperefreshlayout);
        mManager = new LinearLayoutManager(All_topicsActivity.this);
        mTopicsRecyclerview.setLayoutManager(mManager);
        //返回按钮
        mAllTopicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(All_topicsActivity.this, LoginActivity.class));
                finish();
            }
        });
        //刷新页面
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                getNetData();
                mAllTopicsAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    protected void initData() {
        getNetData();
    }

    private void getNetData() {
        //OkHttp请求 推荐页面的 网络数据
        OkHttpUtils.getAsync(AppConstants.ALLTOPIC + page + AppConstants.ALLTOPIC_T, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                //打印错误信息的日志
                Log.e("Recommend_Fragmrnt", "request:" + request);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //把数据添加到集合
                mTopicBeans.addAll(new Gson().fromJson(result, MoretopicsBean.class).getData().getTopic());
                // new适配器
                mAllTopicsAdapter = new AllTopicsAdapter(All_topicsActivity.this, mTopicBeans);
                //把集合的值 赋给RecyclerView
                mTopicsRecyclerview.setAdapter(mAllTopicsAdapter);
            }
        });
    }
}

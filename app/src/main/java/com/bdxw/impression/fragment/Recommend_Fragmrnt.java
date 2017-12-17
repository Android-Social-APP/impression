package com.bdxw.impression.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdxw.impression.AppConstants;
import com.bdxw.impression.R;
import com.bdxw.impression.adapter.RecommendAdapter;
import com.bdxw.impression.base.BaseFragment;
import com.bdxw.impression.bean.RecommendBean;
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
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Name: Recommend_Fragmrnt
 * Author: 王兵洋的Computer
 * Comment: //TODO  推荐 Fragment
 * Date: 2017-12-13 16:32
 */
public class Recommend_Fragmrnt extends BaseFragment {


    @BindView(R.id.recommend_recyclerview)
    RecyclerView mRecommendRecyclerview;
    @BindView(R.id.recommend_springview)
    SpringView mRecommendSpringview;

    private List<RecommendBean.DataBean.ArticleBean> mRecommendBeans = new ArrayList<>();
    private RecommendAdapter mRecommendAdapter;
    private int page = 1;
    private LinearLayoutManager mManager;

    @Override
    protected int fragmentLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        //设置RecyclerView的布局
        mManager = new LinearLayoutManager(getActivity());
        mRecommendRecyclerview.setLayoutManager(mManager);
    }

    @Override
    protected void initData() {
        getNetData();
        mRecommendSpringview.setType(SpringView.Type.FOLLOW);
        mRecommendSpringview.setListener(new SpringView.OnFreshListener() {
            //上拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page--;
                        getNetData();
                        mRecommendSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
            //下拉加载
            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getNetData();
                        mRecommendSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        mRecommendSpringview.setHeader(new DefaultHeader(getActivity()));
        mRecommendSpringview.setFooter(new DefaultFooter(getActivity()));
    }

    @Override
    protected void initListener() {

    }

    private void getNetData() {
        //OkHttp请求 推荐页面的 网络数据
        OkHttpUtils.getAsync(AppConstants.Recommend + page, new OkHttpUtils.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                //打印错误信息的日志
                Log.e("Recommend_Fragmrnt", "request:" + request);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //把数据添加到集合
                mRecommendBeans.addAll(new Gson().fromJson(result, RecommendBean.class).getData().getArticle());
                // new适配器
                mRecommendAdapter = new RecommendAdapter(getActivity(), mRecommendBeans);
                //把集合的值 赋给RecyclerView
                mRecommendRecyclerview.setAdapter(mRecommendAdapter);
            }
        });
    }
}

package com.bdxw.impression.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bdxw.impression.AppConstants;
import com.bdxw.impression.R;
import com.bdxw.impression.adapter.RecommendAdapter;
import com.bdxw.impression.base.BaseFragment;
import com.bdxw.impression.bean.RecommendBean;
import com.bdxw.impression.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.recommend_swiperesresh)
    SwipeRefreshLayout mRecommendSwiperesresh;

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
        //上拉刷新
        mRecommendSwiperesresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page--;
                getNetData();
                //刷新适配器
                mRecommendAdapter.notifyDataSetChanged();
                mRecommendSwiperesresh.setRefreshing(false);
            }
        });
        //下拉加载
        mRecommendRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = mManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mRecommendBeans.size() - 1) {
                    page++;
                    getNetData();
                    mRecommendAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    protected void initListener() {

    }

    private void getNetData(){
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

package com.bdxw.impression.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdxw.impression.AppConstants;
import com.bdxw.impression.R;
import com.bdxw.impression.adapter.RecommendAdapter;
import com.bdxw.impression.base.BaseFragment;
import com.bdxw.impression.bean.RecommendBean;
import com.bdxw.impression.utils.ConnectionUtil;
import com.bdxw.impression.utils.OkHttpUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

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
    @BindView(R.id.recommend_springview)
    SpringView mRecommendSpringview;
    @BindView(R.id.recommend_rcy2)
    TextView mRecommendRcy2;
    @BindView(R.id.recommend_rcy)
    ImageView mRecommendRcy;
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
        //网络判断
        if (!ConnectionUtil.isConn(getActivity())) {
            //当没有网络的时候 显示容错页  隐藏RecyclerView
            mRecommendRcy.setVisibility(View.VISIBLE);
            mRecommendRcy2.setVisibility(View.VISIBLE);
            mRecommendSpringview.setVisibility(View.GONE);
        } else {
            getNetData();
        }
        /**
         *  点击容错页的图片时 再次判断有没有网络
         *  有数据     隐藏容错页       展示数据
         *  没有数据  继续显示容错页
         */
        mRecommendRcy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //网络判断
                if (!ConnectionUtil.isConn(getActivity())) {
                    //当没有网络的时候 显示容错页  隐藏RecyclerView
                    mRecommendRcy.setVisibility(View.VISIBLE);
                    mRecommendRcy2.setVisibility(View.VISIBLE);
                    mRecommendSpringview.setVisibility(View.GONE);
                } else {
                    mRecommendRcy.setVisibility(View.GONE);
                    mRecommendRcy2.setVisibility(View.GONE);
                    mRecommendSpringview.setVisibility(View.VISIBLE);
                    getNetData();
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        /**
         *      判断网络是否开启 如果没有开启跳转到设置界面，开启的话直接跳转到搜索界面
         */
        if (!ConnectionUtil.isConn(getActivity())) {
            //当没有网络的时候 显示容错页  隐藏RecyclerView
            mRecommendRcy.setVisibility(View.VISIBLE);
            mRecommendRcy2.setVisibility(View.VISIBLE);
            mRecommendSpringview.setVisibility(View.GONE);
        } else {
            getNetData();
        }
    }

    private void getNetData() {
        //OkHttp请求 推荐页面的 网络数据
        OkHttpUtils.getAsync(AppConstants.RECOMMEND + page, new OkHttpUtils.DataCallBack() {
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

        //配置SpringView
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
}
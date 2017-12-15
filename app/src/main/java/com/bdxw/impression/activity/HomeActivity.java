package com.bdxw.impression.activity;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdxw.impression.R;
import com.bdxw.impression.adapter.FmentAdapter;
import com.bdxw.impression.base.BaseActivity;
import com.bdxw.impression.fragment.Follow_Fragment;
import com.bdxw.impression.fragment.Recommend_Fragmrnt;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private Recommend_Fragmrnt mRecommend_fragmrnt;         //推荐Fragment
    private Follow_Fragment mFollow_fragment;               //关注Fragment
    private TextView mHomeRecommend,mHomeFollow;
    private ViewPager mVp;
    private List<Fragment> mFragmentList;
    private FragmentManager mManager;
    private FmentAdapter mFmentAdapter;
    private View mLineO;
    private View mLineT;
    private ImageView mHomeUser;
    private ImageView mHomeRelease;

    @Override
    protected int bindLayoutView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mHomeRecommend = findViewById(R.id.home_recommend);
        mHomeRecommend.setOnClickListener(this);
        mHomeFollow = findViewById(R.id.home_follow);
        mHomeFollow.setOnClickListener(this);
        mLineO = findViewById(R.id.line_o);
        mLineO.setOnClickListener(this);
        mLineT = findViewById(R.id.line_t);
        mLineT.setOnClickListener(this);
        mHomeUser = findViewById(R.id.home_user);
        mHomeUser.setOnClickListener(this);
        mHomeRelease=findViewById(R.id.home_release);
        mHomeRelease.setOnClickListener(this);
        mVp = findViewById(R.id.vp);
    }

    @Override
    protected void initData() {
        mRecommend_fragmrnt = new Recommend_Fragmrnt();
        mFollow_fragment = new Follow_Fragment();
        //创建一个放Fragment的集合
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mRecommend_fragmrnt);
        mFragmentList.add(mFollow_fragment);
        //设置Fragment的管理器
        mManager = getSupportFragmentManager();
        //Fragment的适配器
        mFmentAdapter = new FmentAdapter(mManager, mFragmentList);
        mVp.setAdapter(mFmentAdapter);
        //ViewPager与TextView进行关联
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mLineO.setVisibility(View.VISIBLE);
                        mLineT.setVisibility(View.INVISIBLE);
                        mHomeRecommend.setTextColor(Color.BLACK);
                        mHomeFollow.setTextColor(Color.parseColor("#808080"));
                        break;
                    case 1:
                        mLineO.setVisibility(View.INVISIBLE);
                        mLineT.setVisibility(View.VISIBLE);
                        mHomeRecommend.setTextColor(Color.parseColor("#808080"));
                        mHomeFollow.setTextColor(Color.BLACK);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_recommend:
                mVp.setCurrentItem(0);
                mLineO.setVisibility(View.VISIBLE);
                mLineT.setVisibility(View.INVISIBLE);
                mHomeRecommend.setTextColor(Color.BLACK);
                mHomeFollow.setTextColor(Color.parseColor("#808080"));
                break;
            case R.id.home_follow:
                mVp.setCurrentItem(1);
                mLineO.setVisibility(View.INVISIBLE);
                mLineT.setVisibility(View.VISIBLE);
                mHomeFollow.setTextColor(Color.BLACK);
                mHomeRecommend.setTextColor(Color.parseColor("#808080"));
                break;
            case R.id.home_user:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom);
                break;
            case R.id.home_release:
                break;
        }
    }
}

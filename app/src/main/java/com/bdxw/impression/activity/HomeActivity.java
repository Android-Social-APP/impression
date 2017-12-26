package com.bdxw.impression.activity;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdxw.impression.R;
import com.bdxw.impression.adapter.VIewPagerAdapter;
import com.bdxw.impression.base.BaseActivity;
import com.bdxw.impression.fragment.Follow_Fragment;
import com.bdxw.impression.fragment.Recommend_Fragmrnt;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private Recommend_Fragmrnt mRecommend_fragmrnt;         //推荐Fragment
    private Follow_Fragment mFollow_fragment;               //关注Fragment
    private TextView mHomeRecommend, mHomeFollow;
    private ViewPager mVp;
    private List<Fragment> mFragmentList;
    private FragmentManager mManager;
    private VIewPagerAdapter mFmentAdapter;
    private View mLineO;
    private View mLineT;
    private ImageView mHomeUser;
    private ImageView mHomeRelease;
    private boolean mBoolean;
    private boolean isBoolean;
    private SharedPreferences mSharedPreferences1;
    private SharedPreferences mSharedPreferences;
    private String mUid;

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
        mHomeRelease = findViewById(R.id.home_release);
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
        mFmentAdapter = new VIewPagerAdapter(mManager, mFragmentList);
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

        mHomeRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 判断登陆状态..........
                 * 拿取SharedPreferences里面存取的QQ用户的uid
                 * 通过uid判断登陆状态
                 */
                mSharedPreferences = getSharedPreferences("ZT", MODE_PRIVATE);
                mBoolean = mSharedPreferences.getBoolean("zt", false);
                mSharedPreferences1 = getSharedPreferences("QQ", MODE_PRIVATE);
                isBoolean = mSharedPreferences1.getBoolean("状态", false);
                if (isBoolean == true) {
                    mUid = mSharedPreferences1.getString("uid", null);
                    Log.e("SignOutActivity", "uid - - - - - - - - - - -" + mUid);
                }
                // 使用QQ用户的uid判断登陆状态
                if (mUid == null) {
                    startActivity(new Intent(HomeActivity.this, Select_Login_Activity.class));
                } else {
                    /**
                     *  自定义dialog
                     */
                    final Dialog dialog = new Dialog(HomeActivity.this, R.style.MyDialog);
                    //找布局
                    dialog.setContentView(R.layout.layout_select_popuwindow_release);
                    //找控件
                    ImageView fmtw = dialog.findViewById(R.id.releace_fmtw);
                    ImageView tw = dialog.findViewById(R.id.releace_tw);
                    TextView qx = dialog.findViewById(R.id.releace_qx);
                    dialog.setCanceledOnTouchOutside(true);
                    //获得当前窗体
                    Window window = dialog.getWindow();
                    //重新设置
                    WindowManager.LayoutParams lp = window.getAttributes();
                    window.setGravity(Gravity.BOTTOM);
                    WindowManager m = getWindowManager();
                    Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                    WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
                    p.height = (int) (d.getHeight() * 0.4); // 设置屏幕的高度
                    p.width = (d.getWidth()); // 设置屏幕的宽度
                    window.setAttributes(p);
                    dialog.onWindowAttributesChanged(lp);
                    //(当Window的Attributes改变时系统会调用此函数)
                    window.setAttributes(lp);
                    //封面图文
                    fmtw.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(HomeActivity.this, "封面图文", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //图文
                    tw.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(HomeActivity.this, "图文", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //取消发布
                    qx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //推荐
            case R.id.home_recommend:
                mVp.setCurrentItem(0);
                mLineO.setVisibility(View.VISIBLE);
                mLineT.setVisibility(View.INVISIBLE);
                mHomeRecommend.setTextColor(Color.BLACK);
                mHomeFollow.setTextColor(Color.parseColor("#808080"));
                break;
            //关注
            case R.id.home_follow:
                mVp.setCurrentItem(1);
                mLineO.setVisibility(View.INVISIBLE);
                mLineT.setVisibility(View.VISIBLE);
                mHomeFollow.setTextColor(Color.BLACK);
                mHomeRecommend.setTextColor(Color.parseColor("#808080"));
                break;
            //登陆
            case R.id.home_user:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom);
                break;
        }
    }
}

package com.bdxw.impression.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
/**
 * Name: TabLayout_Adapter
 * Author: 王兵洋的Computer
 * Comment: //TODO  ViewPager 的适配器
 * Date: 2017-12-13 16:46
 */
public class VIewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public VIewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }
}
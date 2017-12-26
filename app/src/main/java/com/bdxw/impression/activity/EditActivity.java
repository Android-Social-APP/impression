package com.bdxw.impression.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxz on 17-12-19.
 */

public class EditActivity extends BaseActivity {

    private String mTitle;
    private String mImg;
    private String mSummary;
    private ImageView mImageView;
    private TextView mTextViewTitle;
    private TextView mTextViewDescribe;
    private ImageView mImageViewOne;
    private ImageView mImageViewTwo;
    private ImageView mImageViewThree;
    private RadioButton mRadioButton;

    //标记
    private boolean flag = false;
    private SharedPreferences.Editor mEdit;
    private SharedPreferences mStatus;
    private int mPosition;
    private HashMap mHashMap = new HashMap<Integer, Boolean>();

    @Override
    protected void initData() {
        //获得bundle对象
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mImg = intent.getStringExtra("img");
        mSummary = intent.getStringExtra("summary");
        //获得pos
        mPosition = intent.getIntExtra("position", -1);
        //赋值
        Glide.with(this).load(mImg).into(mImageView);
        mTextViewTitle.setText(mTitle);
        mTextViewDescribe.setText(mSummary);
        Glide.with(this).load(mImg).into(mImageViewOne);
        Glide.with(this).load(mImg).into(mImageViewTwo);
        Glide.with(this).load(mImg).into(mImageViewThree);

        mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    new AlertDialog.Builder(EditActivity.this)
                            .setTitle("您正在取消")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean status = mStatus.getBoolean("follow", true);
                                    if (status) {
                                        flag = false;
                                        mRadioButton.setChecked(!status);
                                        mEdit.clear();
                                        mEdit.commit();
                                    } else {
                                        Toast.makeText(EditActivity.this, "取消失败....", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //关闭对话框
                                    dialog.dismiss();
                                }
                            }).show();
                } else {
                    flag = true;
                    mRadioButton.setChecked(true);
                    mEdit.putBoolean("follow", true);
                    mEdit.commit();
                }
            }
        });
    }

    @Override
    protected int bindLayoutView() {
        return R.layout.edit_activity_layout;
    }

    @Override
    protected void initView() {
        mImageView = findViewById(R.id.img_top_edit);
        mTextViewTitle = findViewById(R.id.tv_title_edit);
        mTextViewDescribe = findViewById(R.id.tv_describe_edit);
        mImageViewOne = findViewById(R.id.img_one_des_edit);
        mImageViewTwo = findViewById(R.id.img_two_des_edit);
        mImageViewThree = findViewById(R.id.img_three_des_edit);
        mRadioButton = findViewById(R.id.radio_like_edit);
        //初始化一个sp
        mStatus = getSharedPreferences("status", MODE_PRIVATE);
        mEdit = mStatus.edit();
        boolean status = mStatus.getBoolean("follow", false);
        if (status) {
            mRadioButton.setChecked(status);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //退出后再进入后,读去sp
        boolean status = mStatus.getBoolean("status", false);
        Log.d("out", status + "");
        if (status) {
            mRadioButton.setChecked(status);
        }
    }
}



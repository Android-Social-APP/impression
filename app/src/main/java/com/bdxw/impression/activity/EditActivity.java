package com.bdxw.impression.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.bdxw.impression.R;
import com.bdxw.impression.base.BaseActivity;
import com.bumptech.glide.Glide;
import java.util.HashMap;

/**
 * Created by xxz on 17-12-19.
 */

public class EditActivity extends BaseActivity {
    private String mTitle;
    private String mImg;
    private String mSummary;
    ImageView mImageView;

    TextView mTextViewTitle;

    TextView mTextViewDescribe;

    ImageView mImageViewOne;

    ImageView mImageViewTwo;

    ImageView mImageViewThree;

    RadioButton mRadioButton;
    //标记
    private boolean flag = false;

    private int mPosition;
    private HashMap mHashMap=new HashMap<Integer,Boolean>();
    private RadioButton mRadioBack;

    @Override
    protected void initData() {
        //获得bundle对象
        final Intent intent = getIntent();
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
        mHashMap.put(mPosition,flag);
        mRadioBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(EditActivity.this, HomeActivity.class);
                int i = editBackPosition();
                boolean b = editBackStack();
                homeIntent.putExtra("position",i);
                homeIntent.putExtra("stack",b);
                startActivity(homeIntent);
            }
        });
        mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 flag = (Boolean) mHashMap.get(mPosition);
                if (flag) {
                    new AlertDialog.Builder(EditActivity.this)
                            .setTitle("您正在取消")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                  if(flag){
                                      flag=false;
                                      mHashMap.put(mPosition,flag);
                                      mRadioButton.setChecked((Boolean) mHashMap.get(mPosition));
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
                    mHashMap.put(mPosition,flag);
                    mRadioButton.setChecked((Boolean) mHashMap.get(mPosition));
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
        mRadioBack = findViewById(R.id.radio_back_edit);
    }
    //返回下标的方法
    public int editBackPosition(){
        return mPosition;
    }

    //返回状态
    public boolean editBackStack(){
        return flag;
    }
}



package com.bdxw.impression.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdxw.impression.R;
import com.bdxw.impression.bean.RecommendBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Name: RecommendAdapter
 * Author: 王兵洋的Computer
 * Comment: //TODO  推荐页面的适配器
 * Date: 2017-12-14 20:58
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RecommendBean.DataBean.ArticleBean> mRecommendBeans;
    //定义两种常量  表示三种条目类型
    private static final int TYPE_HAS_PIC = 0;
    private static final int TYPE_NO_PIC = 1;


    public RecommendAdapter(Context context, List<RecommendBean.DataBean.ArticleBean> recommendBeans) {
        mContext = context;
        mRecommendBeans = recommendBeans;
    }

    //多条目需要重写的方法
    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_HAS_PIC;
        } else {
            return TYPE_NO_PIC;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HAS_PIC) {
            View v = View.inflate(mContext, R.layout.layout_recommendadapter, null);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        } else {
            //找到布局文件
            View vv = View.inflate(mContext, R.layout.layout_recommendadaptertwo, null);
            ViewHolderTwo viewHolderTwo = new ViewHolderTwo(vv);
            return viewHolderTwo;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.recommend_adapter_tv.setText(mRecommendBeans.get(position).getTitle());
            Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(viewHolder.recommend_adapter_img);
            viewHolder.recommend_adapter_admire.setText(mRecommendBeans.get(position).getRead_count());
            viewHolder.recommend_adapter_comment.setText(mRecommendBeans.get(position).getComment_count());
            viewHolder.recommend_adapter_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.recommend_adapter_follow.setVisibility(View.GONE);
                    viewHolder.recommend_adapter_unfollow.setVisibility(View.VISIBLE);
                }
            });
            viewHolder.recommend_adapter_unfollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                    alertDialog.show();
                    Window window = alertDialog.getWindow();
                    window.setContentView(R.layout.reco_dialog_item);
                    TextView dialog_tv = (TextView) window.findViewById(R.id.reco_dialog_tv);
                    dialog_tv.getText();
                    Button dialog_btn = (Button) window.findViewById(R.id.reco_dialog_sure);
                    dialog_btn.getText();
                    dialog_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewHolder.recommend_adapter_follow.setVisibility(View.VISIBLE);
                            viewHolder.recommend_adapter_unfollow.setVisibility(View.GONE);
                            alertDialog.dismiss();
                        }
                    });
                    Button dialog_unbtn = (Button) window.findViewById(R.id.reco_dialog_unsure);
                    dialog_unbtn.getText();
                    dialog_unbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }
            });
        } else if (holder instanceof ViewHolderTwo) {
            final ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.recommend_adapter_tv_two.setText(mRecommendBeans.get(position).getSummary());
            Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(viewHolderTwo.recommend_adapter_img_two11);
            Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(viewHolderTwo.recommend_adapter_img_two22);
            Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(viewHolderTwo.recommend_adapter_img_two33);
            viewHolderTwo.recommend_adapter_admire_two.setText(mRecommendBeans.get(position).getRead_count());
            viewHolderTwo.recommend_adapter_comment_two.setText(mRecommendBeans.get(position).getComment_count());
            viewHolderTwo.recommend_adapter_follow_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.recommend_adapter_follow_two.setVisibility(View.GONE);
                    viewHolderTwo.recommend_adapter_unfollow_two.setVisibility(View.VISIBLE);
                }
            });
            viewHolderTwo.recommend_adapter_unfollow_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                    alertDialog.show();
                    Window window = alertDialog.getWindow();
                    window.setContentView(R.layout.reco_dialog_item);
                    TextView dialog_tv = (TextView) window.findViewById(R.id.reco_dialog_tv);
                    dialog_tv.getText();
                    Button dialog_btn = (Button) window.findViewById(R.id.reco_dialog_sure);
                    dialog_btn.getText();
                    dialog_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewHolderTwo.recommend_adapter_follow_two.setVisibility(View.VISIBLE);
                            viewHolderTwo.recommend_adapter_unfollow_two.setVisibility(View.GONE);
                            alertDialog.dismiss();
                        }
                    });
                    Button dialog_unbtn = (Button) window.findViewById(R.id.reco_dialog_unsure);
                    dialog_unbtn.getText();
                    dialog_unbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mRecommendBeans != null ? mRecommendBeans.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView
                recommend_adapter_tv,
                recommend_adapter_comment,
                recommend_adapter_admire,
                recommend_adapter_data,
                recommend_adapter_follow,
                recommend_adapter_unfollow;

        ImageView recommend_adapter_img;

        public ViewHolder(View itemView) {
            super(itemView);
            recommend_adapter_tv = itemView.findViewById(R.id.recommend_adapter_tv);
            recommend_adapter_img = itemView.findViewById(R.id.recommend_adapter_img);
            recommend_adapter_comment = itemView.findViewById(R.id.recommend_adapter_comment);
            recommend_adapter_admire = itemView.findViewById(R.id.recommend_adapter_admire);
            recommend_adapter_data = itemView.findViewById(R.id.recommend_adapter_data);
            recommend_adapter_follow = itemView.findViewById(R.id.recommend_adapter_follow);
            recommend_adapter_unfollow = itemView.findViewById(R.id.recommend_adapter_unfollow);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView recommend_adapter_tv_two,
                recommend_adapter_comment_two,
                recommend_adapter_admire_two,
                recommend_adapter_data_two,
                recommend_adapter_follow_two,
                recommend_adapter_unfollow_two;

        ImageView recommend_adapter_img_two11, recommend_adapter_img_two22, recommend_adapter_img_two33;

        public ViewHolderTwo(View itemView) {
            super(itemView);

            recommend_adapter_tv_two = itemView.findViewById(R.id.recommend_adapter_tv_two);
            recommend_adapter_img_two11 = itemView.findViewById(R.id.recommend_adapter_img_two11);
            recommend_adapter_img_two22 = itemView.findViewById(R.id.recommend_adapter_img_two22);
            recommend_adapter_img_two33 = itemView.findViewById(R.id.recommend_adapter_img_two33);
            recommend_adapter_comment_two = itemView.findViewById(R.id.recommend_adapter_comment_two);
            recommend_adapter_admire_two = itemView.findViewById(R.id.recommend_adapter_admire_two);
            recommend_adapter_data_two = itemView.findViewById(R.id.recommend_adapter_data_two);
            recommend_adapter_follow_two = itemView.findViewById(R.id.recommend_adapter_follow_two);
            recommend_adapter_unfollow_two = itemView.findViewById(R.id.recommend_adapter_unfollow_two);
        }
    }
}
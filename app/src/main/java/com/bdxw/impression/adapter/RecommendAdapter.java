package com.bdxw.impression.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.recommend_adapter_tv.setText(mRecommendBeans.get(position).getTitle());
            Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(viewHolder.recommend_adapter_img);
            viewHolder.recommend_adapter_admire.setText(mRecommendBeans.get(position).getRead_count());
            viewHolder.recommend_adapter_comment.setText(mRecommendBeans.get(position).getComment_count());
            viewHolder.recommend_adapter_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else if (holder instanceof ViewHolderTwo) {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.recommend_adapter_tv_two.setText(mRecommendBeans.get(position).getSummary());
            Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(viewHolderTwo.recommend_adapter_img_two);
            viewHolderTwo.recommend_adapter_admire_two.setText(mRecommendBeans.get(position).getRead_count());
            viewHolderTwo.recommend_adapter_comment_two.setText(mRecommendBeans.get(position).getComment_count());
            viewHolderTwo.recommend_adapter_follow_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                recommend_adapter_follow;

        ImageView recommend_adapter_img;

        public ViewHolder(View itemView) {
            super(itemView);
            recommend_adapter_tv = itemView.findViewById(R.id.recommend_adapter_tv);
            recommend_adapter_img = itemView.findViewById(R.id.recommend_adapter_img);
            recommend_adapter_comment = itemView.findViewById(R.id.recommend_adapter_comment);
            recommend_adapter_admire = itemView.findViewById(R.id.recommend_adapter_admire);
            recommend_adapter_data = itemView.findViewById(R.id.recommend_adapter_data);
            recommend_adapter_follow = itemView.findViewById(R.id.recommend_adapter_follow);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView recommend_adapter_tv_two,
                recommend_adapter_comment_two,
                recommend_adapter_admire_two,
                recommend_adapter_data_two,
                recommend_adapter_follow_two;

        ImageView recommend_adapter_img_two;

        public ViewHolderTwo(View itemView) {
            super(itemView);

            recommend_adapter_tv_two = itemView.findViewById(R.id.recommend_adapter_tv_two);
            recommend_adapter_img_two = itemView.findViewById(R.id.recommend_adapter_img_two);
            recommend_adapter_comment_two = itemView.findViewById(R.id.recommend_adapter_comment_two);
            recommend_adapter_admire_two = itemView.findViewById(R.id.recommend_adapter_admire_two);
            recommend_adapter_data_two = itemView.findViewById(R.id.recommend_adapter_data_two);
            recommend_adapter_follow_two = itemView.findViewById(R.id.recommend_adapter_follow_two);
        }
    }
}
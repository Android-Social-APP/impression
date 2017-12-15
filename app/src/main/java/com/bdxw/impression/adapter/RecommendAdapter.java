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
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context mContext;
    private List<RecommendBean.DataBean.ArticleBean> mRecommendBeans;

    public RecommendAdapter(Context context, List<RecommendBean.DataBean.ArticleBean> recommendBeans) {
        mContext = context;
        mRecommendBeans = recommendBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_recommendadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.recommend_adapter_tv.setText(mRecommendBeans.get(position).getSummary());
        Glide.with(mContext).load(mRecommendBeans.get(position).getFace()).into(holder.recommend_adapter_img);

        holder.recommend_adapter_admire.setText(mRecommendBeans.get(position).getRead_count());
        holder.recommend_adapter_comment.setText(mRecommendBeans.get(position).getComment_count());
//        holder.recommend_adapter_data.setText(mRecommendBeans.get(position).getCreate_time());
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
            recommend_adapter_comment=itemView.findViewById(R.id.recommend_adapter_comment);
            recommend_adapter_admire=itemView.findViewById(R.id.recommend_adapter_admire);
            recommend_adapter_data=itemView.findViewById(R.id.recommend_adapter_data);
            recommend_adapter_follow=itemView.findViewById(R.id.recommend_adapter_follow);
        }
    }
}
package com.bdxw.impression.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdxw.impression.R;
import com.bdxw.impression.bean.MoretopicsBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Name: AllTopicsAdapter
 * Author: 王兵洋的Computer
 * Comment: //TODO   全部话题页面的适配器
 * Date: 2017-12-20 11:20
 */
public class AllTopicsAdapter extends RecyclerView.Adapter<AllTopicsAdapter.ViewHolder> {

    private Context mContext;
    private List<MoretopicsBean.DataBean.TopicBean> mTopicBeans;

    public AllTopicsAdapter(Context context, List<MoretopicsBean.DataBean.TopicBean> topicBeans) {
        mContext = context;
        mTopicBeans = topicBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_alltopicsadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.all_topic_adapter_textview.setText(mTopicBeans.get(position).getTitle());
        Glide.with(mContext).load(mTopicBeans.get(position).getLogo()).into(holder.all_topic_adapter_image);
    }

    @Override
    public int getItemCount() {
        return mTopicBeans != null ? mTopicBeans.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView all_topic_adapter_textview;
        ImageView all_topic_adapter_image;


        public ViewHolder(View itemView) {
            super(itemView);
            all_topic_adapter_image = itemView.findViewById(R.id.all_topic_adapter_image);
            all_topic_adapter_textview = itemView.findViewById(R.id.all_topic_adapter_textview);
        }
    }
}

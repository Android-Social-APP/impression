package com.bdxw.impression.bean;

import java.util.List;

/**
 * Name: MoretopicsBean
 * Author: 王兵洋的Computer
 * Comment: //TODO
 * Date: 2017-12-19 15:42
 */
public class MoretopicsBean {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TopicBean> topic;

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public static class TopicBean {

            private String id;
            private String title;
            private String logo;
            private String comment_count;
            private String article_count;
            private String is_show;
            private String is_index;
            private String weight;
            private String is_charge;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getArticle_count() {
                return article_count;
            }

            public void setArticle_count(String article_count) {
                this.article_count = article_count;
            }

            public String getIs_show() {
                return is_show;
            }

            public void setIs_show(String is_show) {
                this.is_show = is_show;
            }

            public String getIs_index() {
                return is_index;
            }

            public void setIs_index(String is_index) {
                this.is_index = is_index;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getIs_charge() {
                return is_charge;
            }

            public void setIs_charge(String is_charge) {
                this.is_charge = is_charge;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}

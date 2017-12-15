package com.bdxw.impression.bean;

import java.util.List;

/**
 * Name: RecommendBean
 * Author: 王兵洋的Computer
 * Comment: //TODO
 * Date: 2017-12-14 20:53
 */
public class RecommendBean {


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

        private String total_count;
        private List<ArticleBean> article;

        public String getTotal_count() {
            return total_count;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {

            private String weight;
            private String type;
            private String title;
            private String aid;
            private String cate_id;
            private String summary;
            private String face;
            private String upvote;
            private String comment_count;
            private String read_count;
            private String create_time;
            private String update_time;
            private String uid;
            private String status;
            private int img_count;
            private IsFocusBean is_focus;
            private boolean is_upvote;
            private List<?> img_url;
            private List<?> articles;

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getUpvote() {
                return upvote;
            }

            public void setUpvote(String upvote) {
                this.upvote = upvote;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getRead_count() {
                return read_count;
            }

            public void setRead_count(String read_count) {
                this.read_count = read_count;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getImg_count() {
                return img_count;
            }

            public void setImg_count(int img_count) {
                this.img_count = img_count;
            }

            public IsFocusBean getIs_focus() {
                return is_focus;
            }

            public void setIs_focus(IsFocusBean is_focus) {
                this.is_focus = is_focus;
            }

            public boolean isIs_upvote() {
                return is_upvote;
            }

            public void setIs_upvote(boolean is_upvote) {
                this.is_upvote = is_upvote;
            }

            public List<?> getImg_url() {
                return img_url;
            }

            public void setImg_url(List<?> img_url) {
                this.img_url = img_url;
            }

            public List<?> getArticles() {
                return articles;
            }

            public void setArticles(List<?> articles) {
                this.articles = articles;
            }

            public static class IsFocusBean {
                /**
                 * is_focus : false
                 */

                private boolean is_focus;

                public boolean isIs_focus() {
                    return is_focus;
                }

                public void setIs_focus(boolean is_focus) {
                    this.is_focus = is_focus;
                }
            }
        }
    }
}

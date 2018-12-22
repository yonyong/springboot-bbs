package cn.connext.yonyong.yonyongbbs.entity;


import java.util.Date;

/**
 *  文章评论，对应数据库里的reply表
 */
public class Reply {    //将查询缓存到redis里需要将用到的pojo序列化，否则会报错
    private int id;
    private int article_id;
    private String replyer;
    private Date date;
    private String reply;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}

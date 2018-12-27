package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.Article;

import java.util.Date;
import java.util.List;

public interface ArticleService {
    List<Article> selectAllArticle(int page);

    /**
     * 不分页查询所有文章
     * @return
     */
    List<Article> queryAllArticle();
    /**
     * 根据文章id查询文章
     * @param id
     */
    Article selectArticleById(int id);

    /**
     * 不分页查询用户所属的文章
     * @param id
     * @return
     */
    List<Article> selectAllArticleByUserId(int id);

    /**
     * 分页查询用户所属的文章
     * @param id
     * @param page
     * @return
     */
    List<Article> selectArticleByUserId(int id,int page);
    /**
     * 添加文章
     * @param userid
     * @param title
     * @param author
     * @param date
     * @param content
     */
    void addArticle(int userid, String title,String author,Date date,String content);

    /**
     * 编辑文章
     * @param title
     * @param date
     * @param content
     */
    void updateArticle(String title,Date date,String content,int id);

    /**
     * 删除文章
     * @param id
     */
    void deleteArticle(int id);
}

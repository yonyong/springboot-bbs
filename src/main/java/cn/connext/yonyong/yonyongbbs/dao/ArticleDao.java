package cn.connext.yonyong.yonyongbbs.dao;

import cn.connext.yonyong.yonyongbbs.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleDao {

    /**
     * 不分页查询所有文章
     * @return
     */
    List<Article> queryAllArticle();
    /**
     * 分页查询所有文章
     */
    List<Article> selectAllArticle(int page);

    /**
     * 不分页查询用户所属的文章
     * @param id
     * @return
     */
    List<Article> selectAllArticleByUserId(@Param("id") int id);

    /**
     * 分页查询用户所属的文章
     * @param id
     * @param page
     * @return
     */
    List<Article> selectArticleByUserId(@Param("id") int id,@Param("page") int page);

    /**
     * 根据文章id查询文章
     * @param id
     */
    Article selectArticleById(@Param("id") int id);
    /**
     * 添加文章
     * @param userid
     * @param title
     * @param author
     * @param date
     * @param content
     */
    void addArticle(@Param("userid") int userid,@Param("title") String title,@Param("author") String author,@Param("date") Date date,@Param("content") String content);

    /**
     * 编辑文章
     * @param title
     * @param date
     * @param content
     */
    void updateArticle(@Param("title")String title,@Param("date")Date date,@Param("content")String content,@Param("id") int id);

    /**
     * 删除文章
     * @param id
     */
    void deleteArticle(@Param("id") int id);
}

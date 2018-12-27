package cn.connext.yonyong.yonyongbbs.service.impl;

import cn.connext.yonyong.yonyongbbs.dao.ArticleDao;
import cn.connext.yonyong.yonyongbbs.entity.Article;
import cn.connext.yonyong.yonyongbbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
//    @Cacheable(value="selectAllArticle-key")
    public List<Article> selectAllArticle(int page) {
        return articleDao.selectAllArticle(page);
    }

    @Override
    public List<Article> queryAllArticle() {
        return articleDao.queryAllArticle();
    }

    @Override
//    @Cacheable(value="selectArticleById-key")
    public Article selectArticleById(int id) {
        return articleDao.selectArticleById(id);
    }

    @Override
    public List<Article> selectAllArticleByUserId(int id) {
        return articleDao.selectAllArticleByUserId(id);
    }

    @Override
    public List<Article> selectArticleByUserId(int id, int page) {
        return articleDao.selectArticleByUserId(id,page);
    }

    @Override
    public void addArticle(int userid, String title, String author, Date date, String content) {
        articleDao.addArticle(userid,title,author,date,content);
    }

    @Override
    public void updateArticle(String title, Date date, String content,int id) {
        articleDao.updateArticle(title,date,content,id);
    }

    @Override
    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }
}

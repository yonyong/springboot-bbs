package cn.connext.yonyong.yonyongbbs.controller;

import cn.connext.yonyong.yonyongbbs.entity.Article;
import cn.connext.yonyong.yonyongbbs.dto.ShowArticle;
import cn.connext.yonyong.yonyongbbs.service.ArticleService;
import cn.connext.yonyong.yonyongbbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 此控制器主要用于简单的页面跳转
 */
@Controller
public class JumpController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ReplyService replyService;

    /**
     * 系统首页
     * @return
     */
    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }

    /**
     * 前往登录页
     * @return
     */
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    /**
     * 前往注册页面
     * @return
     */
    @RequestMapping("/toregister")
    public String toregister(){
        return "register";
    }

    @RequestMapping("/toarticle")
    public String toarticle(Model model){
        List<Article> articleList=articleService.selectAllArticle(0);
        List<ShowArticle> list=new ArrayList<ShowArticle>();
        for(Article t:articleList){
            ShowArticle showArticle=new ShowArticle();
            showArticle.setArticle_id(t.getId());
            showArticle.setArticle_author(t.getAuthor());
            showArticle.setArticle_date(t.getDate());
            showArticle.setArticle_title(t.getTitle());
            showArticle.setCount(replyService.selectAllReplyByArticleID(t.getId()).size()==0?0:replyService.selectAllReplyByArticleID(t.getId()).size());
            showArticle.setReply_date(replyService.selectAllReplyByArticleID(t.getId()).size()==0?null:replyService.selectAllReplyByArticleID(t.getId()).get(0).getDate());
            list.add(showArticle);
        }
        model.addAttribute("list0",list.get(0));
        model.addAttribute("list1",list.get(1));
        model.addAttribute("list2",list.get(2));
        model.addAttribute("page",1);
        return "article";
    }

    @RequestMapping("/test")
    public String test(){
        return "articleDetail";
    }
}

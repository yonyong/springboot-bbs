package cn.connext.yonyong.yonyongbbs.controller;

import cn.connext.yonyong.yonyongbbs.entity.Article;
import cn.connext.yonyong.yonyongbbs.entity.Reply;
import cn.connext.yonyong.yonyongbbs.entity.User;
import cn.connext.yonyong.yonyongbbs.service.ArticleService;
import cn.connext.yonyong.yonyongbbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ReplyService replyService;

    /**
     * 跳转到消息详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/article")
    public String article(@RequestParam("id")int id, Model model){
        Article article=articleService.selectArticleById(id);
        List<Reply> replies=replyService.selectAllReplyByArticleID(id);
        model.addAttribute("article",article);
        if(replies.size()>0&&replies.get(0)!=null)
            model.addAttribute("reply0",replies.get(0));
        if(replies.size()>1&&replies.get(1)!=null)
            model.addAttribute("reply1",replies.get(1));
        if(replies.size()>2&&replies.get(2)!=null)
            model.addAttribute("reply2",replies.get(2));
        model.addAttribute("replypage",1);
        model.addAttribute("articleid",id);
        return "articleDetail";
    }

    @RequestMapping("/addArticle")
    @ResponseBody
    public String addArticle(@RequestParam("addArticleTitle")String addArticleTitle,
                             @RequestParam("addArticleContent")String addArticleContent,
                                HttpSession session){

        Date date=new Date();
        User user= (User) session.getAttribute("rs_user");
        if (user==null){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"尚未登陆！\"}";
            return jsonStr;
        }
        int id=user.getId();
        String author=user.getNickname();
        articleService.addArticle(id,addArticleTitle,author,date,addArticleContent);
        String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"发布成功！\"}";
        return jsonStr;
    }

    @RequestMapping("/editArticle")
    @ResponseBody
    public String editArticle(@RequestParam("editArticleTitle")String editArticleTitle,
                              @RequestParam("editArticleContent")String editArticleContent,
                              @RequestParam("articleId")int articleId,
                              HttpSession session){
        User user= (User) session.getAttribute("rs_user");
        if (user==null){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"尚未登陆！\"}";
            return jsonStr;
        }
        Date date=new Date();
        articleService.updateArticle(editArticleTitle,date,editArticleContent,articleId);
        String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"更新成功！\"}";
        return jsonStr;
    }

    @RequestMapping("/deleteArticle")
    @ResponseBody
    public String editArticle(@RequestParam("articleId")int articleId,
                              HttpSession session){
        User user= (User) session.getAttribute("rs_user");
        if (user==null){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"尚未登陆！\"}";
            return jsonStr;
        }
        articleService.deleteArticle(articleId);
        String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"删除成功！\"}";
        return jsonStr;
    }

    @RequestMapping("/addReply")
    @ResponseBody
    public String addReply(@RequestParam("articleId")int articleId,
                           @RequestParam("reply_content")String reply_content,
                           HttpSession session){
        Date date=new Date();
        User user= (User) session.getAttribute("rs_user");
        if (user==null){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"尚未登陆！\"}";
            return jsonStr;
        }
        String replyer=user.getNickname();
        replyService.addReply(articleId,replyer,date,reply_content);
        String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"评论成功！\"}";
        return jsonStr;
    }
}

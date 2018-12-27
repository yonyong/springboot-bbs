package cn.connext.yonyong.yonyongbbs.controller;

import cn.connext.yonyong.yonyongbbs.dto.ShowArticle;
import cn.connext.yonyong.yonyongbbs.entity.*;
import cn.connext.yonyong.yonyongbbs.service.*;
import cn.connext.yonyong.yonyongbbs.util.ConvertRole;
import cn.connext.yonyong.yonyongbbs.util.sort.ArticleSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *  此控制器主要用于处理分页
 */
@Controller
public class PageController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ReplyService replyService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    Role_perService role_perService;

    @Autowired
    User_roleService user_roleService;

    @RequestMapping("/page")
    public String page(@RequestParam("page")int page, Model model){
        //如果传递过来的页数<1或者传过来的页数能展示的信息超过了数据库里的条数，即会空出一页或者更多，则将页数置为1
        int tpage=page;
        int rs=articleService.queryAllArticle().size();
        if(page<1||(page-1)*3>=rs)
            tpage=1;

        List<Article> articleList=articleService.queryAllArticle();
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
        list= ArticleSort.articleSort(list);
    System.out.println("page的值为："+tpage);
        if (list.size()>((tpage-1)*3)&&list.get((tpage-1)*3)!=null)
        model.addAttribute("list0",list.get((tpage-1)*3));
        if (list.size()>((tpage-1)*3+1)&&list.get((tpage-1)*3+1)!=null)
        model.addAttribute("list1",list.get((tpage-1)*3+1));
        if (list.size()>((tpage-1)*3+2)&&list.get((tpage-1)*3+2)!=null)
        model.addAttribute("list2",list.get((tpage-1)*3+2));
        model.addAttribute("page",tpage);
        return "article";
    }

    @RequestMapping("/mypage")
    public String mypage(@RequestParam("mypage")int mypage, Model model, HttpSession session){
        //如果传递过来的页数<1或者传过来的页数能展示的信息超过了数据库里的条数，即会空出一页或者更多，则将页数置为1
       int tmypage=mypage;
        User user= (User) session.getAttribute("rs_user");
        int id=user.getId();
        int rs=articleService.selectAllArticleByUserId(id).size();
        if(mypage<1||(mypage-1)*3>=rs)
            tmypage=1;

        List<Article> articleList=articleService.selectAllArticleByUserId(id);
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
        list= ArticleSort.articleSort(list);
        System.out.println("mypage的值为："+tmypage);
        if (list.size()>((tmypage-1)*3)&&list.get((tmypage-1)*3)!=null)
            model.addAttribute("list0",list.get((tmypage-1)*3));
        if (list.size()>((tmypage-1)*3+1)&&list.get((tmypage-1)*3+1)!=null)
            model.addAttribute("list1",list.get((tmypage-1)*3+1));
        if (list.size()>((tmypage-1)*3+2)&&list.get((tmypage-1)*3+2)!=null)
            model.addAttribute("list2",list.get((tmypage-1)*3+2));
        model.addAttribute("mypage",tmypage);
        return "myarticle";
    }

    @RequestMapping("/replypage")
    public String replypage(@RequestParam("replypage")int replypage, @RequestParam("articleid")int articleid,Model model){
        int rs=replyService.selectAllReplyByArticleID(articleid).size();
        //如果传递过来的页数<1或者传过来的页数能展示的信息超过了数据库里的条数，即会空出一页或者更多，则将页数置为1
        if (replypage<1||(replypage-1)*3>=rs)
            replypage=1;
        Article article=articleService.selectArticleById(articleid);
        model.addAttribute("article",article);
        List<Reply> replies=replyService.selectAllReply(articleid,(replypage-1)*3);
        if(replies.size()>0&&replies.get(0)!=null)
            model.addAttribute("reply0",replies.get(0));
        if(replies.size()>1&&replies.get(1)!=null)
            model.addAttribute("reply1",replies.get(1));
        if(replies.size()>2&&replies.get(2)!=null)
            model.addAttribute("reply2",replies.get(2));
        model.addAttribute("replypage",replypage);
        model.addAttribute("articleid",articleid);
        return "articleDetail";
    }

    @RequestMapping("/userpage")
    public String userpage(@RequestParam("userpage")int userpage, Model model, HttpSession session){
        //如果传递过来的页数<1或者传过来的页数能展示的信息超过了数据库里的条数，即会空出一页或者更多，则将页数置为1
        User user= (User) session.getAttribute("rs_user");
        int id=user.getId();
        int rs=userService.selectAllUser().size();
        if(userpage<1||(userpage-1)*3>=rs)
            userpage=1;

        //用于角色列表展示
        List<Role> allRole=roleService.selectAllRoleByPage(0);
        //用于用户列表展示
        List<User> allUser=userService.selectAllUserByPage((userpage-1)*3);

        //用于用户列表编辑角色所有角色列表的展示
        List<Role> allUserRole=roleService.selectAllRole();
        //用于展示当前登录用户的角色
        User_role user_role=user_roleService.queryByUserId(id);
        Role myRole=roleService.selectRoleById(user_role.getRoleid());
        //用于展示当前登录用户的权限
        List myPermission= new ConvertRole().toList(role_perService.selectPermission(myRole.getId()));
        model.addAttribute("myRole",myRole);
        model.addAttribute("allUserRole",allUserRole);
        model.addAttribute("myPermission",myPermission);
        if (allUser.size()>0&&allUser.get(0)!=null){
            User_role user_role1=user_roleService.queryByUserId(allUser.get(0).getId());
            Role userRole1=roleService.selectRoleById(user_role1.getRoleid());
            model.addAttribute("userRole0", userRole1);
            model.addAttribute("user0",allUser.get(0));
        }
        if (allUser.size()>1&&allUser.get(1)!=null){
            User_role user_role1=user_roleService.queryByUserId(allUser.get(1).getId());
            Role userRole1=roleService.selectRoleById(user_role1.getRoleid());
            model.addAttribute("userRole1", userRole1);
            model.addAttribute("user1",allUser.get(1));
        }
        if (allUser.size()>2&&allUser.get(2)!=null){
            User_role user_role1=user_roleService.queryByUserId(allUser.get(2).getId());
            Role userRole1=roleService.selectRoleById(user_role1.getRoleid());
            model.addAttribute("userRole2", userRole1);
            model.addAttribute("user2",allUser.get(2));
        }

        if (allRole.size()>0&&allRole.get(0)!=null)
            model.addAttribute("role0",allRole.get(0));
        if (allRole.size()>1&&allRole.get(1)!=null)
            model.addAttribute("role1",allRole.get(1));
        if (allRole.size()>2&&allRole.get(2)!=null)
            model.addAttribute("role2",allRole.get(2));

        System.out.println(allUser.size());
    System.out.println(userpage);

        model.addAttribute("userpage",userpage);
        model.addAttribute("rolepage",1);
        return "permission";
    }

    @RequestMapping("/rolepage")
    public String rolepage(@RequestParam("rolepage")int rolepage, Model model, HttpSession session){
        //如果传递过来的页数<1或者传过来的页数能展示的信息超过了数据库里的条数，即会空出一页或者更多，则将页数置为1
        User user= (User) session.getAttribute("rs_user");
        int id=user.getId();
        int rs=roleService.selectAllRole().size();
        if(rolepage<1||(rolepage-1)*3>=rs)
            rolepage=1;

        List<Role> allRole=roleService.selectAllRoleByPage((rolepage-1)*3);
        List<User> allUser=userService.selectAllUserByPage(0);

        User_role user_role=user_roleService.queryByUserId(id);
        Role myRole=roleService.selectRoleById(user_role.getRoleid());

        //用于用户列表编辑角色所有角色列表的展示
        List<Role> allUserRole=roleService.selectAllRole();
        model.addAttribute("allUserRole",allUserRole);

        List myPermission= new ConvertRole().toList(role_perService.selectPermission(myRole.getId()));
        model.addAttribute("myRole",myRole);
        model.addAttribute("myPermission",myPermission);
        if (allUser.size() > 0 && allUser.get(0) != null) {
            User_role user_role1=user_roleService.queryByUserId(allUser.get(0).getId());
            Role userRole0=roleService.selectRoleById(user_role1.getRoleid());
            model.addAttribute("user0", allUser.get(0));
            model.addAttribute("userRole0", userRole0);
        }
        if (allUser.size() > 1 && allUser.get(1) != null) {
            User_role user_role1=user_roleService.queryByUserId(allUser.get(1).getId());
            Role userRole1=roleService.selectRoleById(user_role1.getRoleid());
            model.addAttribute("userRole1", userRole1);
            model.addAttribute("user1", allUser.get(1));
        }
        if (allUser.size()>2&&allUser.get(2)!=null){
            User_role user_role1=user_roleService.queryByUserId(allUser.get(2).getId());
            Role userRole2=roleService.selectRoleById(user_role1.getRoleid());
            model.addAttribute("userRole2", userRole2);
            model.addAttribute("user2",allUser.get(2));
        }

        if (allRole.size()>0&&allRole.get(0)!=null)
            model.addAttribute("role0",allRole.get(0));
        if (allRole.size()>1&&allRole.get(1)!=null)
            model.addAttribute("role1",allRole.get(1));
        if (allRole.size()>2&&allRole.get(2)!=null)
            model.addAttribute("role2",allRole.get(2));

        List<Role> roles=roleService.selectAllRole();
        model.addAttribute("rolepage",rolepage);
        model.addAttribute("userpage",1);
        model.addAttribute("roles",roles);
        return "permission";
    }

}

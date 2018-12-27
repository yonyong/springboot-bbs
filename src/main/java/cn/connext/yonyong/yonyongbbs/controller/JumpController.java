package cn.connext.yonyong.yonyongbbs.controller;

import cn.connext.yonyong.yonyongbbs.entity.*;
import cn.connext.yonyong.yonyongbbs.dto.ShowArticle;
import cn.connext.yonyong.yonyongbbs.service.*;
import cn.connext.yonyong.yonyongbbs.util.ConvertRole;
import cn.connext.yonyong.yonyongbbs.util.sort.ArticleSort;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

    @Autowired
    Role_perService role_perService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    User_roleService user_roleService;

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
     * 登出
     * @param session
     * @return
     */
    @RequestMapping("/tologout")
    public String tologout(HttpSession session){
        session.removeAttribute("rs_user");
        return "index";
    }

    /**
     * 前往权限管理页面
     * @return
     */
    @RequestMapping("/topermission")
    public String topermission(HttpSession session,Model model,HttpServletResponse response){
        User user= (User) session.getAttribute("rs_user");
        if (user==null){
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.flush();
            out.println("<script>");
            out.println("alert('您当前尚未登陆，请先登录！');");
            out.println("history.back();");
            out.println("</script>");
            return "article";
        }
        int id=user.getId();
        List<Role> allRole=roleService.selectAllRoleByPage(0);
        List<User> allUser=userService.selectAllUserByPage(0);

        User_role user_role=user_roleService.queryByUserId(id);
        Role myRole=roleService.selectRoleById(user_role.getRoleid());
        //用于用户列表编辑角色所有角色列表的展示
        List<Role> allUserRole=roleService.selectAllRole();

        model.addAttribute("allUserRole",allUserRole);

        List myPermission= new ConvertRole().toList(role_perService.selectPermission(myRole.getId()));
        model.addAttribute("myRole",myRole);
        model.addAttribute("myPermission",myPermission);
        model.addAttribute("allRole",allRole);
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

        model.addAttribute("userpage",1);
        model.addAttribute("rolepage",1);
        List<Role> roles=roleService.selectAllRole();
        model.addAttribute("roles",roles);

        if (myRole.getLevel()==1)
            return "userPermission";
        return "permission";

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
        //不启用排序
//        List<Article> articleList=articleService.selectAllArticle(0);
        //启用排序
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
        model.addAttribute("list0",list.get(0));
        model.addAttribute("list1",list.get(1));
        model.addAttribute("list2",list.get(2));
        model.addAttribute("page",1);
        return "article";
    }


    @RequestMapping("/tomyarticle")
    public String tomyarticle(HttpSession session, Model model, HttpServletResponse response){
        User user= (User) session.getAttribute("rs_user");
        if (user==null){
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.flush();
            out.println("<script>");
            out.println("alert('您当前尚未登陆，请先登录！');");
            out.println("history.back();");
            out.println("</script>");
            return "article";
        }
        int id=user.getId();
        //不启用排序
//        List<Article> articleList=articleService.selectArticleByUserId(id,0);
        //启用排序
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
        if (list.size()>0&&list.get(0)!=null)
        model.addAttribute("list0",list.get(0));
        if (list.size()>1&&list.get(1)!=null)
        model.addAttribute("list1",list.get(1));
        if (list.size()>2&&list.get(2)!=null)
        model.addAttribute("list2",list.get(2));
        model.addAttribute("mypage",1);
        return "myarticle";
    }

    @RequestMapping("/test")
    public String test(){
        return "articleDetail";
    }

    @RequestMapping("/queryPermission")
    public String queryPermission(@RequestParam("id")int id,Model model){
        Role myRole=roleService.selectRoleById(id);
        List<Role_per> list=role_perService.selectPermission(myRole.getId());

        List myPermission=new ConvertRole().toList(list);

        model.addAttribute("myRole",myRole);

        model.addAttribute("myPermission",myPermission);
        return "selectPermission";
    }

}

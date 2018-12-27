package cn.connext.yonyong.yonyongbbs.controller;

import cn.connext.yonyong.yonyongbbs.entity.Role;
import cn.connext.yonyong.yonyongbbs.entity.User;
import cn.connext.yonyong.yonyongbbs.service.RoleService;
import cn.connext.yonyong.yonyongbbs.service.Role_perService;
import cn.connext.yonyong.yonyongbbs.service.UserService;
import cn.connext.yonyong.yonyongbbs.service.User_roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
public class UserController {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    Role_perService role_perService;

    @Autowired
    User_roleService user_roleService;

    /**
     * redis存放三次错误登陆 <>
     * @param tel
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value="tel") String tel, @RequestParam(value="password") String password,HttpServletRequest request){

        User user=new User();
        user.setTel(tel);
        user.setPassword(password);

        //判断用户是否在数据库里，如果User不为空，则账号密码在数据库中有相应的匹配，即用户存在
        User t_user=userService.selectUserByTel(tel);

        if(t_user==null){
            String jsonStr = "{\"errorCode\":\"11\",\"errorMessage\":\"该用户不存在\"}";
            return jsonStr;
        }

        User rs_user=userService.selectUser(tel,password);

        int count=redisTemplate.opsForValue().get("错误登陆"+tel)==null?
                0:(int)redisTemplate.opsForValue().get("错误登陆"+tel);
        if (rs_user!=null&&count<3){
            HttpSession session=request.getSession();
            session.setAttribute("rs_user", rs_user);
            redisTemplate.delete("错误登陆"+tel);
            String jsonStr = "{\"errorCode\":\"00\",\"errorMessage\":\"登陆成功！\"}";
            return jsonStr;
        }
        else if(count>=3){
            String jsonStr = "{\"errorCode\":\"33\",\"errorMessage\":\"您由于错误登陆次数太多，系统已将您的账户锁定，请在三分钟后重新登录！\"}";
            count++;
            redisTemplate.opsForValue().set("错误登陆"+tel,count,180, TimeUnit.SECONDS);
            return jsonStr;
        }
        else{
            String jsonStr = "{\"errorCode\":\"22\",\"errorMessage\":\"密码输入错误，错误输入三次后您的账户将会被锁定！\"}";
            count++;
            //这里的作用主要是考虑到用户只输错一次，在很长一段时间（姑且认为是一分钟至三分钟内）没有再次登陆，
            //为了用户的体验，决定在这个时候将其错误登陆记录清除
            redisTemplate.opsForValue().set("错误登陆"+tel,count,60,TimeUnit.SECONDS);
            return jsonStr;
        }
    }

    /**
     * 关于redis中存放验证码：
     * 生成验证码，在redis中存入<tel,验证码>
     * @param nickname
     * @param tel
     * @param volidate
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestParam(value = "nickname")String nickname,@RequestParam(value="tel") String tel, @RequestParam(value = "volidate")String volidate, @RequestParam(value="password")String password, HttpServletRequest request){

        User user=userService.selectUserByTel(tel);
        if(!redisTemplate.hasKey(tel)||redisTemplate.opsForValue().get(tel).equals(volidate)) {
            System.out.println("voalidate为"+volidate+"  ,yzm为："+redisTemplate.opsForValue().get(tel));
            System.out.println("验证码错误*****");
            String jsonStr = "{\"errorCode\":\"2\",\"errorMessage\":\"验证码错误\"}";

            return jsonStr;
        }
        else {
            if(user==null) {

                System.out.println("进如此循环");
                userService.addUser(nickname,tel,password);
                User user1=userService.selectUserByTel(tel);
                int user_id=user1.getId();
                user_roleService.add(user_id,3);

                String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"success\"}";

                return  jsonStr;
            }
            else
            {
                System.out.println("未进如此循环");
                String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"phone or password is error\"}";
                return jsonStr;
            }
        }
    }

}

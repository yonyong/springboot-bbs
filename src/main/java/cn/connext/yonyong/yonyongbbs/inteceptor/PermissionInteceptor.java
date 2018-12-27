package cn.connext.yonyong.yonyongbbs.inteceptor;

import cn.connext.yonyong.yonyongbbs.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PermissionInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //首先进入的方法
        System.out.println("preHandle");
        //return false表示拦截，不向下执行
        //return true表示放行
        System.out.println(request.getServletPath());
        HttpSession session = request.getSession();
        User u=(User)session.getAttribute("rs_user");
//        if(u!=null){
            return true;
//        }else{
//            response.sendRedirect("/tologin");
//            return false;
//        }

    }
    //返回modelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");
    }
    //执行Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion");
    }
}

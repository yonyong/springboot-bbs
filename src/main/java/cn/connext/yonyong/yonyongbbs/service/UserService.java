package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    /**
     * 通过用户id查询用户
     * @param tel
     */
    User selectUserByTel(String tel);

    /**
     * 注册用户
     * @param nickname
     * @param tel
     * @param password
     */
    void addUser(String nickname,String tel,String password);

    /**
     * 登陆验证
     * @param tel
     * @param password
     * @return
     */
    User selectUser(String tel,String password);

}

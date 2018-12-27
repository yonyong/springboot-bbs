package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUser();

    /**
     * 分页查询所有数据
     * @param page
     * @return
     */
    List<User> selectAllUserByPage(int page);

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

package cn.connext.yonyong.yonyongbbs.dao;

import cn.connext.yonyong.yonyongbbs.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserDao {
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
    void addUser(@Param("nickname") String nickname, @Param("tel") String tel,@Param("password") String password);

    /**
     * 登陆验证
     * @param tel
     * @param password
     * @return
     */
    User selectUser(@Param("tel") String tel,@Param("password") String password);
}

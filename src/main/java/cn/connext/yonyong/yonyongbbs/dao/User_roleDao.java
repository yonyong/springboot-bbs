package cn.connext.yonyong.yonyongbbs.dao;

import cn.connext.yonyong.yonyongbbs.entity.User_role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_roleDao {

    /**
     * 添加关联
     * @param userid
     * @param roleid
     */
    void add(@Param("userid")int userid,@Param("roleid")int roleid);

    /**
     * 查询某个用户对应的关联
     * @param userid
     */
    User_role queryByUserId(int userid);

    /**
     * 更新关联
     * @param userid
     * @param roleid
     */
    void update(@Param("userid")int userid,@Param("roleid")int roleid);

    /**
     * 删除关联
     * @param userid
     */
    void delete(int userid);

    /**
     * 更新所有roleid为roleid的关联，主要用于角色被删除时，将用户角色权限重置为普通用户
     * @param roleid
     */
    void updateAll(int roleid);

}

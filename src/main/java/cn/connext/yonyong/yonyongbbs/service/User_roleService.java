package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.User_role;

public interface User_roleService {

    /**
     * 添加关联
     * @param userid
     * @param roleid
     */
    void add(int userid,int roleid);

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
    void update(int userid,int roleid);

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

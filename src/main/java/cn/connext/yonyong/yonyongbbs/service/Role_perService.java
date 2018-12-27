package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.Role_per;

import java.util.List;

public interface Role_perService {
    /**
     * 查询某一个角色的所有权限
     * @param roleid
     * @return
     */
    List<Role_per> selectPermission(int roleid);

    /**
     * 为某个角色添加权限
     * @param roleid
     * @param perid
     */
    void addPermission(int roleid,int perid);

    /**
     * 删除某个角色的某个权限
     * @param roleid
     * @param preperid
     * @param afterperid
     */
    void editPermission(int roleid,int preperid,String afterperid);

    /**
     * 修改某个角色的权限
     * @param roleid
     * @param perid
     */
    void deletePermission(int roleid,int perid);

    /**
     * 删除某个角色的所有权限
     * @param roleid
     */
    void deleteAll(int roleid);
}

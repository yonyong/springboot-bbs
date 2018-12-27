package cn.connext.yonyong.yonyongbbs.dao;

import cn.connext.yonyong.yonyongbbs.entity.Role_per;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Role_perDao {

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
    void addPermission(@Param("roleid") int roleid,@Param("perid") int perid);

    /**
     * 删除某个角色的某个权限
     * @param roleid
     * @param preperid
     * @param afterperid
     */
    void editPermission(@Param("roleid")int roleid,@Param("preperid")int preperid,@Param("afterperid")String afterperid);

    /**
     * 修改某个角色的权限
     * @param roleid
     * @param perid
     */
    void deletePermission(@Param("roleid")int roleid,@Param("perid")int perid);

    /**
     * 删除某个角色的所有权限
     * @param roleid
     */
    void deleteAll(int roleid);
}

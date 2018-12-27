package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色
     */
    List<Role> selectAllRole();

    /**
     * 分页查询所有角色
     * @param page
     * @return
     */
    List<Role> selectAllRoleByPage(int page);

    /**
     * 根据id查询对应的角色
     * @param id
     */
    Role selectRoleById(int id);

    /**
     * 添加角色
     * @param role
     * @param level
     */
    void addRole( String role,int level);

    /**
     * 修改角色
     * @param id
     */
    void editRole(int id,String role,int level);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(int id);
}

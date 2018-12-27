package cn.connext.yonyong.yonyongbbs.dao;

import cn.connext.yonyong.yonyongbbs.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao层
 */
@Repository
public interface RoleDao {

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
    Role selectRoleById(@Param("id") int id);


    /**
     * 添加角色
     * @param role
     * @param level
     */
    void addRole(@Param("role") String role,@Param("level") int level);

    /**
     * 修改角色
     * @param id
     */
    void editRole(@Param("id") int id,@Param("role") String role,@Param("level") int level);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(@Param("id") int id);
}

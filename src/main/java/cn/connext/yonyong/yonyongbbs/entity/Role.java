package cn.connext.yonyong.yonyongbbs.entity;

/**
 *  用户角色，对应数据库role表
 */
public class Role {
    private int id;
    private int user_id;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

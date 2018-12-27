package cn.connext.yonyong.yonyongbbs.entity;

/**
 *  用户角色，对应数据库role表
 */
public class Role {
    private int id;
    private String role;
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

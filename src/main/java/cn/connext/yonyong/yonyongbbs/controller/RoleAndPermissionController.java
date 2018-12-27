package cn.connext.yonyong.yonyongbbs.controller;

import cn.connext.yonyong.yonyongbbs.entity.Role;
import cn.connext.yonyong.yonyongbbs.entity.Role_per;
import cn.connext.yonyong.yonyongbbs.entity.User;
import cn.connext.yonyong.yonyongbbs.entity.User_role;
import cn.connext.yonyong.yonyongbbs.service.RoleService;
import cn.connext.yonyong.yonyongbbs.service.Role_perService;
import cn.connext.yonyong.yonyongbbs.service.UserService;
import cn.connext.yonyong.yonyongbbs.service.User_roleService;

import cn.connext.yonyong.yonyongbbs.util.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.util.Collections;
import java.util.List;

@Controller
public class RoleAndPermissionController {

    @Autowired
    RoleService roleService;

    @Autowired
    User_roleService user_roleService;

    @Autowired
    UserService userService;

    @Autowired
    Role_perService role_perService;

    @RequestMapping("/editRole")
    @ResponseBody
    public String editRole(@RequestParam("editRoleUserId")int editRoleUserId,
                            @RequestParam("editRoleSeclection0")int editRoleSeclection0,
                           HttpSession session
                           ){
    System.out.println("11111111111111111111111111111111");

        //获取当前用户的等级
        User user= (User) session.getAttribute("rs_user");
        User_role user_role0=user_roleService.queryByUserId(user.getId());
        Role myRole=roleService.selectRoleById(user_role0.getRoleid());
        int myLevel=myRole.getLevel();

        //获取被操作用户的等级
        User_role user_role=user_roleService.queryByUserId(editRoleUserId);
        Role editRole=roleService.selectRoleById(user_role.getRoleid());
        int editRoleLevel=editRole.getLevel();

        //获取要更为的角色的等级
        Role targetRole=roleService.selectRoleById(editRoleSeclection0);
        int targetRoleLevel=targetRole.getLevel();

        if (editRoleLevel>2){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"只能更改权限等级比自己低的人！\"}";
            return jsonStr;
        }
        else if (myLevel<=targetRoleLevel){
            String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"不可将对象用户等级设置为比您高或与" +
                    "您持平（超级管理员等级>管理员等级>普通用户等级）！\"}";
            return jsonStr;
        }
        else {
            user_roleService.update(editRoleUserId,editRoleSeclection0);
            String jsonStr = "{\"errorCode\":\"2\",\"errorMessage\":\"角色修改成功！\"}";
            return jsonStr;
        }
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(@RequestParam("addrolename")String addrolename,
                          @RequestParam("addRoleLevel")int addRoleLevel,
                          @RequestParam("addRoleSlection") JSONArray addRoleSlection,
                          HttpSession session){
        List per=JsonUtils.getListFromJsonString(addRoleSlection);

        User user= (User) session.getAttribute("rs_user");
        System.out.println("111111111111111111111111");

        if (addRoleLevel==2&&user.getId()!=1005){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"您不具备超级管理员权限，不能添加管理员角色！\"}";
            return jsonStr;
        }
        roleService.addRole(addrolename,addRoleLevel);
        List<Role> list=roleService.selectAllRole();
        Collections.reverse(list);
        Role role=list.get(0);
        for (Object temp:per){
            role_perService.addPermission(role.getId(),Integer.valueOf(temp.toString()));
        }
        String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"操作成功！\"}";
        return jsonStr;
    }

    @RequestMapping("/editRolePer")
    @ResponseBody
    public String editRolePer(@RequestParam("ediPerRoleId")int ediPerRoleId,
                          @RequestParam("ediPerRoleLevel")int ediPerRoleLevel,
                          @RequestParam("ediPerRoleName")String ediPerRoleName,
                          @RequestParam("ediPerRoleTargetLevel")int ediPerRoleTargetLevel,
                          @RequestParam("ediPerRolePer") JSONArray ediPerRolePer,
                          HttpSession session){
        User user= (User) session.getAttribute("rs_user");
        int myId=user.getId();
        User_role user_role=user_roleService.queryByUserId(myId);
        Role myRole=roleService.selectRoleById(user_role.getRoleid());
        int myLevel=myRole.getLevel();
        if (myLevel==ediPerRoleLevel){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"不能修改与你同角色等级的用户！\"}";
            return jsonStr;
        }
        if (myLevel<ediPerRoleLevel){
            String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"不能修改角色等级比你高的的用户！\"}";
            return jsonStr;
        }
        if (myLevel==ediPerRoleTargetLevel){
            String jsonStr = "{\"errorCode\":\"2\",\"errorMessage\":\"不能将目标角色设置为与你角色等级相同！\"}";
            return jsonStr;
        }
        if (myLevel<ediPerRoleTargetLevel){
            String jsonStr = "{\"errorCode\":\"3\",\"errorMessage\":\"不能将目标角色设置为比你角色等级还高！\"}";
            return jsonStr;
        }
        roleService.editRole(ediPerRoleId,ediPerRoleName,ediPerRoleTargetLevel);
        role_perService.deleteAll(ediPerRoleId);
        if (JsonUtils.getListFromJsonString(ediPerRolePer)==null){
      System.out.println("null");
            String jsonStr = "{\"errorCode\":\"4\",\"errorMessage\":\"编辑成功！\"}";
            return jsonStr;
        }
        List per=JsonUtils.getListFromJsonString(ediPerRolePer);
        for (Object t:per){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(Integer.valueOf(t.toString()));
            role_perService.addPermission(ediPerRoleId,Integer.valueOf(t.toString()));
        }
        String jsonStr = "{\"errorCode\":\"4\",\"errorMessage\":\"编辑成功！\"}";
        return jsonStr;
    }

    @RequestMapping("/deleteRolePer")
    @ResponseBody
    public String deleteRole(@RequestParam("ediPerRoleId")int ediPerRoleId,
                             @RequestParam("ediPerRoleLevel")int ediPerRoleLevel,
                             HttpSession session){
        User user= (User) session.getAttribute("rs_user");
        int myId=user.getId();
        User_role user_role=user_roleService.queryByUserId(myId);
        Role myRole=roleService.selectRoleById(user_role.getRoleid());
        int myLevel=myRole.getLevel();
        if (ediPerRoleId==1||ediPerRoleId==2||ediPerRoleId==3){
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"基础角色不可删除！\"}";
            return jsonStr;
        }
        if (ediPerRoleLevel==myLevel){
            String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"不可以删除角色等级与你相同的角色！\"}";
            return jsonStr;
        }
        if (ediPerRoleLevel>myLevel){
            String jsonStr = "{\"errorCode\":\"2\",\"errorMessage\":\"不可以删除角色等级比你高的角色！\"}";
            return jsonStr;
        }

        user_roleService.updateAll(ediPerRoleId);
        roleService.deleteRole(ediPerRoleId);
        role_perService.deleteAll(ediPerRoleId);
        String jsonStr = "{\"errorCode\":\"3\",\"errorMessage\":\"删除成功！\"}";
        return jsonStr;
    }
}

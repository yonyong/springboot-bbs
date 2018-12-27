package cn.connext.yonyong.yonyongbbs.util;

import cn.connext.yonyong.yonyongbbs.entity.Role_per;

import java.util.ArrayList;
import java.util.List;

public class ConvertRole {
    public List toList(List<Role_per> list){
        List list1=new ArrayList();
        for( Role_per temp:list){
            switch (temp.getPerid()){
                case 1:{
                    list1.add("发布文章");
                    break;
                }
                case 2:{
                    list1.add("评论文章");
                    break;
                }
                case 3:{
                    list1.add("编辑自己文章");
                    break;
                }
                case 4:{
                    list1.add("编辑他人文章");
                    break;
                }
                case 5:{
                    list1.add("删除自己文章");
                    break;
                }
                case 6:{
                    list1.add("删除他人文章");
                    break;
                }
            }

        }
        return list1;
    }
}

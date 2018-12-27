package cn.connext.yonyong.yonyongbbs.util;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    /**
     * 传入格式为JSONObject的数据，将其转换为list，并将其每一个元素对应的权限id放入list返回
     * @param jsondata
     * @return
     */
    public static List getListFromJsonString(JSONArray jsondata){
        List<String> list;
        List<Integer> list1=new ArrayList<>();
//        JSONArray jsonArray=JSONArray.fromObject(jsondata);
        list=JSONArray.toList(jsondata);
        for (Object i:list){
            if (i.toString().contains("null"))
                return null;
            if (i.toString().contains("1"))
                list1.add(1);
            else if (i.toString().contains("2"))
                list1.add(2);
            else if (i.toString().contains("3"))
                list1.add(3);
            else if (i.toString().contains("4"))
                list1.add(4);
            else if (i.toString().contains("5"))
                list1.add(5);
            else if (i.toString().contains("6"))
                list1.add(6);
        }
        if (list1.isEmpty())
            return null;
        return list1;
    }
}

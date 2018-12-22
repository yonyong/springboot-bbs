package cn.connext.yonyong.yonyongbbs.util;


import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

    public static String getMD5(String str) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());

            return new BigInteger(1, md.digest()).toString(16);

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }


    public static String MD5(String s) {

        if(s==null)
            s="0";

        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {

            byte[] btInput = s.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;

            char str[] = new char[j * 2];

            int k = 0;

            for (int i = 0; i < j; i++) {

                byte byte0 = md[i];

                str[k++] = hexDigits[byte0 >>> 4 & 0xf];

                str[k++] = hexDigits[byte0 & 0xf];

            }

            return new String(str);

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    public static void main(String[] args) {

        String md5 = MD5Utils.MD5("yonyong");

        System.out.println(md5);

        String md52 = MD5Utils.getMD5("123");

        System.out.println(md52);
    }

}
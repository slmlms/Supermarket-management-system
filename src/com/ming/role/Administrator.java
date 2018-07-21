package com.ming.role;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * 管理员类
 *
 * @author SM22
 * @date 2018年7月11日下午10:42:59
 */
public class Administrator {
    private static String name;
    private static String pass;
    static Properties pro = new Properties();
    static final String filepath = "src/admin.properties";
    static Scanner sc = new Scanner(System.in);

    /**
     * 用户名验证
     *
     * @return 用户名是否正确
     */
    public static String getName() {
        try {
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.properties"));
            name = pro.getProperty("AdminName");
//            Integer name = (name1 == "") ? null : Integer.valueOf(name1);
            if (name == null) {
                return null;
            } else
                return name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 密码验证
     *
     * @return 密码是否正确
     */
    public static String getPass(String key) {
        try {
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.properties"));
//            byte[] bytes = pro.getProperty("AdminPass").getBytes();
            String pass1 = pro.getProperty("AdminPass");
             pass=UserManagement.aesDecrypt(UserManagement.HexStr2Byte(pass1),key) ;      //解密
            return pass;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }


    public static void setName() {
        FileInputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(filepath);
            fos = new FileOutputStream(filepath);
            pro.load(fis);
            fis.close();
            System.out.println("请输入新的用户名");
            String str = sc.next();
            pro.setProperty("AdminName", str);          //保存用户名
//            pro.setProperty("AdminPass",pro.getProperty("AdminPass"));
//            pro.store(fos, "admin.properties");         //使用输出流保存到磁盘
            System.out.println("修改成功，新的用户名为：" + str);
//            Administrator.name = str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
//                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setPass() {
//        pro = new Properties();
        FileInputStream fis=null;
        OutputStream fos = null;
        String key = null;
        try {
            fis = new FileInputStream(filepath);
            fos = new FileOutputStream(filepath);
            pro.load(fis);
            fis.close();
            System.out.println("请输入新的密码");
            String pass = sc.next();
            key = pass.trim();          //用密码本身作为秘钥
            while (key.length() < 16) key += "a";       //秘钥长度不足16时用a填充
            pass = UserManagement.Byte2HexStr(UserManagement.aesEncrypt(pass,key));         //使用aes进行加密,并转换成16进制字符串保存
            pro.setProperty("AdminPass", pass);          //保存密码
//            pro.setProperty("AdminName",pro.getProperty("AdminName"));
            pro.store(fos, "admin.properties");         //使用输出流保存到磁盘
            System.out.println("密码修改成功");
            Administrator.pass = pass;
//            return key;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return key;
    }


}

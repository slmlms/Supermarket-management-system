package com.ming.role;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * ����Ա��
 *
 * @author SM22
 * @date 2018��7��11������10:42:59
 */
public class Administrator {
    private static String name;
    private static String pass;
    static Properties pro = new Properties();
    static final String filepath = "src/admin.properties";
    static Scanner sc = new Scanner(System.in);

    /**
     * �û�����֤
     *
     * @return �û����Ƿ���ȷ
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
     * ������֤
     *
     * @return �����Ƿ���ȷ
     */
    public static String getPass(String key) {
        try {
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.properties"));
//            byte[] bytes = pro.getProperty("AdminPass").getBytes();
            String pass1 = pro.getProperty("AdminPass");
             pass=UserManagement.aesDecrypt(UserManagement.HexStr2Byte(pass1),key) ;      //����
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
            System.out.println("�������µ��û���");
            String str = sc.next();
            pro.setProperty("AdminName", str);          //�����û���
//            pro.setProperty("AdminPass",pro.getProperty("AdminPass"));
//            pro.store(fos, "admin.properties");         //ʹ����������浽����
            System.out.println("�޸ĳɹ����µ��û���Ϊ��" + str);
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
            System.out.println("�������µ�����");
            String pass = sc.next();
            key = pass.trim();          //�����뱾����Ϊ��Կ
            while (key.length() < 16) key += "a";       //��Կ���Ȳ���16ʱ��a���
            pass = UserManagement.Byte2HexStr(UserManagement.aesEncrypt(pass,key));         //ʹ��aes���м���,��ת����16�����ַ�������
            pro.setProperty("AdminPass", pass);          //��������
//            pro.setProperty("AdminName",pro.getProperty("AdminName"));
            pro.store(fos, "admin.properties");         //ʹ����������浽����
            System.out.println("�����޸ĳɹ�");
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

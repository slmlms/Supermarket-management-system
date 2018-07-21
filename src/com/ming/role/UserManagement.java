package com.ming.role;

import com.ming.MainProgram.Menu;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Properties;
import java.util.Scanner;

public class UserManagement {
    static Scanner sc = new Scanner(System.in);
    static Properties pro = new Properties();
    static final String filepath = "admin.properties";

    /**
     * 管理员账户管理
     */
    public static void adminManagement() {

        while (true) {
            System.out.println("请选择需要修改的内容：\n1.修改用户\t2.返回主菜单");
            String i = sc.next();
            if ("1".equals(i)) {
                Administrator.setName();
                Administrator.setPass();
            } else if ("2".equals(i)) {
                Menu.menu();
                return;
            } else {
                System.out.println("输入错误");
            }
        }

    }


    /**
     * 收银员账户管理
     */
    public static void CashierManagement() {
        while (true) {
            System.out.println("请选择需要修改的内容：\n1.修改用户\t2.返回主菜单");
            String i = sc.next();
            if ("1".equals(i)) {
                Cashier.setName();
                Cashier.setPass();
            } else if ("2".equals(i)) {
                Menu.menu();
                return;
            } else {
                System.out.println("输入错误");
            }
        }
    }

    /**
     * 使用AES对字符串加密
     *
     * @param str GBK编码的字符串
     * @param key 密钥（16字节）
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return bytes;
    }

    /**
     * 使用AES对数据解密
     *
     * @param bytes GBK编码的二进制数据
     * @param key   密钥（16字节）
     * @return 解密结果
     * @throws Exception
     */
    public static String aesDecrypt(byte[] bytes, String key) throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

    /**
     * 二进制数组转换为十六进制
     *
     * @param buf 二进制数组
     * @return 十六进制字符串
     */
    public static String Byte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] HexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}

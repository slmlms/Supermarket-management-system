package com.ming.PaymentAndOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.bean.SupermarketVIP;
import com.ming.dao.impl.SupermarketDaoImplUpdate;

/**
 * 支付管理 确认购买后：提示“是否用会员卡支付”？如果是，输入会员卡号，并扣除金额。如果不是，则提示现金支付。
 *
 * @author SM22
 * @date 2018年7月10日下午4:01:48
 */
public class PaymentManagement {

    static Scanner sc = new Scanner(System.in);

    /**
     * 支付管理
     */
    public PaymentManagement() {
        double dingdan =  PurchaseManagement.INSTANCE.Purchase(); // 购买管理单例模式

        System.out.println("请选择支付方式\n1.会员卡余额\t2.现金支付\t3.返回主菜单");
        int i = sc.nextInt();

        if (i == 1)
            PointPayment(dingdan); // 余额支付
        else if (i == 2)
            cashPayment(dingdan); // 现金支付
        else if (i == 3) {
            PurchaseManagement.spmap.clear();   //清空购物车
            Menu.menu();
            return;
        }

    }

    /**
     * 余额支付
     *
     * @throws SQLException
     */
    private void PointPayment(double dingdan) {
        System.out.println("*****使用会员卡支付*****");
        System.out.println("请输入会员卡号");
        Integer cardNum = sc.nextInt();
        if (dingdan > CheckBalances(cardNum)) { // 如果余额不足则使用现金支付
            System.out.println("余额不足");
            cashPayment(dingdan);
        } else {
            double change = CheckBalances(cardNum) - dingdan;
            System.out.println("支付成功，卡上余额" + String.format("%.2f",change) + "元");
            new SupermarketDaoImplUpdate().updateVIP(new SupermarketVIP(), "Balance", cardNum, change);    //	扣除卡中余额
            new OrderManagement().CreateOrder(cardNum, dingdan, "余额支付"); // 创建订单信息
            DeductedGoods();        //扣除商品
            System.out.println("交易♂成功");
            new PaymentManagement();        //返回主菜单
            return;
        }
    }

    /**
     * 现金支付
     *
     * @throws SQLException
     */
    private void cashPayment(double dingdan) {
        System.out.println("*****使用现金支付*****");
        System.out.println("有无会员卡(y/n)");
        String str = sc.next();
        if ("y".equals(str)) { // 如果有会员卡，则生成订单信息
            System.out.println("请输入会员卡号");
            Integer cardNum = sc.nextInt();
            System.out.println("请支付现金");
            double crash = sc.nextDouble();
            double change = (crash - dingdan);
            if (change >= 0) {
                System.out.println("收到" + crash + "元，找零" + String.format("%.2f",change) + "元");
                DeductedGoods(); // 删除扣除商品数量
                new OrderManagement().CreateOrder(cardNum, dingdan, "现金支付");
                System.out.println("交易♂成功");
                new PaymentManagement();
                return;
            } else
                System.out.println("现金不足，请补齐" + String.format("%.2f",(dingdan - crash)) + "元");
        } else if ("n".equals(str)) { // 如果没有会员卡，则使用游客账户生成订单信息
            System.out.println("请支付现金");
            double crash = sc.nextDouble();
            double change = (crash - dingdan);
            if (change >= 0) {
                System.out.println("收到" + crash + "元，找零" + String.format("%.2f",change) + "元");
                DeductedGoods();
                new OrderManagement().CreateOrder(1, dingdan, "现金支付");
                System.out.println("交易♂成功");
                new PaymentManagement();
                return;
            } else
                System.out.println("现金不足，请补齐" + String.format("%.2f",(dingdan - crash)) + "元");
        } else {
            System.out.println("输入错误");
        }
    }

    /**
     * 查询余额
     */
    private Double CheckBalances(Integer cardNum) {
        Connection con = new com.ming.util.Connection().connection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM `testjdbc`.`会员信息` WHERE `VIPNumber` = '" + cardNum + "' LIMIT 0, 1000";
        Double yue = 0.0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("卡上余额：" + rs.getDouble("Balance"));
                yue = rs.getDouble("Balance");
                return yue;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            com.ming.util.Connection.close(con, ps, rs);
        }
        return yue;
    }

    /**
     * 支付成功，扣除商品
     */
    private static void DeductedGoods() {
        Map<SupermarketProduct, Integer> map = PurchaseManagement.spmap;

        for (SupermarketProduct sp1 : map.keySet()) {
            sp1.setNumberOfProducts(sp1.getNumberOfProducts() - map.get(sp1));
            new SupermarketDaoImplUpdate().updateProuuct(sp1, "NumberOfProducts", sp1.getProductNumber(),
                    sp1.getNumberOfProducts());

        }
    }

}

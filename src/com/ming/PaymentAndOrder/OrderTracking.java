package com.ming.PaymentAndOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.OrderInformation;
import com.ming.dao.impl.SupermarketDaoImpl;

/**
 * 用户输入商品编号和会员编号查询出对应订单记录(收银员权限) 输入商品编号或是会员编号，在控制台显示简要订单记录及详细订单记录
 *
 * @author SM22
 * @date 2018年7月11日上午9:54:20
 */
public class OrderTracking {
    static Scanner sc = new Scanner(System.in);


    /**
     * 订单查询主界面
     */
    public void track() {
        while (true) {
            System.out.println("------------------订单查询------------------");
            System.out.println("请选择查询方式：\n1.通过会员号查询\t2.通过商品编号查询\t3.返回主菜单");
            Integer num = sc.nextInt();
            if (num == 1) {
                inquiryVIP();    //通过会员号查询
            } else if (num == 2) {
                inquiryProduct();    //通过商品编号查询
            } else if (num == 3) {
                Menu.menu();    //通过商品编号查询
                return;
            } else
                System.out.println("输入错误");
        }

    }

    /**
     * 通过会员号查询
     */
    private void inquiryVIP() {
        System.out.println("请输入会员卡号");
        Integer vipNum = sc.nextInt();
        ResultSet rs = new SupermarketDaoImpl().searchOrderInformation("UserID", vipNum);
        List<Integer> list = new ArrayList<>();
        /**
         * 判断返回结果是否为空,如果非空就生成订单信息
         */
        Dingdan(rs, list);
//		try {
//			if (!rs.next()) {
//				System.out.println("订单不存在");
//				return;
//			} else {
//				rs.beforeFirst(); // 要将光标提前，否则无法打印
//				while (rs.next()) {
//					list.add(rs.getInt("OrderID"));
//				}
//				orderInformation(rs, list);// 打印订单信息
//				orderDetails(rs, list);
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
    }

    /**
     * 通过商品ID查询
     */
    private void inquiryProduct() {
        System.out.println("请输入商品ID");
        Integer productid = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        ResultSet rs = new SupermarketDaoImpl().searchOrderDetails("ProductID", productid);
        Dingdan(rs,list);
//        try {
//            if (!rs.next()) {
//                System.out.println("订单不存在");
//                return;
//            } else {
//                rs.beforeFirst();
//                while (rs.next()) {
//                    list.add(rs.getInt("OrderID"));
//                }
//                orderInformation(rs, list);
//                orderDetails(rs, list);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }




        private void Dingdan (ResultSet rs, List < Integer > list){
            try {
                if (!rs.next()) {
                    System.out.println("订单不存在");
                    return;
                } else {
                    rs.beforeFirst();
                    while (rs.next()) {
                        list.add(rs.getInt("OrderID"));
                    }
                    orderInformation(rs, list);
                    orderDetails(rs, list);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        /**
         * 打印订单信息
         */
        private List<Integer> orderInformation (ResultSet rs, List < Integer > list){

            try {
                rs.beforeFirst();
                System.out.println("----------------------------订单信息----------------------------");
                System.out.println("订单ID\t\t" + "会员用户ID\t\t" +"会员名称\t\t"+ "订单总金额\t\t" + "下单时间\t\t\t\t" + "下单类型\t");
                for (Integer i : list) {
                    rs = new SupermarketDaoImpl().search("SELECT\n" +
                            "`会员信息`.VIPName,\n" +
                            "`订单信息`.OrderID,\n" +
                            "`订单信息`.UserID,\n" +
                            "`订单信息`.AmountOfOrders,\n" +
                            "`订单信息`.OrderTime,\n" +
                            "`订单信息`.PaymentTypes\n" +
                            "FROM\n" +
                            "`订单信息`\n" +
                            "INNER JOIN `会员信息` ON `订单信息`.UserID = `会员信息`.VIPNumber\n" +
                            "WHERE\n" +
                            "`订单信息`.OrderID = "+i+"");
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("OrderID") + "\t" + rs.getInt("UserID") + "\t\t\t" +rs.getString("VIPName")+"\t\t"+ rs.getDouble("AmountOfOrders")
                                        + "\t\t" + rs.getObject("OrderTime") + "\t" + rs.getString("PaymentTypes"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        /**
         * 打印订单详情
         */
        private void orderDetails (ResultSet rs, List < Integer > list){
            try {
                rs.beforeFirst();
                System.out.println("----------------------------订单详情----------------------------");
                System.out.println("订单ID\t\t" + "商品ID\t\t" +"商品名称\t\t\t"+"商品类型\t\t" +"商品数量\t\t" + "商品单价");
                for (Integer i : list) {
                    rs = new SupermarketDaoImpl().search("SELECT\n" +
                            "\t`订单详情`.OrderID,\n" +
                            "\t`订单详情`.ProductID,\n" +
                            "\t`订单详情`.ProductNum,\n" +
                            "\t`订单详情`.CommodityPrice,\n" +
                            "\t`商品信息`.ProductName,\n" +
                            "\t`商品类型`.TypesName \n" +
                            "FROM\n" +
                            "\t`订单详情`\n" +
                            "\tINNER JOIN `商品信息` ON `订单详情`.ProductID = `商品信息`.ProductNumber\n" +
                            "\tINNER JOIN `商品类型` ON `商品信息`.ProductTypes = `商品类型`.TypesID \n" +
                            "WHERE\n" +
                            "\t`订单详情`.OrderID = "+i+"");
                    while (rs.next()) {
                        System.out.println(rs.getInt("OrderID") + "\t" + rs.getInt("ProductID") + "\t\t\t"
                                +rs.getString("ProductName")+ "\t\t\t"+rs.getString("TypesName")+ "\t\t\t"+ rs.getInt("ProductNum") + "\t\t\t" + rs.getDouble("CommodityPrice"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



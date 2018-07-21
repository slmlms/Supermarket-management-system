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
 * �û�������Ʒ��źͻ�Ա��Ų�ѯ����Ӧ������¼(����ԱȨ��) ������Ʒ��Ż��ǻ�Ա��ţ��ڿ���̨��ʾ��Ҫ������¼����ϸ������¼
 *
 * @author SM22
 * @date 2018��7��11������9:54:20
 */
public class OrderTracking {
    static Scanner sc = new Scanner(System.in);


    /**
     * ������ѯ������
     */
    public void track() {
        while (true) {
            System.out.println("------------------������ѯ------------------");
            System.out.println("��ѡ���ѯ��ʽ��\n1.ͨ����Ա�Ų�ѯ\t2.ͨ����Ʒ��Ų�ѯ\t3.�������˵�");
            Integer num = sc.nextInt();
            if (num == 1) {
                inquiryVIP();    //ͨ����Ա�Ų�ѯ
            } else if (num == 2) {
                inquiryProduct();    //ͨ����Ʒ��Ų�ѯ
            } else if (num == 3) {
                Menu.menu();    //ͨ����Ʒ��Ų�ѯ
                return;
            } else
                System.out.println("�������");
        }

    }

    /**
     * ͨ����Ա�Ų�ѯ
     */
    private void inquiryVIP() {
        System.out.println("�������Ա����");
        Integer vipNum = sc.nextInt();
        ResultSet rs = new SupermarketDaoImpl().searchOrderInformation("UserID", vipNum);
        List<Integer> list = new ArrayList<>();
        /**
         * �жϷ��ؽ���Ƿ�Ϊ��,����ǿվ����ɶ�����Ϣ
         */
        Dingdan(rs, list);
//		try {
//			if (!rs.next()) {
//				System.out.println("����������");
//				return;
//			} else {
//				rs.beforeFirst(); // Ҫ�������ǰ�������޷���ӡ
//				while (rs.next()) {
//					list.add(rs.getInt("OrderID"));
//				}
//				orderInformation(rs, list);// ��ӡ������Ϣ
//				orderDetails(rs, list);
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
    }

    /**
     * ͨ����ƷID��ѯ
     */
    private void inquiryProduct() {
        System.out.println("��������ƷID");
        Integer productid = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        ResultSet rs = new SupermarketDaoImpl().searchOrderDetails("ProductID", productid);
        Dingdan(rs,list);
//        try {
//            if (!rs.next()) {
//                System.out.println("����������");
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
                    System.out.println("����������");
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
         * ��ӡ������Ϣ
         */
        private List<Integer> orderInformation (ResultSet rs, List < Integer > list){

            try {
                rs.beforeFirst();
                System.out.println("----------------------------������Ϣ----------------------------");
                System.out.println("����ID\t\t" + "��Ա�û�ID\t\t" +"��Ա����\t\t"+ "�����ܽ��\t\t" + "�µ�ʱ��\t\t\t\t" + "�µ�����\t");
                for (Integer i : list) {
                    rs = new SupermarketDaoImpl().search("SELECT\n" +
                            "`��Ա��Ϣ`.VIPName,\n" +
                            "`������Ϣ`.OrderID,\n" +
                            "`������Ϣ`.UserID,\n" +
                            "`������Ϣ`.AmountOfOrders,\n" +
                            "`������Ϣ`.OrderTime,\n" +
                            "`������Ϣ`.PaymentTypes\n" +
                            "FROM\n" +
                            "`������Ϣ`\n" +
                            "INNER JOIN `��Ա��Ϣ` ON `������Ϣ`.UserID = `��Ա��Ϣ`.VIPNumber\n" +
                            "WHERE\n" +
                            "`������Ϣ`.OrderID = "+i+"");
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
         * ��ӡ��������
         */
        private void orderDetails (ResultSet rs, List < Integer > list){
            try {
                rs.beforeFirst();
                System.out.println("----------------------------��������----------------------------");
                System.out.println("����ID\t\t" + "��ƷID\t\t" +"��Ʒ����\t\t\t"+"��Ʒ����\t\t" +"��Ʒ����\t\t" + "��Ʒ����");
                for (Integer i : list) {
                    rs = new SupermarketDaoImpl().search("SELECT\n" +
                            "\t`��������`.OrderID,\n" +
                            "\t`��������`.ProductID,\n" +
                            "\t`��������`.ProductNum,\n" +
                            "\t`��������`.CommodityPrice,\n" +
                            "\t`��Ʒ��Ϣ`.ProductName,\n" +
                            "\t`��Ʒ����`.TypesName \n" +
                            "FROM\n" +
                            "\t`��������`\n" +
                            "\tINNER JOIN `��Ʒ��Ϣ` ON `��������`.ProductID = `��Ʒ��Ϣ`.ProductNumber\n" +
                            "\tINNER JOIN `��Ʒ����` ON `��Ʒ��Ϣ`.ProductTypes = `��Ʒ����`.TypesID \n" +
                            "WHERE\n" +
                            "\t`��������`.OrderID = "+i+"");
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



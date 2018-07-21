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
 * ֧������ ȷ�Ϲ������ʾ���Ƿ��û�Ա��֧����������ǣ������Ա���ţ����۳���������ǣ�����ʾ�ֽ�֧����
 *
 * @author SM22
 * @date 2018��7��10������4:01:48
 */
public class PaymentManagement {

    static Scanner sc = new Scanner(System.in);

    /**
     * ֧������
     */
    public PaymentManagement() {
        double dingdan =  PurchaseManagement.INSTANCE.Purchase(); // ���������ģʽ

        System.out.println("��ѡ��֧����ʽ\n1.��Ա�����\t2.�ֽ�֧��\t3.�������˵�");
        int i = sc.nextInt();

        if (i == 1)
            PointPayment(dingdan); // ���֧��
        else if (i == 2)
            cashPayment(dingdan); // �ֽ�֧��
        else if (i == 3) {
            PurchaseManagement.spmap.clear();   //��չ��ﳵ
            Menu.menu();
            return;
        }

    }

    /**
     * ���֧��
     *
     * @throws SQLException
     */
    private void PointPayment(double dingdan) {
        System.out.println("*****ʹ�û�Ա��֧��*****");
        System.out.println("�������Ա����");
        Integer cardNum = sc.nextInt();
        if (dingdan > CheckBalances(cardNum)) { // ���������ʹ���ֽ�֧��
            System.out.println("����");
            cashPayment(dingdan);
        } else {
            double change = CheckBalances(cardNum) - dingdan;
            System.out.println("֧���ɹ����������" + String.format("%.2f",change) + "Ԫ");
            new SupermarketDaoImplUpdate().updateVIP(new SupermarketVIP(), "Balance", cardNum, change);    //	�۳��������
            new OrderManagement().CreateOrder(cardNum, dingdan, "���֧��"); // ����������Ϣ
            DeductedGoods();        //�۳���Ʒ
            System.out.println("���ס�ɹ�");
            new PaymentManagement();        //�������˵�
            return;
        }
    }

    /**
     * �ֽ�֧��
     *
     * @throws SQLException
     */
    private void cashPayment(double dingdan) {
        System.out.println("*****ʹ���ֽ�֧��*****");
        System.out.println("���޻�Ա��(y/n)");
        String str = sc.next();
        if ("y".equals(str)) { // ����л�Ա���������ɶ�����Ϣ
            System.out.println("�������Ա����");
            Integer cardNum = sc.nextInt();
            System.out.println("��֧���ֽ�");
            double crash = sc.nextDouble();
            double change = (crash - dingdan);
            if (change >= 0) {
                System.out.println("�յ�" + crash + "Ԫ������" + String.format("%.2f",change) + "Ԫ");
                DeductedGoods(); // ɾ���۳���Ʒ����
                new OrderManagement().CreateOrder(cardNum, dingdan, "�ֽ�֧��");
                System.out.println("���ס�ɹ�");
                new PaymentManagement();
                return;
            } else
                System.out.println("�ֽ��㣬�벹��" + String.format("%.2f",(dingdan - crash)) + "Ԫ");
        } else if ("n".equals(str)) { // ���û�л�Ա������ʹ���ο��˻����ɶ�����Ϣ
            System.out.println("��֧���ֽ�");
            double crash = sc.nextDouble();
            double change = (crash - dingdan);
            if (change >= 0) {
                System.out.println("�յ�" + crash + "Ԫ������" + String.format("%.2f",change) + "Ԫ");
                DeductedGoods();
                new OrderManagement().CreateOrder(1, dingdan, "�ֽ�֧��");
                System.out.println("���ס�ɹ�");
                new PaymentManagement();
                return;
            } else
                System.out.println("�ֽ��㣬�벹��" + String.format("%.2f",(dingdan - crash)) + "Ԫ");
        } else {
            System.out.println("�������");
        }
    }

    /**
     * ��ѯ���
     */
    private Double CheckBalances(Integer cardNum) {
        Connection con = new com.ming.util.Connection().connection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM `testjdbc`.`��Ա��Ϣ` WHERE `VIPNumber` = '" + cardNum + "' LIMIT 0, 1000";
        Double yue = 0.0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("������" + rs.getDouble("Balance"));
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
     * ֧���ɹ����۳���Ʒ
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

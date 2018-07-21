package com.ming.PaymentAndOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.ming.MainProgram.Menu;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.impl.SupermarketDaoImpl;




/**
 * ������� ������ƷID-->��ʾ��Ʒ����-->���빺������-->������һ����ƷID����˷����� ֱ�����¡�ȷ�����򡱰�ť�� ���ض����ܶ�
 * 
 * @author SM22
 * @date 2018��7��10������11:15:32
 */
public enum PurchaseManagement {
	
	INSTANCE;		//����ö���ഴ������ģʽ
	
	private static Scanner sc = new Scanner(System.in);
	public static Map<SupermarketProduct, Integer> spmap = new HashMap<>();
	 
	 
	/**
	 * �������
	 * @return
	 */
	public Double Purchase() {
		Double sum = 0.0;
		while(true) {
			
			System.out.println("��ѡ��˵�");
			System.out.println("1.������Ʒ\t2.ɾ����Ʒ\t3.����\t4.�������˵�");
			String str = sc.next();
			
			if ("1".equals(str)) {
				while (true){
					inputID();
					System.out.println("�Ƿ����(y/n)");
					str = sc.next();
					if("n".equals(str)) break;
				}

			} 
			else if ("2".equals(str)) {
				deleteProduct();
				
			}else if("3".equals(str)) {
				if (spmap.isEmpty()) {
					System.out.println("���ﳵΪ��");
					Purchase();
				}
				System.out.println("��Ʒ\t\t����\t\t����");
				/**
				 * ��ʾ���ﳵ��ϸ��Ϣ
				 */
				for (SupermarketProduct sp1 : spmap.keySet()) {
					System.out.println(sp1.getProductName()+"\t"+sp1.getUnitPrice()+"\t"+spmap.get(sp1));
					sum+=(sp1.getUnitPrice()*(sp1.getCount()/10)*spmap.get(sp1));
				}
				System.out.println("�����ܶ���" + String.format("%.2f",sum));
				break;
			}else if("4".equals(str)) {
				spmap.clear();
				Menu.menu();
				return null;
			}
		}return sum;
	}

	/**
	 * ������ƷID����Ʒ��ϸ��Ϣ������ӵ����ﳵ
	 */
	private static void inputID() {
		
		System.out.println("*****��������ƷID*****");
		Integer id = sc.nextInt();
		//ÿ����Ʒ�������Ϣ
		
		SupermarketProduct sp = new SupermarketProduct();
		ResultSet rs = new SupermarketDaoImpl().searchProduct("ProductNumber", id);
		//�ж��Ƿ����ظ���Ʒ
		for (SupermarketProduct sp1 : spmap.keySet()) {
			//�����ƷID���ڣ����޸Ĺ�������
			if(sp1.getProductNumber()==id) {
				Integer num = spmap.get(sp1);
				System.out.println(sp1.getProductName()+"�Ѵ���,ʣ��"+sp1.getNumberOfProducts()+"���������빺������");
				Integer i = sc.nextInt();
				if(isEnough(sp1.getProductName(), i+num)) {
					spmap.replace(sp1,(i+num));
					System.out.println("��ӳɹ�");
				}
				System.out.println("*****�Ƿ����(y/n)*****");
				String str1 = sc.next();
				if("y".equals(str1)) inputID();
			}
		}
		try {
			while(rs.next()) {
				sp.setCount(rs.getDouble("Count"));
				sp.setNumberOfProducts(rs.getInt("NumberOfProducts"));
				sp.setProductName(rs.getString("ProductName"));
				sp.setProductNumber(rs.getInt("ProductNumber"));
				sp.setProductTypes(rs.getInt("ProductTypes"));
				sp.setUnitPrice(rs.getDouble("UnitPrice"));
			}
			System.out.println("��ǰ��ƷΪ"+sp.getProductName()+",ʣ��"+sp.getNumberOfProducts()+"��,�����빺������");
			Integer num = sc.nextInt();
			//��ӹ��ﳵ
			if(isEnough(sp.getProductName(), num)) {
				spmap.put(sp, num);
			}
			else {
				System.err.println("��������");
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * �ж���Ʒ�Ƿ����
	 * @param ProductName	��Ʒ����
	 * @param num	Ҫ���������
	 * @return
	 */
	private  static boolean isEnough(String ProductName,Integer num ) {
		ResultSet rs = new SupermarketDaoImpl().searchProduct("ProductName", ProductName);
		boolean bool = false;
		try {
			if(!rs.next()) {
				System.err.println("��Ʒ������");
				return false;
			}else {
				rs.first();
				bool= rs.getInt("NumberOfProducts") >= num;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}


	/**
	 * �ӹ��ﳵɾ����Ʒ
	 */
	private static void deleteProduct() {
		Map<Integer, SupermarketProduct> map= showGouwuche();
		while(!showGouwuche().isEmpty()) {

			System.out.println("*****������Ҫɾ������Ʒ���*****");
			Integer i = sc.nextInt();
			System.out.println("�Ƿ�ɾ��"+map.get(i).getProductName()+"(y/n)");
			String str = sc.next();
			if("y".equals(str)) {
				System.out.println("*****������ɾ��������*****");
				Integer num = sc.nextInt();
				if(num>spmap.get(map.get(i))) {
					System.out.println("*****����������Χ������������*****");
				}else if(num<spmap.get(map.get(i))){
					System.out.println("ɾ���ɹ�,ʣ��"+(spmap.get(map.get(i))-num)+"��");
					spmap.put(map.get(i), spmap.get(map.get(i))-num);
					if(spmap.get(map.get(i))-num==0) return;
				}else {
					spmap.remove(map.get(i));
					System.out.println("*****ɾ����Ʒ�ɹ�*****");
					return;
				}
				System.out.println("�Ƿ����(y/n)");
				str = sc.next();
				if("n".equals(str)) {
					break;
				}
			}else if("n".equals(str)) {
				break;
			}else System.err.println("����������������");
		}
		


	}
	
	private static Map<Integer, SupermarketProduct> showGouwuche() {
		System.out.println("��ǰ���ﳵ��ƷΪ");
		System.out.println("���\t��Ʒ\t����\t����");
		Map<Integer, SupermarketProduct> map = new LinkedHashMap<>();
		for (SupermarketProduct sp1 : spmap.keySet()) {
			map.put(sp1.getProductNumber(), sp1);
			System.out.println(sp1.getProductNumber()+"\t"+sp1.getProductName()+"\t"+sp1.getUnitPrice()+"\t"+spmap.get(sp1));
		}
		return map;
	}
	
	
	
}

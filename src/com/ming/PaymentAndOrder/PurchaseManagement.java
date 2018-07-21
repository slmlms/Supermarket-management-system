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
 * 购买管理 输入商品ID-->显示商品详情-->输入购买数量-->输入下一个商品ID，如此反复。 直到按下“确定购买”按钮。 返回订单总额
 * 
 * @author SM22
 * @date 2018年7月10日上午11:15:32
 */
public enum PurchaseManagement {
	
	INSTANCE;		//利用枚举类创建单例模式
	
	private static Scanner sc = new Scanner(System.in);
	public static Map<SupermarketProduct, Integer> spmap = new HashMap<>();
	 
	 
	/**
	 * 购买管理
	 * @return
	 */
	public Double Purchase() {
		Double sum = 0.0;
		while(true) {
			
			System.out.println("请选择菜单");
			System.out.println("1.购买商品\t2.删除商品\t3.结账\t4.返回主菜单");
			String str = sc.next();
			
			if ("1".equals(str)) {
				while (true){
					inputID();
					System.out.println("是否继续(y/n)");
					str = sc.next();
					if("n".equals(str)) break;
				}

			} 
			else if ("2".equals(str)) {
				deleteProduct();
				
			}else if("3".equals(str)) {
				if (spmap.isEmpty()) {
					System.out.println("购物车为空");
					Purchase();
				}
				System.out.println("商品\t\t单价\t\t数量");
				/**
				 * 显示购物车详细信息
				 */
				for (SupermarketProduct sp1 : spmap.keySet()) {
					System.out.println(sp1.getProductName()+"\t"+sp1.getUnitPrice()+"\t"+spmap.get(sp1));
					sum+=(sp1.getUnitPrice()*(sp1.getCount()/10)*spmap.get(sp1));
				}
				System.out.println("订单总额是" + String.format("%.2f",sum));
				break;
			}else if("4".equals(str)) {
				spmap.clear();
				Menu.menu();
				return null;
			}
		}return sum;
	}

	/**
	 * 输入商品ID，商品详细信息，并添加到购物车
	 */
	private static void inputID() {
		
		System.out.println("*****请输入商品ID*****");
		Integer id = sc.nextInt();
		//每个商品对象的信息
		
		SupermarketProduct sp = new SupermarketProduct();
		ResultSet rs = new SupermarketDaoImpl().searchProduct("ProductNumber", id);
		//判断是否有重复商品
		for (SupermarketProduct sp1 : spmap.keySet()) {
			//如果商品ID存在，就修改购买数量
			if(sp1.getProductNumber()==id) {
				Integer num = spmap.get(sp1);
				System.out.println(sp1.getProductName()+"已存在,剩余"+sp1.getNumberOfProducts()+"件，请输入购买数量");
				Integer i = sc.nextInt();
				if(isEnough(sp1.getProductName(), i+num)) {
					spmap.replace(sp1,(i+num));
					System.out.println("添加成功");
				}
				System.out.println("*****是否继续(y/n)*****");
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
			System.out.println("当前商品为"+sp.getProductName()+",剩余"+sp.getNumberOfProducts()+"件,请输入购买数量");
			Integer num = sc.nextInt();
			//添加购物车
			if(isEnough(sp.getProductName(), num)) {
				spmap.put(sp, num);
			}
			else {
				System.err.println("数量不足");
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * 判断商品是否存在
	 * @param ProductName	商品名称
	 * @param num	要购买的数量
	 * @return
	 */
	private  static boolean isEnough(String ProductName,Integer num ) {
		ResultSet rs = new SupermarketDaoImpl().searchProduct("ProductName", ProductName);
		boolean bool = false;
		try {
			if(!rs.next()) {
				System.err.println("商品不存在");
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
	 * 从购物车删除商品
	 */
	private static void deleteProduct() {
		Map<Integer, SupermarketProduct> map= showGouwuche();
		while(!showGouwuche().isEmpty()) {

			System.out.println("*****请输入要删除的商品编号*****");
			Integer i = sc.nextInt();
			System.out.println("是否删除"+map.get(i).getProductName()+"(y/n)");
			String str = sc.next();
			if("y".equals(str)) {
				System.out.println("*****请输入删除的数量*****");
				Integer num = sc.nextInt();
				if(num>spmap.get(map.get(i))) {
					System.out.println("*****数量超出范围，请重新输入*****");
				}else if(num<spmap.get(map.get(i))){
					System.out.println("删除成功,剩余"+(spmap.get(map.get(i))-num)+"件");
					spmap.put(map.get(i), spmap.get(map.get(i))-num);
					if(spmap.get(map.get(i))-num==0) return;
				}else {
					spmap.remove(map.get(i));
					System.out.println("*****删除商品成功*****");
					return;
				}
				System.out.println("是否继续(y/n)");
				str = sc.next();
				if("n".equals(str)) {
					break;
				}
			}else if("n".equals(str)) {
				break;
			}else System.err.println("输入有误，重新输入");
		}
		


	}
	
	private static Map<Integer, SupermarketProduct> showGouwuche() {
		System.out.println("当前购物车商品为");
		System.out.println("编号\t商品\t单价\t数量");
		Map<Integer, SupermarketProduct> map = new LinkedHashMap<>();
		for (SupermarketProduct sp1 : spmap.keySet()) {
			map.put(sp1.getProductNumber(), sp1);
			System.out.println(sp1.getProductNumber()+"\t"+sp1.getProductName()+"\t"+sp1.getUnitPrice()+"\t"+spmap.get(sp1));
		}
		return map;
	}
	
	
	
}

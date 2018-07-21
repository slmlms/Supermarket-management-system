package com.ming.dao;


import java.sql.ResultSet;

import com.ming.dao.bean.OrderDetails;
import com.ming.dao.bean.OrderInformation;
import com.ming.dao.bean.SupermarketProduct;
import com.ming.dao.bean.SupermarketTypes;
import com.ming.dao.bean.SupermarketVIP;

public interface SupermarketDAO {

	void add(SupermarketProduct sp);		//������Ʒ��Ϣ
	void add(SupermarketTypes st);		//������Ʒ����
	void add(SupermarketVIP svip);		//���ӻ�Ա��Ϣ
	void add(OrderInformation oi);			//���Ӷ�����Ϣ
	void add(OrderDetails od);				//���Ӷ�������
	
	
	void delete(SupermarketProduct sp, Integer ProductNumber);	//ɾ����Ʒ��Ϣ
	void delete(SupermarketTypes st, Integer TypesID);	//ɾ����Ʒ����
	void delete(SupermarketVIP svip, Integer VIPNumber);	//ɾ����Ա��Ϣ
	
	
	ResultSet search(String sql);	//��ѯ��Ϣ

	
	
	void update(String sql);	//�޸���Ʒ��Ϣ
	
		
	
	
	
}

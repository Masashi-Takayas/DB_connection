package dao;

import java.sql.Connection;
import java.util.List;

import entity.Product;
import util.DbUtil;

public class DbExam5 {
	public static void main(String[] args) {
		
		ProductDao productDao;
		Connection connection;

		connection = DbUtil.getConnection();
//		connection.setAutoCommit(false);

		productDao = new ProductDao(connection);
		
		List<Product> list = productDao.findAll();

	    Product newProduct = new Product(null, "ボールペン", 200);
		productDao.register(newProduct);

		for(Product i:list) {
			System.out.println("product_id:"+i.getProductId()+",product_name:"+i.getProductName()+",price:"+i.getPrice());
		}
	}
}


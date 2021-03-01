package com.igeek.ch26.dao.xml;

public interface IBookShopDao {

	//根据bookId获取书的价格
	public int selectPrice(int bookId);
	//根据bookId更新库存
	public void updateStock(int bookId);
	//根据username,price更新账户余额
	public void updateBalance(String username, int price);
	
}

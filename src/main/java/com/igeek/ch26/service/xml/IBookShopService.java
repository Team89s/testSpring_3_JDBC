package com.igeek.ch26.service.xml;

public interface IBookShopService {

	//通过账户名及书号购买书
	public void purchase(String username, int bookId);
}

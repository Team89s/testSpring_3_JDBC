package com.igeek.ch23.service;

import java.io.IOException;

public interface IBookShopService {

	//通过账户名及书号购买书
	public void purchase(String username, int bookId) throws IOException;
}

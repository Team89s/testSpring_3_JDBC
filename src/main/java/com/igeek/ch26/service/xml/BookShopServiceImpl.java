package com.igeek.ch26.service.xml;

import com.igeek.ch26.dao.xml.IBookShopDao;

public class BookShopServiceImpl implements IBookShopService {
	private IBookShopDao bookShopDao;
	
	public void setBookShopDao(IBookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	
	@Override
	public void purchase(String username, int bookId) {
		int price = bookShopDao.selectPrice(bookId);
		bookShopDao.updateStock(bookId);
		bookShopDao.updateBalance(username, price);
	}

}

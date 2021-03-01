package com.igeek.ch26.service.xml;

import java.util.List;


public class CashierServiceImpl implements ICashierService {
	private IBookShopService bookShopService;
	
	public void setBookShopService(IBookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	@Override
	public void cash(String username, List<Integer> bookIds) {
		for (Integer bookId : bookIds) {
			bookShopService.purchase(username, bookId);
		}
	}

}

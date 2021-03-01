package com.igeek.ch23.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*@Transactional*/
@Service("cashierService")
public class CashierServiceImpl implements ICashierService {
	@Resource
	private IBookShopService bookShopService;
	
	//添加事务管理
	@Transactional
	@Override
	public void cash(String username, List<Integer> bookIds) throws IOException {
		for (Integer bookId : bookIds) {
			bookShopService.purchase(username, bookId);
		}
	}

}

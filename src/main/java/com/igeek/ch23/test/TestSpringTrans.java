package com.igeek.ch23.test;

import java.io.IOException;
import java.util.Arrays;

import com.igeek.ch23.dao.IBookShopDao;
import com.igeek.ch23.service.IBookShopService;
import com.igeek.ch23.service.ICashierService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringTrans {

	private ApplicationContext ac;
	private IBookShopDao bookShopDao;
	private IBookShopService bookShopService;
	private ICashierService cashierService;
	
	{
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = (IBookShopDao)ac.getBean("bookShopDao");
		bookShopService = (IBookShopService)ac.getBean("bookShopService");
		cashierService = (ICashierService)ac.getBean("cashierService");
	}
	
	@Test
	public void testSelectPrice() {
		int price = bookShopDao.selectPrice(1);
		System.out.println(price);
	}
	
	@Test
	public void testUpdateStock() {
		bookShopDao.updateStock(1);
	}
	
	@Test
	public void testUpdateBalance() {
		bookShopDao.updateBalance("张三", 40);
	}
	
	@Test
	public void testPurchase() throws IOException {
		//可能会出现一个情况,当余额不足时购买书,余额没变,但库存依旧减少
		bookShopService.purchase("张三", 1);
	}
	
	@Test
	public void testCash() throws IOException {
		//可能会出现一种情况,当余额不足购买所有书时,会事务回滚,一本书都买不成功
		cashierService.cash("张三", Arrays.asList(1,2));
	}


}

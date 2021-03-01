package com.igeek.ch26.test.xml;

import java.util.Arrays;

import com.igeek.ch26.dao.xml.IBookShopDao;
import com.igeek.ch26.service.xml.IBookShopService;
import com.igeek.ch26.service.xml.ICashierService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSpringTrans {

	private ApplicationContext ac;
	private IBookShopDao bookShopDao;
	private IBookShopService bookShopService;
	private ICashierService cashierService;
	
	{
		ac = new ClassPathXmlApplicationContext("applicationContext_xml.xml");
		bookShopDao = (IBookShopDao)ac.getBean("bookShopDao");
		bookShopService = (IBookShopService)ac.getBean("bookShopService");
		cashierService = (ICashierService)ac.getBean("cashierService");
	}
	
	@Test
	public void testPurchase() {
		//可能会出现一个情况,当余额不足时购买书,余额没变,但库存依旧减少
		bookShopService.purchase("张三", 1);
	}
	
	@Test
	public void testCash() {
		//可能会出现一种情况,当余额不足购买所有书时,会事务回滚,一本书都买不成功
		cashierService.cash("张三", Arrays.asList(1,2));
	}


}

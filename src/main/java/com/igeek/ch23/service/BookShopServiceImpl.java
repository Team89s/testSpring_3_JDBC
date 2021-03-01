package com.igeek.ch23.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;

import com.igeek.ch23.dao.IBookShopDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements IBookShopService {
	@Resource
	private IBookShopDao bookShopDao;
	
	
	/**
	 * 支持注解式事务管理  @Transactional
	 * 
	 * 1.事务的传播行为：当前的事务方法被另外一个事务方法调用时，如何使用事务
	 * 使用propagation属性指定事务的传播行为
	 * propagation=Propagation.REQUIRED  默认传播行为，若有事务在运行，当前的方法就在这个事务内运行；否则启动新事务，并在自己的事务内运行。
	 * propagation=Propagation.REQUIRES_NEW 该方法必须启动一个新事务，并在自己的事务内运行； 如果有事务在运行， 就应该先挂起它。
	 * 
	 * 2.isolation 属性中设置隔离级别
	 * isolation=Isolation.READ_COMMITTED  读提交
	 * 
	 * 3.rollbackFor:   遇到异常时必须进行回滚
	 *   noRollbackFor: 遇到异常时不必进行回滚
	 * rollbackFor={IOException.class,SQLException.class}
	 * noRollbackFor={ArithmeticException.class}
	 * 
	 * 4.超时事务属性: timeout属性，单位s秒
	 * timeout=3
	 * 
	 * 5.只读事务属性: 查询时，readOnly属性为true；增删改，readOnly属性为false
	 * readOnly=false  或   readOnly=true
	 * @throws IOException 
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.READ_COMMITTED,
			rollbackFor={IOException.class,SQLException.class},
			noRollbackFor={ArithmeticException.class},
			readOnly=false,
			timeout=3)
	@Override
	public void purchase(String username, int bookId) throws IOException {
		//配合timeout测试
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int price = bookShopDao.selectPrice(bookId);
		bookShopDao.updateStock(bookId);
		bookShopDao.updateBalance(username, price);

		/**
		 * 默认情况下，一旦发生运行期异常，则会回滚事务
		 * 默认情况下，一旦出现编译期异常，则不会回滚事务
		 */
		//此时发生数学异常
		//int i = 10/0;
		
		//此时发生IOException
		//throw new IOException("出现IO异常了！！！");
	}

}

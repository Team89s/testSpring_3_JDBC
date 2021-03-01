package com.igeek.ch23.dao;

import javax.annotation.Resource;

import com.igeek.ch23.exception.AccountException;
import com.igeek.ch23.exception.BookStockException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements IBookShopDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int selectPrice(int bookId) {
		String sql = "select price from t_book where bookId = ?";
		Integer price = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
		return price;
	}

	@Override
	public void updateStock(int bookId) {
		String s = "select stock from t_stock where bookId=?";
		int stock = jdbcTemplate.queryForObject(s, Integer.class, bookId);
		if(stock<=0){
			throw new BookStockException("库存不足!!!");
		}
		
		String sql = "update t_stock set stock=stock-1 where bookId=?";
		jdbcTemplate.update(sql, bookId);
	}

	@Override
	public void updateBalance(String username, int price) {
		String s = "select balance from t_account where username=?";
		Integer balance = jdbcTemplate.queryForObject(s, Integer.class, username);
		if(balance<price){
			throw new AccountException("账户余额不足!!!");
		}
		
		String sql = "update t_account set balance=balance-? where username=?";
		jdbcTemplate.update(sql, price,username);
	}

}

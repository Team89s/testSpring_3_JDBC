package com.igeek.ch22.dao;

import javax.annotation.Resource;

import com.igeek.ch22.entity.Emp;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	//查询方法
	public Emp select(Integer id){
		String sql = "select * from t_emp where id=?";
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<Emp>(Emp.class);
		Emp emp = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return emp;
	}
}

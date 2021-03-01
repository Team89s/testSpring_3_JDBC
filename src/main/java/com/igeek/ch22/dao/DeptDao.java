package com.igeek.ch22.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.igeek.ch22.entity.Dept;
import com.igeek.ch22.entity.Emp;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDao extends JdbcDaoSupport {
	@Resource
	private NamedParameterJdbcTemplate namedParam;
	
	@Resource
	public void setDataSource2(DataSource dataSource){
		//不可重写
		this.setDataSource(dataSource);
	}
	
	/*@Resource
	private JdbcTemplate jdbcTemplate;*/
	
	public Dept select(Integer id){
		String sql = "select * from t_dept where id=?";
		RowMapper<Dept> rowMapper = new BeanPropertyRowMapper<Dept>(Dept.class);
		Dept dept = this.getJdbcTemplate().queryForObject(sql, rowMapper, id);
		return dept;
	}
	
	
	public void insert(Emp emp){
		String sql="insert into t_emp(name,email,dept_id) values(:name,:email,:deptId)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(emp);
		namedParam.update(sql, paramSource);
	}
}

package com.igeek.ch22;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.igeek.ch22.dao.DeptDao;
import com.igeek.ch22.dao.EmpDao;
import com.igeek.ch22.entity.Emp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/*
 * JdbcTemplate
 * 单元测试的类
 */
public class JDBCTest {

	private ApplicationContext ac;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private EmpDao empDao;
	private DeptDao deptDao;
	private NamedParameterJdbcTemplate namedParam;
	
	{
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dataSource = ac.getBean(DataSource.class);
		jdbcTemplate = (JdbcTemplate)ac.getBean("jdbcTemplate");
		empDao = (EmpDao)ac.getBean("empDao");
		deptDao = (DeptDao)ac.getBean("deptDao");
		namedParam = ac.getBean(NamedParameterJdbcTemplate.class);
	}
	
	//测试数据源DataSource
	@Test
	public void testDataSource() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
	
	/*
	 * 1.需求:根据id更新员工姓名
	 * update(String sql, Object... args) 
	 * 第一个参数为sql语句;第二个参数及之后都是给?传值,当不需要传参数时可以直接不写
	 * 此项update()方法可以为insert,update,delete操作
	 */
	@Test
	public void testUpdate(){
		String sql = "update t_emp set name = ?,email=? where id= ?";
		jdbcTemplate.update(sql, "高强1","gaoqiang@123.com",1);
		jdbcTemplate.update(sql, "高强2","gaoqiang@123.com",4);
	}
	
	/*
	 * 2.需求:批量插入数据
	 * batchUpdate(String sql, List<Object[]> batchArgs)
	 * 第一个参数为sql语句;第二个参数批量插入的数据List集合类型
	 * 此项batchUpdate()方法可以为insert,update,delete操作
	 */
	@Test
	public void testBatchUpdate(){
		String sql = "insert into t_emp(name,email,dept_id) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[]{"aa","aa@123.com",1});
		batchArgs.add(new Object[]{"bb","bb@123.com",2});
		batchArgs.add(new Object[]{"cc","cc@123.com",3});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/*
	 * 3.查询需求:
	 * 1).单行记录查询:根据id查询员工信息
	 * queryForObject(String sql, RowMapper<Emp> rowMapper, Object... args)
	 * 第一个参数sql语句;
	 * 第二个参数new BeanPropertyRowMapper(Class class):Class 查询的单行记录类型
	 * 第三个参数给?传值的一个或多个参数;当不需要传参数时可以直接不写
	 */
	@Test
	public void testSelectEmpById(){
		String sql = "select * from t_emp where id=?";
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<Emp>(Emp.class);
		Emp emp = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(emp);
	}

	/*
	 * 3.查询需求:
	 * 2).多行记录查询:根据指定条件查询员工信息
	 * query(String sql, RowMapper<Emp> rowMapper, Object... args)
	 * 第一个参数sql语句;
	 * 第二个参数new BeanPropertyRowMapper(Class class):Class 查询的单行记录类型
	 * 第三个参数给?传值的一个或多个参数;当不需要传参数时可以直接不写
	 */
	@Test
	public void testSelectEmp(){
		String sql = "select * from t_emp where id>?";
		RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<Emp>(Emp.class);
		List<Emp> empList = jdbcTemplate.query(sql, rowMapper,7);
		System.out.println(empList.size());
	}
	
	/*
	 * 3.查询需求:
	 * 3).单值查询:根据id查询员工姓名
	 * queryForObject(String sql, Class<String> requiredType, Object... args)
	 * 第一个参数:sql语句;
	 * 第二个参数:单个数值的类类型
	 * 第三个参数给?传值的一个或多个参数;当不需要传参数时可以直接不写
	 */
	@Test
	public void testSelectNameById(){
		/*String sql = "select name from t_emp where id=?";
		String name = jdbcTemplate.queryForObject(sql, String.class, 8);
		System.out.println(name);*/
		
		String sql = "select dept_id from t_emp where id=?";
		Integer deptId = jdbcTemplate.queryForObject(sql, Integer.class, 8);
		System.out.println(deptId);
	}
	
	@Test
	public void testEmpDaoSelect(){
		System.out.println(empDao.select(1));
	}
	
	@Test
	public void testDeptDaoSelect(){
		System.out.println(deptDao.select(1));
	}
	
	/*
	 * 使用具名参数  :参数名
	 * Map
	 */
	@Test
	public void testNamed1(){
		String sql="insert into t_emp(name,email,dept_id) values(:n,:e,:d)";
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("e", "chenmin@123.com");
		paramMap.put("n", "chenmin");
		paramMap.put("d", "1");
		namedParam.update(sql, paramMap);
	}
	
	/*
	 * 使用具名参数  :参数名(要求参数名必须与Emp类中的属性名称一致)
	 * SqlParameterSource
	 */
	@Test
	public void testNamed2(){
		/*String sql="insert into t_emp(name,email,dept_id) values(:name,:email,:deptId)";
		Emp emp = new Emp("肖磊","xiaolei@123.com",1);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(emp);
		namedParam.update(sql, paramSource);*/
		
		Emp emp = new Emp("肖磊1","xiaolei@123.com",1);
		deptDao.insert(emp);
	}
}

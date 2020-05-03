package com.lokesh.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lokesh.StoredProcConfig;
import com.lokesh.Dao.UserDao;
import com.lokesh.model.User;
import java.sql.CallableStatement;

/**
 * @author lokesh
 *
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StoredProcConfig StoredProcConfig;

	/**
	 *this function is for query the database stored procedure and 
	 * get json response as a result of stored procedure using simpleJdbcCall
	 *
	 */
	@Override
	public Map<String, Object> executeProc1() {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.getDataSource()).withSchemaName("test")
				.withProcedureName("fetch_student");
		Map<String, Object> result = null;
		result = jdbcCall.execute();
		System.out.println("result is ========" + result);
		System.out.println("==================" + result.get("#result-set-1"));
		String s = result.get("#result-set-1").toString();
		String s1 = s.substring(s.indexOf("=") + 1).trim();

		System.out.println("s1 is ======" + s1);

		convertJsonToObject(s1);
		return result;
	}

	/**
	 *this function is for query the database stored procedure and 
	 * get json response as a result of stored procedure using callableStatement
	 *
	 */
	@Override
	public Map<String, Object> executeProc2() {
		try {
			Connection connection = this.getDataSource().getConnection();
//			CallableStatement callableStatement = connection.prepareCall("call test.fetch_student");
			CallableStatement callableStatement = connection.prepareCall(StoredProcConfig.getFetchStudent());
			ResultSet rs = callableStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 *this function is for query the database stored procedure which has one input parameter and 
	 * get json response as a result of stored procedure using callableStatement
	 *
	 */
	@Override
	public Map<String, Object> executeProc3() {
		try {
			Connection connection = this.getDataSource().getConnection();
			CallableStatement callableStatement = connection.prepareCall(StoredProcConfig.getFetchStudentWithInputParam());
//			callableStatement.setString("first_name", "lokesh");
			callableStatement.setString(1, "lokesh");
			ResultSet rs = callableStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 *this function is for query the database stored procedure which has one input parameter and 
	 * get json response as a result of stored procedure using SimpleJdbcCall
	 *
	 */
	@Override
	public Map<String, Object> executeProc4() {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(this.getDataSource()).withSchemaName("test")
				.withProcedureName("fetch_student_with_input_param");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", "lokesh");
		Map<String, Object> result = null;
		result = jdbcCall.execute(paramMap);
		System.out.println("result is =======" + result);
		return result;
	}


	
	/**
	 * @param inputJson 
	 * this function is for converting input json to the user class object
	 */
	private void convertJsonToObject(String inputJson) {
		try {
			User[] userArray = objectMapper.readValue(inputJson.toString(), User[].class);
			List<User> userList = Arrays.asList(userArray);
			System.out.println("userlist is " + userList);
			convertObjectToJson(userList);
		} catch (IOException e) {
			System.out.println("inside catch block");
			e.printStackTrace();
		}
	}



	/**
	 * @param userList
	 * this function converts list of a object to json 
	 */
	private void convertObjectToJson(List<User> userList) {
		try {

			String jsonResult = objectMapper.writeValueAsString(userList);
			System.out.println("jsonresult from convertObjectToJson is ===" + jsonResult);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

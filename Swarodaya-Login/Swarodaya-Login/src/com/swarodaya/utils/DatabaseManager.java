package com.swarodaya.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;

public class DatabaseManager {

	private static final String CLASSNAME = " [DatabaseManager] ";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.6.85.2:3306/newswarodaya", "adminEXCQ5WQ", "UjDYWmR2-M9y");
			//con = DriverManager.getConnection("jdbc:mysql://10.103.1.107:3306/newswarodaya", "admin", "admin");
			
		} catch (Exception e) {
			con = null;
			e.printStackTrace();
		}
		return con;
	}
	
	public ResultSet execute(String strQuery) {
		ResultSet resSet = null;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DatabaseManager.getConnection();
			if(connection != null) {

				System.out.println(CLASSNAME + "Going to fire query : " + strQuery);
				
				statement = connection.createStatement();
				resSet = statement.executeQuery(strQuery);
			}
		}catch(Exception e){
			System.out.println(CLASSNAME + "Exception while executing query : " + e.getMessage());
			e.printStackTrace();
		}
		return resSet;
	}
	
	public int executeUpdate(String strQuery) {
		int executeUpdate = 0;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DatabaseManager.getConnection();
			if(connection != null) {

				System.out.println(CLASSNAME + "Going to fire query : " + strQuery);
				
				statement = connection.createStatement();
				executeUpdate = statement.executeUpdate(strQuery);
			}
		}catch(Exception e){
			System.out.println(CLASSNAME + "Exception while executing query : " + e.getMessage());
			e.printStackTrace();
		}
		return executeUpdate;
	}
	
	public int executeUpdate(String strQuery, Collection parameter) {
		int executeUpdate = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = DatabaseManager.getConnection();
			
			if(connection != null) {

				System.out.println(CLASSNAME + "Going to fire query : " + strQuery + " || paramters : " + parameter);
	
				connection.prepareStatement(strQuery);
				setParameter(pstmt,parameter);
				
				Statement statement = connection.createStatement();
				executeUpdate = statement.executeUpdate(strQuery);
			}
		}catch(Exception e){
			System.out.println(CLASSNAME + "Exception while executing query : " + e.getMessage());
			e.printStackTrace();
		}
		return executeUpdate;
	}
	
	private void setParameter(PreparedStatement pstmt, Collection parameter) throws SQLException{
		if( parameter != null && !parameter.isEmpty()){
			Iterator ite = parameter.iterator();
			int count = 0;
			while( ite.hasNext() ){
				count++;

				Object obj =  ite.next();

				if( obj instanceof java.sql.Date ){
					pstmt.setDate(count,(java.sql.Date)obj);    
				}else if( obj instanceof java.util.Date ){
					pstmt.setDate(count,new java.sql.Date(((java.util.Date)obj).getTime()));    
				}else if( obj instanceof java.sql.Timestamp ){
					pstmt.setTimestamp(count,(java.sql.Timestamp)obj);    
				}else if( obj instanceof Integer ){
					pstmt.setInt(count,((Integer)obj).intValue());    
				}else if( obj instanceof Float){
					pstmt.setFloat(count,((Float)obj).floatValue());    
				}else if( obj instanceof Double){
					pstmt.setDouble(count,((Double)obj).doubleValue());    
				}else if( obj instanceof Long){
					pstmt.setDouble(count,((Long)obj).longValue());    
				}else if( obj instanceof String){
					pstmt.setString(count,(String)obj);    
				}            
			}    
		}
	}
	
}
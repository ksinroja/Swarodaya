package com.swarodaya.base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swarodaya.utils.DatabaseManager;

public abstract class BaseController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = " [BaseController] ";
	
	protected abstract void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException;
	
	protected abstract void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException;
	
	private static DatabaseManager databaseManager = new DatabaseManager();
	
	public ResultSet execute(String strQuery) {
		return databaseManager.execute(strQuery);
	}
	
	public int executeUpdate(String strQuery) {
		return databaseManager.executeUpdate(strQuery);
	}
	
	public int executeUpdate(String strQuery, Collection parameter) {
		return databaseManager.executeUpdate(strQuery, parameter);
	}
	
}

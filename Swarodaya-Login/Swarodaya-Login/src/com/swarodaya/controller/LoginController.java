package com.swarodaya.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swarodaya.base.BaseController;
import com.swarodaya.utils.DatabaseManager;

public class LoginController extends BaseController
{
	private static final long serialVersionUID = 1L;
	private static final String CLASSNAME = " [LoginController] ";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(CLASSNAME + "Started");
		try {
			boolean bLoginSuccess = true;
			String strMessage = "";
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");

			if(userId != null && password != null) {
				Connection connection = DatabaseManager.getConnection();
				if(connection != null) {
					Statement statement = connection.createStatement();
					StringBuffer query = new StringBuffer("select * from tblmstaff where username='" + userId + "'");

					ResultSet resultSet = statement.executeQuery(query.toString());
					if(resultSet.next() && "CST01".equals(resultSet.getString("commonstatusid"))) {
						if (password.equals(resultSet.getString("password"))) {
							System.out.println(CLASSNAME + "Login successfull");
							response.sendRedirect(request.getContextPath() + "/jsp/admin/home.jsp");
						}else {
							bLoginSuccess = false;
							strMessage = "Username and Password does not match";
						}
					}else {
						bLoginSuccess = false;
						strMessage = "Invalid User";
					}
				}else {
					bLoginSuccess = false;
					strMessage = "Unable to connect with system";
				}
			}else {
				bLoginSuccess = false;
				strMessage = "Provide Username and Password";
			}

			if(!bLoginSuccess) {
				System.out.println(CLASSNAME + "Login failed");
				response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp?message=" + strMessage);
			}
		}catch(Exception e) {
			System.out.println(CLASSNAME + "Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
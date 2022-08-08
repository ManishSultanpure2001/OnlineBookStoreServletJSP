package com.online.bookstore.servlet;

import java.sql.*;
import com.online.bookstore.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String userEmail = request.getParameter("loginUserEmail");
		String userPasword = request.getParameter("loginUserPassword");
		UserEmail userEmail2 = new UserEmail();
		userEmail2.setUserEmail(userEmail);
		try {
			Connection con = DatabaseConnectivity.dbConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"Select * from registration where email='" + userEmail + "' and password='" + userPasword + "'");

			if (rs.next()) {

				if (rs.getString(2).equals(userEmail) && rs.getString(3).equals("Admin")
						&& rs.getString(4).equals(userPasword)) {
					response.sendRedirect("AdminHome.jsp");
				} else {
					request.setAttribute("userEmail", userEmail);
					request.getRequestDispatcher("UserHomeProfile.jsp").forward(request, response);
				}
			} else {
				response.sendRedirect("Login.jsp");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

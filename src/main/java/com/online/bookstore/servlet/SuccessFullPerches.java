package com.online.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.bookstore.DatabaseConnectivity;

public class SuccessFullPerches extends HttpServlet {
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String name = request.getParameter("bookName");
		String price = request.getParameter("bookPrice");
		String message = "Thanks for Having Interest in our Store You have successfully purchased [ " + name + "  ] Book"
				+ "   Whose Price is    [ " + price + " ]";

		String queryString = "Insert into userbuydata values(?,?,?)";
		System.out.println("Registration.doGet().before connection calling ");
		try {
			Connection con = DatabaseConnectivity.dbConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryString);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, price);
			int insertResponse = preparedStatement.executeUpdate();
			if (insertResponse > 0) {
				Registration.emailSend(message, email);
				out.print("<html>");
				out.print("<head>");
				out.print("<link rel='stylesheet' href='MainBookStoreCss.css'>");
				out.print("</head>");
				out.print("<body>");
				out.print("<div id='data'>");
				out.print("<center>");
				out.print("<h1 class='AddBookHeading'>Congratulations You have successFully purchased '"+name+"' Book</h1>");
				out.print("</center>");
				out.print("</div>");
				out.print("</body>");
				out.print("</html>");
			} else {
				System.out.println("Data not inserted");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
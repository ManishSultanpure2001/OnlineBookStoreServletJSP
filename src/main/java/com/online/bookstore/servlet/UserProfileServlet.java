package com.online.bookstore.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.bookstore.DatabaseConnectivity;

@MultipartConfig(maxFileSize = 16177215)
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String bookName;
	String htmlRespone;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String queryString = "Select * from bookdetails";

		try (Connection con = DatabaseConnectivity.dbConnection();) {

			PreparedStatement preparedStatement = con.prepareStatement(queryString);
			ResultSet rs = preparedStatement.executeQuery();
			out.print("<html>");
			out.print("<head>");
			out.print("<link rel='stylesheet' href='MainBookStoreCss.css'>");
			out.print("</head>");
			out.print("<body>");
			out.print("<div id='data'>");
			out.print("<center>");
			out.print("<h2 class='AddBookHeading'>Welcome in Profile Section</h2>");
			out.print("</center>");
			out.print("<div id='mymenu'>");
			out.print("<ul>");
			out.print("<li><a href='AdminHome.jsp'>Home Page</a></li>");
			out.print("<li><a href='AddBook.jsp'>Add Book</a></li>");
			out.print("<li><a href='DeleteBook.jsp'>Delete Book</a></li>");
			out.print("<li><a href='Login.jsp'>Logout</a></li>");
			out.print("</ul>");
			out.print("</div>");
			out.println("<center>");
			out.println("<form>");
			out.println("<table cellpadding='15px'>");
			out.println("<tr>");
			out.println("<td> Available Books</td>");
			out.println("<td><select><td>");
			while (rs.next()) {
				out.println("<td><option value =" + rs.getString("bookname") + "</option></td>");
			}
			out.println("<td></select></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Name of Book</td>");
			out.println("<td><input type='text' name='bookName' placeholder='Enter Name'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Book pdf upload</td>");
			out.println("<td><input type='file' id='myFile' name='fileName'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Book image</td>");
			out.println("<td><input type='file' id='myImage' name='imageName'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Enter Price</td>");
			out.println("<td><input type='number' name='bookPrice' placeholder='Enter Book Price'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Enter Author Name</td>");
			out.println("<td><input type='text' name='authorName' placeholder='Enter Author Name'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align='center' colspan='2'><input class='B' type='submit' value='Update Book'></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</center>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
package com.online.bookstore.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.online.bookstore.DatabaseConnectivity;

@MultipartConfig(maxFileSize = 16177215)
public class ViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String bookName, authorName;
	String htmlRespone;
	OutputStream os;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			out.print("	<div id='data'>");

			out.print("	<center>");
			out.print("<h2 class='AddBookHeading'>Welcome in View Book Section</h2>");
			out.print("	</center>");
			out.print("<div id='mymenu'>");
			out.print("<ul>");
			out.print("<li><a href='AdminHome.jsp'>Home Page</a></li>");
			out.print("<li><a href='AddBook.jsp'>Add Book</a></li>");
			out.print("<li><a href='UpdateBook.jsp'>Update Book Detail</a></li>");
			out.print("<li><a href='DeleteBook.jsp'>Delete Book</a></li>");
			out.print("<li><a href='Login.jsp'>Logout</a></li>");
			out.print("</ul>");
			out.print("</div>");
			out.println("<center>");
			out.println("<table cellpadding='15px'>");
			out.println("<tr>");

			out.println("<td>BookName</td>");
			out.println("<td>BookPDF</td>");
			out.println("<td>BookImage</td>");
			out.println("<td>BookPrice</td>");
			out.println("<td>BookAuthor</td>");
			out.println("</tr>");
			while (rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getString(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td>");
				out.println("<td>" + rs.getString(5) + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</center>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

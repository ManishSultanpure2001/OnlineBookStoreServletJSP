package com.online.bookstore.servlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.*;

import com.online.bookstore.DatabaseConnectivity;

public class BookUpdateServlet extends HttpServlet {

	String output = "false";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selected = request.getParameter("selectedIteam");
		String bookName = request.getParameter("updateName");
		String bookPDF = request.getParameter("updatePDF");
		String BookImage = request.getParameter("updateImage");
		String bookPrice = request.getParameter("updatePrice");
		String bookAuthor = request.getParameter("updateAuthor");
		String queryString = "UPDATE bookdetails SET  bookname=? ,bookpdf=? , pic=? ,bookprice=? , authorname=?  WHERE  bookname=?";
		try {
			Connection con = DatabaseConnectivity.dbConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryString);
			preparedStatement.setString(1, bookName);
			preparedStatement.setString(2, bookPDF);
			preparedStatement.setString(3, BookImage);
			preparedStatement.setString(4, bookPrice);
			preparedStatement.setString(5, bookAuthor);
			preparedStatement.setString(6, selected);
			int insertResponse = preparedStatement.executeUpdate();
			if (insertResponse > 0) {

				response.sendRedirect("UpdateBook.jsp?output=true");
			} else {

				response.sendRedirect("UpdateBook.jsp?output=false");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

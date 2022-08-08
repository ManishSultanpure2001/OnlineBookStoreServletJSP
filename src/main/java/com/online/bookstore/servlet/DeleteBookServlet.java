package com.online.bookstore.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.online.bookstore.DatabaseConnectivity;

@MultipartConfig(maxFileSize = 16177215)
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String bookName, authorName;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		bookName = request.getParameter("bookName");

		authorName = request.getParameter("authorName");
		String queryString = "DELETE FROM bookdetails WHERE bookname=? and authorName=?";

		try (Connection con = DatabaseConnectivity.dbConnection();) {

			PreparedStatement preparedStatement = con.prepareStatement(queryString);
			preparedStatement.setString(1, bookName);
			preparedStatement.setString(2, authorName);

			int responceGet = preparedStatement.executeUpdate();
			if (responceGet > 0) {
				File image = new File(
						"C:\\Users\\salon\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\OnlineBookStore\\src\\main\\webapp\\Image\\"
								+ bookName + ".png");
				File pdf = new File(
						"C:\\Users\\salon\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\OnlineBookStore\\src\\main\\webapp\\PDF\\"
								+ bookName + ".pdf");
				if (image.delete() && pdf.delete()) {
					System.out.print("deleteed");
				}
				response.sendRedirect("DeleteBook.jsp?output=true");
			} else {
				System.out.println("not Success");
				response.sendRedirect("DeleteBook.jsp?output=false");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}

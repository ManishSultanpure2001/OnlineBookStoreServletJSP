package com.online.bookstore.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.online.bookstore.DatabaseConnectivity;

@MultipartConfig(maxFileSize = 16177215)
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String bookName, bookPrice, authorName;
	Part bookPDF, bookImage;
	InputStream inputStreamPDF, inputStreamImage;
	String htmlRespone;
	private FileOutputStream fileOutputStreamImage, fileOutputStreamPDF;
	int bytesImage = 0;
	int bytesPDF = 0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		bookName = request.getParameter("addBookName");
		bookPDF = request.getPart("addFileName");
		bookImage = request.getPart("addImageName");
		bookPrice = request.getParameter("addBookPrice");
		authorName = request.getParameter("addAuthorName");
		System.out.println(request.getParameter("addBookName"));
		System.out.println(bookPDF);
		System.out.println(bookImage);
		System.out.println(bookPrice);
		System.out.println(authorName);
		String queryString = "Insert into bookdetails values(?,?,?,?,?)";
		try (Connection con = DatabaseConnectivity.dbConnection();) {
			PreparedStatement preparedStatement = con.prepareStatement(queryString);
			this.fileStore(bookName);
			preparedStatement.setString(1, bookName);
			preparedStatement.setString(2, bookName + ".pdf");
			preparedStatement.setString(3, bookName + ".png");
			preparedStatement.setString(4, bookPrice);
			preparedStatement.setString(5, authorName);
			int responceGet = preparedStatement.executeUpdate();
			if (responceGet > 0) {
				System.out.print("Success");
				String success = "Successfull";
				request.setAttribute("message", success);
				response.sendRedirect("AddBook.jsp?output=true");
			} else {
				System.out.print("not Success");
				response.sendRedirect("AddBook.jsp?output=false");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void fileStore(String bookName) throws IOException {
		inputStreamImage = bookImage.getInputStream();
		inputStreamPDF = bookPDF.getInputStream();
		try {
			fileOutputStreamImage = new FileOutputStream(
					"C:\\Users\\salon\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\OnlineBookStore\\src\\main\\webapp\\Image\\"
							+ bookName + ".png");
			fileOutputStreamPDF = new FileOutputStream(
					"C:\\Users\\salon\\Documents\\workspace-spring-tool-suite-4-4.15.1.RELEASE\\OnlineBookStore\\src\\main\\webapp\\PDF\\"
							+ bookName + ".pdf");
			while ((bytesPDF = inputStreamPDF.read()) != -1) {

				fileOutputStreamPDF.write(bytesPDF);
			}
			while ((bytesImage = inputStreamImage.read()) != -1) {
				fileOutputStreamImage.write(bytesImage);
			}
		} finally {
			fileOutputStreamImage.close();
			fileOutputStreamPDF.close();
		}
	}
}

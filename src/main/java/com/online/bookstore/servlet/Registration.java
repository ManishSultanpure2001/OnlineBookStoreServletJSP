package com.online.bookstore.servlet;

import java.io.File;
import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import java.sql.*;
import java.util.Properties;

import javax.mail.Transport;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ha.backend.Sender;
import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;
import com.online.bookstore.DatabaseConnectivity;

public class Registration extends HttpServlet {

	private String userEmail, userName, userRole, userPassword;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		userName = request.getParameter("UserName");
		userEmail = request.getParameter("UserEmail");
		userRole = request.getParameter("UserRole");
		userPassword = request.getParameter("UserPassword");
		String queryString = "Insert into registration values(?,?,?,?)";
		try {
			Connection con = DatabaseConnectivity.dbConnection();
			PreparedStatement preparedStatement = con.prepareStatement(queryString);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userEmail);
			preparedStatement.setString(3, userRole);
			preparedStatement.setString(4, userPassword);
			int insertResponse = preparedStatement.executeUpdate();
			if (insertResponse > 0) {
				this.emailSend("Dear [" + userName
						+ "]Thank you for completing your registration with [Webkorps]This email serves as a confirmation that your account is activated and that you are officially a part of the [Online Book Store] family Enjoy",
						userEmail);
				response.sendRedirect("Login.jsp");
			} else {
				response.sendRedirect("RegisTration.jsp");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void emailSend(String message, String userEmail) {
		System.out.println("Preparing to send message");

		String subject = "Online Book Store: conformation";
		String from = "manishsultanpure538@gmail.com";
		sendAttachment(message, subject, userEmail, from);

	} // This is responsible to send the message with attachment

	private static void sendAttachment(String message, String subject, String to, String from) {
		// Variable for gmail.
		String host = "smtp.gmail.com"; // get the System properties
		Properties properties = System.getProperties();
		System.out.println("Properties= " + properties);
		// Setting important information to properties Object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");// 465 is default port number of g
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1:- to get the session Object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("manishsultanpure538@gmail.com", "upemktvnaqwifgnc");
			}
		});
		session.setDebug(true);

		// Step 2:- Compose the message
		try {
			MimeMessage mimeMessage = new MimeMessage(session); // from email
			mimeMessage.setFrom("manishsultanpure538@gmail.com"); // Adding resipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message); // Attachments +

			// FilePath

			String path = "E:\\BackgroundOfBook.jpg";
			MimeMultipart mimeMultipart = new MimeMultipart();

			// Text and File
			MimeBodyPart textMime = new MimeBodyPart();
			MimeBodyPart fileMime = new MimeBodyPart();
			try {
				textMime.setText(message);

				File file = new File(path);
				fileMime.attachFile(file);

				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);

			}

			catch (Exception e) {
				System.out.print("First");
				e.printStackTrace();
			}

			mimeMessage.setContent(mimeMultipart);
			// Step 3:- Send the message using Transport Class
			Transport.send(mimeMessage);
			System.out.println("Success Fully Send");
		} catch (Exception e) { // TODO: handle exception
			System.out.print("Second");
			e.printStackTrace();

		}

	}
}

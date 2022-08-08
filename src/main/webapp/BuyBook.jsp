<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.online.bookstore.DatabaseConnectivity"%>
<%!String bookname = "";
	String bookPrice = "";%>

<%
String bookname = request.getParameter("bookName");
try {
	Connection con = DatabaseConnectivity.dbConnection();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("Select * from bookdetails where bookname='" + bookname + "' ");
%>

<html>
<head>
<link rel='stylesheet' href='MainBookStoreCss.css'>
</head>
<body>
	<div id='data'>
		<center>
			<h2 class='AddBookHeading'>Welcome in Buy Book Section</h2>
		</center>
		<div id='mymenu'>
			<ul>
				<li><a href='UserHomeProfile.jsp'>Home Page</a></li>
				<li><a href='SearchBook.jsp'>Search Book</a></li>
				<li><a href=UserBuyBookData.jsp>Your Profile</a></li>
			</ul>
		</div>
		<center>
			<form action='SuccessFullPerches'>


				<%
				if (rs.next()) {
					bookname = rs.getString(1);
					bookPrice = rs.getString(4);
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(4));
				%>
				<%
				} else {
				%>

				<%
				String output = "false";
				response.sendRedirect("SearchBook.jsp?output=false");
				%>

				<%
				}
				con.close();
				} catch (Exception ee) {
				ee.printStackTrace();
				}
				%>

				<table cellpadding='15px'>
					<tr>
						<td>Enter Email FOr Conformation Mail</td>
						<td><input type='text' name='email'
							placeholder='Enter Email ID'></td>
					</tr>
					<tr>
						<td>Book Name</td>
						<td><input type='text' name='bookName' value=<%=bookname%>
							placeholder="Enter Name" readonly></td>
					</tr>
					<tr>
						<td>Book Price</td>
						<td><input type='number' name='bookPrice'
							value="<%=bookPrice%>" placeholder='Enter Book Price' readonly></td>
					</tr>
					<tr>
						<td align='center' colspan='2'><input class='B' type='submit'
							value='Buy Book'></td>
					</tr>
				</table>
			</form>
		</center>
	</div>
</body>
</html>
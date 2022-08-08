<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<!-- Css Code -->
<link rel="stylesheet" href="MainBookStoreCss.css">
</head>
<body>

	<div id="data">
		<center>
			<h1 class="AddBookHeading">
				Welcome in View Book Section
				<h1>
		</center>
		<div id="mymenu">
			<ul>
				<li><a href="AdminHome.jsp">Home Page</a></li>
				<li><a href="AddBook.jsp">Add Book</a></li>
				<li><a href="UpdateBook.jsp">Update Book Detail</a></li>
				<li><a href="DeleteBook.jsp">Delete Book</a></li>
				<li><a href='Login.jsp'>Logout</a></li>
			</ul>
		</div>
		<center>

			<form action="ViewBookServlet">
				<table cellpadding="15px">

					<tr>
						<td>Name of Book</td>
						<td><input type="text" name="bookName"
							placeholder="Enter Name of Book"></td>
					</tr>

					<tr>
						<td>Enter Author Name</td>
						<td><input type="text" name="authorName"
							placeholder="Enter Author Name"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input class="B" type="submit"
							value="View Book"></td>
					</tr>
				</table>
			</form>
		</center>
	</div>

</body>
</html>

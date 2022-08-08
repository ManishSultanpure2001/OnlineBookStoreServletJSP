<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.online.bookstore.DatabaseConnectivity"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<!-- Css Code -->
<link rel="stylesheet" href="MainBookStoreCss.css">

</head>
<body>
	<%
	String output = request.getParameter("output");
	if (output != null) {
		if (output.equals("true")) {
	%>
	<div>
		<h2 id="successHeading">
			"Success Fully Book Updated"
			<h2>
	</div>
	<%
	} else if (output.equals("false")) {
	%>
	<div>
		<h2 id="failHeading">
			"Try Again Book Not Updated"
			<h2>
	</div>
	<%
	}
	}
	%>
	<div id="data">
		<center>
			<h1 class="AddBookHeading">
				Welcome in Update Book Section
				<h1>
		</center>
		<div id="mymenu">
			<ul>
				<li><a href='AdminHome.jsp'>Home Page</a></li>
				<li><a href='AddBook.jsp'>Add Book</a></li>
				<li><a href='ViewBookServlet'>View Book</a></li>
				<li><a href='DeleteBook.jsp'>Delete Book</a></li>
				<li><a href='Login.jsp'>Logout</a></li>
			</ul>
		</div>
		<center>
			<form action="BookUpdateServlet">
			 
				<table cellpadding="15px">
					<tr>
						<td>Available Books</td>
						<td><select name="selectedIteam" onchange="demo()"
							id="comboSelectedValue">
							<option>Select Book</option>

								<%!String bookName = "";
	String bookPdf = "";
	String bookImage = "";
	String bookPrice = "";
	String bookAuthor = "";
	String selected = "";%>
								<%
								String queryString = "Select DISTINCT bookname from bookdetails";
								int i = 0;
								try (Connection con = DatabaseConnectivity.dbConnection();) {

									PreparedStatement preparedStatement = con.prepareStatement(queryString);
									ResultSet rs = preparedStatement.executeQuery();
									while (rs.next()) {
								%>

								<option><%=rs.getString(1)%></option>
								<%
								}
								%>
						</select></td>
						<%
						con.close();

						}

						catch (SQLException e) {

						e.printStackTrace();
						}
						%>


					</tr>
					<tr>
						<td>Name of Book</td>
						<td><input type="text" id="bookName" name="updateName"
							placeholder="Enter Name"></td>
					</tr>
					<tr>
						<td>Book pdf upload</td>
						<td><input type="file" id="bookPDF" name="updatePDF"></td>
						<td><img id="myImg" alt="Please select Any Book"width='100' height='100'></td>
						<td></a></td>;
					</tr>
					<tr>
						<td>Book image</td>

						<td><input type="file" id="bookImage" name="updateImage"></td>

					</tr>
					<tr>
						<td>Enter Price</td>
						<td><input type="number" id="bPrice" name="updatePrice"
							placeholder="Enter Book Price"></td>
					</tr>
					<tr>
						<td>Enter Author Name</td>
						<td><input type="text" id="aName" name="updateAuthor"
							placeholder="Enter Author Name"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input class="B" type="submit"
							value="Update Book"></td>
					</tr>
				</table>
			</form>
		</center>
	</div>

</body>
</html>

<script src="component/jquery/jquery.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="component/jquery/jquery.min.js" type="text/javascript"></script>
<script src="component/jquery.validate.min.js" type="text/javascript"></script>
<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	function demo() {

		var id = document.getElementById('comboSelectedValue').value;
		$
				.ajax({

					type : "GET",
					url : "Edit_returnAjax.jsp",
					data : {
						"id" : id
					},

					success : function(data) {
						data = data.replace(/(\r\n|\n|\r\t)/gm, "");
						data = data.trim();
						data = data.substring(1, data.length - 1);
						var t = data.split(",");

						document.getElementById("bookName").value = t[0];
						document.getElementById("myImg").src = "Image/" + t[0]
								+ ".png";
						var value = parseFloat(t[3]);
						document.getElementById("bPrice").value = value;
						document.getElementById("aName").value = t[4].trim();
						for (var i = 0; i < t.length; i++) {
							console.log(t[i]);
						}
					}
				});
	}
</script> 

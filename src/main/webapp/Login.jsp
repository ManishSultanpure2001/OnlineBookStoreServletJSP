<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!-- Html Code -->

<html>
<head>
<link rel="stylesheet" href="MainBookStoreCss.css" />
</head>
<body>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<div id="mymenu">
		<ul>

			<li><a href="Login.jsp">Login</a></li>
			<li><a href="RegisTration.jsp">Registration</a></li>
		</ul>
	</div>
	<div id="data">

		<center>
			<form action="Login" method="post">
				<table cellpadding="15px">
					<tr>
						<td>Enter email</td>
						<td><input type="email" name="loginUserEmail" id="email"
							placeholder="Enter Email"></td>
					</tr>
					<tr>
						<td>Enter Pass</td>
						<td><input type="password" name="loginUserPassword"
							placeholder="Enter Password"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input class="B" type="submit"
							value="Login"></td>
					</tr>
				</table>
			</form>
		</center>
	</div>
</body>
</html>


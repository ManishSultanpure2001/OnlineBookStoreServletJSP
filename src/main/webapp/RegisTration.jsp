<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>

<!-- Css Code -->
<link rel="stylesheet" href="MainBookStoreCss.css" />
</head>
<body>
	<div id="mymenu">
		<ul>
			<li><a href="Login.jsp">Login</a></li>
			<li><a href="RegisTration.jsp">Registration</a></li>
		</ul>
	</div>
	<div id="data">
		<center>
			<form action="Registration">
				<table cellpadding="15px">
					<tr>
						<td>Enter name</td>
						<td><input type="text" name="UserName"
							placeholder="Enter Name"></td>
					</tr>
					<tr>
						<td>Enter Email</td>
						<td><input type="email" name="UserEmail"
							placeholder="Enter Email"></td>
					</tr>
					<tr>
						<td>Choose Role</td>
						<td><select id="choice" name="UserRole">
								<option value="Please select your Role">Please select
									your Role</option>
								<option value="Clint">Clint</option>
								<option value="Admin">Admin</option>
						</select></td>
					</tr>
					<tr>
					<tr>
						<td>Enter Pass</td>
						<td><input type="password" name="UserPassword"
							placeholder="Enter Password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input class="B" type="submit"
							value="Registration"></td>
					</tr>
				</table>
			</form>
		</center>
	</div>
</body>
</html>

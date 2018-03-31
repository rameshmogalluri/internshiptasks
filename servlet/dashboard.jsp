<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css" />
</head>
<body>
	<%
		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String mobilenumber = (String) session.getAttribute("mobilenumber");
		String address = (String) session.getAttribute("address");
		String msg = config.getInitParameter("message");
	%>
	<h3 style="margin-left: 900px" class="welcomemsg">
		<%
			out.println("Welcome " + name);
		%>
	</h3>
	<div class="maindiv">
		<br>
		<div style="margin-left: 180px">
			<ul>
				<li><a href="index.jsp">LOGOUT</a></li>
			</ul>
			<h3>Your Details are:</h3>
			<table>
				<tr>
					<td>
						<h3>Name:</h3>
					</td>
					<td><h3>
							<%=name%>
						</h3></td>
				</tr>
				<tr>
					<td>
						<h3>Email:</h3>
					</td>
					<td><h3>
							<%=email%>
						</h3></td>
				</tr>
				<tr>
					<td>
						<h3>Phone No:</h3>
					</td>
					<td>
						<h3>
							<%=mobilenumber%>
						</h3>
					</td>
				</tr>
				<tr>
					<td>
						<h3>Address:</h3>
					</td>
					<td><h3>
							<%=address%>
						</h3></td>
				</tr>
			</table>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="footer-copyright py-3 text-center">
		<%
			out.println(application.getInitParameter("copyrights"));
		%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
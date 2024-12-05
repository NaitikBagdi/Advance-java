<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Employee" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/tablestyle.css">
</head>
<body>
	<h2>Employee Registration List</h2>
	<table border="1" class ="table-style">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Username</th>
				<th>Contact Number</th>
				<th>Address</th>
			</tr>
			<%
			List<Employee> employeeList = (List<Employee>) session.getAttribute("employeeList");
			for (Employee employee : employeeList) {
			%>
			<tr>
				<td><%=employee.getFirstName()%></td>
				<td><%=employee.getLastName()%></td>
				<td><%=employee.getUserName()%></td>
				<td><%=employee.getContactNumber()%></td>
				<td><%=employee.getAddress()%></td>
			</tr>
			<%
			}
			%>
	</table>
</body>
</html>
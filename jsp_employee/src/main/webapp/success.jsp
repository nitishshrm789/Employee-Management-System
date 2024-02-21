<%@page import="jsp_employee.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Page</title>
</head>
<body>

	<% String name = (String) request.getAttribute("cookie") ; 
	if(name != null){ 
	%>
	<h2>Changed by <%= name %></h2>
	<% } %>
	

    <% 
    List<Employee> list = (List<Employee>)request.getAttribute("list");    
    %>
    
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Password</th>
            <th>Address</th>
        </tr>
    
    <% for(Employee employee : list) { %>
        <tr>
            <td><%= employee.getId() %></td>
            <td><%= employee.getName() %></td>
            <td><%= employee.getPhone() %></td>
            <td><%= employee.getEmail() %></td>
            <td><%= employee.getPassword() %></td>
            <td><%= employee.getAddress() %></td>
            <td><a href="delete?id=<%= employee.getId() %>"><button>DELETE</button></a></td>
            <td><a href="update?id=<%= employee.getId() %>"><button>UPDATE</button></a></td>
        </tr>
    <% } %>
    
   
    
    </table>
</body>
</html>

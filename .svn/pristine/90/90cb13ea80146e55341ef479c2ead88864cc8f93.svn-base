<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<body>
	<h1>GAE + Spring 3 MVC REST + CRUD Example</h1>

	Function : <a href="addCustomerPage">Add Customer</a>
	<hr />

	<h2>All Customers</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		<%
		
			List<Entity> customers = (List<Entity>)request.getAttribute("customerList");
		    for(Entity e : customers){
		     
		%>
			<tr>
				<td><%=e.getProperty("name") %></td>
				<td><%=e.getProperty("email") %></td>
				<td><%=e.getProperty("date") %></td>
				<td><a href="update/<%=e.getProperty("name")%>">Update</a> | <a href="delete/<%=e.getProperty("name")%>">Delete</a></td>
			</tr>
		<%
			}
		%>
	</table>

</body>
</html>
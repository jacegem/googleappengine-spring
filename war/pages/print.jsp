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
		
	</table>
	
	<% 
		String buf = (String)request.getAttribute("buf");
		out.println(buf);
	%>
		
		
		

</body>
</html>
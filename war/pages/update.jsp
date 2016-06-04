<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<body>
	<h1>Update Customer</h1>
	
	<%
		Entity customer = (Entity)request.getAttribute("customer");
	%>
	
	<form method="post" action="../update" >
		<input type="hidden" name="originalName" id="originalName" 
			value="<%=customer.getProperty("name") %>" /> 
		
		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
						value="<%=customer.getProperty("name") %>" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
						value="<%=customer.getProperty("email") %>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Update" value="Update" />
	</form>
	
</body>
</html>
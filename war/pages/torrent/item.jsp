<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<body>
	<h1>Torrent ITEM List</h1>

	Function : <a href="../list">..list</a>
	<hr />

	<h2><% request.getAttribute("item"); %></h2>
	<table border="1">
		<thead>
			<tr>
				<td>Item</td>
				<td>date</td>
			</tr>
		</thead>
		<%
		
			List<Entity> entityList = (List<Entity>)request.getAttribute("linkList");
		    for(Entity e : entityList){
		     
		%>
			<tr>
				<td><a href="<%=e.getProperty("link")%>"><%=e.getProperty("title") %></td>
				<td><%=e.getProperty("date") %></td>
			</tr>
		<%
			}
		%>
	</table>

</body>
</html>
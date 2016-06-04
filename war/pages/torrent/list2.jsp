<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<body>
	<h1>토렌트 아이템 리스트</h1>

	<h2>토렌트 아이템 추가</h2>
	<form action="addItem" method="get">
		아이템: <input type="text" name="item"><input type="submit" value="Submit">
	</form>
	
	<h2>토렌트 아이템 리스트</h2>	
	<table border="1">
		<thead>
			<tr>
				<td>등록일</td>
				<td>아이템</td>
				<td>비고</td>
			</tr>
		</thead>
		<%
		
			List<Entity> torrentItem = (List<Entity>)request.getAttribute("torrentItem");
		    for(Entity e : torrentItem){
		     
		%>
			<tr>
				
				<td><%=e.getProperty("date") %></td>
				<td><a href="link/<%=e.getProperty("item")%>"><%=e.getProperty("item") %></td>
				<td><a href="site/<%=e.getProperty("item")%>">사이트</td>
			</tr>
		<%
			}
		%>
	</table>

</body>
</html>
<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<body>
	<h1>대나무숲 리스트</h1>

	<h2>대나무숲 비밀 추가</h2>
	<form action="addSecret" method="get">
		아이템: <input type="text" name="secret">
		<input type="submit" value="Submit">
	</form>
	
	<h2>대나무숲 리스트</h2>	
	<table border="1">
		<thead>
			<tr>
				<td>등록일</td>
				<td>카테고리</td>
				<td>비밀</td>
			</tr>
		</thead>
		<%
		
			List<Entity> listEntity = (List<Entity>)request.getAttribute("bambooSecret");
		    for(Entity e : listEntity){
		     
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
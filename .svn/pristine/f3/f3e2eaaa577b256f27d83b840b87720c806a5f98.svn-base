<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<body>
	<h1>토렌트 링크 리스트</h1>

	Function : <a href="../list">아이템 목록</a>
	<hr />

	<h2><% request.getAttribute("item"); %></h2>
	<table border="1">
		<thead>
			<tr>
				<td>입력일</td>
				<td>링크</td>
			</tr>
		</thead>
		<%
		
			List<Entity> entityList = (List<Entity>)request.getAttribute("linkList");
		    for(Entity e : entityList){
		     
		%>
			<tr>
				<td><%=e.getProperty("date") %></td>
				<td><a href="<%=e.getProperty("link")%>"><%=e.getProperty("title") %></td>
			</tr>
		<%
			}
		%>
	</table>

</body>
</html>
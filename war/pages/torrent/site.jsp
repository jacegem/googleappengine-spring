<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<head>
<style type="text/css">
form, input {
	width: 80%;
}
</style>
</head>
<body>
	<h1>사이트 리스트</h1>

	Function : <a href="../list">아이템 리스트</a>
	<hr />
	
	<h2>토렌트 사이트 추가</h2>
	<form action="../addSite" method="get">		 
		아이템:<input 	type="text" name="item" value="<%=request.getAttribute("item") %>" readonly="readonly"><br>
		사이트: <input 	type="text" name="site"><br> 
		정규식: <input type="text"name="regex"><br> 
		링크순번: <input type="text" name="linkIdx"><br> 
		제목순번: <input type="text" name="titleIdx"><br> 
		<input type="submit" value="Submit">
	</form>
	<hr />

	<h2><%=request.getAttribute("item") %></h2>
	<table border="1">
		<thead>
			<tr>
				<td>등록일</td>
				<td>사이트</td>
				<td>정규식</td>
				<td>링크인덱스</td>
				<td>타이틀인덱스</td>
			</tr>
		</thead>
		<%
		
			List<Entity> siteList = (List<Entity>)request.getAttribute("siteList");
		    for(Entity e : siteList){
		     
		%>
			<tr>
				
				<td><%=e.getProperty("date") %></td>
				<td><%=e.getProperty("site") %></td>
				<td><xmp><%=e.getProperty("regex")%></xmp></td>
				<td><%=e.getProperty("linkIdx") %></td>
				<td><%=e.getProperty("titleIdx") %></td>				
			</tr>
		<%
			}
		%>
	</table>

</body>
</html>
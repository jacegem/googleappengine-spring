<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Flat Design Mini Portfolio</title>
<meta name="description" content="Flat Design Mini Portfolio">
<meta name="keywords"
	content="responsive, bootstrap, flat design, flat ui, portfolio">
<meta name="author" content="Dzyngiri">
<meta name="description"
	content="This is a responsive flat design mini portfolio for creative folks who want to showcase their work online.">
<!-- styles -->
<link href="/rw/css/bootstrap.css" rel="stylesheet">
<!-- <link href="/rw/css/bootstrap-responsive.css" rel="stylesheet">-->
<link href="/rw/css/style.css" rel="stylesheet">
<link href="/rw/font/css/fontello.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.html"><img src="/rw/img/user.jpg" /></a>
				<ul class="nav nav-collapse pull-right">
					<li><a href="/torrent/total" class="active"><i class="icon-user"></i>전체</a></li>
					<li><a href="skills.html"><i class="icon-trophy"></i>Skills</a></li>
					<li><a href="work.html"><i class="icon-picture"></i> Work</a></li>
					<li><a href="resume.html"><i class="icon-doc-text"></i>Resume</a></li>
				</ul>
				<!-- Everything you want hidden at 940px or less, place within here -->
				<div class="nav-collapse collapse">
					<!-- .nav, .navbar-search, .navbar-form, etc -->
				</div>
			</div>
		</div>
	</div>
	<h1>토렌트 아이템 리스트</h1>
	

	<h2>토렌트 아이템 추가</h2>
	<form action="addItem" method="get">
		아이템: <input type="text" name="item"><input type="submit"
			value="Submit">
	</form>

	<h2>토렌트 아이템 리스트</h2>
	<table border="1" class="table">
		<thead>
			<tr>
				<td>등록일</td>
				<td>아이템</td>
				<td>비고</td>
			</tr>
		</thead>
		<%		
			List<Entity> torrentItem = (List<Entity>) request
					.getAttribute("torrentItem");
			for (Entity e : torrentItem) {
		%>
		<tr>

			<td><%=e.getProperty("date")%></td>
			<td><a href="link/<%=e.getProperty("item")%>"><%=e.getProperty("item")%></td>
			<td><a href="site/<%=e.getProperty("item")%>">사이트</td>
		</tr>
		<%
			}
		%>
	</table>
	
<!-- Scripts -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/rw/js/bootstrap.min.js"></script>
    <script>
                $('#myModal').modal('hidden')
        </script>
</body>
</html>
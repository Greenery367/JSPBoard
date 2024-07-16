<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP MVC 게시판</title>
<style type="text/css">
body{
	font-family: arial, sans-serif;
	background-color: #f4f4f4;
	color: #333;
	margin: 0;
	padding: 0;
}
.nav-list{
	list-style-type:none;
	padding:0;
}
.nav-list li{
	margin:10px;
	display: inline-block;
}
.nav-list li a{
	text-decoration:none;
	padding: 10px 20px;
	color:white;
}
.btn-primary{
	background-color: #007dff;
}
.btn-secondary{
	background-color: #6c757d;
}
</style>
</head>
<body>
	<div class="container">
		<h2>JSP MVC 게시판 테스트 페이지</h2>
		<ul class="nav-list">
			<li class="btn btn-primary"><a href="/t-board/user/signup">회원가입</a></li>	
			<li class="btn btn-primary"><a href="/t-board/user/signin">로그인</a></li>	
			<li class="btn btn-secondary"><a href="/t-board/user/signup">로그아웃</a></li>	
			<li class="btn btn-secondary"><a href="/t-board/board/list">게시판 목록</a></li>	
		</ul>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 작성하기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/create.css">
</head>
<body>
	<div class="container">
		<h2>새 글 작성하기</h2>
		<form action="${pageContext.request.contextPath}/board/create" method="post">
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="코딩 테스트 연습!">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="10" id="content" name="content">알고리즘 생산성 향상!</textarea>
			</div>
			<div class="form-group">
				<input type="submit" value="작성하기" class="btn btn-submit">
				<a href="${pageContext.request.contextPath}/board/list?page=1" class="btn btn-back">목록으로 돌아가기</a>
			</div>
		</form>
	</div>
</body>
</html>
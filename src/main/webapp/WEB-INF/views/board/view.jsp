<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/list.css">    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기 화면</title>
   <style>
        /* Body styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
        }
        
        /* Container styles */
        .container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px auto;
            max-width: 600px;
        }
        
        /* Heading styles */
        h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 10px;
        }
        
        /* Content styles */
        p {
            color: #666;
            font-size: 16px;
            line-height: 1.6;
        }
        
        /* Button styles */
        .btn {
            display: inline-block;
            background-color: #f39c12;
            color: #fff;
            text-decoration: none;
            padding: 8px 16px;
            border-radius: 5px;
            margin-right: 10px;
            margin-bottom: 10px;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        
        .btn:hover {
            background-color: #e67e22;
        }
        
        /* Comment section styles */
        .comments-section {
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #eee;
        }
        
        h3 {
            color: #333;
            font-size: 20px;
            margin-bottom: 10px;
        }
        
        /* Form styles */
        .comment-form {
            margin-top: 20px;
        }
        
        .comment-form textarea {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ddd;
            border-radius: 5px;
            resize: vertical;
        }
        
        .comment-form .btn-submit {
            background-color: #2ecc71;
            color: #fff;
            border: none;
            padding: 8px 16px;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .comment-form .btn-submit:hover {
            background-color: #27ae60;
        }
    </style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/view.css">
</head>
<body>
	<div class="container">
		<h2>${board.title}</h2>
		<p>${board.content}</p>
		<p> <fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd HH:mm" /></p>
	</div>
	<c:if test="${board.userId==userId}">
		<a href="${pageContext.request.contextPath}/board/edit?id=${board.id}" class="btn btn-edit">수정</a>
		<a href="${pageContext.request.contextPath}/board/delete?id=${board.id}" class="btn btn-delete">삭제</a>
	</c:if>
	
	<a href="${pageContext.request.contextPath}/board/list?page=1" class="btn btn-edit">>목록으로 돌아가기</a>
	
	<h3>댓글</h3>
	<!-- 댓글 리스트 작성 -->
	<!-- 댓글 작성 폼 -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reslut</title>
</head>
<body>
	<h1>検索結果</h1>

	<div class="result">
	<c:if test="${not empty result}">
		<p>データを取得しました</p>
		<p>${requestScope.result}</p>
	</c:if>
	</div>
	<div>
		<a href="top.jsp">戻る</a>
	</div>
</body>
</html>
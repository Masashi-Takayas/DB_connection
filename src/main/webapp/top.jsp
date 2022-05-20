<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>

</head>
<body>
	<h1>検索条件を入力してください</h1>

	<div>
		<p>${requestScope.result}</p>
	</div>
	<form action="InputServlet" method="post">
		<label>product_id：</label> <input type="text" name="product_id">
		<br>
		<button type="submit">検索</button>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="nguyenquocdat.gamedoanso.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1 class="text-primary text-center">Game Đoán Số</h1>
		<form class="w-50"
			action="<%=request.getContextPath() + UrlConst.PLAYER_REGISTER%>"
			method="post" class="form-group">
			<h3>${message == null ? "" : message}</h3>
			<label for="playerName">Your name</label> 
			<input required type="text" class="form-control" name="playerName" id="playerName"
				placeholder="Enter your name"> 
			<label for="userEmail">Email</label>
			<input required type="email" class="form-control" name="userEmail" id="userEmail" placeholder="Enter email"
				value='${lastUsername == null ? "" : lastUsername }'> 
			<label for="password">Password</label> 
			<input required type="password" class="form-control" name="password" id="password">
			<label  for="rPassword">Repeat Password:</label> 
			<input required type="password" class="form-control" name="rPassword" id="rPassword">
			<input class="btn btn-primary mt-2" type="submit" value="Đăng ký" />
		</form>
	</div>
</body>
</html>
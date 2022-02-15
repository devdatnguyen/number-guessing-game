<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="nguyenquocdat.gamedoanso.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>Game Đoán Số</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<div class="container, text-right, btn btn-success"
		style="text-align: center">
		<h3>Thông tin</h3>
		<hr>
		<h5>Tên: ${record.player.playerName}</h5>
		<h5>Email: ${record.player.userEmail}</h5>

		<form class="text-left"
			action="<%=request.getContextPath() + UrlConst.GAME_RANKING%>"
			method="get">
			<button class="btn btn-warning">
				<b>Bẳng xếp hạng</b>
			</button>
		</form>

		<form class="text-right"
			action="<%=request.getContextPath() + UrlConst.PLAYER_LOGIN%>"
			method="post">
			<button>Đăng xuất</button>
		</form>
	</div>

	<div class="container h-100 d-flex justify-content-center">
		<div
			class="row h-100 justify-content-center align-content-center d-flex">
			<form class="col-12 mt-5"
				action="<%=request.getContextPath() + UrlConst.GAME_PLAY%>"
				method="post">
				<input type="text" name="recordId" value="${record.id}" hidden /> <input
					type="text" name="username" value="${record.player.userEmail}"
					hidden />
				<div class="form-group text-center">
					<label class="text text-success" for="tryNumber">${message}</label>
				</div>
				<c:if test="${record.isFinished}">
					<div class="form-group text-center">
						<label class="text-center text-danger">Đáp án chính xác là
							<b>${record.number}</b>
						</label>
					</div>
				</c:if>

				<div class="form-group text-center">
					<label class="text-center" style="color: blue">Số lần đã
						đoán <b>${record.point}</b>
					</label>
				</div>
				<div class="form-group text-center">
					<label for="tryNumber">Nhập số muốn đoán</label> <input
						${record.isFinished ? "disabled": ""} required type="text"
						class="form-control" name="tryNumber" id="tryNumber"
						placeholder="1 - 1000">
				</div>
				<div class="text-center">
					<button ${record.isFinished ? "hidden": ""} type="submit"
						class="btn btn-primary" style="text-align: center">Đoán
						số!</button>
				</div>
			</form>

			<c:if test="${record.isFinished}">
				<div class="container, text-center">
					<form class="text-center"
						action="<%=request.getContextPath() + UrlConst.GAME_PLAY%>"
						method="get">
						<button class="btn btn-success">
							<b>Chơi tiếp</b>
						</button>
					</form>
				</div>
			</c:if>

		</div>
	</div>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
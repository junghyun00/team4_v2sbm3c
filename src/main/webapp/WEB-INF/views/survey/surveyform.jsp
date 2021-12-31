<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BLOG</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
</head>
<body class="pt-5">
	<jsp:include page="../menu/top.jsp" flush='false' />
	<div class="container p-2 mt-4">
		<div class="row mb-4">
			<div class="col-md-12 p-3  rounded text-black ">
				<div class="row">
					<div class="col-md-12 border-bottom mb-3">
						<h1>Survey</h1>
					</div>
					<div class="col-md-12 border-bottom">
						<form action="">
							<div class="mt-3">
								<h5>주차 목적</h5>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="PURPOSEPARK" class="PURPOSEPARK" value=1>시설 이용
								</label>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="PURPOSEPARK" class="PURPOSEPARK" value=2>주변 방문
								</label>  
								<label class="radio-inline mr-5"> 
									<input type="radio" name="PURPOSEPARK" class="PURPOSEPARK" value=3>일반 주차
								</label> 
							</div>
							<div class="mt-3">
								<h5>선호 요일</h5>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="PREFERDAY" class="PREFERDAY" value=1>평일
								</label>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="PREFERDAY" class="PREFERDAY" value=2>주말
								</label>
							</div>
							<div class="mt-3">
								<h5>예약 기간</h5>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="RESERVEPERIOD" class="RESERVEPERIOD" value=1>장기
								</label>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="RESERVEPERIOD" class="RESERVEPERIOD" value=2>단기
								</label>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
		crossorigin="anonymous"></script>

	<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
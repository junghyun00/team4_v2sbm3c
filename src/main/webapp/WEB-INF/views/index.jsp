<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body class="pt-5">
   <jsp:include page="./menu/top.jsp" flush='false' />
	<div class="container-fluied p-2 mb-5 img-fluid" id="indexbg">
		<div class="row m-0">
			<div class="col-md-12 p-3 text-white">
				<div class="row text-center">
					<div class="col-md-12">
						<h1 class="text-white" style="font-size: 5em; margin-top: 22%;">Parking Reservation</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container pt-3">
		<div class="row m-0r">
			<div class="col-md-6 text-left" style="padding-top: 10%;">
				<h3 class="text-info">주차장 정보를 한번에!</h1>
				<p class="font-weight-bold" style="font-size: 2rem">
					<br>가격, 위치, 후기 등 주차장 관련 정보들을 제공합니다. <br>
				</p>
				<p class="font-weight-bold">
					차를 끌고 돌아다니며 주차 자리를 확인하지 않고 어플로 확인하세요<br>
					예약을 통해 원하는 시간만큼 주차가 가능합니다<br>
					챗봇을 통해 간편하게 문의하세요<br>
				</p>
			</div>
			<div class="col-md-6"><img alt="" src="/css/images/phone.jpg" style="width: 50vh; height: 50vh"></div>		
		</div>
	</div>
	<div class="container pt-5 mb-5">
		<div class="row m-0 pt-5">
			<div class="col-md-12">
				<h1 class="font-weight-bold text-info">예약 방법</h1>
			</div>
			<div class="col-md-12 text-center pt-4">
				<p class="font-weight-bold" style="font-size: 1.5rem">회원 가입<br>  ↓<br>  주차 목록<br>  ↓<br>  주차장 클릭<br>  ↓<Br>  예약
			</div>
		</div>
	</div>
	<div class="container pt-5 mb-5">
		<div class="row m-0 pt-5">
			<div class="col-md-12">
				<p class="font-weight-bold text-center text-info" style="font-size: 2rem">
				추천 시스템을 통해 다양한 주차장을 이용할 수 있습니다<br>
			   	지금 바로 시작하세요!<br>
				</p>
			</div>
		</div>
	</div>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

<jsp:include page="./menu/bottom.jsp" flush='false' />
</body>
</html>
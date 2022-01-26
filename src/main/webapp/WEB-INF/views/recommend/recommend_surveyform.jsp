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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">

    <!-- <script type="text/javascript" src="/js/survey.js"></script>   -->
    <script type="text/javascript">
        /*  $('#submit_btn').click(function (e) {
    	   recommend_parking(); 
	       
       
       }); */

       function recommend_parking() {
           var params = $('#frm').serialize();
//     	   alert(params); 

           $.ajax({
               type: "GET",
               url: "/recommend/recommend_create.do",
               data: params,
               dataType: "JSON",
               success: function (response) {
                   if(response.cnt == 1){
                       alert("설문에 응해주셔서 감사합니다");
                       location.href = "/";

                   } else{
                       alert("설문에 다시 응해주세요");
                   }
               }
           }); 

    	   var url = '/recommend/result.do?' + params;
    	   var win = window.open(url, 'AI', 'width=800px, height=750px');

    	   var x = (screen.width - 1000) / 2;
    	   var y = (screen.height - 700) / 2;

    	   win.moveTo(x, y); // 화면 중앙으로 이동

       }
   </script> 
   
    
</head>
<body class="pt-5">
	<jsp:include page="../menu/top.jsp" flush='false' />
	<div class="container p-2 mt-4">
		<div class="row mb-4">
			<div class="col-md-12 p-3  rounded text-black ">
				<div class="row">
					<div class="col-md-12 border-bottom mb-3">
						<h1>Survey </h1>
					</div>
					<div class="col-md-12 border-bottom">
						<form name="frm" id='frm'>
							<input type="hidden" name="memberno" id="memberno" value='${memberno }'>
     
							<div class="mt-3">
								<h5>주차 목적</h5>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="purposepark" id='purposepark1' class="purposepark" value='1' checked='checked'>시설 이용
								</label>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="purposepark" id='purposepark2' class="purposepark" value='2'>주변 방문
								</label>  
								<label class="radio-inline mr-5"> 
									<input type="radio" name="purposepark" id='purposepark3' class="purposepark" value='3'>일반 주차
								</label> 
							</div>
							<div class="mt-3">
								<h5>선호 요일</h5>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="preferday" id='preferday1' class="preferday" value='1' checked='checked'>평일
								</label>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="preferday" id='preferday2' class="preferday" value='2'>주말
								</label>
							</div>
							<div class="mt-3">
								<h5>예약 기간</h5>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="reserveperiod" id='reserveperiod1' class="reserveperiod" value='1' checked='checked'>장기
								</label>
								<label class="radio-inline mr-5"> 
									<input type="radio" name="reserveperiod" id='reserveperiod2' class="reserveperiod" value='2'>단기
								</label>
							</div>
							<div class="mt-3 mb-3 text-center">
								<button type="button" class="btn btn-dark border text-center" onclick="recommend_parking()" id="submit_btn">SUBMIT</button>
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
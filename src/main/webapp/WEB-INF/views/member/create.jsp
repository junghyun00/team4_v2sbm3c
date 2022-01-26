<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

<script type="text/javascript">

function create_memberfun() {
    alert("주차장 추천을 위해 설문조사를 해주시면 회원 가입이 끝납니다.")
}


    $(function() {
        $('#btn_checkID').on('click', checkID);
        $('#btn_close').on('click', setFocus); // Dialog창을 닫은후의 focus 이동
        $('#btn_send').on('click', send); 

    });

    function checkID() {
    	var frm = $('#frm'); // id가 frm인 태그 검색
        var id = $('#id', frm).val(); // frm 폼에서 id가 'id'인 태그 검색
        var params = '';
        var msg = '';

        if ($.trim(id).length == 0) { // id를 입력받지 않은 경우
            msg = '· ID를 입력하세요.<br>· ID는 3자이상 권장합니다.';
            
            $('#modal_content').attr('class', 'alert alert-danger');
            $('#modal_title').html('ID 중복 확인'); // 제목 
            $('#modal_content').html(msg);        // 내용
            $('#btn_close').attr("data-focus", "id");  // 닫기 버튼 클릭시 id 입력으로 focus 이동
            $('#modal_panel').modal();               // 다이얼로그 출력
            return false;
        } else {  // id를 입력 받으면
            params = 'id=' + id;

            $.ajax({
            	url: './checkID.do', // spring execute
                type: 'get',  // post
                cache: false, // 응답 결과 임시 저장 취소
                async: true,  // true: 비동기 통신
                dataType: 'json', // 응답 형식: json, html, xml...
                data: params,      // 데이터
                success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
                	var msg = "";

                	if (rdata.cnt > 0) {
                        $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
                        msg = "이미 사용중인 ID 입니다.";
                        $('#btn_close').attr("data-focus", "id");  // id 입력으로 focus 이동
                      } else {
                        $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap CSS 변경
                        msg = "사용 가능한 ID 입니다.";
                        $('#btn_close').attr("data-focus", "passwd");  // passwd 입력으로 focus 이동
                        // $.cookie('checkId', 'TRUE'); // Cookie 기록
                      }
                      
                      $('#modal_title').html('ID 중복 확인'); // 제목 
                      $('#modal_content').html(msg);        // 내용
                      $('#modal_panel').modal();              // 다이얼로그 출력
                    },
                    error: function(request, status, error) { // callback 함수
                        console.log(error);
                    }
            });
        }

    }


    function setFocus() {
    	var tag = $('#btn_close').attr('data-focus'); // data-focus 속성에 선언된 값을 읽음 
    	$('#' + tag).focus(); // data-focus 속성에 선언된 태그를 찾아서 포커스 이동
    }


    function send() {   // 회원 가입 처리, 패스워드 올바른 입력
    	if ($('#passwd').val() != $('#passwd2').val()) {
			msg = '입력된 패스워드가 일치하지 않습니다.<br>';
			msg += "패스워드를 다시 입력해주세요.<br>"; 
			
			$('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
			$('#modal_title').html('패스워드 일치 여부  확인'); // 제목 
			$('#modal_content').html(msg);  // 내용
			$('#modal_panel').modal();         // 다이얼로그 출력
			
			$('#btn_send').attr('data-focus', 'passwd');
			
			return false; // submit 중지
        }
        
    	$('#frm').submit();
    }
</script>


</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

	<!-- ******************** Modal 알림창 시작 ******************** -->
	<div id="modal_panel" class="modal fade"  role="dialog">
		<div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content">
		    <div class="modal-header">
		      <h4 class="modal-title" id='modal_title'></h4><!-- 제목 -->
		      <button type="button" class="close" data-dismiss="modal">×</button>
		    </div>
		    <div class="modal-body">
		      <p id='modal_content'></p>  <!-- 내용 -->
		    </div>
		    <div class="modal-footer">
		      <button type="button" id="btn_close" data-focus="" class="btn btn-default" 
		                  data-dismiss="modal">닫기</button>
		    </div>
		  </div>
		</div>
	</div>
	<!-- ******************** Modal 알림창 종료 ******************** -->

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>회원 가입</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        
        <div class="row-fluid">
            <form style='margin:20px 10px 10px 50px; ' name='frm' id='frm' method='POST' action='./create.do' class="form-horizontal"
             enctype="multipart/form-data">
                
                <div class="row" style='margin:40px auto;'>
                    <label for="id" class="col-md-2" style='color:#555555; font-weight: bold; '>ID*</label>
                    <div class="col-md-10">
                        <input type='text' class="form-control" name='id' id='id' value='user' required="required" style='width: 30%;' placeholder="아이디" autofocus="autofocus">
				        <button type='button' id="btn_checkID" class="btn btn-secondary btn-sm">중복확인</button>
				        <SPAN id='id_span'></SPAN> <!-- ID 중복 관련 메시지 -->    
                    </div>
                </div>
    
                <div class="row" style='margin:40px auto;'>
			      <label for="passwd" class="col-md-2" style='color:#555555; font-weight: bold; '>패스워드*</label>    
			      <div class="col-md-10">
			        <input type='password' class="form-control" name='passwd' id='passwd' value='1234' required="required" style='width: 30%;' placeholder="패스워드">
			      </div>
			    </div>
			    
			    <div class="row" style='margin:40px auto;'>
			      <label for="passwd2" class="col-md-2" style='color:#555555; font-weight: bold; '>패스워드 확인*</label>    
			      <div class="col-md-10">
			        <input type='password' class="form-control" name='passwd2' id='passwd2' value='1234' required="required" style='width: 30%;' placeholder="패스워드">
			      </div>
			    </div>   
    
                <div class="row" style='margin:40px auto;'>
			      <label for="mname" class="col-md-2" style='color:#555555; font-weight: bold; ' >성명*</label>    
			      <div class="col-md-10">
			        <input type='text' class="form-control" name='name' id='name' value='왕눈이' 
			                   value='' required="required" style='width: 30%;' placeholder="성명">
			      </div>
			    </div>   
			
			    <div class="row" style='margin:40px auto;'>
			      <label for="tel" class="col-md-2" style='color:#555555; font-weight: bold; '>전화번호*</label>    
			      <div class="col-md-10">
			        <input type='text' class="form-control" name='phone' id='phone' value='010-0000-0000'
			                   value='' required="required" style='width: 30%;' placeholder="전화번호"> 예) 010-0000-0000
			      </div>
			    </div>
			    
			    <div class="row" style='margin:40px auto;'>
                  <label for="mname" class="col-md-2" style='color:#555555; font-weight: bold; '>이메일</label>    
                  <div class="col-md-10">
                    <input type='text' class="form-control" name='email' id='email' 
                               value='email value'  style='width: 30%;' placeholder="e-mail">
                  </div>
                </div>   
                
                <div class="row" style='margin:40px auto;'>
			      <label for="address1" class="col-md-2" style='color:#555555; font-weight: bold; '>주소</label>    
			      <div class="col-md-10">
			        <input type='text' class="form-control" name='address' id='address' 
			                   value='주소1' style='width: 80%;' placeholder="주소">
			      </div>
			    </div> 
                

                <div class="content_body_bottom">
                    <button type="submit" onclick ="create_memberfun()"  id='btn_send' class="btn btn-dark">가입</button>
                    <button type="button" onclick="history.back();" class="btn btn-dark">취소</button>
                </div>
            </form>
        </div>
    </div>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
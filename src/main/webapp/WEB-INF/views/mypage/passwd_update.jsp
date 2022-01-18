<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BLOG</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

<script type="text/javascript">
	$(function() { // 자동 실행
	    $('#btn_send').on('click', send); 
	    $('#btn_close').on('click', setFocus); // Dialog창을 닫은후의 focus 이동
	  });

  function send() {
    if ($('#new_passwd').val() != $('#new_passwd2').val()) {
      msg = '· 새로운 패스워드와 패스워드 확인이 일치하지 않습니다.<br>';
      msg += "· 새로운 패스워드를 다시 확인해주세요.<br>"; 
      
      $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경
      $('#modal_title').html('패스워드 일치 여부  확인'); // 제목 
      $('#modal_content').html(msg);  // 내용
      $('#modal_panel').modal();         // 다이얼로그 출력
      
      $('#btn_close').attr("data-focus", "new_passwd");
      
      return false; // submit 중지
    }

    $('#frm').submit();
  }
  
  function setFocus() {  // focus 이동
    var tag = $('#btn_close').attr('data-focus'); // 포커스를 적용할 태그 id 가져오기
    $('#' + tag).focus(); // 포커스 지정
  }
  
</script> 

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />


	<!-- ******************** Modal 알림창 시작 ******************** -->
	<div class="modal fade" id="modal_panel" role="dialog">
		<div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content">
		    <div class="modal-header">
		      <h4 class="modal-title" id='modal_title'></h4>
		      <button type="button" class="close" data-dismiss="modal">×</button>
		    </div>
		    <div class="modal-body">
		      <p id='modal_content'></p>
		    </div>
		    <div class="modal-footer">
		      <button type="button" id="btn_close" data-focus=""
		                 class="btn btn-default" data-dismiss="modal">Close</button>
		    </div>
		  </div>
		</div>
	</div>
	<!-- ******************** Modal 알림창 종료 ******************** -->
 

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>${memberVO.id }(${memberVO.name })님의 패스워드 변경</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
            
        <DIV class='content_body'>  
          <DIV style='width: 50%; margin: 0px auto;'>
            <FORM name='frm' id='frm' method='POST' action='./passwd_update.do' class="form-horizontal">
              <input type='hidden' name='memberno' id='memberno' value='${param.memberno }'>  
              
              <div class="row">
                <label class="col-md-5" style='color:#555555; font-weight: bold; text-align: right;'>현재 패스워드</label>    
                <div class="col-md-7">
                  <input type='password' class="form-control" name='current_passwd' 
                    id='current_passwd' value='' required="required" 
                    style='width: 50%;' placeholder="패스워드">                   
                </div>
              </div>
              <br> 
           
              <div class="row">
                <label class="col-md-5 control-label" style='color:#555555; font-weight: bold; text-align: right;'>새로운 패스워드</label>    
                <div class="col-md-7">
                  <input type='password' class="form-control" name='new_passwd' 
                    id='new_passwd' value='' required="required" 
                    style='width: 50%;' placeholder="패스워드">
                </div>
              </div>   
              <br> 
              
              <div class="row">
		        <label class="col-md-5 control-label" style='color:#555555; font-weight: bold; text-align: right;'>새로운 패스워드 확인</label>    
		        <div class="col-md-7">
		          <input type='password' class="form-control" name='new_passwd2' 
		                    id='new_passwd2' value='' required="required" 
		                    style='width: 50%;' placeholder="패스워드">
		        </div>
		      </div>
               
              <br> 
              <div class="content_body_bottom" style='text-align: center;'>
				<button type="button" id='btn_send' class="btn btn-dark btn-md">변경</button>
				<button type="button" onclick="location.href='/'" class="btn btn-dark btn-md">취소</button>
              </div>   
              
            </FORM>
          </DIV>
        
        </DIV>
    </div>
</div>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>



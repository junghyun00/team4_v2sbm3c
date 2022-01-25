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
  function loadDefault() {
    $('#id').val('abc123');
    $('#passwd').val('12');
  }  
</script> 

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>로그인</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
            
        <DIV class='content_body'>  
		  <DIV style='width: 50%; margin: 0px auto;'>
		    <FORM name='frm' method='POST' action='./login.do' class="form-horizontal">
		      <%-- 로그인 후 자동으로 이동할 페이지 전달 ★ --%>
		      <input type="hidden" name="return_url" value="${return_url}">
		      
		      <div class="row">
		        <label class="col-md-5" style='color:#555555; font-weight: bold; text-align: center;'>아이디</label>    
		        <div class="col-md-7">
		          <input type='text' class="form-control" name='id' id='id' 
		                     value='${ck_id }' required="required" 
		                     style='width: 60%;' placeholder="아이디" autofocus="autofocus">
		          <Label>   
		            <input type='checkbox' name='id_save' value='Y' 
		                      ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
		          </Label>                   
		        </div>
		   
		      </div>   
		   
		      <div class="row">
		        <label class="col-md-5 control-label" style='color:#555555; font-weight: bold; text-align: center;'>패스워드</label>    
		        <div class="col-md-7">
		          <input type='password' class="form-control" name='passwd' id='passwd' 
		                    value='${ck_passwd }' required="required" style='width: 60%;' placeholder="패스워드">
		          <Label>
		            <input type='checkbox' name='passwd_save' value='Y' 
		                      ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
		          </Label>
		        </div>
		      </div>   
		   
		      <div class="content_body_bottom" style='text-align: center;'>
		          <button type="submit" class="btn btn-dark btn-md">로그인</button>
		          <button type='button' onclick="location.href='./create.do'" class="btn btn-dark btn-md">회원가입</button>
		          <button type='button' onclick="loadDefault();" class="btn btn-dark btn-md">테스트 계정</button>
		      </div>   
		      
		    </FORM>
		  </DIV>
		
		</DIV>
    </div>
</div>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>



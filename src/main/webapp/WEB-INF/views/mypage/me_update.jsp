<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var='memberno' value="${memberVO.memberno }" />
<c:set var='id' value="${memberVO.id }" />
<c:set var='passwd'  value="${memberVO.passwd }" />
<c:set var='name'  value="${memberVO.name }" />
<c:set var='address'  value="${memberVO.address }" />
<c:set var='phone'  value="${memberVO.phone }" />
<c:set var='email'  value="${memberVO.email }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BLOG</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

<script>
function updatefun() {
    alert("수정되었습니다.")
}
</script>

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>회원 정보 수정</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        
        <div class="row-fluid" style='margin:20px 10px 10px 50px;'>
            <form style='margin:20px 10px 10px 50px; ' name='frm' method='POST' action='./me_update.do' class="form-horizontal"
             enctype="multipart/form-data">
                <input type="hidden" name="memberno" value='${memberno }'>
                
                <div class="row">
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>아이디</label>
                    <div class="col-md-10">
                        ${id } (아이디는 변경 불가)
                    </div>
                </div>
    
                <div class="row" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>성명</label>
                    <div class="col-md-10">
                        <input type='text' name='name' value='${name }'  required="required" 
                           class="form-control" style='width: 40%;'>
                    </div>
                </div>
    
                <div class="row" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주소</label>
                    <div class="col-md-10">
                        <input type='text' name='address' value='${address }'  required="required" 
                           class="form-control" style='width: 70%;'>
                    </div>
                </div>
    
                <div class="row" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>전화번호</label>
                    <div class="col-md-10">
                        <input type='text' name='phone' value='${phone }'  required="required" 
                           class="form-control" style='width: 40%;'>
                    </div>
                </div>
                
                <div class="row" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>이메일</label>
                    <div class="col-md-10">
                        <input type='text' name='email' value='${email }'  required="required" 
                           class="form-control" style='width: 40%;'>
                    </div>
                </div>
                
                <br>
                <div class="content_body_bottom">
                    <button type="submit" onclick = "updatefun()" class="btn btn-dark">수정</button>
                    <button type="button" onclick="history.back();" class="btn btn-dark">취소</button>
                </div>
            </form>
        </div>

        
    </div>
</div>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>
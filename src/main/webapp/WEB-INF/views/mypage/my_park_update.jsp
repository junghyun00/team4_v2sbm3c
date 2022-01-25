<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var='parkno' value="${parkVO.parkno }" />
<c:set var='memberno' value="${parkVO.memberno }" />
<c:set var='name'  value="${parkVO.name }" />
<c:set var='phone'  value="${parkVO.phone }" />
<c:set var='address'  value="${parkVO.address }" />
<c:set var='area'  value="${parkVO.area }" />
<c:set var='price'  value="${parkVO.price }" />
<c:set var='cmt'  value="${parkVO.cmt }" />
<c:set var='file1'  value="${parkVO.file1 }" />

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
            <h4>${memberVO.id }(${memberVO.name })님의 주차장 수정</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        <div class="row-fluid" style='margin:20px 10px 10px 50px; '>
            <form style='margin:20px 10px 10px 50px; ' name='frm' method='POST' action='./my_park_update.do' class="form-horizontal"
             enctype="multipart/form-data">
                <input type='hidden' name='parkno' value='${parkno }'>
                <input type="hidden" name="memberno" value='${memberno }'>  <!-- 이건 나중에 수정 -->
                
                <div class="form-group">
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 이름</label>
                    <div class="col-md-10">
                        <input type='text' name='name' value='${name }' required="required" 
                           class="form-control" style='width: 50%;'>
                    </div>
                </div>
    
                <div class="form-group" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 주소</label>
                    <div class="col-md-10">
                        <input type='text' name='address' value='${address }'  required="required" 
                           class="form-control" style='width: 80%;'>
                    </div>
                </div>
    
                <div class="form-group" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 구역번호</label>
                    <div class="col-md-10">
                        <input type='text' name='area' value='${area }'  required="required" 
                           class="form-control" style='width: 50%;'>
                    </div>
                </div>
    
                <div class="form-group" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>전화번호</label>
                    <div class="col-md-10">
                        <input type='text' name='phone' value='${phone }'  required="required" 
                           class="form-control" style='width: 50%;'>
                    </div>
                </div>
    
                <div class="form-group" style='margin-top:30px;'>
                    <label class="col-md-6" style='color:#555555;; font-weight: bold; '>주차 30분 당 가격 (숫자만 입력해주세요)</label>
                    <div class="col-md-10">
                        <input type='text' name='price' value='${price }'  required="required" 
                           class="form-control" style='width: 50%;'>
                    </div>
                </div>
    
                <div class="form-group" style='margin-top:30px;'>
                    <label class="col-md-10" style='color:#555555;; font-weight: bold; '>주차장에 대한 공지사항, 주의사항 등을 적어주세요. (필수는 아닙니다)</label>
                    <div class="col-md-10">
                        <textarea type='text' name='cmt' rows="3" 
                           class="form-control" style='width: 80%;'>${cmt }</textarea>
                    </div>
                </div>
                <div class="content_body_bottom">
                    <button type="submit" onclick = "updatefun()" class="btn btn-dark">등록</button>
                    <button type="button" onclick="history.back();" class="btn btn-dark">취소</button>
                </div>
            </form>
        </div>

        
    </div>
</div>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>
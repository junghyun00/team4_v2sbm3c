<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var='qnano' value="${qnaVO.qnano }" />
<c:set var='memberno' value="${qnaVO.memberno }" />
<c:set var='title' value="${qnaVO.title }" />
<c:set var='content'  value="${qnaVO.content }" />


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
            <h4>○○님의 QNA 수정</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        <div class="row-fluid">
            <form style='margin:20px 10px 10px 50px; ' name='frm' method='POST' action='./my_qna_update.do' class="form-horizontal"
             enctype="multipart/form-data">
                <input type='hidden' name='qnano' value='${qnano }'>
                <input type="hidden" name="memberno" value='${memberno }'>
                
                <div class="form-group">
                    <label class="col-md-2" style='color:#555555; font-weight: bold; '>글 제목</label>
                    <div class="col-md-10">
                        <input type='text' name='title' value='${title }' required="required" 
                           class="form-control" style='width: 50%;'>
                    </div>
                </div>
    
                <div class="form-group" style='margin-top:30px;'>
                    <label class="col-md-2" style='color:#555555;; font-weight: bold; '>글 내용</label>
                    <div class="col-md-10">
                        <textarea name='content' required="required" 
                           class="form-control" rows="12" style='width: 100%;'></textarea>
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
</body>
</html>
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

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<DIV class='content_body'>
  <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
	        <h4>${memberVO.id }(${memberVO.name })님의 QNA 등록</h4>
	        <div style='border-bottom: solid 3px #555555; '></div>
	        </div>
        </div>
        <div class="row-fluid">
            <form style='margin:20px 10px 10px 50px; ' name='frm' method='POST' action='./qna_create.do' class="form-horizontal"
             enctype="multipart/form-data">
                <input type="hidden" name="memberno" value="${memberVO.memberno}">   <!-- 이건 나중에 수정 -->
  
    <div class="form-group">
       <label class="control-label col-md-2">글 제목</label>
       <div class="col-md-10">
         <input type='text' name='title' value='' required="required" 
                   autofocus="autofocus" class="form-control" style='width: 100%;'>
       </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-md-2">글 내용</label>
       <div class="col-md-10">
         <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
       </div>
    </div>
    
    <!--  <div class="form-group">
       <label class="control-label col-md-2">이미지</label>
       <div class="col-md-10">
         <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
       </div>
    </div>    -->
    
    <div class="content_body_bottom">
      <button type="submit" class='btn btn-dark'>등록</button>
      <button type="button" onclick="location.href='./qna_list_search_paging.do'" class='btn btn-dark'>목록</button>
    </div>
</form>
</DIV>
</div>
</DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
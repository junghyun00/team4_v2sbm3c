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

<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<DIV class='content_body'>
  <DIV class='menu_line'></DIV>
  
    <div class="form-group">
       <label class="control-label col-md-2">제목</label>
       <div class="col-md-10">
         <input type='text' name='title' value='가을 영화' required="required" 
                   autofocus="autofocus" class="form-control" style='width: 100%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">내용</label>
       <div class="col-md-10">
         <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'>내용을 입력해주세요</textarea>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">검색어</label>
       <div class="col-md-10">
         <input type='text' name='word' value='월터,벤 스틸러,크리스튼위그,휴먼,도전' required="required" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>   
    <div class="form-group">
       <label class="control-label col-md-2">이미지</label>
       <div class="col-md-10">
         <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
       </div>
    </div>   
    <div class="form-group">
       <label class="control-label col-md-2">패스워드</label>
       <div class="col-md-10">
         <input type='password' name='passwd' value='1234' required="required" 
                    class="form-control" style='width: 50%;'>
       </div>
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./qna_list_search_paging.do'" class="btn btn-primary">목록</button>
    </div>
</DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
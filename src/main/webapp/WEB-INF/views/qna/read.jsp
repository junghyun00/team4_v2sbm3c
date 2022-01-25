<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var='qnano' value="${qnaVO.qnano }" />
<c:set var='memberno' value="${qnaVO.memberno }" />
<c:set var='title'  value="${qnaVO.title }" />
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
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body' style='margin:70px;'>
    <div class="container" >
      <!--  <div class="row" >
            <div class="col-md-6" >
                <c:set var="file1" value="${file1.toLowerCase() }" />
	                <c:choose>
		              <c:when test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}">
		                  <IMG src="/qna/storage/${file1 }" style="width: 80%;"> 
		              </c:when>
		               <c:otherwise> 
		                <IMG src="/qna/images/none1.png" style="margin: 0px 0px 0px 20px; width: 90%;"> 
		              </c:otherwise>
		            </c:choose>  
            </div> -->
            
            <div class="col-md-12" ><br>
                <span style="font-size: 1.0em; font-weight: bold;">제목 [ ${title } ]</span><br><br><br><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">내용 [ ${content } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">답변 [ ${answerVO.content } ]</span><br><br>
              
                
                <button type='button' onclick="location.href='/answer/answer_create.do?qnano=${qnaVO.qnano}'" class="btn btn-dark">답변</button>
                <button type='button' onclick="history.back();" class="btn btn-dark">돌아가기</button><br>
            </div>
  
        </div>
    </div>




<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
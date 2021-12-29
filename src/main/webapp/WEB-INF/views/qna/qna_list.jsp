<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<div class='content_body'>
    <div class="container-fluid">
        <div class="row" style='border: solid 1px;'>
                
                <DIV style="text-align: center; clear: both;">  
				    <form name='frm' id='frm' method='get' action='./list_by_cateno_search_paging.do'>
				      <input type='hidden' name='cateno' value='${cateVO.cateno }'>
				      <input type='text' name='word' id='word' placeholder="주차할 지역을 입력해주세요." value='${param.word }' style='margin:15px; width: 40%;'>
				      <button type='submit' type="button" class="btn btn-dark">검색</button>
				      <c:if test="${param.word.length() > 0 }">
				        <button type='button' 
				                     onclick="location.href='./list_by_cateno_search_paging.do?cateno=${cateVO.cateno}&word='">검색 취소</button>  
				      </c:if>    
				    </form>
				  </DIV>
                
                <table class="table">
                <colgroup>
                  <col style="width: 20%;"></col>
                  <col style="width: 30%;"></col>
                  <col style="width: 10%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">주차장 이름</TH>
                      <TH class="th_bs">주소</TH>
                      <TH class="th_bs">1시간 당 <br>가격</TH>
                    </TR>
                 </thead>
                
                <tbody>
                   <c:forEach var="qnaVO" items="${list }">
                        <c:set var='qnano' value="${qnaVO.qnano }" />
                        <c:set var='memberno' value="${qnaVO.memberno }" />
                        <c:set var='title'  value="${qnaVO.title }" />
                        <c:set var='content'  value="${qnaVO.content }" />
                        <c:set var='img'  value="${qnaVO.img }" />
                        
                        <tr>
                            <td class="th_bs">${qnano }</td>
                            <td class="th_bs">${title }</td>
                        </tr>
                   
                   </c:forEach>
                </tbody>
                </table>
            
       </div>
    </div>
</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
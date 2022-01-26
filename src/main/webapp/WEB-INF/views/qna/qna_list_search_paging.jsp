<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var='answerno' value="${answerVO.answerno }" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
	<div class="container">
		<div class="row-fluid">
			<div style='margin:30px;'>
			<h4>QNA</h4>
			<div style='border-bottom: solid 3px #555555; '></div>
			</div>
        </div>
		    
		<div class="row-fluid">
			<%-- ******************** 검색 시작 ******************** --%>
			<DIV style="text-align: center; width: 100%;">  
			    <form name='frm' id='frm' method='get' action='./qna_list_search_paging.do'>
			      <input type='text' name='title' id='title' placeholder="찾고싶은 QNA를 검색해주세요." value='${param.title }' style='margin:15px; width: 30%;'>
			      <button type='submit' type="button" class="btn btn-dark">검색</button>
			      <c:if test="${param.title.length() > 0 }">
			        <button type='button' 
			                     onclick="location.href='./qna_list_search_paging.do?title='" class="btn btn-dark">검색 취소</button>  
			      </c:if> 
			    </form>
			  </DIV>
			  <DIV style='margin:15px; width: 100%;'>
			     <button type="submit" type="button" id='submit' class='btn btn-dark' style='float:right' onclick="location.href='/qna/qna_create.do?memberno=${memberno}'">QNA 등록</button>
			     <br>
			  </DIV>
			  <%-- ******************** 검색 종료 ******************** --%>
		</div>
         
            
		<div class="row-fluid">
			<table class="table">
		        <colgroup>
		          <col style="width: 75%;"></col>
		          <col style="width: 10%;"></col>
		          <col style="width: 10%;"></col>
		        </colgroup>           
		        
		        <thead>  
		            <TR>
		              <TH class="th_bs">QNA 제목</TH>
		              <TH class="th_bs">수정</TH>
		              <TH class="th_bs">삭제</TH>
		            </TR>
		         </thead>
		        
		        <tbody>
		           <c:forEach var="qnaVO" items="${list }">
		                <c:set var='qnano' value="${qnaVO.qnano }" />
		                <c:set var='memberno' value="${qnaVO.memberno }" />
		                <c:set var='title'  value="${qnaVO.title }" />
		                <c:set var='content'  value="${qnaVO.content }" />
		                    
		                <tr>
		                    <td class="th_bs">
		                    <a href="./read.do?qnano=${qnano}&now_page=${param.now_page }&title=${param.title}&answerno=${param.answerno}">${title }</a>
		                    </td>
		                    <td class="th_bs"><A href="./my_qna_update.do?qnano=${qnano }&memberno=${memberno}"  title="수정"><i class="fas fa-pencil-alt"></i></A></td>
		                    <td class="th_bs"><A href="./my_qna_delete.do?qnano=${qnano }" title="삭제"><i class="fas fa-trash-alt" ></i></A></td>
		                </tr>
		                
		               
		                
		           </c:forEach>
		        </tbody>
		        </table>
		            <hr>
					<!-- 페이지 목록 출력 부분 시작 -->
					<DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
					<!-- 페이지 목록 출력 부분 종료 -->
		</div>
   </div>
</div>


<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
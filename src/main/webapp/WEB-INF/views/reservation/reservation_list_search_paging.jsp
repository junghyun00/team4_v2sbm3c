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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container-fluid">
        <div class="row" style='border: solid 1px;'>
                
                <%-- ******************** 검색 시작 ******************** --%>
                <DIV style="text-align: center; width: 100%;">  
                    <form name='frm' id='frm' method='get' action='./reservation_list_search_paging.do'>
                      <input type='text' name='title' id='title' placeholder=" 예약 정보 검색해주세요." value='${param.carno }' style='margin:15px; width: 50%;'>
                      <button type='submit' type="button" class="btn btn-dark">검색</button>
                      <c:if test="${param.carno.length() > 0 }">
                        <button type='button' 
                                     onclick="location.href='./reservation_list_search_paging.do?carno='" class="btn btn-dark">검색 취소</button>  
                      </c:if> 
                    </form>
                  </DIV>
                  <DIV style='margin:15px; width: 100%;'>
                  
                  </DIV>
                  <%-- ******************** 검색 종료 ******************** --%>
                
                <table class="table">
                <colgroup>
                  <col style="width: 10%;"></col>
                  <col style="width: 75%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">예약 번호</TH>
                      <TH class="th_bs">차량 번호</TH>
                      <TH class="th_bs">예약 일시</TH>
                      <TH class="th_bs">수정</TH>
                      <TH class="th_bs">삭제</TH>
                    </TR>
                 </thead>
                
                <tbody>
                   <c:forEach var="reservationVO" items="${list }">
                        <c:set var='reserveno' value="${reservationVO.reserveno }" />
                        <c:set var='memberno' value="${reservationVO.memberno }" />
                        <c:set var='carno'  value="${reservationVO.carno }" />
                        <c:set var='parkno'  value="${reservationVO.parkno }" />
                        <c:set var='reservedate'  value="${reservationVO.reservedate }" />
                        
                        <tr>
                            <td class="th_bs">${reserveno }</td>
                            <td class="th_bs">${carno }</td>
                            <td class="th_bs">${reservedate}</td>
                            <td class="th_bs"><A href="./reservation_update.do?reserveno=${reserveno }"  title="수정"><i class="fas fa-pencil-alt"></i></A></td>
                            <td class="th_bs"><A href="./reservation_delete.do?reserveno=${reserveno}" title="삭제" "><i class="fas fa-trash-alt" ></i></A></td>
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
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
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>${memberVO.name }님이 예약하신 주차장</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        
        <div class="row-fluid">
            <table class="table">
                <colgroup>
                  <col style="width: 20%;"></col>
                  <col style="width: 30%;"></col>
                  <col style="width: 10%;"></col>
                  <col style="width: 30%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">예약한 주차장</TH>
                      <TH class="th_bs">예약 일시</TH>
                      <TH class="th_bs">차량 번호</TH>
                      <TH class="th_bs">전달 사항</TH>
                    </TR>
                 </thead>
                
                <tbody>
                   <c:forEach var="ReservationVO" items="${list }">
                        <c:set var='reserveno' value="${ReservationVO.reserveno }" />
                        <c:set var='memberno' value="${ReservationVO.memberno }" />
                        <c:set var='parkno'  value="${ReservationVO.parkno }" />
                        <c:set var='reservedate'  value="${ReservationVO.reservedate }" />
                        <c:set var='reservetime'  value="${ReservationVO.reservetime }" />
                        <c:set var='carno'  value="${ReservationVO.carno }" />
                        <c:set var='notice'  value="${ReservationVO.notice }" />

                        
                        <tr>
                            <td class="th_bs">${parkVO.parkno} </td>    
                            <td class="th_bs">${reservedate }</td>
                            <td class="th_bs">${carno }</td>
                            <td class="th_bs">${notice }</td>
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
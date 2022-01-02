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
            <h4>${memberVO.name }님이 등록하신 주차장</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        
        <div class="row-fluid">
            <table class="table">
                <colgroup>
                  <col style="width: 20%;"></col>
                  <col style="width: 30%;"></col>
                  <col style="width: 8%;"></col>
                  <col style="width: 8%;"></col>
                  <col style="width: 5%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">이름</TH>
                      <TH class="th_bs">주소</TH>
                      <TH class="th_bs">정보 수정</TH>
                      <TH class="th_bs">이미지 수정</TH>
                      <TH class="th_bs">삭제</TH>
                    </TR>
                 </thead>
                
                <tbody>
                   <c:forEach var="parkVO" items="${list }">
                        <c:set var='parkno' value="${parkVO.parkno }" />
                        <c:set var='memberno' value="${parkVO.memberno }" />
                        <c:set var='name'  value="${parkVO.name }" />
                        <c:set var='phone'  value="${parkVO.phone }" />
                        <c:set var='address'  value="${parkVO.address }" />
                        <c:set var='area'  value="${parkVO.area }" />
                        <c:set var='price'  value="${parkVO.price }" />
                        <c:set var='cmt'  value="${parkVO.cmt }" />
                        <c:set var='file1'  value="${parkVO.file1 }" />
                        
                        <tr>
                            <td class="th_bs">
                                <a href="./my_park_read.do?memberno=${memberno }&parkno=${parkno}">${name }</a>
                            </td>    
                            <td class="th_bs">${address }</a>
                            </td>
                            <td class="th_bs"><A href="./my_park_update.do?memberno=${memberno }&parkno=${parkno}"  title="수정"><i class="fas fa-pencil-alt"></i></A></td>
                            <td class="th_bs"><A href="./my_park_update_file.do?memberno=${memberno }&parkno=${parkno}"  title="이미지 수정"><i class="far fa-image"></i></A></td>
                            <td class="th_bs"><A href="./my_park_delete.do?memberno=${memberno }&parkno=${parkno}"  title="수정"><i class="fas fa-trash-alt" ></i></A></td>
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
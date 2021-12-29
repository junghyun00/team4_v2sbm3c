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

<div class='content_body'>
    <div class="container-fluid">
        <div class="row" style='border: solid 1px;'>
            <div class="col-md-6" style='border: solid 1px;'>
                
                <%-- ******************** 검색 시작 ******************** --%>
                <DIV style="text-align: center; clear: both;">  
                    <form name='frm' id='frm' method='get' action='./park_list_search_paging.do'>
                      <input type='text' name='address' id='address' placeholder="주차할 지역을 입력해주세요." value='${param.address }' style='margin:15px; width: 30%;'>
                      <button type='submit' type="button" class="btn btn-dark">검색</button>
                      <c:if test="${param.address.length() > 0 }">
                        <button type='button' 
                                     onclick="location.href='./park_list_search_paging.do?address='" class="btn btn-dark">검색 취소</button>  
                      </c:if>    
                    </form>
                  </DIV>
                  <%-- ******************** 검색 종료 ******************** --%>
                
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
                                <a href="./read.do?parkno=${parkno}&now_page=${param.now_page }&address=${param.address}">${name }</a>
                            </td>    
                            <td class="th_bs">
                                <a href="./read.do?parkno=${parkno}&now_page=${param.now_page }&address=${param.address}">${address }</a>
                            </td>
                            <td class="th_bs">${price }원</td>
                        </tr>
                        
                   </c:forEach>
                </tbody>
                </table>
                    <hr>
					<!-- 페이지 목록 출력 부분 시작 -->
					<DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
					<!-- 페이지 목록 출력 부분 종료 -->
            </div>
            
            <div class="col-md-6" style='border: solid 1px;'>
                <img src="/ex/images/map.jpg" style='width:100%'>
           </div>
       </div>
    </div>
</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>

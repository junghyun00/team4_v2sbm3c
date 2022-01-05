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
            <h4>회원 목록 (관리자 전용)</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
       
		<div class="row-fluid">
		    <%-- ******************** 검색 시작 ******************** --%>
            <DIV style="text-align: center; clear: both;">  
                <form name='frm' id='frm' method='get' action='./member_list.do'>
                  <input type='text' name='id' id='id' placeholder="아이디를 입력해주세요." value='${param.id }' style='margin:15px; width: 30%;'>
                  <button type='submit' type="button" class="btn btn-dark">검색</button>
                  <c:if test="${param.id.length() > 0 }">
                    <button type='button' 
                                 onclick="location.href='./member_list.do?id='" class="btn btn-dark">검색 취소</button>  
                  </c:if>    
                </form>
              </DIV>
              <%-- ******************** 검색 종료 ******************** --%>
		</div>
       
       
       <div class="row-fluid">
            <table class="table table-hover">
                <colgroup>
                  <col style="width: 15%;"></col>
                  <col style="width: 10%;"></col>
                  <col style="width: 15%;"></col>
                  <col style="width: 20%;"></col>
                  <col style="width: 15%;"></col>
                  <col style="width: 15%;"></col>
                  <col style="width: 10%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">아이디</TH>
                      <TH class="th_bs">성명</TH>
                      <TH class="th_bs">비밀번호</TH>
                      <TH class="th_bs">주소</TH>
                      <TH class="th_bs">전화번호</TH>
                      <TH class="th_bs">이메일</TH>
                      <TH class="th_bs">기타</TH>
                    </TR>
                 </thead>
                
                <tbody>
                   <c:forEach var="MemberVO" items="${list }">
                        <c:set var='memberno' value="${MemberVO.memberno }" />
                        <c:set var='id' value="${MemberVO.id }" />
                        <c:set var='passwd'  value="${MemberVO.passwd }" />
                        <c:set var='name'  value="${MemberVO.name }" />
                        <c:set var='address'  value="${MemberVO.address }" />
                        <c:set var='phone'  value="${MemberVO.phone }" />
                        <c:set var='email'  value="${MemberVO.email }" />

                        
                        <tr>   
                            <td class="th_bs">${id }</td>
                            <td class="th_bs">${name }</td>
                            <td class="th_bs">${passwd }</td>
                            <td class="th_bs">${address }</td>
                            <td class="th_bs">${phone }</td>
                            <td class="th_bs">${email }</td>
                            <td class="th_bs">
	                            <A href="./member_update.do?memberno=${memberno }"  title="수정"><i class="fas fa-pencil-alt"></i></A>
	                            <A href="./member_delete.do?memberno=${memberno }"  title="삭제"><i class="fas fa-trash-alt" ></i></A>
	                        </td>
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

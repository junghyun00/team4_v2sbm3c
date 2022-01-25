<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var='parkno' value="${parkVO.parkno }" />
<c:set var='memberno' value="${parkVO.memberno }" />
<c:set var='name'  value="${parkVO.name }" />
<c:set var='phone'  value="${parkVO.phone }" />
<c:set var='address'  value="${parkVO.address }" />
<c:set var='area'  value="${parkVO.area }" />
<c:set var='price'  value="${parkVO.price }" />
<c:set var='cmt'  value="${parkVO.cmt }" />
<c:set var='file1'  value="${parkVO.file1 }" />


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
        <div class="row" >
            <div class="col-md-6" style="margin: 70px auto;">
                <c:set var="file1" value="${file1.toLowerCase() }" />
	                <c:choose>
		              <c:when test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}">
		                <IMG src="/park/storage/${file1 }" style="width: 90%;"> 
		              </c:when>
		              <c:otherwise> <!-- 기본 이미지 출력 -->
		                <IMG src="/park/images/none1.png" style="margin: 0px 0px 0px 20px; width: 90%;"> 
		              </c:otherwise>
		            </c:choose>
            </div>
            
            <div class="col-md-6" ><br><br>
                <span style="font-size: 1.5em; font-weight: bold; ">${name }</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">주소 [ ${address } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">구역번호 [ ${area } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">주차장 전화번호 [ ${phone } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">1 시간 당 가격 [ ${price }원 ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">주차장 설명 <br> [ ${cmt } ]</span><br><br><br><br>
                
                
                <button type='button' onclick="history.back();" class="btn btn-dark">돌아가기</button><br>
            </div>

            
        </div>
    </div>
</div>




<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
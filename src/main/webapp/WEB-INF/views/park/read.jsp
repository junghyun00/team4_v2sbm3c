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
<c:set var='memberno' value="${memberVO.memberno }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body' style='margin:70px;'>
    <div class="container border-bottom mb-3">
        <div class="row border-bottom">
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
            
            <div class="col-md-6 mb-3" ><br><br>
                <span style="font-size: 1.5em; font-weight: bold; ">${name }</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">주소 [ ${address } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">구역번호 [ ${area } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">주차장 전화번호 [ ${phone } ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">30분 당 가격 [ ${price }원 ]</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">주차장 설명 <br> [ ${cmt } ]</span><br><br><br><br>
                <button type='button' onclick="location.href='/reservation/reservation_create.do?memberno=${memberno }&parkno=${parkno}'"  class="btn btn-dark">예약</button>
                <button type='button' onclick="history.back();" class="btn btn-dark">돌아가기</button><br>
            </div>
        </div>
    </div>
    <div class="container mt-2">
		<div class="row">
			<h4>후기</h4>
		</div>
		<div class="row col-md-12 p-0">
			<form action="" class="col-md-12 p-0" name="review_create" id="review_create">
				<input type="hidden" name="memberno" value="${memberno }">
				<input type="hidden" name="parkno" value="${parkno }">
				<div class="row col-md-12 p-0">
					<div class="col-md-11">
						<textarea class="form-control" name="cmt" id="cmt" rows="2"></textarea>
					</div>
					<div class="col-md-1 p-0">
						<button type="button" class="btn btn-dark" style="width: 100%; height: 100%; text-align: center;" id="review_submit">글쓰기</button>
					</div>
				</div>
				<div class="row col-md-12 pt-2">
					<div class="col-md-1">
						<label class="form-check-label">
					    	<input type="radio" class="form-check-input" name="grade" value=1>★
					 	</label>
					</div>
					<div class="col-md-1">
						<label class="form-check-label">
					    	<input type="radio" class="form-check-input" name="grade" value=2>★★
					 	</label>
					</div>
					<div class="col-md-1">
						<label class="form-check-label">
					    	<input type="radio" class="form-check-input" name="grade" value=3>★★★
					 	</label>
					</div>
					<div class="col-md-1">
						<label class="form-check-label">
					    	<input type="radio" class="form-check-input" name="grade" value=4>★★★★
					 	</label>
					</div>
					<div class="col-md-2">
						<label class="form-check-label">
					    	<input type="radio" class="form-check-input" name="grade" value=5>★★★★★
					 	</label>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="container mt-2 pt-3">
		<c:forEach var="cmtlist" items="${cmtlist}" >
			<div class="row mt-2 review_parent" id=${cmtlist.reviewno }>
				<div class="col-md-2">
					<i class="fas fa-user" style="margin-right: 7px;"></i>${cmtlist.id }
				</div>
				<div class="col-md-2">
					<c:set var="grade" value="${cmtlist.grade }"></c:set>
					<c:choose>
						<c:when test="${grade eq 1 }">
							★
						</c:when>
						<c:when test="${grade eq 2 }">
							★★
						</c:when>
						<c:when test="${grade eq 3 }">
							★★★
						</c:when>
						<c:when test="${grade eq 4 }">
							★★★★
						</c:when>
						<c:when test="${grade eq 5 }">
							★★★★★
						</c:when>
					</c:choose>
				</div>
				<div class="col-md-2">
					${cmtlist.cmt_date }
				</div>
				<c:set var="memberno" value="${cmtlist.memberno }"></c:set>
				<c:choose>
					<c:when test="${memberno eq param.memberno}">
						<div class="col-md-1 text-center">
							<a href="#" id="review_delete"><i class="far fa-trash-alt" style="font-size: 1rem;"></i></a>
						</div>
					</c:when>
				</c:choose>
			</div>
			<div class="row mt-2 border-bottom">
				<div class="col-md-12" style="font-size: 1.3rem;">
					${cmtlist.cmt }
				</div>		
			</div>
		</c:forEach>
	</div>
</div>
<script type="text/javascript" src="/js/review.js"></script>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
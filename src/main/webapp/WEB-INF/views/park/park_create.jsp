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
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
	        <h4>○○님의 주차장 등록</h4>
	        <div style='border-bottom: solid 3px #555555; '></div>
	        </div>
        </div>
        <div class="row-fluid">
            <form style='margin:20px 10px 10px 50px; ' name='frm' method='POST' action='./park_create.do' class="form-horizontal"
             enctype="multipart/form-data">
                <input type="hidden" name="memberno" value="1">   <!-- 이건 나중에 수정 -->
                
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 이름*</label>
	                <div class="col-md-10">
	                    <input type='text' name='name'  placeholder='주차장 이름을 입력해주세요.' required="required" 
	                       class="form-control" style='width: 50%;'>
	                </div>
	            </div>
	
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 주소*</label>
	                <div class="col-md-10">
	                    <input type='text' name='address' placeholder='주차장 상세 주소를 입력해주세요.' required="required" 
	                       class="form-control" style='width: 80%;'>
	                </div>
	            </div>
	
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 구역번호*</label>
	                <div class="col-md-10">
	                    <input type='text' name='area' placeholder='주차장 구역번호를 입력해주세요.' required="required" 
	                       class="form-control" style='width: 50%;'>
	                </div>
	            </div>
	
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-2" style='color:#555555;; font-weight: bold; '>전화번호*</label>
	                <div class="col-md-10">
	                    <input type='text' name='phone' placeholder='주차장 전화번호를 입력해주세요.' required="required" 
	                       class="form-control" style='width: 50%;'>
	                </div>
	            </div>
	
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 가격*</label>
	                <div class="col-md-10">
	                    <input type='text' name='price' placeholder='1시간 당 가격을 입력해주세요. ' required="required" 
	                       class="form-control" style='width: 50%;'>
	                     숫자만 입력해주세요   
	                </div>
	            </div>
	
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-2" style='color:#555555;; font-weight: bold; '>주차장 이미지*</label>
	                <div class="col-md-10">
	                    <input type='file' name='file1MF' id='file1MF' required="required" 
	                       placeholder="파일 선택" class="form-control" style='width: 30%;'>
	                </div>
	            </div>
	
	            <div class="row" style='margin:40px auto;'>
	                <label class="col-md-10" style='color:#555555;; font-weight: bold; '>주차장에 대한 공지사항, 주의사항 등을 적어주세요. (필수는 아닙니다)</label>
	                <div class="col-md-10">
	                    <textarea type='text' name='cmt' rows="3" 
	                       class="form-control" style='width: 80%;'></textarea>
	                </div>
	            </div>
	            <div class="content_body_bottom">
	                <button type="submit" class="btn btn-dark">등록</button>
	                <button type="button" onclick="history.back();" class="btn btn-dark">취소</button>
	            </div>
            </form>
        </div>

        
    </div>
</div>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>

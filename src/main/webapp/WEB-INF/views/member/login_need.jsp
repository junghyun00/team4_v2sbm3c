<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

<script type="text/javascript">

</script>

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div class='message'>
                <span class="span_fail">
					이용하시려면 회원가입 혹은 로그인을 하셔야합니다.<br><br>
				    <button type='button' onclick="location.href='/member/login.do'" class="btn btn-dark btn-md">로그인</button>       
                    <button type='button' onclick="location.href='/member/create.do'" class="btn btn-dark btn-md">회원 가입</button>
                </span>
            </div>
        </div>
    </div>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>



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

<script>
function deletefun() {
	alert("삭제되었습니다.")
}
</script>

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
        <form name='frm' method='POST' action='./delete.do'>
            <input type="hidden" name="memberno" value='${param.memberno }'>
            
            <div class='message' style="font-size: 1.2em; font-weight: bold; ">
                회원을 삭제하면 복구 할 수 없습니다.
                삭제하시겠습니까?<br><br>
                <button type = "submit" onclick = "deletefun()" class="btn btn-dark">삭제</button>
                <button type = "button" onclick = "history.back()" class="btn btn-dark">취소</button>
            </div>
	    </form>
        </div>
    </div>
</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

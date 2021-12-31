<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<div class='content_body' style='margin:70xp;'>
    <div class="container" >
            <div class="col-md-6" ><h2>예약하기</h2>
            </div><br>
        <div class="row" >
 
            
            <div class="col-md-6" style="text-align:center;'" ><br>
               
                <button type='button' onclick=""location.href='./reservation/park_list_search_paging.do'" class="btn btn-dark">예약</button>
                <button type='button' onclick="history.back();" class="btn btn-dark">돌아가기</button><br>
            </div>
        </form>
            
        </div>
    </div>
</div>




<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
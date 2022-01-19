<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var='reserveno' value="${reservationVO.reserveno }" />
<c:set var='memberno' value="${reservationVO.memberno }" />
<c:set var='parkno'  value="${reservationVO.parkno }" />
<c:set var='reservedate'  value="${reservationVO.reservedate }" />
<c:set var='reservetime'  value="${reservationVO.reservetime }" />
<c:set var='carno'  value="${reservationVO.carno }" />
<c:set var='notice'  value="${reservationVO.notice }" />

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
function reser_update() {
    alert("예약이 수정되었습니다.")
}
</script>

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>${memberVO.id }(${memberVO.name })님의 예약 수정</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        <div class="row-fluid" style='margin:30px 0px 0px 80px;'>
            <form style="width:90%;" name="frm" method='POST'  action='./my_reser_update.do'>
                <input type="hidden" name="reserveno" value="${reserveno}" required="required">
                <input type="hidden" name="memberno" value="${memberno}" required="required">
                <input type="hidden" name="parkno" value="${parkno}" required="required">
                <span style="font-size: 1.5em; font-weight: bold; ">${parkVO.name }</span><br><br>
                <span style="font-size: 1.2em; font-weight: bold;">${parkVO.address }</span><br><br>
                <span style="font-size: 1.0em; font-weight: bold;">1 시간 당 가격 [ ${parkVO.price }원 ]</span><br><br><br>
                <table class="table table-bordered">
                    <tbody>
                            <tr>
                                <th style="width:40%;">차량번호</th>
                                <td style="width:40%;"><input type="text"  id="carno" name="carno" style="width:100%;" title="차량번호" placeholder="차량 번호를 입력하세요." value='${carno }'   required="required" ></td>
                            </tr>
                            <tr>
                                <th style="width:40%;">예약날짜</th>
                                <td style="width:40%;"><input type="date"  id="reservedate" class="reservedate" name="reservedate" style="width:100%;" title="예약일시" value='${reservedate }'  required="required"  min="2022-01-01" max="2022-12-31"></td>
                            </tr>
                            <tr>
                                <th style="width:40%;">예약시간</th>
                                <td style="width:40%;"><input type="time"  id="reservetime"  class="reservetime"  name="reservetime" style="width:100%;" title="예약시간" value='${reservetime }'  required="required"  ></td>
                            </tr>  
                            <tr>
                                <th style="width:40%;">전달 사항</th>
                                <td style="width:40%;"><textarea type="text"  id="notice" name="notice" style="width:100%;" title="전달사항" placeholder="전달 사항을 입력하세요." >${notice }</textarea></td>
                            </tr>
                            
                    </tbody>
                </table>
                <br>
                <button type='submit' onclick = "reser_update()"  class="btn btn-dark">예약 수정</button>
                <button type='button' onclick="history.back();" class="btn btn-dark">돌아가기</button><br>
            </form>
        </div>
    </div>
</div>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>
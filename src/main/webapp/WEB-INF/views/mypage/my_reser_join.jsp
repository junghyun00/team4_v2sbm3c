<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 



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

<script type="text/javascript">
function updatefun() {
    alert("수정되었습니다.")
}


function delete_reser_ajax(reserveno) {
    var params= "";
    params = 'reserveno=' + reserveno;

    $.ajax({
        url: '/mypage/my_reservation_delete.do',
        type: 'get',
        cache: false,
        async: true,
        dataType: 'json',
        data: params,
        success: function(rdata) {
            var reserveno = rdata.reserveno;
            var memberno = rdata.memberno;
            var parkno = rdata.parkno;
            var reservedate = rdata.reservedate;
            var reservetime = rdata.reservetime;
            var carno = rdata.carno;
            var notice = rdata.notice;

            var reser_delete = $('#reser_delete');
            $('#reserveno', reser_delete).val(reserveno);
            $('#memberno', reser_delete).val(memberno);
            $('#parkno', reser_delete).val(parkno);
            $('#reservedate', reser_delete).val(reservedate);
            $('#reservetime', reser_delete).val(reservetime);
            $('#carno', reser_delete).val(carno);
            $('#notice', reser_delete).val(notice);


			console.log('-> reserveno:' + reserveno);
			console.log('-> memberno:' + memberno);
			console.log('-> parkno:' + parkno);
			console.log('-> reservedate:' + reservedate);
			console.log('-> reservetime:' + reservetime);
			console.log('-> carno:' + carno);
			console.log('-> notice:' + notice);


            var msg = '예약을 취소하시겠습니까?'
            $('#modal_content').attr('class', 'alert alert-danger');
            $('#modal_title').html('예약 취소');
            $('#modal_content').html(msg);
            $('#modal_panel').modal();
            
            
        },
        error: function(request, status, error) {
            console.log(error);
        }
    }); 
}
</script>

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>

    <!-- ******************** Modal 알림창 시작 ******************** -->
    <div id="modal_panel" class="modal fade" role="dialog">
        <div class="modal-dialog">
        <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id='modal_title'></h4><!-- 제목 -->
                    <button type="button" class="close" data-dismiss="modal">×</button>
                </div>
                <div class="modal-body">
                    <p id='modal_content'></p>  <!-- 내용 -->
                </div>
                <div class="modal-footer">
                   <FORM name='reser_delete' id='reser_delete' method='POST' action='./my_reservation_delete.do'>
                       <input type='hidden' name='reserveno' id='reserveno' >
                       <input type='hidden' name='memberno' id='memberno' >
                       
                       <button  type="submit" id='submit' class="btn btn-default" >삭제</button>
                   </FORM>
                </div>
            </div>
        </div>
    </div>
    <!-- ******************** Modal 알림창 종료 ******************** -->

    <div class="container">
        <div class="row-fluid">
            <div style='margin:30px;'>
            <h4>${memberVO.id }(${memberVO.name })님이 예약하신 주차장</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        
        <div class="row-fluid">
            <table class="table table-hover">
                <colgroup>
                  <col style="width: 15%;"></col>
                  <col style="width: 20%;"></col>
                  <col style="width: 10%;"></col>
                  <col style="width: 20%;"></col>
                  <col style="width: 8%;"></col>
                  <col style="width: 8%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">예약한 주차장</TH>
                      <TH class="th_bs">예약 일시</TH>
                      <TH class="th_bs">차량 번호</TH>
                      <TH class="th_bs">전달 사항</TH>
                      <TH class="th_bs">예약 변경</TH>
                      <TH class="th_bs">예약 취소</TH>
                    </TR>
                 </thead>
                
                <tbody>
                   <c:forEach var="Park_ReservationVO" items="${list }">
                        <c:set var='p_parkno' value="${Park_ReservationVO.p_parkno }" />
                        <c:set var='p_name' value="${Park_ReservationVO.p_name }" />
                        <c:set var='reserveno'  value="${Park_ReservationVO.reserveno }" />
                        <c:set var='memberno'  value="${Park_ReservationVO.memberno }" />
                        <c:set var='parkno'  value="${Park_ReservationVO.parkno }" />
                        <c:set var='reservedate'  value="${Park_ReservationVO.reservedate }" />
                        <c:set var='reservetime'  value="${Park_ReservationVO.reservetime }" />
                        <c:set var='carno'  value="${Park_ReservationVO.carno }" />
                        <c:set var='notice'  value="${Park_ReservationVO.notice }" />
                        <c:set var='memberno'  value="${memberVO.memberno }" />

                        
                        <tr>
                            <td class="th_bs">${p_name} </td>    
                            <td class="th_bs">
                                <c:set var="date"  value="${reservedate }" />
                                <c:set var="time"  value="${reservetime }" />
                                ${fn:substring(date, 0, 10) }
                                ${fn:substring(time, 10, 16) }
                            </td>
                            
                            <td class="th_bs">${carno }</td>
                            <td class="th_bs">${notice }</td>
                            <td class="th_bs"><A href="./my_reser_update.do?memberno=${memberno }&reserveno=${reserveno}"  title="수정"><i class="fas fa-pencil-alt"></i></A></td>
                            <td class="th_bs"><A href="javascript:delete_reser_ajax(${reserveno })"  title="취소"><i class="fas fa-calendar-times"></i></A></td>
                        </tr>
                        
                   </c:forEach>
                </tbody>
                </table>
        </div>
        
<%--         <fmt:formatDate value="${reservedate }" pattern="yyyy.MM.dd" /> --%>
    </div>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
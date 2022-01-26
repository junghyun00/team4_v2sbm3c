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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">


<script type="text/javascript">

function delete_park_ajax(parkno) {
    var params= "";
    params = 'parkno=' + parkno;

    $.ajax({
        url: '/mypage/my_park_delete_ajax.do',
        type: 'get',
        cache: false,
        async: true,
        dataType: 'json',
        data: params,
        success: function(rdata) {
            var parkno = rdata.parkno;
            var memberno = rdata.memberno;
            var name = rdata.name;
            var phone = rdata.phone;
            var address = rdata.address;
            var area = rdata.area;
            var price = rdata.price;
            var cmt = rdata.cmt;
            var file1 = rdata.file1;

            var frm_delete = $('#frm_delete');
            $('#parkno', frm_delete).val(parkno);
            $('#memberno', frm_delete).val(memberno);
            $('#name', frm_delete).val(name);
            $('#phone', frm_delete).val(phone);
            $('#address', frm_delete).val(address);
            $('#area', frm_delete).val(area);
            $('#price', frm_delete).val(price);
            $('#cmt', frm_delete).val(cmt);
            $('#file1', frm_delete).val(file1);


            var msg = '삭제하면 복구 할 수 없습니다. 삭제하시겠습니까?'
            $('#modal_content').attr('class', 'alert alert-danger');
            $('#modal_title').html('[ ' + name + ' ]' + ' 주차장 삭제');
            $('#modal_content').html(msg);
            $('#modal_panel').modal();
            
            console.log('-> parkno:' + parkno);
            console.log('-> memberno:' + memberno);
            console.log('-> name:' + name);
            console.log('-> phone:' + phone);
            console.log('-> address:' + address);
            console.log('-> area:' + area);
            console.log('-> price:' + price);
            console.log('-> cmt:' + cmt);
            console.log('-> file1:' + file1);
            
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
<!--                    <FORM name='frm_delete' id='frm_delete' method='POST' action='./my_park_delete.do'> -->
                        <FORM name='frm_delete' id='frm_delete' method='POST' action='./my_park_delete.do'>
                       <input type='hidden' name='parkno' id='parkno' >
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
            <h4>${memberVO.id }(${memberVO.name })님이 등록하신 주차장</h4>
            <div style='border-bottom: solid 3px #555555; '></div>
            </div>
        </div>
        
        <div class="row-fluid">
            <table class="table table-hover">
                <colgroup>
                  <col style="width: 20%;"></col>
                  <col style="width: 30%;"></col>
                  <col style="width: 8%;"></col>
                  <col style="width: 8%;"></col>
                  <col style="width: 5%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">이름</TH>
                      <TH class="th_bs">주소</TH>
                      <TH class="th_bs">정보 수정</TH>
                      <TH class="th_bs">이미지 수정</TH>
                      <TH class="th_bs">삭제</TH>
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
                                <a href="./my_park_read.do?memberno=${memberno }&parkno=${parkno}">${name }</a>
                            </td>    
                            <td class="th_bs">${address }</a>
                            </td>
                            <td class="th_bs"><A href="./my_park_update.do?memberno=${memberno }&parkno=${parkno}"  title="수정"><i class="fas fa-pencil-alt"></i></A></td>
                            <td class="th_bs"><A href="./my_park_update_file.do?memberno=${memberno }&parkno=${parkno}"  title="이미지 수정"><i class="far fa-image"></i></A></td>
                            <!-- <td class="th_bs"><A href="./my_park_delete.do?memberno=${memberno }&parkno=${parkno}"  title="삭제"><i class="fas fa-trash-alt" ></i></A></td> -->
                            <td class="th_bs"><A href="javascript:delete_park_ajax(${parkno })"  title="삭제"><i class="fas fa-trash-alt" ></i></A></td>
                        </tr>
                        
                   </c:forEach>
                </tbody>
                </table>
        </div>
    </div>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=2.0, width=device-width" />
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maps.google.com/maps/api/js?key=AIzaSyCooY7K9qsN4Bjm4_vMBZLw2fmzB29CwC0&sensor=false" type="text/javascript"></script>
<script src="../google_map/jquery.fn.gmap.js" type="text/javascript"></script>
<script src="../google_map/jquery.ui.map.extensions.js" type="text/javascript"></script>



<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">



<script type="text/javascript">

$(function() {
	// 초기 위치를 서울 중심 으로 설정, 좌표 객체 생성
	var Startlatlng = new google.maps.LatLng(37.53020259711349, 127.00127306458398);
	// 초기 위치 지도 출력
	$("#map_canvas").gmap({
	  "center" : Startlatlng,  // 지도 중심을 좌표 중심으로 이동
	  "zoom" : 13        // 1 ~ 21
	});

// 	var address = document.getElementById('address')
// 	alert(address)
});





// function search_place(address) {
// 	console.log('-> address:' + address);
// // 	address = address.replace(/\(/gi, '&#40');
// //     address = address.replace(/\)/gi, '&#41');
// //     alert('address () 변환: ' + address); //return;

// //     var url = 'https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCooY7K9qsN4Bjm4_vMBZLw2fmzB29CwC0';
// //     var params = '&address=' + address;
// //     $.get(url, params, response_map, 'json');
// }


//Ajax 요청 처리
// function response_map(data) {
// 	var latlng = new google.maps.LatLng(data.results[0].geometry.location.lat, data.results[0].geometry.location.lng)
// 	 // 마커 지정
//     var markerBlue = "https://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png";
//     // 지도 출력
//     $("#map_canvas").gmap({
//       "center" : latlng,
//       "zoom" : 16
//     }); // 1 ~ 21
//     // 마커 출력
//     $("#map_canvas").gmap("addMarker", {
//       "position" : latlng,
//       "icon" : markerBlue
//     });
// }
</script>


</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container-fluid">
        <div class="row" style='border: solid 1px;'>
            <div class="col-md-6" style='border: solid 1px;'>
                
                <%-- ******************** 검색 시작 ******************** --%>
                <DIV style="text-align: center; clear: both;">  
                    <form name='frm' id='frm' method='get' action='./park_list_search_paging.do'>
                      <input type='text' name='address' id='address' placeholder="주차할 지역을 입력해주세요." value='${param.address }' style='margin:15px; width: 40%;'>
                      <button type='submit' id='submit' class="btn btn-dark">검색</button>
                      <c:if test="${param.address.length() > 0 }">
                        <button type='button' 
                                     onclick="location.href='./park_list_search_paging.do?address='" class="btn btn-dark">검색 취소</button>  
                      </c:if>    
                    </form>
                  </DIV>
                  <%-- ******************** 검색 종료 ******************** --%>
                
                <table class="table table-hover">
                <colgroup>
                  <col style="width: 20%;"></col>
                  <col style="width: 30%;"></col>
                  <col style="width: 10%;"></col>
                </colgroup>           
                
                <thead>  
                    <TR>
                      <TH class="th_bs">주차장 이름</TH>
                      <TH class="th_bs">주소</TH>
                      <TH class="th_bs">1시간 당 <br>가격</TH>
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
                        <c:set var='memberno' value="${memberVO.memberno }" />
                        
                        <tr>
                            <td class="th_bs">
                                <a href="./read.do?memberno=${memberno }&parkno=${parkno}&now_page=${param.now_page }&address=${param.address}">${name }</a>
                            </td>    
                            <td class="th_bs">
                                <a href="./read.do?memberno=${memberno }&parkno=${parkno}&now_page=${param.now_page }&address=${param.address}">${address }</a>
                            </td>
                            <td class="th_bs">${price }원</td>
                        </tr>
                        
                   </c:forEach>
                </tbody>
                </table>
                    <hr>
					<!-- 페이지 목록 출력 부분 시작 -->
					<DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
					<!-- 페이지 목록 출력 부분 종료 -->
            </div>
            
            <div class="col-md-6" style='border: solid 1px;'>
                <div id="map_canvas" style='width: 100%; height: 500px'></div>
           </div>
       </div>
    </div>
</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>

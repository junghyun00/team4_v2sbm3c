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
<script src="https://maps.google.com/maps/api/js?key=&sensor=false" type="text/javascript"></script>
<script src="../google_map/jquery.fn.gmap.js" type="text/javascript"></script>
<script src="../google_map/jquery.ui.map.extensions.js" type="text/javascript"></script>

<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">



<script type="text/javascript">

// 지도에 마커를 표시하려면 주소 배열이 필요해서 주소 배열, 목록 만들기
var list = '${list}';
var park_count = '${search_count}';

var split1 = list.split(", ");
//document.writeln(split1.length+ "<br>");

var address_list = [];
for(var i = 1; i <= park_count; i++) {
    var num = 4+(i-1)*11;

    //console.log("split1[num] : ", split1[num]);
    var split1num = split1[num];
    //console.log("split1num : ", split1num);
    
    var split2 = split1num.split("=");
    //console.log("split2[0] : ", split2[0]);
    //console.log("split2[1] : ", split2[1]);
    //document.writeln(i + ". " + split2[1] + "<br>");

    address_list[i-1] = split2[1];          
}
// console.log(address_list);
/////////////                 완성                        ///////////////


var map;
var markersArray = [];   // 마커 배열
var coordinates = [];   // 좌표 배열




function initialize() {
	var url = 'https://maps.googleapis.com/maps/api/geocode/json?key=';
    var param = [];


	
    for (var i = 0; i < 2; i++) {
    	const addr = address_list[i];
//     	console.log(i, ". addr : " , addr);
    	
    	var address = addr.replace(/\(/gi, '&#40');
    	address = address.replace(/\)/gi, '&#41');    // 주소에 있는 괄호 없앰
//     	console.log('address : ', address);

    	
    	

    	var params = '&address=' + address;
    	console.log('params ', params);


//         param[i] = address;

        $.ajax({
            type: "GET",
            url: url,  
            data: params,  
            success: response_map,  
          });
        
    }


    console.log('param', param);
    console.log(typeof(param));
    
}




function response_map(data){ 
	console.log("response_map 함수로 들어옴");
    // 전송된 위도, 경도를 설정
    var latlng = new google.maps.LatLng(data.results[0].geometry.location.lat, data.results[0].geometry.location.lng);
//     console.log("latlng"+latlng);
//     console.log(typeof(latlng));
    
    coordinates.push(latlng);
//     console.log(typeof(coordinates));
//     console.log(coordinates);
//     alert(coordinates);
//     console.log("coordinates"+coordinates);


//     console.log("coordinates[0] : "+coordinates[0]);
//     console.log("coordinates[1] : "+coordinates[1]);
//     console.log("----------------------------------------");










 // 지도 출력 옵션
//     var mapOptions = {
//       streetViewControl : false,
//       mapTypeControl : true, // 지도 출력 종류 선택
//       mapTypeId : google.maps.MapTypeId.ROADMAP // 일반 지도
//       // mapTypeId : google.maps.MapTypeId.SATELLITE // 위성 지도
//     }
    
//     // 지도를 출력할 DIV 객체 추출
//     map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

//     // 마커를 지도에 묶기(출력)위해서 저장소에 마커의 수만큼 
// //     coordinates 배열에 있는 좌표 객체를 저장 
//     bounds = new google.maps.LatLngBounds();
//     for (var i = 0; i < 5; i++) {
//       bounds.extend(coordinates);
//     }
//     map.fitBounds(bounds);
//     addMarker(latlng);















    

    
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
    
}


// 지도에 마커출력
// function addMarker(latlng) {
//   marker = new google.maps.Marker({
//     position : latlng,
//     map : map
// });
// markersArray.push(marker);

// }


window.onload = initialize; // 시작 함수 지정 및 호출

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

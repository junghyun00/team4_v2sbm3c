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
var list2 = '${list2}';  // 페이징 안 된 이름, 주소 리스트
// console.log("list2 : "+list2);


var list2_park_count = '${search_count}';
var park_su = '${park_su}';
var split1 = list2.split(", ");
                
//////////////////주차장 이름 name_list에 배열로 저장/////////////////////
var name_list = [];
for(var i = 1; i <= list2_park_count; i++) {
    var num1 = 2+(i-1)*11;
    var split1num1 = split1[num1];
    var split3 = split1num1.split("=");

    name_list[i-1] = split3[1];          
}


//////////////////주차장 주소 address_list에 배열로 저장/////////////////////
var address_list = [];
for(var i = 1; i <= list2_park_count; i++) {
    var num = 4+(i-1)*11;
    var split1num = split1[num];
    var split2 = split1num.split("=");

    address_list[i-1] = split2[1];          
}


var map;
var markersArray = [];   // 마커 배열
var coordinates = [];   // 좌표 배열
var infowindow = new google.maps.InfoWindow();

function initialize() {
    var url = 'https://maps.googleapis.com/maps/api/geocode/json?key=';
    var param = [];

    for (var i = 0; i < list2_park_count; i++) {
        const addr = address_list[i];

        address = addr.replace(/\(/gi, '&#40');
        address = addr.replace(/\)/gi, '&#41');    // 주소에 있는 괄호 없앰

        var params = '&address=' + address;

        $.ajax({
            type: "GET",
            url: url,  
            data: params,  
            success: response_map,  
          });
    }
}            
                
                
function response_map(data){ 
    var latlng = new google.maps.LatLng(data.results[0].geometry.location.lat, data.results[0].geometry.location.lng);
    
 // 지도 출력 옵션
var mapOptions = {
  streetViewControl : false,
  mapTypeControl : true, // 지도 출력 종류 선택
  mapTypeId : google.maps.MapTypeId.ROADMAP, // 일반 지도
}
    
// 지도를 출력할 DIV 객체 추출
map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

coordinates.push(latlng);

bounds = new google.maps.LatLngBounds();
for (var i = 0; i < coordinates.length; i++) {
    bounds.extend(coordinates[i]);
}
map.fitBounds(bounds); // 마커가 전부 출력되도록 중심점과 사이즈 자동 조절
for (var i = 0; i < coordinates.length; i++) {
    addMarker(coordinates[i]);
  }    
}


function addMarker(latlng) {
    marker = new google.maps.Marker({
      position : latlng,
      map : map
      });
    markersArray.push(marker);
    
    google.maps.event.addListener(marker, 'click', function(event) {
        popInfoWindow(latlng);
    });
}

function popInfoWindow(latlng) {
    console.log("popInfoWindow 들어옴");
       
                    
    var geocoder = new google.maps.Geocoder(); // 주소와 좌표 변환
    map.setCenter(latlng);
    addMarker(latlng);   // 마커출력
    geocoder.geocode({'latLng' : latlng // 좌표 지정
    }, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) { // 구굴 맵 지원 여부
        if (results[0]) {              
            var lat = latlng.lat();
            var lng = latlng.lng();
            
            
            var cont='';
            var address = results[0].formatted_address;
            
            // 주소에서 대한민국 추출
            var space_position = address.indexOf(' ');
            address = address.substring(space_position);
            address_s = address.substr(1);
            
            for (var j=0; j<address_list.length; j++) {
                if (address_list[j] == address_s) {
                    cont = '<div id="content">';
                    cont += '<br><div id="address">';
                    cont += '<b>'+ name_list[j] +'</b><br>';
                    cont += '<br></div>';
                    cont += '<p><b>주소 :</b> ' + address_s + '</p>';
                    cont += '</div>';
                }
            }
                       
            infowindow.setContent(cont);
            infowindow.open(map, marker);
            } else {
              alert("No results found");
            }
      } else {
        alert("Geocoder failed due to: " + status);
      }
    });
}
                
                
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

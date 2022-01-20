<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>http://localhost:9091/google_map/map.jsp</title>

<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<meta name="viewport"
  content="user-scalable=yes, initial-scale=1.0, maximum-scale=2.0, width=device-width" />
 
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
  
<script src="https://maps.google.com/maps/api/js?key=AIzaSyCooY7K9qsN4Bjm4_vMBZLw2fmzB29CwC0&sensor=true" type="text/javascript"></script>
<script src="./jquery.fn.gmap.js" type="text/javascript"></script>
<script src="./jquery.ui.map.extensions.js" type="text/javascript"></script>
 
 <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

 
 
<script type="text/javascript">
  $(function() {
    //초기 위치를 '군자역'으로 설정, 좌표 객체 생성
    var latlng = new google.maps.LatLng(37.53020259711349, 127.00127306458398)
    // 마커(icon) 객체 생성
    // var markerBlue = "./images/building.png";
    var markerBlue = "http://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png";
    
    // 지도 출력
    $("#map_canvas").gmap({
      "center" : latlng,  // 지도 중심을 좌표 중심으로 이동
      "zoom" : 13        // 1 ~ 21
    }); 
    // 마커 출력
//     $("#map_canvas").gmap("addMarker", {
//       "position" : latlng,    // 마커 출력 위치
//       "icon" : markerBlue  // 마커 할당
//     });
  });
</script>
</head>
 
<body>
  <div>
    <div>
      <h2>군자역</h2>
    </div>
    <div id="map_canvas" style='width: 100%; height: 500px'></div>
  </div>
</body>
</html>
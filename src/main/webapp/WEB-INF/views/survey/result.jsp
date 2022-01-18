<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BLOG</title>
    <link href="{% static '/css/style.css' %}" rel="Stylesheet" type="text/css">
    <script type="text/JavaScript"
                 src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script type="text/javascript">
        $(function() {
        	send(); // Django ajax 호출
            $('#btn_previous').on('click', function() { history.back(); });   // 이전
            $('#btn_close').on('click', function() { window.close(); });      // 윈도우 닫기
        });

        function send() {
            var data = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
            // alert('params: ' + params);  // 수신 데이터 확인
            $.ajax({
              url: 'http://localhost:8000/recommend_parking/end_ajax/',  // Spring Boot -> Django 호출
              type: 'get',  // get or post
              cache: false, // 응답 결과 임시 저장 취소
              async: true,  // true: 비동기 통신
              dataType: 'json', // 응답 형식: json, html, xml...
              data: data,      // 데이터
              success: function(rdata) { // 응답이 온경우
                if (rdata.index == 0) {        // 노상주차장 추천 필요
                    $('#parking1').css('display','');
                } else if(rdata.index == 1) { // 노외주차장 추천 필요
                    $('#parking2').css('display','');
                } else {                            // 부설주차장 추천 필요
                    $('#parking3').css('display','');
                } 

                $('#panel').html("");  // animation gif 삭제
                $('#panel').css('display', 'none'); // 숨겨진 태그의 출력

                // --------------------------------------------------
                // 분류 정보에 따른 상품 이미지 SELECT
                //  --------------------------------------------------
              },
              // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우
              error: function(request, status, error) { // callback 함수
                console.log(error);
              }
            });

            $('#panel').show(); // 숨겨진 태그의 출력
            
        }
    </script>
    <style>
        *{
            text-align: center;
        }
        .td_image{
            vertical-align: middle;
            padding: 5px;
            cursor: pointer;
        }
    </style>

</head>
<body>

<DIV style='display: none;'>
    <form name='frm' id='frm'>
        <input type='hidden' name=purposepark value='${param.purposepark }'>
        <input type='hidden' name='preferday' value='${param.preferday }'>
        <input type='hidden' name='reserveperiod' value='${param.reserveperiod }'>
    </form>
</DIV>

<DIV class="container">
    <H2>참여해주셔서 감사합니다.</H2>
    <DIV id='panel' style='margin: 30px auto; width: 90%;'>
        <DIV id='parking1' style='display: none;'> <!-- 노상주차장 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <H2>추천하는 주차장은 노상주차장입니다.</H2><br><br><br>
                        <img id='img1' src="/recommend_parking/images/노상주차장.jpg" style='float:center; height: 200px'>
                    </TD>
                </TR>
            </TABLE>
        </DIV>
            
        <DIV id='parking2' style='display: none;'> <!-- 노외주차장 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <H2>추천하는 주차장은 노외주차장입니다.</H2><br><br><br>
                        <img id='img1' src="/recommend_parking/images/노외주차장.jpg" style='float:center; height: 200px'>
                    </TD>
                </TR>
            </TABLE>
        </DIV>
        
        <DIV id='parking3' style='display: none;'> <!-- 부설주차장 추천 필요 -->
            <TABLE style='margin: 0px auto;'>
                <TR>
                    <TD class='td_image'>
                        <H2>추천하는 주차장은 부설주차장입니다.</H2><br><br><br>
                        <img id='img1' src="/recommend_parking/images/부설주차장.jpg" style='float:center; height: 200px'>
                    </TD>
                </TR>
            </TABLE>
        </DIV>
    </DIV>

    <form id='frm' name='frm' action='' method='GET'>
        <br>
        <DIV style="text-align:center;">
            <button type='button' id='btn_previous' class="btn btn-primary">이전</button>
            <button type='button' id='btn_close' class="btn btn-primary">닫기</button>
        </DIV>
    </form>
</DIV>
</body>
</html>
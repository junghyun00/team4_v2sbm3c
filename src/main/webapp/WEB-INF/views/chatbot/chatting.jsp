<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>챗봇</title>
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
        *{
            margin:0;
            padding:0;
        }
        .container {
            width: 600px;
            margin: 0 auto;
            padding: 10px
        }
        .container h2{
            text-align: left;
            padding: 5px 5px 5px 15px; /* top, right, bottom, left */
            color: #FFBB00;
            border-left: 3px solid #FFBB00;
            margin-bottom: 20px;
        }
        .chatting_pannel {
            background-color: #EFEFEF;
            width: 100%;
            height: 400px;
            overflow-y: scroll; /* scrollbar가 자동으로 생김 */
            padding: 5px;
        }
        .chatting_pannel p{
            font-size: 16px;
            border-radius: 10px;
            display: inline-block;
            padding: 2px 5px;
        }
        input {
            width: 78%;
            height: 25px;
        }
        .send_msg{
            text-align: right;
            color: #000000;
            background-color: yellow;
        }
        .receive_msg{
            text-align: left;
            color: #FFFFFF;
            background-color: #7a7373;
        }
</style>
<script type="text/javascript">
$(function() {
    // 키이벤트 처리
    $('#chatting').on('keydown', function(key) {
      if (key.keyCode == 13) { // Enter
        // alert('enter input');
        send();
      }
    });
})
function send() {
    var msg = $.trim($("#chatting").val()); // 공백 제거후 문자열 산출

    if (msg.length == 0) {
        $('#chatting').val(""); // 공백등 제거
        return; // 실행 중지
    }

    $("#chatting_pannel").append("<div style='text-align: right'><p class='send_msg'>문의: " + msg + "</p></div>");
    $('#chatting').val("");
    // var params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
    var params = 'msg=' + msg;
   /*  alert('params: ' + params);  // 수신 데이터 확인 */
    $.ajax({
        url: 'http://127.0.0.1:8000/chatbot/chatting_query',
        type: 'get',  // get or post
        cache: false, // 응답 결과 임시 저장 취소
        async: true,  // true: 비동기 통신
        dataType: 'json', // 응답 형식: json, html, xml...
        data: params,      // 데이터
        success: function(rdata) { // 응답이 온경우
            // alert(rdata.result);
            var msg = rdata.result;
            if(msg != null && msg.trim() != ''){
                $("#chatting_pannel").append("<p class='receive_msg'>안내 센터: " + msg + "</p><br>");
            }
            $('#panel').html(str);  // document.getElementById('panel').innerHTML=str;
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우
        error: function(request, status, error) { // callback 함수
        console.log(error);
        }
    });
    // $('#panel').html('처리중입니다....');  // 텍스트를 출력하는 경우
    // $('#panel').html("<img src='{% static '/images/ani04.gif' %}' style='width: 10%;'>");
    // $('#panel').show(); // 숨겨진 태그의 출력
}
// function receive() {
//     var msg = '감사합니다.';
//     if(msg != null && msg.trim() != ''){
//         $("#chatting_pannel").append("<p class='receive_msg'>안내 센터: " + msg + "</p><br>");
//     }
// }
</script>
</head>
<body>
    <div id="container" class="container">
        <h2>실시간 문의</h2>
        <div id="chatting_pannel" class="chatting_pannel">
        </div>
        <div style='margin: 0px auto;'>
            메시지
            <input id="chatting" name='chatting' placeholder="보내실 메시지를 입력하세요."
                   value=""></th>
            <button onclick="send()" id="sendBtn" class="btn btn-info">보내기</button></th>
        </div>
    </div>
</body>
</html>

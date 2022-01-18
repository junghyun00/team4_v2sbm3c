$('#submit_btn').click(function (e) { 
	recommend_parking();
    let purposepark = $(':radio[name="purposepark"]:checked').val();
    let preferday = $(':radio[name="preferday"]:checked').val();
    let reserveperiod = $(':radio[name="reserveperiod"]:checked').val();
    let memberno = $('#memberno').val()
    if ($('input:radio[name=purposepark]').is(':checked') == false){
        alert("첫번째 항목을 체크해 주세요")
    } 
    else if ($('input:radio[name=preferday]').is(':checked') == false) {
        alert("두번째 항목을 체크해 주세요")
    }
    else if ($('input:radio[name=reserveperiod]').is(':checked') == false) {
        alert("세번째 항목을 체크해 주세요")
    }
    else {
        let data = {"memberno": memberno ,
                    "purposepark" : purposepark, 
                    "preferday": preferday, 
                    "reserveperiod": reserveperiod}
        $.ajax({
            type: "GET",
            url: "/survey_create.do",
            data: data,
            dataType: "JSON",
            success: function (response) {
                if(response.cnt == 1){
                    alert("설문에 응해주셔서 감사합니다")
                    location.href = "/"
                } else{
                    alert("설문에 다시 응해주세요")
                }
            }
        });  
        
       
        

    }  
    
});

 function recommend_parking() {
     	var url = '/survey/result.do';
  		var win = window.open(url, 'AI', 'width=800px, height=750px');

  		var x = (screen.width - 1000) / 2;
  		var y = (screen.height - 700) / 2;

  		win.moveTo(x, y); // 화면 중앙으로 이동
    }




$("#review_submit").click(function (e) { 
    let data = $("#review_create").serialize()
    $.ajax({
        type: "POST",
        url: "/review/review_create.do",
        data: data,
        dataType: "JSON",
        success: function (response) {
            if (response.cnt == 1){
                location.reload()
            }
            else {
                alert("후기 작성에 실패하였습니다.")
            }
        }
    });
});

$("#review_edit").click(function (e) { 
    $("#review_edit_box").css("display", "block")
});

$("#revire_edit_submit").click(function (e) { 
      
});
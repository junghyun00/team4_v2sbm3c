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

$("#review_delete").click(function (e) { 
    let reviewno = $("#review_delete").parents(".review_parent").attr('id')
    let data = {"reviewno" : reviewno}
    $.ajax({
        type: "POST",
        url: "/review/delete.do",
        data: data,
        dataType: "JSON",
        success: function (response) {
            if(response.cnt == 1){
                alert("댓글을 삭제하였습니다.")
                location.reload()
            } else {
                alert("댓글 삭제에 실패하였습니다.")
            }
        }
    });
});
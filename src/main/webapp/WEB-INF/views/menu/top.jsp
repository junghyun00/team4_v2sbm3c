<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
function logoutfun() {
    alert("로그아웃 되었습니다.")
}

function chatting() {
    var url = '/chatbot/chatting.do';
    var win = window.open(url, '챗봇', 'width=700px, height=630px');

    var x = (screen.width - 700) / 2;
    var y = (screen.height - 630) / 2;

    win.moveTo(x, y); // 화면 중앙으로 이동
}
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3 fixed-top">
   <a class="navbar-brand" href="/">주차장</a>
   <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbar_list" aria-controls="navbar_list"
      aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
   </button>
   <div class="collapse navbar-collapse" id="navbar_list">
      <ul class="navbar-nav mr-auto" id="cate_bar">
         <li class="nav-item">
            <button class="btn btn-dark pt-2" id="manage_btn"  onclick="location.href='/park/park_list_search_paging.do?memberno=${memberno }'">주차 목록</button>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/park/park_create.do?memberno=${memberno }'">주차 등록</button>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/qna/qna_list_search_paging.do?memberno=${memberno }'" >QNA </button>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="javascript: chatting()" >챗봇 </button>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/recommend/recommend_surveyform.do?memberno=${memberno }'" >주차장 추천 </button>
            
         </li>
      </ul>
      <ul class="navbar-nav">
         <li class="nav-item" >

         
		<c:choose>
			<c:when test="${sessionScope.id == null}"> <%-- 로그인 안 한 경 우 --%>
			 <button class="btn btn-dark pt-2" onclick="location.href='/member/login.do'" id="login_btn">로그인</button>
			</c:when>
            <c:otherwise>   <!-- 로그인 했는데-->
                <c:choose>
	                <c:when test="${sessionScope.grade == 10}">     <!-- 관리자인 경우 -->
	                    <span class="dropdown">
			                <button class="btn btn-dark pt-2" id="manage_btn" data-toggle="dropdown">관리자 전용</button>   
			                    <ul class="dropdown-menu" style="margin:15px 0px 0px 0px ; background-color: #545a5e;">
			                        <li><button class="dropdown-item" onclick="location.href='/admin/member_list.do'" style="color:#fff; background-color: #545a5e;">회원 관리</button></li>
			                        <li><button class="dropdown-item" onclick="location.href='/admin/park_list.do'" style="color:#fff; background-color: #545a5e;">주차장 관리</button></li>
			                        <li><button class="dropdown-item" onclick="location.href='/admin/reser_list.do'" style="color:#fff; background-color: #545a5e;">예약 관리</button></li>
			                    </ul>
			            </span>
	                </c:when>
	              </c:choose>
				<span class="dropdown">
					<button class="btn btn-dark pt-2" id="manage_btn" data-toggle="dropdown">마이페이지</button>   
					    <ul class="dropdown-menu" style="margin:15px 0px 0px 0px ; background-color: #545a5e;">
					        <li><button class="dropdown-item" onclick="location.href='/mypage/me_update.do?memberno=${memberno }'" style="color:#fff; background-color: #545a5e;">내 정보 변경 </button></li>
					        <li><button class="dropdown-item" onclick="location.href='/mypage/passwd_update.do?memberno=${memberno }'" style="color:#fff; background-color: #545a5e;">비밀번호 변경 </button></li>
							<li><button class="dropdown-item" onclick="location.href='/mypage/my_reser_join.do?memberno=${memberno }'" style="color:#fff; background-color: #545a5e;">내가 예약한 주차장</button></li>
							<li><button class="dropdown-item" onclick="recommend_parking()" style="color:#fff; background-color: #545a5e;">내가 등록한 주차장</button></li>
					    </ul>
				</span>
			 <button class="btn btn-dark pt-2" onclick="location.href='/member/logout.do', logoutfun() " id="login_btn"> ${sessionScope.id } 로그아웃</button>
			</c:otherwise>
		</c:choose>  
         
         
         
            
         </li>
      </ul>
   </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

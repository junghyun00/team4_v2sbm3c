<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
function logoutfun() {
    alert("로그아웃 되었습니다.")
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
            <button class="btn btn-dark pt-2" id="manage_btn"  onclick="location.href='/park/park_list_search_paging.do'">주차 목록</button>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/park/park_create.do'">주차 등록</button>
            <span class="dropdown">
                <button class="btn btn-dark pt-2" id="manage_btn" data-toggle="dropdown">마이페이지</button>   
                    <ul class="dropdown-menu" style="margin:15px 0px 0px 0px ; background-color: #545a5e;">
                        <li><button class="dropdown-item" onclick="location.href='/mypage/my_reser_join.do?memberno=${param.memberno }'" style="color:#fff; background-color: #545a5e;">내가 예약한 주차장</button></li>
                        <li><button class="dropdown-item" onclick="location.href='/mypage/my_park.do?memberno=${param.memberno }'" style="color:#fff; background-color: #545a5e;">내가 등록한 주차장</button></li>
                    </ul>
            </span>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/qna/qna_list_search_paging.do'" >QNA </button>
         </li>
      </ul>
      <ul class="navbar-nav">
         <li class="nav-item" >
         <span class="dropdown">
                <button class="btn btn-dark pt-2" id="manage_btn" data-toggle="dropdown">관리자 전용</button>   
                    <ul class="dropdown-menu" style="margin:15px 0px 0px 0px ; background-color: #545a5e;">
                        <li><button class="dropdown-item" onclick="location.href='/member/list.do'" style="color:#fff; background-color: #545a5e;">회원 관리</button></li>
                        <li><button class="dropdown-item" onclick="" style="color:#fff; background-color: #545a5e;">주차장 관리</button></li>
                        <li><button class="dropdown-item" onclick="" style="color:#fff; background-color: #545a5e;">예약 관리</button></li>
                    </ul>
            </span>
         
		<c:choose>
			<c:when test="${sessionScope.id == null}"> <%-- 로그인 안 한 경 우 --%>
			 <button class="btn btn-dark pt-2" onclick="location.href='/member/login.do'" id="login_btn">로그인</button>
			</c:when>
			<c:otherwise>
			 <button class="btn btn-dark pt-2" onclick="location.href='/member/logout.do', logoutfun() " id="login_btn"> ${sessionScope.id } 로그아웃</button>
			</c:otherwise>
		</c:choose>  
         
         
         
            
         </li>
      </ul>
   </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/park/park_list_search_paging.do'">주차 목록</button>
            <button class="btn btn-dark pt-2" id="manage_btn" onclick="location.href='/park/park_create.do'">주차 등록</button>
            <button class="btn btn-dark pt-2" id="manage_btn">마이페이지</button>            
         </li>
      </ul>
      <ul class="navbar-nav">
         <li class="nav-item" >
            <button class="btn btn-dark pt-2" id="login_btn">로그인</button>
         </li>
      </ul>
   </div>
</nav>

<script type="text/javascript" src="/js/index_load.js"></script>
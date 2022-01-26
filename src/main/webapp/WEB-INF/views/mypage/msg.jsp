<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumGgocNaeEum.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='content_body'>
    <div class="container">
        <div class="row-fluid">
            <div class='message'>
                <fieldset class='fieldset_basic'>
                    <UL>
                        <c:choose>
                        
					        <c:when test="${code == 'passwd_update_success'}"> <%-- Java if --%>
					          <LI class='li_none'>
					            <span class="span_success">${name }님(${id }) 패스워드를 변경했습니다.</span>
					          </LI>                                                                      
					          <LI class='li_none'>
					            <button type='button' 
					                         onclick="location.href='/'"
					                         class="btn btn-dark">확인</button>
					           </LI>            
					        </c:when>
					        
					        <c:when test="${code == 'passwd_fail'}">
					          <LI class='li_none'>
					            <span class="span_fail">현재 패스워드가 일치하지 않습니다.</span>
					          </LI>
					          <LI class='li_none'>
					            <button type='button' 
					                         onclick="history.back()"
					                         class="btn btn-dark">다시 시도</button>
					           </LI>     
					        </c:when>
					        
					        <c:when test="${code == 'passwd_update_fail'}"> <%-- Java if --%>
					          <LI class='li_none'>
					            <span class="span_fail">${name }님(${id }) 패스워드 변경에 실패했습니다.</span>
					          </LI>
					          <LI class='li_none'>
                                <button type='button' 
                                             onclick="history.back()"
                                             class="btn btn-dark">다시 시도</button>
                               </LI>                                                                        
					        </c:when>
					        
					        <c:otherwise>
					          <LI class='li_none_left'>
					            <span class="span_fail">알 수 없는 에러로 작업에 실패했습니다.</span>
					          </LI>
					          <LI class='li_none_left'>
					            <span class="span_fail">다시 시도해주세요.</span>
					          </LI>
					          <LI class='li_none'>
                                <button type='button' 
                                             onclick="history.back()"
                                             class="btn btn-dark">다시 시도</button>
                               </LI>  
					        </c:otherwise>
					        
					        
                        </c:choose>
                        
                        <LI class='li_none'><br>
                        
				        <c:choose>
				            <c:when test="${param.cnt == 0 }">
				                <button type='button' onclick="history.back()" class="btn btn-dark">다시 시도</button>    
				            </c:when>
				        </c:choose>
				        
				        </LI>
                     </UL>
                </fieldset>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>
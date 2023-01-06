<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<head>
<script src="https://kit.fontawesome.com/ca93809e69.js" crossorigin="anonymous"></script>
</head>


    
<header class="w3-container w3-xlarge">
	<p class="w3-left"></p> <!-- 카테고리명 페이지마다 추가하기 -->
	<p class="w3-right">
<!-- 		<i class="fa fa-search"></i> -->
	<!-- 로그인 드롭다운 기능! -->	
	<c:choose>
		<c:when test="${not empty sessionScope.sId }">
		<a href="CartList.ca?member_idx=${member_idx }&pageNum=1"><i class="fa fa-shopping-cart w3-margin-right"></i></a>
		<i class="material-icons">cloud</i>
		<i>
		  <button onclick="myFunction()" >
		  	<img src="./images/login_image.png" width="20px" height="20px">
		  </button>
		  <div id="Demo" class="w3-dropdown-content w3-bar-block w3-border">

		    <a href="MemberLogout.me" class="w3-bar-item w3-button">logout</a>
		    <a href="MemberModifyForm.me?id=${sessionScope.sId }" class="w3-bar-item w3-button">정보수정</a>
		    <a href="MemberDeleteForm.me?id=${sessionScope.sId }" class="w3-bar-item w3-button">회원탈퇴</a>
		    <a href="ProductOrderList.po?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="w3-bar-item w3-button">주문내역</a>
		    <a href="LikeList.ca?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="w3-bar-item w3-button">위시리스트</a>
		    
		    <c:choose>
		    	<c:when test="${sessionScope.sId eq 'admin' }">
		    		<a href="Admin.ad?id=${sessionScope.sId }" class="w3-bar-item w3-button">관리자 페이지</a>
		    	</c:when>
		    </c:choose>
		     </div>
		</i>
		</c:when>
		<c:otherwise>
		<div class="w3-dropdown-click" id="logintvar" style="margin-right:34px;">
		
<span><i class="fa-solid fa-cart-shopping fa-xl" onclick="location.href='./CartList.ca'"></i></span>
<span style="margin: 0 5px;"><i class="fa-regular fa-heart fa-xl" onclick="location.href=''"></i></span>
<span><i class="fa-regular fa-user fa-xl" onclick="location.href='LoginMember.me'"></i></span>



<!-- 		<div><a href="LoginMember.me">login</a> | <a href="MemberJoinForm.me">join</a></div> -->
		</div>
		</c:otherwise>
	</c:choose>	
	</p>
	
</header>

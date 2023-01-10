<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<head>
<style>
#Demo{
font-size: 70%;
}
</style>
<script src="https://kit.fontawesome.com/ca93809e69.js" crossorigin="anonymous"></script>
</head>


    
<header class="w3-container w3-xlarge">
	<p class="w3-left"></p> <!-- 카테고리명 페이지마다 추가하기 -->
	<p class="w3-right">
<!-- 		<i class="fa fa-search"></i> -->
<!-- 	<i> -->
       
<%--         <button onmouseover="myFunction()" onclick="location.href='MemberMyPage.me?id=${sessionScope.sId }'" > --%>
<!--            <img src="./images/login_image.png" width="20px" height="20px"> -->
<!--         </button> -->
<!--         <div id="Demo" class="w3-dropdown-content w3-bar-block w3-border" style="font-style:none"> -->

<!--           <a href="MemberLogout.me" class="w3-bar-item w3-button">logout</a> -->
<%--           <a href="MemberModifyForm.me?id=${sessionScope.sId }" class="w3-bar-item w3-button">정보수정</a> --%>
<%--           <a href="MemberDeleteForm.me?id=${sessionScope.sId }" class="w3-bar-item w3-button">회원탈퇴</a> --%>
<%--           <a href="ProductOrderList.po?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="w3-bar-item w3-button">주문내역</a> --%>
<%--           <a href="LikeList.ca?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="w3-bar-item w3-button">위시리스트</a> --%>
          


	<!-- 로그인 드롭다운 기능! -->	
	<c:choose>
		<c:when test="${not empty sessionScope.sId }">
			<div id="logintvar" style="padding-right: 35px;">
			<i class="fa-solid fa-heart fa-sm" onclick="location.href='LikeList.ca?id=${sessionScope.sId}&member_idx=${member_idx }&pageNum=1'" style="cursor: pointer;"></i>
			<i class="fa-solid fa-cart-shopping fa-sm" onclick="location.href='CartList.ca?member_idx=${member_idx}&pageNum=1'" style="margin: 10px; cursor: pointer;"></i>
		 <div class="w3-dropdown-click" id="logintvar">
		 <i class="fa-solid fa-user fa-sm" onmouseover="myFunction()" onclick="location.href='MemberMyPage.me?id=${sessionScope.sId }&member_idx=${member_idx }'" style="margin: 10px;"></i>
		  <div id="Demo" class="w3-dropdown-content w3-bar-block w3-border">
		    <a href="MemberLogout.me"  class="w3-bar-item w3-button">로그아웃</a>
		    <a href="BoardList.bo" class="w3-bar-item w3-button">고객센터</a>
		    
		    <c:choose>
		    	<c:when test="${sessionScope.sId eq 'admin' }">
		    		<a href="Admin.ad?id=${sessionScope.sId }" class="w3-bar-item w3-button">관리자 페이지</a>
		    	</c:when>
		    </c:choose>
		    </div>
		    </div>
		    </div>
		 <div style="float: right;">
	
		</div>
		</c:when>
		<c:otherwise>
		<div id="logintvar" style="margin-right:35px;">
		
<!-- <span style="margin: 0 5px;"><i class="fa-regular fa-heart fa-xl" onclick="location.href='LikeList.ca'"></i></span> -->
			<span><i class="fa-solid fa-heart fa-sm" onclick="location.href='LoginMember.me'" style="cursor: pointer;"></i></span>
			<span><i class="fa-solid fa-cart-shopping fa-sm" onclick="location.href='LoginMember.me'" style="margin: 10px; cursor: pointer;"></i></span>
			<span><i class="fa-solid fa-user fa-sm" onclick="location.href='LoginMember.me'" style="cursor: pointer;"></i></span>

<!-- 		<div><a href="LoginMember.me">login</a> | <a href="MemberJoinForm.me">join</a></div> -->
		</div>
		</c:otherwise>
	</c:choose>	
	</p>
	
</header>


<script>
	function myFunction() {
	  var x = document.getElementById("Demo");
	  if (x.className.indexOf("w3-show") == -1) { 
	    x.className += " w3-show";
	  } else {
	    x.className = x.className.replace(" w3-show", "");
	  }
	}
</script>

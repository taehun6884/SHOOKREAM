<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	$(function() {
		$("#customerBtn").click(function(){
			$("#cusList").toggle();	
		});
	});
</script>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide" onclick="location.href='./'"><b>SHOOKREAM</b></h3>
  </div>
  
  <!-- 검색창 -->
  <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action="BrandCG.MAIN">
      <div class="input-group">
          <input class="form-control" type="text" name="keyword" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
          <button class="btn btn-primary" id="btnNavbarSearch" type="submit" ><i class="fa fa-search"></i></button>
      </div>
  </form>
  
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
    <hr>
    
    <a onclick="myAccFunc()"  href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
    나의 정보 관리&nbsp;<i class="fa fa-caret-down"></i>
    </a>
    <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
      <a href="MemberModifyForm.me?id=${sessionScope.sId }" class="w3-bar-item w3-button">회원정보 수정</a>
      <a href="" class="w3-bar-item w3-button">배송지 관리</a>
      <a href="MemberDeleteForm.me?id=${sessionScope.sId }" class="w3-bar-item w3-button">회원탈퇴</a>
    </div>

    <a onclick="myAccFunc1()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="customerBtn">
    나의 쇼핑 정보&nbsp;<i class="fa fa-caret-down"></i>
    </a>
    <div id=cusAcc class="w3-bar-block w3-hide w3-padding-large w3-medium">
	   	<a href="ProductOrderList.po?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="w3-bar-item w3-button">내 주문관리</a>
	   	<a href="LikeList.ca?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="w3-bar-item w3-button">내 위시리스트</a>
   	</div>
    
    <a onclick="myAccFunc2()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="customerBtn">
    아이디어 &nbsp;<i class="fa fa-caret-down"></i>
    </a>
    <div id=orderAcc class="w3-bar-block w3-hide w3-padding-large w3-medium">
	   	<a href="" class="w3-bar-item w3-button">히히</a>
	   	<a href="" class="w3-bar-item w3-button">정보</a>
   	</div>

  </div>
</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>SHOOKREAM</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<!-- 네이버아이디로그인 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- 구글 아이디 로그인 -->
<meta name="google-signin-client_id" content="1047574308186-h6ehte2k4901kjn1u3g5vnonbf2g56on.apps.googleusercontent.com">
<style type="text/css">
#sform {
          display: inline-block;
          text-align: center;
        }
</style>
<style type="text/css">
#table, {
     text-align: center;
}
</style>
<style>
    .paging {
        text-align: center;
    }
    .paging a {
        /*
        display: inline-block 인라인 요소의 특징과 블록 요소의 특징을 모두 갖는다
        크기를 가질 수 있으며 텍스트 정렬도 적용받는다
        */
        display: inline-block;
        
        font-weight: bold;
        text-decoration: none;
        padding: 5px 8px;
        border: 1px solid #ccc;
       	color: #000; 
/*         background-color: #F5F5DC; */
    }
    /* 현재 페이징에 select 클래스를 적용한다*/
    .paging a.select {
/*         color: #fff; */
/*         background-color: #FFA7A7; */
    }
    </style>
<style>
.w3-sidebar a {font-family: "Noto Sans KR", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Noto Sans KR", sans-serif;}
</style>
<script>
	// 리뷰작성 폼 팝업 
	function reviewForm(re_idx,re_size,re_color,re_name) {
		let url = "ReviewWriteForm.me?member_idx=${sessionScope.member_idx}&product_idx="+re_idx+"&product_size="+re_size+"&product_color="+re_color+"&product_name="+re_name;  // 테스트용 파라미터임!
		let name = "review form";
		let attr = "width=600, height=600, top=200, left=510"
		
		window.open(url, name, attr);
// 		window.open.member_idx = ${sessionScope.member_idx};
// 		window.open.product_idx = idx;
// 		window.open.product_size = size;
// 		window.open.product_color = color;
		
	}
	
</script>

</head>
<body class="w3-content" style="max-width:95%">
<!-- Sidebar/menu -->
<jsp:include page="../inc/side.jsp"/>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">SHOOKREAM</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>


<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px;margin-top: 20px;margin-right: 17px;">

 <!-- Push down content on small screens -->
 <div class="w3-hide-large" style="margin-top:83px"></div>
 
 <!-- Top header -->
 <div style="float: right;">
 <jsp:include page="../inc/top.jsp"/>
</div>
	
  <!-- Top header -->
  
  <div style="padding: 80px;">
  <header class="w3-container w3-xlarge">
    <p class="w3-left">주문내역</p>
    <p class="w3-right">
    </p>
</header>
   

  <div class="w3-padding-64 w3-small w3-center">
  <form action="ReviewWrite.me" method="post">
  <table class="table">
  <thead  class="table-dark" >
    <tr>
      <th scope="col">#</th>
      <th scope="col">image</th>
      <th scope="col">product name</th>
      <th scope="col">size</th>
      <th scope="col">color</th>
      <th scope="col">price</th>
      <th scope="col">order status</th>
<!--       <th scope="col">id</th> -->
      <th scope="col">date</th>
      <th scope="col" colspan="2">manage</th>
    </tr>
  </thead>
  <tbody id="tableList">
    <c:forEach var="order" items="${orderlist }">
    <tr>
      <td>${order.order_idx }</td>
      <td><img src="upload/${order.order_main_image }"  alt="없음!" class="img-thumbnail" width="150" height="150"></td>
      <td>${order.order_product_name }</td>
      <td>${order.order_product_size }</td>
      <td>${order.order_product_color }</td>
      <td>${order.order_product_price }</td>
      <td>${order.order_category }</td>
<%--       <td>${order.order_member_id }</td> --%>
      <td><fmt:formatDate value="${order.order_date }" pattern="yyyy-MM-DD"/></td>
	  <td><input type="button" value="리뷰 작성하기" class="btn btn-dark" onclick="reviewForm(${order.order_product_idx },'${order.order_product_size }','${order.order_product_color }','${order.order_product_name }')">
<%--       <button type="button" class="btn btn-dark" onclick="deleteOrder(${order.order_idx})">삭제</button></td> --%>
    </tr>
    </c:forEach>
  </tbody>
</table>
</form>
<div class="paging">
        <c:choose>
			<c:when test="${param.pageNum > 1}">
				<a href="ProductOrderList.po?pageNum=${param.pageNum - 1 }&member_idx=${member_idx }">이전</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)">이전</a>
			</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${param.pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="ProductOrderList.po?pageNum=${i }&member_idx=${member_idx }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${param.pageNum < pageInfo.maxPage}">
				<a href="ProductOrderList.po?pageNum=${param.pageNum + 1 }&member_idx=${member_idx }">다음</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)">다음</a>
			</c:otherwise>
		</c:choose>
<!--         <a class="select" href="#">1</a> -->
<!--         <a href="#">2</a> -->
<!--         <a href="#">3</a> -->
<!--         <a href="#">4</a> -->
<!--         <a href="#">5</a> -->
    </div>
</div>

  </div>
<!-- 로그인 화면 폼 -->
  <!-- End page content -->


 <footer>
  	<jsp:include page="../inc/footer.jsp"/>
  </footer>
</div>

<!-- ------------------------------------------------------------------------------------------------------------>
<!-- 자바스크립트 부분 -->

<script>
//드롭다운 기능
	function myFunction() {
	  var x = document.getElementById("Demo");
	  if (x.className.indexOf("w3-show") == -1) { 
	    x.className += " w3-show";
	  } else {
	    x.className = x.className.replace(" w3-show", "");
	  }
	}
</script>
<script>
//주문리스트 삭제
// function deleteOrder(idx){
// 	let result =  confirm("삭제 하시겠습니까?");
// 	if(result){
// 		location.href="OrderDeletePro.po?order_idx="+idx+"&member_idx=${sessionScope.member_idx}&product_idx=${param.product_idx}";
// 	}
// }



// Accordion 
function myAccFunc() {
  var x = document.getElementById("demoAcc");
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else {
    x.className = x.className.replace(" w3-show", "");
  }
}

function myAccFunc1() {
	  var x = document.getElementById("cusAcc");
	  if (x.className.indexOf("w3-show") == -1) {
	    x.className += " w3-show";
	  } else {
	    x.className = x.className.replace(" w3-show", "");
	  }
	}

// Click on the "Jeans" link on page load to open the accordion for demo purposes
document.getElementById("myBtn").click();


// Open and close sidebar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("myOverlay").style.display = "none";
}
</script>
<!-- Channel Plugin Scripts -->
<script>
  (function() {
    var w = window;
    if (w.ChannelIO) {
      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
    }
    var ch = function() {
      ch.c(arguments);
    };
    ch.q = [];
    ch.c = function(args) {
      ch.q.push(args);
    };
    w.ChannelIO = ch;
    function l() {
      if (w.ChannelIOInitialized) {
        return;
      }
      w.ChannelIOInitialized = true;
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    }
    if (document.readyState === 'complete') {
      l();
    } else if (window.attachEvent) {
      window.attachEvent('onload', l);
    } else {
      window.addEventListener('DOMContentLoaded', l, false);
      window.addEventListener('load', l, false);
    }
  })();
  ChannelIO('boot', {
    "pluginKey": "552ea0bb-d4a5-4c70-8ba7-463b7682c434"
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>

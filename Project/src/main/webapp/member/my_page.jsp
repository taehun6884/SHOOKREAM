<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>SHOOKREAM</title>
<script src="js/jquery-3.6.3.js"></script>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style type="text/css">
	#sform {
          display: inline-block;
          text-align: center;
        }
	
	.myOrderState {
		text-align: center;
		margin-left: 5em;
		padding-top: 10em;
	}
	
	nav>ul>li {
		float: center;
		display: inline-block;
		width: 10%;
	}
	
	#navCount {
		font-size: 20px;
		font-weight: bold;
	}
	
	#myPageList {
		padding-top: 100px;
		padding-bottom: 50px;
	}
	
	.grid-container {
		display: grid;
		grid-template-columns: auto auto auto ;
		grid-template-rows: 120px 120px;
		border-color: gray;****8888

	}
	
	.grid-item {
		border: 1px solid;
		padding: 20px;
		font-size: 15px;
		text-align: center;
	}
	
	i {
 		text-align: center; 
		font-size:15px;
	}

	h4 {
		text-align: left;
	}
	
	.aList {
		text-decoration: none;
		color: black;
	}
	
	.aList:hover {
		color: gray;
	}
	
</style>
<style>
    .paging {
        text-align: center;
    }
    
    .paging a {
        display: inline-block;
		font-weight: bold; 
	   	text-decoration: none; 
	    padding: 5px 8px;
        border: 1px solid #ccc;
       	color: #000; 
    }
		
	.w3-sidebar a {
		font-family: "Noto Sans KR", sans-serif;
	}
	
	body,h1,h2,h3,h4,h5,h6,.w3-wide {
		font-family: "Noto Sans KR", sans-serif;
	}
</style>
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
	
<div class="myOrderState">
	<h4>Delivery</h4><br><br>
	<nav class="myPageNav">
		<ul>
			<li><i class='fas fa-money-check'><br><br>입금 확인중</i></li>
			<li><i class='fas fa-angle-right'></i></li>
			<li><i class='fas fa-clipboard-check'><br><br>결제완료</i></li>
			<li><i class='fas fa-angle-right'></i></li>
			<li><i class='fas fa-box'><br><br>상품 준비중</i></li>
			<li><i class='fas fa-angle-right'></i></li>
			<li><i class='fas fa-truck'><br><br>배송중</i></li>
			<li><i class='fas fa-angle-right'></i></li>
			<li><i class='fas fa-home'><br><br>배송 완료</i></li>
		</ul>
		<ul id="navCount">
			<li>0</li>
			<li></li>
			<li>0</li>
			<li></li>
			<li>0</li>
			<li></li>
			<li>0</li>
			<li></li>
			<c:choose>
				<c:when test="${not empty order.order_progress}">
					<li>1</li>
				</c:when>	
				<c:otherwise>
					<li>0</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>

<hr>
	<div id="myPageList">
		<h4>My Page</h4>
		<div class="grid-container">
			<div class="grid-item"><a href="MemberModifyCheck.me?id=${sessionScope.sId }" class="aList"><br>회원 정보 수정</a></div>
			<div class="grid-item"><a href="MemberDeleteForm.me?id=${sessionScope.sId }" class="aList"><br>회원 탈퇴</a></div>
			<div class="grid-item"><a href="CouponListMypage.po?member_idx=${sessionScope.member_idx }" class="aList"><br>쿠폰함</a></div>
			<div class="grid-item"><a href="ProductOrderList.po?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="aList"><br>주문관리</a></div>
			<div class="grid-item"><a href="LikeList.ca?id=${sessionScope.sId }&member_idx=${member_idx}&pageNum=1" class="aList"><br>위시리스트</a></div>
			<div class="grid-item"><a href="MyReviewList.me?member_idx=${member_idx}" class="aList"><br>리뷰 관리</a></div>
		</div>
	</div>	
</div>
<footer>
	<jsp:include page="../inc/footer.jsp"/>
</footer>
</div>
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
<script>
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

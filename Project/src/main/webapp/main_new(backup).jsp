<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<title>SHOOKREAM</title>
<meta charset="UTF-8">
<!-- 네이버 아이디 로그인 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->
<link rel="stylesheet" href="./css/main.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/498a54c4c7.js"
	crossorigin="anonymous"></script>
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>

<style type="text/css">
#logintvar{
	float: right;
}

#main_category{
	text-align: center;
	padding-top: 100px;
	padding-bottom: 30px;
	font-size: x-large;
}

#product_brand {
	margin-bottom: 1.5px; 
	margin-top:2px; 
	font-weight: bold
}

#product_name {
	margin-bottom: 3.5px;
	color: gray;
}

#price {
}

#product_price {
	text-decoration: line-through; 
	font-size: small;
}

#product_discount_price {
	color: red; 
	font-size: big; 
	float: right;
}

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
</head>
<body class="w3-content" style="max-width:95%">

<!-- Sidebar/menu -->
<jsp:include page="./inc/side.jsp"/>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">SHOOKREAM</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:83px"></div>
  
  <!-- Top header -->
  <jsp:include page="./inc/top.jsp"/>

  

<!--   <div class="w3-container w3-text-grey" id="jeans"> -->
<!--     <p>BEST</p> -->
<!--   </div> -->

	<div id="main_category">
		<p>NEW</p>
	</div>
	
	
  <!-- Product grid -->
  <div class="w3-row w3-grayscale">
  <c:forEach var="productNewList" items="${productNewList }">
   <div class="w3-col l3 s6">
      <div class="w3-container">
        <div class="w3-display-container">
          <img src="./upload/${productNewList.product_img }"  alt="..." style="width:100%">
          <div class="w3-display-middle w3-display-hover">
									<button class="w3-button w3-black" onclick="location.href='ProductInfoForm.po?product_idx=${productNewList.product_idx }&member_idx=${sessionScope.member_idx }'">
										Buy now <i class="fa fa-shopping-cart" ></i>
									</button>
								</div>
								<input type="hidden" id="product_idx${productNewList.product_idx }"
									value="${productNewList.product_idx }">
							</div>
							<p id="product_brand" >${productNewList.product_brand }</p>
							<p id="product_name" >${productNewList.product_name }<br></p>
							
							<div id="price">
							<c:choose>
								<c:when test="${productNewList.product_discount_price gt 0}">
									<span>
<%-- 									<c:set var="discounted_price" value="${productBestList.product_price - (productBestList.product_price * productBestList.product_discount_price) }"/> --%>
<%-- 									<c:out value="${discounted_price}" /> --%>
										<fmt:formatNumber value="${productNewList.product_price - (productNewList.product_price * (productNewList.product_discount_price/100)) }" pattern="#,###" />
									</span>
									<span id="product_price">
									<fmt:formatNumber value="${productNewList.product_price }" pattern="#,###" /></span>
									<span id="product_discount_price" ><fmt:formatNumber value="${productNewList.product_discount_price }" pattern="" />%</span>
								</c:when>
								<c:otherwise>
									<span><fmt:formatNumber value="${productNewList.product_price }" pattern="#,###" /></span>
								</c:otherwise>
							</c:choose>
							</div>
							<div id="etcInfo" style="font-size: small; padding-bottom: 20px;">
								<span>
									구매 ${productNewList.product_sell_count } &nbsp;
								</span>
								<span>
									<i class="fa-solid fa-heart" style="color:pink;"></i> ${productNewList.product_wishcount }
								</span>
							</div>
						</div>
					</div>
				</c:forEach>
    </div>
</div>
	<!-- 페이징 처리 -->	
	<div class="paging">
        <c:choose>
			<c:when test="${param.pageNum > 1}">
				<a href="New.MAIN?pageNum=${param.pageNum - 1 }">이전</a>
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
					<a href="New.MAIN?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${param.pageNum < pageInfo.maxPage}">
				<a href="New.MAIN?pageNum=${param.pageNum + 1 }">다음</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)">다음</a>
			</c:otherwise>
		</c:choose>
    </div>
<!-- </footer> -->
  <!-- footer -->
<%--     <jsp:include page="./inc/footer.jsp"/> --%>
  
  

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

<!-- 로그인 드롭다운 기능! -->
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
<!-- 네이버 아이디 로그인 -->
<script type="text/javascript">
  var naver_id_login = new naver_id_login("nSNLHIW18gDjrrJsFDeE", "http://localhost:8080/Project/index.jsp");
  // 접근 토큰 값 출력
//   alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    alert(naver_id_login.getProfileData('email'));
  }
</script>
<!-- End Channel Plugin -->
</body>
</html>

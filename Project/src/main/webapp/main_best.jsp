<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>SHOKREAM</title>
<meta charset="UTF-8">
<!-- 네이버 아이디 로그인 -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/498a54c4c7.js"
	crossorigin="anonymous"></script>
<style>
.w3-sidebar a {
	font-family: "Roboto", sans-serif
}

body, h1, h2, h3, h4, h5, h6, .w3-wide {
	font-family: "Montserrat", sans-serif;
}
</style>

<style type="text/css">
#logintvar {
	float: right;
}

#main_category {
	text-align: center;
	padding-top: 100px;
	padding-bottom: 30px;
	font-size: x-large;
}

#btnLikeImage {
	width: 20px;
	height: 20px;
}
</style>

<script src="jquery/jquery-3.6.3.js"></script>
<script type="text/javascript">
// for(var i = 0; i < ${fn:length(productBestList)}; i++){
$(function() {
	$("button[id^='btnLike']").on("click", function(e) {
		
// 		var loginCk = "${sessionScope.sId}";
		var loginCk = "<%=(String)session.getAttribute("sId")%>"
		
		if(loginCk == "null"){
			alert("로그인 후 이용 가능합니다.");
			location.href="LoginMember.me";
		} 
		
		$.ajax({
			type: "post", 
			url: "LikeInsertPro.ca", 
			data: { 
				member_idx: ${sessionScope.member_idx},
				product_idx: $("input[id^='product_idx']").val()
			},
			dataType: "html", 
			async:false,
			success: function(data) { 
					$("#btnLikeImage").attr("src", "images/after_heart.png");
					alert("찜한 상품에 추가되었습니다!");
			}, 
			error: function(xhr, textStatus, errorThrown) {
				alert("찜하기 실패"); 
			}
		});
		
	});
});
// }
</script>
</head>
<body class="w3-content" style="max-width: 1200px">

	<!-- Sidebar/menu -->
	<jsp:include page="./inc/side.jsp" />

	<!-- Top menu on small screens -->
	<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
		<div class="w3-bar-item w3-padding-24 w3-wide">SHOOKREAM</div>
		<a href="javascript:void(0)"
			class="w3-bar-item w3-button w3-padding-24 w3-right"
			onclick="w3_open()"><i class="fa fa-bars"></i></a>
	</header>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 250px">

		<!-- Push down content on small screens -->
		<div class="w3-hide-large" style="margin-top: 83px"></div>


		<!--   <div class="w3-container w3-text-grey" id="jeans"> -->
		<!--     <p>BEST</p> -->
		<!--   </div> -->

		<div id="main_category">
			<p>BEST</p>
		</div>


		<!-- Product grid -->
		<div class="w3-row w3-grayscale">
		<c:forEach var="productBestList" items="${productBestList }">
			<div class="w3-col l3 s6">
				<div class="w3-container">
						<div class="w3-container">
							<div class="w3-display-container">
								<img src="./upload/${productBestList.product_img }" alt="..."
									style="width: 100%">
								<div class="w3-display-middle w3-display-hover">
									<button class="w3-button w3-black">
										Buy now <i class="fa fa-shopping-cart" ></i>
									</button>
								</div>
								<button id="btnLike${productBestList.product_idx }">
									<img alt="" src="images/before_heart.png" id="btnLikeImage" />
								</button>
								<input type="hidden" id="product_idx${productBestList.product_idx }"
									value="${productBestList.product_idx }">
							</div>
							<p>${productBestList.product_brand }<br>
							</p>
							<p>${productBestList.product_name }<br>
								<b><fmt:formatNumber
										value="${productBestList.product_price }" pattern="#,###" /></b>
							</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>



					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[4].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[4].product_name }<br><b>${productBestList[4].product_price }</b></p><!-- 5번째 그리드 --> --%>
					<!--       </div> -->
					<!--     </div> -->

					<!--     <div class="w3-col l3 s6"> -->
					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[1].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[1].product_name }<br><b>${productBestList[1].product_price }</b></p><!-- 2번째 그리드 --> --%>
					<!--       </div> -->
					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[5].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[5].product_name }<br><b>${productBestList[5].product_price }</b></p><!-- 6번째 그리드 --> --%>
					<!--       </div> -->
					<!--     </div> -->


					<!--     <div class="w3-col l3 s6"> -->
					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[2].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[2].product_name }<br><b>${productBestList[2].product_price }</b></p><!-- 3번째 그리드 --> --%>
					<!--       </div> -->
					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[6].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[6].product_name }<br><b>${productBestList[6].product_price}</b></p><!-- 7번째 그리드 --> --%>
					<!--       </div> -->
					<!--     </div> -->

					<!--     <div class="w3-col l3 s6"> -->
					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[3].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[3].product_name }<br><b>${productBestList[3].product_price }</b></p><!-- 4번째 그리드 --> --%>
					<!--       </div> -->
					<!--       <div class="w3-container"> -->
					<!--         <div class="w3-display-container"> -->
					<%--           <img src="./upload/${productBestList[7].product_img }" class="img-thumbnail" alt="..." style="width:100%"> --%>
					<!--           <div class="w3-display-middle w3-display-hover"> -->
					<!--             <button class="w3-button w3-black">Buy now <i class="fa fa-shopping-cart"></i></button> -->
					<!--           </div> -->
					<!--         </div> -->
					<%--         <p>${productBestList[7].product_name }<br><b>${productBestList[7].product_price }</b></p><!-- 8번째 그리드 --> --%>

		<!-- footer -->
<%-- 		<jsp:include page="./inc/footer.jsp" /> --%>

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

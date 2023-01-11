<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Notice</title>
		<meta charset="UTF-8">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
		<style>
		.w3-sidebar a {font-family: "Noto Sans KR", sans-serif}
		body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Noto Sans KR", sans-serif;}
		</style>
		
		<style type="text/css">
		#logintvar{
			float: right;
		}
			
		#collectView {
			float: right;
			text-align: left;
		}
	
		#border_content {
		padding: 130px;
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
	  <jsp:include page="../inc/top.jsp"/>
	  
	  
	  <div id="border_content">
			<h3 style="padding: 20px;">자주묻는 질문</h3>
			<hr style="border:solid 2px black;">
			<table class="table">
			  <c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum }" />
					</c:otherwise>
				</c:choose>
			  <thead>
			    <tr style="text-align: center">
			      <th scope="col">번호</th>
			      <th scope="col">구분</th>
			      <th scope="col">제목</th>
			      <th scope="col">등록일</th>
			    </tr>
			  </thead>
			 <c:forEach var="board" items="${boardList }">
			  <tbody>
			    <tr>
			      <th scope="row"  style="text-align: center">${board.notice_idx }</th>
			      <td style="text-align: center"><b>${board.notice_category }</b></td>
			      <td>
			      	<a href="FAQInfo.bo?notice_idx=${board.notice_idx }&pageNum=${pageNum }" style="text-decoration:none">${board.notice_subject }</a>
				  </td>
			      <td style="text-align: center">${board.notice_date }</td>
			    </tr>
			  </tbody>
			 </c:forEach> 
			</table>
			<c:choose>
		    	<c:when test="${sessionScope.sId eq 'admin' }">
		    		<a href="AdminBoard.ad" class="w3-bar-item btn btn-dark btn-sm" style="float:right">게시판 관리</a>
		    	</c:when>
		    </c:choose>
		<section id="buttonArea" style="text-align:center">
			<form action="BoardList.bo">
				<input type="text" name="keyword">
				<input type="submit" value="검색" class="btn btn-dark btn-sm">
			</form>
		</section>
		<br>
		<section id="pageList" style="text-align:center">
			<c:choose>
				<c:when test="${pageNum > 1}">
					<input type="button" class="btn btn-dark btn-sm" value="이전" onclick="location.href='BoardList.bo?pageNum=${pageNum - 1}'">
				</c:when>
				<c:otherwise>
					<input type="button" class="btn btn-dark btn-sm" value="이전">
				</c:otherwise>
			</c:choose>
				
			<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
				<c:choose>
					<c:when test="${pageNum eq i}">
						${i }
					</c:when>
					<c:otherwise>
						<a href="FAQList.bo?pageNum=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
			<c:choose>
				<c:when test="${pageNum < pageInfo.maxPage}">
					<input type="button" value="다음" class="btn btn-dark btn-sm" onclick="location.href='FAQList.bo?pageNum=${pageNum + 1}'">
				</c:when>
				<c:otherwise>
					<input type="button" class="btn btn-dark btn-sm" value="다음">
				</c:otherwise>
			</c:choose>
		</section>	
		<br><br>
<!-- 		<div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div> -->
	</div>
	  <!-- End page content -->
	  <footer>
	  	<jsp:include page="../inc/footer.jsp"></jsp:include>
	  </footer>
	</div>
		 
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

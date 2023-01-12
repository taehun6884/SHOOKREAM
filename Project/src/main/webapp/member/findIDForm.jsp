<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
<title>아이디 / 비밀번호 찾기</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style type="text/css">
#sform {
          display: inline-block;
          text-align: center;
        }
</style>
<style>
.w3-sidebar a {font-family: "Noto Sans KR", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Noto Sans KR", sans-serif;}
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
  
  <!-- Top header -->
  <header class="w3-container w3-xlarge">
<!--     <p class="w3-left">제목</p> -->
    <p class="w3-right">
    </p>
</header>
   
  <!-- Footer -->
  <!-- 로그인 화면 폼 -->
  <footer class="w3-padding-64 w3-small w3-center" id="footer">
    <div id = "sform">
        <h4 style="padding-top: 30px;">아이디 찾기</h4>
        <form method="post" action="FindIdFormAction.me" >
        <p align="center"><a href="LoginMember.me">[돌아가기]</a></p>
          <table>
				<tr style="">
					<th class="active">이름</th>
					<td width="230px"><input class="w3-input w3-border" type="text" name="member_name" id="member_name" placeholder="이름을 입력해주세요." required></td>
				</tr>
				<tr>
					<th class="active">이메일 주소</th>
					<td width="230px"><input class="w3-input w3-border" type="text" name="member_email" id="member_email" placeholder="이메일을 입력해주세요." required></td>
				</tr>
				<tr>
					<td colspan="2" width="230px">
						<input type="submit" value="아이디찾기" class="w3-button w3-block w3-black" >
					</td>
				</tr>
			</table>
        </form>
        		<div style="margin-bottom: 10px;"></div>
				<input style="margin-bottom: 2px;" type="button" value="비밀번호찾기" class="w3-button w3-block w3-black" onclick="location.href='./FindPwForm.me'">
				<input type="button" value="회원가입" class="w3-button w3-block w3-black" onclick="location.href='./MemberJoinForm.me'">
				
    </div>
  </footer>
  <footer>
  	<jsp:include page="../inc/footer.jsp"/>
  </footer>
 </div>	
<!--   <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div> -->
<!-- 아이디/비번찾기 화면 폼 -->
  <!-- End page content -->


<!-- Newsletter Modal -->
<div id="newsletter" class="w3-modal">
  <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
    <div class="w3-container w3-white w3-center">
      <i onclick="document.getElementById('newsletter').style.display='none'" class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i>
      <h2 class="w3-wide">NEWSLETTER</h2>
      <p>Join our mailing list to receive updates on new arrivals and special offers.</p>
      <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail"></p>
      <button type="button" class="w3-button w3-padding-large w3-red w3-margin-bottom" onclick="document.getElementById('newsletter').style.display='none'">Subscribe</button>
    </div>
  </div>
</div>


<!-- ------------------------------------------------------------------------------------------------------------>
<!-- 자바스크립트 부분 -->
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

<!-- 아이디/비번찾기 유효성 검사 -->
<script type="text/javascript">
fuction findIDCheck(){
	let member_name = document.getElementById("member_name").value;
	member_name = member_name.trim();
		if(member_name.length < 2){
			alert("이름 2글자 이상 입력해주세요.");
			return false;
		}
	let member_email = document.getElementById("member_email").value;
	member_email = member_email.trim();
		if(member_name.length < 4){
			alert("이메일 5글자 이상 입력해주세요.");
			return false;
		}
		return true;
} // findIDCheck() 끝

</script>
 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
 
  
</body>
</html>

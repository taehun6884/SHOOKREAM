<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
#sform {
          display: inline-block;
          text-align: center;
        }
</style>
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
</head>
<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<jsp:include page="../inc/side.jsp"/>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">LOGO</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

  <!-- Push down content on small screens -->
<!--   <div class="w3-hide-large" style="margin-top:83px"></div> -->
  
  <!-- Top header -->
  <header class="w3-container w3-xlarge">
    <p class="w3-left">회원가입</p>
    <p class="w3-right">
      <i class="fa fa-shopping-cart w3-margin-right"></i>
      <i class="fa fa-search"></i>
    </p>
</header>

  
  <!-- Footer -->
  <!-- 로그인 화면 폼 -->
  <footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
    <div id = "sform">
        <h4>회원가입</h4>
        <p>Questions? Go ahead.</p>
        <form action="MemberJoinPro.me" method="post" name="joinForm">
          <table>
          <tr>
          <td width="300px">
          <input class="w3-input w3-border" type="text" placeholder="name" name="name" required></td>
          </tr>
          <tr>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="id" name="id" id="id" required onkeydown="inputIdChk()">
          <td>
         <button type="button" class="btn btn-secondary" name="dbCheckId" onclick="fn_dbCheckId()">ID check</button>
         <input type="hidden" name="isCheckId" value="idUncheck"/> <!-- 체크 여부 확인 -->
          </td>
          </tr>
          <tr>
          <td width="300px">
          <input class="w3-input w3-border" type="password" placeholder="password" name="pass" required onkeyup="checkPasswd(this.value)">
          <span id="checkPasswdResult"></span>
          </td> <!-- 비밀번호 확인 -->
          </tr>
          <tr>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="phone   ex) 01012345678" name="phone" required></td>
          </tr>
          <tr>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="email    ex) abcd@gmail.com" name="email" required></td>
          </tr>
          <tr>
          <td width="300px"><input class="w3-input w3-border" id="address_kakao" type="text" placeholder="address" name="address" required></td>
          </tr>
          <tr>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="address_detail" name="address_detail" required></td>
          </tr>
          <tr>
          <td><button type="submit" class="w3-button w3-block w3-black" onclick="fn_joinMember()">Send</button></td>
		  </tr> 	        
        </table>
        </form>
    </div>
  </footer>
 </div>	
  <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>
<!-- 로그인 화면 폼 -->
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

<c:set var="contextPath" value="<%= request.getContextPath()%>"/>
<!-- ------------------------------------------------------------------------------------------------------------>
<!-- 자바스크립트 부분 -->
<script>

function fn_joinMember() {
	var joinForm = document.joinForm;
	
	if(joinForm.isCheckId.value != "idCheck"){
		alert("ID 중복체크를 해주세요!")
		event.preventDefault(); // submit 기능 막기
	}
}

function fn_dbCheckId() {
	var id = document.getElementById("id").value;
	if(id.length == 0 || id == ""){
		alert("아이디를 입력해주세요.");
		joinForm.id.focus();
	}else{
		window.open("dbCheckId.me?member_id="+id,"","width=300, height=200, left=600, top=300");
// 		window.open("http://localhost:8080/Project/dbCheckId.me?member_id="+id,"","width=500, height=300");
	}
}

function inputIdChk(){
	var joinForm = document.joinForm;
	var dbCheckId = document.joinForm.dbCheckId;
	document.joinForm.isCheckId.value = "idUncheck";
	dbCheckId.disabled=false;
	openJoinfrm.dbCheckId.style.opacity=1;
	openJoinfrm.dbCheckId.style.cursor="pointer";
}


function checkPasswd(passwd) { // 패스워드 길이 체크
	let spanCheckPasswdResult = document.getElementById("checkPasswdResult");

	if(passwd.length >= 8 && passwd.length <= 16) {
		spanCheckPasswdResult.innerHTML = "사용 가능한 패스워드 입니다";
		spanCheckPasswdResult.style.color = "BLUE";    		
	} else {
		spanCheckPasswdResult.innerHTML = "8 ~ 16자를 입력하세요";
		spanCheckPasswdResult.style.color = "RED";
	}
}



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
<!-- End Channel Plugin -->

<!-- 카카오 주소 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address_kakao").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
</body>
</html>

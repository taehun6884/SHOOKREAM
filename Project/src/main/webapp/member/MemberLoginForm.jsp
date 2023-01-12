<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
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
  <div class="w3-padding-64 w3-small w3-center" >
    <div id = "sform">
        <h4 style="padding: 30px;">로그인</h4>
        <form action="LoginMemberPro.me" method="post">
          <table>
          <tr>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="아이디" name="id" required></td>
          </tr>
          <tr>
          <td width="300px"><input class="w3-input w3-border" type="password" placeholder="비밀번호" name="pass" required></td>
          </tr>
          <tr>
          <td><button type="submit" class="w3-button w3-block w3-black">로그인</button></td>
		  </tr> 	        
          <tr>
          	<td>
          		<button type="button" class="w3-button w3-block w3-black" onclick="location.href='MemberJoinForm.me'">회원가입</button>
          	</td>
          </tr>
          <tr>
          	<td>
          		<button type="button" class="w3-button w3-block w3-black" onclick="location.href='FindMemberIdForm.me'">아이디 / 비밀번호 찾기</button>
          	</td>
          </tr>
        </table>
        </form>
    </div>
  </div>
  <footer>
  	<jsp:include page="../inc/footer.jsp"/>
  </footer>
 </div>	
<!--   <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div> -->
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
 
<!-- End Channel Plugin -->
 <!-- 네이버 아이디 로그인 -->
 <script type="text/javascript">
//   	var naver_id_login = new naver_id_login("nSNLHIW18gDjrrJsFDeE", "http://localhost:8080/Project/index.jsp");
//   	var state = naver_id_login.getUniqState();
//   	naver_id_login.setButton("white", 2,35);
//   	naver_id_login.setDomain("http://localhost:8080/Project");
// //   	naver_id_login.setState(state);
// //   	naver_id_login.setPopup();
//   	naver_id_login.init_naver_id_login();
  </script>
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
// Kakao.init('6405849a8d7a7fa490a223800b55af42'); //발급받은 키 중 javascript키를 사용해준다.
// console.log(Kakao.isInitialized()); // sdk초기화여부판단
// //카카오로그인
//         window.Kakao.init('본인 JAVASCRIPT API 키');

//  function kakaoLogin() {
//      window.Kakao.Auth.login({
//          scope: 'account_email,	profile_nickname', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
//          success: function(response) {
//              console.log(response) // 로그인 성공하면 받아오는 데이터
//              window.Kakao.API.request({ // 사용자 정보 가져오기 
//                  url: '/v2/user/me',
//                  success: (res) => {
//                      const kakao_account = res.kakao_account;
//                      console.log(kakao_account)
//                  }
//              });
//           window.location.href="./main.MAIN"; 
//          },
//          fail: function(error) {
//              console.log(error);
//          }
//      });
//  }
// //카카오로그아웃  
// function kakaoLogout() {
//     if (Kakao.Auth.getAccessToken()) {
//       Kakao.API.request({
//         url: '/v1/user/unlink',
//         success: function (response) {
//         	console.log(response)
//         },
//         fail: function (error) {
//           console.log(error)
//         },
//       })
//       Kakao.Auth.setAccessToken(undefined)
//     }
//   }  
</script>
<!-- plugin -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
 
  
</body>
</html>

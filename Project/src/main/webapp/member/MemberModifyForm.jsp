<%@page import="vo.MemberBean"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>회원 수정 </title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style type="text/css">
#sform {
          display: inline-block;
          text-align: center;
        }
        
 	#dbCheckId {
		display:inline;
      box-sizing: border-box;
}
table.type03 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1.5px solid #ccc;
  border-left: 1.5px solid #ccc;
  margin-left:auto; 
  margin-right:auto;
}
table.type03 th {
  width: 147px;
  padding: 10px;
  font-weight: bold;
  font-size : 17px;
  vertical-align: top;
  color: #828282; 
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  height: 90px;
  width: 300px;

}
table.type03 td {
  height: 90px;
  width: 700px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
</style>
<style>
.w3-sidebar a {font-family: "Noto Sans KR", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Noto Sans KR", sans-serif;}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
var passwdStatus = false;
var phoneStatus = false;

$(function() {
$("#pass").on("keyup", function() {
	let pass = $("#pass").val();
	
	let lengthRegex = /^[A-Za-z0-9!@#$%]{8,16}$/; // 전체 규칙만 판별
	
	let engUpperRegex = /[A-Z]/; // 대문자
	let engLowerRegex = /[a-z]/; // 소문자
	let numRegex = /[0-9]/; // 숫자
	let specRegex = /[!@#$%]/; // 특수문자
	
	
		if(!lengthRegex.exec(pass)) {
			$("#checkPasswdResult").html("사용 불가능한 패스워드").css("color", "red");
			passwdStatus = false;
			
			if($("#pass").length > 0){
				$("#newpass2").attr('required', 'required');
			}
		} else {
	
			let count = 0; // 각 항목별 포함 갯수를 카운팅 할 변수 선언
			
			if(engUpperRegex.exec(pass)) { count++ };
			if(engLowerRegex.exec(pass)) { count++ };
			if(numRegex.exec(pass)) { count++ };
			if(specRegex.exec(pass)) { count++ };
			
			passwdStatus = true;
			switch(count) {
				case 4 : $("#checkPasswdResult").html("안전").css("color", "green"); break;
				case 3 : $("#checkPasswdResult").html("보통").css("color", "orange"); break;
				case 2 : $("#checkPasswdResult").html("위험").css("color", "gray"); break;
				case 1 : 
							$("#checkPasswdResult").html("사용 불가능한 패스워드").css("color", "red");
							passwdStatus = false;
			}
			
		}
	
});

//휴대전화 확인
	$("#phone").on("change", function() {
		let phone = $("#phone").val();
		
		let regex =/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})[0-9]{3,4}[0-9]{4}$/;
		if(!regex.exec(phone)) {
			$("#phoneCheckResult").html("잘못된 휴대전화 형식입니다").css("color", "red");
			phoneStatus = false;
		}else {
			$("#phoneCheckResult").html("올바른 휴대전화 형식입니다").css("color", "blue");
			phoneStatus = true;
			}
		});
	
	
	
});	


function reCheckPasswd(pass2) {//재입력 확인 
	var pass = document.getElementById("pass").value;
// 	var pass2 = document.getElementById("pass2").value;
	let spanRecheckResult = document.getElementById("recheckResult");

	if(pass == pass2){
		spanRecheckResult.innerHTML = "동일한 패스워드 입니다";
		spanRecheckResult.style.color = "BLUE";    		
	}else{
		spanRecheckResult.innerHTML = "일치하지 않는 패스워드 입니다";
		spanRecheckResult.style.color = "RED";   
		passwdStatus = false;
		event.preventDefault(); // submit 기능 막기
	}
}

function fn_modify() {
	var mdForm = document.mdForm;
	var pass = document.getElementById("pass").value;
	var pass2 = document.getElementById("newpass2").value;
	var phone = document.getElementById("phone").value;
	
	if(!pass){
		passwdStatus = true;
	} 
	
	if(!phone){
		phoneStatus = true;
	}
	
	if(passwdStatus == false) {
		alert("패스워드를 확인해주세요!")
		event.preventDefault(); // submit 기능 막기
	}else if(phoneStatus == false) {
		alert("휴대전화를 확인해주세요!")
		event.preventDefault(); // submit 기능 막기
	}
}
</script>
</head>
<body class="w3-content" style="max-width:95% ">

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
  
  <!-- Footer -->
  <!-- 로그인 화면 폼 -->
  <div style="padding: 80px;">
  <form action="MemberModifyPro.me" method="post" name="mdForm" style="margin-bottom: 300px">
  			<input type="hidden" value = "${member.member_pass }" name="oldpass">
  			<input type="hidden" value = "${member.member_address }" name="oldaddress">
  			<input type="hidden" value = "${member.member_phone }" name="oldphone">
  			
			<h1 style="text-align: center; padding: 60px; font-size: 25px;">회원 정보 수정</h1>
			<h6></h6>
<!-- 			<h6 style="color: gray;text-align: center;margin-bottom: 50px" >SHOOKREAM에 오신 것을 환영합니다.</h6> -->
<!-- 		    <h3 class="w3-wide" ><b>SHOOKREAM</b></h3> -->
			<div>
				<table class="type03" style='vertical-align: middle'>
				<tr>
						<th scope="row" >이름</th>
						<td>
						<input type="text" name="name" value="${member.member_name }" size="20px"style="line-height: 30px; border: none;" readonly="readonly"><br>
						</td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td>
						<input type="text" name="id" id ="id" value="${member.member_id }" size="20px" style="line-height: 30px; border: none;" readonly="readonly" onkeydown="inputIdChk()" readonly="readonly"> &nbsp;
						<br>
						
						</td>
					</tr>
					<tr>
						<th scope="row">신규 비밀번호</th>
						<td>
						<input type="password" name="newpass1" id ="pass" size="20px" placeholder="변경시에만 입력"  style="line-height: 30px" >&nbsp;<span id="checkPasswdResult"></span><br>
						<span style="color: gray;">(영문소문자/숫자/특수문자 중 2가지 이상 조합, 8~16자.)</span>
						</td>
					</tr>
					<tr>
						<th scope="row">신규 비밀번호 확인</th>
						<td>
						<input type="password" name="newpass2"  id="newpass2" size="20px" style="line-height: 30px" placeholder="변경시에만 입력"  onkeyup="reCheckPasswd(this.value)">&nbsp;<span id ="recheckResult"></span><br>
						<span style="color: gray;">(비밀번호 확인을 위해 동일하게 입력해주세요.)</span>
						</td>
					</tr>
					
					<tr>
						<th scope="row">주소</th>
						<td>
						<div>${member.member_address }</div><br>
						<input type="text" name="address" id="address_kakao2"  size="30px" placeholder="변경시에만 입력" style="margin-bottom: 10px;line-height: 30px"> &nbsp;
						<button id="address_kakao" class="btn btn-dark">주소찾기</button><br>
						<input type="text" name="address_detail"  size="30px" style="line-height: 30px"  ><br>
						</td>
					</tr>
					<tr>
						<th scope="row">휴대전화</th>
						<td>
						<div>${member.member_phone }</div><br>
						<input type="text" name="phone" id="phone" size="20px" style="line-height: 30px" placeholder="변경시에만 입력" >&nbsp; <span id ="phoneCheckResult"></span><br>
						<span style="color: gray;">("-"를 제외한 휴대전화를 입력해주세요. ex)01011111111 )</span>
						</td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>
						<input type="text" name="email" id="email" value="${member.member_email }" readonly="readonly" size="20px" style="line-height: 30px; border: none;"> &nbsp;
						<span id="authEmailResult"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="submit" class="btn btn-secondary btn-lg" onclick="fn_modify()">수정하기</button>
						<button type="submit" class="btn btn-secondary btn-lg" onclick="history.back()">취소</button></td>
					</tr>
				</table>

			</div>

		</form>
		
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

<!-- 비밀번호 수정 -->
<script type="text/javascript">

	// 비밀번호확인 -기존 비밀번호와 같은지 체크하기
	function checkConfirmPasswd(confirmPasswd) {
		let newpass1 = document.fr.newpass1.value;
		let spanCheckConfirmPasswdResult = document.getElementById("checkConfirmPasswdResult");
		// 두 패스워드 일치 여부 판별
		if(newpass1 == confirmPasswd) {
			spanCheckConfirmPasswdResult.innerHTML = "패스워드 일치";
			spanCheckConfirmPasswdResult.style.color = "BLUE";    		
		} else {
			spanCheckConfirmPasswdResult.innerHTML = "패스워드 불일치";
			spanCheckConfirmPasswdResult.style.color = "RED";    	
		}
	}

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
                document.getElementById("address_kakao2").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
</body>
</html>

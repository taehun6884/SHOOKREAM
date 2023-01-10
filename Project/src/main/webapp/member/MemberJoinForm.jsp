<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>SHOOKREAM</title>
<!-- <script src="js/jquery-3.6.3.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

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
  color: #153d73; 
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
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>


<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
var idStatus = false;
var passwdStatus = false;
var phoneStatus = false;
var emailStatus = false;

$(function() {
	$("#id").on("keyup", function() {
		let id = $("#id").val();
		
		let regex = /^[A-Za-z0-9-_.]{5,20}$/;
		if(!regex.exec(id)) {
			$("#checkIdSpan").html("잘못된 아이디 형식입니다").css("color", "red");
			idStatus = false;
		} else {
			$("#checkIdSpan").html("올바른 아이디 형식입니다").css("color", "blue");
			idStatus = true;
		}
	});
	
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
	
		// 이메일 확인
		$("#email").on("change", function() {
			let email = $("#email").val();
			
// 			let regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			let regex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			if(!regex.exec(email)) {
				$("#emailCheckResult").html("잘못된 이메일 형식입니다").css("color", "red");
				emailStatus = false;
			} else {
				$("#emailCheckResult").html("올바른 이메일 형식입니다").css("color", "blue");
				emailStatus = true;
			}
		});
		
		// 휴대전화 확인
		$("#phone").on("change", function() {
			let phone = $("#phone").val();
			
			let regex =/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
			if(!regex.exec(phone)) {
				$("#phoneCheckResult").html("잘못된 휴대전화 형식입니다").css("color", "red");
				phoneStatus = false;
			}else {
				$("#phoneCheckResult").html("올바른 휴대전화 형식입니다").css("color", "blue");
				phoneStatus = true;
				}
			});
		});
		
</script>
</head>
<body class="w3-content" style="max-width:95%">

<!-- Top/menu -->
<jsp:include page="../inc/top.jsp"/>

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
<div class="w3-main" style="margin-left:250px">

  <!-- 회원가입 폼 -->
  
  	<form action="MemberJoinPro.me" method="post" id="joinForm" name="joinForm" style="margin-bottom: 300px">
			<h1 style="text-align: center;">회원가입</h1>
			<h6 style="color: gray;text-align: center;margin-bottom: 50px" >SHOOKREAM에 오신 것을 환영합니다.</h6>
<!-- 		    <h3 class="w3-wide" ><b>SHOOKREAM</b></h3> -->
			
			
			<div>
				<table class="type03">
					<tr>
						<th scope="row">아이디</th>
						<td>
						<input type="text" name="id" id ="id" required size="20px" style="line-height: 30px" onkeydown="inputIdChk()" > &nbsp;
						<button class="btn btn-dark" name="dbCheckId" id="dbCheckId" onclick="fn_dbCheckId()">중복 확인</button>
<!-- 						<button type="button" class="btn btn-secondary" name="dbCheckId" id="dbCheckId" onclick="fn_dbCheckId()">ID check</button> -->
<!-- 						<input type="hidden" name="isCheckId" value="idUncheck"/> 체크 여부 확인			 -->
						<br>
						<span style="color: gray;" >(영문 소문자/숫자/특수문자(-_.) 사용가능, 5~20자)</span> &nbsp;
						<span id="checkIdSpan"></span>
						</td>
					</tr>
					<tr>
						<th scope="row">비밀번호</th>
						<td>
						<input type="password" name="pass" id ="pass" required size="20px" style="line-height: 30px" onkeyup="checkPasswd(this.value)">&nbsp; <span id="checkPasswdResult"></span><br>
						<span style="color: gray;">(영문 대 소문자/숫자/특수문자(!@#$%) 중 2가지 이상 조합, 8~16자)</span>
						
						</td>
					</tr>
					<tr>
						<th scope="row">비밀번호 확인</th>
						<td>
						<input type="password" name="pass2" required size="20px" style="line-height: 30px" onkeyup="reCheckPasswd(this.value)">&nbsp; <span id ="recheckResult"></span><br>
						<span style="color: gray;">(비밀번호 확인을 위해 동일하게 입력해주세요.)</span>
						</td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td>
						<input type="text" name="name" required size="20px"style="line-height: 30px" ><br>
						<span style="color: gray;">(성함을 입력해주세요.)</span>
					
						
						</td>
					</tr>
					<tr>
						<th scope="row">주소</th>
						<td>
						<input type="text" name="address" id="address_kakao2" required size="30px" style="margin-bottom: 10px;line-height: 30px"> &nbsp;
						<button id="address_kakao" class="btn btn-dark">주소찾기</button><br>
						<input type="text" name="address_detail"  size="30px" style="line-height: 30px" ><br>
						<span style="color: gray;">(상세 주소를 입력해주세요.)</span>
						</td>
					</tr>
					<tr>
						<th scope="row">휴대전화</th>
						<td>
						<input type="text" name="phone" id="phone" required size="20px" style="line-height: 30px" >&nbsp; <span id ="phoneCheckResult"></span><br>
						<span style="color: gray;">("-"를 제외한 휴대전화를 입력해주세요. ex)01011111111 )</span>
						</td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>
						<input type="text" name="email" id="email" placeholder="" required size="20px" style="line-height: 30px"> &nbsp;
						<input type="button" class="btn btn-dark" id="checkEmail"  value="인증 메일 전송" onclick="alert('이메일 전송 완료!')"><br>
						<span style="color: gray">("@"를 포함하여 이메일을 입력해주세요. ex) abcd@gmail.com)</span><br>
						<div id ="authResult">
						<input type="text" name="authCode" id="authCode" size="20px" style="line-height: 30px" required="required"> &nbsp;
						<input type="button" class="btn btn-dark" id="checkEmail2"  value="인증 하기"><br>
						<span style="color: gray">(인증코드 6자리를 입력하세요)</span> &nbsp;
						<span id="authEmailResult"></span>
						</div>
						</td>
						
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="submit" id="join_btn" name ="join" class="btn btn-dark"  >회원가입</button></td>
					</tr>
				</table>

			</div>

		</form>


  <br>
  <!-- Footer -->
  <jsp:include page="../inc/footer.jsp"/>
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

<c:set var="contextPath" value="<%= request.getContextPath()%>"/>
<!-- ------------------------------------------------------------------------------------------------------------>
<!-- 자바스크립트 부분 -->
<script type="text/javascript">
// 	var isIdCheck = false;
	var isEmailAuth = false;
	
function fn_joinMember() {
	var joinForm = document.joinForm;
	
	if(joinForm.isCheckId.value != "idCheck"){
		alert("ID 중복체크를 해주세요!")
		event.preventDefault(); // submit 기능 막기
	
	} else if(idStatus == false) {
		alert("ID를 확인해주세요!")
		event.preventDefault(); // submit 기능 막기
	} else if(passwdStatus == false) {
		alert("패스워드를 확인해주세요!")
		event.preventDefault(); // submit 기능 막기
	}else if(phoneStatus == false) {
		alert("휴대전화를 확인해주세요!")
		event.preventDefault(); // submit 기능 막기
	}else if(emailStatus == false) {
		alert("이메일을 확인해주세요!")
		event.preventDefault(); // submit 기능 막기
	}
}

// window.opener.isIdCheck = true;

function fn_dbCheckId() {
	var id = document.getElementById("id").value;
// 	if(id.length == 0 || id == ""){
// 		alert("아이디를 입력해주세요.");
// 		joinForm.id.focus();
// 	}
// 	else 
	if(id.length >= 5 && id.length <= 20){
		window.open("dbCheckId.me?member_id="+id,"","width=500, height=200, left=600, top=300");
	}else{
		alert("5 ~ 20 문자로 입력해주세요.");
		joinForm.id.focus();
	}
// 		if(id.length > 16 && id.length < 8) {
// 		alert("8 ~ 16 문자로 입력해주세요.");
// 		joinForm.id.focus();
// 	}else{
// 		window.open("dbCheckId.me?member_id="+id,"","width=500, height=200, left=600, top=300");
// 		window.open("http://localhost:8080/Project/dbCheckId.me?member_id="+id,"","width=500, height=300");
	
}
			
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
		event.preventDefault(); // submit 기능 막기
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


// function checkPasswd(passwd) { // 패스워드 길이 체크
// 	let spanCheckPasswdResult = document.getElementById("checkPasswdResult");

// 	if(passwd.length >= 8 && passwd.length <= 16) {
// 		spanCheckPasswdResult.innerHTML = "사용 가능한 패스워드 입니다";
// 		spanCheckPasswdResult.style.color = "BLUE";    		
// 	} else {
// 		spanCheckPasswdResult.innerHTML = "8 ~ 16자를 입력하세요";
// 		spanCheckPasswdResult.style.color = "RED";
// 		event.preventDefault(); // submit 기능 막기

// 	}
// }


	/* 이메일 인증 1 */
	$(function() {
		$("#joinForm").submit(function() { // 폼 서브밋
			// 아이디 체크, 이메일 인증 체크 
// 			if($("") != "idCheck"){ //
// 				alert("ID 중복체크를 해주세요!")
// 				event.preventDefault(); // submit 기능 막기
// 			}	
		
			// 이메일 인증 확인
			if(!isEmailAuth) { 
				
				alert(isEmailAuth); 
				alert("Email 인증을 완료 해주세요!");
				event.preventDefault(); // submit 기능 막기
				
				return false; // submit 취소
			}
				alert(isEmailAuth); 
				alert("Email 인증 성공!");
				return true; // submit 실행
		});
		
			$("#checkEmail").on("click", function() {
				
				$.ajax({
					type: "get",
		            url: "CheckEmailAddress.me",
		            data: {
		               id: $("#id").val(),
		               authCode: $("#authCode").val(),
		               email: $("#email").val()
		            }
				                   
		         });
			});
			
		/* 이메일 인증 2 */
		$("#checkEmail2").on("click", function() {
				
				$.ajax({
					type: "get",
		            url: "CompareEmailAddress.me",
		            data: {
		               id: $("#id").val(),
		               authCode: $("#authCode").val()
		            },
		            datatype: "html",
					success:function(data){
						const isSuccess = $.trim(data);
						alert(isSuccess);
						if(isSuccess == "true"){ 
							
							alert("인증 성공");
	                	  	 $("#authEmailResult").html("인증 성공!").css("color", "blue");
// 	                	  	 $("#authResult").reload(window.location.href + " #authResult");
	                	  	 isEmailAuth = true;
	                	  	 alert(isEmailAuth);
	    	            } else {
							alert("인증 실패");
							 $("#authEmailResult").html("인증 실패!").css("color", "red");
// 							 event.preventDefault(); // submit 기능 막기
// 							 $('join_btn').prop('disabled', false);
// 							 $("#authResult").reload(window.location.href + " #authResult");
	                	  	 isEmailAuth = false;
	                	  	alert(isEmailAuth);
	    	            }//else
					
					}//success
		         
				});//ajax
			});
		});
	
	
	

	


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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>주문 상세 내용</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
	<style type="text/css">
	#delivery_table{
	td {
		vertical-align : middle;
	}	
	
	table th {
		vertical-align : middle;
	}	
	
	}
	
	</style>
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
</head>
<body class="w3-content" style="max-width:95%">
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
    <p class="w3-left">주문 상세 페이지</p>
    <p class="w3-right">
      <i class="fa fa-shopping-cart w3-margin-right"></i>
      <i class="fa fa-search"></i>
    </p>
</header>
   
  <!-- Footer -->
  <table class="table">
  <thead  class="table-dark" >
    <tr>
      <th scope="col" colspan="6" style="text-align: center">주문 내역</th>
    </tr>
  </thead>
  <tbody>
	     <tr>
     		 <th>상품</th>
     		 <th>상품이름</th>
     		 <th>상품가격</th>
     		 <th>수량</th>
     		 <th>할인</th>
     		 <th>할인 금액</th>
    	</tr>
	    <tr>
	      <td><img src="upload/${image.image_main_file }"  alt="없음!" class="img-thumbnail" width="100" height="100"></td>
	      <td>${product.product_name }</td>
	      <td>${product.product_price }</td>
	      <td>1</td>
	      <td>${product.product_discount_price }</td>
	      <td>${product.product_price }</td>
	    </tr>
	  </tbody>
	</table>
   
	  <table class="table">
	  <thead>
	    <tr>
	      <th scope="col" colspan="6" style="font-size: x-large;">할인 혜택</th>
	    </tr>
	  </thead>
	  <tbody>
	   <tr>
	    	<td colspan="8">
	    	<p style="font: bold; font-size: large; text-align: center;">
	    	사용 가능한 쿠폰(0장) 중 (0장)의 쿠폰이 적용되었습니다.
	    	</p>
	    	<button type="button" class="btn btn-dark btn-sm" style="  margin:auto; display:block;" onclick="CouponCheck()">내가 보유한 쿠폰 보러가기</button>
	    	</td>
	   </tr>
	   <tr>
		<th colspan="2">상품 할인쿠폰</th>
		<td colspan="6" style="margin-left:500px;"><input type="text" id="priceValue">원 할인</td>
	   </tr>
	   <tr>
	    <th colspan="2">이포인트</th>
	   	<td colspan="6"><input type="checkbox">모두사용 하기 (이포인트 *원 보유) <button type="button" class="btn btn-dark btn-sm">포인트 조회</button></td>
	   </tr>
	   <tr>
	   	<th colspan="2">할인된 가격</th>
	   	<td colspan="6"><input type="text" id="totalprice">원</td>
	   </tr>   
	  </tbody>
	</table>
	 
	 <table class="table" id="delivery_table">
	  <thead>
	    <tr>
	      <th scope="col" colspan="8" style="font-size: x-large;">배송 정보</th>
	    </tr>
	  </thead>
	  <tbody>
	   <tr>
	    	<th colspan="2">
	    		주문 하시는 분
	    	</th>
	    	<td colspan="6">
	    		주문자이름<br>
	    		xxxxx@xxxx.com<br>
	    		010-1111-1111<br>
	    	</td>
	   </tr>
	   <tr>
		<th colspan="2">배송지 선택</th>
		<td colspan="6" style="margin-left:500px;">
			<input type="radio" value="" name=""> 기본배송지
			<input type="radio" value="새로운 배송지" name="">새로운 배송지
		</td>
	   </tr>
	   <tr>
	    <th colspan="2">배송지명</th>
	   	<td colspan="6" style="text-align: left;">집</td>
	   </tr>
	   
	   <tr>
	   <th colspan="2">받으시는 분</th>   
	   <td>박영진<br>
	 	   전화번호 : 000-0000-0000/휴대폰 번호 : 000-0000-0000 <br>
	       주소: 주소들어가기<br>
	   </td>
	   </tr>
	   <tr>
	   	<th colspan="2">배송 메세지</th>
	   	<td>
	   		<select>
	   			<option>부재시 문 앞에 놓아주세요</option>
	   			<option>경비실에 ㄱㄱ </option>
	   			<option>전화 부탁 드립니다</option>
	   			<option>소화전에 넣어 주세요</option>
	   		</select>
	   	 </td>
	   </tr>
	   <tr>
	   	<th colspan="2">총 배송비</th>
	   	<td colspan="6">0원 </td>
	   </tr>
	  </tbody>
	</table>
	 <button type="button" style="margin:auto; display:block;" class="btn btn-dark btn-sm" onclick="iamport()">구매하기</button>
	    </div>
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
<script type="text/javascript">
// alert(typeof(${product.product_price }));
function CouponCheck() {
	let url = "CouponListForm.po?member_idx="+${sessionScope.member_idx}+"&product_price="+${product.product_price };  // 테스트용 파라미터임!
	let name = "Coupon List";
	let attr = "width=900, height=600, top=200, left=510"

	window.open(url, name, attr);
}

</script>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#totalprice").on("change",function(){
		
		
		
	})

});
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
<!-- 주문하기 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript">
function iamport(){
		//가맹점 식별코드
		IMP.init('imp77718215');
		IMP.request_pay({
		    pg : 'kakaopay',
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '${product.product_name}' , //결제창에서 보여질 이름
		    amount : $("#totalprice").val(), //실제 결제되는 가격
		    buyer_name : '${sessionScope.sId}',
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        location.href="ProductOrderPro.po?order_category=주문완료&order_progress=배송완료&member_idx=${member_idx}&product_idx=${product.product_idx}&product_amount=${product.product_amount}&product_sell_count=${product.product_sell_count}&product_price="+rsp.paid_amount;
		    } else {
		    	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
		         window.history.back();
		    }
		    alert(msg);
		    
		});
	}
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>

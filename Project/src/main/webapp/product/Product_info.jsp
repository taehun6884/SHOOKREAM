<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<!-- 네이버 아이디 로그인 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
<style type="text/css">
#sform { 
		  border:1px solid red;
          display: inline-block;
          text-align: center;
          margin-left: 270PX;
          
        }
#image{
/* background-color: blue; */
padding-left: 50;
float: left;
}

#title{
align-content: center;
}

#detail{border:1px solid blue;
font-family: "Montserrat", sans-serif;
font-size:15px;
float: right;
margin-top: 100px;
margin-left: 20px;
text-align: left;
}      
</style>

<style type="text/css">
#logintvar{
	float: right;
}

</style>

<style>
#button {
display:inline‑block;
    height:10px;
    width:10px;
    border‑radius:10px;
    background‑color: ;
}

</style>

</head>
<body class="w3-content" style="max-width:1200px">

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
<div class="w3-main" style="margin-left:300px">

  <!-- Push down content on small screens -->
  <div class="w3-hide-large" style="margin-top:83px"></div>
  
  <!-- Top header -->
  <jsp:include page="../inc/top.jsp"/>
	
	 </div>
	

<!-- 제품 상세 페이지 -->
  <div class="w3-padding-64 w3-light-grey w3-small w3-center" id="div">
  <div id = "sform">
	<h3><b>상품 상세 정보</b></h3>
	<section id="image">
		<p>
		<div class="title" align="left">
			<h4>${product.product_brand}</h4>
			<img alt="shoes" src="images/캡처10.PNG">
		</div>
	</section>
	<section id="detail" >
		<div class="text" > 
			<p>상품명 : ${product.product_name }</p>
		</div>
		<div id="detail1">
			<p>상품번호 : ${product.product_idx }</p>
			<p>가격 : ${product.product_price }</p>
			<p>판매수 : ${product.product_sell_count } </p>
			<p>좋아요 </p>
			<p>구매후기(별점) </p>
			<input type="button" id ="button1" class = "button" value="black">
			<input type="button" id ="button2" class = "button" value="white">
			<input type="button" id ="button3" class = "button" value="blue">
			<input type="button" id ="button4" class = "button" value="yellow">
			
		</div>
		
		<div id="detail2" >
			<select>
				<option selected>사이즈</option>
				<option>220</option>
				<option>230</option>
				<option>240</option>
				<option>250</option>
				<option>260</option>
				<option>270</option>
				<option>280</option>
				<option>290</option>
			</select><br>
			<input type="button" value="좋아요">
			<input type="button" value="장바구니" onclick="location.href='CartInsertPro.ca?product_idx=${param.product_idx}&member_idx=${param.member_idx }'">
			<button onclick="iamport()">구매하기</button>
		</div>
	</section>
  </div>
  </div>
  
    <!-- 
<footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
   -->
<%-- 	<img src="./upload/${product.product_img }" class="img-thumbnail" alt="..." width="150" height="150"> --%>
<!--  	<table border="1"> -->
<!-- 	 	<tr> -->
<%-- 			 <td width="70"><h1>${product }</h1></td> --%>
<!-- 			 <td width="70"></td> -->
<!-- 	 	</tr> -->
	 
<!-- 	 </table> -->


<%-- 	<img src="./upload/${product.product_img }" class="img-thumbnail" alt="..." width="150" height="150"> --%>
<!--  	<table border="1"> -->
<!-- 	 	<tr> -->
<%-- 			 <td width="70"><h1>${product }</h1></td> --%>
<!-- 			 <td width="70"></td> -->
<!-- 	 	</tr> -->
	 
<!-- 	 </table> -->


  <!-- 제품 상세 페이지 끝 -->
<!--   <!-- Subscribe section --> 
<!--   <div class="w3-container w3-black w3-padding-32"> -->
<!--     <h1>Subscribe</h1> -->
<!--     <p>To get special offers and VIP treatment:</p> -->
<!--     <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail" style="width:100%"></p> -->
<!--     <button type="button" class="w3-button w3-red w3-margin-bottom">Subscribe</button> -->
<!--   </div> -->
  
  <!-- Footer -->
<%--   <jsp:include page="../inc/footer.jsp"></jsp:include> --%>

<!--   <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div> -->

  <!-- End page content -->
<!-- </div> -->

<!-- <!-- Newsletter Modal -->
<!-- <div id="newsletter" class="w3-modal"> -->
<!--   <div class="w3-modal-content w3-animate-zoom" style="padding:32px"> -->
<!--     <div class="w3-container w3-white w3-center"> -->
<!--       <i onclick="document.getElementById('newsletter').style.display='none'" class="fa fa-remove w3-right w3-button w3-transparent w3-xxlarge"></i> -->
<!--       <h2 class="w3-wide">NEWSLETTER</h2> -->
<!--       <p>Join our mailing list to receive updates on new arrivals and special offers.</p> -->
<!--       <p><input class="w3-input w3-border" type="text" placeholder="Enter e-mail"></p> -->
<!--       <button type="button" class="w3-button w3-padding-large w3-red w3-margin-bottom" onclick="document.getElementById('newsletter').style.display='none'">Subscribe</button> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->

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
		    amount : '${product.product_price }', //실제 결제되는 가격
		    buyer_name : '${sessionScope.sId}',
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		    } else {
		    	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		    location.href="ProductOrderPro.po?order_category=주문완료&order_progress=배송완료&member_idx=${member_idx}&product_idx=${product.product_idx}&product_amount=${product.product_amount}&product_sell_count=${product.product_sell_count} ";
		});
	}
</script>
<script type="text/javascript">
document.getElementById("button1").style.backgroundColor ="";
document.getElementById("button2").style.backgroundColor ="";
document.getElementById("button3").style.backgroundColor ="";
document.getElementById("button4").style.backgroundColor ="";

document.getElementById("button1").onclick = function(){
            this.style.backgroundColor ="gray";
            document.getElementById("button2").style.backgroundColor ="";
            document.getElementById("button3").style.backgroundColor ="";
            document.getElementById("button4").style.backgroundColor ="";
        };

document.getElementById("button2").onclick = function(){
            this.style.backgroundColor ="gray";
            document.getElementById("button1").style.backgroundColor ="";
            document.getElementById("button3").style.backgroundColor ="";
            document.getElementById("button4").style.backgroundColor ="";
        };
document.getElementById("button3").onclick = function(){
            this.style.backgroundColor ="gray";
            document.getElementById("button1").style.backgroundColor ="";
            document.getElementById("button2").style.backgroundColor ="";
            document.getElementById("button4").style.backgroundColor ="";
        };
document.getElementById("button4").onclick = function(){
            this.style.backgroundColor ="gray";
            document.getElementById("button2").style.backgroundColor ="";
            document.getElementById("button3").style.backgroundColor ="";
            document.getElementById("button1").style.backgroundColor ="";
        };
</script>
<!-- End Channel Plugin -->
</body>
</html>

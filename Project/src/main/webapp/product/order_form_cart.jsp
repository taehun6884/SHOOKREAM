<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>구매페이지</title>
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
.td_cart{
	font-size: 13px;
	text-align: center;
	vertical-align : middle;
}

.th_cart{
	font-size: 16px;
	text-align: center;
}
</style>
<style type="text/css">
#table {	
	margin-top: 150px
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

<style>
/* .w3-sidebar a {font-family: "Roboto", sans-serif} */
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Noto Sans KR", sans-serif;} 
</style>

<style>
    
    .footer {
    	padding: 0px;
    }
    
    .top_circle {
    	 
	    border-radius: 50%;
	    
	    width: 100px;
	    height: 100px;
	    margin: 0px 7px;
/* 	    padding-bottom: 24%; */
	    
	    position: relative;
    }
    
    .top_circle_h {
    	 position : absolute;
    	 top: 20%;
	    left : 19%;
	    bottom :30%;
	    
	    font-size: 15px;
	    text-align: center;
	    font-weight: bold;
    }
    
    #order_circle {
    	background-color: #d2d2d2;
    }
    #cart_circle {
    	background-color: #d2d2d2;
    }
    #com_circle {
    	background-color: #DCEBFF;
    }
    
    .cb {
    font-size: 23px;
    }
    
    #no_cart {
    	padding: 50px 0px;
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
 <div style="float: right;">
 <jsp:include page="../inc/top.jsp"/>
</div>

  <header class="w3-container w3-xlarge" style="padding: 80px 50px;  z-index: -1">
    <p class="w3-left">
    <i class="fa-solid fa-bag-shopping" ></i>
<!--     <i class="fa-solid fa-cart-shopping"></i> -->
    &nbsp;주문 / 결제</p>
<!--     <div class="w3-right out-div"> -->
	    <div class="top_circle w3-right" id="order_circle"><h3 class="top_circle_h"><b class="cb">03</b><br>주문완료</h3></div>
	    <div class="top_circle w3-right" id="com_circle"><h3 class="top_circle_h"><b class="cb">02</b><br>주문/결제</h3></div>
	    <div class="top_circle w3-right" id="cart_circle"><h3 class="top_circle_h"><b class="cb">01</b><br>장바구니</h3></div>
<!--     <i id="cart_circle" class="fa-solid fa-circle"></i> -->
<!--     </div> -->
</header>
   <hr size="25px">
<!--   Footer -->
<!--   <footer class="w3-padding-64 w3-small w3-center" id="footer"> -->
<form action="" style="padding: 0px 40px;">
  <table class="table" border ="3">
  <thead  class="table-dark" >
    <tr>
      <th scope="col" class ="th_cart"colspan="2">상품명</th>
<!--       <th scope="col">상품명</th> -->	
      <th scope="col"  class ="th_cart">상품금액</th>
      <th scope="col"  class ="th_cart">할인금액</th>
      <th scope="col"  class ="th_cart">주문금액</th>
      <th scope="col"  class ="th_cart">수량</th>
      <th scope="col"  class ="th_cart">배송정보</th>
    </tr>
  </thead>
  <tbody>
  	<!-- 카트 리스트가 없을 때 처리 -->
    <c:if test="${total le 0 }">
			<tr>
				<td colspan="8" style="text-align: center"><h3 >구매 할 상품이 없습니다.</h3></td>
			</tr>
		</c:if>
	<!-- 카트 리스트가 없을 때 처리 -->
	<c:if test="${total gt 0}">
    <c:forEach var="cart" items="${cartOrder }" varStatus="status">
    <tr>
      <td><a href="ProductInfoForm.po?product_idx=${cart.product_idx }"><img src="upload/${cart.cart_product_image }"  alt="없음!" class="img-thumbnail" width="150" height="150" ></a></td>
      <td class ="td_cart">${cart.cart_product_name }<br>색상 : ${cart.cart_color }</td>
	  <td class ="td_cart" id="cart_price"><fmt:formatNumber value="${cart.cart_price }" pattern="#,###원"></fmt:formatNumber></td>
      <td class ="td_cart" id="cart_discount_price"><fmt:formatNumber value="${cart.cart_price * (cart.cart_discount / 100)}" pattern="#,###원"></fmt:formatNumber></td>
      <td class ="td_cart" id="cart_order_price" ><fmt:formatNumber value="${cart.cart_order_price}" pattern="#,###원"></fmt:formatNumber></td> 
      <td class ="td_cart">
      <input type="number" value="${cart.cart_count }" style="width: 35px" readonly="readonly">
      </td>
      <td class ="td_cart">무료배송</td>
    </tr>
    </c:forEach>
    </c:if>
  </tbody>
</table>
	<div class="container px-4 text-center" id="totalResult">
	  <div class="row gx-5" >
	    <div class="col">
			<div class="p-3 border bg-light" >
			<h4>총 주문금액 : <fmt:formatNumber value="${total }" pattern="#,###"></fmt:formatNumber>원</h4>
			</div>	    
		</div>
	    <div class="col">
	      <div class="p-3 border bg-light">
	      <input type="button" onclick="goOrder()" value="주문하기" >
 	      </div>
	    </div>
	  </div>
    </div>
</form>
<!-- 페이징 처리 -->	
	<div class="paging">
        <c:choose>
			<c:when test="${param.pageNum > 1}">
				<a href="CartList.ca?pageNum=${param.pageNum - 1 }&member_idx=${member_idx }">이전</a>
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
					<a href="CartList.ca?pageNum=${i }&member_idx=${member_idx }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${param.pageNum < pageInfo.maxPage}">
				<a href="CartList.ca?pageNum=${param.pageNum + 1 }&member_idx=${member_idx }">다음</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)">다음</a>
			</c:otherwise>
		</c:choose>
<!--         <a class="select" href="#">1</a> -->
<!--         <a href="#">2</a> -->
<!--         <a href="#">3</a> -->
<!--         <a href="#">4</a> -->
<!--         <a href="#">5</a> -->
    </div>
   </div>
<!-- </footer> -->
 <footer>
  	<jsp:include page="../inc/footer.jsp"/>
  </footer> 



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

<script type="text/javascript">
//----------------------장바구니 체크박스 선택 여부에 따라 카트 금액 증가, 감소 작업 -------------------------
function removeCheck(cb) {
// 	alert(cb.id);
// 	let cartCheckBox = cb.id.replace("cartCheckBox", ""); // id값의 index값을 가져옴
	let cart_idx = cb.value; // id값의 index값을 가져옴
// 	alert(cart_idx);
	
	//체크박스 상태 판별(true이면 체크된 상태, false이면 체크가 풀린 상태)
	let ischeck = cb.checked;
	
	//check가 true일 때
	if(ischeck == true){
		$.ajax({
			type: "get",
			url: "CartPlusPro.ca",
			data: {
				cart_idx: cart_idx
			},
			dataType: "html",
			success: function() {
				 $("#totalResult").load(window.location.href + " #totalResult");
			}
		});
	//check가 false일 때
	}else if(ischeck == false){
		$.ajax({
			type: "get",
			url: "CartMinusPro.ca",
			data: {
				cart_idx: cart_idx
			},
			dataType: "html",
			success: function() {
				 $("#totalResult").load(window.location.href + " #totalResult");
			}
		});
	}
	
};
	
</script>
<script type="text/javascript">

// 체크된 cart_idx 값을 넘기는 작업
function goOrder() {
		var check = $('input[name=cartCheckBox]:checked');
		let chk_arr = new Array();
		$('input[name=cartCheckBox]:checked').each(function(i) {
			chk_arr.push($(this).val());
		});
// 	
		//체크된 상품 개수만큼 반복
		for(var i =0; i<chk_arr.length; i++){
                 alert("배열값 = "+ chk_arr);
			
		}
		location.href = "CartOrderDetailProAtion.ca?cart_idx=" + chk_arr + "&member_idx=" + ${sessionScope.member_idx};

		
	
	
}



// $(document).ready(function(){
// 	let listArr = new Array();
//     let list = $("input[name='cartCheckBox']:checked");
    
//     for(var i = 0; i < list.length; i++){
//         if(list[i].checked){
//            listArr.push(list[i].value);
//            alert("배열값 = "+ listArr);
//         }
//      }
// }); 
</script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript">
function iamport(){
	// 	
		//가맹점 식별코드
		IMP.init('imp77718215');
		IMP.request_pay({
		    pg : 'kakaopay',
		    pay_method : 'cart',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : 'SHOOKREAM' , //결제창에서 보여질 이름
		    amount : '${total }', //실제 결제되는 가격
		    buyer_name : '${sessionScope.sId}',
		}, function(rsp) {
			console.log(rsp);
		    if ( rsp.success ) {
		    	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        location.href="ProductOrderPro.po?order_category=주문완료&order_progress=배송완료&member_idx=${member_idx}&product_idx=${product.product_idx}&product_amount=${product.product_amount}&product_sell_count=${product.product_sell_count} ";
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

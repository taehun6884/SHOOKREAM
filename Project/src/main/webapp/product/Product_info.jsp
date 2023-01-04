<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%> 
<!DOCTYPE html>
<html>
<head>
<title>${product.product_name }</title>
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
		  border:1px;
		  display: inline-block;
          text-align: center;
          margin-left: 270PX;
          
        }

#reviewListArea { 
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

#detail{border:1px;
font-family: "Montserrat", sans-serif;
font-size:15px;
float: right;
margin-left: 20px;
text-align: left;
}   
.prod_name{
font-size: 30px;
font-weight: 900px;

}
.prod_title{
font-size: 15px;
font-weight: bold;

}    

#detail_table{
border:1px;
display: inline-block;
text-align: center;
margin-left: 270PX;
}


</style>

<style type="text/css">
#logintvar{
	float: right;
}

.reviewContent { 
	width : 900px;
	height: 150px; 
}


#imgSize {
	width: 9em;
	height: 9em;
}

#delBtn {
	float: right;
}

</style>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">

// function btnWishFn() {
<%-- 	var checkLogin = '<%=(String)session.getAttribute("sId")%>'; --%>
	
// 	if(checkLogin == "null"){
// 		alert("로그인 후 이용 가능합니다.");
// 		location.href="LoginMember.me";
// 	} 
// }



// 찜하기
// $(function() {
// 	$("#beforeHeart").on("click", function() {
		
// // 		alert("btnBeforWish");
<%-- 		var checkLogin = '<%=(String)session.getAttribute("sId")%>'; --%>
		
// 		if(checkLogin == "null"){
// 			alert("로그인 후 이용 가능합니다.");
// 			location.href="LoginMember.me";
// 		}
// 	});	
// 			$.ajax({
// 				type: "post", 
// 				url: "LikeInsertPro.ca", 
// 				data: { 
// 					member_idx: ${sessionScope.member_idx},
// 					product_idx: $("#product_idx").val()
// 				},	
// 				dataType: "html", 
// 				success: function(data) { 
// // 						$("#btnWishBeforImage").attr("src", "images/after_heart.png");
// // 						$('#wishLoad').load(location.href+' #wishLoad')
		
// 						alert("찜한 상품에 추가되었습니다!");
						
// 						$(".wishBtn").html('<img id="afterHeart" alt="" src="images/after_heart.png" id="btnWishAfterImage" style="width: 30px; height: 30px;"/>');
// 				}, 
// 				error: function(xhr, textStatus, errorThrown) {
// 					alert("찜하기 실패"); 
// 				}
// 			});
// 		});
			
// 	$("#afterHeart").on("click", function() {
// 		alert("btnAfterWish");
// 			$.ajax({
// 				type: "post", 
// 				url: "LikeDeletePro.ca", 
// 				data: { 
// 					member_idx: ${sessionScope.member_idx},
// 					product_idx: $("#product_idx").val()
// 				},	
// 				dataType: "html", 
// 				success: function(data) { 
// // 						$("#btnWishAfterImage").attr("src", "images/before_heart.png");
// 						alert("찜한 상품에서 삭제했습니다!");
// // 						$('#wishLoad').load(location.href+' #wishLoad')
// 						$(".wishBtn").html('<img id="beforeHeart" alt="" src="images/before_heart.png" id="btnWishBeforImage" style="width: 30px; height: 30px;"/>');
// 				}, 
// 				error: function(xhr, textStatus, errorThrown) {
// 					alert("찜 삭제 실패"); 
// 				}
// 			});
// 	});
	
	
			
// });

	
	function deleteWish() {
		$.ajax({
			type: "post", 
			url: "LikeDeletePro.ca", 
			data: { 
				member_idx: '${sessionScope.member_idx}',
				product_idx: $("#product_idx").val()
			},	
			dataType: "html", 
			success: function(data) { 
	//					$("#btnWishAfterImage").attr("src", "images/before_heart.png");
					alert("찜한 상품에서 삭제했습니다!");
	//					$('#wishLoad').load(location.href+' #wishLoad')
					$(".wishBtn").html('<img id="beforeHeart" alt="" src="images/before_heart.png" id="btnWishBeforImage" onclick="addWish()" style="width: 30px; height: 30px;"/>');
			}, 
			error: function(xhr, textStatus, errorThrown) {
				alert("찜 삭제 실패"); 
			}
		});
	}
		
	function addWish() {
		
		var checkLogin = '<%=(String)session.getAttribute("sId")%>';
// 		var checkLogin = ${sessionScope.member_idx};
		
		if( checkLogin == "null"){
			alert("로그인 후 이용 가능합니다.");
			location.href="LoginMember.me";
			
		} else {
			
			$.ajax({
				type: "post", 
				url: "LikeInsertPro.ca", 
				data: { 
					member_idx: '${sessionScope.member_idx}',
					product_idx: $("#product_idx").val()
				},	
				dataType: "html", 
				success: function(data) { 
	//						$("#btnWishBeforImage").attr("src", "images/after_heart.png");
	//						$('#wishLoad').load(location.href+' #wishLoad')
		
						alert("찜한 상품에 추가되었습니다!");
						
						$(".wishBtn").html('<img id="afterHeart" alt="" src="images/after_heart.png" id="btnWishAfterImage" onclick="deleteWish()" style="width: 30px; height: 30px;"/>');
				}, 
				error: function(xhr, textStatus, errorThrown) {
					alert("찜하기 실패"); 
				}
			});
		}
	}

//찜하기 취소
</script>
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
	

  <!-- 섬네일 이미지 -->
  <div id = "sform">
	<section id="image">
		<p>
		<div class="title" align="left">
			<img alt="shoes" src="./upload/${image.image_main_file}" width="450px" height="650px">
		</div>
	</section>
	<!-- 상품 사진 옆 -->
	<section id="detail" >
		<div class="text" > 
			<p>${product.product_brand}</p>
			<p class ="prod_name">${product.product_name }</p>
			<p>상품번호 : ${product.product_idx }</p>			
		</div>
		<div id="detail1">
			<p class="prod_title">가격</p>
			<p>${product.product_price }</p>
			<hr>
			<p class="prod_title">판매수</p>
			<p>${product.product_sell_count }</p>
			<hr>
			<p class="prod_title">좋아요 </p>
			<hr>
			<p class="prod_title">구매후기(별점) </p>
			<hr>
			<p class="prod_title">색상</p>
			<select name="product_color">
				<option selected>색깔</option>

				<c:forEach var="color" items="${colorlist}">
				<option value="color">${color }</option>
				</c:forEach>
			</select>
			<hr>
		</div>
		<div id="detail2" >
			<p>사이즈</p>
			<select name="product_size">
				<option selected>사이즈</option>
				<c:forEach var="category" items="${categorylist}">
				<option value="${category}">${category}</option>
				</c:forEach>
			</select>
			<hr>
			<input type="hidden" id="product_idx" value="${product.product_idx }">
		<span id="wishLoad">
			<c:choose>
				<c:when test="${wish.product_idx eq product.product_idx }">
					<span class="wishBtn">
						<img onclick="deleteWish()" id="afterHeart" alt="" src="images/after_heart.png" id="btnWishAfterImage" style="width: 30px; height: 30px;"/>
					</span>
				</c:when>
				<c:otherwise>
					<span class="wishBtn">
						<img id="beforeHeart" onclick="addWish()" alt="" src="images/before_heart.png" id="btnWishBeforImage" style="width: 30px; height: 30px;"/>
					</span>
				</c:otherwise>
			</c:choose>
		</span>	
		
		<input type="button" value="장바구니" onclick="location.href='CartInsertPro.ca?product_idx=${param.product_idx}&member_idx=${member_idx}'">

			<button onclick="location.href='OrderDetailForm.po?member_idx=${sessionScope.member_idx}&product_idx=${param.product_idx}'">구매하기</button>
		</div>
	</section>

  </div>
  <Br>
  <br>
  <br>
 	
  <table id="detail_table">
		<tr>
			<td><img alt="shoes" src="./upload/${image.image_real_file1}" width="450px"></td>
		</tr>	
		
		<tr>
			<td><img alt="shoes" src="./upload/${image.image_real_file2}" width="450px"></td>
		</tr>
		
	</table>

	<hr>	 <%-- 리뷰구역 --%> -->
		<div id="reviewListArea">
			<h3>Review</h3>
				<div>
					<c:forEach var="review" items="${reviewList }">
						<table class="reviewContent">
							<tr>
								<td rowspan="7" width="20%"><img id="imgSize" src="./upload/${review.review_real_img }" width="100px" ></td>
								<td style="text-align:left;" width="65%"><small>상세 사이즈 및 색상 : ${review.re_order_detail }</small></td>							
								<td style="text-align:left;" width="15%">
									<small>
									작성일 : ${review.review_date } <br>
 									주문자 : ${review.member_idx }<br>
 									</small>
								</td>					
							</tr>
							<tr></tr>
							<tr>
								<td colspan="4" style="text-align:left"><br>${fn:replace(review.review_content, cn, br) }</td>
							</tr>
						</table>
						<c:if test="${sessionScope.sId eq 'admin' || param.member_idx eq review.member_idx }" >
							<input id="delBtn" type="button" value="리뷰 삭제하기" class="btn btn-sm btn-secondary" onclick="location.href='ReviewDeletePro.po?product_idx=${review.product_idx }&member_idx=${sessionScope.member_idx }&review_idx=${review.review_idx}'">
						</c:if>		
					</c:forEach>
				</div>
				<section id="pageList" style="text-align:center">				
			<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
				<c:choose>
					<c:when test="${pageNum eq i}">
						${i }
					</c:when>
					<c:otherwise>
						<a href="${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</section>	
		</div> 	
	

 main
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

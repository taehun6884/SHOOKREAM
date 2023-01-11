<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>상품 등록</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
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
    <p class="w3-left">상품수정</p>
    <p class="w3-right">
      <i class="fa fa-shopping-cart w3-margin-right"></i>
      <i class="fa fa-search"></i>
    </p>
</header>

  
  <!--  상품등록 폼 -->
  <footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
    <div id = "sform">
        <h4>상품수정</h4>
        <form action="ProductModifyPro.po" method="post" enctype="multipart/form-data">
          <table>
          <tr>
          <td width="100px" align="left">상품명</td>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="Product Name" name="name" required value="${bean.product_name }" ></td>
          </tr>
          
          <tr>
          <td width="100px" align="left">상품 브랜드</td>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="Product Brand" name="brand" required value="${bean.product_brand }" ></td>
          </tr>
          
          <tr>
          <td width="100px" align="left">상품 가격</td>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="Product Price" name="price" required value="${bean.product_price }" ></td>
          </tr>
          
          <tr>
          
          <td width="100px" align="left">상품 사이즈</td>
       	  <td width="300px">
				<select name="size">
					<option value="220">220</option>
					<option value="230">230</option>
					<option value="240">240</option>
				</select>
		  </td>
		 
          <tr>
          <td width="100px" align="left">상품 재고량</td>
          <td width="300px"><input class="w3-input w3-border" type="number" min ="0" max="100" placeholder="재고수" name="amount" required value="${bean.product_amount }" ></td>
          </tr>
          
          <tr>
          <td width="100px" align="left">상품색상</td>
          <td width="300px">
				<select name="color">
					<option value="red">red</option>
					<option value="blue">blue</option>
					<option value="grey">grey</option>
				</select>
			</td>
          </tr>
          
          <tr>
          <td width="100px" align="left">요약 설명</td>
          <td><textarea class="w3-input w3-border" style="resize: none" rows="5" cols="30" placeholder="Product summary" name="exp" required="required" value="${bean.product_exp }" ></textarea></td>
<!--           <td width="300px"><input class="w3-input w3-border" type="" placeholder="Product summary" name="Product summary" required></td> -->
          </tr>
          
          <tr>
          <td width="100px" align="left">상세 설명</td>
          <td><textarea class="w3-input w3-border" style="resize: none" rows="5" cols="30" placeholder="Product detail" name="detail_exp" required="required" value="${bean.product_detail_exp }" ></textarea></td>
          </tr>
          
          <tr>
          <td width="100px" align="left">할인율</td>
          <td width="300px"><input class="w3-input w3-border" type="text" placeholder="Product discount" name="discount" required value="${bean.product_discount_price }" ></td>
          </tr>
          
          <tr>
          <td width="100px" align="left">메인 이미지</td>
          <td><input type="file" name="file"></td>
          </tr>
          <tr>
          <td width="100px" align="left">제품 이미지</td>
          <td><input type="file" name="file2"></td>
          </tr>
          
          <tr>
          <td colspan="2"><button type="submit" class="w3-button w3-block w3-black" onclick ="location.href ='ProductModifyPro.po?product_idx=${product.product_idx}'">수정하기</button></td>
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
// 드롭다운 
function dp_menu(){
    let click = document.getElementById("drop-content");
    if(click.style.display === "none"){
        click.style.display = "block";

    }else{
        click.style.display = "none";

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
<script src="//https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

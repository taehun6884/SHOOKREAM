
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<html lang="en">
    <head>
<!--         <meta charset="utf-8" /> -->
<!--         <meta http-equiv="X-UA-Compatible" content="IE=edge" /> -->
<!--         <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" /> -->
<!--         <meta name="description" content="" /> -->
<!--         <meta name="author" content="" /> -->
        <title>쿠폰등록 페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="admin/css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
        <!-- 외부 jQuery 라이브러리 등록 -->
		<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script type="text/javascript">
			<% 
			String sId = (String)session.getAttribute("sId");
			String id = request.getParameter("id");
			if(sId == null || !sId.equals("admin")) { %>
				alert("잘못된 접근입니다!")
				location.href=history.back();
			<% 
			} 
			%>
		</script>
		<style type="text/css">
			* {
				font-family: "Noto Sans KR", sans-serif;
			}
		</style>
		<script type="text/javascript">
		//할인 버튼에 따른 처리
		$(function() {
			$('input:radio[id="saleRadio1"]').on("click", function() {
				$('#testRate').attr('readonly', true);
			});
			$('input:radio[id="saleRadio2"]').on("click", function() {
				$('#testRate').attr('readonly', false);
			});
			
			
		});
		
		//할인율 숫자만 입력
		$(function() {
			$('input:number[id="discount"]').on("change", function() {
				var discount = $('#testRate').val();
				
				alert(discount);
			});
		});
		
		
		
		</script>
		
<style type="text/css">
.table-secondary{
	font-weight: bold;
	width: 20px;
}

</style>
    </head>
    <body class="sb-nav-fixed">
    
    <!-- TOP -->
       <jsp:include page="./inc2/top.jsp"></jsp:include>
          
    <!-- SIDE --> 
       <jsp:include page="./inc2/side.jsp"></jsp:include>             
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">쿠폰 등록</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"></li>
                        </ol>
                	 
                	 </div>
                	 
          
            <!-- 상품 등록 폼 -->
			<form action="CouponInsertPro.po" method="post">
				<table class="table">
					<tr>
						<td width="20px" align="left" class="table-secondary">쿠폰명</td>
						<td width="100px"><input class="w3-input w3-border"
							type="text" placeholder="Coupon Name" name="coupon_name" required ></td>
					</tr>
					
					<tr>
						<td width="20px" align="left" class="table-secondary">쿠폰 내용</td>
						<td><textarea class="w3-input w3-border" style="resize: none"
								rows="5" cols="40" placeholder="Coupon summary" name="coupon_content"
								required="required"></textarea></td>
						<!--           <td width="300px"><input class="w3-input w3-border" type="" placeholder="Product summary" name="Product summary" required></td> -->
					</tr>
					
					<tr>
						<td width="20px" align="left" class="table-secondary">할인 가격</td>
						<td><input type="text" id="testPrice" name ="coupon_price" placeholder="할인 가격을 입력하세요"><span>&nbsp;원</span> 
						</td>
						
					</tr>
					
						
				
						
					<tr>
						<td width="20px" align="left" class="table-secondary">쿠폰 시작일</td>
						<td><input type="date" name="coupon_start"></td>
					</tr>
					<tr>
						<td width="20px" align="left" class="table-secondary">쿠폰 만료일</td>
						<td><input type="date" name="coupon_end"></td>
					</tr>
					

					

					<tr>
						<td colspan="2"><button type="submit"
								class="w3-button w3-block w3-black">등록하기</button></td>
					</tr>
				</table>
			</form>


		</main>
				<jsp:include page="./inc2/footer.jsp"></jsp:include>
            </div>
        <!-- plugin -->
            
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="admin/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="admin/assets/demo/chart-area-demo.js"></script>
        <script src="admin/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="admin/js/datatables-simple-demo.js"></script>
<!--         <script src="../js/jquery-3.6.3.js"></script> -->
        
		



    </body>
	<!-- 할인율 -->
		<script type="text/javascript">
			document.querySelector('#testCalBtn').addEventListener('click', function() {
				//상품가격의 값 가져오기.
			    var originPrice = document.querySelector('#testPrice').value;
				//할인율 값 가져오기. 
			    var discountRate = document.querySelector('#testRate').value;
			 	//연산결과에 따른 처리
			    if(! originPrice || !discountRate) {
			        return false;
			    //할인율 계산식
			    } else {
			    	//할인율에 따른 계산
			        var discounted = Math.round(originPrice * (discountRate / 100));	// 정수로 출력하기 위해 소수점 아래 반올림 처리
			        //판매가격 - 할인율 계산
			        var releasePrice = originPrice - discounted;
			        document.querySelector('#testResultBox02').innerText = releasePrice + '원'
			    }
			});
		</script>
		
	<!-- 숫자 에 "," 처리를 위한 함수 -->
        <script type="text/javascript">
		    function comma(str) {
		        str = String(str);
		        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		    }
		
		    function uncomma(str) {
		        str = String(str);
		        return str.replace(/[^\d]+/g, '');
		    } 
		    
		    function inputNumberFormat(obj) {
		        obj.value = comma(uncomma(obj.value));
		    }
		    
		    function inputOnlyNumberFormat(obj) {
		        obj.value = onlynumber(uncomma(obj.value));
		    }
		    
		    function onlynumber(str) {
			    str = String(str);
			    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g,'$1');
			}
		</script>
     	
</html>
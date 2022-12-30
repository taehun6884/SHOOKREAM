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
        <title>상품수정 페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="admin/css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- 외부 jQuery 라이브러리 등록 -->
		<script src = "../js/jquery-3.6.3.js"></script>
		
		
    </head>
    <body class="sb-nav-fixed">
    
    <!-- TOP -->
       <jsp:include page="./inc2/top.jsp"></jsp:include>
          
    <!-- SIDE --> 
       <jsp:include page="./inc2/side.jsp"></jsp:include>             
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">상품 수정</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"></li>
                        </ol>
                	 
                	 </div>
                	 
          
            <!-- 상품 등록 폼 -->
			<form action="ProductModifyPro.po?product_idx=${product.product_idx}" method="post" enctype="multipart/form-data">
				<table class="table">

					<tr>
						<td width="100px" align="left">상품명</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product Name" name="name" value ="${product.product_name }"  required ></td>
					</tr>
					<tr>
						<td width="100px" align="left">상품 브랜드</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product brand" name="brand" value ="${product.product_brand }" required></td>
					</tr>
					<tr>
						<td width="100px" align="left" >상품 가격</td>
						<td><input type="text" id="testPrice" name ="price" placeholder="원래 가격을 입력하세요" value ="${product.product_price }"><span>&nbsp;원</span> <!-- 콤마 있는 값 -->
						</td>
						
					</tr>
					
						
					<tr>
						<td width="100px" align="left">할인율</td>
						<td>
						<input type="text" id="testRate" name = "discount" placeholder="할인율"  value ="${product.product_discount_price }">
						<span>&nbsp;%&nbsp;</span>
						<button type="button" id="testCalBtn">&nbsp;계산하기&nbsp;</button>
						</td>
						
					<tr>
						<td width="150px" align="left">할인율 적용가격</td>
						<td><p id ="testResultBox02"></p></td>
						
					</tr>
	
					<tr>

						<td width="100px" align="left">상품 사이즈</td>
						<td width="300px">
						<select name="size">
								<option value ="${product.product_discount_price }" selected="selected"></option>
								<option value="220">220</option>
								<option value="230">230</option>
								<option value="240">240</option>
								<option value="240">250</option>
								<option value="240">260</option>
								<option value="240">270</option>
								<option value="240">280</option>
								<option value="240">290</option>
								<option value="240">300</option>
						</select></td>
					<tr>
						<td width="100px" align="left">상품 재고량</td>
						<td width="300px">
						<input class="w3-input w3-border" type="number" min="0" max="100" placeholder="수량" name="amount" onkeyup="inputNumberFormat(this);" value ="${product.product_amount }" required>
						</td>
					</tr>

					<tr>
						<td width="100px" align="left">상품색상</td>
						<td width="300px"><select name="color">
								<option value="red">red</option>
								<option value="blue">blue</option>
								<option value="grey">grey</option>
						</select></td>
					</tr>

					<tr>
						<td width="100px" align="left">요약 설명</td>
						<td><textarea class="w3-input w3-border" style="resize: none"
								rows="5" cols="30" placeholder="Product summary" name="exp" 
								required="required">${product.product_exp }</textarea></td>
						<!--           <td width="300px"><input class="w3-input w3-border" type="" placeholder="Product summary" name="Product summary" required></td> -->
					</tr>

					<tr>
						<td width="100px" align="left">상세 설명</td>
						<td><textarea class="w3-input w3-border" style="resize: none"
								rows="5" cols="30" placeholder="Product detail"
								name="detail_exp" required="required">${product.product_detail_exp }</textarea></td>
					</tr>


					<tr>
						<td width="100px" align="left">메인 이미지</td>
						<td><input type="file" name="file" >기존 메인 파일: <input type="text" value="${image.image_main_file }" width="100" name ="origin_file" readonly></td>
					</tr>
					<tr>
						<td width="100px" align="left">제품 이미지1</td>
						<td><input type="file" name="file2" >기존 제품 이미지1: <input type="text" value="${image.image_real_file1 }" width="100" name ="origin_file2" readonly></td>
					</tr>
					<tr>
						<td width="100px" align="left">제품 이미지2</td>
						<td><input type="file" name="file3"> 기존 제품 이미지2: <input type="text" value="${image.image_real_file2 }" width="100" name ="origin_file3" readonly></td>
					</tr>

					<tr>
						<td colspan="2"><button type="submit"
								class="w3-button w3-block w3-black">수정하기</button></td>
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
        <script src="../js/jquery-3.6.3.js"></script>
        
		



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
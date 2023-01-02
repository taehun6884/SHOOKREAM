
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
        <title>상품등록 페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="admin/css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- 외부 jQuery 라이브러리 등록 -->
		<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
		
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
    </head>
    <body class="sb-nav-fixed">
    
    <!-- TOP -->
       <jsp:include page="./inc2/top.jsp"></jsp:include>
          
    <!-- SIDE --> 
       <jsp:include page="./inc2/side.jsp"></jsp:include>             
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">상품 등록</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"></li>
                        </ol>
                	 
                	 </div>
                	 
          
            <!-- 상품 등록 폼 -->
			<form action="ProductInsertPro.po" method="post"
				enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td width="100px" align="left" class="table-secondary">상품명</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product Name" name="name" required ></td>
					</tr>
					<tr>
						<td width="100px" align="left" class="table-secondary">상품 브랜드</td>
						<td width="300px">
						<select name="brand" >
								<option value="" selected>브랜드를 선택하세요</option>
								<option value="나이키">나이키</option>
								<option value="뉴발란스">뉴발란스</option>
								<option value="컨버스">컨버스</option>
								<option value="아디다스">아디다스</option>
								<option value="슈펜">슈펜</option>
						</select>
						</td>
					</tr>
					<tr>
						<td width="100px" align="left" class="table-secondary">상품 가격</td>
						<td><input type="text" id="testPrice" name ="price" placeholder="원래 가격을 입력하세요"><span>&nbsp;원</span> 
						</td>
						
					</tr>
					
						
					<tr>
						<td width="100px" align="left" class="table-secondary">할인율</td>
						<td>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="saleRadio" id="saleRadio1" >
							<label class="form-check-label" for="flexRadioDefault2">할인 미선택</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="saleRadio" id="saleRadio2" > 
							<label class="form-check-label" for="flexRadioDefault1">할인 적용 
							<input type="number" id="testRate" name ="discount" value = "0" size="1" min = "0" max ="100" style = "text-align:center;" readonly="readonly"> <!-- 할인율 입력칸 -->
							<span>%</span>
							<button type="button" id="testCalBtn">&nbsp;계산하기&nbsp;</button>
							</label>
						</div>
						
						</td>
						<td>
						</td>
						
					<tr>
						<td width="150px" align="left" class="table-secondary">할인율 적용가격</td>
						
						<td><p id = "testResultBox02"></p></td>
						
					</tr>
	
					<tr>

						<td width="100px" align="left" class="table-secondary">상품 사이즈</td>
						<td width="300px">
						<select name="size">
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
						<td width="100px" align="left" class="table-secondary">상품 재고량</td>
						<td width="300px"><input class="w3-input w3-border" type="number" min="0" max="100" placeholder="수량" name="amount" onkeyup="inputNumberFormat(this);" required></td>
					</tr>

					<tr>
						<td width="100px" align="left" class="table-secondary">상품색상</td>
						<td width="300px"><select name="color">
								<option value="red">red</option>
								<option value="blue">blue</option>
								<option value="grey">grey</option>
						</select></td>
					</tr>

					<tr>
						<td width="100px" align="left" class="table-secondary">요약 설명</td>
						<td><textarea class="w3-input w3-border" style="resize: none"
								rows="5" cols="40" placeholder="Product summary" name="exp"
								required="required"></textarea></td>
						<!--           <td width="300px"><input class="w3-input w3-border" type="" placeholder="Product summary" name="Product summary" required></td> -->
					</tr>

					<tr>
						<td width="100px" align="left" class="table-secondary">상세 설명</td>
						<td><textarea class="w3-input w3-border" style="resize: none"
								rows="10" cols="150" placeholder="Product detail"
								name="detail_exp" required="required"></textarea></td>
					</tr>


					<tr>
						<td width="100px" align="left" class="table-secondary">메인 이미지</td>
						<td><input type="file" name="file"></td>
					</tr>
					<tr>
						<td width="100px" align="left" class="table-secondary">제품 이미지1</td>
						<td><input type="file" name="file2"></td>
					</tr>
					<tr>
						<td width="100px" align="left" class="table-secondary">제품 이미지2</td>
						<td><input type="file" name="file3"></td>
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
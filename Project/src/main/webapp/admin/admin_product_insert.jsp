<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
    <head>
<!--         <meta charset="utf-8" /> -->
<!--         <meta http-equiv="X-UA-Compatible" content="IE=edge" /> -->
<!--         <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" /> -->
<!--         <meta name="description" content="" /> -->
<!--         <meta name="author" content="" /> -->
        <title>관리자 페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="admin/css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
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
                	 
          
                     <!-- 회원정보 확인 (List) -->
			<form action="ProductInsertPro.po" method="post"
				enctype="multipart/form-data">
				<table class="table">

					<tr>
						<td width="100px" align="left">상품명</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product Name" name="name" required></td>
					</tr>

					<tr>
						<td width="100px" align="left">상품 브랜드</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product Brand" name="brand" required></td>
					</tr>

					<tr>
						<td width="100px" align="left">상품 가격</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product Price" name="price" required></td>
					</tr>

					<tr>

						<td width="100px" align="left">상품 사이즈</td>
						<td width="300px"><select name="size">
								<option value="220">220</option>
								<option value="230">230</option>
								<option value="240">240</option>
						</select></td>
					<tr>
						<td width="100px" align="left">상품 재고량</td>
						<td width="300px"><input class="w3-input w3-border"
							type="number" min="0" max="100" placeholder="재고수" name="amount"
							required></td>
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
								required="required"></textarea></td>
						<!--           <td width="300px"><input class="w3-input w3-border" type="" placeholder="Product summary" name="Product summary" required></td> -->
					</tr>

					<tr>
						<td width="100px" align="left">상세 설명</td>
						<td><textarea class="w3-input w3-border" style="resize: none"
								rows="5" cols="30" placeholder="Product detail"
								name="detail_exp" required="required"></textarea></td>
					</tr>

					<tr>
						<td width="100px" align="left">할인율</td>
						<td width="300px"><input class="w3-input w3-border"
							type="text" placeholder="Product discount" name="discount"
							required></td>
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
    </body>
</html>
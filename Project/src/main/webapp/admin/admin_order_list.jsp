<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 목록</title>
<meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>주문 조회</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="admin/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
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
</head>
 <body class="sb-nav-fixed">
  	<!-- TOP -->
       <jsp:include page="./inc2/top.jsp"></jsp:include>
          
    <!-- SIDE --> 
       <jsp:include page="./inc2/side.jsp"></jsp:include>            
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">주문 목록</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="Admin.ad">Dashboard</a></li>
<!--                             <li class="breadcrumb-item active">Tables</li> -->
                        </ol>
                        <div class="card mb-4">
<!--                             <div class="card-body"> -->
<!--                                 DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the -->
<!--                                 <a target="_blank" href="https://datatables.net/">official DataTables documentation</a> -->
<!--                                 . -->
<!--                             </div> -->
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                주문 목록 조회
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>이미지</th>
                                            <th>주문ID</th>
                                            <th>가격</th>
                                            <th>주문상태</th>
                                            <th>배송상태</th>
                                            <th>주문일자</th>
                                            <th>수정</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Office</th>
                                            <th>Age</th>
                                            <th>Start date</th>
                                            <th>Salary</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                      <c:forEach var="orderlist" items="${Adminorderlist }">
										<tr>
										<td>${orderlist.order_idx }</td>
										 <td><img src="upload/${orderlist.order_main_image }"  alt="없음!" class="img-thumbnail" width="150" height="150"></td>
									      <td>${orderlist.order_member_id }</td>
									      <td><fmt:formatNumber value="${orderlist.order_product_price }" pattern="#,###원"></fmt:formatNumber></td>
									      <td>${orderlist.order_category }</td>
									      <td>${orderlist.order_progress }</td>
									      <td><fmt:formatDate value="${orderlist.order_date }" pattern ="yyyy-MM-dd"/></td>
										<td>
<!-- 										<button type="button" class="btn btn-light" onclick="location.href=''">수정</button> -->
										<button type="button" class="btn btn-light" onclick="location.href='AdminProductOrderListDelete.ad?order_idx=${orderlist.order_idx}'">삭제</button>
										</td>
										</tr> 
										</c:forEach>  
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
<!--         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script> -->
        <script src="admin/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="admin/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
</html>
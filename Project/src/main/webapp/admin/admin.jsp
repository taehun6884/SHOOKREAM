<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>관리자 페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="admin/css/styles.css" rel="stylesheet" />
<!--         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" /> -->
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
                        <h1 class="mt-4" style="padding: 45px; font-size: 25px; font-weight: bolder;">관리자 페이지</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"></li>
                        </ol>
                        <div class="row" style="padding: 20px 45px;">
                             <div class="col-xl-3 col-md-6">
                                <div class="card text-white mb-4" style="background-color: #787878" >
                                    <div class="card-body" onclick="location.href='AdminProduct.ad'">상품 및 주문 관리</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="AdminProduct.ad">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card text-white  mb-4" style="background-color: #787878">
                                    <div class="card-body" onclick="location.href='AdminMemberCoupon.ad'">회원 및 쿠폰 관리</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="AdminMemberCoupon.ad">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                           
                            <div class="col-xl-3 col-md-6">
                                <div class="card text-white mb-4" style="background-color: #787878">
                                    <div class="card-body">게시판 관리</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="AdminBoard.ad">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                     
                    </div>
                </main>
				<jsp:include page="./inc2/footer.jsp"></jsp:include>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="admin/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="admin/assets/demo/chart-area-demo.js"></script>
        <script src="admin/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="admin/js/datatables-simple-demo.js"></script>
    </body>
</html>

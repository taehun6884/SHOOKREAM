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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        
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
                        <h1 class="mt-4">관리자 - 게시판 관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">FAQ 관리</li>
                        </ol>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                	<c:choose>
										<c:when test="${empty param.pageNum }">
											<c:set var="pageNum" value="1" />
										</c:when>
										<c:otherwise>
											<c:set var="pageNum" value="${param.pageNum }" />
										</c:otherwise>
									</c:choose>
                                    <thead>
                                        <tr>
                                            <th>분류</th>
                                            <th>카테고리</th>
                                            <th>공지사항 번호</th>
                                            <th>공지사항 제목</th>
                                            <th>게시 날짜</th>
                                            <th>게시물 관리</th>
                                        </tr>
                                    </thead>
                                    <c:forEach var="board" items="${boardList }">
                                        <tr>
                                            <th>${board.notice_type }</th>
                                            <th>${board.notice_category }</th>
                                            <th>${board.notice_idx }</th>
                                            <th>${board.notice_subject }</th>
                                            <th>${board.notice_date }</th>
                                            <th>
										      	<input type="button" value="수정"  class="btn btn-outline-secondary btn-sm" onclick="location.href='BoardModifyForm.bo'">
												<input type="button" value="삭제" class="btn btn-outline-secondary btn-sm" onclick="confirmDelete()">
										    </th>
                                        </tr>
                                     </c:forEach>   
                                </table>
                       		 </div>
                    </div>
                </main>
				<jsp:include page="./inc2/footer.jsp"></jsp:include>
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

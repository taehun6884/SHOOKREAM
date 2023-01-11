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
                        <h1 class="mt-4">게시판 관리</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">공지사항 관리</li>
                        </ol>
                            <div class="card-body">
                           	<form method="post">
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
                                            <th>글 번호</th>
                                            <th>카테고리</th>
                                            <th>글 제목</th>
                                            <th>게시 날짜</th>
                                            <th>게시물 관리</th>
                                        </tr>
                                    </thead>
                                    <c:forEach var="board" items="${boardList }">
                                        <tr>
                                            <th>${board.notice_type }</th>
                                            <th>${board.notice_idx }</th>
                                            <th>${board.notice_category }</th>
                                            <th>${board.notice_subject }</th>
                                            <th>${board.notice_date }</th>
                                            <th>
										      	<a href="BoardModifyForm.bo?notice_idx=${board.notice_idx }&pageNum=${param.pageNum}"><input type="button" value="수정"  class="btn btn-outline-secondary btn-sm" ></a>
												<a href="BoardDeletePro.bo?notice_idx=${board.notice_idx }&pageNum=${param.pageNum}&board_type=${board.notice_type }"><input type="button" value="삭제" class="btn btn-outline-secondary btn-sm"></a>
										    </th>
                                        </tr>
                                     </c:forEach>   
                                </table>
							 </form>
							 <input type="button" onclick="location.href='BoardWriteForm.bo'" value="글쓰기" style="text-align:center">
                                <section id="pageList" style="text-align:center">
									<c:choose>
										<c:when test="${param.pageNum > 1}">
											<input type="button" class="btn btn-outline-secondary btn-sm" value="이전" onclick="location.href='AdminNoticeManage.ad?pageNum=${pageNum - 1}'">
										</c:when>
										<c:otherwise>
											<input type="button" class="btn btn-outline-secondary btn-sm" value="이전">
										</c:otherwise>
									</c:choose>
										
									<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
									<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
										<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
										<c:choose>
											<c:when test="${param.pageNum eq i}">
												${i }
											</c:when>
											<c:otherwise>
												<a href="AdminNoticeManage.ad?pageNum=${i }">${i }</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							
									<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
									<c:choose>
										<c:when test="${param.pageNum < pageInfo.maxPage}">
											<input type="button" value="다음" class="btn btn-outline-secondary btn-sm" onclick="location.href='AdminNoticeManage.ad?pageNum=${pageNum + 1}'">
										</c:when>
										<c:otherwise>
											<input type="button" class="btn btn-outline-secondary btn-sm" value="다음">
										</c:otherwise>
									</c:choose>
								</section>	
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

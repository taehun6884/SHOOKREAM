<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<script type="text/javascript">
	// 드롭박스 선택에 따른 드롭박스2번 변
	function categoryChange(e) {
		var category_notice = ["Event", "Delivery", "Notice"];
		var category_faq = ["교환/반품","회원정보","배송","주문/결제", "서비스"];
		var target = document.getElementById("category");
		
		if(e.value == "Notice") var cat_detail = category_notice;
		else if(e.value = "FAQ") var cat_detail = category_faq;
		
		target.options.length = 0;
		
		for(x in cat_detail) {
			var opt = document.createElement("option");
			opt.value = cat_detail[x];
			opt.innerHTML = cat_detail[x];
			target.appendChild(opt);
		}
	}
</script>
<style type="text/css">
			* {
				font-family: "Noto Sans KR", sans-serif;
			}
</style>
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
                        <h1 class="mt-4">관리자 페이지</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">게시판 관리</li>
                        </ol>
					  <!-- 공지사항 글쓰기 폼 -->
					  <footer class="w3-padding-64 w3-light-grey w3-small" id="footer">
					  <h3>Notice</h3>
					  <form action="BoardModifyPro.bo" method="post">
					  	<input type="hidden" name="notice_idx" value=${param.notice_idx }>
						<input type="hidden" name="pageNum" value=${param.pageNum }>
<!-- 					  	<input type="hidden" name="notice_type" value="Notice"> -->
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">Subject</label>
						  	<input type="text" class="form-control" name="notice_subject" value="${board.notice_subject }" placeholder="제목을 입력하세요">
						</div>
						<label for="exampleFormControlInput1" class="form-label">Type</label>
						<select class="form-select" aria-label="Default select example" name="notice_type" onchange="categoryChange(this)">
						  <option selected>${board.notice_type }</option>
						  
						</select>
						<label for="exampleFormControlInput1" class="form-label">Category</label>
						<select class="form-select" aria-label="Default select example" name="notice_category" id="category" onchange="selectChange(this.value)">
						  <option selected>${board.notice_category }</option>
						</select>
						<div class="mb-3">
						  <label for="exampleFormControlTextarea1" class="form-label">Content</label><br>
						  <textarea class="form-control" id="notice_content" name="notice_content" rows="15">${board.notice_content }</textarea>
						</div>
						<section id="commandCell">
							<input type="submit" value="수정" class="btn btn-secondary">&nbsp;&nbsp;
							<input type="reset" value="다시쓰기" class="btn btn-secondary">&nbsp;&nbsp;
							<input type="button" value="취소" onclick="history.back()" class="btn btn-secondary">
						</section>
					  </form>
					  </footer>
						
						
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

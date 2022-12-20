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
                        <h1 class="mt-4">관리자 페이지</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">회원목록</li>
                        </ol>
                	 
                	 </div>
                	 
          
                     <!-- 회원정보 확인 (List) -->
			  <table class ="table">
				<thead class="table-dark">
					<tr>
						<td width="100px">번호</td>
						<td width="100px">아이디</td>
						<td width="150px">이름</td>
						<td width="150px">주소</td>
						<td width="100px">전화번호</td>
						<td width="100px">적립금</td>
						<td width="100px">가입일자</td>
						<td width="150px">수정</td>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="member" items="${memberList }">
						<tr>
						<td>${member.member_idx }</td>
				  		<td>${member.member_id }</td>
					  	<td>${member.member_name }</td>
					  	<td>${member.member_address }</td>
					  	<td>${member.member_phone }</td>
					  	<td>${member.member_point }</td>
					  	<td>${member.member_date }</td>
					  	<td><input type ="button" value ="수정" onclick="location.href='MemberModifyForm.me?id=${member.member_id}&pass=${member.member_pass }'">
					  	<input type ="button" value ="삭제" onclick="location.href='MemberDeleteMemberProAction.me?id=${member.member_id}&pass=${member.member_pass }'"></td>
					
						</tr>
				  	</c:forEach>
				</tbody>
			</table>
			
			  
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

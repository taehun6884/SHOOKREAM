<%@page import="vo.BoardBean"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>SHOOKREAM</title>
		<meta charset="UTF-8">
		<script src="../images"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
		<style>
		.w3-sidebar a {font-family: "Roboto", sans-serif}
		body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
		</style>
		
		<style type="text/css">
		#logintvar{
			float: right;
		}
		
		.reviewListArea {
			
		}
		
		table { 
			border: solid 1px;
			width : 800px;
			height: 150px; 
		}
		</style>
		
	</head>
	<body class="w3-content" style="max-width:1200px">
	
	<!-- Sidebar/menu -->
	<jsp:include page="../inc/side.jsp"/>
	
	<!-- Top menu on small screens -->
	<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
	  <div class="w3-bar-item w3-padding-24 w3-wide">SHOOKREAM</div>
	  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
	</header>
	
	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>
	
	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left:250px">
	
	  <!-- Push down content on small screens -->
	  <div class="w3-hide-large" style="margin-top:83px"></div>
	  
	  <!-- Top header -->
	  <jsp:include page="../inc/top.jsp"/>
		<footer class="w3-padding-64 w3-light-grey w3-small" id="footer">
		<h2>Notice</h2>
		<br><br>
		<hr style="border:solid 1px;">	
			<h5><b>${board.notice_subject }</b></h5><br>
			Date:${board.notice_date } / View:${board.notice_readcount }
			<hr>
			${fn:replace(board.notice_content, cn, br) }			
		</footer> 
		<hr>		
		<div class="reviewListArea">
			<h3>Review</h3>
			<div class="reviewContent">
				<div>
					<table class="reviewContent">
						<tr>
							<td rowspan="2" width="30%">리뷰사진<img src="../images/kakao_small.jpg"/>${review.review_img }</td>
							<td width="60%">주문 내용${review.order_detail }</td> <%-- 주문 상세내용 : 사이즈 색상 --%>
							<td width="10%">작성 멤버${review.member_idx }<br>작성일자${review.review_data }</td> <%-- 리뷰 작성하는 멤버 --%>
						</tr>
						<tr>
							<td rowspan="2">리뷰 내용${review.review_content }</td>
						</tr>
					</table>
				</div>
			</div>	
		</div> 
		<div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></div>
	
	  <!-- End page content -->
	</div>
	</body>
</html>	
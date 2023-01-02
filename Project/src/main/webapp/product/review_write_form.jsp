<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" />
<title>Review</title>
<script>
	function closeReview() { // 창닫기
		let result = confirm("작성한 내용은 모두 삭제됩니다. 그래도 작성을 취소하시겠습니까?");
		if(result == true) {
			window.close();				
		} else {
			history.back();
		}
	}
	
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
		    
			reader.onload = function(e) {
		    	document.getElementById('preview').src = e.target.result;
		    };
		    
		    reader.readAsDataURL(input.files[0]);
		} else {
			document.getElementById('preview').src = "";
		}
	}
	$(function() {
		$(document).ready(function() {		
			$("#register").on("click", function() {
				self.close();
			});
		});		
	});
</script>
<style type="text/css">
	h2 {
		text-align : center;
	}
	
	h3 {
		text-align: right;
		margin: 10px ;
	}
	
	textarea {
		margin: 10px;
 		width: 35em; 
 		height: 300px; 
		resize: none;	
	}

	#reviewBtn {
		text-align: center;
	}
	
	#fileInsert{
		margin : 5px;
	}
	
	#preview {
		height:10em;
		width:10em;
	}
</style>
</head>
<body>
	<div>
	<h2>Review</h2>
	<h3>shookream</h3>
	</div>
	<form action="ReviewWrite.me" method="post" enctype="multipart/form-data">
		<input type="hidden" name="member_idx" value="">
		<input type="hidden" name="re_order_detail" value="">
		<input type="hidden" name="" value="">
		<input type="file" id="fileInsert" name="review_img" accept=".png, .jpeg, .jpg, .gif" onchange="readURL(this);"><br>
		<img id="preview"></img>
		<hr>
		<div >
		&nbsp; 구매 옵션<br>
			<select name="product_size">
				<option selected>구매하신 사이즈를 골라주세요</option>
				<c:forEach var="category" items="${categorylist}">
				<option value="${category}">${category}</option>
				</c:forEach>
			</select>
			<select name="product_colour" >
				<option selected>구매하신 색상 골라주세요</option>
				<c:forEach var="color" items="${colorlist}">
					<option value="${color}">${color}</option>
				</c:forEach>
			</select>	
		</div>
		<textarea placeholder="욕설 및 관련없는 내용은 관리자에 의해 삭제될 수 있습니다."></textarea><br>
		<div id="reviewBtn">
			<input type="submit" class="btn btn-outline-secondary btn-sm" id="register" value="글 등록하기">
			<input type="button" class="btn btn-outline-secondary btn-sm" value="닫기" onclick="closeReview()">
		</div>	
	</form>
</body>
</html>
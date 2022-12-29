<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" />
<title>Review</title>
<script>
	function closeReview() {
		window.close();	
	}
	
	$()
</script>
<style type="text/css">
	h2 {
		text-align : center;
	}
	
	h3 {
		text-align: right;
	}
	
	textarea {
		margin: 10px 10px 10px 10px;
 		width: 35em; 
 		height: 300px; 
		resize: none;	
	}

	#submitBtn {
		text-align: center;
	}
</style>
</head>
<body>
	<div>
	<h2>Review</h2>
	<h3>shookream</h3>
	</div>
	<form action="ReviewInsert.me" method="post" enctype="multipart/form-data">
		<input type="file">
		<hr>
		<textarea placeholder="욕설 및 관련없는 내용은 관리자에 의해 삭제될 수 있습니다."></textarea><br>
		<input type="button" class="btn btn-outline-secondary btn-sm" value="글 등록하기" id="submitBtn">
		<input type="button" class="btn btn-outline-secondary btn-sm" value="닫기" onclick="closeReview()">
	</form>
</body>
</html>
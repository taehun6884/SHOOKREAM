<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>ID Check</title>
<style type="text/css">
	* {
		font-family: "Noto Sans KR", sans-serif;
	}
</style>
<script type="text/javascript">

	function sendCheckValue() {
		var openJoinfrm = opener.document.joinForm;
		var chResult = document.getElementsByClassName("chResult");
// 		var window.opener.isIdCheck = false;
		if (document.checkIdForm.chResult.value=="N") { // 중복된 id 가 있음에도 '사용하기' 버튼 눌렀을 때
			alert("다른 아이디를 입력해주세요.");
			openJoinfrm.id.focus();
			window.close();
			
		}else { // 중복된 id 가 없고 '사용하기' 버튼 눌렀을 때
			// 중복체크 결과인 idCheck 값을 전달
			openJoinfrm.isCheckId.value="idCheck";
			openJoinfrm.dbCheckId.disabled=true;
			openJoinfrm.dbCheckId.style.opacity=0.6;
			openJoinfrm.dbCheckId.style.cursor="default";
// 			window.opener.isIdCheck = false;
// 			alert(window.opener.isIdCheck.value);
			window.close();
		}
	}
</script>

</head>
<body>
<c:set var="member_id" value="${param.member_id}"/>

<b><font size="4" color="gray">ID 중복 확인</font></b>
	<br>
	
	<form name="checkIdForm">
		<input type="text" name="id" value="${member_id}" id="id" disabled>
			
		<c:choose>
		<c:when test="${result==1}">
			<p style="color: red">이미 사용 중인 아이디입니다.</p>
			<input type="hidden" name="chResult" value="N"/>
		</c:when>
		<c:when test="${result==0}">	
			<p style="color: blue">사용가능한 아이디입니다.</p>
			<input type="hidden" name="chResult" value="Y"/>
		</c:when>
		<c:otherwise>
			<p>오류 발생(-1)</p>
			<input type="hidden" name="chResult" value="N"/>
		</c:otherwise>
		</c:choose>
		<button type="button" class="btn btn-secondary" onclick="sendCheckValue()">사용하기</button>
		<button type="button" class="btn btn-secondary" onclick="window.close()">취소</button>
	</form>
</body>
</html>
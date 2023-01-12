<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>신고하기</h1>
<form action ="ReportAddProAction.me" method ="post">
<input type="hidden" name="idx" value="${member_idx }">
<input type="hidden" name="sId" value="${sessionScope.sId }">
<table border ="1">


<tr>
<th>신고제목

</th>
<td>
<select name = "title">
    <option >신고사유 선택</option>
    <option value="광고, 상업성 리뷰입니다">광고, 상업성 리뷰입니다</option>
    <option value="욕설 ,비방글입니다">욕설 ,비방글입니다 </option>
    <option value="기타">기타 </option>
</select></td>

</tr>

<tr>
<th>신고내용</th>
<td><textarea name="content" rows="20" cols="40" placeholder="신고 내용을 입력하세요"></textarea></td>
</tr>


<tr>
<td colspan="2"><input type="submit" value = "신고하기" >  <input type="button" value = "신고취소" onclick="history.back()"></td>

</tr>


</table>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table">
  <thead  class="table-dark" >
    <tr>
      <th scope="col">#</th>
      <th scope="col">name</th>
      <th scope="col">price</th>
      <th scope="col">Use</th>
      <th scope="col">StartDate</th>
      <th scope="col">EndDate</th>
      <th scope="col">Button</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach var="coupon" items="${couponList }">
    <tr>
      <th scope="row">${coupon.coupon_idx }</th>
      <td>${coupon.coupon_name }</td>
      <td id="price">${coupon.coupon_price }</td>
      <c:choose>
      	<c:when test="${coupon.coupon_isUse eq 0 }">
      	  <td>사용가능</td>
      	</c:when>
    	<c:otherwise>
    		<td>사용 불가</td>
    	</c:otherwise>
      </c:choose>
      <td>${coupon.coupon_start }</td>
      <td>${coupon.coupon_end }</td>
      <td>
      <button type="button" class="btn btn-dark" id="useCoupon" value="${coupon.coupon_price}" onclick="useCoupon(${coupon.coupon_price})">적용하기</button>
    </tr>
     </c:forEach>
  </tbody>
</table>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	function useCoupon(price) {
		 window.opener.document.getElementById( "priceValue" ).value = price;
		 window.opener.document.getElementById( "totalprice" ).value =Number(${param.product_price})-Number(price);	
		 close();
	}
	

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>
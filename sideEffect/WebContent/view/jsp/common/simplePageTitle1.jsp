<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${!empty titleImg }">
		<img src="${titleImg }" alt="titleImg"/>
	</c:when>
</c:choose>
<br>
<c:choose>
	<c:when test="${!empty titleImage }">
		<img src="${titleImage }" alt="titleImage"/>	
	</c:when>
</c:choose>

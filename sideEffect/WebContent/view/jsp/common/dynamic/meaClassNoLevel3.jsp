<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:choose>
 	<c:when test="${!empty list }">
		<select name="parentItem2" >
			<c:forEach var="lv2Item" items="${list }">
				<option value="${lv2Item.mea_class_no }">${lv2Item.class_kor_name }</option>
			</c:forEach>
		</select>
		
		<select class="hidden" name="parentItemGrade2" >
			<c:forEach var="lv2Item" items="${list }">
				<option value="${lv2Item.grade }">${lv2Item.grade }</option>
			</c:forEach>
		</select>
 	</c:when>
 	<c:otherwise>
 		no match
 	</c:otherwise>
 </c:choose>
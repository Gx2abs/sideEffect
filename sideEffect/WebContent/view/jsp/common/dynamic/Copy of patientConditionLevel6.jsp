<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:choose>
 	<c:when test="${!empty level2List }">
		<select name="parentItem2" id="parentItem2">
			<c:forEach var="lv2Item" items="${level2List }">
				<option value="${lv2Item.id }">${lv2Item.fdaSourcePtKor }</option>
			</c:forEach>
		</select>
	</c:when>
 	<c:otherwise>
 		no match
 	</c:otherwise>
 </c:choose>
 
 <c:choose>
 	<c:when test="${!empty level3List }">
 		<select name="parentItem2"  id="parentItem2">
			<c:forEach var="lv2Item" items="${level2List }">
				<option value="${lv2Item.id }">${lv2Item.fdaSourcePtKor }</option>
			</c:forEach>
		</select>
		<select name="parentItem3" >
			<c:forEach var="lv3Item" items="${level3List }">
				<option value="${lv3Item.id }">${lv3Item.fdaSourcePtKor }</option>
			</c:forEach>
		</select>
	</c:when>
 	<c:otherwise>
 		no match
 	</c:otherwise>
 </c:choose>
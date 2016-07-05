<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
 	<c:when test="${!empty level5List }">
 		<select name="parentItem5" id="parentItem5">
 			<option value=""> - LEVEL 5 선택 - </option>
			<c:forEach var="lv5Item" items="${level5List }">
				<%-- <option value="${lv5Item.id }">${lv5Item.id } ${lv5Item.fdaSourcePtKor }</option> --%>
				<option value="${lv5Item.id }">[${lv5Item.fdaCode}] ${lv5Item.fdaSourcePtKor }</option>
			</c:forEach>
		</select>
	</c:when>
 	<c:otherwise>
 		no match
 	</c:otherwise>
 </c:choose>
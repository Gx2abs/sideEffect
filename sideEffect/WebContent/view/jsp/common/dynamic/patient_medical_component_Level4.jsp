<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
 	<c:when test="${!empty level3List }">
 		<select name="parentItem3" id="parentItem3">
 			<option value=""> - LEVEL 3 선택 - </option>
			<c:forEach var="lv3Item" items="${level3List }">
				<%-- <option value="${lv3Item.id }">${lv3Item.id } ${lv3Item.fdaSourcePtKor }</option> --%>
				<option value="${lv3Item.id }">[${lv3Item.fdaCode}] ${lv3Item.fdaSourcePtKor }</option>
			</c:forEach>
		</select>
	</c:when>
 	<c:otherwise>
 		no match
 	</c:otherwise>
 </c:choose>
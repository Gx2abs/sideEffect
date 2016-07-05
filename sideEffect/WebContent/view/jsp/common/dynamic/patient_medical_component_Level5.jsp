<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:choose>
 	<c:when test="${!empty level4List }">
		<select name="parentItem4" id="parentItem4">
			<option value=""> - LEVEL 4 선택 - </option>
			<c:forEach var="lv4Item" items="${level4List }">
				<%-- <option value="${lv4Item.id }">${lv4Item.id } ${lv4Item.fdaSourcePtKor }</option> --%>
				<option value="${lv4Item.id }">[${lv4Item.fdaCode}] ${lv4Item.fdaSourcePtKor }</option>
			</c:forEach>
		</select>
	</c:when>
 	<c:otherwise>
 		no match
 	</c:otherwise>
 </c:choose>
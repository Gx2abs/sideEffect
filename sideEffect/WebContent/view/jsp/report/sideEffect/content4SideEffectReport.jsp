<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style=" width: 100%; height: 100%; border: 3px thick;">

	<c:forEach var="item" items="${requestScope.list }">
		${item.reporterName}/${item.gender.propertyValue }/${item.causality.propertyValue }<br/>
	</c:forEach>
	<br/>
	contentName ; ${contentName }<br>
	
</div>

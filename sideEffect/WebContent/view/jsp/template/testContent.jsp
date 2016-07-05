<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div >
	<c:forEach var="item" items="${list }">
		${item.meddev_item_seq }/${item.delete_yn }<br/>
	</c:forEach>
</div>
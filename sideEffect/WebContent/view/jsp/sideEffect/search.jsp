<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">

<form name="allSearchForm1" method="post" action="sideEffectReport.do">

	<input type="text" name="action" value="list" />
	
	<div class="header">
		    
		<select name="sc">
			<option value="1">통합검색 </option>
		</select>
		
		<input type="text" name="sv" value="<%=sv %>" onkeypress="EnterDown()"/>
		<input type="button" value="검색" onclick="search()" />
		
	</div>

</form>
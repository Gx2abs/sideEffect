<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 	<div style="overflow-x: scroll; overflow-y: scroll">
 		${message }
 		<input type="text" name="returnUrl" value="${returnUrl }"/>
 		<input type="button" name="confirm" value="Ok" onclick="ok();"/>
 	</div>
 
 
 <script type="text/javascript" >
 	function ok(){
 		var returnUrl = $("input[name=returnUrl]").val();
 		location.href=returnUrl;
 	}
 </script>
 
 
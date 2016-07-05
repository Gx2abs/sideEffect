<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function submit(articleId, name){
	//alert(articleId, name);
	     //    alert(opener.document.safetyCreateForm1);
	opener.document.safetyCreateForm1.meb_item_manuf_id.value = articleId;
	opener.document.safetyCreateForm1.meb_item_manuf_name.value = name;
	window.close();
}
</script>

</head>
<body>

<c:forEach var="meb_item" items="${list }">	
	<div class="buttonised" onclick="submit(${meb_item.meddev_item_seq}, '${meb_item.item_name }');">${meb_item.item_name }</div><br/>
</c:forEach>
</body>
</html>
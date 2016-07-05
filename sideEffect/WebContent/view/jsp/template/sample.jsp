<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper" style="width: 100%; height: 100%;">
		<div id="head" style="width: 100%; display: block; border: 3px solid green;"><%@include file="head.jsp" %></div>
			<div style="display: block; border: 3px solid black; width: 100%; height: 800px;">
				<div id="left" style="float: left; position: relative; width: 20%; border: 2px solid brown;"><%@include file="left.jsp" %></div>
					<div id="content" style="float: left; position: relative; width: 59%; border: 2px solid red;"><%@include file="content.jsp" %></div>
				<div id="right" style="float: right; position: relative; width: 20%; border: 2px solid purple"><%@include file="right.jsp" %></div>
			</div>
		<div id="footer" style="width: 100%; border: 3px solid blue;"><%@include file="footer.jsp" %></div>
	</div>
</body>
</html>
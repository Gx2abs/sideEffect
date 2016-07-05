<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge.chrome=1" />
<title>Insert title here</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">

window.onload = function() {
	elementValueCheck("searchKeyword");
	elementValueCheck("accountName");
	elementValueCheck("password");
};

function elementValueCheck(element){
	
	//var targetElement = $("[name=searchKeyword], [name=accountName]");
	var targetElement = $("input[name="+element+"]");
	var searchKeyword = targetElement.val();
	
	if(searchKeyword!=undefined){
		
		$("form").on("submit", function(e){
			
			searchKeyword = targetElement.val().toLowerCase();
			
			if(searchKeyword!=undefined){
				var badList = new Array("!", "@",  "#", 
						                "$", "%",  "^", 
						                "&", "*",   
						                "_",  "--", 
						                "=", "+",  "\"", 
						                "|", "~",  "`", 
						                "{", "}",  "[", 
						                "]", ";",  ":", 
						                "'", "<",  ">", 
						                "/", 
						                "?", "\\",  
						                "select", "from", "where", 
						                "union", "' or 1=1", "drop", 
						                "/*", "*/");
		

				for(word in badList){
					if(searchKeyword.indexOf(badList[word]) > -1){
						alert("특수문자 및 허용하지 않는 문자열이 포함되어있습니다.");
						targetElement.focus();
						return false;
						break;
					}
				}
			}
			
		});
		
	}
}
</script>


</head>
<body>
	<div id="wrapper" style="width: 100%; height: 100%; min-height: 100%; ">
		<div id="head" style="width: 100%; display: block; position: absolute;"><%@include file="head.jsp" %></div>
			<div style="display: block; width: 100%; height: 750px;">
				<div id="left" style="float: left;"><%@include file="left.jsp" %></div>
					<div id="content"><%@include file="content.jsp" %></div>
				<%-- <div id="right" style="float: right; position: relative; width: 10%; left:50px; top:200px;"><%@include file="right.jsp" %></div> --%>
			</div>
		<div id="footer" style="width: 100%; float: left;"><%@include file="footer.jsp" %></div>
	</div>
</body>
</html>
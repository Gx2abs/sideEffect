<%@ page language="java" pageEncoding="UTF-8"%>
<div class="logoutBox3">
	<img src="view/style/images/topnavi/top_logout.jpg" name="logoutButton" id="logoutButton"/>
	${sessionScope.user.accountName }
</div>
 
 <script type="text/javascript">
 	$(document).ready(function(){
 		
 		$("#logoutButton").bind("click", function(){
 			logout();
 		});
 	});
 	
 	function logout(){
 		location.href="members.do?action=logout";
 	}
 </script>
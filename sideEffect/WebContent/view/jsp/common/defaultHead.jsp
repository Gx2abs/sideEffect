<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/jquery-ui.css">


<div class="header">
	<table class="toptable1">
	<tr>
		<td class="top1" style="background-image:url('view/style/images/topnavi/top_bg1px.jpg')" >
		<table class="toptable2">
			<tr>
				<td class="top2">
					<img src="view/style/images/topnavi/top_logo.jpg"></td>
				<td class="logoutBox1"> <%@include file="/view/jsp/members/loginBox.jsp" %></td>
			</tr>
  			<tr class="top3">
  				<td><a class="btn1" href="sideEffectReport.do?action=list"><img src="view/style/images/topnavi/menu01.jpg"></a>
  					<a class="btn2" href="statistics.do?action=xpSideEffectStatisticsAndReport"><img src="view/style/images/topnavi/menu02.jpg"></a>
  					<a class="btn3" href="risk.do?action=matrixAndRiskInItemGroup"><img src="view/style/images/topnavi/menu03.jpg"></a>
  					<a class="btn4" href="itemCategory.do?action=listItemCategory&menu=1&subMenu=1"><img src="view/style/images/topnavi/menu04.jpg"></a>
  					<a class="btn5" href="members.do?action=listMember"><img src="view/style/images/topnavi/menu05.jpg"></a>
  					<a class="btn6" href="system.do?action=listSystemProperties"><img src="view/style/images/topnavi/menu06.jpg"></a>
  				</td>
  			</tr>
  			<tr><td> <br><br><br><br><br> </td></tr>
  		</table>
  	</td>
  </tr>
  </table>
</div>

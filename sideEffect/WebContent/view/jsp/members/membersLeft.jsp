<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<div class="leftmenu">
	<img src="view/style/images/leftmenu/left04.jpg">
	<ul style="padding-left:15px;">
		<c:choose>
			<c:when test="${param.menu eq 1 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line" style="border-top:1px solid #d4d4d4;">
						<a href="members.do?action=listMember">
							<b>사용자 관리</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="members.do?action=listMember">
					사용자 관리
				</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
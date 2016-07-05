<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<div class="leftmenu">
<img src="view/style/images/leftmenu/left03.jpg">
	<ul style="padding-left:15px;">
		<c:choose>
			<c:when test="${param.menu eq 1 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line" style="border-top:1px solid #d4d4d4;">
						<a href="safetyInfo.do?action=listSafetyInfo&menu=1&subMenu=1&regionId=1">
							<b>국내 안전성 정보</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="safetyInfo.do?action=listSafetyInfo&menu=1&subMenu=1&regionId=1">
					국내 안전성 정보
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${param.menu eq 5 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="safetyInfo.do?action=listSafetyInfo&menu=1&subMenu=1&regionId=2">
							<b>해외 안전성 정보</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="safetyInfo.do?action=listSafetyInfo&menu=1&subMenu=1&regionId=2">
					해외 안전성 정보
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 6 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="publication.do?action=listPublication&menu=6&subMenu=1">
							<b>센터 발간물</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="publication.do?action=listPublication&menu=6&subMenu=1">
					센터 발간물
				</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>

</div>
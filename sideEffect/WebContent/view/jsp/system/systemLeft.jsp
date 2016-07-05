<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<div class="leftmenu">
	<img src="view/style/images/leftmenu/left05.jpg">
	<ul style="padding-left:15px;">
		<c:choose>
			<c:when test="${param.menu eq 1 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line" style="border-top:1px solid #d4d4d4;">
						<a href="system.do?action=listSystemProperties">
							<b>시스템 환경 설정</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="system.do?action=listSystemProperties">
					시스템 환경 설정
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${param.menu eq 2 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
					<a href="system.do?action=listBatchJobGroups">
						<b>엑셀 업로드</b>
					</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
					<a href="system.do?action=listBatchJobGroups">
						엑셀 업로드
					</a>
				</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${param.menu eq 3 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
					<a href="system.do?action=listSystemLog">
						<b>시스템 로그</b>
					</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
					<a href="system.do?action=listSystemLog">
						시스템 로그
					</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<div class="leftmenu">
<img src="view/style/images/leftmenu/left02.jpg">
	
	<ul>
		<li class="mebixonLeftMenu leftmenu_bottom_line">
			<a href="statistics.do?action=sideEffectStatistics">
			부작용보고 DB
			</a>
		</li>
		<li class="mebixonLeftMenu leftmenu_bottom_line">
			<a href="statistics.do?action=xpSideEffectStatisticsAndReport">
				- 항목별 월별통계
			</a>
		</li>
		<li class="mebixonLeftMenu leftmenu_bottom_line">
			<a href="statistics.do?action=xpSideEffectItemStatistics">
				- 허가번호별 통계
			</a>
		</li>
		<li class="mebixonLeftMenu leftmenu_bottom_line">
			<a href="statistics.do?action=safetyStatistics">
			안전성정보 DB
			</a>
		</li>
		<li class="mebixonLeftMenu leftmenu_bottom_line">
			<a href="statistics.do?action=xpSafetyStatisticsAndReport">
				- 항목별 월별통계
			</a>
		</li>
		<li class="mebixonLeftMenu leftmenu_bottom_line">
			<a href="statistics.do?action=xpSafetyItemStatistics">
				- 허가번호별 통계
			</a>
		</li>
	</ul>

</div>
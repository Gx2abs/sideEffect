<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<div class="leftmenu">
<img src="view/style/images/leftmenu/left03.jpg">
	<ul style="padding-left:15px;">
	
		
		<c:choose>
			<c:when test="${param.menu eq 2 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listPatientCondition&menu=2&subMenu=1">
							<b>환자문제 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listPatientCondition&menu=2&subMenu=1">
					환자문제 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 3 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listMedicalDevice&menu=3&subMenu=1">
							<b>의료기기문제 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listMedicalDevice&menu=3&subMenu=1">
					의료기기문제 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 4 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listComponent&menu=4&subMenu=1">
							<b>구성요소 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listComponent&menu=4&subMenu=1">
					구성요소 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 1 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="itemCategory.do?action=listItemCategory&menu=1&subMenu=1">
							<b>품목 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="itemCategory.do?action=listItemCategory&menu=1&subMenu=1">
					품목 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 5 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="safetyCompany.do?action=listCompany&menu=5&subMenu=1">
							<b>업체 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="safetyCompany.do?action=listCompany&menu=5&subMenu=1">
					업체 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<%-- <c:choose>
			<c:when test="${param.menu eq 6 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listProduct&menu=6&subMenu=1">
							<b>제품 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listProduct&menu=6&subMenu=1">
					제품 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose> --%>
		<!-- renewal 제품코드 -->
		<c:choose>
			<c:when test="${param.menu eq 6 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="safetyItem.do?action=listItem&menu=6&subMenu=1">
							<b>제품 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="safetyItem.do?action=listItem&menu=6&subMenu=1">
					제품 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 7}">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listCountry&menu=7&subMenu=1">
							<b>국가 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listCountry&menu=7&subMenu=1">
					국가 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 8 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listReportType&menu=8&subMenu=1">
							<b>보고종류 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listReportType&menu=8&subMenu=1">
					보고종류 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 9 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listMatchingItems&menu=9&subMenu=1">
							<b>신구품목매칭관리</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listMatchingItems&menu=9&subMenu=1">
					신구품목매칭관리
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 10 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="properties.do?action=listSeverity&menu=10&subMenu=1">
							<b>위해심각도 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="properties.do?action=listSeverity&menu=10&subMenu=1">
					위해심각도 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 11 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="risk.category.do?action=listItemCategoryGroup&menu=11&subMenu=1">
							<b>위험도 관련 코드</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="risk.category.do?action=listItemCategoryGroup&menu=11&subMenu=1">
					위험도 관련 코드
				</a>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${param.menu eq 12 }">
				<li class="mebixonLeftMenu leftmenu_bottom_line">
						<a href="importoutput.do?action=listOutput&menu=12&subMenu=1">
							<b>생산·수입 실적 데이터</b>
						</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="mebixonLeftMenu leftmenu_bottom_line">
				<a href="importoutput.do?action=listOutput&menu=12&subMenu=1">
					생산·수입 실적 데이터
				</a>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>

</div>
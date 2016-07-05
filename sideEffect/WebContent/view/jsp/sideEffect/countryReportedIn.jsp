<%@ page language="java"     pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config2.jsp" %>
	<c:choose>
		<c:when test="${!empty countriesReportedIn }">
			<c:forEach var="countryReportedIn" items="${countriesReportedIn }">
				<c:choose>
					<c:when test="${countryReportedIn.id eq article.colCountryReportedIn }">
						<input type="radio" name="countryReportedIn" checked="checked" value="${countryReportedIn.id }">${countryReportedIn.propertyValue }
					</c:when>
					<c:otherwise>
						<input type="radio" name="countryReportedIn" value="${countryReportedIn.id }">${countryReportedIn.propertyValue }
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			Component Loading Error
		</c:otherwise>
	</c:choose>
	
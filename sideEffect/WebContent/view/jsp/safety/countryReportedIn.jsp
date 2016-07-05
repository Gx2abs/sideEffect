<%@ page language="java"     pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config2.jsp" %>
	<c:choose>
		<c:when test="${!empty countriesReported_In }">
			<c:forEach var="countriesReportedin" items="${countriesReported_In }" begin="0" end="1">
				<c:choose>
					<c:when test="${countriesReportedin.id eq article.countryReportedIn.id }">
						<input type="radio" name="countryReportedIn" checked="checked" value="${countriesReportedin.id }">${countriesReportedin.propertyValue }
					</c:when>
					<c:otherwise>
						<input type="radio" name="countryReportedIn" value="${countriesReportedin.id }">${countriesReportedin.propertyValue }
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			Component Loading Error
		</c:otherwise>
	</c:choose>
	
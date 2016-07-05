<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>

  
		<c:choose>
			<c:when test="${!empty sideEffectReportTypes }">
				<c:forEach var="sideEffectReportType" items="${sideEffectReportTypes }" varStatus="num">
					<c:choose>
						<c:when test="${article.report_status[num.index].id eq sideEffectReportType.id }">
							<input type="checkbox" name="sideEffectReportType" checked value="${sideEffectReportType.id }"/> ${sideEffectReportType.propertyValue }<br/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="sideEffectReportType" value="${sideEffectReportType.id }"/> ${sideEffectReportType.propertyValue }<br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
 
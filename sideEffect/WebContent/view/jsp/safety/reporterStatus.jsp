<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

  
		<c:choose>
			<c:when test="${!empty reportStatus }">
				<c:forEach var="report_status" items="${reportStatus }" varStatus="num">
					<c:choose>
						<c:when test="${article.reportStatus.id eq report_status.id }">
							<input type="radio" name="report_status" checked value="${report_status.id }"/> ${report_status.propertyValue }<br/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="report_status" value="${report_status.id }"/> ${report_status.propertyValue }<br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
 
<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

  
		<c:choose>
			<c:when test="${!empty status }">
				<c:forEach var="varStatus" items="${status }" varStatus="num" begin="0" end="1">
					<c:choose>
						<c:when test="${article.reportStatus.id eq varStatus.id }">
							<input type="radio" name="report_status" checked value="${varStatus.id }"/> ${varStatus.propertyValue }<br/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="report_status" value="${varStatus.id }"/> ${varStatus.propertyValue }<br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
 
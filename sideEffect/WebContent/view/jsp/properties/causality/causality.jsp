<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>
		<c:choose>
			<c:when test="${!empty causality }">
			
			
				<c:forEach var="varCausality" items="${causality }" varStatus="k">
					<c:choose>
						<c:when test="${varCausality.id eq article.causality.id }">
							<input type="radio" name="CAUSALITY_ID" value="${varCausality.id }" checked /> ${varCausality.propertyValue }<br/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="CAUSALITY_ID" value="${varCausality.id }" /> ${varCausality.propertyValue }<br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
 
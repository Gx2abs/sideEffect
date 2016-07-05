<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

		<c:choose>
			<c:when test="${!empty gender }">
			
			
				<c:forEach var="varGender" items="${gender }" varStatus="k">
					<c:choose>
						<c:when test="${article.gender.id eq varGender.id }">
							<input type="radio" name="patient_gender" checked value="${varGender.id }"/> ${varGender.propertyValue }
						</c:when>
						<c:otherwise>
							<input type="radio" name="patient_gender" value="${varGender.id }"/> ${varGender.propertyValue }
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
 
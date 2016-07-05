<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

<script type="text/javascript">
	function fn_readonly(){
		var etcCheck = document.getElementsByName("safetyReportType")[2].checked;
		if(etcCheck){
			//alert("if");
			document.getElementById("safetyReportTypeEtc").readOnly = false;
		}else{
			//alert("else");
			document.getElementById("safetyReportTypeEtc").readOnly = true;
			document.getElementById("safetyReportTypeEtc").value="";
		}
	}
</script>
  
  
		<c:choose>
			<c:when test="${!empty sideEffectReportTypes }">
				<c:forEach var="safetyReportType" items="${sideEffectReportTypes }" varStatus="num">
					<c:choose>
						<c:when test="${sotrSideEffectReportType[num.index] eq safetyReportType.id }">
							<input type="checkbox" name="safetyReportType" checked value="${safetyReportType.id }" onclick="fn_readonly()" /> ${safetyReportType.propertyValue }
							
							<c:if test="${safetyReportType.id eq 3 }">
								<input type="text" id="safetyReportTypeEtc" name="safetyReportTypeEtc" size="80" value="${article.safety_report_type_etc }" onclick="fn_readonly()" />
							</c:if>
						<br/>	
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="safetyReportType" value="${safetyReportType.id }" onclick="fn_readonly()" /> ${safetyReportType.propertyValue }
							
							<c:if test="${safetyReportType.id eq 3 }">
								<input type="text" id="safetyReportTypeEtc" name="safetyReportTypeEtc" size="80" value="${article.safety_report_type_etc }" onclick="fn_readonly()" />
							</c:if>
							<br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
 
<script type="text/javascript">
	fn_readonly();
</script>
		
 
 
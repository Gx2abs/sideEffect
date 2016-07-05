<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

<script type="text/javascript">
	function fn_reporterReadonly(){
		var etcCheck = document.getElementsByName("reporterType")[11].checked;
		if(etcCheck){
			//alert("if");
			document.getElementById("reporterEtc").readOnly = false;
		}else{
			//alert("else");
			document.getElementById("reporterEtc").readOnly = true;
			document.getElementById("reporterEtc").value="";
		}
	}
</script>

		<c:choose>
			<c:when test="${!empty reporterTypes }">
				<c:forEach var="varReporterType" items="${reporterTypes }" varStatus="num">
					<c:choose>
						<c:when test="${varReporterType.id eq sotrReporterTypes[num.index] }">
							<c:choose>
								<c:when test="${varReporterType.propertyValue eq '기타'}">
									<input type="checkbox" name="reporterType" checked value="${varReporterType.id }"  onclick="fn_reporterReadonly()" /> ${varReporterType.propertyValue } <input type="text" id="reporterEtc" name="reporterEtc" size="80" value="${article.reporter_etc}" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="reporterType" checked value="${varReporterType.id }"  onclick="fn_reporterReadonly()" /> ${varReporterType.propertyValue }<br/>
								</c:otherwise>
							</c:choose>
							
							
							<%-- <input type="checkbox" name="reporterType" checked value="${varReporterType.id }"  onclick="fn_reporterReadonly()" /> ${varReporterType.propertyValue }<br/> --%>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${varReporterType.propertyValue eq '기타'}">
									<input type="checkbox" name="reporterType"  value="${varReporterType.id }" onclick="fn_reporterReadonly()" /> ${varReporterType.propertyValue } <input type="text" id="reporterEtc" name="reporterEtc" size="80" value="${article.reporter_etc}" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="reporterType"  value="${varReporterType.id }" onclick="fn_reporterReadonly()" /> ${varReporterType.propertyValue }<br/>
								</c:otherwise>
							</c:choose>
						
							<%-- <input type="checkbox" name="reporterType"  value="${varReporterType.id }" onclick="fn_reporterReadonly()" /> ${varReporterType.propertyValue }<br/> --%>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
								
		<%-- <input type="text" id="reporterEtc" name="reporterEtc" size="130" value="${article.reporter_etc}" /> --%>
		
		
<script type="text/javascript">
 	fn_reporterReadonly();
</script>	
 	
 
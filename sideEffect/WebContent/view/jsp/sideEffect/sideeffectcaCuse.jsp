<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

<script type="text/javascript">
	function fn_causeReadonly(){
		var etcCheck = document.getElementsByName("sideeffectCause")[3].checked;
		if(etcCheck){
			//alert("if");
			document.getElementById("causeEtc").readOnly = false;
		}else{
			//alert("else");
			document.getElementById("causeEtc").readOnly = true;
			document.getElementById("causeEtc").value="";
		}
	}
</script>


		<c:choose>
			<c:when test="${!empty sideeffectCause }">
				<c:forEach var="cause" items="${sideeffectCause }" varStatus="i">
					<c:choose>
						<c:when test="${cause.id eq sotrCauses[i.index] }">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									<input type="checkbox" name="sideeffectCause" value="${cause.id }" checked onclick="fn_causeReadonly()" /> ${cause.property_value } <input type="text" id="causeEtc" name="causeEtc" size="80" value="${article.cause_etc}" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="sideeffectCause" value="${cause.id }" checked onclick="fn_causeReadonly()" /> ${cause.property_value }<br/>
								</c:otherwise>
							</c:choose>
							<%-- <input type="checkbox" name="sideeffectCause" value="${cause.id }" checked onclick="fn_causeReadonly()" /> ${cause.property_value }<br/> --%>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									<input type="checkbox" name="sideeffectCause" value="${cause.id }" onclick="fn_causeReadonly()" /> ${cause.property_value } <input type="text" id="causeEtc" name="causeEtc" size="50" value="${article.cause_etc}" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="sideeffectCause" value="${cause.id }" onclick="fn_causeReadonly()" /> ${cause.property_value }<br/>
								</c:otherwise>
							</c:choose>
							<%-- <input type="checkbox" name="sideeffectCause" value="${cause.id }" onclick="fn_causeReadonly()" /> ${cause.property_value }<br/> --%>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose> 
  
  					
		<%-- <input type="text" id="causeEtc" name="causeEtc" size="130" value="${article.cause_etc}" /> --%>

<script type="text/javascript">
	fn_causeReadonly();
</script>
		
		
		
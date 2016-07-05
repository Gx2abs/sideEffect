<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>


<script type="text/javascript">
	function fn_followUpReadonly(){
		if(undefined !=document.getElementsByName("follow_up_action")[6]){
			var etcCheck = document.getElementsByName("follow_up_action")[6].checked;
			if(etcCheck){
				//alert("if");
				document.getElementById("followUpActionEtc").readOnly = false;
			}else{
				//alert("else");
				document.getElementById("followUpActionEtc").readOnly = true;
				document.getElementById("followUpActionEtc").value="";
			}
		}
	}
</script>



		<c:choose>
			<c:when test="${!empty reportFollowUpAction }">
			
			
				<c:forEach var="varReportFollowUpAction" items="${reportFollowUpAction }" varStatus="k">
					<c:choose>
						<c:when test="${article.obj_follow_up_action.id eq varReportFollowUpAction.id }">
							<input type="radio" name="follow_up_action" checked value="${varReportFollowUpAction.id }"  onclick="fn_followUpReadonly()" /> ${varReportFollowUpAction.property_value }
							
							<c:if test="${varReportFollowUpAction.property_value eq '기타' }">
							 	<input type="text" id="followUpActionEtc" name="followUpActionEtc" size="80" value="${article.followUpActionEtc}" onclick="fn_followUpReadonly()" />
							</c:if>
							<br>
						</c:when>
						<c:otherwise>
							<input type="radio" name="follow_up_action" value="${varReportFollowUpAction.id }" onclick="fn_followUpReadonly()" /> ${varReportFollowUpAction.property_value }
							
							<c:if test="${varReportFollowUpAction.property_value eq '기타' }">
							 	<input type="text" id="followUpActionEtc" name="followUpActionEtc" size="80" value="${article.followUpActionEtc}" onclick="fn_followUpReadonly()" />
							</c:if>
							<br>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
<script type="text/javascript">
	fn_followUpReadonly();
</script>
		
 
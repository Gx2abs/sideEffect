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
			
			
				<c:forEach var="reporterType" items="${reporterTypes }" varStatus="num">
					<c:choose>
						<c:when test="${sotrReporterTypes[num.index] eq reporterType.id }">
							<c:choose>
								<c:when test="${reporterType.propertyValue eq '기타'}">
									<input type="checkbox" id="reporterType" name="reporterType" checked value="${reporterType.id }"  onclick="fn_reporterReadonly()" /> ${reporterType.propertyValue } <input type="text" id="reporterEtc" name="reporterEtc" size="80" value="${article.reporter_etc}" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="reporterType" name="reporterType" checked value="${reporterType.id }"  onclick="fn_reporterReadonly()" /> ${reporterType.propertyValue }<br/>
								</c:otherwise>
							</c:choose>
							<%-- <input type="checkbox" id="reporterType" name="reporterType" checked value="${reporterType.id }"  onclick="fn_reporterReadonly()" /> ${reporterType.propertyValue }<br/> --%>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${reporterType.propertyValue eq '기타'}">
									<input type="checkbox" id="reporterType" name="reporterType" value="${reporterType.id }" onclick="fn_reporterReadonly()"/> ${reporterType.propertyValue } <input type="text" id="reporterEtc" name="reporterEtc" size="80" value="${article.reporter_etc}" />
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="reporterType" name="reporterType" value="${reporterType.id }" onclick="fn_reporterReadonly()"/> ${reporterType.propertyValue }<br/>
								</c:otherwise>
							</c:choose>
							<%-- <input type="checkbox" id="reporterType" name="reporterType" value="${reporterType.id }" onclick="fn_reporterReadonly()"/> ${reporterType.propertyValue }<br/> --%>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		
		
							
		<%-- <input type="text" id="reporterEtc" name="reporterEtc" size="130" value="${article.reporter_etc}" /> --%>
		
		<!-- <input type="text" style="display : none" id="etc"> --> 
		<div id="ResultBox"></div>
	
 
 <script type="text/javascript">
 	fn_reporterReadonly();

 
 
 <%/*
 $(document).ready(function(){
		
		var nodes = $("input[name=reporterType]");
		
		$.each(nodes, function(index, element){
			
			$(element).bind("click", function(){
				
				if( $(element).val() == 12){
					//alert("ggggg");
					//var ResultItem = "<input type='text' name='name[]' />";
					var ResultItem = "<textarea name='name'>";
					document.getElementById("ResultBox").innerHTML += ResultItem;
					
				}
			});
			
		});
		
		
	});
 */
 %>
 </script>
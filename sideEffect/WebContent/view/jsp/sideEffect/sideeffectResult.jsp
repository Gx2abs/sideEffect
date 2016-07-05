<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>

<script type="text/javascript">
	function fn_resultReadonly(){
		var etcCheck = document.getElementsByName("sideeffectResult")[7].checked;
		if(etcCheck){
			//alert("if");
			document.getElementById("resultEtc").readOnly = false;
		}else{
			//alert("else");
			document.getElementById("resultEtc").readOnly = true;
			document.getElementById("resultEtc").value="";
		}
	}
</script>

		<%-- <c:choose>
			<c:when test="${!empty sideeffectResult }">
				<c:forEach var="result" items="${sideeffectResult }" varStatus="i">
					<c:choose>
						<c:when test="${result.id eq sotrSideeffectResult[i.index] }">
							<input type="checkbox" name="sideeffectResult" value="${result.id }" checked /> ${result.property_value }<br/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="sideeffectResult" value="${result.id }" /> ${result.property_value }<br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose> --%>
	
<script type="text/javascript">
	 function display1(gubun){
		 $('#sideeffectResultDiv2').attr("style","display:");
		 $('#sideeffectResultDiv3').attr("style","display:");
		 $('#sideeffectResultDiv4').attr("style","display:");
		 $('#sideeffectResultDiv5').attr("style","display:");
		 
		 
		 
 	}
</script>
	
	
		<c:choose>
			<c:when test="${!empty sideeffectResult }">
				<c:forEach var="result" items="${sideeffectResult }" varStatus="i">
				 	<%-- <c:choose>
				 		<c:when test="${result.id == 2 }">
				 			<div id ="sideeffectResultDiv2"  style="display:none">
				 				<c:choose>
				 					<c:when test="${result.id eq sotrSideeffectResult[i.index] }">
				 						
				 						<script type="text/javascript">
				 							display1('<c:out value="${result.id}"/>');
										</script>
				 					
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:when>
									<c:otherwise>
										
										<c:if test="${param.action eq 'update'}">
											<script type="text/javascript">
					 							display1('<c:out value="${result.id}"/>');
											</script>
										</c:if>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:otherwise>
								</c:choose>
							</div>
						</c:when>
						<c:when test="${result.id == 3 }">
				 			<div id ="sideeffectResultDiv3"  style="display:none">
								<c:choose>
				 					<c:when test="${result.id eq sotrSideeffectResult[i.index] }">
				 					
				 						<script type="text/javascript">
				 							display1('<c:out value="${result.id}"/>');
										</script>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:when>
									<c:otherwise>
										<c:if test="${param.action eq 'update'}">
											<script type="text/javascript">
					 							display1('<c:out value="${result.id}"/>');
											</script>
										</c:if>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:otherwise>
								</c:choose>
							</div>
						</c:when>
						<c:when test="${result.id == 4 }">
				 			<div id ="sideeffectResultDiv4"  style="display:none">
								<c:choose>
				 					<c:when test="${result.id eq sotrSideeffectResult[i.index] }">
				 					
				 						<script type="text/javascript">
				 							display1('<c:out value="${result.id}"/>');
										</script>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:when>
									<c:otherwise>
										<c:if test="${param.action eq 'update'}">
											<script type="text/javascript">
					 							display1('<c:out value="${result.id}"/>');
											</script>
										</c:if>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:otherwise>
								</c:choose>
							</div>
						</c:when>
						<c:when test="${result.id == 5 }">
				 			<div id ="sideeffectResultDiv5"  style="display:none">
								<c:choose>
				 					<c:when test="${result.id eq sotrSideeffectResult[i.index] }">
				 					
				 						<script type="text/javascript">
				 							display1('<c:out value="${result.id}"/>');
										</script>
				 					
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:when>
									<c:otherwise>
										<c:if test="${param.action eq 'update'}">
											<script type="text/javascript">
					 							display1('<c:out value="${result.id}"/>');
											</script>
										</c:if>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
									</c:otherwise>
								</c:choose>
							</div>
						</c:when>
						<c:otherwise> --%>
							<c:choose>
								<c:when test="${result.id eq sotrSideeffectResult[i.index] }">
									<c:choose>
										<c:when test="${result.propertyValue eq '기타'}">
											<input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.propertyValue } <input type="text" id="resultEtc" name="resultEtc" size="80" value="${article.result_etc}" />
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
										</c:otherwise>
									</c:choose>
									<%-- <input type="checkbox" name="sideeffectResult" value="${result.id }" checked onclick="fn_resultReadonly()" /> ${result.property_value }<br/> --%>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${result.propertyValue eq '기타'}">
											<input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.propertyValue } <input type="text" id="resultEtc" name="resultEtc" size="50" value="${article.result_etc}" />
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.propertyValue }<br/>
										</c:otherwise>
									</c:choose>
									<%-- <input type="checkbox" name="sideeffectResult" value="${result.id }" onclick="fn_resultReadonly()" /> ${result.property_value }<br/> --%>
									
								</c:otherwise>
							</c:choose>
						<%-- </c:otherwise>
					</c:choose> --%>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>


<%-- <input type="text" id="resultEtc" name="resultEtc" size="130" value="${article.result_etc}" /> --%>
 
 <script type="text/javascript">

 $(document).ready(function(){
	
	 var nodes = $("input[name=sideeffectResult]");
		
		$.each(nodes, function(index, element){
			
			$(element).bind("click", function(){
				
				if( $(element).val() == 1){
					//alert("ggggg");
					 $('#sideeffectResultDiv2').attr("style","display:");
					 $('#sideeffectResultDiv3').attr("style","display:");
					 $('#sideeffectResultDiv4').attr("style","display:");
					 $('#sideeffectResultDiv5').attr("style","display:");
				}
			});
			
		});
		
		
	});


 
 fn_resultReadonly();
 

 
 </script>
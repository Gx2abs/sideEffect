<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>
<%@ include file="/view/config/jqUI.jsp" %>
  <script>
  $(function() {
    $( ".datepicker" ).datepicker();
  });
  
  </script>
  
  <!-- 
  <input type="hidden" name="reportDate" value="0" />
   -->
   
   <input type="hidden" name="reportType" value="0"/>
   <input type="hidden" name="arrReportDate" value="0" />
		<c:choose>
			<c:when test="${!empty reportTypes }">
				<c:forEach var="varReportType" items="${reportTypes }" varStatus="i">
					<c:choose>
						<c:when test="${varReportType.id eq sotrsafetyReportTypeDate[i.index] }">
							<input type="checkbox" name="reportType" checked="checked" value="${varReportType.id }"  /> ${varReportType.propertyValue }<input type="text" class="datepicker" name="arrReportDate" id="arrReportDate_${i.index }" value="${fn:substring(sotrsafetyReportDate[i.index],5,7)}/${fn:substring(sotrsafetyReportDate[i.index],8,10)}/${fn:substring(sotrsafetyReportDate[i.index],0,4)}" /><br/>
						</c:when>
						<c:otherwise>	
							<input type="checkbox" name="reportType" value="${varReportType.id }" /> ${varReportType.propertyValue }<input type="text" class="datepicker" name="arrReportDate" id="arrReportDate_${i.index }" /><br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
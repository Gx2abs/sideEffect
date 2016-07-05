<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>
<%@ include file="/view/config/jqUI.jsp" %>
  <script>
  $(function() {
    $( ".datepicker" ).datepicker();
  });

  function doGetSideeffectResult(dateText){
	  $(".sideeffectResult").html("");
	  $.ajax({
			url : "sideEffectReport.do?action=getSideEffectResult",
			dataType : "html",
			type : "post",
			data : {"reportDate" : dateText},
			dataType : "json",
			success : function(result) {
				if(result.length>0){
					var html = '';
					for(var i=0;i<result.length;i++){
						if(result[i][2]==2){
							html += '&nbsp;&nbsp;';
						}
						html += '<input type="radio" name="sideeffectResult" value="'+result[i][0]+'"';
						
						if(result[i+1]&&result[i][2]==1&&result[i+1][2]==2){
							html += 'disabled="disabled"';
						}
						html +='/>'+result[i][1]+'<br>';
											}
					$(".sideeffectResult").html(html);
				}else{
					//alert("최초 보고 일자에 해당하는 위해 심각도 코드가 존재하지 않습니다.");
				}
			},
			error : function(request, status, error) {
				alert("error"+error);
			},
			complete : function() {
			}
		});
  }
  
  </script>
  <!-- 
  <input type="hidden" name="reportDate" value="0" />
   -->
   
   <input type="hidden" name="reportType" value="0"/>
   <input type="hidden" name="arrReportDate" value="0"/>
		<c:choose>
			<c:when test="${!empty reportTypes }">
				<c:forEach var="varReportType" items="${reportTypes }" varStatus="i">
					<c:choose>
						<c:when test="${varReportType.id eq sotrReporterTypeDate[i.index] }">
							<input type="checkbox" name="reportType" checked="checked" value="${varReportType.id }"  /> ${varReportType.propertyValue }<input type="text" class="datepicker" name="arrReportDate" id="arrReportDate_${i.index}" value="${fn:substring(sotrReporterDate[i.index],5,7)}/${fn:substring(sotrReporterDate[i.index],8,10)}/${fn:substring(sotrReporterDate[i.index],0,4)}" /><br/>
						</c:when>
						<c:otherwise>	
							<input type="checkbox" name="reportType" value="${varReportType.id }" /> ${varReportType.propertyValue }<input type="text" class="datepicker" name="arrReportDate" id="arrReportDate_${i.index}" /><br/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
			no data
			</c:otherwise>
		</c:choose>
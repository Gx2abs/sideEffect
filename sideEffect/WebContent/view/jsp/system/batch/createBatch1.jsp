<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="createBatchJob"  method="post" action="system.do" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="createBatchJob"/>
	 	<table class=" view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">Excel Batch Job</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >메뉴</th>
					<td class="tdBody">
						<select name="tableTypeId" id="tableTypeId">
							<c:forEach var="table" items="${requestScope.tables }">
								<option value="${table.id }">${table.propertyValue }</option>
							</c:forEach>
						</select>
						<input type="button" value="Download Format" name="downloadFormat" id="downloadFormat"/>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >파일</th>
					<td class="tdBody">
						<input type="file" name="file"/>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >적용</th>
					<td class="tdBody">
						<input type="checkbox" name="applicationDate"/>적용 <input type="text" name="applicationDateCalendar" readonly="readonly"/>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick=""/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	
 		$(document).ready(function(){
 			bindDownloadFormat();
 		});
 	
 	
 		function bindDownloadFormat(){
 			$("#downloadFormat").bind("click", function(){
 				downloadFormat();
 			});
 		}
 	
 		function downloadFormat(){
 			var formatId =$("select[name=tableTypeId] option:selected").val();
 			var url = "system.do?action=downloadFormat&formatId="+formatId;
 			location.href = url;
 			return;
 		}
 	
 		 function create(){
 			 $("form[name=createBatchJob]").submit();
 		 }
 		 
 		 $(document).ready(function(){
 			 
 			$("input[name=applicationDateCalendar]").datepicker();
 			 $("input[name=applicationDateCalendar]").datepicker("option", "dateFormat", "yy-mm-dd");
 			 
 			 bindApplicationDate();
 			 
 		 });
 		 
 		 function bindApplicationDate(){
 			 
 			 $("input[name=applicationDate]").bind("change", function(){
 				 alert('hi');
 			 });
 			 
 		 }
 		 
 	</script>
 
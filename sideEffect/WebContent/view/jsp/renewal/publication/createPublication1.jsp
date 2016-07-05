<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/popup.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/SE2.3.8.O10836/js/HuskyEZCreator.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="patientConditionCreateForm1"  method="POST" action="publication.do"
 	enctype="multipart/form-data">
 		<input type="text" name="action" value="createPublication"/><br/>
 		
 		
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="5">공통 코드 관리 - 환자문제 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">
					<select name="publicationType">
							<c:choose>
								<c:when test="${!empty requestScope.publicationType  }">
									<c:forEach var="Type" items="${publicationType }" >
										<option value="${Type.id }">${Type.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
			
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >제목 </th>
					<td colspan="3"><input type="text" name="title"/>
						</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">내용</th>
					<td class="tdBody"  colspan="3"> 
					<textarea id="body" name="body" style="width:98%;height:55px;padding:4px;" style="display: none"></textarea>
                                   <script type="text/javascript">
                                   		
                                   		$(document).ready(function(){
                                   		
                                   			nhn.husky.EZCreator.createInIFrame({
                                       		    oAppRef: oEditors,
                                       		    elPlaceHolder: "body",
                                       		    sSkinURI: "view/js/SE2.3.8.O10836/SmartEditor2Skin.html",
                                       		    fCreator: "createSEditor2"
                                       		});
                                   			
                                   		});
                                   		
										
									</script>
					
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">첨부파일</th>
					<td class="tdBody"  colspan="3">
						<input type="button" value="파일추가" id="addFile"/>
						<div id="fileDiv1"></div>
					</td>
				</tr>
				
			</tbody>
 	</table>
	 	</form>
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	
 	var oEditors = [];
 	
 		function create(){
 			oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
 			$("form[name=patientConditionCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 			$("#lastModified").datepicker();
 			$("#activeFrom").datepicker();
 			$("#lastModified").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 			bindAddAttachment()
 		}
 		
 		function bindAddAttachment(){
			$("#addFile").bind("click", function(){
				addAttachment();
			});
		}
		
		function addAttachment(){

			var file = document.createElement("input");
			var br = document.createElement("br");
			$(file).attr("type", "file");
			$(file).attr("name", "attachment");
			
			$("#fileDiv1").append(file);
			$("#fileDiv1").append(br);
			
		}
 		
 	</script>
 
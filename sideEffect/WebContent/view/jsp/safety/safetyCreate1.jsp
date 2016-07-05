<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 
<script type="text/javascript" src="cheditor/cheditor.js"></script>
<script type="text/javascript">
 
 var myeditor1 = new cheditor("myeditor1");
 var myeditor2 = new cheditor("myeditor2");
 var myeditor3 = new cheditor("myeditor3");
 var myeditor4 = new cheditor("myeditor4");
 //alert("myeditor1  " + myeditor1);
 
 function createEditor(){
	 myeditor1.outputBodyHTML();
	 myeditor2.outputBodyHTML();
	 myeditor3.outputBodyHTML();
	 myeditor4.outputBodyHTML();
 }
 
 function submit2(){
	 var frm1 = document.safetyCreateForm1;
	 //if(frm1.check1.value == 0){
	 //alert("문서번호 중복검사를  해주세요.");
	 //return;
 	//}
	 createEditor();
	 //var reportLength = frm1.reportType.length;
	 
	 //for(var i=0; i<reportLength; i++){
		 //if(frm1.reportType[i].checked == true){
			 //alert("선택  " + frm1.reportType[i].value);
			 //alert("  " + frm1.arrReportDate[i].value);
			// frm1.reportDate.value = frm1.arrReportDate[i].value;
			 
		 //}
	 //}
	 
	 
	//frm1.action="safetyReport.do?action=create";
	 frm1.submit();
 }
 
 function fc_check1(){
	 var frm1 = document.safetyCreateForm1;
	 	 frm1.check1.value=0;
 }
 function duplicate_chk(){
	 var frm1 = document.safetyCreateForm1;
	 var document_number = frm1.document_number.value;
   	 var xmlHttpRequest = new XMLHttpRequest();
 			
	 var method = "POST";
	 var url = "properties.do?action=documentNumberCheck&document_number="+document_number;
	 var async = true;
			
	 xmlHttpRequest.open(method, url, async);
	 //xmlHttpRequest.setRequestHeader("enctype","multipart/form-data");
	 xmlHttpRequest.send();
			
	 xmlHttpRequest.onreadystatechange = function(){
				
				if ( xmlHttpRequest.status ==200 && xmlHttpRequest.readyState ==4){
					//alert(xmlHttpRequest.responseText);
					var result = xmlHttpRequest.responsetext;
								 //alert(xmlHttpRequest.responseText);	
					
					if(xmlHttpRequest.responseText > 0){
						document.getElementById("message").innerHTML = "<font color='#ff0000'>이미 사용중인 문서번호 입니다.</font>";
						frm1.check1.value=0;
					}else{
						document.getElementById("message").innerHTML = "<font color='#ff0000'>사용 가능한 문서번호 입니다.</font>";
						frm1.check1.value=1;
					}
					
				} 
			};
			
	 	 
 }
</script>
 
 
 	<div style="width: 100%; height: 99%;">
 	<img src="${titleImg }" />
 	<form name="safetyCreateForm1" enctype="multipart/form-data" method="post" action="safetyReport.do">
 		<input type="hidden" name="action" value="create"/>
 		<input type="hidden" name="check1" value="0"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="21%" />
				<col width="30%" />
				<col width="20%" />
				<col width="29%" />
			</colgroup>
				<thead>
					<tr>
						<th colspan="4" class="textCenter">보고의종류</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th class="tdHead">문서번호</th>
						<td colspan="3"><input type="text" name="document_number" onkeypress="fc_check1();" />
							<!--  <input type="button" value="중복체크" onclick="duplicate_chk()"/>-->
							<span id="message"></span>
						</td>
					</tr>
					<tr>
						<th class="tdHead">구분</th>
						<td colspan="3">
							<%@include file="/view/jsp/safety/countryReportedIn.jsp" %>
						</td>
					</tr>
					<tr>
						<th class="tdHead">보고구분/보고일자</th>
						<td class="tdBody">
						<%@include file="/view/jsp/safety/reportType.jsp" %>
						
						</td>
						<th class="tdHead"> 식약처 보고일자</th>
						<!-- <td class="tdBody"><input type="text" class="datepicker" name="mreport_date" id="" /></td> -->
						<td class="tdBody"><input type="text"  name="mreport_date" id="mreport_date" /></td>
					</tr>
					<tr>
						<th class="tdHead">보고자 유형</th>
						<td colspan="3">
							<%@include file="/view/jsp/safety/reporterType.jsp" %>
							
						</td>
					</tr>
				</tbody>
	 	</table>
	 	
	 	<br/>
	 	
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="11%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="29%" />
			</colgroup>
				<thead>
					<tr>
						<th colspan="5" class="textCenter">보고자 정보</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th rowspan="4">보고자정보</th>
						<th class="tdHead">상호명</th>
						<td class="tdBody"><input type="text" value="" name="company_name" /></td>
						<th class="tdHead">대표자 성명</th>
						<td class="tdBody"><input type="text" value="" name="representatives" /></td>
					</tr>
					<tr>
						<th class="tdHead">담당자명</th>
						<td class="tdBody"><input type="text" value="" name="manager"/></td>
						<th class="tdHead"> 전화</th>
						<td class="tdBody"><input type="text" value="" name="report_tel"/></td>
					</tr>
					<tr>
						<th class="tdHead">FAX</th>
						<td class="tdBody"><input type="text" value="" name="fax"/></td>
						<th class="tdHead">EMAIL</th>
						<td class="tdBody"><input type="text" value="" name="email"/></td>
					</tr>
					<tr>
						<th class="tdHead">주소</th>
						<td colspan="3">
							<input type="text" value="" size="70" name="report_address"/>
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="addrSearch1('report_address')" /> 
						</td>
					</tr>
				</tbody>
	 	</table>
	 	
	 	<br/>
	 	
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="11%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="29%" />
			</colgroup>
	
				<thead>
					<tr>
						<th colspan="5" class="textCenter">의료기기 정보</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th class="tdHead" colspan="2">의료기기정보</th>
						<td>
							<input onclick="pop();" name="meb_item_manuf_name" type="text" readonly value="${article.meb_item.manuf_name }" class="buttonised"/>
							<input type="hidden" name="meb_item_manuf_id" readonly />
							<input type="hidden" name="mebTypeInfoSelect" value="0" />
						</td>
						
						<th class="tdHead">제조국/제조원</th>
						<td class="tdBody"><input type="text" value="" onclick="pop2();" readonly class="buttonised" name="countryManufactured" />
						<input type="hidden" name="country_manufactured_id" />
						 /<input type="text" name="manufacturer" ></td>
					</tr>
					 
					<tr>
						<th class="tdHead" colspan="2">제조번호</th>
						<td class="tdBody">
							<input type="text" size="100" name="serial_number" />
						</td>
					</tr>
				</tbody>
	 	</table>
	 	
	 	<br/>
	 	
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="11%" />
				<col width="10%" />
				<col width="79%" />
			</colgroup>
	
				<thead>
					<tr>
						<th colspan="3" class="textCenter">안전성 정보</th>
					</tr>
				</thead>
				<tbody>
			 		<tr>
						<th class="tdHead" colspan="2">안전성 정보 원인<br>&nbsp;및 내용</th>
						<td class="tdBody" >
							<textarea name="safety_cause_content" style="width:100%; height:220;" id="safety_cause_content"></textarea>
								<script type="text/javascript" >
									myeditor1.config.editorHeight = '100px';            // 에디터 세로폭입니다.
									myeditor1.config.editorWidth = '100%';              // 에디터 가로폭입니다.
									myeditor1.inputForm = 'safety_cause_content';         // textarea의 ID 이름입니다.
									myeditor1.run();                                    // 에디터를 실행합니다.
								</script>
						</td>
					</tr>
					<tr>
						<th class="tdHead" rowspan="3">안전성 문제<br>&nbsp;코드</th>
						<th class="tdHead" >환자문제<br>&nbsp;코드</th>
						<td class="tdBody" >
						
							<input type="hidden" value="0" name="patient_code_id"/>
							<input type="text" value="" size="50" name="patient_code_user_view"/>
							
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="selectPatient()" />
						</td>
					</tr>
					<tr>
						<th class="tdHead" >의료기기문제<br>&nbsp;코드</th>
						<td class="tdBody" >
						
							<input type="hidden" value="0" name="medical_code_id"/>
							<input type="text" value="" size="50" name="medical_code_user_view"/>
							
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="selectMedical()" />
						</td>
					</tr>
					<tr>
						<th class="tdHead" >구성요소<br>&nbsp;코드</th>
						<td class="tdBody" >
							
							<input type="hidden" value="0" name="component_id"/>
							<input type="text" value="" size="50" name="component_id_user_view"/>
							
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="selectComponent()" />
						</td>
					</tr>
					<tr>
						<th class="tdHead" colspan="2">업소의 후속조치</th>
						<td class="tdBody" >
							<textarea name="followup" style="width:100%; height:220;" id="followup"></textarea>
								<script type="text/javascript" >
									myeditor2.config.editorHeight = '100px';            // 에디터 세로폭입니다.
									myeditor2.config.editorWidth = '100%';              // 에디터 가로폭입니다.
									myeditor2.inputForm = 'followup';         // textarea의 ID 이름입니다.
									myeditor2.run();                                    // 에디터를 실행합니다.
								</script>
						</td>
					</tr>
					<tr>
						<th colspan="2">사후조치 결과</th>
						<td >
							<%@include file="/view/jsp\sideEffect\followUpAction.jsp" %>
						</td>
					</tr>
					<tr>
					<th class="tdHead" colspan="2">안전성정보보고 유형</th>
						<td >
							<%@include file="/view/jsp/safety/sideEffectReportType.jsp" %>
						</td>
					</tr>
					<tr>
						<th class="tdHead" colspan="2">진행상황</th>
						<td >
							<%@include file="/view/jsp/safety/reporterStatus.jsp" %>
						</td>
					</tr>
					<tr>
						<th class="tdHead" colspan="2">식약처 후속조치</th>
						<td colspan="3">
							 <textarea name="kfda_followup" style="width:100%; height:220;" id="kfda_followup"></textarea>
								<script type="text/javascript" >
								myeditor4.config.editorHeight = '100px';            // 에디터 세로폭입니다.
								myeditor4.config.editorWidth = '100%';              // 에디터 가로폭입니다.
								myeditor4.inputForm = 'kfda_followup';                 // textarea의 ID 이름입니다.
								myeditor4.run();                                    // 에디터를 실행합니다.
								</script> 
						</td>	
					</tr>
					<tr>
						<th class="tdHead" colspan="2">기타사항</th>
						<td >
							<textarea name="extra_info" style="width:100%; height:220;" id="extra_info"></textarea>
								<script type="text/javascript" >
									myeditor3.config.editorHeight = '100px';            // 에디터 세로폭입니다.
									myeditor3.config.editorWidth = '100%';              // 에디터 가로폭입니다.
									myeditor3.inputForm = 'extra_info';         // textarea의 ID 이름입니다.
									myeditor3.run();                                    // 에디터를 실행합니다.
								</script>
						</td>
					</tr>
				<tr>
					<th class="tdHead" colspan="2">등록일</th>
					<td >
						<input type="text" name="first_modified" class="datepicker" id="first_modified" />
					</td>
				</tr>
				</tbody>
	 	</table>
	 	
	 	<br/>
	 	
	 	<table class="bordered fitToParent view_table" >
	
				<thead>
					<tr>
						<th class="textCenter">첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<%@include file="/view/jsp/common/multiFileUploader.jsp" %>
						</td>
					</tr>
					
				</tbody>
	 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="submit2()"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" />
	 	</div>
	 	
 	</div>
 
 
 <script type="text/javascript">
 
 
 $(document).ready(function(){
	 
	 $("#mreport_date").datepicker();
	 $("#mreport_date").datepicker("option", "dateFormat", "yy-mm-dd");
	 
	 
 });
 
 
 
 function pop(){
		
		var url ="meb_item.do?action=getManufImportEntpNameList";
		var properties ="width=500,height=700";
		//window.open(url,properties, true);
		window.open(url, '', properties, 'scrollbars=yes');
		
	}
	
	function pop2(){
		//alert("국가코드(?)");
		var url ="properties.do?action=listCountryPop";
		var properties ="width=500,height=700";
		window.open(url, '', properties, 'scrollbars=yes');
		
	}
	
 	
 	$(document).ready(function(){
 		initPage();
 		
 		$("#submit1").bind("click", function(){
 			var xmlHttpRequest = new XMLHttpRequest();
 			
 			var form = $("#safetyCreateForm1");
 			var formData = new FormData(form);
 			
 			var method = "POST";
 			var url = "safetyReport.do";
 			var async = true;
 			
 			xmlHttpRequest.open(method, url, async);
 			//xmlHttpRequest.setRequestHeader("enctype","multipart/form-data");
 			xmlHttpRequest.send("?action=create");
 			
 			xmlHttpRequest.onreadystatechange = function(){
 				
 				if ( xmlHttpRequest.status ==200 && xmlHttpRequest.readyState ==4){
 					alert("file upload complete");
 					
 					alert ( xmlHttpRequest.responseText );
 					
 				} 
 				
 			};
 			
 		});
 		
 		
 		
 	
 		
 		
 		
 		
 		
 		$("#cancel1").bind("click", function(){
 			location.href="safetyReport.do?action=list";
 		});
 		
 		
 		
 		
 		
 		
 		
 	});
 	
	function selectPatient(){
 		var url ="sideEffectReport.do?action=selectPatient";
 		var properties ="width=820,height=720";
 		window.open(url, '', properties, 'scrollbars=yes');
 	}
 	function selectMedical(){
 		var url ="sideEffectReport.do?action=selectMedical";
 		var properties ="width=820,height=720";
 		window.open(url, '', properties, 'scrollbars=yes');
 	}
 	function selectComponent(){
 		var url ="sideEffectReport.do?action=selectComponent";
 		var properties ="width=820,height=720";
 		window.open(url, '', properties, 'scrollbars=yes');
 
 	}
 	
 	
 	
	function initPage(){
		
 		
		$("#arrReportDate_0").datepicker();
		$("#arrReportDate_0").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#arrReportDate_1").datepicker();
		$("#arrReportDate_1").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#arrReportDate_2").datepicker();
		$("#arrReportDate_2").datepicker("option", "dateFormat", "yy-mm-dd");
		
		$("#first_modified").datepicker();
		$("#first_modified").datepicker("option", "dateFormat", "yy-mm-dd");
		
	}
	
	function addrSearch1(inputName){
		//alert("inputName  " + inputName);
			var url ="address.do?action=pop&inputName="+inputName;
	 		var properties ="width=500,height=700";
	 		//window.open(url,properties, true);
	 		window.open(url, '', properties, 'scrollbars=yes');
	 		
		}
		
	
 	
 </script>
 
 
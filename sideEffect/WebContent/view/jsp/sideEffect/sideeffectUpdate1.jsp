<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config2.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 
<%
	String hyhtest = (String) request.getAttribute("hyhtest");
	hyhtest = Function.fnTagOn(hyhtest);
%> 
 
 
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<script type="text/javascript" src="cheditor/cheditor.js"></script>

<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

<script type="text/javascript">
 
 var myeditor1 = new cheditor("myeditor1");
 var myeditor2 = new cheditor("myeditor2");
 var myeditor3 = new cheditor("myeditor3");
 var myeditor4 = new cheditor("myeditor4");
 var myeditor5 = new cheditor("myeditor5");
 
 function submit1(){
		 var frm1 = document.safetyCreateForm1;
		 myeditor1.outputBodyHTML();
		 myeditor2.outputBodyHTML();
		 myeditor3.outputBodyHTML();
		 myeditor4.outputBodyHTML();
		 myeditor5.outputBodyHTML();
		// var reportLength = frm1.reportType.length;
		 
		 //for(var i=0; i<reportLength; i++){
			 //if(frm1.reportType[i].checked == true){
				 //alert("선택  " + frm1.reportType[i].value);
				 //alert("  " + frm1.arrReportDate[i].value);
				//frm1.reportDate.value = frm1.arrReportDate[i].value;
				 
			 //}
		//}
		// frm1.action="safetyReport.do?action=create";
		
		var reportLength = frm1.reportType.length;
		
		for(var i=0; i<reportLength; i++){
			if(frm1.reportType[i].checked == false){
				frm1.arrReportDate[i].value="";
			}
			
		}
	
/////////////////////////////////////////환자문제코드////////////////////////////////////////////////////////
var nullChk = frm1.patient_code_size.value;
var patient_code_del = frm1.patient_code_del.value;
if(nullChk >= 1){
	if(nullChk == 1){
		var patient_code_id2_length = 1;
	}else{
		var patient_code_id2_length = frm1.patient_code_id2.length;
	}
		var varPatient_code_id = frm1.patient_code_id.value.split(",");
		var patient_code_id_length = varPatient_code_id.length;
		
		if(patient_code_id2_length != 3){
			if((patient_code_id2_length + patient_code_id_length) > 3){
				alert("환자문제코드는 3개 이상 입력 하실 수 없습니다.");
				frm1.patient_code_id.value=0;
				frm1.medical_code_id.value=0;
				frm1.component_id.value=0;
				return;
			}
		}

		for(var i=0; i<patient_code_id2_length; i++){
			for(var k=0; k<patient_code_id_length; k++){
				if(nullChk == 1){
					if(patient_code_del == 0){
						if(frm1.patient_code_id2.value == varPatient_code_id[k]){
							alert("환자문제코드에 중복되는 코드가 있습니다.");
							frm1.patient_code_id.value=0;
							frm1.medical_code_id.value=0;
							frm1.component_id.value=0;
							return;
						}
					}else{
						if(frm1.patient_code_id2[i].value == varPatient_code_id[k]){
							alert("환자문제코드에 중복되는 코드가 있습니다.");
							frm1.patient_code_id.value=0;
							frm1.medical_code_id.value=0;
							frm1.component_id.value=0;
							return;
						}	
					}
				}else{
					if(frm1.patient_code_id2[i].value == varPatient_code_id[k]){
						alert("환자문제코드에 중복되는 코드가 있습니다.");
						frm1.patient_code_id.value=0;
						frm1.medical_code_id.value=0;
						frm1.component_id.value=0;
						return;
					}
				}
			}
		}
		
		var varPatient_code_id2 = "";
		
		for(var i=0; i<patient_code_id2_length; i++){
			if(i == (patient_code_id2_length -1)){
				if(nullChk == 1){
					if(patient_code_del == 1){
						varPatient_code_id2 += frm1.patient_code_id2[i].value;
					}else{
						varPatient_code_id2 += frm1.patient_code_id2.value;
					}
				}else{
					varPatient_code_id2 += frm1.patient_code_id2[i].value;
				}
			}else{
				varPatient_code_id2 += frm1.patient_code_id2[i].value+ ",";
			}
			
		}			
		
		if(frm1.patient_code_id.value == 0){
			frm1.patient_code_id.value = varPatient_code_id2;
		}else{
			frm1.patient_code_id.value = varPatient_code_id2+","+frm1.patient_code_id.value;
		}
}		
	
/////////////////////////////////////////의료기기문제코드////////////////////////////////////////////////////////
var nullChk = frm1.medical_code_size.value;
var medical_code_del = frm1.medical_code_del.value;
if(nullChk >= 1){
	if(nullChk == 1){
		var medical_code_id2_length = 1;
	}else{
		var medical_code_id2_length = frm1.medical_code_id2.length;
	}
		var varMedical_code_id = frm1.medical_code_id.value.split(",");
		var medical_code_id_length = varMedical_code_id.length;
		
		if(medical_code_id2_length != 3){
			if((medical_code_id2_length + medical_code_id_length) > 3){
				alert("의료기기문제코드는 3개 이상 입력 하실 수 없습니다.");
				frm1.patient_code_id.value=0;
				frm1.medical_code_id.value=0;
				frm1.component_id.value=0;
				return;
			}
		}

		for(var i=0; i<medical_code_id2_length; i++){
			for(var k=0; k<medical_code_id_length; k++){
				if(nullChk == 1){
					if(medical_code_del == 0){
						if(frm1.medical_code_id2.value == varMedical_code_id[k]){
							alert("의료기기문제코드에 중복되는 코드가 있습니다.");
							frm1.patient_code_id.value=0;
							frm1.medical_code_id.value=0;
							frm1.component_id.value=0;
							return;
						}
					}else{
						if(frm1.medical_code_id2[i].value == varMedical_code_id[k]){
							alert("의료기기문제코드에 중복되는 코드가 있습니다.");
							frm1.patient_code_id.value=0;
							frm1.medical_code_id.value=0;
							frm1.component_id.value=0;
							return;
						}	
					}
				}else{
					if(frm1.medical_code_id2[i].value == varMedical_code_id[k]){
						alert("의료기기문제코드에 중복되는 코드가 있습니다.");
						frm1.patient_code_id.value=0;
						frm1.medical_code_id.value=0;
						frm1.component_id.value=0;
						return;
					}
				}
			}
		}
		
		var varMedical_code_id2 = "";
		
		for(var i=0; i<medical_code_id2_length; i++){
			if(i == (medical_code_id2_length -1)){
				if(nullChk == 1){
					if(medical_code_del == 1){
						varMedical_code_id2 += frm1.medical_code_id2[i].value;
					}else{
						varMedical_code_id2 += frm1.medical_code_id2.value;
					}
				}else{
					varMedical_code_id2 += frm1.medical_code_id2[i].value;
				}
			}else{
				varMedical_code_id2 += frm1.medical_code_id2[i].value+ ",";
			}
			
		}			
		
		if(frm1.medical_code_id.value == 0){
			frm1.medical_code_id.value = varMedical_code_id2;
		}else{
			frm1.medical_code_id.value = varMedical_code_id2+","+frm1.medical_code_id.value;
		}
}		
	
		
/////////////////////////////////////////구성요소코드////////////////////////////////////////////////////////
var nullChk = frm1.component_code_size.value;
var component_code_del = frm1.component_code_del.value;
if(nullChk >= 1){
	if(nullChk == 1){
		var component_code_id2_length = 1;
	}else{
		var component_code_id2_length = frm1.component_id2.length;
	}

		var varComponent_code_id = frm1.component_id.value.split(",");
		var component_code_id_length = varComponent_code_id.length;
		
		if(component_code_id2_length != 3){
			if((component_code_id2_length + component_code_id_length) > 3){
				alert("구성요소문제코드는 3개 이상 입력 하실 수 없습니다.");
				frm1.patient_code_id.value=0;
				frm1.medical_code_id.value=0;
				frm1.component_id.value=0;
				return;
			}
		}

		for(var i=0; i<component_code_id2_length; i++){
			for(var k=0; k<component_code_id_length; k++){
				if(nullChk == 1){
					if(component_code_del == 0){
						if(frm1.component_id2.value == varComponent_code_id[k]){
							alert("구성요소문제코드에 중복되는 코드가 있습니다.");
							frm1.patient_code_id.value=0;
							frm1.medical_code_id.value=0;
							frm1.component_id.value=0;
							return;
						}
					}else{
						if(frm1.component_id2[i].value == varComponent_code_id[k]){
							alert("구성요소문제코드에 중복되는 코드가 있습니다.");
							frm1.patient_code_id.value=0;
							frm1.medical_code_id.value=0;
							frm1.component_id.value=0;
							return;
						}	
					}
				}else{
					if(frm1.component_id2[i].value == varComponent_code_id[k]){
						alert("구성요소문제코드에 중복되는 코드가 있습니다.");
						frm1.patient_code_id.value=0;
						frm1.medical_code_id.value=0;
						frm1.component_id.value=0;
						return;
					}
				}
			}
		}
		
		var varComponent_code_id2 = "";
		
		for(var i=0; i<component_code_id2_length; i++){
			if(i == (component_code_id2_length -1)){
				if(nullChk == 1){
					if(component_code_del == 1){
						varComponent_code_id2 += frm1.component_id2[i].value;
					}else{
						varComponent_code_id2 += frm1.component_id2.value;
					}
				}else{
					varComponent_code_id2 += frm1.component_id2[i].value;
				}
			}else{
				varComponent_code_id2 += frm1.component_id2[i].value+ ",";
			}
		
		}			
		
		if(frm1.component_id.value == 0){
			frm1.component_id.value = varComponent_code_id2;
		}else{
			frm1.component_id.value = varComponent_code_id2+","+frm1.component_id.value;
		}
}		
	

	var mebTypeInfoItemSeqLength = frm1.mebTypeInfoItemSeq.length;
	
	var arr = new Array();
	
	if(mebTypeInfoItemSeqLength == undefined){
		if(frm1.mebTypeInfoSelect.value == 0 && frm1.mebTypeInfoItemSeq.value > 0){
			arr[0] = -1;
			arr[1] = frm1.mebTypeInfoItemSeq.value;
			frm1.mebTypeInfoSelect.value = arr;
		}
	}
	
	for(var k=0; k<mebTypeInfoItemSeqLength; k++){
		arr[k] = frm1.mebTypeInfoItemSeq[k].value;
	}

	if(mebTypeInfoItemSeqLength != undefined && frm1.mebTypeInfoSelect.value == 0){
		frm1.mebTypeInfoSelect.value = arr;
	}
	
	frm1.submit();
}
 
function codeDelete(report_id, code_id, gubun){
	//alert("report_id  "+report_id+"  code_id  "+code_id+"  gubun  "+gubun);
	
	 var xmlHttpRequest = new XMLHttpRequest();
		
	 var method = "POST";
	 var variable = "&report_id="+report_id+"&code_id="+code_id+"&gubun="+gubun+"&gubun2=1";
	 var url = "properties.do?action=codeDelete"+variable;
	 var async = true;
	
	 
	 
	 xmlHttpRequest.open(method, url, async);
	 //xmlHttpRequest.setRequestHeader("enctype","multipart/form-data");
	 xmlHttpRequest.send(variable);
			
	 xmlHttpRequest.onreadystatechange = function(){
				
				if ( xmlHttpRequest.status ==200 && xmlHttpRequest.readyState ==4){
					//alert(xmlHttpRequest.responseText);
					var result = xmlHttpRequest.responseText;
					
					if(result > 0){
						//document.getElementById("divPatientCodeList_"+code_id+"_"+gubun+"").style.display = "none";
						$("#divPatientCodeList_"+code_id+"_"+gubun+"").remove();
						
						if(gubun == 1){
							var codeSize = document.safetyCreateForm1.patient_code_size.value;
							document.safetyCreateForm1.patient_code_size.value = codeSize-1;
							document.safetyCreateForm1.patient_code_del.value = 1;
						}
						if(gubun == 2){
							var codeSize = document.safetyCreateForm1.medical_code_size.value;
							document.safetyCreateForm1.medical_code_size.value = codeSize-1;
							document.safetyCreateForm1.medical_code_del.value = 1;
						}
						if(gubun == 3){
							var codeSize = document.safetyCreateForm1.component_code_size.value;
							document.safetyCreateForm1.component_code_size.value = codeSize-1;
							document.safetyCreateForm1.component_code_del.value = 1;
						}
					}
					
				}else{
					//alert("통신에러");
				} 
			};
} 
 
</script>
 
 
 	<div style="width: 970; margin: 20 20 20 20; overflow-y: hidden;">
 	<img src="${titleImg }" />
 <form name="safetyCreateForm1" enctype="multipart/form-data" method="post" action="sideEffectReport.do">
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
			<col width="20%" />
			<col width="30%" />
			<col width="20%" />
			<col width="30%" />
		</colgroup>
<input type="hidden" name="action" value="modified"/>
	<input type="hidden" name="id" value="${article.id}" />

			<thead>
				<tr>
					<th colspan="4" class="textCenter">보고의종류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead">구분</th>
					<td colspan="3">
						<%@include file="/view/jsp/sideEffect/countryReportedIn.jsp" %>
					</td>
				</tr>
				<tr>
					<th class="tdHead">보고구분/보고일자</th>
					<td class="tdBody">
					<%@include file="/view/jsp/sideEffect/reportType.jsp" %>
					
					</td>
					<th>식약처 보고일자</th>
					<td>
					<input type="hidden" name="document_number" value="${article.document_number }"/> <!-- 문서번호 -->
					<input type="text" class="datepicker" id="mreport_date" name="mreport_date" value="${fn:substring(article.mreport_date,5,7)}/${fn:substring(article.mreport_date,8,10)}/${fn:substring(article.mreport_date,0,4)}" /></td>
				</tr>
				<tr>
					<th class="tdHead">보고자 유형</th>
					<td colspan="3">
						<%@include file="/view/jsp/sideEffect/reporterType.jsp" %> 
						 
					</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
			<col width="20%" />
			<col width="30%" />
			<col width="20%" />
			<col width="30%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="4" class="textCenter">보고자 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead">상호명</th>
					<td class="tdBody"><input type="text" value="${article.company_name }" name="company_name" /></td>
					<th class="tdHead">대표자 성명</th>
					<td class="tdBody"><input type="text" value="${article.representatives }" name="representatives" /></td>
				</tr>
				<tr>
					<th class="tdHead">담당자명</th>
					<td class="tdBody"><input type="text" value="${article.manager }" name="manager" /></td>
					<th class="tdHead"> 전화</th>
					<td class="tdBody"><input type="text" value="${article.report_tel }" name="report_tel" /></td>
				</tr>
				<tr>
					<th class="tdHead">FAX</th>
					<td class="tdBody"><input type="text" value="${article.fax }" name="fax" /></td>
					<th class="tdHead">EMAIL</th>
					<td class="tdBody"><input type="text" value="${article.email }" name="email" /></td>
				</tr>
					<tr>
						<th class="tdHead">주소</th>
						<td colspan="3">
							<input type="text" size="70" value="${article.report_address }" name="report_address"/>
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="addrSearch1('report_address')" /> 
						</td>
					</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
			<col width="20%" />
			<col width="30%" />
			<col width="20%" />
			<col width="30%" />
		</colgroup>

			<thead>
				<tr>
					<th colspan="4" class="textCenter">의료기기 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead">의료기기정보</th>
					<td><input onclick="pop();" type="text" readonly value="${article.meb_item.company.entp_name}" class="buttonised" name="meb_item_manuf_name" />
						<input type="hidden" value="${article.meb_item.id }" name="meb_item_manuf_id" />
						<input type="hidden" name="mebTypeInfoSelect" value="0" />
						<c:choose>
							<c:when test="${! empty article.sideEffectReportMebTypeInfo}">
								<c:forEach var="mebType" items="${article.sideEffectReportMebTypeInfo}">
									<input type="hidden" value="${mebType.id }" name="mebTypeInfoItemSeq"/>
								</c:forEach>
							</c:when>
							<c:otherwise>
									<input type="hidden" value="0" name="mebTypeInfoItemSeq"/>
							</c:otherwise>
						</c:choose>
					
					</td>
					
					<th class="tdHead">제조국/제조원</th>
						<td class="tdBody"><input type="text" value="${article.country_manufactured.propertyValue }" onclick="pop2();" readonly class="buttonised" name="countryManufactured" />
						<input type="hidden" name="country_manufactured_id" value="${article.country_manufactured_id }" readonly />/
						<input type="text" name="manufacturer" value="${article.manufacturer }">
						</td>
				</tr>
				<tr>
					<th class="tdHead">제조번호</th>
					<td class="tdBody" colspan="3">
						<input type="text" name="serial_number" value="${article.serial_number }" />
					</td>
				</tr>
					
				<!-- 
				<tr>
					<td class="tdHead">대상업체명</td>
					<td class="tdBody"><input type="text" value="${article.meb_item.manuf_name }" name="compnayName2"/></td>
					<td class="tdHead"> 업허가번호</td>
					<td class="tdBody"><input type="text" value="${article.meb_item.meddev_entp_seq }" name="grantNumber1" /></td>
				</tr>
				<tr>
					<td class="tdHead">품목명</td>
					<td class="tdBody"><input type="text" value="${article.meb_item.item_name }" name="itemName1" /></td>
					<td class="tdHead"> 분류번호/등급</td>
					<td class="tdBody"><input type="text" value="${article.meb_item.mea_class_no } / ${article.meb_item.grade } "name="classificationNumber" /></td>
				</tr>
				<tr>
					<td class="tdHead">품목허가번호</td>
					<td class="tdBody"><input type="text" value="${article.meb_item.meddev_item_seq }"  name="grantNumber2"/></td>
					
				</tr>
				<tr>
					<td class="tdHead">품목명</td>
					<td class="tdBody"><input type="text" value="" name="itemName2" /></td>
					<td class="tdHead"> 형명</td>
					<td class="tdBody"><input type="text" value="${article.meb_item.shape }" name="penalty" /></td>
				</tr>
				<tr>
					<td class="tdHead">제조번호</td>
					<td colspan="3"><input type="text" value="${article.id }" name="serialNumber"/></td>
				</tr>
				 -->
			</tbody>
 	</table>
 	
 	<br/>
 	
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
					<th colspan="5" class="textCenter">환자정보</th>
				</tr>
			</thead>
			<tbody>
			
				<tr>
					<th rowspan="2">환자정보</th>
						<tr>
							<th class="tdHead">성명</th>
							<td><input type="text" value="${article.patient_name }" name="patient_name"/></td>
							<th class="tdHead">성별/나이</th>
							<td class="tdBody">
						<%@include file="/view/jsp/sideEffect/gender.jsp" %>
						/<input type="text" value="${article.patient_age }"  name="patient_age" />	
					</td>
				</tr>
			
			<tr>
				<th class="tdHead" colspan="2">기타<br>&nbsp;특이사항</th>
					<td colspan="3">
					<textarea name="patient_extra_info" style="width:100%; height:220;" id="patient_extra_info">${article.patient_extra_info}</textarea>
						<script type="text/javascript" >
							myeditor1.config.editorHeight = '100px';            // 에디터 세로폭입니다.
							myeditor1.config.editorWidth = '100%';              // 에디터 가로폭입니다.
							myeditor1.inputForm = 'patient_extra_info';         // textarea의 ID 이름입니다.
							myeditor1.run();                                    // 에디터를 실행합니다.
						</script>
					</td>
			</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	
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
					<th colspan="5" class="textCenter">부작용 정보</th>
				</tr>
			</thead>
			<tbody>
			
				<tr>
					<th rowspan="3">부작용<br>&nbsp;발생기간</th>
					<th class="tdHead">최초<br>&nbsp;인지일자</th>
					<td colspan="3">
					<input type="text" name="SIDE_EFFECT_FIRST_DATE" id="SIDE_EFFECT_FIRST_DATE" class="datepicker" value="${fn:substring(article.side_effect_first_date,5,7)}/${fn:substring(article.side_effect_first_date,8,10)}/${fn:substring(article.side_effect_first_date,0,4)}" />
					</td>
				</tr>
				<tr>
					<th class="tdHead">부작용<br>&nbsp;발생일자</th>
					<td colspan="3">
						<input type="text" name="SIDE_EFFECT_GENERATION_DATE" id="SIDE_EFFECT_GENERATION_DATE" class="datepicker" value="${fn:substring(article.side_effect_generation_date,5,7)}/${fn:substring(article.side_effect_generation_date,8,10)}/${fn:substring(article.side_effect_generation_date,0,4)}""/>
					</td>
				</tr>
				<tr>
					<th class="tdHead">종료일자</th>
					<td colspan="3">
					<input type="text" name="SIDE_EFFECT_LAST_DATE" id="SIDE_EFFECT_LAST_DATE" class="datepicker" value="${fn:substring(article.side_effect_last_date,5,7)}/${fn:substring(article.side_effect_last_date,8,10)}/${fn:substring(article.side_effect_last_date,0,4)}""/>
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">진행상황</th>
					<td colspan="3">
						<%@include file="/view/jsp/sideEffect/reporterStatus.jsp" %>
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">부작용 결과 및<br>&nbsp;위해정도</th>
					<td class="tdBody" colspan="3">
						<%@include file="/view/jsp/sideEffect/sideeffectResult.jsp" %>						
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">부작용 원인 분류</th>
						<td class="tdBody" colspan="3">
							<%@include file="/view/jsp/sideEffect/sideeffectcaCuse.jsp" %>
						</td>
				</tr>
				<tr>
					<th rowspan="3">부작용 문제<br>&nbsp;코드</th>
					<th class="tdHead">환자문제<br>&nbsp;코드</th>
					<td colspan="3">
							<input type="hidden" value="0" name="patient_code_id"/>
							<input type="text" value="" size="50" name="patient_code_user_view"/>
						
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="selectPatient()" />
						
					<c:set var="varI" value="0"></c:set>	
					<c:choose>	
						<c:when test="${! empty article.patientCodeCondition }">
						<input type="hidden" name="patient_code_null" value="1"  />	
							<c:forEach var="patientCode" items="${article.patientCodeCondition }" varStatus="i">
							
							<c:set var="varI" value="${i.count }"></c:set>
							<div id="divPatientCodeList_${patientCode.id }_1" width="100%" name="divPatientCodeList_${patientCode.id }_1">
								<input type="hidden" value="${patientCode.id }" name="patient_code_id2"/>
								${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
								<img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="codeDelete(${article.id}, ${patientCode.id }, 1);" />
							</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
								<input type="hidden" name="patient_code_null" value="0"  />
						</c:otherwise>
					</c:choose>
					
						<input type="hidden" value="${varI }" name="patient_code_size"/>
						<input type="hidden" value="0" name="patient_code_del"/>
					</td>
				</tr>
				<tr>
					<th class="tdHead">의료기기문제<br>&nbsp;코드</th>
					<td colspan="3">
							<input type="hidden" value="0" name="medical_code_id"/>
							<input type="text" value="" size="50" name="medical_code_user_view"/>
							
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="selectMedical()" />
						
					<c:set var="varI2" value="0"></c:set>	
					<c:choose>	
						<c:when test="${! empty article.medicalCode }">
						<input type="hidden" name="medical_code_null" value="1" />		
							<c:forEach var="medicalCode" items="${article.medicalCode }" varStatus="i">
							<c:set var="varI2" value="${i.count }"></c:set>
							<div id="divPatientCodeList_${medicalCode.id }_2" width="100%">
								<input type="hidden" name="medical_code_id2" value="${medicalCode.id}" />
								${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
								<img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="codeDelete(${article.id}, ${medicalCode.id }, 2);" />
							</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
								<input type="hidden" name="medical_code_null" value="0" />
						</c:otherwise>						
					</c:choose>
					<input type="hidden" name="medical_code_size" value="${varI2 }" />
					<input type="hidden" value="0" name="medical_code_del"/>
					</td>
				</tr>
				<tr>
					<th class="tdHead">구성요소<br>&nbsp;코드</th>
					<td colspan="3">
							<input type="hidden" value="0" name="component_id"/>
							<input type="text" value="" size="50"name="component_id_user_view"/>
							
							<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="selectComponent()" />
						
						<c:set var="varI3" value="0"></c:set>
						<c:choose>	
							<c:when test="${! empty article.juncComponentCode }">
							<input type="hidden" name="component_code_null" value="1" />			
								<c:forEach var="componentCode" items="${article.juncComponentCode }" varStatus="i">
								<c:set var="varI3" value="${i.count }"></c:set>
								<div id="divPatientCodeList_${componentCode.id }_3" width="100%">
									<input type="hidden" name="component_id2" value="${componentCode.id }" />
									${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
									<img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="codeDelete(${article.id}, ${componentCode.id }, 3);" />
								</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
									<input type="hidden" name="component_code_null" value="0" />
							</c:otherwise>
							
						</c:choose>
						<input type="hidden" name="component_code_size" value="${varI3 }" />
						<input type="hidden" value="0" name="component_code_del"/>
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">인과관계</th>
					<td class="tdBody" colspan="3">
						<%@include file="/view/jsp\properties\causality\causality.jsp" %>
					</td>
				</tr>
				
				<tr>
					<th class="tdHead" colspan="2">부작용 원인 및 내용</th>
					<td colspan="3">
						<textarea name="extra_info" style="width:100%; height:220;" id="extra_info">${article.extra_info}</textarea>
						<script type="text/javascript" >
									myeditor3.config.editorHeight = '100px';            // 에디터 세로폭입니다.
									myeditor3.config.editorWidth = '100%';              // 에디터 가로폭입니다.
									myeditor3.inputForm = 'extra_info';                 // textarea의 ID 이름입니다.
									myeditor3.run();                                    // 에디터를 실행합니다.
						</script>
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">경과 및 후속조치</th>
					<td colspan="3">
						<textarea name="followup" style="width:100%; height:220;" id="followup">${article.followup }</textarea>
								<script type="text/javascript" >
									myeditor2.config.editorHeight = '100px';            // 에디터 세로폭입니다.
									myeditor2.config.editorWidth = '100%';              // 에디터 가로폭입니다.
									myeditor2.inputForm = 'followup';                 // textarea의 ID 이름입니다.
									myeditor2.run();                                    // 에디터를 실행합니다.
								</script>
					</td>
				</tr>
				<tr>
					<th colspan="2">사후조치 결과</th>
					<td colspan="3">
						<%@include file="/view/jsp\sideEffect\followUpAction.jsp" %>  
					</td>
				</tr>
				
				<tr>
					<th rowspan="2">부작용<br>&nbsp;발생시설</th>
					<th class="tdHead">기관명</th>
					<td>
						<input type="text" name="organization" value="${article.organization}"/>					
					</td>
					<th class="tdHead">연락처</th>
					<td>
						<input type="text" name="organization_tel" value="${article.organization_tel }"/>
					</td>
				</tr>
				<tr>
					<th class="tdHead">주소</th>
					<td colspan="3">
						<input type="text" name="organization_address" size="70" value="${article.organization_address}"/>
						<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색"  onclick="addrSearch1('organization_address');"/>
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">식약처 후속조치</th>
					<td colspan="3">
						 <textarea name="kfda_followup" style="width:100%; height:220;" id="kfda_followup">${article.kfda_followup}</textarea>
							<script type="text/javascript" >
							myeditor5.config.editorHeight = '100px';            // 에디터 세로폭입니다.
							myeditor5.config.editorWidth = '100%';              // 에디터 가로폭입니다.
							myeditor5.inputForm = 'kfda_followup';              // textarea의 ID 이름입니다.
							myeditor5.run();                                    // 에디터를 실행합니다.
							</script> 
					</td>	
				</tr>
				<tr>
					<th class="tdHead" colspan="2">기타사항</th>
					<td colspan="3">
						 <textarea name="extra_info2" style="width:100%; height:220;" id="extra_info2">${article.extra_info2}</textarea>
						<script type="text/javascript" >
									myeditor4.config.editorHeight = '100px';            // 에디터 세로폭입니다.
									myeditor4.config.editorWidth = '100%';              // 에디터 가로폭입니다.
									myeditor4.inputForm = 'extra_info2';                 // textarea의 ID 이름입니다.
									myeditor4.run();                                    // 에디터를 실행합니다.
						</script> 
					</td>
				</tr>
				
				<!-- 
					<tr>
						<th class="tdHead" colspan="2">hyhtest</th>
						<td colspan="3">
							 <textarea name="contents" class="ckeditor" rows="20" cols="50"><//%=hyhtest %></textarea>
						</td>	
					</tr>
				 -->
				 
				<tr>
					<th class="tdHead" colspan="2">등록일</th>
						<td colspan="3">
							<input type="text" name="first_modified" class="datepicker" id="first_modified" value="${fn:substring(article.first_modified,5,7)}/${fn:substring(article.first_modified,8,10)}/${fn:substring(article.first_modified,0,4)}" />
						</td>
					</tr>
				
			</tbody>
 	</table>
 	
 	<br/>
 	
 	
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="40%" />
				<col width="10%" />
		</colgroup>
 	
 	${juncRepoerAttachment[0].id }<br>
 	
 	
 	
 			<thead>
				<tr>
					<th colspan="4" class="textCenter">첨부자료</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>No.</th>
					<th>파일명</th>
					<th colspan="2">파일설명</th>
				</tr>
				<c:forEach var="file" items="${requestScope.fileList}" varStatus="i">
					<tr id="divFileList_${file.id}">
						
						<td>
						<input type="hidden" name="fileId" value="${file.id }" />
							${i.count }
						</td>
						<td>${file.name }</td>
						<td>${file.fileName }</td>
						<td><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="fileDel1(${file.id}, ${article.id }, '${file.fullURL }')" /></td>
						
					</tr>
				</c:forEach>
					<tr><td colspan="4">
				
				<%@include file="/view/jsp/common/multiFileUploader.jsp" %>
					</td></tr>
			</tbody>


 
 	</table>
 	
 	<div style="float: right; padding: 10px 0 20px 0;">			
		<img src="view/style/images/btn_ok.jpg" alt="확인" class="hover1" name="update" onclick="submit1()"/>
		<img src="view/style/images/bt_cancel.jpg" alt="취소" class="hover1" name="list" onclick="cancel('${article.id}')"/>
 	</div>
 	
 	
 	</div>
 </form>
 
 
 
 	
 
 <script type="text/javascript">
 
 var divNum = 0;
 
 	function fileDel1(fileId, articleId, fullURL){
 		//alert("파일삭제버튼  " + fileId);
 		divNum = fileId;
 		var xmlHttpRequest = new XMLHttpRequest();
		
		var isAsynch = true;
		var requestMethod = "POST";
		var requestURL = "sideEffectReport.do?action=fileDelete&fileId="+fileId+"&articleId="+articleId+"&fullURL="+fullURL;
		
		xmlHttpRequest.onreadystatechange = function(){
			xmlHttpRequestCallBack(xmlHttpRequest);
		
		};
		
		xmlHttpRequest.open(requestMethod, requestURL, isAsynch);
		xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlHttpRequest.send(null);
		
		
 	}
 	
 	
	function xmlHttpRequestCallBack(xmlHttpRequest){
		
		var responseReady = 4;	//request finished and response is ready
		var statusOK = 200;	// status OK
		
		if(xmlHttpRequest==null){
			alert('xmlHttpRequest is null');
		}else{
			
			if (xmlHttpRequest.readyState == responseReady && xmlHttpRequest.status == statusOK ){
				//var file_id = xmlHttpRequest.responseText;
				document.getElementById("divFileList_"+divNum+"").style.display = "none";
			}
			
		}
		
	}
	
	
 
 	//function pop(){
 	//	var url ="meb_item.do?action=list";
 	//	var properties ="width=500,height=700";
 	//	window.open(url,properties, true);
 	//	window.open(url, '', properties, 'scrollbars=yes');
 	//}
 	 
 	function pop(){
 		
 		var url ="meb_item.do?action=getManufImportEntpNameList";
 		var properties ="width=500,height=700";
 		//window.open(url,properties, true);
 		window.open(url, '', properties, 'scrollbars=yes');
 		
 	}
 	
 	
 	//function pop2(){
 	//	var url ="properties.do?action=listCountryPop";
 	//	var properties ="width=500,height=700";
 	//	window.open(url, '', properties, 'scrollbars=yes');
 	//}
 	function pop2(){
 		//alert("국가코드(?)");
 		var url ="properties.do?action=listCountryPop";
 		var properties ="width=500,height=700";
 		window.open(url, '', properties, 'scrollbars=yes');
 		
 	}
 	
 	function addrSearch1(inputName){
		//alert("inputName  " + inputName);	
		var url ="address.do?action=pop&inputName="+inputName;
 		var properties ="width=500,height=700";
 		//window.open(url,properties, true);
 		window.open(url, '', properties, 'scrollbars=yes');
 		
	}
 	
 	
 	function cancel(id){
  		 location.href="sideEffectReport.do?action=read&articleId="+id;
 		//history.back();
  	}
 	
 	function selectPatient(){
 		var frm1 = document.safetyCreateForm1;
 		var nullChk = frm1.patient_code_size.value;
 		if(nullChk >= 1){
	 		var patient_code_id2_length = frm1.patient_code_id2.length;
	 		var patient_code_id2_del = frm1.patient_code_del.value;
			if(patient_code_id2_length >= 3 && patient_code_id2_del==0){
				alert("환자문제코드는 3개 이상 선택 하실 수 없습니다. 기존에 코드를 삭제해 주세요.");
				return;
			}
 		}
	 		var url ="sideEffectReport.do?action=selectPatient";
	 		var properties ="width=820,height=720";
	 		window.open(url, '', properties, 'scrollbars=yes');
 		
 	}
 	function selectMedical(){
 		var frm1 = document.safetyCreateForm1;
 		var nullChk = frm1.medical_code_size.value;
 		if(nullChk >= 1){
 			var medical_code_id2_length = frm1.medical_code_id2.length;
 			var medical_code_id2_del = frm1.medical_code_del.value;
			if(medical_code_id2_length >= 3 && medical_code_id2_del==0){
				alert("의료기기문제코드 3개 이상 선택 하실 수 없습니다. 기존에 코드를 삭제해 주세요.");
				return;
			}
 		}
 		var url ="sideEffectReport.do?action=selectMedical";
 		var properties ="width=820,height=720";
 		window.open(url, '', properties, 'scrollbars=yes');
 	}
 	function selectComponent(){
 		var frm1 = document.safetyCreateForm1;
 		var nullChk = frm1.component_code_size.value;
 		if(nullChk >= 1){
	 		var component_id2_length = frm1.component_id2.length;
	 		var component_code_id2_del = frm1.component_code_del.value;
			if(component_id2_length >= 3 && component_code_id2_del==0){
				alert("구성요소코드는 3개 이상 선택 하실 수 없습니다. 기존에 코드를 삭제해 주세요.");
				return;
			}
 		}
 		
 		var url ="sideEffectReport.do?action=selectComponent";
 		var properties ="width=820,height=720";
 		window.open(url, '', properties, 'scrollbars=yes');
 
 	}
 	
 	
 	$(document).ready(function(){
 		
 		initPage();
 		
 		
	});
 	
 	function initPage(){
 		
		$("#arrReportDate_0").datepicker();
		$("#arrReportDate_0").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#arrReportDate_1").datepicker();
		$("#arrReportDate_1").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#arrReportDate_2").datepicker();
		$("#arrReportDate_2").datepicker("option", "dateFormat", "yy-mm-dd");
		
		$("#mreport_date").datepicker();
		$("#mreport_date").datepicker("option", "dateFormat", "yy-mm-dd");
		
		$("#SIDE_EFFECT_FIRST_DATE").datepicker();
		$("#SIDE_EFFECT_FIRST_DATE").datepicker("option", "dateFormat", "yy-mm-dd");
		
		$("#SIDE_EFFECT_GENERATION_DATE").datepicker();
		$("#SIDE_EFFECT_GENERATION_DATE").datepicker("option", "dateFormat", "yy-mm-dd");
		
		$("#SIDE_EFFECT_LAST_DATE").datepicker();
		$("#SIDE_EFFECT_LAST_DATE").datepicker("option", "dateFormat", "yy-mm-dd");

		$("#first_modified").datepicker();
		$("#first_modified").datepicker("option", "dateFormat", "yy-mm-dd");
		
	}
	
 	
 </script>
 
 
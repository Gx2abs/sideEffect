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
 	<form name="patientConditionCreateForm1"  method="POST" action="safetyInfo.do"
 	enctype="multipart/form-data">
 		<input type="hidden" name="level" value="${param.level }"/>
 		<input type="hidden" name="action" value="updateSafetyInfo"/><br/>
 		<input type="hidden" name="regionId" value="${param.regionId }"><br/>
 		<input type="hidden" name="radioInitialClick" value="-1">
 		<input type="hidden" name="referenceInputIndex" value="-1">
 		<input type="hidden" name="isModalOpen" value="-1">
 		 <input type="hidden" name="articleId" value="${article.id }">
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
					<th class="tdHead" colspan="2" >지역/구분 수정 </th>
					<td colspan="3">
						<input type="radio" value="1" name="modRegion"/>지역/구분 수정 <input type="radio" name="modRegion" value="0" checked="checked"/>지역/구분 수정안함<br/>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >지역 </th>
					<td colspan="3" >
						
						${article.region.propertyValue } <b class="modClass1">-></b>
						<select name="regionIdSelect" class="modClass1">
							<c:forEach var="region" items="${requestScope.regionList }">
								<option value="${region.id }">${region.propertyValue }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">
						
						<table style="width: 25%; float: left; display: inline;">
							<c:forEach var="currProp" items="${article.prop1List}" varStatus="i">
								<tr>
									<td>현재구분${i.count }</td><td>${currProp.propertyValue }</td><td><b class="modClass1">-></b></td>
								</tr>
							</c:forEach>
						</table>
						<table style=" width: 20%; float: left; display: inline;" class="modClass1">
							<tr>
								<td>구분1</td>
								<td>
									<div id="prop1Area" >
									</div>
								</td>
							</tr>
							<tr>
								<td>구분2</td>
								<td>
									<div id="prop2Area" style="float: left" class="modClass1">
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >제목 </th>
					<td colspan="3"><input type="text" name="title" value="${article.title }"/>
						</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">내용</th>
					<td class="tdBody"  colspan="3">
						<textarea id="body" name="body" style="width:98%;height:55px;padding:4px;" style="display: none">
							${article.body }
						</textarea>
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
						<c:forEach var="attachment" items="${article.attachments }" varStatus="i">
							<div class="fileDiv"><div class="buttonised" onclick="download(${attachment.id})">${attachment.originalName }</div><input type="button" value="파일삭제" onclick="deleteAttachment(${attachment.id},${i.count });"/><br/></div>
						</c:forEach>
						<input type="button" value="파일추가" id="addFile"/>
						<div id="fileDiv1"></div>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">의료기기 정보</th>
					<td class="tdBody"  colspan="3">
					
						<c:forEach var="reference" items="${article.itemList }" varStatus="i">
						<table style="border: solid red 2px;" class="referenceTable1">
							<tr class="itemProperty">
								<th class="tdHead">
									품목코드 
									<input type="button" value="삭제" 
									onclick="deleteReference(${article.id},${reference.id},${article.referenceTypeId }, ${i.count } );">
								</th>
								<td>
									${reference.mea_item.mea_class_no}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									품목명
								</th>
								<td>
									${reference.mea_item.class_kor_name}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									등급
								</th>
								<td>
									${reference.mea_item.grade}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									업소명
								</th>
								<td>
									${reference.company.entp_name}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									품목허가번호
								</th>
								<td>
									${reference.company.item_permit_code}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									형명
								</th>
								<td>
									<c:forEach var="itemType" items="${reference.item_type}">
										${itemType.type_name }<br/>
									</c:forEach>
									
								</td>
							</tr>
						</table>
						</c:forEach>
						
						<c:forEach var="reference" items="${article.itemCategoryList }" varStatus="i">
						<table class="referenceTable1">
							<tr class="itemProperty">
								<th class="tdHead">
									품목코드
									<input type="button" value="삭제" onclick="deleteReference(${article.id},${reference.id},${article.referenceTypeId }, ${i.count });">
								</th>
								<td>
									${reference.mea_class_no}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									품목명
								</th>
								<td>
									${reference.class_kor_name}
								</td>
							</tr>
							<tr class="itemProperty">
								<th class="tdHead">
									등급
								</th>
								<td>
									${reference.grade}
								</td>
							</tr>
						</table>
						</c:forEach>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">의료기기 정보</th>
					<td class="tdBody"  colspan="3">
						<c:choose>
							<c:when test="${!empty requestScope.referenceTypes }">
								<c:forEach var="referenceType" items="${requestScope.referenceTypes }">
									<input type="radio" name="referenceType" value="${referenceType.id }"/>${referenceType.propertyValue }
								</c:forEach>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="referenceType" value="0"/>no data
							</c:otherwise>
						</c:choose>
						<div><input id="addItem" type="button" name="addItem" value="제품추가" class="hidden referenceButton1"/></div>
						<div><input type="button" name="addCategory" value="품목추가" class="hidden referenceButton1"/></div>
						<div id="referenceDiv1">
						</div>
					</td>
				</tr>
			</tbody>
 	</table>
 	
 		<div id="modal" style="border:3px solid black; background-color:white;  padding:25px; font-size:150%; text-align:center; display:none;">
			
			<input type="button" value="OK" onClick="hideModal()">
		</div>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<c:if test="${!empty sessionScope.user }">
	 			<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" onclick="create();"/>
	 			<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/>
	 		</c:if>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	
 		function deleteReference(articleId, referenceId, referenceTypeId, deleteIndex){
 			
 			if(confirm("정말 삭제 하시겟습니까?")){
 				
 				var message = "articleId : " + articleId + "\n";
 				message += "referenceId : " + referenceId + "\n";
 				message += "referenceType : " + referenceTypeId + "\n";
 				
 				alert(message);
 				
 				var method = "get";
 				var url = "safetyInfo.do?action=deleteSafetyInfoReference&articleId="+articleId;
 				url += "&referenceId="+referenceId;
 				url += "&referenceTypeId="+referenceTypeId;
 				var isAsync = true;
 				var xhr = new XMLHttpRequest();
 				
 				xhr.open(method, url, isAsync);
 				xhr.send();
 				
 				xhr.onreadystatechange = function(){
 					
 					if(xhr.readyState==4 && xhr.status == 200){
 						var response = xhr.responseText;
 						alert(response);
 						if(response==1){
 							alert("삭제성공");
 							deleteIndex = deleteIndex-1;
 							var node = $(".referenceTable1:eq("+deleteIndex+ ")");
 							$(node).remove();
 						}else{
 							alert("삭제실패");
 							
 							
 							
 						}
 					}
 					
 				};
 				
 			}
 			
 		}
 	
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
 			
 			bindMeddevRadio1();
 			bindAddItem();
 			bindAddCategory();
 			bindAddAttachment();
 			checkRadio();
 			bindSelectProp1();
 			bindRegionIdSelect();
 			bindModRegionRadio();
 			
 			$(".modClass1").css("display", "none");
			$("#prop1Area").html("");
			$("#prop2Area").html("");
 			
 		}
 		
 		function checkRadio(){
 			$("input[name=referenceType]:nth-child(1)").attr("checked", "checked");
 			$("#addItem").css("display", "inline");
 		}
 		
 		function bindAddCategory(){
 			$("input[name=addCategory]").bind("click", function(){
 				addMeddev();
 			});
 		}
 		
 		/*
 		제품, 품목 한줄 추가 
 		*/
 		function bindAddItem(){
 			$("input[name=addItem]").bind("click", function(){
 				addMeddev();
 			});
 		}
 		
 		function addMeddev(){
 			var wrapper = document.createElement("div");
				var textInput = document.createElement("input");
				var textInput2 = document.createElement("input");
				var br = document.createElement("br");
				var deleteButton = document.createElement("input");
				$(deleteButton).attr("type","button");
				$(deleteButton).val("삭제");
				
				$(deleteButton).bind("click", function(){
					$(wrapper).remove();
				});
				
				$(textInput).attr("type", "text");
				$(textInput).attr("class", "buttonised");
				$(textInput).attr("readonly", "readonly");
				$(textInput).attr("name", "referenceId");
				$(textInput).css("display", "none");
				
				$(textInput2).attr("type", "text");
				$(textInput2).attr("class", "buttonised");
				$(textInput2).attr("readonly", "readonly");
				$(textInput2).attr("name", "referenceText");
				
				$(wrapper).append(textInput);
				$(wrapper).append(textInput2);
				$(wrapper).append(deleteButton);
				$(wrapper).append(br);
				$("#referenceDiv1").append(wrapper);
						
				$(textInput2).bind("click", function(e){
					
					//openLayerSafetyInfoProp1($("input[name=referenceId]").index(textInput));
					openPopupSafetyInfoProp1($("input[name=referenceId]").index(textInput2));
					
				});
				
 		}
 		
 		function bindMeddevRadio1(){
 			var list = $("input[name=referenceType]");
 			$.each(list,function(index, element){
 				console.log("binding... ");
 				$(element).bind("click", function(e){
 					//e.preventDefault();
 					var radioInitialClick = $("input[name=radioInitialClick]").val();
 			 
 					if("-1"!=radioInitialClick){
 						if(confirm("현재 의료기기 정보는 초기화 됩니다. 계속 하시겟습니까?")){
 							
 	 						changeMeddevRefType($(element).val());
 	 					}else{
 	 						console.log("$(element).val()"+$(element).val());
 	 						e.preventDefault();
 	 					}	
 					}else{
 							
 	 						changeMeddevRefType($(element).val());	
 	 						$("input[name=radioInitialClick]").val("1");
 	 						
 					}
 					
 				});
 			});
 		}
 		
 		function changeMeddevRefType(refType){
 			console.log("refType := " + refType);
 			
 			$("#referenceDiv1").html("");
 			
 			switch (refType){
 			
 			case "1":
 				$(".referenceButton1").css("display", "none");
 				$("input[name=addCategory]").css("display", "inline");
 				break;
 			case "2":
 				$(".referenceButton1").css("display", "none");
 				$("input[name=addItem]").css("display", "inline");
 				break;
 			default:
 					console.log("switch hit default");
 				break;
 			}
 			
 		}
 		 
	
		function bindAddAttachment(){
			$("#addFile").bind("click", function(){
				addAttachment();
			});
		}
		
		function addAttachment(){

			var file = document.createElement("input");
			var remove = document.createElement("input");
			var br = document.createElement("br");
			$(file).attr("type", "file");
			$(file).attr("name", "attachment");
			$(remove).attr("type","button");
			$(remove).attr("name", "removeFile");
			$(remove).val("파일삭제");
			
			
			$("#fileDiv1").append(file);
			$("#fileDiv1").append(remove);
			$("#fileDiv1").append(br);
			
			$(remove).bind("click", function(){
				$(file).remove();
				$(remove).remove();
				$(br).remove();
			});
			
		}
	
	function openPopupSafetyInfoProp1(targetIndex){
			
			
			var referenceType = $("input[name=referenceType]:checked").val();
			console.log("checked referenceType := " + referenceType);
			var url = "";
			var name="_blank";
			var specs="width=800,height=500";
			if(referenceType==1){
				url = "itemCategory.do?action=popItemCategory";
			}else{
				url = "safetyItem.do?action=listItemPopup";
			}
						
			window.open(url, name, specs);
			
		}
		
		function openLayerSafetyInfoProp1(targetIndex){
			console.log("add value to input # " + targetIndex + "");
			var isModalOpen = $("input[name=isModalOpen]").val();
			if(isModalOpen !=1){
				$("input[name=isModalOpen]").val("1");
				$("input[name=referenceInputIndex]").val(targetIndex);
				var referenceType = $("input[name=referenceType]:checked").val();
				console.log("checked referenceType := " + referenceType);
				var xhr = new XMLHttpRequest();
				var method = "get";
				var url = "";
				if(referenceType==1){
					url = "itemCategory.do?action=popItemCategory";
				}else{
					url = "safetyItem.do?action=listItemPopup";
				}
				 
				var isAsync = true;
				
				xhr.open(method, url, isAsync);
				xhr.send();
				
				xhr.onreadystatechange = function(){
					
					if(xhr.readyState == 4){
						if(xhr.status == 200){
							
							$("#modal").html(xhr.responseText);
							
							Popup.showModal('modal',null,null,{'screenColor':'blue','screenOpacity':0.2,'offsetTop':-250,'offsetLeft':-200});
							
						}
					}
					
				};		
			}
			
			
			return;
		}
		
		function hideModal(){
			
			Popup.hide('modal');
			$("input[name=isModalOpen]").val("-1");
		}
		
		function initSelectProp1(){
			getChildSelectProp(  $("select[name=prop1] option:selected").val(), 2 ,"prop2Area");
		}
		
		function bindSelectProp1(){
			try{
				 var regionId = $("input[name=regionId]").val();
				$("select[name=prop1]").bind("change", function(e){
					getChildSelectProp1(  $("select[name=prop1] option:selected").val(), 2, regionId , "prop2Area");
				});
				
			}catch(Exception ){
				alert(Exception.message);
			}
		}
		
		function getChildSelectProp(parentId, propertyLevel, regionId, targetAreaId){
			
			var method = "get";
			var url = "safetyInfo.do?action=listSafetyInfoProp&parentId="+parentId+"&propertyLevel="+propertyLevel+"&regionId="+regionId;
			var isAsync = true;
			var xhr = new XMLHttpRequest();
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState==4 && xhr.status ==200){
					var completeTargetAreaId = "#" + targetAreaId;
					$(completeTargetAreaId).html(xhr.responseText);
				}
				
			};
			
		}
		
	function getChildSelectProp1(parentId, propertyLevel, regionId, targetAreaId, nodeName){
			
			var method = "get";
			var url = "safetyInfo.do?action=listSafetyInfoProp&parentId="+parentId;
			url +="&propertyLevel="+propertyLevel+"&regionId="+regionId+"&nodeName="+nodeName ;
			
			var isAsync = true;
			var xhr = new XMLHttpRequest();
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState==4 && xhr.status ==200){
					var completeTargetAreaId = "#" + targetAreaId;
					$(completeTargetAreaId).html(xhr.responseText);
					
					var prop1 = $("select[name=prop1]");
					
					$(prop1).bind("change", function(){
						var prop1Id = $("select[name=prop1] option:selected").val();
						if(prop1Id==0){
							return;
						}
						var regionId = $("select[name=regionIdSelect] option:selected").val();
						getChildSelectProp1(prop1Id,2, regionId, "prop2Area","prop2");
					});
					
				}
				
			};
			
		}
		
		function bindRegionIdSelect(){
			$("select[name=regionIdSelect]").bind("change", function(event){
					var selected = $("select[name=regionIdSelect] option:selected").val();
					if(selected==0){
						$("#prop1Area").html("");
						$("#prop2Area").html("");
						return;}
					getProp1(selected);
			});
		}
		
		function getProp1(selected){
			
			getChildSelectProp1(0, 1, selected, "prop1Area", "prop1");
		}
		
		function bindModRegionRadio(){
			
			$("input[name=modRegion]").bind("change", function(e){
				
				var modRegion = $("input[name=modRegion]:checked").val();
				
				if(modRegion==0){
					$(".modClass1").css("display", "none");
					$("#prop1Area").html("");
					$("#prop2Area").html("");
				}else{
					$(".modClass1").css("display", "inline");
				}
				
			});
			
		}

		function deleteAttachment(id, index){
			alert(id);
			
			var method = "get";
			var url = "safetyInfo.do?action=deleteAttachment&fileId="+id;
			var isAsync = true;
			
			var xhr = new XMLHttpRequest();
			
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState==4 && xhr.status==200){
					if(xhr.responseText==1){
						alert("삭제성공");
						console.log("removing # " + index );
						index = index -1;
						$(".fileDiv:eq("+index+")").html("");
						
					}else{
						alert("삭제실패");
					}
				}
				
			};
			
		}
		
		function download(id){
	  		
	  		console.log("download.id : = " + id);
	  		location.href="safetyInfo.do?action=download&fileId="+id;
	  		
	  	}
 	</script>
 
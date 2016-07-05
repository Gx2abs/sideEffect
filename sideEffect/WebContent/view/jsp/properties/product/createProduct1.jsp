<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
  	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="productCreateForm1" method="POST" action="properties.do">
 		<input type="hidden" name="action" value="createProduct"/>
 		<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
	 	<table class="bordered fitToParent view_table" id="insertTable">
			<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">공통 코드 관리 - 제품 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업체정보</th>
					<td class="tdBody">
					<input type="hidden" name="company_id" readonly /><input type="text" name="company_name" />
					<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="pop1();" />
					</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2" >품목정보</th>
					<td class="tdBody"><input type="hidden" name="item_grade" /><input type="text" name="item_id" /><img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="pop2();" /></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2" >품목허가번호</th>
					<td class="tdBody">
						<select name="codeType" id="codeType">
							<c:choose>
								<c:when test="${!empty requestScope.codeType  }">
									<c:forEach var="cType" items="${codeType }">
										<option value="${cType.id }">${cType.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
						<input type="text" name="meddev_item_no" />
						<img src="view/style/images/bt_search_chk.jpg" class="hover1" alt="중복검색" onclick="chkName();" /><span id="meddev_item_no"></span></td>
				<tr>
					<th class="tdHead" colspan="2" >제조원(수입의 경우)</th>
					<td class="tdBody"><input type="text" name="manuf_import_name" /></td>
				</tr>
				<tr >
					<th class="tdHead" colspan="2" >형명</th>
					<td class="tdBody" colspan="3" ><input type="text" name='name[]' /><!-- /<input type="text" name='manuf_lot[]' /> -->
					<img src="view/style/images/btn_tab02.jpg" class="hover1" alt="추가" id="" onclick="AddBox()" />
					<div id="ResultBox"></div></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력사항</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" name="lastModified" id="lastModified"/></td> -->
					<th class="tdHead">적용일자</th>
					<td class="tdBody" ><input type="text" name="activeFrom" id="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody">
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<option value="${hType.id }">${hType.propertyValue }</option>
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
					<th class="tdHead" >담당자</th>
					<td class="tdBody"><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody"><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody">
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<option value="${useStat.id }">${useStat.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>&nbsp;&nbsp;
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 			$("form[name=productCreateForm1]").submit();			
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
 			
 		}
 		
 		// 업체정보POP
 		function pop1(){
 	 		var url ="properties.do?action=listCompanyPop";
 	 		var properties ="width=500,height=700";
 	 		window.open(url, '', properties, 'scrollbars=yes');
 	 		
 	 	}
 		
 		// 품목정보POP
 		function pop2(){
 	 		var url ="properties.do?action=listItemPop";
 	 		var properties ="width=500,height=700";
 	 		window.open(url, '', properties, 'scrollbars=yes');
 	 		
 	 	}
 		
 	// 중복체크
 		function chkName(){
 	
			var meddev_item_no = document.productCreateForm1.meddev_item_no.value;
 			var cob_flag_code = document.productCreateForm1.codeType.value;
 			//alert("meddev_item_no  " + meddev_item_no);
 			//alert("cob_flag_code  " + cob_flag_code);
			
 				if(meddev_item_no != "" && cob_flag_code != ""){
 					
 					 createXMLHttpRequest();
 					   var url = "properties.do?action=ProductChk";  
 					  xmlhttp.open("POST",url,true);
 					 xmlhttp.onreadystatechange = function(){
 					    if(xmlhttp.readyState == 4){
 					     if(xmlhttp.status == 200){
 					      if(fReturnCheckId(xmlhttp.responseText)){
 					    	
 					    	  
 					       return;
 					       }
 					     }
 					    }
 					    }
 					xmlhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
 					xmlhttp.send("meddev_item_no=" + meddev_item_no +"&cob_flag_code=" + cob_flag_code);
 					    //xmlhttp.send("meddev_item_no=" + meddev_item_no + "&sc=" + sc + "&sc2=" + sc2);            
 					  }
 					 }
 					 var xmlhttp;
 					 function createXMLHttpRequest(){
 						 
 					  if (window.XMLHttpRequest)
 					  {// code for IE7+, Firefox, Chrome, Opera, Safari
 					     xmlhttp=new XMLHttpRequest();
 					    }else{// code for IE6, IE5
 					     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); 
 					    }
 					 }
 					 
 					 function fReturnCheckId(resultData){
 					  if(resultData == '0' || resultData == 0){
 							alert("사용할 수 있는 품목허가번호 입니다.");
 					   return false;
 					  }else{			  
 						alert("이미 사용 중인 품목허가번호입니다.");
 						document.productCreateForm1.meddev_item_no.value="";
 					   return true;
 					  }
 				 }
 					 				 
	// 추가
	//var cnt = 2; 
   	 function AddBox() { 
  			
  		//var ResultItem = "<input type='text' name='name[]' />/<input type='text' name='manuf_lot[]' /><br>";
  		var ResultItem = "<input type='text' name='name[]' /><br>";
       	document.getElementById("ResultBox").innerHTML += ResultItem;
       	//cnt++; 
	     /* if(cnt > 5) { 
	      alert("추가는 5개까지 추가가 가능합니다."); 
	      exit; 
	    } */  
    }
   		 
 </script>
 	
 	
 	
 
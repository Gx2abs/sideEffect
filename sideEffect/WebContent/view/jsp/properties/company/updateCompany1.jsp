<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 	<form name="companyCreateForm1" method="post" action="safetyCompany.do">
 		<input type="hidden" name="action" value="updateCompany"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
 		<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
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
					<th colspan="5">공통 코드 관리 - 업체코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업체명</th>
					<td class="tdBody" ><input type="text" name="entp_name" value="${article.entp_name }"/></td>
					<th class="tdHead" >업허가번호</th>
					<td class="tdBody" >제<input type="text" name="meddev_entp_no" value="${article.meddev_entp_no }"/>호
					<img src="view/style/images/bt_search_chk.jpg" class="hover1" alt="중복검색" onclick="chkName();" /><span id="meddev_entp_no"></span></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">업구분</th>
					<td class="tdBody" >
					<select name="flagType" id="flagType">
							<c:choose>
								<c:when test="${!empty requestScope.flagType  }">
									<c:forEach var="flagType" items="${flagType }">
							 			<c:choose>
											<c:when test="${article.cob_flag_code eq flagType.id }">
												<option value="${flagType.id }" selected="selected">${flagType.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${flagType.id }" >${flagType.propertyValue }</option>
											</c:otherwise>
										</c:choose>		
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select></td>
					<th class="tdHead" >상태</th>
					<td class="tdBody" >
					<select name="closeType" >
						<c:choose>
							<c:when test="${!empty requestScope.closeType  }">
								<c:forEach var="cType" items="${closeType }">
									<c:choose>
											<c:when test="${article.shutdown_close_reopen_code eq cType.id }">
												<option value="${cType.id }" selected="selected">${cType.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${cType.id }">${cType.propertyValue }</option>
											</c:otherwise>
										</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="-1">no data</option>
							</c:otherwise>
						</c:choose>
					</select>
					</td>
				<tr>
					<th class="tdHead" colspan="2" >업허가일자</th>
					<td class="tdBody" ><input type="text" name="permit_date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${article.permit_date }"/>"/></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2" >주소</th>
					<td class="tdBody" colspan="3"><input type="text" name="entp_zip_no"  value="${article.entp_zip_no }"/><img src="view/style/images/bt_postcode.jpg" class="hover1" alt="우편번호검색" onclick="addrSearch1()" /> <br>
						<input type="text" name="entp_addr1" value="${article.entp_addr1 }"/><input type="text" name="entp_addr2" value="${article.entp_addr2 }"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력사항</th>
					<%-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" name ="lastModified" id="lastModified" value="${article.lastModified }"/></td> --%>
					<th class="tdHead" >작성일자</th>
					<td class="tdBody"><input type="text" name = "activeFrom" id="activeFrom" value="${article.activeFrom }"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
							<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${hType.propertyValue eq '개정'}">
												<option selected="selected" value="${hType.id }">${hType.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${hType.id }">${hType.propertyValue }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
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
					<td class="tdBody" colspan="3"><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdHead"  colspan="3">
						<table class="his_table">
						<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
						<col width="40%" />
						<col width="10%" />
						</colgroup>
						<tr class="itemProperty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th>사유</th>
							<th>삭제</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.companyHistory }">
								<c:forEach var="companyHistory" items="${article.companyHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${companyHistory.activeFrom }"/>
							</td> --%>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${companyHistory.lastModified }"/>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${companyHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td>
							<c:choose>
								<c:when test="${!empty article.companyHistory }">
										${companyHistory.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty companyHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${companyHistory.historyDescription }</textarea>
									</c:when>
								<c:otherwise>
									<textarea disabled="disabled" name="historyDescription"/>no record</textarea>
								</c:otherwise>
								</c:choose>
							</td>
							<td id="history${companyHistory.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="이력삭제" onclick="deleteHistory('${companyHistory.id}','<%=privilegeId %>');"/></td>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<c:choose>
											<c:when test="${useStat.id eq article.isInUse }">
												<option selected="selected" value="${useStat.id }">${useStat.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${useStat.id }">${useStat.propertyValue }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
					<%-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">이력사항</th>
					<td class="tdBody" colspan="3">
						<ul>
						<c:choose>
							<c:when test="${!empty article.companyHistory }">
								<c:forEach var="itemHistory" items="${article.companyHistory }">
									<li id="history${itemHistory.id }">
									사유: ${itemHistory.historyDescription }
									담당자 : ${companyHistory.manager }										
									구분 : ${companyHistory.historyType }
									변경일자 : ${companyHistory.activeFrom }									
									 <input type="button" value="이력삭제" name="historyToDelete" onclick="deleteHistory(${itemHistory.id});"></input></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>
									No record
								</li>
							</c:otherwise>
						</c:choose>
						</ul>
					</td>
				</tr> --%>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="createCompany();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>&nbsp;&nbsp;
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function createCompany(){
 			$("form[name=companyCreateForm1]").submit();			
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
 	
 		// 주소
		 function addrSearch1(){
			var url ="address.do?action=popCompany";
	 		var properties ="width=500,height=700";
	 		//window.open(url,properties, true);
	 		window.open(url, '', properties, 'scrollbars=yes');
		} 
		
 		
 		
 	// 중복체크
 		function chkName(){
 	
			var meddev_entp_no = document.companyCreateForm1.meddev_entp_no.value;
			var cob_flag_code = document.companyCreateForm1.flagType.value;	
			//alert("meddev_entp_no  " + meddev_entp_no);
 			//alert("cob_flag_code  " + cob_flag_code);
 					
 				if(meddev_entp_no != "" &&  cob_flag_code != ""){
 					
 					 createXMLHttpRequest();
 					   var url = "safetyCompany.do?action=CompanyChk";  
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
 					xmlhttp.send("meddev_entp_no=" + meddev_entp_no + "&cob_flag_code=" + cob_flag_code);
 					    //xmlhttp.send("meddev_entp_no=" + meddev_entp_no + "&sc=" + sc + "&sc2=" + sc2);            
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
 							alert("사용할 수 있는 업허가번호 입니다.");
 					   return false;
 					  }else{			  
 						alert("이미 사용 중인 업허가번호 입니다.");
 						document.companyCreateForm1.meddev_entp_no.value="";
 					   return true;
 					  }
 					 }
 		
	function deleteHistory(articleId,privilegeId){
		if(privilegeId>1){
	 			var method = "get";
	 			var url = "safetyCompany.do?action=deleteCompanyHistory&articleId="+articleId+"&privilegeId="+privilegeId;
	 			var xhr = new XMLHttpRequest();
	 			xhr.open(method,url, true);
	 			xhr.send();
	 			
	 			xhr.onreadystatechange = function(){
	 				
	 				if(xhr.readyState==4 && xhr.status==200){
	 					//alert(xhr.responseText );
	 					alert("삭제성공");
	 					if( xhr.responseText=="1" ||  xhr.responseText==1){
	 						//alert("삭제성공");
	 						
	 						$("#history"+articleId).remove();
	 					}
	 				}
	 				
	 			};
		}
	}
 	</script>
 
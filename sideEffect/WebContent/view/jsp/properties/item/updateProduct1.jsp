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
 	<form name="productCreateForm1" method="post" action="safetyItem.do">
 		<input type="hidden" name="action" value="updateItem"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
 		<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
	 	<table class="bordered fitToParent view_table" >
			<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">공통 코드 관리 - 제품 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업체정보</th>
					<td class="tdBody"><input type="hidden" name="company_id" value="${article.company.id }"/><input type="text" name="company_num" value="${article.company.meddev_entp_no }"/><input type="text" name="company_name" value="${article.company.entp_name }"/><img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="pop1();" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목정보</th>
					<td class="tdBody"><input type="hidden" name="item_id" value="${article.mea_item.id }"/><input type="text" name="mea_class_no" value="${article.mea_item.mea_class_no }"/><input type="text" name="item_class_kor_name" value="${article.mea_item.class_kor_name }"/><img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="pop2();" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목허가번호</th>
					<td class="tdBody">
						<select name="codeType" >
							<c:choose>
								<c:when test="${!empty requestScope.codeType  }">
									<c:forEach var="cType" items="${codeType }">
										<c:choose>
										<c:when test="${cType.id eq article.cob_flag_code}">
											<option selected="selected" value="${cType.id }">${cType.propertyValue }</option>
										</c:when>
										<c:otherwise>
											<option value="${cType.id }">${cType.propertyValue }</option>
										</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
						<input type="text" name="meddev_item_no" value="${article.meddev_item_no }"/>
						<img src="view/style/images/bt_search_chk.jpg" class="hover1" alt="중복검색" onclick="chkName('${article.meddev_item_no }')" /><span id="meddev_item_no"></span></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >제조원(수입의 경우)</th>
					<td class="tdBody"><input type="text" name="manuf_import_name" value="${article.manuf_import_name }"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >형명</th>
					<td>
						<ul id="itemUl">
							<li>
							<table>
								<colgroup>
								<col width="30%" />
								<col width="70%" />
								</colgroup>
									<tr>
										<th colspan="2">형명</th>
									</tr>
									<tr>
							<c:choose>
									<c:when test="${!empty article.item_type }">
										<c:forEach var="item_type" items="${article.item_type }">
											<li class="item_typeLi">
											<tr>
											<td height="20px">${item_type.type_name}</td>
											<%-- <td><input type="button" value="삭제" name="del" onclick="deleteSlave(${product_type.id})"></td> --%>
											<%-- <td id="type${item_type.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" name="del" onclick="deleteType(${item_type.id});"/></td> --%>
											<td id="item_type_${item_type.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" name="del" onclick="deleteItemType('${item_type.id}','<%=privilegeId %>')" /></td>
											</tr>
											</li>
										</c:forEach>
									</c:when>
							</c:choose>
								</table><br><br>
								형명
								<img src="view/style/images/btn_tab02.jpg" class="hover1" alt="추가" id="addItemType" onclick="fnc_addItemType()" />
								</li>
							</ul>
						</td>
				</tr>
					<%-- <td class="tdBody" colspan="3">
					<c:choose>
						<c:when test="${!empty article.product_type }">
							<c:forEach var="product_type" items="${article.product_type }">
									<input type="text" name='name[]' value="${product_type.type_name}"/>
							</c:forEach>
						</c:when>
						<c:otherwise>
								No record
						</c:otherwise>
					</c:choose>	
					<c:choose>
							<c:when test="${!empty article.product_lot }">
								<c:forEach var="product_lot" items="${article.product_lot }">
										<input type="text" name='manuf_lot[]' value="${product_lot.lot_no}"/>
								</c:forEach>
							</c:when>
							<c:otherwise>
									No record
							</c:otherwise>
					</c:choose>
					<input type="button" value="추가" id="" onclick="AddBox()" />
					<div id="ResultBox"></div></td> --%>
				
				
				
				
				
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력사항</th>
					<%-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" name ="lastModified" id="lastModified" value="${article.lastModified }"/></td> --%>
					<th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" name = "activeFrom" id="activeFrom" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody">
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
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" >
						<table class="his_table">
						<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
						<col width="40%" />
						<col width="10%" />
						</colgroup>
						<tr class="itemProprty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.itemHistory }">
								<c:forEach var="itemHistory" items="${article.itemHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${itemHistory.lastModified }"/>
							</td> --%>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${itemHistory.activeFrom }"/>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${reportTypeHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td><c:choose>
								<c:when test="${!empty article.itemHistory }">
										${itemHistory.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty itemHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${itemHistory.historyDescription }</textarea>
									<%-- ${reportTypeHistory.historyDescription } --%>
									</c:when>
								<c:otherwise>
									<textarea disabled="disabled" name="historyDescription"/>no record</textarea>
								</c:otherwise>
								</c:choose>
							</td>
							<td id="history${itemHistory.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="deleteHistory('${itemHistory.id}','<%=privilegeId %>');"/></td>
						</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" >
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
					
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="chkNameCreate('${article.meddev_item_no }');"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	
 	function chkNameCreate(id){
 		if(id==document.productCreateForm1.meddev_item_no.value){
 				 $("form[name=productCreateForm1]").submit();
 			}
 		else{
 			var meddev_item_no = document.productCreateForm1.meddev_item_no.value;
 					
 				//alert("meddev_item_no  " + meddev_item_no);
 			
 					if(meddev_item_no != ""){
 						
 						 createXMLHttpRequest();
 						   var url = "safetyItem.do?action=ProductChk";  
 						  xmlhttp.open("POST",url,true);
 						 xmlhttp.onreadystatechange = function(){
 						    if(xmlhttp.readyState == 4){
 						     if(xmlhttp.status == 200){
 						      if(fReturnCheckId2(xmlhttp.responseText)){
 						    	
 						    	  
 						       return;
 						       }
 						     }
 						    }
 						    }
 						xmlhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
 						xmlhttp.send("meddev_item_no=" + meddev_item_no);
 						    //xmlhttp.send("meddev_item_no=" + meddev_item_no + "&sc=" + sc + "&sc2=" + sc2);            
 						  }
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
 						 
 						 function fReturnCheckId2(resultData){
 						  if(resultData == '0' || resultData == 0){
 							  $("form[name=productCreateForm1]").submit();
 						   return false;
 						  }else{			  
 							alert("이미 사용 중인 품목허가번호입니다.");
 							document.productCreateForm1.meddev_item_no.value="";
 						   return true;
 						  }
 					 }
 	 	
 	 		function createProduct(){
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
 	 	 		var url ="safetyItem.do?action=listCompanyPop";
 	 	 		var properties ="width=500,height=700";
 	 	 		window.open(url, '', properties, 'scrollbars=yes');
 	 	 		
 	 	 	}
 	 		
 	 		// 품목정보POP
 	 		function pop2(){
 	 	 		//alert("품목");
 	 	 		var url ="safetyItem.do?action=listItemCategoryPop";
 	 	 		var properties ="width=500,height=700";
 	 	 		window.open(url, '', properties, 'scrollbars=yes');
 	 	 		
 	 	 	}
 	 		
 	 		function deleteHistory(articleId,privilegeId){
 	 			if(privilegeId>1){
 	 			var method = "get";
 	 			var url = "safetyItem.do?action=deleteItemHistory&articleId="+articleId+"&privilegeId="+<%=privilegeId %>;
 	 			var xhr = new XMLHttpRequest();
 	 			xhr.open(method,url, true);
 	 			xhr.send();
 	 			
 	 			xhr.onreadystatechange = function(){
 	 				
 	 				if(xhr.readyState==4 && xhr.status==200){
 	 					//alert(xhr.responseText );
 	 					alert("삭제성공");
 	 					if( xhr.responseText.trim() =="1"){
 	 						//alert("삭제성공");
 	 						$("#history"+articleId).remove();
 	 					}
 	 				}
 	 				
 	 			};
 	 			}else alert("권한이 없습니다.");
 	}
 	 	// 중복체크
 	 		function chkName(id){
 	 
 	 		if(id==document.productCreateForm1.meddev_item_no.value){
 	 			alert("현재 품목허가번호입니다.");
 	 		}else{
 	 	
 				var meddev_item_no = document.productCreateForm1.meddev_item_no.value;
 	 				
 	 			
 				
 	 				if(meddev_item_no != ""){
 	 					
 	 					 createXMLHttpRequest();
 	 					   var url = "safetyItem.do?action=ProductChk";  
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
 	 					xmlhttp.send("meddev_item_no=" + meddev_item_no);
 	 					    //xmlhttp.send("meddev_item_no=" + meddev_item_no + "&sc=" + sc + "&sc2=" + sc2);            
 	 					  }
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
 	 						document.productCreateForm1.meddev_item_no.value="";
 	 					   return true;
 	 					  }
 	 				 }
 	 	   	 
 	 	   	function fnc_addItemType(){
 	 			var li = document.createElement("li");
 	 			var textBox1 = document.createElement("input");
 	 			var deleteButton = document.createElement("img");
 	 			
 	 			$(li).attr("class", "item_typeLi");
 	 			
 	 			$(textBox1).attr("type", "text");
 	 			$(textBox1).attr("name", "name[]");

 	 			$(deleteButton).attr("src", "view/style/images/btn_tab03.jpg");
 	 			$(deleteButton).attr("class", "hover1");
 	 			$(deleteButton).attr("alt", "삭제");
 	 			
 	 			
 	 			$(li).append(textBox1);
 	 			$(li).append(deleteButton);
 	 			
 	 			
 	 			$("#itemUl").append(li);
 	 			
 	 			var itemTypeLiList = $(".item_typeLi");
 	 			
 	 			var index = $(itemTypeLiList).index(li);
 	 			
 	 			$(deleteButton).bind("click", function(){
 	 				$(li).remove();
 	 			});
 	 			
 	 		}
 	 	   	
 	 	   function deleteItemType(articleId,privilegeId){
 	 		  if(privilegeId>1){
 					var xhr = new XMLHttpRequest();
 					var url = "safetyItem.do?action=deleteItemType&articleId="+articleId+"&privilegeId="+<%=privilegeId %>;
 					var method = "get";
 					var isAsync = true;
 					xhr.open(method, url, isAsync);
 					xhr.send();
 					xhr.onreadystatechange = function(){

 						if(xhr.readyState==4 && xhr.status==200){
 		 					//alert(xhr.responseText );
 		 					alert("삭제성공");
 		 					if( xhr.responseText.trim() =="1"){
 		 						//alert("삭제성공");
 		 						$("#item_type_"+articleId).remove();
 		 					}
 		 				}
 					};
 	 	  		}else alert("권한이 없습니다.");
 				
 			}
 	 	   	 
 	 	</script>
 
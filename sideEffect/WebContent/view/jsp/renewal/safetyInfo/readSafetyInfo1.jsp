<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="patientConditionCreateForm1" enctype="multipart/form-data">
 		<input type="text" name="action" value="readSafetyInfo"/>
 		<input type="text" name="articleId" value="${article.id }"/>
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
					<th colspan="5">구분</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">제목</th>
					<td class="tdBody"  colspan="3">${article.title}</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">내용</th>
					<td class="tdBody"  colspan="3">${article.body }
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">등록일</th>
					<td class="tdBody"  colspan="3">${article.firstRegistered }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">첨부파일</th>
					<td class="tdBody"  colspan="3">
						<c:forEach var="attachment" items="${article.attachments }">
							<div style="border: solid red 1px;" class="buttonised" onclick="download(${attachment.id});">${attachment.originalName}</div><br/>
						</c:forEach>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">원문링크</th>
					<td class="tdBody"  colspan="3"></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">의료기기 정보</th>
					<td class="tdBody"  colspan="3">
					
						<c:forEach var="reference" items="${article.itemList }">
						<table>
							<tr class="itemProperty">
								<th class="tdHead">
									품목코드
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
						
						<c:forEach var="reference" items="${article.itemCategoryList }">
						<table>
							<tr class="itemProperty">
								<th class="tdHead">
									품목코드
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
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<c:if test="${!empty sessionScope.user }">
		 		<img src="view/style/images/btn_edit.jpg" name="update" alt="update" onclick="updateSafetyInfo(${article.id});"/>
		 		<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" onclick="del(${article.id});"/>
		 	</c:if>	
		 		<img src="view/style/images/bt_cancel.jpg" name="cancel" alt="cancel" onclick="toList(1,1,1,'${param.regionId}');"/>
		 	
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=itemCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 			
 		}
 	</script>
 <script type="text/javascript">
  	function updateSafetyInfo(articleId){
  		
  		location.href="safetyInfo.do?action=updateSafetyInfoPage&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("Really ?!")){
  			location.href="safetyInfo.do?action=deleteSafetyInfo&articleId="+articleId;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn, regionId){
  		location.href="safetyInfo.do?action=listSafetyInfo&page=&searchColumn=&searchKeyword=&regionId="+regionId;
  	}
  	
  	function download(id){
  		
  		console.log("download.id : = " + id);
  		location.href="safetyInfo.do?action=download&fileId="+id;
  		
  	}
 </script>
 
 
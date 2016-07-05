<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 
 <%
 	int pg = Function.nullChk(request.getParameter("pg"),1);
 	String sv = Function.nullChk(request.getParameter("sv"),"");
 %>
 
 
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="overflow-x: scroll; overflow-y: scroll">
 	<img src="${titleImg }" />
 	
 	<%@ include file="/view/jsp/sideEffect/search.jsp" %>
 	
 	
 	<table class="bordered" style="width: 3000px; ">
<input type="button" value="등록" onclick="add();"/>
<input type="button" value="xpList" onclick="test22();"/>
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">첨부</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">문서번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">식약처보고일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고자유형</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">대상업체명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업허가번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">분류번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목허가번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					제조원
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					형명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					제조번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					성명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					성별
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					나이
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					최초인지일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					부작용발생일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					종료일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					부작용결과 및 위해정도
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					의료기기원인분류
 				</th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					환자분제코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					의료기기문제코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					구성요소코드
 				</th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					인과관계
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					진행상황
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					기관명
 				</th>
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id});" >
				 		<td >
				 			${item.id }
				 		</td>
				 		
				 		<td>
				 		
				 		</td>
				 		      
				 		<td>
				 		
				 		</td>
				 		<td >
			 				<c:choose>
			 					<c:when test="${!empty countryList}">
			 						<c:forEach var="country" items="${countryList }">
			 							<c:choose>
			 								<c:when test="${country.id eq item.colCountryReportedIn  }">
			 									${country.propertyValue }
			 								</c:when>
			 							</c:choose>
				 					</c:forEach>
				 				</c:when>
			 					<c:otherwise>
			 					</c:otherwise>
			 				</c:choose>
				 		</td>
				 		<td >
				 			${item.reportType.propertyValue }
				 		</td>
				 		<td >
				 			${item.reportDate }
				 		</td>
				 		<td >
				 			${item.reportDate }
				 		</td>
				 		
				 		<td >
							<c:forEach var="reporterType" items="${item.reporterTypes }"  varStatus="i">
								${reporterType.propertyValue }<br>
							</c:forEach>
				 		</td>
				 		<td >
				 			${item.meb_item.manuf_name }
				 		</td>
				 		<td >
						 	${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 			${item.meb_item.mea_class_no }
				 		</td>
				 		<td >
				 			${item.meb_item.grade }
				 		</td>
				 		<td >
				 			${item.meb_item.item_name }		 			
				 		</td>
				 		<td >
							${item.meb_item.meddev_item_seq }
				 		</td>
				 		<td >
				 			${item.manufacturer }
				 		</td>
				 		<td >
				 			${item.meb_item.shape }
				 		</td>
				 		<td >
						 	${item.id }
				 		</td>
				 		<td >
				 			${item.patient_name }
				 		</td>
				 		<td >
				 			${item.gender.propertyValue }
				 		</td>
				 		<td >
				 	   		${item.patient_age }
				 		</td>
				 		<td >
				 			${item.side_effect_first_date }
				 		</td>
				 		<td >
				 			${item.side_effect_generation_date }
				 		</td>
				 		<td >
				 			${item.side_effect_last_date }
				 		</td>
				 		<td >
				 			<c:forEach var="result" items="${item.sideeffectResult }"  varStatus="i">
								${result.property_value }<br>
							</c:forEach>
				 		</td>
				 		<td >
				 			<c:forEach var="cause" items="${item.sideeffectCause }"  varStatus="i">
								${cause.property_value }<br>
							</c:forEach>
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 	
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.causality.propertyValue }
				 		</td>
				 		<td >
				 			${item.reportStatus.propertyValue }
				 		</td>
				 		<td >
				 	
				 		</td>
				 	</tr>
				 </c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr>
		 			<td>no data</td>
				</tr>
			</c:otherwise>
		 </c:choose>
		 
	
 	</table>
 	
 	
 	<div>
 		<input type="button" value="등록" onclick="add();"/>
 		<input type="button" value="엑셀로내리기" onclick="goExcel();"/>
 	</div>
 	
 		 <div class="paging">
 			${requestScope.pageString }
 		</div>
 		
 		
 	
 	</div>
 
 
 <script type="text/javascript" >
 	function read(id){
 		try{
 			//alert(id);
 			location.href = "sideEffectReport.do?action=read&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function add(){
 		location.href = "sideEffectReport.do?action=createPage";
 	}
 	
 	function search(){
 		var frm = document.allSearchForm1;
 		//alert(frm);
 		frm.submit();
 		
 	}
 	
 	function EnterDown(){
		if(event.keyCode == 13) {
			search();
		}
 	}
 	
 	//엑셀로 내리기
 	function goExcel(){
 		location.href="?action=goExcel&pg="+<%=pg%>+"<%=sv%>";
 	}
 	function test22(){
 		location.href="?action=xpSideEffectList";
 	}
 </script>
 
 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@include file="/view/config/jqUI.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript">

var addCount = -1; 

function CallSelect(MakeCnt, ctrlName, CtrlLoc, CtrlLoc2)
{
	addCount = addCount + 1;
    var eleName = document.getElementById(CtrlLoc);
    var eleName2 = document.getElementById(CtrlLoc2);
    
    mkSelect(MakeCnt, ctrlName, eleName, eleName2);
    
 }


function mkSelect(cnt, sltName, objLoc, objLoc2)
{
 var AppendSltList = objLoc;
 var AppendSltList2 = objLoc2;
 var optionsText = new Array("구분", "보고구분", "보고자 유형", "상호명", "대표자 성명", "담당자명", "전화", "FAX", "EMAIL", "주소", "대상업체명", "업허가번호", "품목명", "분류번호", "등급", "품목허가번호", "제조국", "제조원", "품목명", "형명", "제조번호", "성명", "성별", "나이", "기타 특이사항", "부작용 결과 및 위해정도", "부작용 원인 분류", "환자문제코드", "의료기기문제코드", "구성요소코드", "경과 및 후속조치", "기관명", "연락처", "기타사항");
 var optionsValue = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34);
 var optionsValue2 = new Array("AND", "OR");

 var objSlt = new Array(cnt);
 var objOpt = null;

 var objSlt2 = new Array(cnt);
 var objOpt2 = null;
 
 var textBox = new Array(cnt);
 
 
 //선택상자 생성
 for(var i = 0; i < cnt; i++)
 {
	 	var container = $("#container");
	    var div = document.createElement("div");
	    textBox[i] = document.createElement("input");
	    textBox[i].id="sv_0";
	    textBox[i].name="sv_0";
	    textBox[i].type="text";
	    textBox[i].size="70";
	    textBox[i].value="";
	    textBox[i].innerText="";
	    
        objSlt[i] = document.createElement("select");
        objSlt[i].id = sltName + i;
        objSlt[i].name = sltName + i;

        objSlt2[i] = document.createElement("select");
        objSlt2[i].id = "logicalOperator_" + i;
        objSlt2[i].name = "logicalOperator_" + i;
      
        
        var objOpt = document.createElement("option");   
        objOpt.value = "";
        objOpt.innerText = "               -선택-";
        objSlt[i].appendChild(objOpt);
        
        var objOpt2 = document.createElement("option");   
        objOpt2.value = "";
        objOpt2.innerText = " -선택-  ";
        objSlt2[i].appendChild(objOpt2);
        
        for(var j = 0; j < optionsValue.length; j++)
        {
             objOpt = document.createElement("option");
             objOpt.value  = optionsValue[j];
             objOpt.innerText = optionsText[j];
             objSlt[i].appendChild(objOpt);
        }
        for(var j = 0; j < optionsValue2.length; j++)
        {
             objOpt2 = document.createElement("option");
             objOpt2.value  = optionsValue2[j];
             objOpt2.innerText = optionsValue2[j];
             objSlt2[i].appendChild(objOpt2);
        }
        
        AppendSltList.appendChild(objSlt[i]);
        AppendSltList.appendChild(objSlt2[i]);
        AppendSltList.appendChild(textBox[i]);
        	
        $(div).append(objSlt[i]);
        $(container).append(div);
        
        //$(svInput).attr("type", "text");
		//$(svInput).attr("name", "sv_"+0);
		//$(svInput).attr("size", "70");
		//svInput.type="text";
		//svInput.name="sv_0";
		//svInput.size="70";
		//div.appendChild(svInput);
		
        //$(div).append(svInput);
        $(div).append(textBox[i]);
        $(container).append(div);
		
		$(div).append(objSlt2[i]);
        $(container).append(div);
        

        
   }


  }


function details_search(){
	var frm1 = document.details_search_form1;
	//frm1.count.value=addCount;
	//frm1.submit();
	
	var scLength = frm1.sc_0.length;
	var svLength = $("input[name=sv_0]").length;  //frm1.sv_0.length;
	var logicalOperatorLength = frm1.logicalOperator_0.length;
	
	//alert("scLength:  "+scLength+"  svLength  "+svLength+"  logicalOperatorLength  "+logicalOperatorLength);
	//alert("scLength:  "+scLength+"  logicalOperatorLength  "+logicalOperatorLength);
	
	var scValue = 0;
	var svValue = 0;
	var logicalOperatorValue = 0;
	for(var i=0; i<scLength; i++){
		scValue += ","+frm1.sc_0[i].value;
		svValue += ","+frm1.sv_0[i].value;
		logicalOperatorValue += ","+frm1.logicalOperator_0[i].value;
	}
	
	
	//opener.document.xp_details_search_form1.sc_0.value = frm1.sc_0.value;
	opener.document.xp_details_search_form1.sc_0.value = scValue;
	opener.document.xp_details_search_form1.sv_0.value = svValue;
	opener.document.xp_details_search_form1.logicalOperator_0.value = logicalOperatorValue;
	
	$( opener.location ).attr( "href" , "javascript:fn_details_search();");
	
	window.close();

}

 window.onload = function() {
 CallSelect(1, "sc_", "SltBoard", "SltBoard2");   
}

</script>

<title>Insert title here</title>
</head>
<body>

<form name="details_search_form1" method="post" action="sideEffectReport.do">
	<input type="hidden" name="action" value="list" />
	<input type="hidden" name="count" value="0" />
	<input type="hidden" name="sc_0" value="0" />
	<input type="hidden" name="sv_0" value="0" />
	<input type="hidden" name="logicalOperator_0" value="0" />
	
	
	<div id="container">
		<div id="SltBoard">
		</div>
	</div>
	
</form>
			<input type="button" value="추가" onclick="CallSelect(1,'sc_', 'SltBoard', 'SltBoard2')" id="add"/>
			<input type="button" value="검색" onclick="details_search()" id="click"/>
</body>
</html>
<%@page import="com.sun.xml.internal.bind.v2.runtime.Location"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ include file="/view/config/config.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<html>
<head>
<script>

   window.onload = function () {
	   fn_load();
   };
   
 function fn_load(){
	var frm1 = document.select;
	frm1.action="statistics.do?action=selectCodeStatistics";
	frm1.submit();
 }  

</script>
</head>
<body>

<%! 

// Dataset value
public String  dsGet(DataSet ds, int rowno, String colid) throws Exception
{
	String value;
	value = ds.getString(rowno,colid);
	if( value == null )
		return "";
	else
		return value;
} 

%>
<form name="select" method="post" action="">
<%
// PlatformData 
PlatformData o_xpData = new PlatformData();
	
int nErrorCode = 0;
String strErrorMsg = "START";

// HttpPlatformRequest
HttpPlatformRequest pReq = new HttpPlatformRequest(request);
	
// XML parsing
pReq.receiveData();

// PlatformData
PlatformData i_xpData = pReq.getData();

// Get
VariableList in_vl = i_xpData.getVariableList();
String popName = in_vl.getString("varCodeName");
String popGB = in_vl.getString("varGubun");
String fmYY = in_vl.getString("varfmYY");
String fmMM = in_vl.getString("varfmMM");
String toYY = in_vl.getString("vartoYY");
String toMM = in_vl.getString("vartoMM");
String fmDate = fmYY+fmMM;
String toDate = toYY+toMM;

ArrayList<String> getIdArr =  new ArrayList<String>();
ArrayList<String> getNameArr =  new ArrayList<String>();

%>

	<input type="text" name="popName" value=<%=popName %>>
	<input type="text" name="popGB" value=<%=popGB %>>
	<input type="text" name="fmYY" value=<%=fmYY %>>
	<input type="text" name="fmMM" value=<%=fmMM %>>
	<input type="text" name="toYY" value=<%=toYY %>>
	<input type="text" name="toMM" value=<%=toMM %>>


<% 


DataSet ds = i_xpData.getDataSet("ds_codeList");

try {	
	for(int i=0; i<ds.getRowCount(); i++) {
		int rowType = ds.getRowType(i);
		if( rowType == DataSet.ROW_TYPE_UPDATED ) {
			String getId = dsGet(ds, i, "ID");
			String getName = dsGet(ds,i,"NAME" );
			System.out.println("getId ==== " + getId);
			System.out.println("getName ==== " + getName);
			getIdArr.add(getId);
			getNameArr.add(getName);			
%>

	<input type="text" name="getId" value=<%=getId %>>
	<input type="text" name="getName" value=<%=getName %> > <br>

<% 
		}
	}
		
} catch (Throwable th) {
	nErrorCode = -1;
	strErrorMsg = th.getMessage();
}

//VariableList 참조
VariableList varList = o_xpData.getVariableList();
		
//VariableList에 값을 직접 추가
varList.add("ErrorCode", nErrorCode);
varList.add("ErrorMsg", strErrorMsg);

//HttpServletResponse를 이용하여 HttpPlatformResponse 생성
HttpPlatformResponse pRes = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
pRes.setData(o_xpData);


//데이터 송신
pRes.sendData();

%>


</form>
</body>
</html>


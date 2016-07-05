<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="report.SimpleSideeffectReport" %>
<%@ page import="common.Function"%>
<%@ page import="java.util.ArrayList"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	
	ArrayList<String> codeArr = (ArrayList)request.getAttribute("codeArr"); 
	ArrayList<String> dateArr = (ArrayList)request.getAttribute("dateArr"); 
	ArrayList<String> cntArr =  (ArrayList)request.getAttribute("cntArr"); 
	ArrayList<String> nameArr = (ArrayList)request.getAttribute("nameArr"); 
	ArrayList<String> depthLevelArr = (ArrayList)request.getAttribute("depthLevelArr"); 
	
	
	System.out.println("codeArr======" + codeArr.size());
	
	try {	
		
		DataSet ds = new DataSet("ds_data0");
		  ds.addColumn("CODE" 			  			,DataTypes.STRING	,(short)256  );
		  ds.addColumn("YEAR"   					,DataTypes.STRING	,(short)255  );
		  ds.addColumn("VALUE"    					,DataTypes.INT   	,(short)10   );
		  ds.addColumn("NAME"    					,DataTypes.STRING   ,(short)900   );
		  ds.addColumn("DEPTH_LEVEL"    			,DataTypes.STRING   ,(short)900   );
		  
		  for(int i=0; i <codeArr.size(); i++) {
			  
			int row = ds.newRow();
			ds.set(row ,"CODE"    				,codeArr.get(i) );
			ds.set(row ,"YEAR"    				,dateArr.get(i) );
			ds.set(row ,"VALUE"  				,cntArr.get(i));
		  	ds.set(row ,"NAME"  				,nameArr.get(i));
		  	ds.set(row ,"DEPTH_LEVEL"  			,depthLevelArr.get(i));
		  	
		  }

		o_xpData.addDataSet(ds);
		 
		nErrorCode = 0;
		strErrorMsg = "SUCC";
			
	} catch (Throwable th) {
		nErrorCode = -1;
		strErrorMsg = th.getMessage();

	}
	
	// VariableList 
	VariableList varList = o_xpData.getVariableList();
			
	strErrorMsg=sTest;
			
	//Variable--> VariableList
	varList.add("ErrorCode", nErrorCode);
	varList.add("ErrorMsg", strErrorMsg);

	// HttpPlatformResponse 
	HttpPlatformResponse pRes = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
	pRes.setData(o_xpData);

	// Send data
	pRes.sendData();
	
%>


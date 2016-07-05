<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="properties.SimpleSideEffectReportType" %>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	
	List codeList = (List)request.getAttribute("codeList");
	
	System.out.println("codeListSize======" + codeList.size());
	
	try {	
		
		DataSet ds = new DataSet("ds_codeList");
		  ds.addColumn("ID" 			  			,DataTypes.INT		,(short)19   );
		  ds.addColumn("NAME"   					,DataTypes.STRING	,(short)255  );
		  ds.addColumn("DEPTH_LEVEL"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("CHECK_YN"    				,DataTypes.STRING   ,(short)10   );
		  ds.addColumn("SUB_NAME"    				,DataTypes.STRING   ,(short)400  );
		 
		  for(int i=0; i <codeList.size(); i++) {
			  SimpleSideEffectReportType bean = (SimpleSideEffectReportType)codeList.get(i);
			  
			int row = ds.newRow();
			ds.set(row ,"ID"    					,(bean.getId())   );
			ds.set(row ,"NAME"    					,Function.nullChk(bean.getPropertyValue(),"") );
			ds.set(row ,"DEPTH_LEVEL"  				,1);
		  	ds.set(row ,"CHECK_YN"  				,"0");
		  	ds.set(row ,"SUB_NAME"  				,"");
		  	
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


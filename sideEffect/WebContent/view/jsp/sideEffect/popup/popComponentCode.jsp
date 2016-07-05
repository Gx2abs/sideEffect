<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="properties.SimpleComponentCode" %>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";

	List componentCodeList = (List)request.getAttribute("componentCodeList");
	
	System.out.println("componentCodeListSize======" + componentCodeList.size());
	
	try {	
		
		DataSet ds = new DataSet("ds_componentCode");
		  ds.addColumn("ID" 			  			,DataTypes.INT		,(short)10   );
		  ds.addColumn("NAME"   					,DataTypes.STRING	,(short)400   );
		  ds.addColumn("FDA_CODE"			 		,DataTypes.INT		,(short)10  );
		  ds.addColumn("FDA_SOURCE_DEFINITION"  	,DataTypes.STRING	,(short)600  );
		  ds.addColumn("FDA_SOURCE_DEFINITION_KOR"  ,DataTypes.STRING	,(short)600  );
		  ds.addColumn("NCIT_DEFINITION"   			,DataTypes.STRING	,(short)400  );
		  ds.addColumn("NCIT_DEFINITION_KOR"   		,DataTypes.STRING	,(short)400  );
		  ds.addColumn("PARENT_ID" 					,DataTypes.INT  	,(short)10 	 );
		  ds.addColumn("DEPTH_LEVEL"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("FDA_SOURCE_PT_KOR" 			,DataTypes.STRING	,(short)500  );
		  ds.addColumn("NCI_CODE"    				,DataTypes.STRING   ,(short)10   );
		  
		  ds.addColumn("CHECK_YN"    				,DataTypes.STRING   ,(short)10   );
		  
		  for(int i=0; i <componentCodeList.size(); i++) {
			SimpleComponentCode bean = (SimpleComponentCode)componentCodeList.get(i);
			  
			int row = ds.newRow();
			ds.set(row ,"ID"    					,(bean.getId())   );
			ds.set(row ,"NAME"    					,Function.nullChk(bean.getName(),"") );
		  	ds.set(row ,"FDA_CODE"  				,Function.nullChk(bean.getFdaCode(),"") );
		  	ds.set(row ,"FDA_SOURCE_DEFINITION"     ,Function.nullChk(bean.getFdaSourceDefinition(),"")   );
		  	ds.set(row ,"FDA_SOURCE_DEFINITION_KOR" ,Function.nullChk(bean.getFdaSourceDefinitionKor(),"")    );
		  	ds.set(row ,"NCIT_DEFINITION"     		,Function.nullChk(bean.getNcitDefinition(),"")    );
		  	ds.set(row ,"NCIT_DEFINITION_KOR"    	,Function.nullChk(bean.getNcitDefinitionKor(),"")  );
		  	ds.set(row ,"PARENT_ID"  				,Function.nullChk(bean.getParentId(),0) );
		  	ds.set(row ,"FDA_SOURCE_PT_KOR"  		,Function.nullChk(bean.getFdaSourcePtKor(),"") );
		  	ds.set(row ,"DEPTH_LEVEL"  				,Function.nullChk(bean.getDepthLevel(),0));
		  	ds.set(row ,"NCI_CODE"  				,Function.nullChk(bean.getNciCode(),""));
		  	ds.set(row ,"CHECK_YN"  				,"0");
		  	
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


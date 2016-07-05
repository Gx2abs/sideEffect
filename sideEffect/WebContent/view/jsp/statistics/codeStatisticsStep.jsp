<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="report.SimpleSideeffectReport" %>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	
	List<SimpleSideeffectReport> selectList = (List<SimpleSideeffectReport>)request.getAttribute("selectList");
	String step = Function.nullChk(request.getAttribute("curStep").toString(),"0");

	
	try {	
		
		DataSet ds = new DataSet("ds_step"+step);
		  ds.addColumn("ID" 			  			,DataTypes.INT		,(short)10  );
		  ds.addColumn("CODE_NAME"   				,DataTypes.STRING	,(short)500  );
		  ds.addColumn("CODE_SUM"    				,DataTypes.INT   	,(short)10   );
		  
		  for(int i=0; i <selectList.size(); i++) {
			  
			int row = ds.newRow();
			
			String codeName = selectList.get(i).getVarValue1(); 
			Integer groupId = Function.nullChk(selectList.get(i).getVarIntId(), 0);
			
			if(groupId > 0 ) {
				ds.set(row ,"ID"    				,selectList.get(i).getVarIntId());
			} else {
				ds.set(row ,"ID"    				,selectList.get(i).getVarId());
			}
			
			String sub_name = Function.nullChk(selectList.get(i).getVarValue2(),"");
			String name = Function.nullChk(selectList.get(i).getVarValue1(),"");
			if(!sub_name.equals("")) {
				name = sub_name+" "+name;
			}
			
		  	ds.set(row ,"CODE_NAME"  			,name);
		  	ds.set(row ,"CODE_SUM"  			,selectList.get(i).getVarCnt());
		  	
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


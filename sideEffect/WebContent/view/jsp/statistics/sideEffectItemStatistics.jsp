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
	
	List codeList = (List)request.getAttribute("ItemSideeffectList");
	String totalCnt =request.getAttribute("ItemSideeffectCnt").toString();
	String moreYN = request.getAttribute("moreYN").toString();
	System.out.println("codeList.size()======" + codeList.size());
	String ds_name = "";
	if (moreYN.equals("N")) {
		ds_name = "ds_item";
	} else {
		ds_name = "ds_item_more";
	}
	
	try {	

		DataSet ds = new DataSet(ds_name);
		  ds.addColumn("ITEM_CATEGORY_ID" 		,DataTypes.INT		,(short)10   );
		  ds.addColumn("MEA_CLASS_NO"   		,DataTypes.STRING	,(short)255  );
		  ds.addColumn("ITEM_ID"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("COB_FLAG_CODE"    		,DataTypes.INT  	,(short)10  );
		  ds.addColumn("COB_FLAG_NAME"    		,DataTypes.STRING   ,(short)255  );
		  ds.addColumn("MEDDEV_ITEM_NO" 		,DataTypes.STRING	,(short)255  );
		  ds.addColumn("CLASS_KOR_NAME"   		,DataTypes.STRING	,(short)255  );
		  ds.addColumn("COMPANY_ID"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("ENTP_NAME"    			,DataTypes.STRING   ,(short)255  );
		  ds.addColumn("GET_SUM"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("GET_SUM_C"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("GET_SUM_M"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("GET_SUM_P"    			,DataTypes.INT   	,(short)10   );
		  ds.addColumn("MEDDEV_ITEM_NO_NUM1" 	,DataTypes.INT		,(short)10  );
		  ds.addColumn("MEDDEV_ITEM_NO_NUM2" 	,DataTypes.INT		,(short)10  );
		
		  int row = 0;
		  for(int i=0; i <codeList.size(); i++) {
			  
			SimpleSideeffectReport bean = (SimpleSideeffectReport)codeList.get(i);
			row = ds.newRow();
			
			// 품목허가번호 정렬때문에 추가!! 
			Integer meddevItemNo1 = 0;
			Integer meddevItemNo2 = 0;

			meddevItemNo1 = Integer.parseInt(bean.getVarValue3().trim().substring(0, 2));
			meddevItemNo2 = Integer.parseInt(bean.getVarValue3().trim().substring(3, bean.getVarValue3().trim().length()));
			
			ds.set(row ,"ITEM_CATEGORY_ID"    		,bean.getVarId());
			ds.set(row ,"MEA_CLASS_NO"    			,bean.getVarValue1());
			ds.set(row ,"ITEM_ID"    				,bean.getMeb_item_id());
			ds.set(row ,"COB_FLAG_CODE"    			,0);
			ds.set(row ,"COB_FLAG_NAME"    			,bean.getVarValue2());
			ds.set(row ,"MEDDEV_ITEM_NO"    		,bean.getVarValue3().trim());
			ds.set(row ,"CLASS_KOR_NAME"    		,bean.getVarValue4());
			ds.set(row ,"COMPANY_ID"    			,0);
			ds.set(row ,"ENTP_NAME"  				,bean.getVarValue5());
		  	ds.set(row ,"GET_SUM"  					,bean.getVarCnt());
		  	ds.set(row ,"GET_SUM_C"  				,bean.getVarCnt1());
		  	ds.set(row ,"GET_SUM_M"  				,bean.getVarCnt2());
		  	ds.set(row ,"GET_SUM_P"  				,(bean.getVarCnt3()));
		  	ds.set(row ,"MEDDEV_ITEM_NO_NUM1"  		,meddevItemNo1);
		  	ds.set(row ,"MEDDEV_ITEM_NO_NUM2"  		,meddevItemNo2);
		  	
		  }
		  
		  
		 System.out.println("totalCnt=========" + totalCnt);
		  
		o_xpData.addDataSet(ds);
		 
		nErrorCode = 0;
		strErrorMsg = totalCnt;//"SUCC";
			
	} catch (Throwable th) {
		nErrorCode = -1;
		strErrorMsg = th.getMessage();

	}
	
	// VariableList 
	VariableList varList = o_xpData.getVariableList();
			
	//Variable--> VariableList
	varList.add("ErrorCode", nErrorCode);
	varList.add("ErrorMsg", strErrorMsg);

	// HttpPlatformResponse 
	HttpPlatformResponse pRes = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
	pRes.setData(o_xpData);

	// Send data
	pRes.sendData();
	
%>


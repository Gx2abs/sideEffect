<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="report.SimpleSideeffectReport" %>
<%@ page import="safety.renewal.sgi.item.SimpleItem1"%>
<%@ page import="safety.renewal.sgi.category.SimpleItemCategory"%>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	
	List codeList = (List)request.getAttribute("codeList");
	SimpleItem1 item = (SimpleItem1) request.getAttribute("simpleitem1");
	SimpleItemCategory itemCategory = (SimpleItemCategory) request.getAttribute("itemCategory");
	String tabGB = request.getAttribute("tabGB").toString();

	System.out.println("codeList.size()======" + codeList.size());


	try {	
		DataSet ds = null;
		// 품목허가번호별
		if(tabGB.equals("I")) {
			 ds = new DataSet("ds_item_code");
		} 
		// 품목별
		else {
			 ds = new DataSet("ds_category_code");
		}
		 
		  ds.addColumn("NUM" 						,DataTypes.INT		,(short)10   );
		  ds.addColumn("CODE_ID" 					,DataTypes.INT		,(short)10   );
		  ds.addColumn("FDA_CODE"   				,DataTypes.STRING	,(short)255  );
		  ds.addColumn("FDA_SOURCE_DEFINITION_KOR"  ,DataTypes.STRING	,(short)255  );
		  ds.addColumn("SUM_COUNT"    				,DataTypes.INT   	,(short)10   );

		  for(int i=0; i <codeList.size(); i++) {			  
			SimpleSideeffectReport bean = (SimpleSideeffectReport)codeList.get(i);
			int row = ds.newRow();
			ds.set(row ,"NUM"    					,i);
			ds.set(row ,"CODE_ID"    				,bean.getVarId());
			ds.set(row ,"FDA_CODE"    				,bean.getVarValue1());
			ds.set(row ,"FDA_SOURCE_DEFINITION_KOR" ,bean.getVarValue2()+" / "+bean.getVarValue3());
			ds.set(row ,"SUM_COUNT"    				,bean.getVarCnt());
		  }

		  
		  DataSet ds_view = null;  
		// 품목허가번호별
		if(tabGB.equals("I")) {
			ds_view = new DataSet("ds_item_view");
		} 
		// 품목별
		else {
			ds_view = new DataSet("ds_category");
		}
		  ds_view.addColumn("ITEM_ID" 						,DataTypes.INT		,(short)10   );
		  ds_view.addColumn("CLASS_KOR_NAME"   				,DataTypes.STRING	,(short)255  );
		  ds_view.addColumn("MEA_CLASS_NO"  				,DataTypes.STRING	,(short)255  );
		  ds_view.addColumn("GRADE"    						,DataTypes.STRING   ,(short)255  );
		  ds_view.addColumn("ENTP_NAME"    					,DataTypes.STRING   ,(short)255  );
		  ds_view.addColumn("MEDDEV_ITEM_NO"    			,DataTypes.STRING   ,(short)255  );
		  ds_view.addColumn("ITEM_CATEGORY_ID"    			,DataTypes.INT   	,(short)10   );
		  
		  int row_viw = ds_view.newRow();
		  
		  if(tabGB.equals("I")) {
			  ds_view.set(row_viw ,"ITEM_ID"    				,item.getId());
			  ds_view.set(row_viw ,"CLASS_KOR_NAME"    			,Function.nullChk(item.getMea_item().getClass_kor_name(),""));
			  ds_view.set(row_viw ,"MEA_CLASS_NO"				,Function.nullChk(item.getMea_item().getMea_class_no(),"") + " / " + item.getMea_item().getGrade());
			  ds_view.set(row_viw ,"GRADE"    					,Function.nullChk(item.getMea_item().getGrade(),""));
			  ds_view.set(row_viw ,"ENTP_NAME"    				,item.getCobFlagType().getPropertyValue() + " " +item.getMeddev_item_no() + "호 / " + Function.nullChk(item.getCompany().getEntp_name(),""));
			  ds_view.set(row_viw ,"MEDDEV_ITEM_NO"    			,item.getCobFlagType().getPropertyValue() + " " +item.getMeddev_item_no() + "호 / " );
			  ds_view.set(row_viw ,"ITEM_CATEGORY_ID"    		,Function.nullChk(item.getItem_category_number(),0));
		  } else {
			  ds_view.set(row_viw ,"ITEM_ID"    				,0);
			  ds_view.set(row_viw ,"CLASS_KOR_NAME"    			,Function.nullChk(itemCategory.getClass_kor_name(),""));
			  ds_view.set(row_viw ,"MEA_CLASS_NO"				,Function.nullChk(itemCategory.getMea_class_no(),"") + " / " + Function.nullChk(itemCategory.getGrade(),""));
			  ds_view.set(row_viw ,"GRADE"    					,Function.nullChk(itemCategory.getGrade(),""));
			  ds_view.set(row_viw ,"ENTP_NAME"    				,"");
			  ds_view.set(row_viw ,"MEDDEV_ITEM_NO"    			,"");
			  ds_view.set(row_viw ,"ITEM_CATEGORY_ID"    		,itemCategory.getId());
		  }
		 
		  
		o_xpData.addDataSet(ds);
		o_xpData.addDataSet(ds_view);
		 
		nErrorCode = 0;
		strErrorMsg = "SUCC";
			
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


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
	
	SimpleItem1 item = (SimpleItem1) request.getAttribute("simpleitem1");
	//SimpleItemCategory itemCategory = (SimpleItemCategory) request.getAttribute("itemCategory");
	String tabGB = request.getAttribute("tabGB").toString();
	SimpleSideeffectReport terget = (SimpleSideeffectReport) request.getAttribute("tergetSideeffect");
	SimpleSideeffectReport allTerget = (SimpleSideeffectReport) request.getAttribute("allTergetSideeffect");
	
	System.out.println("tabGB===" + tabGB);


	try {	
		DataSet ds = new DataSet("ds_totalFrequency");
		 
		  ds.addColumn("NUM" 						,DataTypes.INT		,(short)10   );
		  ds.addColumn("NAME"   					,DataTypes.STRING	,(short)255  );
		  ds.addColumn("TARGET"  					,DataTypes.INT		,(short)10  );
		  ds.addColumn("ALL_OTHER"    				,DataTypes.INT   	,(short)10   );

		  int row = ds.newRow();
			ds.set(row ,"NUM"    					,1);
			ds.set(row ,"NAME"    					,"Target Device");
			ds.set(row ,"TARGET"    				,terget.getVarCnt1());
			ds.set(row ,"ALL_OTHER"    				,terget.getVarCnt2());
			
			row = ds.newRow();
			ds.set(row ,"NUM"    					,2);
			ds.set(row ,"NAME"    					,"All Other Device");
			ds.set(row ,"TARGET"    				,allTerget.getVarCnt1());
			ds.set(row ,"ALL_OTHER"    				,allTerget.getVarCnt2());
 
		 
		  
		  DataSet ds_view = new DataSet("ds_frequency"); 
		  
		  ds_view.addColumn("ITEM_CATEGORY_ID" 		,DataTypes.INT		,(short)10   );
		  ds_view.addColumn("MEA_CLASS_NO"   		,DataTypes.STRING	,(short)255  );
		  ds_view.addColumn("ITEM_ID"    			,DataTypes.INT   	,(short)10   );
		  ds_view.addColumn("COB_FLAG_CODE"    		,DataTypes.INT  	,(short)10  );
		  ds_view.addColumn("COB_FLAG_NAME"    		,DataTypes.STRING   ,(short)255  );
		  ds_view.addColumn("MEDDEV_ITEM_NO" 		,DataTypes.STRING	,(short)255  );
		  ds_view.addColumn("CLASS_KOR_NAME"   		,DataTypes.STRING	,(short)255  );
		  ds_view.addColumn("COMPANY_ID"    		,DataTypes.INT   	,(short)10   );
		  ds_view.addColumn("ENTP_NAME"    			,DataTypes.STRING   ,(short)255  );
		  ds_view.addColumn("SUM_ROR"    			,DataTypes.FLOAT   	,(short)10   );
		  ds_view.addColumn("SUM_PRR"    			,DataTypes.FLOAT   	,(short)10   );
		  ds_view.addColumn("MEDDEV_ITEM_NO_NUM1" 	,DataTypes.INT		,(short)10  );
		  ds_view.addColumn("MEDDEV_ITEM_NO_NUM2" 	,DataTypes.INT		,(short)10  );
		  
		  int row_viw = ds_view.newRow();
		  
		  if(tabGB.equals("I")) {
				
			// 품목허가번호 정렬때문에 추가!! 
			Integer meddevItemNo1 = 0;
			Integer meddevItemNo2 = 0;
			double item_ror = 0.0;
			double item_prr = 0.0;
			double targetVar1 = (double) terget.getVarCnt1();
			double targetVar2 = (double) terget.getVarCnt2();
			double allTargetVar1 = (double) allTerget.getVarCnt1();
			double allTargetVar2 = (double) allTerget.getVarCnt2();
			
			meddevItemNo1 = Integer.parseInt(item.getMeddev_item_no().trim().substring(0, 2));
			meddevItemNo2 = Integer.parseInt(item.getMeddev_item_no().trim().substring(3, item.getMeddev_item_no().trim().length()));
			
			
			// 보고오즈비계산
			if(terget.getVarCnt2() == 0) {
				item_ror = targetVar1;
			} else {
				item_ror = targetVar1/targetVar2;
			}
			
			if(allTerget.getVarCnt2() == 0){
				if (allTerget.getVarCnt1() != 0) item_ror = (item_ror/allTargetVar1);
			} else {
				if (allTerget.getVarCnt1() != 0) item_ror = item_ror/(allTargetVar1/allTargetVar2);
			}
			// 보고분율비계산
			if((terget.getVarCnt1()+terget.getVarCnt2()) == 0) {
				item_prr = targetVar1;
			} else {
				item_prr = targetVar1/(targetVar1+targetVar2);
			}
			if((allTerget.getVarCnt2() != 0) && (allTerget.getVarCnt1() != 0)){
				 item_prr = item_prr/allTargetVar1*(allTargetVar1+allTargetVar2);
			}
			
			ds_view.set(row_viw ,"ITEM_CATEGORY_ID"    		,item.getItem_category_number());
			ds_view.set(row_viw ,"MEA_CLASS_NO"    			,item.getMea_item().getMea_class_no());
			ds_view.set(row_viw ,"ITEM_ID"    				,item.getId());
			ds_view.set(row_viw ,"COB_FLAG_CODE"    		,item.getCob_flag_code());
			ds_view.set(row_viw ,"COB_FLAG_NAME"    		,item.getCobFlagType().getPropertyValue());
			ds_view.set(row_viw ,"MEDDEV_ITEM_NO"    		,item.getMeddev_item_no().trim());
			ds_view.set(row_viw ,"CLASS_KOR_NAME"    		,item.getMea_item().getClass_kor_name());
			ds_view.set(row_viw ,"COMPANY_ID"    			,item.getCompany().getId());
			ds_view.set(row_viw ,"ENTP_NAME"  				,item.getCompany().getEntp_name());
			ds_view.set(row_viw ,"SUM_ROR"	  				,item_ror);
			ds_view.set(row_viw ,"SUM_PRR"  				,item_prr);
			ds_view.set(row_viw ,"MEDDEV_ITEM_NO_NUM1"  	,meddevItemNo1);
			ds_view.set(row_viw ,"MEDDEV_ITEM_NO_NUM2"  	,meddevItemNo2);

		  } else {
			  /*
			  ds_view.set(row_viw ,"ITEM_ID"    				,0);
			  ds_view.set(row_viw ,"CLASS_KOR_NAME"    			,Function.nullChk(itemCategory.getClass_kor_name(),""));
			  ds_view.set(row_viw ,"MEA_CLASS_NO"				,Function.nullChk(itemCategory.getMea_class_no(),"") + " / " + Function.nullChk(itemCategory.getGrade(),""));
			  ds_view.set(row_viw ,"GRADE"    					,Function.nullChk(itemCategory.getGrade(),""));
			  ds_view.set(row_viw ,"ENTP_NAME"    				,"");
			  ds_view.set(row_viw ,"MEDDEV_ITEM_NO"    			,"");
			  ds_view.set(row_viw ,"ITEM_CATEGORY_ID"    		,itemCategory.getId());
			  */
		  }
		  
		  
		  DataSet ds_test = new DataSet("ds_test");
		  ds_test.addColumn("NUM" 						,DataTypes.INT		,(short)10   );
		  ds_test.addColumn("1.경미"   					,DataTypes.STRING	,(short)255  );
		  ds_test.addColumn("2.중증"   					,DataTypes.STRING	,(short)255  );
			
		  int test_row = ds_test.newRow();
		  
		  ds_test.set(test_row ,"NUM"    		,1);
		  ds_test.set(test_row ,"1.경미"    		,"1.아주낮음");
		  ds_test.set(test_row ,"2.중증"    		,"1");
		  
		  test_row = ds_test.newRow();
		  ds_test.set(test_row ,"NUM"    		,2);
		  ds_test.set(test_row ,"1.경미"    		,"2.낮음");
		  ds_test.set(test_row ,"2.중증"    		,"2");
		  
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


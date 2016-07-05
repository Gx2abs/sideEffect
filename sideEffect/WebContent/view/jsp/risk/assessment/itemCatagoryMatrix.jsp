<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="sideeffect.SimpleSideeffectResult" %>
<%@ page import="risk.category.SimpleItemCategoryGroupBack" %>
<%@ page import="risk.category.*" %>
<%@ page import="report.SimpleSideeffectReport" %>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	List<SimpleSideeffectResult> resultList = (List<SimpleSideeffectResult>)request.getAttribute("resultList");
	SimpleItemCategoryGroupBack groupBack = (SimpleItemCategoryGroupBack)request.getAttribute("groupBack");

	  
	try {	
		
		DataSet ds = new DataSet("ds_matrix");	  
		ds.addColumn("ID" 		,DataTypes.INT		,(short)10   );
		ds.addColumn("NUM" 		,DataTypes.INT		,(short)10   );
		ds.addColumn("NAME" 	,DataTypes.STRING	,(short)255  );
		
		System.out.println("resultList.size()=="+resultList.size());
		// matric 위해심각도 col 만들기
 		for(int a=0; a<resultList.size(); a++) {
 			// 기타코드제외
 			if(resultList.get(a).getValue() > 0) {
			ds.addColumn(resultList.get(a).getPropertyValue(), DataTypes.INT	,(short)10  );
			ds.addColumn(resultList.get(a).getId() +"COLOR" 	,DataTypes.STRING	,(short)255  );	
			}
		} 
		
		int row = 0;
	  	for(int b=0; b <groupBack.getPossibility().size(); b++) {
	  		row = ds.newRow();
	  		
	  		ds.set(row ,"ID"    		,groupBack.getPossibility().get(b).getId());
	  		ds.set(row ,"NUM"    		,groupBack.getPossibility().get(b).getValue());
	  		ds.set(row ,"NAME"    		,groupBack.getPossibility().get(b).getValue()+"."+groupBack.getPossibility().get(b).getClassName());
	 
	  		for(int c=0; c<resultList.size(); c++) {
	  			// 기타코드제외
	 			if(resultList.get(c).getValue() > 0) {
	  			ds.set(row, resultList.get(c).getPropertyValue() , 0);	
	  	 		ds.set(row ,resultList.get(c).getId() + "COLOR"			,"#FFFFFF");
	  			
	  			Integer riskGrade = groupBack.getPossibility().get(b).getValue() * resultList.get(c).getValue();
	  			
	  			//System.out.println("riskGrade=="+riskGrade);
	  			
	  			for(int d=0; d<groupBack.getRisk().size(); d++) {
	  				if((groupBack.getRisk().get(d).getRiskGradeFm() <= riskGrade) && (riskGrade <=groupBack.getRisk().get(d).getRiskGradeTo())) {
	  					ds.set(row ,resultList.get(c).getId() +"COLOR"			,groupBack.getRisk().get(d).getColor());
	  					//System.out.println("COLOR =="+groupBack.getRisk().get(d).getColor());
	  				}
	  			} // for문종료-d
	 			} // if문종료-기타제외
	  		} // fro문종료-c
	  	} // for문종료-b
	  	
 	  	for(int a=0; a<resultList.size(); a++) {
 	  		// 기타코드제외
 			if(resultList.get(a).getValue() > 0) {
 	  		
	  		List<SimpleSideeffectReport> itemCatagoryMatrix = (List<SimpleSideeffectReport>)request.getAttribute("itemCatagoryMatrix"+a);
	  		
	  		for(int b=0; b< itemCatagoryMatrix.size(); b++) {
		  		int bf_item = 0;
		  		String bf_fdacode = "";
		  		long totalCnt = 0L;
		  		long bf_cnt = 0L;
		  		
		  		// 누적수량 계산 (품목별 부작용코드 기준으로 누적수량 계산)
		  		totalCnt = itemCatagoryMatrix.get(b).getVarCnt() + itemCatagoryMatrix.get(b).getVarCnt1();
		  		if((bf_item == itemCatagoryMatrix.get(b).getMeb_item_id()) && (bf_fdacode == itemCatagoryMatrix.get(b).getVarValue4()) ) {
		  			totalCnt = totalCnt + bf_cnt;
		  		}

	  			if((itemCatagoryMatrix.get(b).getVarCnt2()+itemCatagoryMatrix.get(b).getVarCnt3()) > 0) {
	  				// 발생빈도분율 = 부작용누적보고건수/(생산량+수입량) *100
	  				float possibility = (float) totalCnt;
	  				possibility = possibility /  ((float)itemCatagoryMatrix.get(b).getVarCnt2()+(float)itemCatagoryMatrix.get(b).getVarCnt3()) *100;
	  				
	  				for(int c=0; c <groupBack.getPossibility().size(); c++) {
	  					
	  					if((groupBack.getPossibility().get(c).getLikelihoodFm() <= possibility) && (possibility < groupBack.getPossibility().get(c).getLikelihoodTo())) {
	  						int cellVal = ds.getInt(c, resultList.get(a).getPropertyValue());
	  						ds.set(c, resultList.get(a).getPropertyValue() , cellVal+itemCatagoryMatrix.get(b).getVarCnt());	
	  					}
	  				} //fro문 종료-c
	  			} //if문 종료
	  			
	  			bf_item = itemCatagoryMatrix.get(b).getMeb_item_id();
		  		bf_fdacode = itemCatagoryMatrix.get(b).getVarValue4();
		  		bf_cnt = bf_cnt + itemCatagoryMatrix.get(b).getVarCnt();
	  			
	  			
	  		}//for문 종료-b
 			}//if문종료-기타제외
	  		
	  	}//for문종료-a
	  	
	  	
	  	// 위해심각도 코드
	  	DataSet ds_result = new DataSet("ds_result");	 
	  	ds_result.addColumn("NUM" 	,DataTypes.INT		,(short)10   );
	  	ds_result.addColumn("NAME" 	,DataTypes.STRING	,(short)255  );
	  	ds_result.addColumn("ID" 	,DataTypes.INT		,(short)10   );
	  	for(int a=0; a<resultList.size(); a++) {
	  		// 기타코드제외
	  		if(resultList.get(a).getValue() > 0) {
		  		row = ds_result.newRow();
		  		ds_result.set(row, "NUM"  , resultList.get(a).getValue());
		  		ds_result.set(row, "NAME" , resultList.get(a).getPropertyValue());
		  		ds_result.set(row, "ID"  , resultList.get(a).getId());
	  		}
	  	}
		
		o_xpData.addDataSet(ds);
		o_xpData.addDataSet(ds_result);
		 
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


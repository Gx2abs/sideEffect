<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="sideeffect.SimpleSideeffectResult" %>
<%@ page import="risk.category.SimpleItemCategoryGroupBack" %>
<%@ page import="report.SimpleSideeffectReport" %>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	List<SimpleSideeffectReport> matrixList = (List<SimpleSideeffectReport>)request.getAttribute("matrixList");
	SimpleItemCategoryGroupBack groupBack = (SimpleItemCategoryGroupBack)request.getAttribute("groupBack");
	String possibilitySeq = request.getAttribute("possibilitySeq").toString();
	SimpleSideeffectResult resultList = (SimpleSideeffectResult)request.getAttribute("resultList");

	float likelihoodFm = 0;
	float likelihoodTo = 0;
	String possibilityName = "";
	Integer score = 0;

	System.out.println("size==="+matrixList.size() );
	// 발생가능성 범위
	for(int c=0; c <groupBack.getPossibility().size(); c++) {
		if ( groupBack.getPossibility().get(c).getId() == Long.parseLong(possibilitySeq)) {
			likelihoodFm = groupBack.getPossibility().get(c).getLikelihoodFm();
			likelihoodTo = groupBack.getPossibility().get(c).getLikelihoodTo();
			possibilityName = groupBack.getPossibility().get(c).getValue() + "."+groupBack.getPossibility().get(c).getClassName();
			score = groupBack.getPossibility().get(c).getValue() * resultList.getValue();
		}
	}
	
	try {	
		
		DataSet ds = new DataSet("ds_matrix_list");	  
		ds.addColumn("NUM" 				,DataTypes.INT			,(short)10   );
		ds.addColumn("ITEM_ID" 			,DataTypes.INT			,(short)10   );
		ds.addColumn("ITEM" 			,DataTypes.STRING		,(short)255   );
		ds.addColumn("ENTPNAME" 		,DataTypes.STRING		,(short)500  );
		ds.addColumn("FDACODE" 			,DataTypes.STRING		,(short)10   );
		ds.addColumn("FDASOURCE" 		,DataTypes.STRING		,(short)500   );
		ds.addColumn("REPORTDATE" 		,DataTypes.STRING		,(short)255  );
		ds.addColumn("VARCNT" 			,DataTypes.INT			,(short)10  );
		ds.addColumn("TOTALCNT" 		,DataTypes.INT			,(short)10  );
		ds.addColumn("POSSIBILITY" 		,DataTypes.STRING		,(short)255  );
		ds.addColumn("FRACTION" 		,DataTypes.FLOAT		,(short)10  );
		ds.addColumn("RISK" 			,DataTypes.STRING		,(short)255  );
		ds.addColumn("RISKNUM" 			,DataTypes.INT			,(short)10  );
		ds.addColumn("IMPORT" 			,DataTypes.FLOAT		,(short)20  );
		ds.addColumn("OUTPUT" 			,DataTypes.FLOAT		,(short)20  );

		
		int row = 0;
	  	for(int a=0; a <matrixList.size(); a++) {
	  		int bf_item = 0;
	  		String bf_fdacode = "";
	  		long totalCnt = 0L;
	  		long bf_cnt = 0L;
	  		
	  		

/* 	  		System.out.println("1==="+matrixList.get(a).getVarValue1());
	  		System.out.println("2==="+matrixList.get(a).getVarValue2());
	  		System.out.println("3==="+matrixList.get(a).getVarValue3());
	  		System.out.println("4==="+matrixList.get(a).getVarValue4());
	  		System.out.println("5==="+matrixList.get(a).getVarValue5());
	  		
	  		System.out.println("6==="+matrixList.get(a).getVarYear());
	  		System.out.println("7==="+matrixList.get(a).getVarMonth());
	  		System.out.println("8==="+matrixList.get(a).getVarCnt());
	  		System.out.println("9==="+matrixList.get(a).getVarCnt1());
	  		System.out.println("10==="+matrixList.get(a).getVarCnt2());
	  		System.out.println("11==="+matrixList.get(a).getVarCnt3()); */
	  		
	  		// 누적수량 계산 (품목별 부작용코드 기준으로 누적수량 계산)
	  		totalCnt = matrixList.get(a).getVarCnt() + matrixList.get(a).getVarCnt1();
	  		if((bf_item == matrixList.get(a).getMeb_item_id()) && (bf_fdacode == matrixList.get(a).getVarValue4()) ) {
	  			totalCnt = totalCnt + bf_cnt;
	  		}
	  		
	  		// 발생빈도분율
	  		float possibility = (float) totalCnt;
			possibility = possibility /  ((float)matrixList.get(a).getVarCnt2()+(float)matrixList.get(a).getVarCnt3()) *100;
	  		
			// 선택한 발생가능성에 해당하는 레코드만 추가
			if ((likelihoodFm <=possibility ) && (possibility<likelihoodTo)) {
				row = ds.newRow();
		  		ds.set(row ,"NUM"    			,a);
		  		ds.set(row ,"ITEM_ID"    		,matrixList.get(a).getMeb_item_id());
		  		ds.set(row ,"ITEM"    			,matrixList.get(a).getVarValue1()+" "+ matrixList.get(a).getVarValue2());
		  		ds.set(row ,"ENTPNAME"    		,matrixList.get(a).getVarValue3());
		  		ds.set(row ,"FDACODE"    		,matrixList.get(a).getVarValue4());
		  		ds.set(row ,"FDASOURCE"    		,matrixList.get(a).getVarValue5());
		  		
		  		ds.set(row ,"REPORTDATE"    	,matrixList.get(a).getVarYear()+"."+matrixList.get(a).getVarMonth());
		  		ds.set(row ,"VARCNT"    		,matrixList.get(a).getVarCnt());
		  		ds.set(row ,"TOTALCNT"    		,totalCnt);
		  		
		  		ds.set(row ,"POSSIBILITY"    	,possibilityName);
		  		ds.set(row ,"FRACTION"    		,possibility);
		  		ds.set(row ,"RISK"    			,resultList.getValue()+"."+resultList.getPropertyValue());
		  		ds.set(row ,"RISKNUM"    		,score);
		  		
		  		ds.set(row ,"IMPORT"    		,matrixList.get(a).getVarCnt3());
		  		ds.set(row ,"OUTPUT"    		,matrixList.get(a).getVarCnt2());
	
		  		
		  		bf_item = matrixList.get(a).getMeb_item_id();
		  		bf_fdacode = matrixList.get(a).getVarValue4();
		  		bf_cnt = bf_cnt + matrixList.get(a).getVarCnt();
			}
	  		
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
			
	//Variable--> VariableList
	varList.add("ErrorCode", nErrorCode);
	varList.add("ErrorMsg", strErrorMsg);

	// HttpPlatformResponse 
	HttpPlatformResponse pRes = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
	pRes.setData(o_xpData);

	// Send data
	pRes.sendData();
	
%>


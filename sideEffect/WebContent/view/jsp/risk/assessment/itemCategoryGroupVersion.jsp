<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="risk.category.SimpleItemCategoryGroupBack" %>
<%@ page import="risk.category.possibility.Possibility" %>
<%@ page import="risk.category.risk.Risk" %>
<%@ page import="common.Function"%>

<%@ include file="/view/config/config.jsp" %>
<%
	PlatformData o_xpData = new PlatformData();
	
	int nErrorCode = 0;
	String strErrorMsg = "START";
	String sTest="조회실패";
	
	List codeList = (List)request.getAttribute("groupBackList");
	String groupId =request.getAttribute("groupId").toString();
	
	try {	

		DataSet ds = new DataSet("ds_version");
		  ds.addColumn("ID" 				,DataTypes.INT		,(short)10   );
		  ds.addColumn("GROUP_ID" 			,DataTypes.INT		,(short)10   );
		  ds.addColumn("REVISION_NAME"   	,DataTypes.STRING	,(short)255  );
		  ds.addColumn("START_DATE"   		,DataTypes.STRING	,(short)255  );
		  ds.addColumn("CLASS_GB"   		,DataTypes.STRING	,(short)20 	  );
		  ds.addColumn("CLASS_CODE"    		,DataTypes.INT   	,(short)255  );
		  ds.addColumn("CLASS_NAME"    		,DataTypes.STRING   ,(short)255  );
		  ds.addColumn("LIMIT_FM"    		,DataTypes.FLOAT   	,(short)10   );
		  ds.addColumn("LIMIT_TO"    		,DataTypes.FLOAT   	,(short)10   );
		  ds.addColumn("NOTE"    			,DataTypes.STRING   ,(short)500  );
		
		  int row = 0;
		  //List<SimpleItemCategoryGroupBack> bean = (SimpleItemCategoryGroupBack)codeList;
		  for(int i=0; i <codeList.size(); i++) {
			/* float getSumPo = 0.0F;
			getSumPo = ((float)bean.getVarCnt1()+(float)bean.getVarCnt2());
		  	getSumPo = (float)bean.getVarCnt()/getSumPo*100; */
		  	
		  	SimpleItemCategoryGroupBack itemCategoryGroup = (SimpleItemCategoryGroupBack)codeList.get(i);
		  	
		  	List<Possibility> possibility = itemCategoryGroup.getPossibility();
		  	
			for(int a=0; a<possibility.size(); a++) {
				row = ds.newRow();
				ds.set(row ,"ID"    			,itemCategoryGroup.getId());
				ds.set(row ,"GROUP_ID"    		,itemCategoryGroup.getGroup_id());
				ds.set(row ,"REVISION_NAME"    	,itemCategoryGroup.getRevisionName());
				ds.set(row ,"START_DATE"  		,itemCategoryGroup.getStartDate());
			  	ds.set(row ,"CLASS_GB"  		,"발생가능성");
			  	ds.set(row ,"CLASS_CODE"  		,possibility.get(a).getValue());
			  	ds.set(row ,"CLASS_NAME"  		,possibility.get(a).getClassName());
			  	ds.set(row ,"LIMIT_FM"  		,possibility.get(a).getLikelihoodFm());
			  	ds.set(row ,"LIMIT_TO"  		,possibility.get(a).getLikelihoodTo());
			  	ds.set(row ,"NOTE"  			,"");
			}

			List<Risk> risk = itemCategoryGroup.getRisk();
			
			for(int b=0; b<risk.size(); b++) {
				row = ds.newRow();
				ds.set(row ,"ID"    			,itemCategoryGroup.getId());
				ds.set(row ,"GROUP_ID"    		,itemCategoryGroup.getGroup_id());
				ds.set(row ,"REVISION_NAME"    	,itemCategoryGroup.getRevisionName());
				ds.set(row ,"START_DATE"  		,itemCategoryGroup.getStartDate());
			  	ds.set(row ,"CLASS_GB"  		,"위험도점수");
			  	ds.set(row ,"CLASS_CODE"  		,risk.get(b).getValue());
			  	ds.set(row ,"CLASS_NAME"  		,risk.get(b).getClassName());
			  	ds.set(row ,"LIMIT_FM"  		,risk.get(b).getRiskGradeFm());
			  	ds.set(row ,"LIMIT_TO"  		,risk.get(b).getRiskGradeTo());
			  	ds.set(row ,"NOTE"  			,risk.get(b).getCorrectiveMeasure());
			}
		  	
		  }
		  
		 
		  
		o_xpData.addDataSet(ds);
		 
		nErrorCode = 0;
		strErrorMsg = groupId;//"SUCC";
			
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


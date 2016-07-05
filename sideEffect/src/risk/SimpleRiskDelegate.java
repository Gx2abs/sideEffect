// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleRiskDelegate.java

package risk;

import abstraction.SimpleDelegate;
import com.tobesoft.xplatform.data.*;
import com.tobesoft.xplatform.tx.*;
import java.io.PrintStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import properties.PropertiesDAO;
import report.SimpleSideeffectReport;
import report.SimpleSideeffectReportDAO;
import risk.category.ItemCategoryGroupDAO;
import risk.category.SimpleItemCategoryGroupBack;
import risk.category.possibility.Possibility;
import risk.category.risk.Risk;
import safety.SafetyDAO;
import safety.renewal.sgi.category.ItemCategoryDAO;
import safety.renewal.sgi.item.ItemDAO;
import sideeffect.SimpleSideeffectResult;

public class SimpleRiskDelegate extends SimpleDelegate {

	public SimpleRiskDelegate() {
	}

	public PropertiesDAO getPropertiesDAO() {
		return propertiesDAO;
	}

	public void setPropertiesDAO(PropertiesDAO propertiesDAO) {
		this.propertiesDAO = propertiesDAO;
	}

	public ItemCategoryDAO getItemCategoryDAO() {
		return itemCategoryDAO;
	}

	public void setItemCategoryDAO(ItemCategoryDAO itemCategoryDAO) {
		this.itemCategoryDAO = itemCategoryDAO;
	}

	public SimpleSideeffectReportDAO getSideEffectDAO() {
		return sideEffectDAO;
	}

	public void setSideEffectDAO(SimpleSideeffectReportDAO sideEffectDAO) {
		this.sideEffectDAO = sideEffectDAO;
	}

	public SafetyDAO getSafetyDAO() {
		return safetyDAO;
	}

	public void setSafetyDAO(SafetyDAO safetyDAO) {
		this.safetyDAO = safetyDAO;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public void setItemCategoryGroupDAO(ItemCategoryGroupDAO itemCategoryGroupDAO) {
		this.itemCategoryGroupDAO = itemCategoryGroupDAO;
	}

	public ItemCategoryGroupDAO getItemCategoryGroupDAO() {
		return itemCategoryGroupDAO;
	}

	public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/risk/assessment/riskLeft.jsp");
		mav.addObject("rightName", request.getAttribute("rightName"));
		return mav;
	}

	public ModelAndView xpRiskLink(HttpServletRequest request, HttpServletResponse respnse) {
		System.out.println("[SimpleRiskDelegate].xpStatisticsAndReport()");
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		mav.addObject("contentName", "/view/jsp/risk/assessment/xp_riskLink.jsp");
		mav.addObject("titleImg", "view/style/images/title/sub06_02.gif");
		mav.addObject("titleImage", "");
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		return mav;
	}

	public ModelAndView itemLikelihoodList(HttpServletRequest request, HttpServletResponse respnse) throws PlatformException {
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView();
		PlatformData o_xpData = new PlatformData();
		int nErrorCode = 0;
		String strErrorMsg = "START";
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		pReq.receiveData();
		PlatformData i_xpData = pReq.getData();
		VariableList in_vl = i_xpData.getVariableList();
		String classKorName = in_vl.getString("classKorName");
		String meaClassNo = in_vl.getString("meaClassNo");
		String reportType = in_vl.getString("reportType");
		String fmDate = in_vl.getString("fmDate");
		String toDate = in_vl.getString("toDate");
		int pgSize = in_vl.getInt("pgSize");
		int startPg = in_vl.getInt("startPg");
		String moreYN = in_vl.getString("moreYN");
		String statisticsGB = in_vl.getString("statisticsGB");
		String classKorNameEqual = in_vl.getString("classKorNameEqual");
		long totalCnt = 0L;
		if (statisticsGB.equals("sideeffect")) {
			if (moreYN.equals("N")) {
				totalCnt = sideEffectDAO.itemLikelihoodCnt(classKorName, meaClassNo, reportType, fmDate, toDate, classKorNameEqual);
				startPg = 0;
			}
			List itemLikelihoodList = sideEffectDAO.itemLikelihoodList(classKorName, meaClassNo, reportType, fmDate, toDate, pgSize, startPg,
					classKorNameEqual);
			mav.addObject("ItemSideeffectList", itemLikelihoodList);
			mav.addObject("ItemSideeffectCnt", Long.valueOf(totalCnt));
			mav.setViewName("/view/jsp/risk/assessment/itemLikelihoodList.jsp");
		}
		mav.addObject("moreYN", moreYN);
		return mav;
	}

	public ModelAndView itemCatagoryGroup(HttpServletRequest request, HttpServletResponse respnse) throws PlatformException {
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView();
		PlatformData o_xpData = new PlatformData();
		int nErrorCode = 0;
		String strErrorMsg = "START";
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		pReq.receiveData();
		PlatformData i_xpData = pReq.getData();
		VariableList in_vl = i_xpData.getVariableList();
		String itemCategorySeq = in_vl.getString("itemCategorySeq");
		String fmDate = in_vl.getString("fmDate");
		String toDate = in_vl.getString("toDate");
		Long groupId = Long.valueOf(itemCategoryGroupDAO.itemCategoryGroupId(Long.parseLong(itemCategorySeq)));
		List groupBackList = itemCategoryGroupDAO.groupBackList(fmDate, toDate, groupId.longValue());
		mav.addObject("groupId", groupId);
		mav.addObject("groupBackList", groupBackList);
		mav.setViewName("/view/jsp/risk/assessment/itemCategoryGroupVersion.jsp");
		return mav;
	}

	public ModelAndView itemCatagoryMatrix(HttpServletRequest request, HttpServletResponse respnse) throws PlatformException {
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView();
		PlatformData o_xpData = new PlatformData();
		int nErrorCode = 0;
		String strErrorMsg = "START";
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		pReq.receiveData();
		PlatformData i_xpData = pReq.getData();
		VariableList in_vl = i_xpData.getVariableList();
		String fmDate = in_vl.getString("fmDate");
		String toDate = in_vl.getString("toDate");
		String reportType = in_vl.getString("reportType");
		String itemCategorySeq = in_vl.getString("itemCategorySeq");
		String codeGB = in_vl.getString("codeGB");
		String verSeq = in_vl.getString("verSeq");
		System.out.println((new StringBuilder("[SimpleRiskDelegate].itemCatagoryMatrix().itemCategorySeq ")).append(itemCategorySeq).toString());
		SimpleItemCategoryGroupBack groupBack = new SimpleItemCategoryGroupBack();
		List resultList = propertiesDAO.riskSideeffectResultList(fmDate, toDate, Integer.valueOf(1), 0L);
		groupBack = (SimpleItemCategoryGroupBack) itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, Long.parseLong(verSeq));
		List itemCatagoryMatrix = null;
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMM");
		System.out.println((new StringBuilder("[SimpleRiskDelegate].itemCatagoryMatrix().verSeq ")).append(verSeq).toString());
		for (int a = 0; a < resultList.size(); a++)
			if (((SimpleSideeffectResult) resultList.get(a)).getValue() > 0) {
				itemCatagoryMatrix = null;
				itemCatagoryMatrix = sideEffectDAO.itemCatagoryMatrix(fmDate, toDate, dataFormat.format(groupBack.getStartDate()), reportType,
						Long.parseLong(itemCategorySeq), codeGB, ((SimpleSideeffectResult) resultList.get(a)).getId());
				mav.addObject((new StringBuilder("itemCatagoryMatrix")).append(a).toString(), itemCatagoryMatrix);
			}

		mav.addObject("resultList", resultList);
		mav.addObject("groupBack", groupBack);
		mav.setViewName("/view/jsp/risk/assessment/itemCatagoryMatrix.jsp");
		return mav;
	}

	public ModelAndView matrixList(HttpServletRequest request, HttpServletResponse respnse) throws PlatformException {
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView();
		PlatformData o_xpData = new PlatformData();
		int nErrorCode = 0;
		String strErrorMsg = "START";
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		pReq.receiveData();
		PlatformData i_xpData = pReq.getData();
		VariableList in_vl = i_xpData.getVariableList();
		String fmDate = in_vl.getString("fmDate");
		String toDate = in_vl.getString("toDate");
		String reportType = in_vl.getString("reportType");
		String itemCategorySeq = in_vl.getString("itemCategorySeq");
		String codeGB = in_vl.getString("codeGB");
		String verSeq = in_vl.getString("verSeq");
		String resultSeq = in_vl.getString("resultSeq");
		String possibilitySeq = in_vl.getString("possibilitySeq");
		SimpleItemCategoryGroupBack groupBack = new SimpleItemCategoryGroupBack();
		SimpleSideeffectResult resultList = new SimpleSideeffectResult();
		groupBack = (SimpleItemCategoryGroupBack) itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, Long.parseLong(verSeq));
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMM");
		List matrixList = null;
		resultList = (SimpleSideeffectResult) propertiesDAO.read(SimpleSideeffectResult.class, Long.parseLong(resultSeq));
		matrixList = sideEffectDAO.itemCatagoryMatrix(fmDate, toDate, dataFormat.format(groupBack.getStartDate()), reportType,
				Long.parseLong(itemCategorySeq), codeGB, Long.parseLong(resultSeq));
		mav.addObject("matrixList", matrixList);
		mav.addObject("groupBack", groupBack);
		mav.addObject("possibilitySeq", possibilitySeq);
		mav.addObject("resultList", resultList);
		mav.setViewName("/view/jsp/risk/assessment/itemCatagoryMatrixList.jsp");
		return mav;
	}

	public ModelAndView matrixAndRiskInItemGroup(HttpServletRequest request, HttpServletResponse respnse) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		mav.addObject("contentName", "/view/jsp/risk/assessment/xp_matrixAndRiskInItemGroup.jsp");
		mav.addObject("titleImg", "view/style/images/title/sub06_01.gif");
		mav.addObject("titleImage", "");
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		return mav;
	}

	public void getVersionAndMatrix(HttpServletRequest request, HttpServletResponse response) throws PlatformException {
		String debug_item_id1 = "";
		String debug_item_id2 = "";
		PlatformData o_xpData = new PlatformData();
		int nErrorCode = 0;
		String strErrorMsg = "START";
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		pReq.receiveData();
		PlatformData i_xpData = pReq.getData();
		VariableList in_vl = i_xpData.getVariableList();
		long group_id = in_vl.getLong("group_id");
		String reportType = in_vl.getString("reportType");
		String fm_y = in_vl.getString("fm_y");
		String fm_m = in_vl.getString("fm_m");
		String to_y = in_vl.getString("to_y");
		String to_m = in_vl.getString("to_m");
		fm_y = (new StringBuilder(String.valueOf(fm_y))).append(fm_m).toString();
		to_y = (new StringBuilder(String.valueOf(to_y))).append(to_m).toString();
		System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().group_id ")).append(group_id).toString());
		System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().reportType ")).append(reportType).toString());
		System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().fm_y ")).append(fm_y).toString());
		System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().fm_m ")).append(fm_m).toString());
		System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().to_y ")).append(to_y).toString());
		System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().to_m ")).append(to_m).toString());
		List codeList = itemCategoryGroupDAO.groupLatestBackList(fm_y, to_y, group_id);
		if (codeList.size() <= 0) {
			nErrorCode = 1;
			codeList = null;
		}
		try {
			if (codeList != null) {
				DataSet ds = new DataSet("ds_version");
				ds.addColumn("ID", 3, 10);
				ds.addColumn("GROUP_ID", 3, 10);
				ds.addColumn("REVISION_NAME", 2, 255);
				ds.addColumn("START_DATE", 2, 255);
				ds.addColumn("CLASS_GB", 2, 20);
				ds.addColumn("CLASS_CODE", 3, 255);
				ds.addColumn("CLASS_NAME", 2, 255);
				ds.addColumn("LIMIT_FM", 6, 10);
				ds.addColumn("LIMIT_TO", 6, 10);
				ds.addColumn("NOTE", 2, 500);
				int row = 0;
				long verSeq = 0L;
				String verDate = "";
				SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMM");
				for (int i = 0; i < codeList.size(); i++) {
					verSeq = ((SimpleItemCategoryGroupBack) codeList.get(i)).getId();
					verDate = dataFormat.format(((SimpleItemCategoryGroupBack) codeList.get(i)).getStartDate());
					SimpleItemCategoryGroupBack itemCategoryGroup = (SimpleItemCategoryGroupBack) codeList.get(i);
					List possibility = itemCategoryGroup.getPossibility();
					for (int a = 0; a < possibility.size(); a++) {
						row = ds.newRow();
						ds.set(row, "ID", itemCategoryGroup.getId());
						ds.set(row, "GROUP_ID", itemCategoryGroup.getGroup_id());
						ds.set(row, "REVISION_NAME", itemCategoryGroup.getRevisionName());
						ds.set(row, "START_DATE", itemCategoryGroup.getStartDate());
						ds.set(row, "CLASS_GB", "\uBC1C\uC0DD\uAC00\uB2A5\uC131");
						ds.set(row, "CLASS_CODE", ((Possibility) possibility.get(a)).getValue());
						ds.set(row, "CLASS_NAME", ((Possibility) possibility.get(a)).getClassName());
						ds.set(row, "LIMIT_FM", ((Possibility) possibility.get(a)).getLikelihoodFm());
						ds.set(row, "LIMIT_TO", ((Possibility) possibility.get(a)).getLikelihoodTo());
						ds.set(row, "NOTE", "");
					}

					List risk = itemCategoryGroup.getRisk();
					for (int b = 0; b < risk.size(); b++) {
						row = ds.newRow();
						ds.set(row, "ID", itemCategoryGroup.getId());
						ds.set(row, "GROUP_ID", itemCategoryGroup.getGroup_id());
						ds.set(row, "REVISION_NAME", itemCategoryGroup.getRevisionName());
						ds.set(row, "START_DATE", itemCategoryGroup.getStartDate());
						ds.set(row, "CLASS_GB", "\uC704\uD5D8\uB3C4\uC810\uC218");
						ds.set(row, "CLASS_CODE", ((Risk) risk.get(b)).getValue());
						ds.set(row, "CLASS_NAME", ((Risk) risk.get(b)).getClassName());
						ds.set(row, "LIMIT_FM", ((Risk) risk.get(b)).getRiskGradeFm());
						ds.set(row, "LIMIT_TO", ((Risk) risk.get(b)).getRiskGradeTo());
						ds.set(row, "NOTE", ((Risk) risk.get(b)).getCorrectiveMeasure());
					}

				}

				String versionStartDate = verDate;
				String selectFmDate = "";
				String selectToDate = "";
				String nextDate = verDate;
				String nextDateYY = "";
				String nextDateMM = "";
				System.out.println(
						(new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().versionStartDate ")).append(versionStartDate).toString());
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().fm_y ")).append(fm_y).toString());
				if (versionStartDate.compareTo(fm_y) > 0)
					selectFmDate = versionStartDate;
				else
					selectFmDate = fm_y;
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().selectFmDate ")).append(selectFmDate).toString());
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().nextDate ")).append(nextDate).toString());
				if (nextDate.compareTo(selectFmDate) > 0) {
					System.out.println("111");
					nextDateYY = nextDate.substring(0, 4);
					nextDateMM = nextDate.substring(4, 6);
					System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().nextDateYY ")).append(nextDateYY).toString());
					System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().nextDateMM ")).append(nextDateMM).toString());
					if (nextDateMM == "01") {
						Date date = (Date) dataFormat.parse(nextDateYY);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(2, -1);
						Date date2 = (Date) cal.getTime();
						nextDateYY = new String(dataFormat.format(date2));
						nextDateMM = "12";
					} else {
						Date date = (Date) dataFormat.parse(nextDateMM);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(2, -1);
						Date date2 = (Date) cal.getTime();
						nextDateMM = new String(dataFormat.format(date2));
						if (nextDateMM.length() < 2)
							nextDateMM = (new StringBuilder("0")).append(nextDateMM).toString();
					}
					selectToDate = (new StringBuilder(String.valueOf(nextDateYY))).append(nextDateMM).toString();
				} else {
					System.out.println("2222222");
					selectToDate = to_y;
				}
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().selectToDate ")).append(selectToDate).toString());
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().verSeq ")).append(verSeq).toString());
				List resultList = propertiesDAO.riskSideeffectResultList(selectFmDate, selectToDate, Integer.valueOf(1), 0L);
				SimpleItemCategoryGroupBack groupBack = (SimpleItemCategoryGroupBack) itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class,
						verSeq);
				DataSet ds_matrix = new DataSet("ds_matrix");
				ds_matrix.addColumn("ID", 3, 10);
				ds_matrix.addColumn("NUM", 3, 10);
				ds_matrix.addColumn("NAME", 2, 255);
				for (int a = 0; a < resultList.size(); a++)
					if (((SimpleSideeffectResult) resultList.get(a)).getValue() > 0) {
						ds_matrix.addColumn(((SimpleSideeffectResult) resultList.get(a)).getPropertyValue(), 3, 10);
						ds_matrix.addColumn(
								(new StringBuilder(String.valueOf(((SimpleSideeffectResult) resultList.get(a)).getId()))).append("COLOR").toString(),
								2, 255);
					}

				row = 0;
				for (int b = 0; b < groupBack.getPossibility().size(); b++) {
					row = ds_matrix.newRow();
					ds_matrix.set(row, "ID", ((Possibility) groupBack.getPossibility().get(b)).getId());
					ds_matrix.set(row, "NUM", ((Possibility) groupBack.getPossibility().get(b)).getValue());
					ds_matrix.set(row, "NAME", (new StringBuilder(String.valueOf(((Possibility) groupBack.getPossibility().get(b)).getValue())))
							.append(".").append(((Possibility) groupBack.getPossibility().get(b)).getClassName()).toString());
					for (int c = 0; c < resultList.size(); c++)
						if (((SimpleSideeffectResult) resultList.get(c)).getValue() > 0) {
							ds_matrix.set(row, ((SimpleSideeffectResult) resultList.get(c)).getPropertyValue(), 0);
							ds_matrix.set(row, (new StringBuilder(String.valueOf(((SimpleSideeffectResult) resultList.get(c)).getId())))
									.append("COLOR").toString(), "#FFFFFF");
							Integer riskGrade = Integer.valueOf(((Possibility) groupBack.getPossibility().get(b)).getValue()
									* ((SimpleSideeffectResult) resultList.get(c)).getValue());
							for (int d = 0; d < groupBack.getRisk().size(); d++)
								if (((Risk) groupBack.getRisk().get(d)).getRiskGradeFm() <= (float) riskGrade.intValue()
										&& (float) riskGrade.intValue() <= ((Risk) groupBack.getRisk().get(d)).getRiskGradeTo())
									ds_matrix.set(row, (new StringBuilder(String.valueOf(((SimpleSideeffectResult) resultList.get(c)).getId())))
											.append("COLOR").toString(), ((Risk) groupBack.getRisk().get(d)).getColor());

						}

				}

				Long category_type = itemCategoryGroupDAO.getCategoryType(group_id);
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().category_type ")).append(category_type).toString());
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().selectFmDate ")).append(selectFmDate).toString());
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().selectToDate ")).append(selectToDate).toString());
				System.out.println((new StringBuilder("[SimpleRiskDelegate].getVersionAndMatrix().verDate ")).append(verDate).toString());
				for (int a = 0; a < resultList.size(); a++)
					if (((SimpleSideeffectResult) resultList.get(a)).getValue() > 0) {
						List itemCatagoryMatrix = sideEffectDAO.getMatrix(selectFmDate, selectToDate, verDate, reportType, group_id,
								((SimpleSideeffectResult) resultList.get(a)).getParentId(), category_type);
						for (int b = 0; b < itemCatagoryMatrix.size(); b++) {
							int bf_item = 0;
							String bf_fdacode = "";
							long totalCnt = 0L;
							long bf_cnt = 0L;
							totalCnt = ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt().longValue()
									+ ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt1().longValue();
							if (bf_item == ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id().intValue()
									&& bf_fdacode == ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue4())
								totalCnt += bf_cnt;
							if (((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt2().longValue()
									+ ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt3().longValue() > 0L) {
								float possibility = totalCnt;
								possibility = (possibility / ((float) ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt2().longValue()
										+ (float) ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt3().longValue())) * 100F;
								for (int c = 0; c < groupBack.getPossibility().size(); c++)
									if (((Possibility) groupBack.getPossibility().get(c)).getLikelihoodFm() <= possibility
											&& possibility < ((Possibility) groupBack.getPossibility().get(c)).getLikelihoodTo()) {
										int cellVal = ds_matrix.getInt(c, ((SimpleSideeffectResult) resultList.get(a)).getPropertyValue());
										if (((SimpleSideeffectResult) resultList.get(a)).getPropertyValue().equals("\uC0AC\uB9DD")
												&& ((Possibility) groupBack.getPossibility().get(c)).getClassName().equals("\uB192\uC74C"))
											debug_item_id1 = (new StringBuilder(String.valueOf(debug_item_id1))).append(" this_.meb_item_id= ")
													.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id()).append(" or ")
													.toString();
										ds_matrix.set(c, ((SimpleSideeffectResult) resultList.get(a)).getPropertyValue(),
												(long) cellVal + ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt().longValue());
									} else if (((SimpleSideeffectResult) resultList.get(a)).getPropertyValue().equals("\uC2EC\uAC01")
											&& ((Possibility) groupBack.getPossibility().get(c)).getClassName().equals("\uC544\uC8FC \uB192\uC74C"))
										debug_item_id2 = (new StringBuilder(String.valueOf(debug_item_id2))).append(" ")
												.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id()).toString();

							}
							bf_item = ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id().intValue();
							bf_fdacode = ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue4();
							bf_cnt += ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt().longValue();
						}

					}

				DataSet ds_result = new DataSet("ds_result");
				ds_result.addColumn("NUM", 3, 10);
				ds_result.addColumn("NAME", 2, 255);
				ds_result.addColumn("ID", 3, 10);
				for (int a = 0; a < resultList.size(); a++)
					if (((SimpleSideeffectResult) resultList.get(a)).getValue() > 0) {
						row = ds_result.newRow();
						ds_result.set(row, "NUM", ((SimpleSideeffectResult) resultList.get(a)).getValue());
						ds_result.set(row, "NAME", ((SimpleSideeffectResult) resultList.get(a)).getPropertyValue());
						ds_result.set(row, "ID", ((SimpleSideeffectResult) resultList.get(a)).getId());
					}

				o_xpData.addDataSet(ds);
				o_xpData.addDataSet(ds_matrix);
				o_xpData.addDataSet(ds_result);
			}
			System.out.println((new StringBuilder("debug_item_id1 ")).append(debug_item_id1).toString());
			System.out.println((new StringBuilder("debug_item_id2 ")).append(debug_item_id2).toString());
			nErrorCode = 0;
			strErrorMsg = String.valueOf(group_id);
			HttpPlatformResponse res = new HttpPlatformResponse(response);
			res.setData(o_xpData);
			try {
				res.sendData();
			} catch (PlatformException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (Throwable th) {
			nErrorCode = -1;
			strErrorMsg = th.getMessage();
		}
	}

	public void getItemList(HttpServletRequest request, HttpServletResponse respnse) throws PlatformException {
		List itemCatagoryMatrix = null;
		int nErrorCode = 0;
		String strErrorMsg = "START";
		String sTest = "\uC870\uD68C\uC2E4\uD328";
		PlatformData o_xpData = null;
		HttpPlatformRequest pReq = null;
		try {
			o_xpData = new PlatformData();
			pReq = new HttpPlatformRequest(request);
			pReq.receiveData();
			PlatformData i_xpData = pReq.getData();
			VariableList in_vl = i_xpData.getVariableList();
			long group_id = in_vl.getLong("group_id");
			String reportType = in_vl.getString("reportType");
			String fm_y = in_vl.getString("fm_y");
			String fm_m = in_vl.getString("fm_m");
			String to_y = in_vl.getString("to_y");
			String to_m = in_vl.getString("to_m");
			long parentId = in_vl.getLong("parent_id");
			long possibilityId = in_vl.getLong("possibilityId");
			fm_y = (new StringBuilder(String.valueOf(fm_y))).append(fm_m).toString();
			to_y = (new StringBuilder(String.valueOf(to_y))).append(to_m).toString();
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().group_id ")).append(group_id).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().fm_y ")).append(fm_y).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().fm_m ")).append(fm_m).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().to_y ")).append(to_y).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().to_m ")).append(to_m).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().parentId ")).append(parentId).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().possibilityId ")).append(possibilityId).toString());
			List codeList = itemCategoryGroupDAO.groupLatestBackList(fm_y, to_y, group_id);
			long verSeq = 0L;
			String verDate = "";
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMM");
			for (int i = 0; i < codeList.size(); i++) {
				verSeq = ((SimpleItemCategoryGroupBack) codeList.get(i)).getId();
				verDate = dataFormat.format(((SimpleItemCategoryGroupBack) codeList.get(i)).getStartDate());
			}

			String versionStartDate = verDate;
			String selectFmDate = "";
			String selectToDate = "";
			String nextDate = verDate;
			String nextDateYY = "";
			String nextDateMM = "";
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().versionStartDate ")).append(versionStartDate).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().fm_y ")).append(fm_y).toString());
			if (versionStartDate.compareTo(fm_y) > 0)
				selectFmDate = versionStartDate;
			else
				selectFmDate = fm_y;
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().selectFmDate ")).append(selectFmDate).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().nextDate ")).append(nextDate).toString());
			if (nextDate.compareTo(selectFmDate) > 0) {
				System.out.println("111");
				nextDateYY = nextDate.substring(0, 4);
				nextDateMM = nextDate.substring(4, 6);
				if (nextDateMM == "01") {
					Date date = (Date) dataFormat.parse(nextDateYY);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(2, -1);
					Date date2 = (Date) cal.getTime();
					nextDateYY = new String(dataFormat.format(date2));
					nextDateMM = "12";
				} else {
					Date date = (Date) dataFormat.parse(nextDateMM);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(2, -1);
					Date date2 = (Date) cal.getTime();
					nextDateMM = new String(dataFormat.format(date2));
					if (nextDateMM.length() < 2)
						nextDateMM = (new StringBuilder("0")).append(nextDateMM).toString();
				}
				selectToDate = (new StringBuilder(String.valueOf(nextDateYY))).append(nextDateMM).toString();
			} else {
				System.out.println("2222222");
				selectToDate = to_y;
			}
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().selectToDate ")).append(selectToDate).toString());
			Long category_type = itemCategoryGroupDAO.getCategoryType(group_id);
			List itemIdList = new ArrayList();
			SimpleItemCategoryGroupBack groupBack = (SimpleItemCategoryGroupBack) itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class,
					verSeq);
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().parentId ")).append(parentId).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().selectFmDate ")).append(selectFmDate).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().selectToDate ")).append(selectToDate).toString());
			System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().verDate ")).append(verDate).toString());
			itemCatagoryMatrix = sideEffectDAO.getMatrix(selectFmDate, selectToDate, verDate, reportType, group_id, parentId, category_type);
			for (int b = 0; b < itemCatagoryMatrix.size(); b++) {
				int bf_item = 0;
				String bf_fdacode = "";
				long totalCnt = 0L;
				long bf_cnt = 0L;
				totalCnt = ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt().longValue()
						+ ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt1().longValue();
				if (bf_item == ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id().intValue()
						&& bf_fdacode == ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue4())
					totalCnt += bf_cnt;
				if (((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt2().longValue()
						+ ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt3().longValue() > 0L) {
					float possibility = totalCnt;
					possibility = (possibility / ((float) ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt2().longValue()
							+ (float) ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt3().longValue())) * 100F;
					for (int c = 0; c < groupBack.getPossibility().size(); c++)
						if (((Possibility) groupBack.getPossibility().get(c)).getLikelihoodFm() <= possibility
								&& possibility < ((Possibility) groupBack.getPossibility().get(c)).getLikelihoodTo()) {
							System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().groupBack.getPossibility().get(c).getId() "))
									.append(((Possibility) groupBack.getPossibility().get(c)).getId()).append(" possibilityId ").append(possibilityId)
									.toString());
							if (((Possibility) groupBack.getPossibility().get(c)).getId() == possibilityId) {
								System.out
										.println((new StringBuilder("[SimpleRiskDelegate].getItemList().groupBack.getPossibility().get(c).getId() "))
												.append(((Possibility) groupBack.getPossibility().get(c)).getId()).append("  ")
												.append(((Possibility) groupBack.getPossibility().get(c)).getClassName()).append("  ")
												.append(parentId).append("  ")
												.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id()).append(" count ")
												.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt()).toString());
								System.out.println((new StringBuilder("[SimpleRiskDelegate].getItemList().itemCatagoryMatrix.get(b).getId() "))
										.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id()).toString());
								itemIdList.add(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id());
							}
						}

				}
			}

			int row = 0;
			itemIdList = new ArrayList(new HashSet(itemIdList));
			itemCatagoryMatrix = sideEffectDAO.selectByItemId(selectFmDate, selectToDate, verDate, reportType, group_id, parentId, category_type,
					itemIdList);
			List itemList = sideEffectDAO.getItemList(selectFmDate, selectToDate, verDate, reportType, group_id, parentId, itemIdList, category_type);
			DataSet ds = new DataSet("ds_category");
			ds.addColumn("MEA_CLASS_NO", 2, 255);
			ds.addColumn("CLASS_KOR_NAME", 2, 255);
			ds.addColumn("MEA_GRADE", 2, 10);
			ds.addColumn("GET_SUM", 3, 10);
			ds.addColumn("GET_SUM_M", 3, 10);
			ds.addColumn("GET_SUM_P", 3, 10);
			ds.addColumn("GET_SUM_C", 3, 10);
			for (int i = 0; i < itemList.size(); i++) {
				SimpleSideeffectReport bean = (SimpleSideeffectReport) itemList.get(i);
				row = ds.newRow();
				ds.set(row, "MEA_CLASS_NO", bean.getVarValue1());
				ds.set(row, "CLASS_KOR_NAME", bean.getVarValue2());
				ds.set(row, "MEA_GRADE", bean.getVarValue3());
				ds.set(row, "GET_SUM", bean.getVarCnt());
				ds.set(row, "GET_SUM_M", bean.getVarCnt1());
				ds.set(row, "GET_SUM_P", bean.getVarCnt2());
				ds.set(row, "GET_SUM_C", bean.getVarCnt3());
			}

			for (int b = 0; b < itemCatagoryMatrix.size(); b++) {
				int bf_item = 0;
				String bf_fdacode = "";
				long totalCnt = 0L;
				long bf_cnt = 0L;
				totalCnt = ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt().longValue()
						+ ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt1().longValue();
				if (bf_item == ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id().intValue()
						&& bf_fdacode == ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue4())
					totalCnt += bf_cnt;
				float possibility = totalCnt;
				possibility = (possibility / ((float) ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt2().longValue()
						+ (float) ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt3().longValue())) * 100F;
				for (int c = 0; c < groupBack.getPossibility().size(); c++)
					if (((Possibility) groupBack.getPossibility().get(c)).getId() == possibilityId)
						if (((Possibility) groupBack.getPossibility().get(c)).getLikelihoodFm() <= possibility
								&& possibility < ((Possibility) groupBack.getPossibility().get(c)).getLikelihoodTo()) {
							System.out.println((new StringBuilder("[SimpleRiskDelegate].groupBack.getPossibility().get(c).4getId() "))
									.append(((Possibility) groupBack.getPossibility().get(c)).getId()).append("  ")
									.append(((Possibility) groupBack.getPossibility().get(c)).getClassName()).append("  ").append(parentId)
									.append("  ").append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id()).append(" count ")
									.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt()).append(" ")
									.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMedicalCount()).toString());
						} else {
							for (int k = 0; k < itemList.size(); k++) {
								String mea_class_no = ds.getString(k, "MEA_CLASS_NO");
								String mea_grade = ds.getString(k, "MEA_GRADE");
								if (mea_class_no.equals(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue1())
										&& mea_grade.equals(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue3())) {
									int cellVal = ds.getInt(k, "GET_SUM");
									int get_sum_m = ds.getInt(k, "GET_SUM_M");
									int get_sum_p = ds.getInt(k, "GET_SUM_P");
									int get_sum_c = ds.getInt(k, "GET_SUM_C");
									System.out.println((new StringBuilder("[SimpleRiskDelegate].groupBack.getPossibility().get(c).3getId() "))
											.append(((Possibility) groupBack.getPossibility().get(c)).getId()).append("  ")
											.append(((Possibility) groupBack.getPossibility().get(c)).getClassName()).append("  ").append(parentId)
											.append("  ").append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMeb_item_id())
											.append(" count ").append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt())
											.append(" cellVal ").append(cellVal).append("  ")
											.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMedicalCount()).append(" mea_class_no ")
											.append(mea_class_no).append(" itemCatagoryMatrix.get(b).getVarValue1() ")
											.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue1()).append(" mea_grade ")
											.append(mea_grade).append(" itemCatagoryMatrix.get(b).getVarValue3() ")
											.append(((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarValue3()).toString());
									ds.set(k, "GET_SUM",
											(long) cellVal - ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getVarCnt().longValue());
									ds.set(k, "GET_SUM_M",
											(long) get_sum_m - ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getMedicalCount().longValue());
									ds.set(k, "GET_SUM_P",
											(long) get_sum_p - ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getPatientCount().longValue());
									ds.set(k, "GET_SUM_C",
											(long) get_sum_c - ((SimpleSideeffectReport) itemCatagoryMatrix.get(b)).getComponentCount().longValue());
								}
							}

						}

			}

			o_xpData.addDataSet(ds);
			nErrorCode = 0;
			strErrorMsg = "7";
			HttpPlatformResponse res = new HttpPlatformResponse(respnse);
			res.setData(o_xpData);
			try {
				res.sendData();
			} catch (PlatformException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (Throwable th) {
			System.out.println(th.getMessage());
			nErrorCode = -1;
			strErrorMsg = th.getMessage();
		}
	}

	private PropertiesDAO propertiesDAO;
	private ItemCategoryDAO itemCategoryDAO;
	private SimpleSideeffectReportDAO sideEffectDAO;
	private SafetyDAO safetyDAO;
	private ItemDAO itemDAO;
	private ItemCategoryGroupDAO itemCategoryGroupDAO;
}

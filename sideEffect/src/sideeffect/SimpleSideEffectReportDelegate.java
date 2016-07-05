// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideEffectReportDelegate.java

package sideeffect;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.servlet.ModelAndView;

import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import com.tobesoft.xplatform.tx.HttpPlatformRequest;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformException;

import abstraction.SimpleDelegate;
import edu.emory.mathcs.backport.java.util.Collections;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import kr.co.sgis.legacy.common.Function;
import member.Member;
import net.sf.json.JSONArray;
import properties.JuncSideEffectReportTypes;
import properties.JuncSideEffectReportTypes2;
import properties.PropertiesDAO;
import properties.ReporterType;
import properties.SimpleCausality;
import properties.SimpleCountryReportedIn;
import properties.SimpleGender;
import properties.SimpleReportFollowUpAction;
import properties.SimpleReportStatus;
import properties.SimpleReportType2;
import properties.SimpleReporterType;
import properties.SimpleSideEffectReportMebTypeInfo;
import report.Report;
import report.SideeffectReportVO;
import report.SimpleSideeffectReport;
import report.SimpleSideeffectReportDAO;
import system.io.Attachment;
import system.io.AttachmentDAO;
import system.io.AttachmentServiceProvider;
import system.io.SimpleAttachment;
import system.io.SimpleJuncRepoerAttachment;

// Referenced classes of package sideeffect:
//            SimpleSideeffectResult, SimpleSideeffectCause, JuncSideEffectComponentCode, JuncSideEffectMedicalCode, 
//            JuncSideEffectPatientCondition, SimpleSideEffectReportTypeDate, JuncSideEffectResult, JuncSideEffectCause, 
//            SideeffectResult, SideeffectCause

public class SimpleSideEffectReportDelegate extends SimpleDelegate {

	public SimpleSideEffectReportDelegate() {
	}

	public CommonsFileUploadSupport getMultipartResolver() {
		return multipartResolver;
	}

	public void setMultipartResolver(CommonsFileUploadSupport multipartResolver) {
		this.multipartResolver = multipartResolver;
	}

	public AttachmentServiceProvider getAttachmentServiceProvider() {
		return attachmentServiceProvider;
	}

	public void setAttachmentServiceProvider(AttachmentServiceProvider attachmentServiceProvider) {
		this.attachmentServiceProvider = attachmentServiceProvider;
	}

	public SimpleSideeffectReportDAO getSideEffectDAO() {
		return sideEffectDAO;
	}

	public void setSideEffectDAO(SimpleSideeffectReportDAO sideEffectDAO) {
		this.sideEffectDAO = sideEffectDAO;
	}

	public PropertiesDAO getPropertiesDAO() {
		return propertiesDAO;
	}

	public void setPropertiesDAO(PropertiesDAO propertiesDAO) {
		this.propertiesDAO = propertiesDAO;
	}

	public AttachmentDAO getAttachmentDAO() {
		return attachmentDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[SimpleSideEffectReportDelegate].list().");
		ModelAndView mav = null;
		mav = new ModelAndView();
		String sc_0 = Function.nullChk(request.getParameter("sc_0"), "");
		String sv_0 = Function.nullChk(request.getParameter("sv_0"), "");
		String logicalOperator_0 = Function.nullChk(request.getParameter("logicalOperator_0"), "");
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].list().sc_0  ")).append(sc_0).toString());
		List list = null;
		setDefaultViewSet(mav, request);
		mav.addObject("contentName", "/view/jsp/sideEffect/xp_sideEffectList1.jsp");
		mav.addObject("popYN", "N");
		mav.addObject("sc_0", sc_0);
		mav.addObject("sv_0", sv_0);
		mav.addObject("logicalOperator_0", logicalOperator_0);
		return mav;
	}

	public ModelAndView poplist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String popName = Function.nullChk(request.getParameter("popName"), "");
		String fmDate = Function.nullChk(request.getParameter("fmDate"), "");
		String toDate = Function.nullChk(request.getParameter("toDate"), "");
		String reportType = Function.nullChk(request.getParameter("reportType"), "");
		String codeArr = Function.nullChk(request.getParameter("codeArr"), "");
		String codeName = Function.nullChk(request.getParameter("codeName"), "");
		String reporterTypeArr = Function.nullChk(request.getParameter("reporterTypeArr"), "");
		String itemSeq = Function.nullChk(request.getParameter("itemSeq"), "0");
		mav.setViewName("/view/jsp/sideEffect/xp_sideEffectList1.jsp");
		mav.addObject("popName", popName);
		mav.addObject("fmDate", fmDate);
		mav.addObject("toDate", toDate);
		mav.addObject("reportType", reportType);
		mav.addObject("codeArr", codeArr);
		mav.addObject("codeName", codeName);
		mav.addObject("itemSeq", itemSeq);
		mav.addObject("reporterTypeArr", reporterTypeArr);
		mav.addObject("popYN", "Y");
		return mav;
	}

	public ModelAndView createPage(HttpServletRequest request, HttpServletResponse response) {
		List countriesReportedIn = propertiesDAO.list(SimpleCountryReportedIn.class);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].createPage().countriesReportedIn.size() : "))
				.append(countriesReportedIn.size()).toString());
		List reportTypes = propertiesDAO.list(SimpleReportType2.class);
		List reporterTypes = propertiesDAO.list(SimpleReporterType.class);
		List gender = sideEffectDAO.list(new SimpleGender());
		List sideeffectResult = sideEffectDAO.list(new SimpleSideeffectResult());
		List sideeffectCause = sideEffectDAO.list(new SimpleSideeffectCause());
		List causality = propertiesDAO.list(SimpleCausality.class);
		List reportStatus = propertiesDAO.list(SimpleReportStatus.class);
		List reportFollowUpAction = propertiesDAO.list(SimpleReportFollowUpAction.class);
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("contentName", "/view/jsp/sideEffect/sideEffectCreate1.jsp");
		mav.addObject("countriesReportedIn", countriesReportedIn);
		mav.addObject("reportTypes", reportTypes);
		mav.addObject("reporterTypes", reporterTypes);
		mav.addObject("gender", gender);
		mav.addObject("sideeffectResult", sideeffectResult);
		mav.addObject("sideeffectCause", sideeffectCause);
		mav.addObject("causality", causality);
		mav.addObject("reportStatus", reportStatus);
		mav.addObject("reportFollowUpAction", reportFollowUpAction);
		String titleImg = "view/style/images/title/sub01_01.jpg";
		mav.addObject("titleImg", titleImg);
		return mav;
	}

	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		long privilegeId = -1L;
		ModelAndView mav = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		int countryReportedIn = Function.nullChk(multipartRequest.getParameter("countryReportedIn"), 0);
		List<Integer> reportType = null;
		reportType = Function.nullChk(multipartRequest.getParameterValues("reportType"), 0);
		for (Integer i : reportType) {
			System.out.println("[SimpleSideEffectReportDelegate].reportType  " + i.intValue());
		}

		String arrReportDate[] = request.getParameterValues("arrReportDate");
		List listReportDate = new ArrayList();
		for (int i = 0; i < arrReportDate.length; i++)
			if (arrReportDate[i].length() > 0 && i != 0)
				listReportDate.add(arrReportDate[i]);

		String mreport_date = Function.nullChk(multipartRequest.getParameter("mreport_date"), "");
		List<Integer> reporterType = null;
		reporterType = Function.nullChk(multipartRequest.getParameterValues("reporterType"), 0);
		String company_name = Function.nullChk(multipartRequest.getParameter("company_name"), "");
		String representatives = Function.nullChk(multipartRequest.getParameter("representatives"), "");
		String manager = Function.nullChk(multipartRequest.getParameter("manager"), "");
		String report_tel = Function.nullChk(multipartRequest.getParameter("report_tel"), "");
		String fax = Function.nullChk(multipartRequest.getParameter("fax"), "");
		String email = Function.nullChk(multipartRequest.getParameter("email"), "");
		String report_address = Function.nullChk(multipartRequest.getParameter("report_address"), "");
		int meb_item_manuf_id = Function.nullChk(multipartRequest.getParameter("meb_item_manuf_id"), 0);
		int country_manufactured_id = Function.nullChk(multipartRequest.getParameter("country_manufactured_id"), 0);
		String manufacturer = Function.nullChk(multipartRequest.getParameter("manufacturer"), "");
		String patient_name = Function.nullChk(multipartRequest.getParameter("patient_name"), "");
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].create().patient_name  ")).append(patient_name).toString());
		int patient_gender = Function.nullChk(multipartRequest.getParameter("patient_gender"), 0);
		int patient_age = Function.nullChk(multipartRequest.getParameter("patient_age"), 0);
		String patient_extra_info = Function.nullChk(multipartRequest.getParameter("patient_extra_info"), "");
		System.out
				.println((new StringBuilder("[SimpleSideEffectReportDelegate].create().patient_extra_info  ")).append(patient_extra_info).toString());
		String side_effect_first_date = Function.nullChk(multipartRequest.getParameter("SIDE_EFFECT_FIRST_DATE"), "");
		String side_effect_generation_date = Function.nullChk(multipartRequest.getParameter("SIDE_EFFECT_GENERATION_DATE"), "");
		String side_effect_last_date = Function.nullChk(multipartRequest.getParameter("SIDE_EFFECT_LAST_DATE"), "");
		List sideeffectResult = null;
		sideeffectResult = Function.nullChk(multipartRequest.getParameterValues("sideeffectResult"), 0);
		List sideeffectCause = null;
		sideeffectCause = Function.nullChk(multipartRequest.getParameterValues("sideeffectCause"), 0);
		int causality_id = Function.nullChk(multipartRequest.getParameter("CAUSALITY_ID"), 0);
		String followup = Function.nullChk(multipartRequest.getParameter("followup"), "");
		int report_status = Function.nullChk(multipartRequest.getParameter("report_status"), 0);
		String organization = Function.nullChk(multipartRequest.getParameter("organization"), "");
		String organization_tel = Function.nullChk(multipartRequest.getParameter("organization_tel"), "");
		String organization_address = Function.nullChk(multipartRequest.getParameter("organization_address"), "");
		String extra_info = Function.nullChk(multipartRequest.getParameter("extra_info"), "");
		String patient_code_id = Function.nullChk(multipartRequest.getParameter("patient_code_id"), "");
		String medical_code_id = Function.nullChk(multipartRequest.getParameter("medical_code_id"), "");
		String component_id = Function.nullChk(multipartRequest.getParameter("component_id"), "");
		int document_number = Function.nullChk(multipartRequest.getParameter("document_number"), 0);
		String reporter_etc = Function.nullChk(multipartRequest.getParameter("reporterEtc"), "");
		String cause_etc = Function.nullChk(multipartRequest.getParameter("causeEtc"), "");
		String result_etc = Function.nullChk(multipartRequest.getParameter("resultEtc"), "");
		String serial_number = Function.nullChk(multipartRequest.getParameter("serial_number"), "");
		String extra_info2 = Function.nullChk(multipartRequest.getParameter("extra_info2"), "");
		int follow_up_action = Function.nullChk(multipartRequest.getParameter("follow_up_action"), 0);
		String followUpActionEtc = Function.nullChk(multipartRequest.getParameter("followUpActionEtc"), "");
		String first_modified = Function.nullChk(multipartRequest.getParameter("first_modified"), "");
		String mebTypeInfoSelect = Function.nullChk(multipartRequest.getParameter("mebTypeInfoSelect"), "");
		String side_result_from_reporter = Function.nullChk(multipartRequest.getParameter("side_result_from_reporter"), "");
		String side_cause_from_reporter = Function.nullChk(multipartRequest.getParameter("side_cause_from_reporter"), "");
		String patient_condition_from_reporter = Function.nullChk(multipartRequest.getParameter("patient_condition_from_reporter"), "");
		String meddev_code_from_reporter = Function.nullChk(multipartRequest.getParameter("meddev_code_from_reporter"), "");
		String omponent_code_from_reporter = Function.nullChk(multipartRequest.getParameter("omponent_code_from_reporter"), "");
		String causality_from_reporter = Function.nullChk(multipartRequest.getParameter("causality_from_reporter"), "");
		String kfda_followup = Function.nullChk(multipartRequest.getParameter("kfda_followup"), "");
		for (int i = 0; i < reporterType.size(); i++)
			System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].create.reporterType  "))
					.append(((Integer) reporterType.get(i)).intValue()).toString());

		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date parseStrFirst_date = null;
		Date parseGeneration_date = null;
		Date parseLastDate = null;
		Date parseMreport_date = null;
		Date parsFirst_modified = null;
		String strFirst_date = "";
		String strGeneration_date = "";
		String strside_effect_last_date = "";
		String strmreport_date = "";
		String strFirst_modified = "";
		try {
			if (side_effect_first_date.length() > 0)
				parseStrFirst_date = dateFormat.parse(side_effect_first_date);
			if (side_effect_generation_date.length() > 0)
				parseGeneration_date = dateFormat.parse(side_effect_generation_date);
			if (side_effect_last_date.length() > 0)
				parseLastDate = dateFormat.parse(side_effect_last_date);
			if (mreport_date.length() > 0) {
				System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].create().mreport_date  ")).append(mreport_date).toString());
				parseMreport_date = dateFormat.parse(mreport_date);
			}
			if (first_modified.length() > 0)
				parsFirst_modified = dateFormat.parse(first_modified);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleSideeffectReport simpleSideeffectReport = new SimpleSideeffectReport();
		simpleSideeffectReport.setColCountryReportedIn(Integer.valueOf(countryReportedIn));
		simpleSideeffectReport.setCompany_name(company_name);
		simpleSideeffectReport.setRepresentatives(representatives);
		simpleSideeffectReport.setManager(manager);
		simpleSideeffectReport.setReport_tel(report_tel);
		simpleSideeffectReport.setFax(fax);
		simpleSideeffectReport.setEmail(email);
		simpleSideeffectReport.setReport_address(report_address);
		simpleSideeffectReport.setMeb_item_id(Integer.valueOf(meb_item_manuf_id));
		simpleSideeffectReport.setCountry_manufactured_id(Integer.valueOf(country_manufactured_id));
		simpleSideeffectReport.setManufacturer(manufacturer);
		simpleSideeffectReport.setPatient_name(patient_name);
		simpleSideeffectReport.setPatient_gender(Integer.valueOf(patient_gender));
		simpleSideeffectReport.setPatient_age(Integer.valueOf(patient_age));
		simpleSideeffectReport.setPatient_extra_info(patient_extra_info);
		if (side_effect_first_date.length() > 0)
			simpleSideeffectReport.setSide_effect_first_date(parseStrFirst_date);
		if (side_effect_generation_date.length() > 0)
			simpleSideeffectReport.setSide_effect_generation_date(parseGeneration_date);
		if (side_effect_last_date.length() > 0)
			simpleSideeffectReport.setSide_effect_last_date(parseLastDate);
		simpleSideeffectReport.setCausality_id(Integer.valueOf(causality_id));
		simpleSideeffectReport.setFollowup(followup);
		simpleSideeffectReport.setReport_status(Integer.valueOf(report_status));
		simpleSideeffectReport.setOrganization(organization);
		simpleSideeffectReport.setOrganization_tel(organization_tel);
		simpleSideeffectReport.setOrganization_address(organization_address);
		simpleSideeffectReport.setExtra_info(extra_info);
		if (mreport_date.length() > 0)
			simpleSideeffectReport.setMreport_date(parseMreport_date);
		simpleSideeffectReport.setDocument_number(Double.valueOf(document_number));
		if (first_modified.length() > 0)
			simpleSideeffectReport.setFirst_modified(parsFirst_modified);
		else
			simpleSideeffectReport.setFirst_modified(curDate);
		simpleSideeffectReport.setReporter_etc(reporter_etc);
		simpleSideeffectReport.setCause_etc(cause_etc);
		simpleSideeffectReport.setResult_etc(result_etc);
		simpleSideeffectReport.setSerial_number(serial_number);
		simpleSideeffectReport.setExtra_info2(extra_info2);
		simpleSideeffectReport.setFollow_up_action(Integer.valueOf(follow_up_action));
		simpleSideeffectReport.setFollowUpActionEtc(followUpActionEtc);
		simpleSideeffectReport.setSide_result_from_reporter(side_result_from_reporter);
		simpleSideeffectReport.setSide_cause_from_reporter(side_cause_from_reporter);
		simpleSideeffectReport.setPatient_condition_from_reporter(patient_condition_from_reporter);
		simpleSideeffectReport.setMeddev_code_from_reporter(meddev_code_from_reporter);
		simpleSideeffectReport.setOmponent_code_from_reporter(omponent_code_from_reporter);
		simpleSideeffectReport.setCausality_from_reporter(causality_from_reporter);
		simpleSideeffectReport.setKfda_followup(kfda_followup);
		System.out.println((new StringBuilder("[SimpleSideEffectDelegate].create().mreport_date  ")).append(mreport_date).toString());
		if (mreport_date.length() > 0) {
			simpleSideeffectReport.setReport_year(mreport_date.substring(0, 4));
			simpleSideeffectReport.setReport_month(mreport_date.substring(5, 7));
		} else {
			simpleSideeffectReport.setReport_year("");
			simpleSideeffectReport.setReport_month("");
		}
		int addSideEffectId = sideEffectDAO.add(simpleSideeffectReport);
		int type = 1;
		long reportTrpesAddId = reportTypesAdd(addSideEffectId, reportType, listReportDate);
		long reporterTrpesAddId = reporterTypesAdd(addSideEffectId, reporterType, type);
		long sideEffectResultAddId = sideEffectResultAdd(addSideEffectId, sideeffectResult);
		long sideEffectCauseAddId = sideEffectCauseAdd(addSideEffectId, sideeffectCause);
		long mebTypeInfoSelectAdd;
		if (!patient_code_id.equals("0"))
			mebTypeInfoSelectAdd = sideEffectPatientAdd(addSideEffectId, patient_code_id);
		if (!medical_code_id.equals("0"))
			mebTypeInfoSelectAdd = sideEffectmedicalCodeAdd(addSideEffectId, medical_code_id);
		if (!component_id.equals("0"))
			mebTypeInfoSelectAdd = sideEffectComponentCodeAdd(addSideEffectId, component_id);
		mebTypeInfoSelectAdd = mebTypeInfoSelectAdd(addSideEffectId, mebTypeInfoSelect);
		List result = attachment(request, response, addSideEffectId);
		int juncReportAttachmentAddId = juncReportAttachment(addSideEffectId, result);
		out.println("<script language=javascript>");
		out.println("alert('\uB4F1\uB85D\uB418\uC5C8\uC2B5\uB2C8\uB2E4.');");
		out.println("location.replace('sideEffectReport.do?action=list')");
		out.println("</script>");
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].create().mreport_date  ")).append(mreport_date).toString());
		return null;
	}

	long mebTypeInfoSelectAdd(int addSideEffectId, String mebTypeInfoSelect) {
		long returnId = -1L;
		SimpleSideEffectReportMebTypeInfo mebTypeInfo = new SimpleSideEffectReportMebTypeInfo();
		String mebTypeInfoSelectArr[] = mebTypeInfoSelect.split(",");
		for (int i = 0; i < mebTypeInfoSelectArr.length; i++)
			if (Integer.parseInt(mebTypeInfoSelectArr[i]) > 0) {
				mebTypeInfo.setMeddev_item_seq(Integer.parseInt(mebTypeInfoSelectArr[i]));
				mebTypeInfo.setReport_id(addSideEffectId);
				returnId = sideEffectDAO.mebTypeInfoSelectAdd(mebTypeInfo);
			}

		return returnId;
	}

	long sideEffectComponentCodeAdd(int safetyReportAddId, String component_id) {
		long returnId = -1L;
		JuncSideEffectComponentCode componentCode = new JuncSideEffectComponentCode();
		String component_id_arr[] = component_id.split(",");
		for (int i = 0; i < component_id_arr.length; i++)
			if (Integer.parseInt(component_id_arr[i]) > 0) {
				componentCode.setComponent_id(Integer.parseInt(component_id_arr[i]));
				componentCode.setReport_id(safetyReportAddId);
				returnId = sideEffectDAO.componentCodeAdd(componentCode);
			}

		return returnId;
	}

	long sideEffectmedicalCodeAdd(int safetyReportAddId, String medical_code_id) {
		long returnId = -1L;
		JuncSideEffectMedicalCode medicalCode = new JuncSideEffectMedicalCode();
		String medical_code_id_arr[] = medical_code_id.split(",");
		for (int i = 0; i < medical_code_id_arr.length; i++) {
			medicalCode.setMedical_id(Integer.parseInt(medical_code_id_arr[i]));
			medicalCode.setReport_id(safetyReportAddId);
			returnId = sideEffectDAO.medicalCodeAdd(medicalCode);
		}

		return returnId;
	}

	long sideEffectPatientAdd(int safetyReportAddId, String patient_code_id) {
		long returnId = -1L;
		JuncSideEffectPatientCondition patientCondition = new JuncSideEffectPatientCondition();
		System.out.println(
				(new StringBuilder("[SimpleSideEffectreportDelegate].sideEffectPatientAdd().patient_code_id  ")).append(patient_code_id).toString());
		String patient_code_arr[] = patient_code_id.split(",");
		for (int j = 0; j < patient_code_arr.length; j++)
			System.out.println((new StringBuilder("[SimpleSideEffectreportDelegate].sideEffectPatientAdd().patient_code_arr  "))
					.append(patient_code_arr[j]).toString());

		for (int i = 0; i < patient_code_arr.length; i++) {
			patientCondition.setPatient_id(Integer.parseInt(patient_code_arr[i]));
			patientCondition.setReport_id(safetyReportAddId);
			returnId = sideEffectDAO.patientCodeAdd(patientCondition);
		}

		return returnId;
	}

	long reportTypesAdd(int safetyReportAddId, List reportType, List listReportDate) {
		long returnId = -1L;
		JuncSideEffectReportTypes2 reportTypes = new JuncSideEffectReportTypes2();
		int i = 0;
		for (Iterator iterator = reportType.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			if (types.intValue() > 0) {
				System.out.println((new StringBuilder("types.intValue()  ")).append(types.intValue()).toString());
				reportTypes.setReport_type_id(types.intValue());
				reportTypes.setReport_id(safetyReportAddId);
				returnId = sideEffectDAO.reportTypesAdd(reportTypes);
				sideEffectReportTypeDateIns(safetyReportAddId, returnId, listReportDate, i, reportType);
			}
			i++;
		}

		return returnId;
	}

	void sideEffectReportTypeDateIns(long safetyReportAddId, long returnId, List listReportDate, int i, List reportType) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectReportTypeDateIns.i  ")).append(i).toString());
		Date parseReportDate = null;
		try {
			System.out.println((new StringBuilder("listReportDate.get(i).toString()  ")).append(listReportDate.get(i - 1).toString()).toString());
			parseReportDate = dateFormat.parse(listReportDate.get(i - 1).toString());
		} catch (ParseException e) {
			System.out.println((new StringBuilder("ParseException  ")).append(e.getMessage()).toString());
			e.printStackTrace();
		}
		SimpleSideEffectReportTypeDate date = new SimpleSideEffectReportTypeDate();
		date.setJunc_id(returnId);
		date.setReport_id(safetyReportAddId);
		date.setReport_date(parseReportDate);
		date.setReport_type_id(((Integer) reportType.get(i)).intValue());
		date.setReport_year(listReportDate.get(i - 1).toString().substring(0, 4));
		date.setReport_month(listReportDate.get(i - 1).toString().substring(5, 7));
		long result = -1L;
		result = sideEffectDAO.reportTypeDateAdd(date);
	}

	long reporterTypesAdd(int safetyReportAddId, List reporterType, int type) {
		long returnId = -1L;
		JuncSideEffectReportTypes reportTypes = new JuncSideEffectReportTypes();
		for (Iterator iterator = reporterType.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			reportTypes.setReport_type_id(types.intValue());
			reportTypes.setReport_id(safetyReportAddId);
			returnId = sideEffectDAO.reporterTypesAdd(reportTypes);
		}

		return returnId;
	}

	long sideEffectResultAdd(int safetyReportAddId, List sideeffectResult) {
		long returnId = -1L;
		JuncSideEffectResult juncSideEffectResult = new JuncSideEffectResult();
		for (Iterator iterator = sideeffectResult.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			juncSideEffectResult.setResult_id(types.intValue());
			juncSideEffectResult.setReport_id(safetyReportAddId);
			returnId = sideEffectDAO.sideEffectResultAdd(juncSideEffectResult);
		}

		return returnId;
	}

	long sideEffectCauseAdd(int safetyReportAddId, List sideeffectCause) {
		long returnId = -1L;
		JuncSideEffectCause juncSideEffectCause = new JuncSideEffectCause();
		for (Iterator iterator = sideeffectCause.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			juncSideEffectCause.setCause_id(types.intValue());
			juncSideEffectCause.setReport_id(safetyReportAddId);
			returnId = sideEffectDAO.sideEffectCauseAdd(juncSideEffectCause);
		}

		return returnId;
	}

	int juncReportAttachment(int safetyReportAddId, List reslut) {
		int returnId = -1;
		SimpleJuncRepoerAttachment juncRepoerAttachment = new SimpleJuncRepoerAttachment();
		for (Iterator iterator = reslut.iterator(); iterator.hasNext();) {
			Integer r = (Integer) iterator.next();
			juncRepoerAttachment.setType(1);
			juncRepoerAttachment.setTable_id(safetyReportAddId);
			juncRepoerAttachment.setAttachment_id(r.intValue());
			returnId = attachmentDAO.add(juncRepoerAttachment);
		}

		return returnId;
	}

	public ModelAndView popPatient(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String search = Function.nullChk(request.getParameter("search"), "");
		search = new String(search.getBytes("8859_1"), "UTF-8");
		search = (new StringBuilder("%")).append(search).append("%").toString();
		List patientList = propertiesDAO.listPopupPatient(search);
		mav.addObject("patientList", patientList);
		mav.addObject("patientListSize", Integer.valueOf(patientList.size()));
		mav.setViewName("/view/jsp/sideEffect/popup/popPatientCondition.jsp");
		return mav;
	}

	public ModelAndView selectPatient(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("/view/jsp/sideEffect/popup/popPatientConditionSelect.jsp");
		return mav;
	}

	public ModelAndView popMedical(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String search = Function.nullChk(request.getParameter("search"), "");
		search = new String(search.getBytes("8859_1"), "UTF-8");
		search = (new StringBuilder("%")).append(search).append("%").toString();
		List medicalDMCList = propertiesDAO.listPopupMedical(search);
		mav.addObject("medicalDMCList", medicalDMCList);
		mav.addObject("medicalDMCListSize", Integer.valueOf(medicalDMCList.size()));
		mav.setViewName("/view/jsp/sideEffect/popup/popMedicalDeviceMalfunctionCode.jsp");
		return mav;
	}

	public ModelAndView selectMedical(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("/view/jsp/sideEffect/popup/popMedicalDeciveMalfunctionCodeSelect.jsp");
		return mav;
	}

	public ModelAndView popComponent(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String search = Function.nullChk(request.getParameter("search"), "");
		search = new String(search.getBytes("8859_1"), "UTF-8");
		search = (new StringBuilder("%")).append(search).append("%").toString();
		List componentCodeList = propertiesDAO.listPopupComponent(search);
		mav.addObject("componentCodeList", componentCodeList);
		mav.setViewName("/view/jsp/sideEffect/popup/popComponentCode.jsp");
		return mav;
	}

	public ModelAndView selectComponent(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("/view/jsp/sideEffect/popup/popComponentCodeSelect.jsp");
		return mav;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		String titleImg = "view/style/images/title/sub01_01.jpg";
		int type = 1;
		List fileList = attachmentDAO.get(articleId, type);
		List countriesReportedIn = propertiesDAO.list(SimpleCountryReportedIn.class);
		List reportTypes = propertiesDAO.list(SimpleReportType2.class);
		List reporterTypes = propertiesDAO.list(SimpleReporterType.class);
		List gender = sideEffectDAO.list(new SimpleGender());
		List sideeffectResult = sideEffectDAO.list(new SimpleSideeffectResult());
		List sideeffectCause = sideEffectDAO.list(new SimpleSideeffectCause());
		List causality = propertiesDAO.list(SimpleCausality.class);
		List status = propertiesDAO.list(SimpleReportStatus.class);
		List reportFollowUpAction = propertiesDAO.list(SimpleReportFollowUpAction.class);
		Report rp = sideEffectDAO.read(articleId);
		List sotrReporterTypes = new ArrayList();
		List sotrReporterTypes2 = new ArrayList();
		int k = 0;
		for (int i = 0; i < rp.getReporterTypes().size(); i++)
			sotrReporterTypes2.add(Long.valueOf(((ReporterType) rp.getReporterTypes().get(i)).getId()));

		Collections.sort(sotrReporterTypes2);
		for (int i = 0; i < reporterTypes.size(); i++)
			if (rp.getReporterTypes().size() > k) {
				if (Long.valueOf(((SimpleReporterType) reporterTypes.get(i)).getId()) == sotrReporterTypes2.get(k)) {
					sotrReporterTypes.add(sotrReporterTypes2.get(k));
					k++;
				} else {
					sotrReporterTypes.add(Integer.valueOf(0));
				}
			} else {
				sotrReporterTypes.add(Integer.valueOf(0));
			}

		List sotrReporterDate = new ArrayList();
		List sotrReporterTypeDate = new ArrayList();
		List sotrReporterTypeDate2 = new ArrayList();
		List sotrReporterTypeDate3 = new ArrayList();
		k = 0;
		for (int i = 0; i < rp.getSideEffectReportTypeDate().size(); i++)
			if (rp.getSideEffectReportTypeDate().get(i) != null) {
				sotrReporterTypeDate2
						.add(Long.valueOf(((SimpleSideEffectReportTypeDate) rp.getSideEffectReportTypeDate().get(i)).getReport_type_id()));
				sotrReporterTypeDate3.add(((SimpleSideEffectReportTypeDate) rp.getSideEffectReportTypeDate().get(i)).getReport_date());
			}

		for (int i = 0; i < sotrReporterTypeDate2.size(); i++) {
			System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].update().sotrReporterTypeDate2.size()  "))
					.append(sotrReporterTypeDate2.size()).toString());
			System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].update().sotrReporterTypeDate2.get()  "))
					.append(sotrReporterTypeDate2.get(i).toString()).toString());
		}

		Collections.sort(sotrReporterTypeDate2);
		Collections.sort(sotrReporterTypeDate3);
		for (int i = 0; i < sotrReporterTypeDate2.size(); i++)
			if (sotrReporterTypeDate2.size() > k) {
				for (int j = 0; j < reporterTypes.size(); j++) {
					System.out.println((new StringBuilder(String.valueOf(((SimpleReporterType) reporterTypes.get(j)).getId()))).append("  ")
							.append(sotrReporterTypeDate2.get(k)).toString());
					if (Long.valueOf(((SimpleReporterType) reporterTypes.get(j)).getId()) == sotrReporterTypeDate2.get(k)) {
						System.out.println((new StringBuilder(String.valueOf(((SimpleReporterType) reporterTypes.get(j)).getId()))).append("  ")
								.append(sotrReporterTypeDate2.get(k)).toString());
						sotrReporterTypeDate.add(sotrReporterTypeDate2.get(k));
						sotrReporterDate.add(sotrReporterTypeDate3.get(k).toString());
						k++;
						if (sotrReporterTypeDate2.size() <= k)
							k--;
					} else {
						sotrReporterTypeDate.add(Integer.valueOf(0));
						sotrReporterDate.add(Integer.valueOf(0));
					}
				}

			} else {
				sotrReporterTypeDate.add(Integer.valueOf(0));
				sotrReporterDate.add(Integer.valueOf(0));
			}

		List sotrSideeffectResult = new ArrayList();
		List sotrSideeffectResult2 = new ArrayList();
		k = 0;
		for (int i = 0; i < rp.getSideeffectResult().size(); i++)
			sotrSideeffectResult2.add(Long.valueOf(((SideeffectResult) rp.getSideeffectResult().get(i)).getId()));

		Collections.sort(sotrSideeffectResult2);
		for (int i = 0; i < sideeffectResult.size(); i++)
			if (rp.getSideeffectResult().size() > k) {
				if (Long.valueOf(((SimpleSideeffectResult) sideeffectResult.get(i)).getId()) == sotrSideeffectResult2.get(k)) {
					sotrSideeffectResult.add(sotrSideeffectResult2.get(k));
					k++;
				} else {
					sotrSideeffectResult.add(Integer.valueOf(0));
				}
			} else {
				sotrSideeffectResult.add(Integer.valueOf(0));
			}

		List sotrCauses = new ArrayList();
		List sotrCauses2 = new ArrayList();
		k = 0;
		for (int i = 0; i < rp.getSideeffectCause().size(); i++)
			sotrCauses2.add(Integer.valueOf(((SideeffectCause) rp.getSideeffectCause().get(i)).getId()));

		Collections.sort(sotrCauses2);
		for (int i = 0; i < causality.size(); i++)
			if (rp.getSideeffectCause().size() > k) {
				if (Integer.valueOf(((SimpleCausality) causality.get(i)).getIntId()) == sotrCauses2.get(k)) {
					sotrCauses.add(sotrCauses2.get(k));
					k++;
				} else {
					sotrCauses.add(Integer.valueOf(0));
				}
			} else {
				sotrCauses.add(Integer.valueOf(0));
			}

		mav.addObject("article", rp);
		mav.addObject("kfda_followup", rp.getKfda_followup());
		mav.addObject("countriesReportedIn", countriesReportedIn);
		mav.addObject("reportTypes", reportTypes);
		mav.addObject("reporterTypes", reporterTypes);
		mav.addObject("gender", gender);
		mav.addObject("sideeffectResult", sideeffectResult);
		mav.addObject("sideeffectCause", sideeffectCause);
		mav.addObject("causality", causality);
		mav.addObject("status", status);
		mav.addObject("fileList", fileList);
		mav.addObject("titleImg", titleImg);
		mav.addObject("sotrReporterTypes", sotrReporterTypes);
		mav.addObject("sotrSideeffectResult", sotrSideeffectResult);
		mav.addObject("sotrCauses", sotrCauses);
		mav.addObject("sotrReporterTypeDate", sotrReporterTypeDate);
		mav.addObject("sotrReporterDate", sotrReporterDate);
		mav.addObject("reportFollowUpAction", reportFollowUpAction);
		mav.setViewName("/view/jsp/sideEffect/sideeffectUpdate1.jsp");
		return mav;
	}

	public ModelAndView modified(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		long privilegeId = -1L;
		ModelAndView mav = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Enumeration parameterNames = multipartRequest.getParameterNames();
		int id = Function.nullChk(multipartRequest.getParameter("id"), 0);
		String document_number = Function.nullChk(multipartRequest.getParameter("document_number"), "");
		double document_number2 = Double.parseDouble(document_number);
		int countryReportedIn = Function.nullChk(multipartRequest.getParameter("countryReportedIn"), 0);
		List reportType = null;
		reportType = Function.nullChk(multipartRequest.getParameterValues("reportType"), 0);
		String arrReportDate[] = request.getParameterValues("arrReportDate");
		List listReportDate = new ArrayList();
		for (int i = 0; i < arrReportDate.length; i++)
			if (arrReportDate[i].length() > 0 && i != 0)
				listReportDate.add(arrReportDate[i]);

		String mreport_date = Function.nullChk(multipartRequest.getParameter("mreport_date"), "");
		List reporterType = null;
		reporterType = Function.nullChk(multipartRequest.getParameterValues("reporterType"), 0);
		String company_name = Function.nullChk(multipartRequest.getParameter("company_name"), "");
		String representatives = Function.nullChk(multipartRequest.getParameter("representatives"), "");
		String manager = Function.nullChk(multipartRequest.getParameter("manager"), "");
		String report_tel = Function.nullChk(multipartRequest.getParameter("report_tel"), "");
		String fax = Function.nullChk(multipartRequest.getParameter("fax"), "");
		String email = Function.nullChk(multipartRequest.getParameter("email"), "");
		String report_address = Function.nullChk(multipartRequest.getParameter("report_address"), "");
		int meb_item_manuf_id = Function.nullChk(multipartRequest.getParameter("meb_item_manuf_id"), 0);
		int country_manufactured_id = Function.nullChk(multipartRequest.getParameter("country_manufactured_id"), 0);
		String manufacturer = Function.nullChk(multipartRequest.getParameter("manufacturer"), "");
		String patient_name = Function.nullChk(multipartRequest.getParameter("patient_name"), "");
		int patient_gender = Function.nullChk(multipartRequest.getParameter("patient_gender"), 0);
		int patient_age = Function.nullChk(multipartRequest.getParameter("patient_age"), 0);
		String patient_extra_info = Function.nullChk(multipartRequest.getParameter("patient_extra_info"), "");
		String side_effect_first_date = Function.nullChk(multipartRequest.getParameter("SIDE_EFFECT_FIRST_DATE"), "");
		String side_effect_generation_date = Function.nullChk(multipartRequest.getParameter("SIDE_EFFECT_GENERATION_DATE"), "");
		String side_effect_last_date = Function.nullChk(multipartRequest.getParameter("SIDE_EFFECT_LAST_DATE"), "");
		List sideeffectResult = null;
		sideeffectResult = Function.nullChk(multipartRequest.getParameterValues("sideeffectResult"), 0);
		List sideeffectCause = null;
		sideeffectCause = Function.nullChk(multipartRequest.getParameterValues("sideeffectCause"), 0);
		int causality_id = Function.nullChk(multipartRequest.getParameter("CAUSALITY_ID"), 0);
		String followup = Function.nullChk(multipartRequest.getParameter("followup"), "");
		int report_status = Function.nullChk(multipartRequest.getParameter("report_status"), 0);
		String organization = Function.nullChk(multipartRequest.getParameter("organization"), "");
		String organization_tel = Function.nullChk(multipartRequest.getParameter("organization_tel"), "");
		String organization_address = Function.nullChk(multipartRequest.getParameter("organization_address"), "");
		String extra_info = Function.nullChk(multipartRequest.getParameter("extra_info"), "");
		String patient_code_id = Function.nullChk(multipartRequest.getParameter("patient_code_id"), "");
		String medical_code_id = Function.nullChk(multipartRequest.getParameter("medical_code_id"), "");
		String component_id = Function.nullChk(multipartRequest.getParameter("component_id"), "");
		String reporter_etc = Function.nullChk(multipartRequest.getParameter("reporterEtc"), "");
		String cause_etc = Function.nullChk(multipartRequest.getParameter("causeEtc"), "");
		String result_etc = Function.nullChk(multipartRequest.getParameter("resultEtc"), "");
		String serial_number = Function.nullChk(multipartRequest.getParameter("serial_number"), "");
		String extra_info2 = Function.nullChk(multipartRequest.getParameter("extra_info2"), "");
		int follow_up_action = Function.nullChk(multipartRequest.getParameter("follow_up_action"), 0);
		String followUpActionEtc = Function.nullChk(multipartRequest.getParameter("followUpActionEtc"), "");
		String first_modified = Function.nullChk(multipartRequest.getParameter("first_modified"), "");
		String mebTypeInfoSelect = Function.nullChk(multipartRequest.getParameter("mebTypeInfoSelect"), "");
		String kfda_followup = Function.nullChk(multipartRequest.getParameter("kfda_followup"), "");
		List test1 = Function.nullChk(multipartRequest.getParameterValues("test1"), 0);
		System.out.println((new StringBuilder("test1.  ")).append(test1.size()).toString());
		Integer te;
		for (Iterator iterator = test1.iterator(); iterator.hasNext(); System.out
				.println((new StringBuilder("test1  ")).append(te.intValue()).toString()))
			te = (Integer) iterator.next();

		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String strFirst_date = "";
		String strGeneration_date = "";
		String strside_effect_last_date = "";
		String strmreport_date = "";
		Date parsFirst_modified = null;
		Date parseRepotrDate = null;
		Date parseStrFirst_date = null;
		Date parseGeneration_date = null;
		Date parseLastDate = null;
		Date parseMreport_date = null;
		String strFirst_modified = "";
		try {
			if (side_effect_first_date.length() > 0)
				parseStrFirst_date = dateFormat.parse(side_effect_first_date);
			if (side_effect_generation_date.length() > 0)
				parseGeneration_date = dateFormat.parse(side_effect_generation_date);
			if (side_effect_last_date.length() > 0)
				parseLastDate = dateFormat.parse(side_effect_last_date);
			if (mreport_date.length() > 0)
				parseMreport_date = dateFormat.parse(mreport_date);
			if (first_modified.length() > 0)
				parsFirst_modified = dateFormat.parse(first_modified);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleSideeffectReport simpleSideeffectReport = new SimpleSideeffectReport();
		SimpleSideeffectReport ReadSimpleSideeffectReport = (SimpleSideeffectReport) sideEffectDAO.read(id);
		simpleSideeffectReport.setId(id);
		simpleSideeffectReport.setDocument_number(Double.valueOf(Math.floor(document_number2)));
		simpleSideeffectReport.setColCountryReportedIn(Integer.valueOf(countryReportedIn));
		simpleSideeffectReport.setReportDate(parseRepotrDate);
		simpleSideeffectReport.setCompany_name(company_name);
		simpleSideeffectReport.setRepresentatives(representatives);
		simpleSideeffectReport.setManager(manager);
		simpleSideeffectReport.setReport_tel(report_tel);
		simpleSideeffectReport.setFax(fax);
		simpleSideeffectReport.setEmail(email);
		simpleSideeffectReport.setReport_address(report_address);
		simpleSideeffectReport.setMeb_item_id(Integer.valueOf(meb_item_manuf_id));
		simpleSideeffectReport.setCountry_manufactured_id(Integer.valueOf(country_manufactured_id));
		simpleSideeffectReport.setManufacturer(manufacturer);
		simpleSideeffectReport.setPatient_name(patient_name);
		simpleSideeffectReport.setPatient_gender(Integer.valueOf(patient_gender));
		simpleSideeffectReport.setPatient_age(Integer.valueOf(patient_age));
		simpleSideeffectReport.setPatient_extra_info(patient_extra_info);
		if (side_effect_first_date.length() > 0)
			simpleSideeffectReport.setSide_effect_first_date(parseStrFirst_date);
		if (side_effect_generation_date.length() > 0)
			simpleSideeffectReport.setSide_effect_generation_date(parseGeneration_date);
		if (side_effect_last_date.length() > 0)
			simpleSideeffectReport.setSide_effect_last_date(parseLastDate);
		simpleSideeffectReport.setCausality_id(Integer.valueOf(causality_id));
		simpleSideeffectReport.setFollowup(followup);
		simpleSideeffectReport.setReport_status(Integer.valueOf(report_status));
		simpleSideeffectReport.setOrganization(organization);
		simpleSideeffectReport.setOrganization_tel(organization_tel);
		simpleSideeffectReport.setOrganization_address(organization_address);
		simpleSideeffectReport.setExtra_info(extra_info);
		if (mreport_date.length() > 0) {
			simpleSideeffectReport.setMreport_date(parseMreport_date);
			simpleSideeffectReport.setReport_year(mreport_date.substring(0, 4));
			simpleSideeffectReport.setReport_month(mreport_date.substring(5, 7));
		} else {
			simpleSideeffectReport.setReport_year("");
			simpleSideeffectReport.setReport_month("");
		}
		if (first_modified.length() > 0)
			simpleSideeffectReport.setFirst_modified(parsFirst_modified);
		else
			simpleSideeffectReport.setFirst_modified(curDate);
		simpleSideeffectReport.setReporter_etc(reporter_etc);
		simpleSideeffectReport.setCause_etc(cause_etc);
		simpleSideeffectReport.setResult_etc(result_etc);
		simpleSideeffectReport.setSerial_number(serial_number);
		simpleSideeffectReport.setExtra_info2(extra_info2);
		simpleSideeffectReport.setFollow_up_action(Integer.valueOf(follow_up_action));
		simpleSideeffectReport.setFollowUpActionEtc(followUpActionEtc);
		simpleSideeffectReport.setKfda_followup(kfda_followup);
		simpleSideeffectReport.setSide_result_from_reporter(ReadSimpleSideeffectReport.getSide_result_from_reporter());
		simpleSideeffectReport.setSide_cause_from_reporter(ReadSimpleSideeffectReport.getSide_cause_from_reporter());
		simpleSideeffectReport.setPatient_condition_from_reporter(ReadSimpleSideeffectReport.getPatient_condition_from_reporter());
		simpleSideeffectReport.setMeddev_code_from_reporter(ReadSimpleSideeffectReport.getMeddev_code_from_reporter());
		simpleSideeffectReport.setOmponent_code_from_reporter(ReadSimpleSideeffectReport.getOmponent_code_from_reporter());
		simpleSideeffectReport.setCausality_from_reporter(ReadSimpleSideeffectReport.getCausality_from_reporter());
		int updateSideEffectId = sideEffectDAO.update(simpleSideeffectReport);
		int type = 1;
		sideEffectDAO.reportTypeDateDelete(id);
		long reportTrpesAddId = reportTypesAdd(id, reportType, listReportDate);
		long reporterTrpesAddId = reporterTypesAdd(id, reporterType, type);
		long sideEffectResultAddId = sideEffectResultAdd(id, sideeffectResult);
		long sideEffectCauseAddId = sideEffectCauseAdd(id, sideeffectCause);
		long mebTypeInfoSelectAdd;
		if (!patient_code_id.equals("0"))
			mebTypeInfoSelectAdd = sideEffectPatientAdd(id, patient_code_id);
		if (!medical_code_id.equals("0"))
			mebTypeInfoSelectAdd = sideEffectmedicalCodeAdd(id, medical_code_id);
		if (!component_id.equals("0"))
			mebTypeInfoSelectAdd = sideEffectComponentCodeAdd(id, component_id);
		mebTypeInfoSelectAdd = mebTypeInfoSelectAdd(id, mebTypeInfoSelect);
		List result = attachment(request, response, id);
		int juncReportAttachmentAddId = juncReportAttachment(id, result);
		out.println("<script language=javascript>");
		out.println("alert('\uC218\uC815\uB418\uC5C8\uC2B5\uB2C8\uB2E4.');");
		out.println((new StringBuilder("location.replace('sideEffectReport.do?action=read&articleId=")).append(id).append("')").toString());
		out.println("</script>");
		return null;
	}

	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		long privilegeId = -1L;
		if (session.getAttribute("user") != null) {
			Member objMember = (Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uB85C\uADF8\uC778 \uD6C4 \uC0AD\uC81C \uAC00\uB2A5\uD569\uB2C8\uB2E4');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
		if (privilegeId != 2L) {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'sideEffectReport.do?action=list'");
			out.println("</script>");
			return null;
		}
		ModelAndView mav = new ModelAndView();
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		List fileId = Function.nullChk(request.getParameterValues("fileId"), 0);
		sideEffectDAO.reportTypeDateDelete(articleId);
		Integer id;
		for (Iterator iterator = fileId.iterator(); iterator.hasNext(); sideEffectDAO.fileDelete(id.intValue())) {
			id = (Integer) iterator.next();
			System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].delete.fileId  ")).append(id.intValue()).toString());
		}

		sideEffectDAO.delete(articleId);
		out.println("<script language=javascript>");
		out.println("alert('\uC0AD\uC81C\uB418\uC5C8\uC2B5\uB2C8\uB2E4.');");
		out.println("window.close()");
		out.println("</script>");
		return null;
	}

	public ModelAndView fileDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ModelAndView mav = new ModelAndView();
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		int fileId = Function.nullChk(request.getParameter("fileId"), 0);
		String fullURL = Function.nullChk(request.getParameter("fullURL"), "");
		String fileUploadPath = request.getSession().getServletContext().getInitParameter("physicalUploadPath");
		sideEffectDAO.fileDelete(fileId);
		sideEffectDAO.juncReportAttachmentDelete(fileId);
		File f = new File((new StringBuilder(String.valueOf(fileUploadPath))).append(fullURL).toString());
		if (f.exists()) {
			f.delete();
			System.out.println("[SimpleSideEffectReportDelegate].fileDelete().\uD30C\uC77C\uC0AD\uC81C\uC131\uACF5");
		} else {
			System.out.println("[SimpleSideEffectReportDelegate].fileDelete().\uD30C\uC77C\uC0AD\uC81C\uC2E4\uD328");
		}
		out.println(fileId);
		out.close();
		return mav;
	}

	List attachment(HttpServletRequest request, HttpServletResponse response, int returnAddNum) {
		System.out.println("SimpleSideEffectReportDelegate.attachment().begin()");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		int result = 0;
		List addIdArr = new ArrayList();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		Date current = new Date();
		String date = formater.format(current);
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String fileUploadPath = request.getSession().getServletContext().getInitParameter("physicalUploadPath");
		System.out.println((new StringBuilder("fileUploadPath  ")).append(fileUploadPath).toString());
		String upPath0 = (new StringBuilder(String.valueOf(fileUploadPath))).append(Function.fnUploadPath("")).toString();
		String upPath1 = (new StringBuilder(String.valueOf(upPath0))).append("/").append("sideEffectReportFile").append("/").append(returnAddNum)
				.toString();
		Function.fnFolderChk(upPath0);
		Function.fnFolderChk(upPath1);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].upPath1  ")).append(upPath1).toString());
		String full_url = "";
		Iterator fileNameIter = multipartRequest.getFileNames();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String organizedfilePath = "";
		UUID randomeUUID = null;
		try {
			int i = 0;
			while (fileNameIter.hasNext()) {
				String key = (String) fileNameIter.next();
				MultipartFile file = multipartRequest.getFile(key);
				String originaFileName = file.getOriginalFilename();
				String contentType = file.getContentType();
				long fileSize = file.getSize();
				String fileType = "";
				double systemUploadSize = attachmentServiceProvider.getUploadSize();
				systemUploadSize = systemUploadSize * 1024D * 1024D;
				Double db = new Double(systemUploadSize);
				multipartResolver.setMaxUploadSize(db.longValue());
				System.out.println((new StringBuilder("SimpleSideEffectReportDelegate.attachment().System limit := ")).append(systemUploadSize)
						.append(", uploaded file size := ").append(fileSize).toString());
				if ((double) fileSize > systemUploadSize)
					throw new Exception((new StringBuilder("Uploaded file size exceeds the system limit. Limit := ")).append(systemUploadSize)
							.append(", uploaded file size := ").append(fileSize).toString());
				String fileName = multipartRequest.getParameter((new StringBuilder("fileName_")).append(i).toString());
				i++;
				System.out.println((new StringBuilder("[SimpleSideeffectReportDelegate].attachment.fileName  ")).append(fileName).toString());
				originaFileName = originaFileName.toLowerCase();
				System.out.println((new StringBuilder("[SimpleSideeffectReportDelegate].attachment.contentType  ")).append(contentType).toString());
				System.out.println(
						(new StringBuilder("[SimpleSideeffectReportDelegate].attachment.originaFileName  ")).append(originaFileName).toString());
				int listIndex = originaFileName.lastIndexOf(".");
				int fileNameLength = originaFileName.length();
				String extension = originaFileName.substring(listIndex + 1, fileNameLength);
				inputStream = file.getInputStream();
				if (fileSize > 0L) {
					result++;
					File realUploadDir = new File(upPath1);
					if (!realUploadDir.exists())
						realUploadDir.mkdirs();
					randomeUUID = UUID.randomUUID();
					String uid = randomeUUID.toString();
					String strUid = (new StringBuilder(String.valueOf(uid.substring(0, 7)))).append(uid.substring(14, 17)).toString();
					System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].attachment.strUid  ")).append(strUid).toString());
					full_url = (new StringBuilder("/upload/sideEffectReportFile/")).append(returnAddNum).toString();
					full_url = (new StringBuilder(String.valueOf(full_url))).append("/").append(strUid).append("_").append(file.getOriginalFilename())
							.toString();
					System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].create.full_url  ")).append(full_url).toString());
					organizedfilePath = (new StringBuilder(String.valueOf(upPath1))).append("/").append(strUid).append("_")
							.append(file.getOriginalFilename()).toString();
					System.out.println(
							(new StringBuilder("[SimpleSideEffectReportDelegate].organizedfilePath  ")).append(organizedfilePath).toString());
					outputStream = new FileOutputStream(organizedfilePath);
					int readByte = 0;
					byte buffer[] = new byte[8192];
					while ((readByte = inputStream.read(buffer, 0, 8120)) != -1)
						outputStream.write(buffer, 0, readByte);
					Attachment attVO = new SimpleAttachment();
					attVO.setExtension(extension);
					attVO.setFullURL(full_url);
					attVO.setName(file.getOriginalFilename());
					attVO.setPhysicalPath((new StringBuilder(String.valueOf(strUid))).append("_").append(file.getOriginalFilename()).toString());
					attVO.setRelativeLogicalPath("");
					attVO.setTable_id(returnAddNum);
					attVO.setType(1);
					attVO.setFileName(fileName);
					attVO.setLogical_name((new StringBuilder(String.valueOf(strUid))).append("_").append(file.getOriginalFilename()).toString());
					int returnAddAttachmentId = attachmentDAO.add(attVO);
					addIdArr.add(Integer.valueOf(returnAddAttachmentId));
					System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].attachment.returnAddAttachmentId  "))
							.append(returnAddAttachmentId).toString());
				}
			}
		} catch (Exception e) {
			System.out.println((new StringBuilder(" Exception  ")).append(e.getMessage()).toString());
			e.printStackTrace();
		}
		return addIdArr;
	}

	public ModelAndView treeTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("contentName", "/view/jsp/sideEffect/treeTest_hyh.jsp");
		setDefaultViewSet(mav, request);
		return mav;
	}

	public HashSet search(String sv) {
		HashSet hs = new HashSet();

		List<SimpleSideeffectReport> result = sideEffectDAO.countryReportedInSearch(sv);

		for (SimpleSideeffectReport report : result) {
			System.out.println("[SimpleSideEffectReportDelegate].result  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result2 = sideEffectDAO.reportTypeSearch(sv);

		for (SimpleSideeffectReport report : result2) {
			System.out.println("[SimpleSideEffectReportDelegate].result2  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result3 = sideEffectDAO.reporterTypeSearch(sv);

		for (SimpleSideeffectReport report : result3) {
			System.out.println("[SimpleSideEffectReportDelegate].result3  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result4 = sideEffectDAO.mebItemSearch(sv);

		for (SimpleSideeffectReport report : result4) {
			System.out.println("[SimpleSideEffectReportDelegate].result4  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result5 = sideEffectDAO.patientSearch(sv);

		for (SimpleSideeffectReport report : result5) {
			System.out.println("[SimpleSideEffectReportDelegate].result5  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result6 = sideEffectDAO.sideeffectResultSearch(sv);

		for (SimpleSideeffectReport report : result6) {
			System.out.println("[SimpleSideEffectReportDelegate].result6  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result7 = sideEffectDAO.sideeffectCauseSearch(sv);

		for (SimpleSideeffectReport report : result7) {
			System.out.println("[SimpleSideEffectReportDelegate].result7  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result8 = sideEffectDAO.causalitySearch(sv);

		for (SimpleSideeffectReport report : result8) {
			System.out.println("[SimpleSideEffectReportDelegate].result8  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSideeffectReport> result9 = sideEffectDAO.reportStatusSearch(sv);

		for (SimpleSideeffectReport report : result9) {
			System.out.println("[SimpleSideEffectReportDelegate].result9  " + report.getId());
			hs.add(report.getId());
		}

		return hs;
	}

	public ModelAndView goExcel(HttpServletRequest request, HttpServletResponse response)
			throws PlatformException, RowsExceededException, WriteException, IOException {
		String fileName;
		String path;
		byte b[];
		BufferedInputStream fin;
		BufferedOutputStream outs;
		ModelAndView mav = new ModelAndView();
		int sc = Function.nullChk(request.getParameter("reportType"), -1);
		String year1 = Function.nullChk(request.getParameter("year1"), "");
		String year2 = Function.nullChk(request.getParameter("year2"), "");
		String month1 = Function.nullChk(request.getParameter("month1"), "");
		String month2 = Function.nullChk(request.getParameter("month2"), "");
		int pgSize = Function.nullChk(request.getParameter("pgSize"), 0);
		int rowCount = Function.nullChk(request.getParameter("rowCount"), 0);
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String orderText = Function.nullChk(request.getParameter("orderText"), "");
		String order1 = Function.nullChk(request.getParameter("order"), "desc");
		String getPopYN = Function.nullChk(request.getParameter("gvPopYN"), "N");
		String scid = Function.nullChk(request.getParameter("scid"), "");
		String sv = Function.nullChk(request.getParameter("sv"), "");
		String option = Function.nullChk(request.getParameter("option"), "");
		String reporterTypes = Function.nullChk(request.getParameter("reporterTypes"), "");
		String getVpopNameArr = Function.nullChk(request.getParameter("gVpopName"), "");
		String getVcodeAllArr = Function.nullChk(request.getParameter("gVcodeArr"), "0");
		String getVitemSeq = Function.nullChk(request.getParameter("gVitemSeq"), "0");
		String VpopName = "";
		getPopYN = getPopYN.replace("\u2019", "");
		getVpopNameArr = getVpopNameArr.replace("\u2019", "");
		getVcodeAllArr = getVcodeAllArr.replace("\u2019", "");
		String paging = "n";
		int firstResult = Function.nullChk(request.getParameter("firstResult"), 0);
		int rowMaxValue = Function.nullChk(request.getParameter("rowMaxValue"), 0);
		if (year1.length() > 0) {
			year1 = year1.substring(0, 4);
			year2 = year2.substring(0, 4);
			month1 = month1.substring(4, 6);
			month2 = month2.substring(4, 6);
		}
		String temp[] = null;
		List arraySc = new ArrayList();
		List arraySv = new ArrayList();
		List arrayOption = new ArrayList();
		List arrayIdOrName = new ArrayList();
		String VpopNameArr[] = getVpopNameArr.split(",");
		String VcodeArr[] = getVcodeAllArr.split(",");
		for (int a = 0; a < VpopNameArr.length; a++) {
			String getVpopName = VpopNameArr[a];
			String getVcodeArr = VcodeArr[a];
			if (getPopYN.equals("Y")) {
				if (getVpopName.equals("popReporterType"))
					VpopName = "3";
				else if (getVpopName.equals("popMeaCompany"))
					VpopName = "11";
				else if (getVpopName.equals("popCountry"))
					VpopName = "17";
				else if (getVpopName.equals("popMeaClassNo"))
					VpopName = "13";
				else if (getVpopName.equals("popItemGrade"))
					VpopName = "15";
				else if (getVpopName.equals("popCausality"))
					VpopName = "35";
				else if (getVpopName.equals("popPatient") || getVpopName.equals("popPatientLevel"))
					VpopName = "28";
				else if (getVpopName.equals("popMedical") || getVpopName.equals("popMedicalLevel"))
					VpopName = "29";
				else if (getVpopName.equals("popComponent") || getVpopName.equals("popComponentLevel"))
					VpopName = "30";
				else if (getVpopName.equals("popCauseOfSideeffect"))
					VpopName = "27";
				else if (getVpopName.equals("popSideeffectResult"))
					VpopName = "26";
				else if (getVpopName.equals("popMeddevItem"))
					VpopName = "16";
				if (getPopYN.equals("Y") && !VpopName.equals("") && !getVcodeAllArr.equals("0") && !getVcodeAllArr.equals("")) {
					arraySc.add(VpopName);
					if (VpopNameArr.length < 2)
						arraySv.add(getVcodeAllArr);
					else
						arraySv.add(getVcodeArr);
					arrayOption.add("AND");
					arrayIdOrName.add("ID");
				}
			}
		}

		if (!getVitemSeq.equals("0") && getPopYN.equals("Y"))
			if (getVitemSeq.subSequence(0, 1).equals("I")) {
				arraySc.add("16");
				arraySv.add(getVitemSeq.substring(1, getVitemSeq.length()));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			} else if (getVitemSeq.subSequence(0, 1).equals("C")) {
				arraySc.add("13");
				arraySv.add(getVitemSeq.substring(1, getVitemSeq.length()));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			}
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].reporterTypes  ")).append(reporterTypes).toString());
		temp = null;
		Integer ds_chk = Integer.valueOf(0);
		String reporterTypeId = "";
		if (reporterTypes != null && reporterTypes.length() > 0) {
			temp = reporterTypes.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println(
							(new StringBuilder("1[SimpleSideEffectReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					reporterTypeId = (new StringBuilder(String.valueOf(reporterTypeId))).append(temp[i]).append(",").toString();
					ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
				}

			System.out.println((new StringBuilder("reporterTypeId====")).append(reporterTypeId).toString());
			if (ds_chk.intValue() > 0) {
				arraySc.add("3");
				arraySv.add(reporterTypeId.substring(0, reporterTypeId.length() - 1));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			}
		}
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].scid  ")).append(scid).toString());
		temp = null;
		if (scid != null && scid.length() > 0) {
			temp = scid.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println(
							(new StringBuilder("1[SimpleSideEffectReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					arraySc.add(temp[i]);
					arrayIdOrName.add("NAME");
				}

		}
		temp = null;
		if (sv != null && sv.length() > 0) {
			temp = sv.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println(
							(new StringBuilder("2[SimpleSideEffectReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					arraySv.add(temp[i]);
				}

		}
		temp = null;
		if (option != null && option.length() > 0) {
			temp = option.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println(
							(new StringBuilder("3[SimpleSideEffectReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					if (temp[i].equals("undefined"))
						arrayOption.add("AND");
					else
						arrayOption.add(temp[i]);
				}

		}
		List list = null;
		List list1 = null;
		List list2 = null;
		List list3 = null;
		List list4 = null;
		List list5 = null;
		list = sideEffectDAO.details_search2(new SideeffectReportVO(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1, year2, month1, month2,
				getPopYN, pgSize, firstResult, rowCount, rowMaxValue, pg, orderText, order1, paging);
		fileName = "\\sideeffect_temp1.xls";
		path = "C:\\sgi";
		Function.fnFolderChk(path);
		path = (new StringBuilder(String.valueOf(path))).append("\\hyh").toString();
		Function.fnFolderChk(path);
		WritableWorkbook workbook = Workbook.createWorkbook(new File((new StringBuilder(String.valueOf(path))).append(fileName).toString()));
		WritableSheet sheet1 = workbook.createSheet("sheet 1", 0);
		WritableCellFormat headFormat = new WritableCellFormat();
		headFormat.setAlignment(Alignment.LEFT);
		sheet1.setColumnView(0, 7);
		sheet1.setColumnView(1, 25);
		sheet1.setColumnView(2, 25);
		sheet1.setColumnView(3, 15);
		sheet1.setColumnView(4, 20);
		sheet1.setColumnView(5, 25);
		sheet1.setColumnView(6, 10);
		sheet1.setColumnView(7, 10);
		sheet1.setColumnView(8, 15);
		sheet1.setColumnView(9, 15);
		sheet1.setColumnView(10, 25);
		sheet1.setColumnView(11, 70);
		sheet1.setColumnView(12, 25);
		sheet1.setColumnView(13, 15);
		sheet1.setColumnView(14, 20);
		sheet1.setColumnView(15, 13);
		sheet1.setColumnView(16, 7);
		sheet1.setColumnView(17, 17);
		sheet1.setColumnView(18, 15);
		sheet1.setColumnView(19, 30);
		sheet1.setColumnView(20, 15);
		sheet1.setColumnView(21, 30);
		sheet1.setColumnView(22, 7);
		sheet1.setColumnView(23, 25);
		sheet1.setColumnView(24, 15);
		sheet1.setColumnView(25, 25);
		sheet1.setColumnView(26, 15);
		sheet1.setColumnView(27, 15);
		sheet1.setColumnView(28, 15);
		sheet1.setColumnView(30, 25);
		sheet1.setColumnView(31, 30);
		sheet1.setColumnView(32, 20);
		sheet1.setColumnView(33, 20);
		sheet1.setColumnView(34, 20);
		sheet1.setColumnView(35, 15);
		sheet1.setColumnView(36, 70);
		sheet1.setColumnView(37, 70);
		sheet1.setColumnView(38, 25);
		sheet1.setColumnView(39, 20);
		sheet1.setColumnView(41, 25);
		sheet1.setColumnView(45, 20);
		sheet1.setColumnView(46, 25);
		Label label1 = null;
		Label label2 = null;
		Label label3 = null;
		Label label4 = null;
		Label label5 = null;
		Label label6 = null;
		Label label7 = null;
		Label label8 = null;
		Label label9 = null;
		Label label10 = null;
		Label label11 = null;
		Label label12 = null;
		Label label13 = null;
		Label label14 = null;
		Label label15 = null;
		Label label16 = null;
		Label label17 = null;
		Label label18 = null;
		Label label19 = null;
		Label label20 = null;
		Label label21 = null;
		Label label22 = null;
		Label label23 = null;
		Label label24 = null;
		Label label25 = null;
		Label label26 = null;
		Label label27 = null;
		Label label28 = null;
		Label label29 = null;
		Label label30 = null;
		Label label31 = null;
		Label label32 = null;
		Label label33 = null;
		Label label34 = null;
		Label label35 = null;
		Label label36 = null;
		Label label37 = null;
		Label label38 = null;
		Label label39 = null;
		Label label40 = null;
		Label label41 = null;
		Label label42 = null;
		Label label43 = null;
		Label label44 = null;
		Label label45 = null;
		Label label46 = null;
		Label label47 = null;
		label1 = new Label(0, 0, "\uAD6C\uBD84");
		label2 = new Label(1, 0, "\uBCF4\uACE0\uAD6C\uBD84");
		label3 = new Label(2, 0, "\uBCF4\uACE0\uC77C\uC790");
		label4 = new Label(3, 0, "\uC2DD\uC57D\uCC98 \uBCF4\uACE0\uC77C\uC790");
		label5 = new Label(4, 0, "\uBCF4\uACE0\uC790 \uC720\uD615");
		label6 = new Label(5, 0, "\uC0C1\uD638\uBA85");
		label7 = new Label(6, 0, "\uB300\uD45C\uC790 \uC131\uBA85");
		label8 = new Label(7, 0, "\uB2F4\uB2F9\uC790\uBA85");
		label9 = new Label(8, 0, "\uC804\uD654");
		label10 = new Label(9, 0, "FAX");
		label11 = new Label(10, 0, "EMAIL");
		label12 = new Label(11, 0, "\uBCF4\uACE0\uC790\uC815\uBCF4 - \uC8FC\uC18C");
		label13 = new Label(12, 0, "\uB300\uC0C1\uC5C5\uCCB4\uBA85");
		label14 = new Label(13, 0, "\uC5C5\uD5C8\uAC00\uBC88\uD638");
		label15 = new Label(14, 0, "\uD488\uBAA9\uBA85");
		label16 = new Label(15, 0, "\uBD84\uB958\uBC88\uD638");
		label17 = new Label(16, 0, "\uB4F1\uAE09");
		label18 = new Label(17, 0, "\uD488\uBAA9\uD5C8\uAC00\uBC88\uD638");
		label19 = new Label(18, 0, "\uC81C\uC870\uAD6D");
		label20 = new Label(19, 0, "\uC81C\uC870\uC6D0");
		label21 = new Label(20, 0, "\uD615\uBA85");
		label22 = new Label(21, 0, "\uC81C\uC870\uBC88\uD638");
		label23 = new Label(22, 0, "\uC131\uBA85");
		label24 = new Label(23, 0, "\uC131\uBCC4");
		label25 = new Label(24, 0, "\uB098\uC774");
		label26 = new Label(25, 0, "\uAE30\uD0C0 \uD2B9\uC774\uC0AC\uD56D");
		label27 = new Label(26, 0, "\uCD5C\uCD08\uC778\uC9C0\uC77C\uC790");
		label28 = new Label(27, 0, "\uBD80\uC791\uC6A9 \uBC1C\uC0DD\uC77C\uC790");
		label29 = new Label(28, 0, "\uC885\uB8CC\uC77C\uC790");
		label30 = new Label(29, 0, "\uC9C4\uD589\uC0C1\uD669");
		label31 = new Label(30, 0, "\uBD80\uC791\uC6A9 \uACB0\uACFC \uBC0F \uC704\uD574\uC815\uB3C4");
		label32 = new Label(31, 0, "\uBD80\uC791\uC6A9 \uC6D0\uC778 \uBD84\uB958");
		label33 = new Label(32, 0, "\uD658\uC790\uBB38\uC81C\uCF54\uB4DC");
		label34 = new Label(33, 0, "\uC758\uB8CC\uAE30\uAE30\uBB38\uC81C\uCF54\uB4DC");
		label35 = new Label(34, 0, "\uAD6C\uC131\uC694\uC18C\uCF54\uB4DC");
		label36 = new Label(35, 0, "\uC778\uACFC\uAD00\uACC4");
		label37 = new Label(36, 0, "\uBD80\uC791\uC6A9 \uC6D0\uC778 \uBC0F \uB0B4\uC6A9");
		label38 = new Label(37, 0, "\uACBD\uACFC \uBC0F \uD6C4\uC18D \uC870\uCE58");
		label39 = new Label(38, 0, "\uC0AC\uD6C4\uC870\uCE58 \uACB0\uACFC");
		label40 = new Label(39, 0, "\uAE30\uAD00\uBA85");
		label41 = new Label(40, 0, "\uC5F0\uB77D\uCC98");
		label42 = new Label(41, 0, "\uBD80\uC791\uC6A9\uC815\uBCF4 - \uC8FC\uC18C");
		label43 = new Label(42, 0, "\uAE30\uD0C0\uC0AC\uD56D");
		label44 = new Label(43, 0, "\uB4F1\uB85D\uC77C");
		label45 = new Label(44, 0, "\uCCA8\uBD80\uC790\uB8CC");
		label46 = new Label(45, 0, "\uBB38\uC11C\uBC88\uD638");
		label47 = new Label(46, 0, "\uC2DD\uC57D\uCC98 \uD6C4\uC18D\uC870\uCE58");
		sheet1.addCell(label1);
		sheet1.addCell(label2);
		sheet1.addCell(label3);
		sheet1.addCell(label4);
		sheet1.addCell(label5);
		sheet1.addCell(label6);
		sheet1.addCell(label7);
		sheet1.addCell(label8);
		sheet1.addCell(label9);
		sheet1.addCell(label10);
		sheet1.addCell(label11);
		sheet1.addCell(label12);
		sheet1.addCell(label13);
		sheet1.addCell(label14);
		sheet1.addCell(label15);
		sheet1.addCell(label16);
		sheet1.addCell(label17);
		sheet1.addCell(label18);
		sheet1.addCell(label19);
		sheet1.addCell(label20);
		sheet1.addCell(label21);
		sheet1.addCell(label22);
		sheet1.addCell(label23);
		sheet1.addCell(label24);
		sheet1.addCell(label25);
		sheet1.addCell(label26);
		sheet1.addCell(label27);
		sheet1.addCell(label28);
		sheet1.addCell(label29);
		sheet1.addCell(label30);
		sheet1.addCell(label31);
		sheet1.addCell(label32);
		sheet1.addCell(label33);
		sheet1.addCell(label34);
		sheet1.addCell(label35);
		sheet1.addCell(label36);
		sheet1.addCell(label37);
		sheet1.addCell(label38);
		sheet1.addCell(label39);
		sheet1.addCell(label40);
		sheet1.addCell(label41);
		sheet1.addCell(label42);
		sheet1.addCell(label43);
		sheet1.addCell(label44);
		sheet1.addCell(label45);
		sheet1.addCell(label46);
		sheet1.addCell(label47);
		for (int i = 0; i < list.size(); i++) {
			int col = -1;
			int index = -1;
			String report_type2 = "";
			report_type2 = ((SideeffectReportVO) list.get(i)).getReport_type2();
			index = report_type2.lastIndexOf(",");
			if (index > 0)
				report_type2 = report_type2.substring(0, index);
			index = -1;
			String report_date = "";
			report_date = ((SideeffectReportVO) list.get(i)).getReport_date();
			index = report_date.lastIndexOf(",");
			if (index > 0)
				report_date = report_date.substring(0, index);
			String mreport_date = "";
			mreport_date = Function.nullChk(((SideeffectReportVO) list.get(i)).getMreport_date());
			index = -1;
			String reporter_type = "";
			reporter_type = ((SideeffectReportVO) list.get(i)).getReporter_type();
			index = reporter_type.lastIndexOf(",");
			if (index > 0)
				reporter_type = reporter_type.substring(0, index);
			index = -1;
			String result_value = "";
			result_value = Function.nullChk(((SideeffectReportVO) list.get(i)).getResult_value());
			index = result_value.lastIndexOf(",");
			if (index > -1)
				result_value = result_value.substring(0, index);
			index = -1;
			String cause_value = "";
			cause_value = Function.nullChk(((SideeffectReportVO) list.get(i)).getCause_value());
			index = cause_value.lastIndexOf(",");
			if (index > -1)
				cause_value = cause_value.substring(0, index);
			index = -1;
			String patientCode = "";
			patientCode = Function.nullChk(((SideeffectReportVO) list.get(i)).getPatientcode());
			index = patientCode.lastIndexOf(",");
			if (index > -1)
				patientCode = patientCode.substring(0, index);
			index = -1;
			String medicalCode = "";
			medicalCode = Function.nullChk(((SideeffectReportVO) list.get(i)).getMedicalcode());
			index = medicalCode.lastIndexOf(",");
			if (index > -1)
				medicalCode = medicalCode.substring(0, index);
			index = -1;
			String componentCode = "";
			componentCode = Function.nullChk(((SideeffectReportVO) list.get(i)).getComponetcode());
			index = componentCode.lastIndexOf(",");
			if (index > -1)
				componentCode = componentCode.substring(0, index);
			String attachment = "\uC5C6\uC74C";
			attachment = Function.nullChk(((SideeffectReportVO) list.get(i)).getAttachment());
			if (attachment != null && attachment.length() > 0)
				attachment = "\uC788\uC74C";
			label1 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getGubun()));
			label2 = new Label(++col, i + 1, report_type2);
			label3 = new Label(++col, i + 1, report_date);
			label4 = new Label(++col, i + 1, mreport_date);
			label5 = new Label(++col, i + 1, reporter_type);
			label6 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getCompany_name()));
			label7 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getRepresentatives()));
			label8 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getManager()));
			label9 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getReport_tel()));
			label10 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getFax()));
			label11 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getEmail()));
			label12 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getReport_address()));
			label13 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getEntp_name()));
			label14 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getMeddev_entp_no()));
			label15 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getClass_kor_name()));
			label16 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getMea_class_no()));
			label17 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getGrade()));
			label18 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getProduct_cob_code()));
			label19 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getCountry_manufactured()));
			label20 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getManufacturer()));
			label21 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getMeb_type()));
			label22 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getSerial_number()));
			label23 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getPatient_name()));
			label24 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getPatient_gender()));
			label25 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getPatient_age()));
			label26 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getPatient_extra_info()));
			label27 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getSide_effect_first_date()));
			label28 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getSide_effect_generation_date()));
			label29 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getSide_effect_last_date()));
			label30 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getReport_status()));
			label31 = new Label(++col, i + 1, result_value);
			label32 = new Label(++col, i + 1, cause_value);
			label33 = new Label(++col, i + 1, patientCode);
			label34 = new Label(++col, i + 1, medicalCode);
			label35 = new Label(++col, i + 1, componentCode);
			label36 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getCausality()));
			label37 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getExta_info()));
			label38 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getFollowup()));
			label39 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getFollow_up_action()));
			label40 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getOrganization1()));
			label41 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getOrganization_tel()));
			label42 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getOrganization_address()));
			label43 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getExtra_info2()));
			label44 = new Label(++col, i + 1, Function.nullChk(String.valueOf(((SideeffectReportVO) list.get(i)).getFirst_modified())));
			label45 = new Label(++col, i + 1, attachment);
			label46 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getDocument_number()));
			label47 = new Label(++col, i + 1, Function.nullChk(((SideeffectReportVO) list.get(i)).getKfda_followup()));
			sheet1.addCell(label1);
			sheet1.addCell(label2);
			sheet1.addCell(label3);
			sheet1.addCell(label4);
			sheet1.addCell(label5);
			sheet1.addCell(label6);
			sheet1.addCell(label7);
			sheet1.addCell(label8);
			sheet1.addCell(label9);
			sheet1.addCell(label10);
			sheet1.addCell(label11);
			sheet1.addCell(label12);
			sheet1.addCell(label13);
			sheet1.addCell(label14);
			sheet1.addCell(label15);
			sheet1.addCell(label16);
			sheet1.addCell(label17);
			sheet1.addCell(label18);
			sheet1.addCell(label19);
			sheet1.addCell(label20);
			sheet1.addCell(label21);
			sheet1.addCell(label22);
			sheet1.addCell(label23);
			sheet1.addCell(label24);
			sheet1.addCell(label25);
			sheet1.addCell(label26);
			sheet1.addCell(label27);
			sheet1.addCell(label28);
			sheet1.addCell(label29);
			sheet1.addCell(label30);
			sheet1.addCell(label31);
			sheet1.addCell(label32);
			sheet1.addCell(label33);
			sheet1.addCell(label34);
			sheet1.addCell(label35);
			sheet1.addCell(label36);
			sheet1.addCell(label37);
			sheet1.addCell(label38);
			sheet1.addCell(label39);
			sheet1.addCell(label40);
			sheet1.addCell(label41);
			sheet1.addCell(label42);
			sheet1.addCell(label43);
			sheet1.addCell(label44);
			sheet1.addCell(label45);
			sheet1.addCell(label46);
			sheet1.addCell(label47);
		}

		workbook.write();
		workbook.close();
		File f = new File((new StringBuilder(String.valueOf(path))).append(fileName).toString());
		String contentType = request.getContentType();
		response.setContentType("x-msdownload");
		if (contentType == null) {
			if (request.getHeader("user-agent").indexOf("MSIE 5.5") != -1)
				response.setContentType("doesn/matter");
			else
				response.setContentType("application/octet-stream");
		} else {
			response.setContentType(contentType);
		}
		response.setHeader("Content-Transfer-Encoding:", "binary");
		response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(fileName).append(";").toString());
		response.setHeader("Content-Length", (new StringBuilder()).append(f.length()).toString());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		b = new byte[1024];
		fin = new BufferedInputStream(new FileInputStream(f));
		outs = new BufferedOutputStream(response.getOutputStream());
		try {
			int read = 0;
			while ((read = fin.read(b)) != 0) {
				outs.write(b, 0, read);
			}

		} catch (Exception e) {

		} finally {
			if (outs != null)
				outs.close();
			if (fin != null)
				fin.close();
		}

		File fd = new File(path + fileName);

		if (fd.delete()) {
			System.out.println("지움");
		} else {
			System.out.println("안지움");
		}

		return null;
	}

	public ModelAndView xplattest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("/view/jsp/sideEffect/popup/xplattest.jsp");
		return mav;
	}

	public ModelAndView xplattest2(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String search = Function.nullChk(request.getParameter("search"), "");
		search = new String(search.getBytes("8859_1"), "UTF-8");
		search = (new StringBuilder("%")).append(search).append("%").toString();
		List componentCodeList = propertiesDAO.listPopupComponent(search);
		mav.addObject("componentCodeList", componentCodeList);
		mav.addObject("componentCodeListSize", Integer.valueOf(componentCodeList.size()));
		mav.setViewName("/view/jsp/sideEffect/popup/xplattest2.jsp");
		return mav;
	}

	public ModelAndView xpSideEffectList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ModelAndView mav = null;
		mav = new ModelAndView();
		System.out.println("[SimpleSideEffectReportDelegate].xpSideEffectList().");
		mav.setViewName("/view/jsp/sideEffect/xp_sideEffectList1.jsp");
		return mav;
	}

	public String dsGet(DataSet ds, int rowno, String colid) throws Exception {
		String value = ds.getString(rowno, colid);
		if (value == null)
			return "";
		else
			return value;
	}

	public ModelAndView sideEffectList(HttpServletRequest request, HttpServletResponse response) throws PlatformException {
		ModelAndView mav = null;
		mav = new ModelAndView();
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		String arrSc = null;
		String arrSv = null;
		String arrOption = null;
		String idOrName = null;
		List arraySc = new ArrayList();
		List arraySv = new ArrayList();
		List arrayOption = new ArrayList();
		List arrayIdOrName = new ArrayList();
		pReq.receiveData();
		PlatformData i_xpData = pReq.getData();
		VariableList in_vl = i_xpData.getVariableList();
		String getPopYN = Function.nullChk(in_vl.getString("gvPopYN"), "N");
		String getVpopNameArr = Function.nullChk(in_vl.getString("gVpopName"), "");
		String getVcodeAllArr = Function.nullChk(in_vl.getString("gVcodeArr"), "0");
		String getVitemSeq = Function.nullChk(in_vl.getString("gVitemSeq"), "0");
		String VpopName = "";
		String VpopNameArr[] = getVpopNameArr.split(",");
		String VcodeArr[] = getVcodeAllArr.split(",");
		for (int a = 0; a < VpopNameArr.length; a++) {
			String getVpopName = VpopNameArr[a];
			String getVcodeArr = VcodeArr[a];
			if (getPopYN.equals("Y")) {
				if (getVpopName.equals("popReporterType"))
					VpopName = "3";
				else if (getVpopName.equals("popMeaCompany"))
					VpopName = "11";
				else if (getVpopName.equals("popCountry"))
					VpopName = "17";
				else if (getVpopName.equals("popMeaClassNo"))
					VpopName = "13";
				else if (getVpopName.equals("popItemGrade"))
					VpopName = "15";
				else if (getVpopName.equals("popCausality"))
					VpopName = "35";
				else if (getVpopName.equals("popPatient") || getVpopName.equals("popPatientLevel"))
					VpopName = "28";
				else if (getVpopName.equals("popMedical") || getVpopName.equals("popMedicalLevel"))
					VpopName = "29";
				else if (getVpopName.equals("popComponent") || getVpopName.equals("popComponentLevel"))
					VpopName = "30";
				else if (getVpopName.equals("popCauseOfSideeffect"))
					VpopName = "27";
				else if (getVpopName.equals("popSideeffectResult"))
					VpopName = "26";
				else if (getVpopName.equals("popMeddevItem"))
					VpopName = "16";
				if (getPopYN.equals("Y") && !VpopName.equals("") && !getVcodeAllArr.equals("0") && !getVcodeAllArr.equals("")) {
					arraySc.add(VpopName);
					if (VpopNameArr.length < 2)
						arraySv.add(getVcodeAllArr);
					else
						arraySv.add(getVcodeArr);
					arrayOption.add("AND");
					arrayIdOrName.add("ID");
				}
			}
		}

		if (!getVitemSeq.equals("0") && getPopYN.equals("Y"))
			if (getVitemSeq.subSequence(0, 1).equals("I")) {
				arraySc.add("16");
				arraySv.add(getVitemSeq.substring(1, getVitemSeq.length()));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			} else if (getVitemSeq.subSequence(0, 1).equals("C")) {
				arraySc.add("13");
				arraySv.add(getVitemSeq.substring(1, getVitemSeq.length()));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			}
		DataSet ds_reporterTypes = i_xpData.getDataSet("ds_reporterTypes");
		try {
			Integer ds_chk = Integer.valueOf(0);
			String reporterTypeId = "";
			for (int i = 0; i < ds_reporterTypes.getRowCount(); i++) {
				String check_yn = dsGet(ds_reporterTypes, i, "CHECK_YN");
				if (check_yn.equals("1")) {
					ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
					reporterTypeId = (new StringBuilder(String.valueOf(reporterTypeId))).append(dsGet(ds_reporterTypes, i, "ID")).append(",")
							.toString();
				}
			}

			if (ds_chk.intValue() > 0) {
				arraySc.add("3");
				arraySv.add(reporterTypeId.substring(0, reporterTypeId.length() - 1));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		DataSet ds2 = i_xpData.getDataSet("ds_details_search");
		try {
			for (int i = 0; i < ds2.getRowCount(); i++) {
				arraySc.add(arrSc = dsGet(ds2, i, "SCID"));
				arraySv.add(arrSv = dsGet(ds2, i, "SV"));
				arrayOption.add(arrOption = dsGet(ds2, i, "OPTION"));
				arrayIdOrName.add("NAME");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String sc_0 = Function.nullChk(request.getParameter("sc_0"), "");
		String sv_0 = Function.nullChk(request.getParameter("sv_0"), "");
		String logicalOperator_0 = Function.nullChk(request.getParameter("logicalOperator_0"), "");
		String sv = Function.nullChk(request.getParameter("sv"), "");
		int sc = Function.nullChk(request.getParameter("sc"), -1);
		String year1 = Function.nullChk(request.getParameter("year1"), "");
		String year2 = Function.nullChk(request.getParameter("year2"), "");
		String month1 = Function.nullChk(request.getParameter("month1"), "");
		String month2 = Function.nullChk(request.getParameter("month2"), "");
		int pgSize = Function.nullChk(request.getParameter("pgSize"), 0);
		int firstResult = Function.nullChk(request.getParameter("firstResult"), 0);
		int rowMaxValue = Function.nullChk(request.getParameter("rowMaxValue"), 0);
		int rowCount = Function.nullChk(request.getParameter("rowCount"), 0);
		String dataSetGubun = Function.nullChk(request.getParameter("dataSetGubun"), "");
		String paging = "y";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String orderText = Function.nullChk(request.getParameter("orderText"), "");
		String order1 = Function.nullChk(request.getParameter("order"), "desc");
		int checkDuplicate = Function.nullChk(request.getParameter("checkDuplicate"), 0);
		if (year1.length() > 0) {
			year1 = year1.substring(0, 4);
			year2 = year2.substring(0, 4);
			month1 = month1.substring(4, 6);
			month2 = month2.substring(4, 6);
		}
		List list = null;
		List list2 = null;
		int reportId = -2;
		int method = 0;
		BigDecimal total = sideEffectDAO.countReportList(new SimpleSideeffectReport(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1, year2,
				month1, month2, getPopYN, pgSize);
		int page = Function.nullChk(request.getParameter("page"), -1);
		if (arraySc.size() > 0 || sc > -1) {
			method = 1;
			list2 = sideEffectDAO.details_search2(new SideeffectReportVO(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1, year2, month1,
					month2, getPopYN, pgSize, firstResult, rowCount, rowMaxValue, pg, orderText, order1, paging);
		}
		if (sv.length() > 1 && method == 0) {
			method = 2;
			HashSet hs = new HashSet();
			hs = search(sv);
			list = sideEffectDAO.searchList(new SimpleSideeffectReport(), hs, sv);
		}
		if (sv.length() <= 1 && list == null && method == 0) {
			method = 3;
			list = null;
		}
		int nErrorCode = 0;
		String strErrorMsg = "START";
		DataSet ds = null;
		if (dataSetGubun.equals(""))
			ds = new DataSet("ds_sideEffectList_server");
		else
			ds = new DataSet("ds_sideEffectList_server_more");
		int i = 0;
		ds.addColumn("ROW", 3);
		ds.addColumn("document_number", 2, 256);
		ds.addColumn("reporter_type", 2, 256);
		ds.addColumn("company_name", 2, 256);
		ds.addColumn("entp_name", 2, 256);
		ds.addColumn("grade", 2, 256);
		ds.addColumn("class_kor_name", 2, 256);
		ds.addColumn("product_cob_code", 2, 256);
		ds.addColumn("medicalcode", 2, 256);
		ds.addColumn("patientcode", 2, 256);
		ds.addColumn("componetcode", 2, 256);
		ds.addColumn("result_value", 2, 256);
		ds.addColumn("cause_value", 2, 256);
		ds.addColumn("causality", 2, 256);
		ds.addColumn("exta_info", 2, 256);
		ds.addColumn("followup", 2, 256);
		ds.addColumn("id", 3);
		int row = 0;
		int no = 0;
		no = rowMaxValue + 1;
		System.out.println((new StringBuilder("[SimpleSideeffectRepotyDelegate].rowMaxValue  ")).append(rowMaxValue).toString());
		int row1 = 0;
		if (51 > pg * 50)
			row1 = 51 - pg * 50;
		else
			row1 = pg * 50 - 49;
		int row2 = 0;
		int index = -1;
		String temp = null;
		if (list2 != null) {
			SideeffectReportVO r;
			for (Iterator iterator = list2.iterator(); iterator.hasNext(); ds.set(row, "id", r.getId())) {
				r = (SideeffectReportVO) iterator.next();
				row = ds.newRow();
				row2 = row1++;
				ds.set(row, "ROW", row2);
				if (r.getDocument_number() != null)
					ds.set(row, "document_number", r.getDocument_number());
				else
					ds.set(row, "document_number", "");
				if (r.getReporter_type() != null) {
					index = r.getReporter_type().lastIndexOf(",");
					if (index > 0) {
						temp = r.getReporter_type().substring(0, index);
						if (temp != null)
							ds.set(row, "reporter_type", temp);
						else
							ds.set(row, "reporter_type", "");
					} else {
						ds.set(row, "reporter_type", "");
					}
				} else {
					ds.set(row, "reporter_type", "");
				}
				if (r.getCompany_name() != null)
					ds.set(row, "company_name", r.getCompany_name());
				else
					ds.set(row, "company_name", "");
				if (r.getEntp_name() != null)
					ds.set(row, "entp_name", r.getEntp_name());
				else
					ds.set(row, "entp_name", "");
				if (r.getGrade() != null)
					ds.set(row, "grade", r.getGrade());
				else
					ds.set(row, "grade", "");
				if (r.getClass_kor_name() != null)
					ds.set(row, "class_kor_name", r.getClass_kor_name());
				else
					ds.set(row, "class_kor_name", "");
				if (r.getProduct_cob_code() != null)
					ds.set(row, "product_cob_code", r.getProduct_cob_code());
				else
					ds.set(row, "product_cob_code", r.getProduct_cob_code());
				if (r.getMedicalcode() != null) {
					index = -1;
					temp = null;
					index = r.getMedicalcode().lastIndexOf(",");
					if (index > 0)
						temp = r.getMedicalcode().substring(0, index);
					else
						ds.set(row, "medicalcode", "");
					if (temp != null)
						ds.set(row, "medicalcode", temp);
					else
						ds.set(row, "medicalcode", "");
				} else {
					ds.set(row, "medicalcode", "");
				}
				if (r.getPatientcode() != null) {
					index = -1;
					temp = null;
					index = r.getPatientcode().lastIndexOf(",");
					if (index > 0)
						temp = r.getPatientcode().substring(0, index);
					else
						ds.set(row, "patientcode", "");
					if (temp != null)
						ds.set(row, "patientcode", temp);
					else
						ds.set(row, "patientcode", "");
				} else {
					ds.set(row, "patientcode", "");
				}
				if (r.getComponetcode() != null) {
					index = -1;
					temp = null;
					index = r.getComponetcode().lastIndexOf(",");
					if (index > 0)
						temp = r.getComponetcode().substring(0, index);
					else
						ds.set(row, "componetcode", "");
					if (temp != null)
						ds.set(row, "componetcode", temp);
					else
						ds.set(row, "componetcode", "");
				} else {
					ds.set(row, "componetcode", "");
				}
				if (r.getResult_value() != null) {
					index = -1;
					temp = null;
					index = r.getResult_value().lastIndexOf(",");
					if (index > 0)
						temp = r.getResult_value().substring(0, index);
					else
						ds.set(row, "result_value", "");
					if (temp != null)
						ds.set(row, "result_value", temp);
					else
						ds.set(row, "result_value", "");
				} else {
					ds.set(row, "result_value", "");
				}
				if (r.getCause_value() != null) {
					index = -1;
					temp = null;
					index = r.getCause_value().lastIndexOf(",");
					if (index > 0)
						temp = r.getCause_value().substring(0, index);
					else
						ds.set(row, "cause_value", "");
					if (temp != null)
						ds.set(row, "cause_value", temp);
					else
						ds.set(row, "cause_value", "");
				} else {
					ds.set(row, "cause_value", "");
				}
				if (r.getCausality() != null)
					ds.set(row, "causality", r.getCausality());
				else
					ds.set(row, "causality", "");
				if (r.getExta_info() != null)
					ds.set(row, "exta_info", r.getExta_info());
				else
					ds.set(row, "exta_info", "");
				if (r.getFollowup() != null)
					ds.set(row, "followup", Function.fnTagOff(r.getFollowup()));
				else
					ds.set(row, "followup", "");
			}

		}
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectList().pgSize  ")).append(pgSize).toString());
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectList().firstResult  ")).append(firstResult).toString());
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectList().total  ")).append(total).toString());
		BigDecimal countNmberDuplicate = null;
		if (checkDuplicate == 1)
			countNmberDuplicate = sideEffectDAO.countNmberDuplicate(new SideeffectReportVO(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1,
					year2, month1, month2, getPopYN, pgSize, firstResult, rowCount, rowMaxValue, pg, orderText, order1, paging);
		if (countNmberDuplicate == null)
			countNmberDuplicate = new BigDecimal(0);
		if (!countNmberDuplicate.equals(BigDecimal.ZERO))
			countNmberDuplicate = total.subtract(countNmberDuplicate);
		else
			countNmberDuplicate = total;
		PlatformData pdata = new PlatformData();
		pdata.addDataSet(ds);
		VariableList varList = pdata.getVariableList();
		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", total);
		varList.add("countNumberDuplicate", countNmberDuplicate);
		HttpPlatformResponse res = new HttpPlatformResponse(response);
		res.setData(pdata);
		try {
			res.sendData();
		} catch (PlatformException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return mav;
	}

	public ModelAndView testList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		List list = null;
		list = sideEffectDAO.list(new SimpleSideeffectReport());
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("contentName", "/view/jsp/sideEffect/sideEffectList1.jsp");
		setDefaultViewSet(mav, request);
		return mav;
	}

	public ModelAndView read(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub01_01.jpg";
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].articleId  ")).append(articleId).toString());
		List country = propertiesDAO.list(SimpleCountryReportedIn.class);
		Report rp = sideEffectDAO.read(articleId);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].rp.toString()  ")).append(rp.toString()).toString());
		String patient_extra_info = rp.getPatient_extra_info();
		String followup = rp.getFollowup();
		String extra_info = rp.getExtra_info();
		String side_result_from_reporter = rp.getSide_result_from_reporter();
		String side_cause_from_reporter = rp.getSide_cause_from_reporter();
		String patient_condition_from_reporter = rp.getPatient_condition_from_reporter();
		String meddev_code_from_reporter = rp.getMeddev_code_from_reporter();
		String omponent_code_from_reporter = rp.getOmponent_code_from_reporter();
		String causality_from_reporter = rp.getCausality_from_reporter();
		String kfda_followup = rp.getKfda_followup();
		int type = 1;
		List fileList = attachmentDAO.get(articleId, type);
		mav.addObject("article", rp);
		mav.addObject("countryList", country);
		mav.addObject("fileList", fileList);
		mav.setViewName("/view/jsp/sideEffect/sideeffectView1.jsp");
		mav.addObject("titleImg", titleImg);
		mav.addObject("patient_extra_info", patient_extra_info);
		mav.addObject("extra_info", extra_info);
		mav.addObject("side_result_from_reporter", side_result_from_reporter);
		mav.addObject("side_cause_from_reporter", side_cause_from_reporter);
		mav.addObject("patient_condition_from_reporter", patient_condition_from_reporter);
		mav.addObject("meddev_code_from_reporter", meddev_code_from_reporter);
		mav.addObject("omponent_code_from_reporter", omponent_code_from_reporter);
		mav.addObject("causality_from_reporter", causality_from_reporter);
		mav.addObject("followup", followup);
		mav.addObject("kfda_followup", kfda_followup);
		return mav;
	}

	public ModelAndView sideEffectDetailsSearchPage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[SimpleSideEffectReportDelegate].sideEffectDetailsSearchPage().");
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("/view/jsp/sideEffect/details_search.jsp");
		return mav;
	}

	public ModelAndView sideEffectDetailsSearch(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[SimpleSideEffectReportDelegate].sideEffectDetailsSearch().");
		ModelAndView mav = null;
		mav = new ModelAndView();
		int count = Function.nullChk(request.getParameter("count"), 0);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectDetailsSearch().count  ")).append(count).toString());
		String sc[] = request.getParameterValues("sc_0");
		String sv[] = request.getParameterValues("sv_0");
		String logicalOperator[] = request.getParameterValues("logicalOperator_0");
		System.out
				.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectDetailsSearch().sc.length  ")).append(sc.length).toString());
		System.out
				.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectDetailsSearch().sv.length  ")).append(sv.length).toString());
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectDetailsSearch().logicalOperator.length  "))
				.append(logicalOperator.length).toString());
		for (int i = 0; i < sc.length; i++)
			System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectDetailsSearch().sc[")).append(i).append("]  ")
					.append(sc[i]).append("  sv[i] ").append(sv[i]).append("  logicalOperator[i]  ").append(logicalOperator[i]).toString());

		return mav;
	}

	public void sideEffectFirstList(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) {
	}

	public ModelAndView getSideEffectResult(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String reportDate = Function.nullChk(request.getParameter("reportDate"), "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rd = new Date();
		try {
			rd = sdf.parse(reportDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			List sideeffectResult = sideEffectDAO.resultList(new SimpleSideeffectResult(), rd);
			PrintWriter out = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			JSONArray arr;
			for (Iterator iterator = sideeffectResult.iterator(); iterator.hasNext(); jsonArray.add(arr)) {
				SimpleSideeffectResult result = (SimpleSideeffectResult) iterator.next();
				arr = new JSONArray();
				arr.add(Long.valueOf(result.getId()));
				arr.add(result.getPropertyValue());
				arr.add(result.getDepthLevel());
			}

			out.println(jsonArray.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mav;
	}

	private SimpleSideeffectReportDAO sideEffectDAO;
	private PropertiesDAO propertiesDAO;
	private AttachmentDAO attachmentDAO;
	private AttachmentServiceProvider attachmentServiceProvider;
	private CommonsFileUploadSupport multipartResolver;
}

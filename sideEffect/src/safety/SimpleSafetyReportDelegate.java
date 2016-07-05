// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSafetyReportDelegate.java

package safety;

import abstraction.SimpleDelegate;
import com.tobesoft.xplatform.data.*;
import com.tobesoft.xplatform.tx.*;
import edu.emory.mathcs.backport.java.util.Collections;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import kr.co.sgis.legacy.common.Function;
import member.Member;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import properties.*;
import system.io.*;

// Referenced classes of package safety:
//            SafetyDAO, SafetyReport, SimpleSafetyReportTypeDate, SimpleSafetyReport, 
//            JuncSafetyReporterTypes, SimpleJuncSafetyReportType2, JuncSafetyReportType, SimpleSafetyReportView, 
//            JuncSafetyPatientCondition, JuncSafetyMedicalCode, JuncSafetyComponentCode

public class SimpleSafetyReportDelegate extends SimpleDelegate {

	public SimpleSafetyReportDelegate() {
	}

	public PropertiesDAO getPropertiesDAO() {
		return propertiesDAO;
	}

	public void setPropertiesDAO(PropertiesDAO propertiesDAO) {
		this.propertiesDAO = propertiesDAO;
	}

	public SafetyDAO getSafetyDAO() {
		return safetyDAO;
	}

	public void setSafetyDAO(SafetyDAO safetyDAO) {
		this.safetyDAO = safetyDAO;
	}

	public AttachmentDAO getAttachmentDAO() {
		return attachmentDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		System.out.println("[SimpleSafetyReportDelegate].list()");
		String sc = Function.nullChk(request.getParameter("sc"), "");
		String date1 = Function.nullChk(request.getParameter("date1"), "");
		String date2 = Function.nullChk(request.getParameter("date2"), "");
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].list().sc  ")).append(sc).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].list().date1  ")).append(date1).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].list().date2  ")).append(date2).toString());
		setDefaultViewSet(mav, request);
		mav.addObject("contentName", "/view/jsp/safety/xp_safetyList1.jsp");
		mav.addObject("sc", sc);
		mav.addObject("date1", date1);
		mav.addObject("date12", date2);
		mav.addObject("popYN", "N");
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
		mav.setViewName("/view/jsp/safety/xp_safetyList1.jsp");
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

	public ModelAndView read(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub01_02.jpg";
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		int type = 2;
		SafetyReport sr = safetyDAO.read(articleId);
		List fileList = attachmentDAO.get(articleId, type);
		List reporterTypes = propertiesDAO.list(SimpleReporterType.class);
		List reportStatus = propertiesDAO.list(SimpleReportStatus.class);
		List sideEffectReportTypes = propertiesDAO.list(SimpleSideEffectReportType.class);
		String safety_cause_content = "";
		if (sr.getSafety_cause_content() != null)
			safety_cause_content = sr.getSafety_cause_content();
		String followup = "";
		if (sr.getFollowup() != null)
			followup = sr.getFollowup();
		String extra_info = "";
		if (extra_info != null)
			extra_info = sr.getExtra_info();
		mav.setViewName("/view/jsp/safety/safetyView1.jsp");
		mav.addObject("article", sr);
		mav.addObject("fileList", fileList);
		mav.addObject("reporterTypes", reporterTypes);
		mav.addObject("titleImg", titleImg);
		mav.addObject("reportStatus", reportStatus);
		mav.addObject("sideEffectReportTypes", sideEffectReportTypes);
		mav.addObject("safety_cause_content", safety_cause_content);
		mav.addObject("followup", followup);
		mav.addObject("extra_info", extra_info);
		return mav;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub01_02.jpg";
		List countriesReportedIn = propertiesDAO.list(SimpleCountryReportedIn.class);
		List reportTypes = propertiesDAO.list(SimpleReportType2.class);
		List reporterTypes = propertiesDAO.list(SimpleReporterType.class);
		List sideEffectReportTypes = propertiesDAO.list(SimpleSideEffectReportType.class);
		List status = propertiesDAO.list(SimpleReportStatus.class);
		List reportFollowUpAction = propertiesDAO.list(SimpleReportFollowUpAction.class);
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		int type = 2;
		SafetyReport sr = safetyDAO.read(articleId);
		List sotrReporterTypes = new ArrayList();
		List sotrReporterTypes2 = new ArrayList();
		int k = 0;
		for (int i = 0; i < sr.getReporterTypes().size(); i++)
			sotrReporterTypes2.add(Long.valueOf(((ReporterType) sr.getReporterTypes().get(i)).getId()));

		Collections.sort(sotrReporterTypes2);
		for (int i = 0; i < reporterTypes.size(); i++)
			if (sr.getReporterTypes().size() > k) {
				if (Long.valueOf(((SimpleReporterType) reporterTypes.get(i)).getId()) == sotrReporterTypes2.get(k)) {
					sotrReporterTypes.add(sotrReporterTypes2.get(k));
					k++;
				} else {
					sotrReporterTypes.add(Integer.valueOf(0));
				}
			} else {
				sotrReporterTypes.add(Integer.valueOf(0));
			}

		List sotrsafetyReportDate = new ArrayList();
		List sotrsafetyReportTypeDate = new ArrayList();
		List sotrsafetyReportTypeDate2 = new ArrayList();
		List sotrsafetyReportTypeDate3 = new ArrayList();
		k = 0;
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].update().sr.getSafetyReportTypeDate().size()  "))
				.append(sr.getSafetyReportTypeDate().size()).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].update().sr.getSafetyReportTypeDate().toString()  "))
				.append(sr.getSafetyReportTypeDate().toString()).toString());
		for (int i = 0; i < sr.getSafetyReportTypeDate().size(); i++)
			if (sr.getSafetyReportTypeDate().get(i) != null) {
				System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].update().sr.getSafetyReportTypeDate().getReport_date()  "))
						.append(((SimpleSafetyReportTypeDate) sr.getSafetyReportTypeDate().get(i)).getReport_date()).toString());
				sotrsafetyReportTypeDate2.add(Long.valueOf(((SimpleSafetyReportTypeDate) sr.getSafetyReportTypeDate().get(i)).getReport_type_id()));
				sotrsafetyReportTypeDate3.add(((SimpleSafetyReportTypeDate) sr.getSafetyReportTypeDate().get(i)).getReport_date());
			}

		Collections.sort(sotrsafetyReportTypeDate2);
		Collections.sort(sotrsafetyReportTypeDate3);
		System.out.println((new StringBuilder("size  ")).append(sotrsafetyReportTypeDate2.size()).toString());
		for (int i = 0; i < sotrsafetyReportTypeDate2.size(); i++)
			if (sotrsafetyReportTypeDate2.size() > k) {
				for (int j = 0; j < reporterTypes.size(); j++) {
					System.out.println((new StringBuilder("jj  ")).append(j).append(" kk ").append(k).toString());
					if (Long.valueOf(((SimpleReporterType) reporterTypes.get(j)).getId()) == sotrsafetyReportTypeDate2.get(k)) {
						sotrsafetyReportTypeDate.add(sotrsafetyReportTypeDate2.get(k));
						sotrsafetyReportDate.add(sotrsafetyReportTypeDate3.get(k).toString());
						k++;
						if (sotrsafetyReportTypeDate2.size() <= k)
							k--;
					} else {
						sotrsafetyReportTypeDate.add(Integer.valueOf(0));
						sotrsafetyReportDate.add(Integer.valueOf(0));
					}
				}

			} else {
				sotrsafetyReportTypeDate.add(Integer.valueOf(0));
				sotrsafetyReportDate.add(Integer.valueOf(0));
			}

		List sotrSideEffectReportType = new ArrayList();
		List sotrSideEffectReportType2 = new ArrayList();
		k = 0;
		for (int i = 0; i < sr.getReport_type().size(); i++)
			sotrSideEffectReportType2.add(Long.valueOf(((SimpleSideEffectReportType) sr.getReport_type().get(i)).getId()));

		Collections.sort(sotrSideEffectReportType2);
		for (int i = 0; i < sideEffectReportTypes.size(); i++)
			if (sr.getReport_type().size() > k) {
				if (Long.valueOf(((SimpleSideEffectReportType) sideEffectReportTypes.get(i)).getId()) == sotrSideEffectReportType2.get(k)) {
					sotrSideEffectReportType.add(sotrSideEffectReportType2.get(k));
					k++;
				} else {
					sotrSideEffectReportType.add(Integer.valueOf(0));
				}
			} else {
				sotrSideEffectReportType.add(Integer.valueOf(0));
			}

		List fileList = attachmentDAO.get(articleId, type);
		System.out.println("[SimpleSafetyReportDelegate].update()");
		mav.addObject("article", sr);
		mav.addObject("fileList", fileList);
		mav.addObject("countriesReported_In", countriesReportedIn);
		mav.addObject("reportTypes", reportTypes);
		mav.addObject("reporterTypes", reporterTypes);
		mav.addObject("titleImg", titleImg);
		mav.addObject("sideEffectReportTypes", sideEffectReportTypes);
		mav.addObject("reportStatus", status);
		mav.addObject("sotrReporterTypes", sotrReporterTypes);
		mav.addObject("sotrSideEffectReportType", sotrSideEffectReportType);
		mav.addObject("sotrsafetyReportTypeDate", sotrsafetyReportTypeDate);
		mav.addObject("sotrsafetyReportDate", sotrsafetyReportDate);
		mav.addObject("reportFollowUpAction", reportFollowUpAction);
		mav.setViewName("/view/jsp/safety/safetyUpdate1.jsp");
		return mav;
	}

	public ModelAndView createPage(HttpServletRequest request, HttpServletResponse response) {
		List countriesReportedIn = propertiesDAO.list(SimpleCountryReportedIn.class);
		List reportTypes = propertiesDAO.list(SimpleReportType2.class);
		List reporterTypes = propertiesDAO.list(SimpleReporterType.class);
		List reportStatus = propertiesDAO.list(SimpleReportStatus.class);
		List sideEffectReportTypes = propertiesDAO.list(SimpleSideEffectReportType.class);
		List reportFollowUpAction = propertiesDAO.list(SimpleReportFollowUpAction.class);
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("contentName", "/view/jsp/safety/safetyCreate1.jsp");
		mav.addObject("countriesReported_In", countriesReportedIn);
		mav.addObject("reportTypes", reportTypes);
		mav.addObject("reporterTypes", reporterTypes);
		mav.addObject("reportStatus", reportStatus);
		mav.addObject("sideEffectReportTypes", sideEffectReportTypes);
		mav.addObject("reportFollowUpAction", reportFollowUpAction);
		String titleImg = "view/style/images/title/sub01_02.jpg";
		mav.addObject("titleImg", titleImg);
		return mav;
	}

	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			out.println("alert('\uB85C\uADF8\uC778 \uD6C4 \uB4F1\uB85D\uC774 \uAC00\uB2A5\uD569\uB2C8\uB2E4');");
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
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Enumeration parameterNames = multipartRequest.getParameterNames();
		int countryReportedIn = Function.nullChk(multipartRequest.getParameter("countryReportedIn"), 0);
		int document_number = Function.nullChk(multipartRequest.getParameter("document_number"), 0);
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
		String safety_cause_content = Function.nullChk(multipartRequest.getParameter("safety_cause_content"), "");
		String followup = Function.nullChk(multipartRequest.getParameter("followup"), "");
		List safetyReportType = null;
		safetyReportType = Function.nullChk(multipartRequest.getParameterValues("safetyReportType"), 0);
		int report_status = Function.nullChk(multipartRequest.getParameter("report_status"), 0);
		String extra_info = Function.nullChk(multipartRequest.getParameter("extra_info"), "");
		String patient_code_id = Function.nullChk(multipartRequest.getParameter("patient_code_id"), "");
		String medical_code_id = Function.nullChk(multipartRequest.getParameter("medical_code_id"), "");
		String component_id = Function.nullChk(multipartRequest.getParameter("component_id"), "");
		String reporter_etc = Function.nullChk(multipartRequest.getParameter("reporterEtc"), "");
		String serial_number = Function.nullChk(multipartRequest.getParameter("serial_number"), "");
		String safetyReportTypeEtc = Function.nullChk(multipartRequest.getParameter("safetyReportTypeEtc"), "");
		String first_modified = Function.nullChk(multipartRequest.getParameter("first_modified"), "");
		int follow_up_action = Function.nullChk(multipartRequest.getParameter("follow_up_action"), 0);
		String followUpActionEtc = Function.nullChk(multipartRequest.getParameter("followUpActionEtc"), "");
		String mebTypeInfoSelect = Function.nullChk(multipartRequest.getParameter("mebTypeInfoSelect"), "");
		String kfda_followup = Function.nullChk(multipartRequest.getParameter("kfda_followup"), "");
		Integer safetyReport;
		for (Iterator iterator = safetyReportType.iterator(); iterator.hasNext(); System.out
				.println((new StringBuilder("[SimpleSafety_ReportDelegate].create.safetyReportType  ")).append(safetyReport.intValue()).toString()))
			safetyReport = (Integer) iterator.next();

		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date parseRepotrDate = null;
		Date parseMreport_date = null;
		Date parsFirst_modified = null;
		String strMreport_date = "";
		String strFirst_modified = "";
		try {
			if (mreport_date.length() > 0)
				parseMreport_date = dateFormat.parse(mreport_date);
			if (first_modified.length() > 0)
				parsFirst_modified = dateFormat.parse(first_modified);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleSafetyReport report = new SimpleSafetyReport();
		report.setFirst_modified(curDate);
		report.setCountry_reported_in(Integer.valueOf(countryReportedIn));
		report.setDocument_number(new Double(document_number));
		report.setReportDate(parseRepotrDate);
		report.setMreport_date(parseMreport_date);
		report.setCompany_name(company_name);
		report.setRepresentative(representatives);
		report.setManager(manager);
		report.setTelephone(report_tel);
		report.setFax(fax);
		report.setEmail(email);
		report.setAddress(report_address);
		report.setMeb_item_id(Integer.valueOf(meb_item_manuf_id));
		report.setCountry_manufactured_id(country_manufactured_id);
		report.setManufacturer(manufacturer);
		report.setSafety_cause_content(safety_cause_content);
		report.setFollowup(followup);
		report.setReport_status(Integer.valueOf(report_status));
		report.setExtra_info(extra_info);
		if (mreport_date.length() > 0) {
			report.setMreport_date(parseMreport_date);
			report.setReport_year(mreport_date.substring(0, 4));
			report.setReport_month(mreport_date.substring(5, 7));
		} else {
			report.setReport_year("");
			report.setReport_month("");
		}
		report.setReporter_etc(reporter_etc);
		report.setSerial_number(serial_number);
		report.setSafety_report_type_etc(safetyReportTypeEtc);
		if (first_modified.length() > 0)
			report.setFirst_modified(parsFirst_modified);
		else
			report.setFirst_modified(curDate);
		report.setFollow_up_action(Integer.valueOf(follow_up_action));
		report.setFollowUpActionEtc(followUpActionEtc);
		report.setKfda_followup(kfda_followup);
		report.setCause(Integer.valueOf(0));
		int safetyReportAddId = safetyDAO.add(report);
		int type = 2;
		long reportTrpesAddId = reportTypesAdd(safetyReportAddId, reportType, listReportDate);
		long reporterTrpesAddId = reporterTypesAdd(safetyReportAddId, reporterType, type);
		long safteyReportTypeAddId = safteyReportTypeAdd(safetyReportAddId, safetyReportType, type);
		long mebTypeInfoSelectAdd;
		if (!patient_code_id.equals("0"))
			mebTypeInfoSelectAdd = safetyPatientAdd(safetyReportAddId, patient_code_id);
		if (!medical_code_id.equals("0"))
			mebTypeInfoSelectAdd = safetyMedicalCodeAdd(safetyReportAddId, medical_code_id);
		if (!component_id.equals("0"))
			mebTypeInfoSelectAdd = safetyComponentCodeAdd(safetyReportAddId, component_id);
		mebTypeInfoSelectAdd = mebTypeInfoSelectAdd(safetyReportAddId, mebTypeInfoSelect);
		List result = attachment(request, response, safetyReportAddId);
		int juncReportAttachmentAddId = juncReportAttachment(safetyReportAddId, result);
		out.println("<script language=javascript>");
		out.println("alert('\uB4F1\uB85D\uB418\uC5C8\uC2B5\uB2C8\uB2E4.');");
		out.println("location.replace('safetyReport.do?action=list')");
		out.println("</script>");
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].create().arrReportDate  ")).append(arrReportDate).toString());
		return null;
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
		if (session.getAttribute("user") != null) {
			Member objMember = (Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uB85C\uADF8\uC778 \uD6C4 \uB4F1\uB85D\uC774 \uAC00\uB2A5\uD569\uB2C8\uB2E4');");
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
		setDefaultViewSet(mav, request);
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

		String mreport_date = Function.nullChk(multipartRequest.getParameter("kfdaReportDate"), "");
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
		int report_status = Function.nullChk(multipartRequest.getParameter("report_status"), 0);
		String extra_info = Function.nullChk(multipartRequest.getParameter("extra_info"), "");
		String safety_cause_content = Function.nullChk(multipartRequest.getParameter("safety_cause_content"), "");
		String followup = Function.nullChk(multipartRequest.getParameter("followup"), "");
		List safetyReportType = null;
		safetyReportType = Function.nullChk(multipartRequest.getParameterValues("safetyReportType"), 0);
		String patient_code_id = Function.nullChk(multipartRequest.getParameter("patient_code_id"), "");
		String medical_code_id = Function.nullChk(multipartRequest.getParameter("medical_code_id"), "");
		String component_id = Function.nullChk(multipartRequest.getParameter("component_id"), "");
		String reporter_etc = Function.nullChk(multipartRequest.getParameter("reporterEtc"), "");
		String serial_number = Function.nullChk(multipartRequest.getParameter("serial_number"), "");
		String safetyReportTypeEtc = Function.nullChk(multipartRequest.getParameter("safetyReportTypeEtc"), "");
		String first_modified = Function.nullChk(multipartRequest.getParameter("first_modified"), "");
		int follow_up_action = Function.nullChk(multipartRequest.getParameter("follow_up_action"), 0);
		String followUpActionEtc = Function.nullChk(multipartRequest.getParameter("followUpActionEtc"), "");
		String mebTypeInfoSelect = Function.nullChk(multipartRequest.getParameter("mebTypeInfoSelect"), "");
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].modified().patient_code_id  ")).append(patient_code_id).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].modified().medical_code_id  ")).append(medical_code_id).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].modified().component_id  ")).append(component_id).toString());
		String kfda_followup = Function.nullChk(multipartRequest.getParameter("kfda_followup"), "");
		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date parseRepotrDate = null;
		Date parseMreport_date = null;
		Date parsFirst_modified = null;
		String strMreport_date = "";
		String strFirst_modified = "";
		try {
			if (mreport_date.length() > 0)
				parseMreport_date = dateFormat.parse(mreport_date);
			if (first_modified.length() > 0)
				parsFirst_modified = dateFormat.parse(first_modified);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleSafetyReport report = new SimpleSafetyReport();
		report.setId(Integer.valueOf(id));
		report.setDocument_number(Double.valueOf(Math.floor(document_number2)));
		report.setFirst_modified(curDate);
		report.setCountry_reported_in(Integer.valueOf(countryReportedIn));
		report.setReportDate(parseRepotrDate);
		report.setMreport_date(parseMreport_date);
		report.setCompany_name(company_name);
		report.setRepresentative(representatives);
		report.setManager(manager);
		report.setTelephone(report_tel);
		report.setFax(fax);
		report.setEmail(email);
		report.setAddress(report_address);
		report.setMeb_item_id(Integer.valueOf(meb_item_manuf_id));
		report.setCountry_manufactured_id(country_manufactured_id);
		report.setManufacturer(manufacturer);
		report.setSafety_cause_content(safety_cause_content);
		report.setFollowup(followup);
		report.setReport_status(Integer.valueOf(report_status));
		report.setExtra_info(extra_info);
		if (mreport_date.length() > 0) {
			report.setMreport_date(parseMreport_date);
			report.setReport_year(mreport_date.substring(0, 4));
			report.setReport_month(mreport_date.substring(5, 7));
		} else {
			report.setReport_year("");
			report.setReport_month("");
		}
		report.setReporter_etc(reporter_etc);
		report.setSerial_number(serial_number);
		report.setSafety_report_type_etc(safetyReportTypeEtc);
		if (first_modified.length() > 0)
			report.setFirst_modified(parsFirst_modified);
		else
			report.setFirst_modified(curDate);
		report.setFollow_up_action(Integer.valueOf(follow_up_action));
		report.setFollowUpActionEtc(followUpActionEtc);
		report.setCause(Integer.valueOf(0));
		report.setOrganisation(" ");
		report.setKfda_followup(kfda_followup);
		int returnNum = -1;
		returnNum = safetyDAO.update(report);
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].returnNum  ")).append(returnNum).toString());
		long reporterTrpesAddId = -1L;
		safetyDAO.reportTypeDateDelete(id);
		safetyDAO.juncReportTypeDateDelete(id);
		safetyDAO.juncReporterTypeDateDelete(id);
		safetyDAO.juncSafetyReportTypeDateDelete(id);
		safetyDAO.safetyPatientDelete(id);
		safetyDAO.safetyMedicalCodeDelete(id);
		safetyDAO.safetyComponentCodeDelete(id);
		long mebTypeInfoSelectDelete = 0L;
		mebTypeInfoSelectDelete = safetyDAO.mebTypeInfoSelectDelete(id);
		int type = 2;
		long reportTrpesAddId = reportTypesAdd(id, reportType, listReportDate);
		reporterTrpesAddId = reporterTypesAdd(id, reporterType, type);
		System.out.println(
				(new StringBuilder("[SimpleSafetyReportDelegate].create.modified.reporterTrpesAddId  ")).append(reporterTrpesAddId).toString());
		long safteyReportTypeAddId = safteyReportTypeAdd(id, safetyReportType, type);
		long mebTypeInfoSelectAdd;
		if (!patient_code_id.equals("0"))
			mebTypeInfoSelectAdd = safetyPatientAdd(id, patient_code_id);
		if (!medical_code_id.equals("0"))
			mebTypeInfoSelectAdd = safetyMedicalCodeAdd(id, medical_code_id);
		if (!component_id.equals("0"))
			mebTypeInfoSelectAdd = safetyComponentCodeAdd(id, component_id);
		mebTypeInfoSelectAdd = mebTypeInfoSelectAdd(id, mebTypeInfoSelect);
		List result = attachment(request, response, id);
		int juncReportAttachmentAddId = juncReportAttachment(id, result);
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].modified().attachment.result  ")).append(result).toString());
		System.out.println(
				(new StringBuilder("[SimpleSafetyReportDelegate].modified().mebTypeInfoSelectDelete  ")).append(mebTypeInfoSelectDelete).toString());
		out.println("<script language=javascript>");
		out.println("alert('\uC218\uC815\uB418\uC5C8\uC2B5\uB2C8\uB2E4.');");
		out.println((new StringBuilder("location.replace('safetyReport.do?action=read&articleId=")).append(id).append("')").toString());
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
		safetyDAO.reportTypeDateDelete(articleId);
		Integer id;
		for (Iterator iterator = fileId.iterator(); iterator.hasNext(); attachmentDAO.fileDelete(id.intValue()))
			id = (Integer) iterator.next();

		safetyDAO.delete(articleId);
		out.println("<script language=javascript>");
		out.println("alert('\uC0AD\uC81C\uB418\uC5C8\uC2B5\uB2C8\uB2E4.');");
		out.println("window.close()");
		out.println("</script>");
		return null;
	}

	long reporterTypesAdd(int safetyReportAddId, List reporterType, int type) {
		long returnId = -1L;
		JuncSafetyReporterTypes reportTypes = new JuncSafetyReporterTypes();
		for (Iterator iterator = reporterType.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			reportTypes.setReporter_type(types.intValue());
			reportTypes.setSafety_report(safetyReportAddId);
			reportTypes.setType(type);
			returnId = safetyDAO.reporterTypesAdd(reportTypes);
		}

		return returnId;
	}

	long reportTypesAdd(int safetyReportAddId, List reportType, List listReportDate) {
		long returnId = -1L;
		SimpleJuncSafetyReportType2 reportTypes = new SimpleJuncSafetyReportType2();
		int i = 0;
		for (Iterator iterator = reportType.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			if (types.longValue() > 0L) {
				reportTypes.setReport_type_id(types.longValue());
				reportTypes.setReport_id(safetyReportAddId);
				returnId = safetyDAO.reportTypesAdd(reportTypes);
				safetyReportTypeDateIns(safetyReportAddId, returnId, listReportDate, i, reportType);
			}
			i++;
		}

		return returnId;
	}

	void safetyReportTypeDateIns(long safetyReportAddId, long returnId, List listReportDate, int i, List reportType) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		System.out.println((new StringBuilder("[SimpleSideEffectReportDelegate].sideEffectReportTypeDateIns.i  ")).append(i).toString());
		Date parseReportDate = null;
		try {
			parseReportDate = dateFormat.parse(listReportDate.get(i - 1).toString());
		} catch (ParseException e) {
			System.out.println((new StringBuilder("ParseException  ")).append(e.getMessage()).toString());
			e.printStackTrace();
		}
		SimpleSafetyReportTypeDate date = new SimpleSafetyReportTypeDate();
		date.setJunc_id(returnId);
		date.setReport_id(safetyReportAddId);
		date.setReport_date(parseReportDate);
		date.setReport_type_id(((Integer) reportType.get(i)).intValue());
		date.setReport_year(listReportDate.get(i - 1).toString().substring(0, 4));
		date.setReport_month(listReportDate.get(i - 1).toString().substring(5, 7));
		long result = safetyDAO.reportTypeDateAdd(date);
	}

	int reporterTypesDel(int safety_report) {
		int returnId = -1;
		returnId = safetyDAO.reporterTypesDel(safety_report);
		return returnId;
	}

	long safteyReportTypeAdd(int safetyReportAddId, List safetyReportType, int type) {
		long returnId = -1L;
		JuncSafetyReportType reportTypes = new JuncSafetyReportType();
		for (Iterator iterator = safetyReportType.iterator(); iterator.hasNext();) {
			Integer types = (Integer) iterator.next();
			reportTypes.setReport_id(safetyReportAddId);
			reportTypes.setReport_type_id(types.intValue());
			returnId = safetyDAO.reporterTypesAdd(reportTypes);
		}

		return returnId;
	}

	List attachment(HttpServletRequest request, HttpServletResponse response, int returnAddNum) {
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
		String upPath1 = (new StringBuilder(String.valueOf(upPath0))).append("/").append("safetyReportFile").append("/").append(returnAddNum)
				.toString();
		Function.fnFolderChk(upPath0);
		Function.fnFolderChk(upPath1);
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].upPath1  ")).append(upPath1).toString());
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
				String fileName = multipartRequest.getParameter((new StringBuilder("fileName_")).append(i).toString());
				i++;
				System.out.println((new StringBuilder("[SimpleSideeffectReportDelegate].attachment.fileName  ")).append(fileName).toString());
				originaFileName = originaFileName.toLowerCase();
				System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].attachment.contentType  ")).append(contentType).toString());
				System.out
						.println((new StringBuilder("[SimpleSafetyReportDelegate].attachment.originaFileName  ")).append(originaFileName).toString());
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
					System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].attachment.strUid  ")).append(strUid).toString());
					full_url = (new StringBuilder("/upload/safetyReportFile/")).append(returnAddNum).toString();
					full_url = (new StringBuilder(String.valueOf(full_url))).append("/").append(strUid).append("_").append(file.getOriginalFilename())
							.toString();
					System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].create.full_url  ")).append(full_url).toString());
					organizedfilePath = (new StringBuilder(String.valueOf(upPath1))).append("/").append(strUid).append("_")
							.append(file.getOriginalFilename()).toString();
					System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].organizedfilePath  ")).append(organizedfilePath).toString());
					outputStream = new FileOutputStream(organizedfilePath);
					int readByte = 0;
					byte buffer[] = new byte[8192];
					while ((readByte = inputStream.read(buffer, 0, 8120)) != -1) {
						System.out.println("fileWrire");
						outputStream.write(buffer, 0, readByte);
					}
					Attachment attVO = new SimpleAttachment();
					attVO.setExtension(extension);
					attVO.setFullURL(full_url);
					attVO.setName(file.getOriginalFilename());
					attVO.setPhysicalPath((new StringBuilder(String.valueOf(strUid))).append("_").append(file.getOriginalFilename()).toString());
					attVO.setRelativeLogicalPath("");
					attVO.setTable_id(returnAddNum);
					attVO.setType(2);
					attVO.setFileName(fileName);
					attVO.setLogical_name((new StringBuilder(String.valueOf(strUid))).append("_").append(file.getOriginalFilename()).toString());
					int returnAddAttachmentId = attachmentDAO.add(attVO);
					addIdArr.add(Integer.valueOf(returnAddAttachmentId));
					System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].attachment.returnAddAttachmentId  "))
							.append(returnAddAttachmentId).toString());
				}
			}
		} catch (Exception e) {
			System.out.println((new StringBuilder(" Exception  ")).append(e.getMessage()).toString());
			e.printStackTrace();
		}
		return addIdArr;
	}

	int juncReportAttachment(int safetyReportAddId, List reslut) {
		int returnId = -1;
		SimpleJuncRepoerAttachment juncRepoerAttachment = new SimpleJuncRepoerAttachment();
		for (Iterator iterator = reslut.iterator(); iterator.hasNext();) {
			Integer r = (Integer) iterator.next();
			juncRepoerAttachment.setType(2);
			juncRepoerAttachment.setTable_id(safetyReportAddId);
			juncRepoerAttachment.setAttachment_id(r.intValue());
			returnId = attachmentDAO.add(juncRepoerAttachment);
		}

		return returnId;
	}

	public ModelAndView fileDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ModelAndView mav = new ModelAndView();
		int articleId = Function.nullChk(request.getParameter("articleId"), 0);
		int fileId = Function.nullChk(request.getParameter("fileId"), 0);
		String fullURL = Function.nullChk(request.getParameter("fullURL"), "");
		String fileUploadPath = request.getSession().getServletContext().getInitParameter("physicalUploadPath");
		attachmentDAO.fileDelete(fileId);
		attachmentDAO.juncReportAttachmentDelete(fileId);
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].fileDelete().fileUploadPath+fullURL  ")).append(fileUploadPath)
				.append(fullURL).toString());
		File f = new File((new StringBuilder(String.valueOf(fileUploadPath))).append(fullURL).toString());
		if (f.exists()) {
			f.delete();
			System.out.println("[SimpleSafetyReportDelegate].fileDelete().\uD30C\uC77C\uC0AD\uC81C\uC131\uACF5");
		} else {
			System.out.println("[SimpleSafetyReportDelegate].fileDelete().\uD30C\uC77C\uC0AD\uC81C\uC2E4\uD328");
		}
		out.println(fileId);
		out.close();
		return mav;
	}

	public HashSet search(String sv) {
		HashSet hs = new HashSet();

		List<SimpleSafetyReport> result = safetyDAO.countryReportedInSearch(sv);

		for (SimpleSafetyReport report : result) {
			System.out.println("[SimpleSafetyReportDelegate].result  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSafetyReport> result2 = safetyDAO.reportTypeSearch(sv);

		for (SimpleSafetyReport report : result2) {
			System.out.println("[SimpleSafetyReportDelegate].result2  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSafetyReport> result3 = safetyDAO.reporterTypeSearch(sv);

		for (SimpleSafetyReport report : result3) {
			System.out.println("[SimpleSafetyReportDelegate].result3  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSafetyReport> result4 = safetyDAO.mebItemSearch(sv);

		for (SimpleSafetyReport report : result4) {
			System.out.println("[SimpleSafetyReportDelegate].result4  " + report.getId());
			hs.add(report.getId());
		}

		List<SimpleSafetyReport> result9 = safetyDAO.reportStatusSearch(sv);

		for (SimpleSafetyReport report : result9) {
			System.out.println("[SimpleSafetyReportDelegate].result9  " + report.getId());
			hs.add(report.getId());
		}

		return hs;
	}

	public ModelAndView goExcel(HttpServletRequest request, HttpServletResponse response) throws RowsExceededException, WriteException, IOException {
		String fileName;
		String path;
		byte b[];
		BufferedInputStream fin;
		BufferedOutputStream outs;
		ModelAndView mav = new ModelAndView();
		String arrSc = null;
		String arrSv = null;
		String arrOption = null;
		String temp[] = null;
		List arraySc = new ArrayList();
		List arraySv = new ArrayList();
		List arrayOption = new ArrayList();
		List arrayIdOrName = new ArrayList();
		String sv = Function.nullChk(request.getParameter("sv"), "");
		int sc = Function.nullChk(request.getParameter("sc"), -1);
		String year1 = Function.nullChk(request.getParameter("year1"), "");
		String year2 = Function.nullChk(request.getParameter("year2"), "");
		String month1 = Function.nullChk(request.getParameter("month1"), "");
		String month2 = Function.nullChk(request.getParameter("month2"), "");
		int pgSize = Function.nullChk(request.getParameter("pgSize"), 0);
		int firstResult = Function.nullChk(request.getParameter("firstResult"), 0);
		int rowMaxValue = Function.nullChk(request.getParameter("rowMaxValue"), 0);
		String dataSetGubun = Function.nullChk(request.getParameter("dataSetGubun"), "");
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String getPopYN = Function.nullChk(request.getParameter("gvPopYN"), "N");
		String orderText = Function.nullChk(request.getParameter("orderText"), "");
		String order = Function.nullChk(request.getParameter("order"), "desc");
		String scid = Function.nullChk(request.getParameter("scid"), "");
		String option = Function.nullChk(request.getParameter("option"), "");
		String getVpopNameArr = Function.nullChk(request.getParameter("gVpopName"), "");
		String getVcodeAllArr = Function.nullChk(request.getParameter("gVcodeArr"), "0");
		String getVitemSeq = Function.nullChk(request.getParameter("gVitemSeq"), "0");
		String VpopName = "";
		String reporterTypes = Function.nullChk(request.getParameter("reporterTypes"), "");
		if (year1.length() > 0) {
			year1 = year1.substring(0, 4);
			year2 = year2.substring(0, 4);
			month1 = month1.substring(4, 6);
			month2 = month2.substring(4, 6);
		}
		getPopYN = getPopYN.replace("\u2019", "");
		getVpopNameArr = getVpopNameArr.replace("\u2019", "");
		getVcodeAllArr = getVcodeAllArr.replace("\u2019", "");
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
				else if (getVpopName.equals("popPatient") || getVpopName.equals("popPatientLevel"))
					VpopName = "23";
				else if (getVpopName.equals("popMedical") || getVpopName.equals("popMedicalLevel"))
					VpopName = "24";
				else if (getVpopName.equals("popComponent") || getVpopName.equals("popComponentLevel"))
					VpopName = "25";
				else if (getVpopName.equals("popMeddevItem"))
					VpopName = "16";
				else if (getVpopName.equals("popSideEffectReportType"))
					VpopName = "27";
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
					reporterTypeId = (new StringBuilder(String.valueOf(reporterTypeId))).append(temp[i]).append(",").toString();
					ds_chk = Integer.valueOf(ds_chk.intValue() + 1);
				}

			if (ds_chk.intValue() > 0) {
				arraySc.add("3");
				arraySv.add(reporterTypeId.substring(0, reporterTypeId.length() - 1));
				arrayOption.add("AND");
				arrayIdOrName.add("ID");
			}
		}
		temp = null;
		if (scid != null && scid.length() > 0) {
			temp = scid.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println((new StringBuilder("1[SimpleSafetyReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					arraySc.add(temp[i]);
				}

		}
		temp = null;
		if (sv != null && sv.length() > 0) {
			temp = sv.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println((new StringBuilder("2[SimpleSafetyReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					arraySv.add(temp[i]);
					arrayIdOrName.add("NAME");
				}

		}
		temp = null;
		if (option != null && option.length() > 0) {
			temp = option.split(",");
			for (int i = 0; i < temp.length; i++)
				if (temp[i] != null) {
					System.out.println((new StringBuilder("3[SimpleSafetyReportDelegate].temp[")).append(i).append("]  ").append(temp[i]).toString());
					if (temp[i].equals("undefined"))
						arrayOption.add("AND");
					else
						arrayOption.add(temp[i]);
				}

		}
		List list = null;
		String paging = "n";
		list = safetyDAO.list2(new SimpleSafetyReportView(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1, year2, month1, month2, getPopYN,
				pgSize, firstResult, pg, orderText, order, paging);
		fileName = "\\safety_temp1.xls";
		path = "C:\\sgi";
		Function.fnFolderChk(path);
		path = (new StringBuilder(String.valueOf(path))).append("\\hyh").toString();
		Function.fnFolderChk(path);
		WritableWorkbook workbook = Workbook.createWorkbook(new File((new StringBuilder(String.valueOf(path))).append(fileName).toString()));
		WritableSheet sheet1 = workbook.createSheet("sheet 1", 0);
		WritableCellFormat headFormat = new WritableCellFormat();
		headFormat.setAlignment(Alignment.LEFT);
		sheet1.setColumnView(0, 7);
		sheet1.setColumnView(1, 30);
		sheet1.setColumnView(2, 30);
		sheet1.setColumnView(3, 15);
		sheet1.setColumnView(4, 30);
		sheet1.setColumnView(5, 20);
		sheet1.setColumnView(6, 10);
		sheet1.setColumnView(7, 10);
		sheet1.setColumnView(8, 15);
		sheet1.setColumnView(9, 15);
		sheet1.setColumnView(10, 15);
		sheet1.setColumnView(11, 50);
		sheet1.setColumnView(12, 25);
		sheet1.setColumnView(13, 20);
		sheet1.setColumnView(14, 20);
		sheet1.setColumnView(15, 13);
		sheet1.setColumnView(16, 5);
		sheet1.setColumnView(17, 15);
		sheet1.setColumnView(19, 13);
		sheet1.setColumnView(20, 30);
		sheet1.setColumnView(21, 15);
		sheet1.setColumnView(22, 70);
		sheet1.setColumnView(23, 25);
		sheet1.setColumnView(24, 25);
		sheet1.setColumnView(25, 30);
		sheet1.setColumnView(26, 70);
		sheet1.setColumnView(27, 20);
		sheet1.setColumnView(28, 25);
		sheet1.setColumnView(30, 30);
		sheet1.setColumnView(31, 30);
		sheet1.setColumnView(32, 7);
		sheet1.setColumnView(33, 25);
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
		label12 = new Label(11, 0, "\uC8FC\uC18C");
		label13 = new Label(12, 0, "\uB300\uC0C1\uC5C5\uCCB4\uBA85");
		label14 = new Label(13, 0, "\uC5C5\uD638\uAC00\uBC88\uD638");
		label15 = new Label(14, 0, "\uD488\uBAA9\uBA85");
		label16 = new Label(15, 0, "\uBD84\uB958\uBC88\uD638");
		label17 = new Label(16, 0, "\uB4F1\uAE09");
		label18 = new Label(17, 0, "\uD488\uBAA9\uD5C8\uAC00\uBC88\uD638");
		label19 = new Label(18, 0, "\uC81C\uC870\uAD6D");
		label20 = new Label(19, 0, "\uC81C\uC870\uC6D0");
		label21 = new Label(20, 0, "\uD615\uBA85");
		label22 = new Label(21, 0, "\uC81C\uC870\uBC88\uD638");
		label23 = new Label(22, 0, "\uC548\uC120\uC131 \uC815\uBCF4 \uC6D0\uC778 \uBC0F \uB0B4\uC6A9");
		label24 = new Label(23, 0, "\uD658\uC790\uBB38\uC81C\uCF54\uB4DC");
		label25 = new Label(24, 0, "\uC758\uB8CC\uAE30\uAE30\uBB38\uC81C\uCF54\uB4DC");
		label26 = new Label(25, 0, "\uAD6C\uC131\uC694\uC18C\uCF54\uB4DC");
		label27 = new Label(26, 0, "\uC5C5\uC18C\uC758 \uD6C4\uC18D\uC870\uCE58");
		label28 = new Label(27, 0, "\uC0AC\uD6C4\uC870\uCE58 \uACB0\uACFC");
		label29 = new Label(28, 0, "\uC548\uC804\uC131\uC815\uBCF4\uBCF4\uACE0 \uC720\uD615");
		label30 = new Label(29, 0, "\uC9C4\uD589\uC0C1\uD669");
		label31 = new Label(30, 0, "\uAE30\uD0C0\uC0AC\uD56D");
		label32 = new Label(31, 0, "\uB4F1\uB85D\uC77C");
		label33 = new Label(32, 0, "\uCCA8\uBD80\uC790\uB8CC");
		label34 = new Label(33, 0, "\uC2DD\uC57D\uCC98\uD6C4\uC18D\uC870\uCE58");
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
		for (int i = 0; i < list.size(); i++) {
			int col = -1;
			int index = -1;
			String report_date2 = "";
			report_date2 = ((SimpleSafetyReportView) list.get(i)).getReport_type2();
			if (report_date2 != null)
				index = report_date2.lastIndexOf(",");
			if (index > -1)
				report_date2 = report_date2.substring(0, index);
			index = -1;
			String report_date = "";
			report_date = ((SimpleSafetyReportView) list.get(i)).getReport_date();
			if (report_date != null)
				index = report_date.lastIndexOf(",");
			if (index > -1)
				report_date = report_date.substring(0, index);
			index = -1;
			String mreport_date = "";
			mreport_date = String.valueOf(((SimpleSafetyReportView) list.get(i)).getMreport_date());
			if (mreport_date != null) {
				if (mreport_date.length() > 10)
					mreport_date = mreport_date.substring(0, 10);
			} else {
				mreport_date = "";
			}
			mreport_date = Function.nullChk(mreport_date);
			index = -1;
			String reporter_type = "";
			reporter_type = ((SimpleSafetyReportView) list.get(i)).getReporter_type();
			if (reporter_type != null)
				index = reporter_type.lastIndexOf(",");
			if (index > -1)
				reporter_type = reporter_type.substring(0, index);
			String product_cob_code = "";
			product_cob_code = Function.nullChk(((SimpleSafetyReportView) list.get(i)).getProduct_cob_code());
			if (product_cob_code.equals("\uD638"))
				product_cob_code = "";
			index = -1;
			String meb_type = "";
			meb_type = ((SimpleSafetyReportView) list.get(i)).getMeb_type();
			if (meb_type != null)
				index = meb_type.lastIndexOf(",");
			if (index > -1)
				meb_type = meb_type.substring(0, index);
			index = -1;
			String patientcode = "";
			patientcode = ((SimpleSafetyReportView) list.get(i)).getPatientcode();
			if (patientcode != null)
				index = patientcode.lastIndexOf(",");
			if (index > -1)
				patientcode = patientcode.substring(0, index);
			index = -1;
			String medicalCode = "";
			medicalCode = ((SimpleSafetyReportView) list.get(i)).getMedicalcode();
			if (medicalCode != null)
				index = medicalCode.lastIndexOf(",");
			if (index > -1)
				medicalCode = medicalCode.substring(0, index);
			index = -1;
			String componentcode = "";
			componentcode = ((SimpleSafetyReportView) list.get(i)).getComponentcode();
			if (componentcode != null)
				index = componentcode.lastIndexOf(",");
			if (index > -1)
				componentcode = componentcode.substring(0, index);
			index = -1;
			String report_type = "";
			report_type = ((SimpleSafetyReportView) list.get(i)).getReport_type();
			if (componentcode != null)
				index = report_type.lastIndexOf(",");
			if (index > -1)
				report_type = report_type.substring(0, index);
			String attachment = "\uC5C6\uC74C";
			attachment = ((SimpleSafetyReportView) list.get(i)).getAttachment();
			if (attachment != null && attachment.length() > 0)
				attachment = "\uC788\uC74C";
			label1 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getGubun()));
			label2 = new Label(++col, i + 1, report_date2);
			label3 = new Label(++col, i + 1, report_date);
			label4 = new Label(++col, i + 1, mreport_date);
			label5 = new Label(++col, i + 1, reporter_type);
			label6 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getCompany_name()));
			label7 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getRepresentative()));
			label8 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getManager()));
			label9 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getTelephone()));
			label10 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getFax()));
			label11 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getEmail()));
			label12 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getAddress()));
			label13 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getEntp_name()));
			label14 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getMeddev_entp_no()));
			label15 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getClass_kor_name()));
			label16 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getMea_class_no()));
			label17 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getGrade()));
			label18 = new Label(++col, i + 1, product_cob_code);
			label19 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getCountry_manufactured()));
			label20 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getManufacturer()));
			label21 = new Label(++col, i + 1, meb_type);
			label22 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getSerial_number()));
			label23 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getSafety_cause_content()));
			label24 = new Label(++col, i + 1, patientcode);
			label25 = new Label(++col, i + 1, medicalCode);
			label26 = new Label(++col, i + 1, componentcode);
			label27 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getFollowup()));
			label28 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getFollow_up_action()));
			label29 = new Label(++col, i + 1, report_type);
			label30 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getReport_status()));
			label31 = new Label(++col, i + 1, Function.nullChk(((SimpleSafetyReportView) list.get(i)).getExtra_info()));
			label32 = new Label(++col, i + 1, Function.nullChk(String.valueOf(((SimpleSafetyReportView) list.get(i)).getFirst_modified())));
			label33 = new Label(++col, i + 1, attachment);
			label34 = new Label(++col, i + 1, Function.nullChk(String.valueOf(((SimpleSafetyReportView) list.get(i)).getKfda_followup())));
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

	long safetyPatientAdd(int safetyReportAddId, String patient_code_id) {
		long returnId = -1L;
		JuncSafetyPatientCondition patientCondition = new JuncSafetyPatientCondition();
		String patient_code_arr[] = patient_code_id.split(",");
		for (int i = 0; i < patient_code_arr.length; i++) {
			patientCondition.setCondition_id(Integer.parseInt(patient_code_arr[i]));
			patientCondition.setReport_id(safetyReportAddId);
			returnId = safetyDAO.safetyPatientAdd(patientCondition);
		}

		return returnId;
	}

	long safetyMedicalCodeAdd(int safetyReportAddId, String medical_code_id) {
		long returnId = -1L;
		JuncSafetyMedicalCode medicalCode = new JuncSafetyMedicalCode();
		String medical_code_id_arr[] = medical_code_id.split(",");
		for (int i = 0; i < medical_code_id_arr.length; i++) {
			medicalCode.setMedical_id(Integer.parseInt(medical_code_id_arr[i]));
			medicalCode.setReport_id(safetyReportAddId);
			returnId = safetyDAO.safetyMedicalCodeAdd(medicalCode);
		}

		return returnId;
	}

	long mebTypeInfoSelectAdd(int addSideEffectId, String mebTypeInfoSelect) {
		long returnId = -1L;
		SimpleSafetyReportMebTypeInfo mebTypeInfo = new SimpleSafetyReportMebTypeInfo();
		String mebTypeInfoSelectArr[] = mebTypeInfoSelect.split(",");
		for (int i = 0; i < mebTypeInfoSelectArr.length; i++)
			if (Integer.parseInt(mebTypeInfoSelectArr[i]) > 0) {
				mebTypeInfo.setMeddev_item_seq(Integer.parseInt(mebTypeInfoSelectArr[i]));
				mebTypeInfo.setReport_id(addSideEffectId);
				returnId = safetyDAO.mebTypeInfoSelectAdd(mebTypeInfo);
			}

		return returnId;
	}

	long safetyComponentCodeAdd(int safetyReportAddId, String component_id) {
		long returnId = -1L;
		JuncSafetyComponentCode componentCode = new JuncSafetyComponentCode();
		String component_id_arr[] = component_id.split(",");
		for (int i = 0; i < component_id_arr.length; i++) {
			componentCode.setComponent_id(Integer.parseInt(component_id_arr[i]));
			componentCode.setReport_id(safetyReportAddId);
			returnId = safetyDAO.safetyComponentCodeAdd(componentCode);
		}

		return returnId;
	}

	public ModelAndView safetyList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=utf-8");
		ModelAndView mav = null;
		mav = new ModelAndView();
		System.out.println("[SimpleSafetyReportDelegate].safetyList()");
		String arrSc = null;
		String arrSv = null;
		String arrOption = null;
		List arraySc = new ArrayList();
		List arraySv = new ArrayList();
		List arrayOption = new ArrayList();
		List arrayIdOrName = new ArrayList();
		HttpPlatformRequest pReq = new HttpPlatformRequest(request);
		try {
			pReq.receiveData();
		} catch (PlatformException e1) {
			e1.printStackTrace();
		}
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
				else if (getVpopName.equals("popPatient"))
					VpopName = "23";
				else if (getVpopName.equals("popMedical"))
					VpopName = "24";
				else if (getVpopName.equals("popComponent"))
					VpopName = "25";
				else if (getVpopName.equals("popSideEffectReportType"))
					VpopName = "27";
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
		String titleImg = "view/style/images/title/sub01_02.jpg";
		String sv = Function.nullChk(request.getParameter("sv"), "");
		System.out.println((new StringBuilder("1[SimpleSafetyReportDelegate].safetyList().sv  ")).append(sv).toString());
		int sc = Function.nullChk(request.getParameter("sc"), -1);
		String year1 = Function.nullChk(request.getParameter("year1"), "");
		String year2 = Function.nullChk(request.getParameter("year2"), "");
		String month1 = Function.nullChk(request.getParameter("month1"), "");
		String month2 = Function.nullChk(request.getParameter("month2"), "");
		int pgSize = Function.nullChk(request.getParameter("pgSize"), 0);
		int firstResult = Function.nullChk(request.getParameter("firstResult"), 0);
		int rowMaxValue = Function.nullChk(request.getParameter("rowMaxValue"), 0);
		String dataSetGubun = Function.nullChk(request.getParameter("dataSetGubun"), "");
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().year1  ")).append(year1).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().year2  ")).append(year2).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().month1  ")).append(month1).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().month2  ")).append(month2).toString());
		if (year1.length() > 0) {
			year1 = year1.substring(0, 4);
			year2 = year2.substring(0, 4);
			month1 = month1.substring(4, 6);
			month2 = month2.substring(4, 6);
		}
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().year1  ")).append(year1).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().year2  ")).append(year2).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().month1  ")).append(month1).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().month2  ")).append(month2).toString());
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		int limit = pg * 10;
		String orderText = Function.nullChk(request.getParameter("orderText"), "");
		String order = Function.nullChk(request.getParameter("order"), "desc");
		String paging = Function.nullChk(request.getParameter("order"), "y");
		BigDecimal total = safetyDAO.reportCount(new SimpleSafetyReportView(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1, year2, month1,
				month2, getPopYN, pgSize, firstResult);
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList.limit  ")).append(limit).toString());
		List list = null;
		List list2 = null;
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList.sv.length()  ")).append(sv.length()).toString());
		int method = 0;
		if (arraySc.size() > 0 || sc > -1) {
			System.out.println("[SimpleSafetyReportDelegate].\uC0C1\uC138\uAC80\uC0C9");
			method = 1;
			list2 = safetyDAO.list2(new SimpleSafetyReportView(), arraySc, arraySv, arrayOption, arrayIdOrName, sc, year1, year2, month1, month2,
					getPopYN, pgSize, firstResult, pg, orderText, order, paging);
		}
		if (sv.length() > 1 && method == 0) {
			method = 2;
			HashSet hs = new HashSet();
			hs = search(sv);
			list = safetyDAO.searchList(new SimpleSafetyReport(), hs, sv);
		}
		if (sv.length() <= 1 && method == 0) {
			method = 3;
			list = null;
		}
		if (sc > -1 && method == 0) {
			method = 4;
			list = safetyDAO.list(new SimpleSafetyReport(), sc, year1, year2, month1, month2);
		}
		PlatformData pdata = new PlatformData();
		int nErrorCode = 0;
		String strErrorMsg = "START";
		DataSet ds = null;
		if (dataSetGubun.equals(""))
			ds = new DataSet("ds_saferyList_server");
		else
			ds = new DataSet("ds_saferyList_more");
		ds.addColumn("ROW", 3);
		ds.addColumn("document_number", 2, 256);
		ds.addColumn("company_name", 2, 256);
		ds.addColumn("reporter_type", 2, 256);
		ds.addColumn("entp_name", 2, 256);
		ds.addColumn("grade", 2, 256);
		ds.addColumn("class_kor_name", 2, 256);
		ds.addColumn("product_cob_code", 2, 256);
		ds.addColumn("medicalcode", 2, 256);
		ds.addColumn("patientcode", 2, 256);
		ds.addColumn("componentcode", 2, 256);
		ds.addColumn("report_type", 2, 256);
		ds.addColumn("safety_cause_content", 2, 256);
		ds.addColumn("sollowup", 2, 256);
		ds.addColumn("report_year", 2, 256);
		ds.addColumn("report_month", 2, 256);
		ds.addColumn("id", 3, 256);
		int row1 = 0;
		if (51 > pg * 50)
			row1 = 51 - pg * 50;
		else
			row1 = pg * 50 - 49;
		int row2 = 0;
		int row = 0;
		int no = 0;
		no = rowMaxValue + 1;
		if (list2 != null) {
			SimpleSafetyReportView r;
			for (Iterator iterator = list2.iterator(); iterator.hasNext(); ds.set(row, "id", r.getId())) {
				r = (SimpleSafetyReportView) iterator.next();
				int endIndex = -1;
				String value1 = "";
				row = ds.newRow();
				row2 = row1++;
				System.out.println((new StringBuilder("[SimpleSaferyReportDelegate].row2  ")).append(row2).toString());
				ds.set(row, "ROW", row2);
				ds.set(row, "document_number", r.getDocument_number());
				ds.set(row, "company_name", r.getCompany_name());
				if (r.getReporter_type() != null) {
					endIndex = r.getReporter_type().lastIndexOf(",");
					value1 = r.getReporter_type().substring(0, endIndex);
				}
				ds.set(row, "reporter_type", value1);
				ds.set(row, "entp_name", r.getEntp_name());
				ds.set(row, "grade", r.getGrade());
				ds.set(row, "class_kor_name", r.getClass_kor_name());
				endIndex = -1;
				value1 = "";
				if (r.getProduct_cob_code() != null)
					if (r.getProduct_cob_code().length() <= 2 || r.getProduct_cob_code().equals("\uD638"))
						value1 = "";
					else
						value1 = r.getProduct_cob_code();
				ds.set(row, "product_cob_code", value1);
				endIndex = -1;
				value1 = "";
				if (r.getMedicalcode() != null) {
					endIndex = r.getMedicalcode().lastIndexOf(",");
					value1 = r.getMedicalcode().substring(0, endIndex);
				}
				ds.set(row, "medicalcode", value1);
				endIndex = -1;
				value1 = "";
				if (r.getPatientcode() != null) {
					endIndex = r.getPatientcode().lastIndexOf(",");
					value1 = r.getPatientcode().substring(0, endIndex);
				}
				ds.set(row, "patientcode", value1);
				endIndex = -1;
				value1 = "";
				if (r.getComponentcode() != null) {
					endIndex = r.getComponentcode().lastIndexOf(",");
					value1 = r.getComponentcode().substring(0, endIndex);
				}
				ds.set(row, "componentcode", value1);
				endIndex = -1;
				value1 = "";
				if (r.getReport_type() != null) {
					endIndex = r.getReport_type().lastIndexOf(",");
					value1 = r.getReport_type().substring(0, endIndex);
				}
				ds.set(row, "report_type", value1);
				ds.set(row, "safety_cause_content", r.getSafety_cause_content());
				ds.set(row, "sollowup", r.getFollowup());
				ds.set(row, "report_year", r.getReport_year());
				ds.set(row, "report_month", r.getReport_month());
			}

		}
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().row  ")).append(row).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().pgSize  ")).append(pgSize).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().firstResult  ")).append(firstResult).toString());
		System.out.println((new StringBuilder("[SimpleSafetyReportDelegate].safetyList().rowMaxValue  ")).append(rowMaxValue).toString());
		pdata.addDataSet(ds);
		VariableList varList = pdata.getVariableList();
		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", total);
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

	public String dsGet(DataSet ds, int rowno, String colid) throws Exception {
		String value = ds.getString(rowno, colid);
		if (value == null)
			return "";
		else
			return value;
	}

	private SafetyDAO safetyDAO;
	private PropertiesDAO propertiesDAO;
	private AttachmentDAO attachmentDAO;
}

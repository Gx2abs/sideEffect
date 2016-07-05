// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleDeviceDelegate.java

package device;

import foreign.Meb_item;
import java.io.PrintStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import properties.PropertiesDAO;
import properties.SimpleReporterType;
import report.SideeffectReportDAO;
import report.SimpleSideeffectReport;

// Referenced classes of package device:
//            DeviceDAO

public class SimpleDeviceDelegate {

	public SimpleDeviceDelegate() {
	}

	public SideeffectReportDAO getSreportDAO() {
		return sreportDAO;
	}

	public void setSreportDAO(SideeffectReportDAO sreportDAO) {
		this.sreportDAO = sreportDAO;
	}

	public PropertiesDAO getPropDAO() {
		return propDAO;
	}

	public void setPropDAO(PropertiesDAO propDAO) {
		this.propDAO = propDAO;
	}

	public DeviceDAO getTestDAO() {
		return testDAO;
	}

	public void setTestDAO(DeviceDAO testDAO) {
		this.testDAO = testDAO;
	}

	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("hi : )");
		testDAO.test();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/view/jsp/device/deviceViewTester.jsp");
		mav.addObject("contentName", "/view/jsp/template/testContent.jsp");
		mav.addObject("footerName", "/view/jsp/template/testContent.jsp");
		return mav;
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/view/jsp/template/sample.jsp");
		List l = testDAO.list(new Meb_item());
		mav.addObject("list", l);
		mav.addObject("contentName", "/view/jsp/template/testContent.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/common/defaultLeft.jsp");
		mav.addObject("rightName", "/view/jsp/common/defaultRight.jsp");
		return mav;
	}

	public ModelAndView property(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/view/jsp/template/sample.jsp");
		Class clas = SimpleReporterType.class;
		List l = propDAO.list(new Meb_item());
		if (l == null)
			System.out.println("propDAO.typedList returned a null list : ");
		else
			System.out.println((new StringBuilder("list.size() :  ")).append(l.size()).toString());
		mav.addObject("list", l);
		mav.addObject("contentName", "/view/jsp/device/content4Device.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/common/defaultLeft.jsp");
		mav.addObject("rightName", "/view/jsp/common/defaultRight.jsp");
		return mav;
	}

	public ModelAndView sreport(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/view/jsp/template/sample.jsp");
		List l = sreportDAO.list(new SimpleSideeffectReport());
		if (l == null)
			System.out.println("sreport.sreportDAO.typedList returned a null list : ");
		else
			System.out.println((new StringBuilder("sreport.list.size() :  ")).append(l.size()).toString());
		mav.addObject("list", l);
		mav.addObject("contentName", "/view/jsp/report/sideEffect/content4SideEffectReport.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/common/defaultLeft.jsp");
		mav.addObject("rightName", "/view/jsp/common/defaultRight.jsp");
		return mav;
	}

	DeviceDAO testDAO;
	PropertiesDAO propDAO;
	SideeffectReportDAO sreportDAO;
}

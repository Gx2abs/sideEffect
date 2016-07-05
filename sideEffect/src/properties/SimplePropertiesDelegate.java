/**
 * 
 */
package properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;

import org.springframework.web.servlet.ModelAndView;

import foreign.MeaClassNoCompositeKey;
import foreign.Mea_class_no;
import foreign.Mea_class_no_view2;
import foreign.Meb_item;
import abstraction.IDValuePair;
import properties.company.CompanyHistory;
import properties.company.CompanyServiceProvider;
import properties.company.SimpleCloseType;
import properties.company.SimpleCompany;
import properties.company.SimpleCompanyHistory;
import properties.company.SimpleFlagType;
import properties.company.SimpleJuncCompany;
import properties.component.SimpleComponentHistory;
import properties.component.SimpleJuncComponent;
import properties.country.CountryHistory;
import properties.country.CountryServiceProvider;
import properties.country.SimpleCountry;
import properties.country.SimpleCountryHistory;
import properties.country.SimpleJuncCountry;
import properties.history.History;
import properties.history.HistoryServiceProvider;
import properties.item.ItemHistory;
import properties.item.SimpleHistoryType;
import properties.item.SimpleItem;
import properties.item.SimpleItemCodeType;
import properties.item.SimpleItemGrade;
import properties.item.SimpleItemHistory;
import properties.item.SimpleJuncItemMCN;
import properties.item.SimpleTraceability;
import properties.match.MatchServiceProvider;
import properties.match.SimpleMatchMaster;
import properties.medical.device.SimpleJuncMedicalDeviceMalfunction;
import properties.medical.device.SimpleMedicalDeviceMalfunctionHistory;
import properties.patient.condition.PatientConditionHistory;
import properties.patient.condition.SimplePatientConditionHistory;
import properties.patient.condition.SimpleJuncPatientCondition;
import properties.product.ProductServiceProvider;
import properties.product.SimpleJuncProduct;
import properties.product.SimpleJuncProductType;
import properties.product.SimpleProduct;
import properties.product.SimpleProductCodeType;
import properties.product.SimpleProductHistory;
import properties.product.SimpleProductType;
import properties.report.type.ReportTypeHistory;
import properties.report.type.SimpleJuncReportType;
import properties.report.type.SimpleReportType;
import properties.report.type.SimpleReportTypeHistory;
import properties.SimplePatientCondition;
import safety.SimpleSafetyReport;
import safety.renewal.sgi.category.SimpleItemCategory;
import safety.renewal.sgi.category.SimpleItemCategoryCodeType;
import safety.renewal.sgi.category.SimpleItemCategoryLevel;

/**
 * @author Adam Hun/���ѿ�
 * @date 2013. 10. 29.
 * @description
 * TODO TODO
 */
public class SimplePropertiesDelegate extends abstraction.SimpleDelegate{

	private PropertiesDAO propertiesDAO;
	private CountryServiceProvider countryServiceProvider;
	private CompanyServiceProvider companyServiceProvider;
	private ProductServiceProvider productServiceProvider;
	private HistoryServiceProvider historyServiceProvider;
	private MatchServiceProvider matchServiceProvider;
	
	public MatchServiceProvider getMatchServiceProvider() {
		return matchServiceProvider;
	}

	public void setMatchServiceProvider(
			MatchServiceProvider matchingItemServiceProvider) {
		this.matchServiceProvider = matchingItemServiceProvider;
	}

	public CompanyServiceProvider getCompanyServiceProvider() {
		return companyServiceProvider;
	}

	public ProductServiceProvider getProductServiceProvider() {
		return productServiceProvider;
	}

	public void setProductServiceProvider(
			ProductServiceProvider productServiceProvider) {
		this.productServiceProvider = productServiceProvider;
	}

	public void setCompanyServiceProvider(
			CompanyServiceProvider companyServiceProvider) {
		this.companyServiceProvider = companyServiceProvider;
	}

	public HistoryServiceProvider getHistoryServiceProvider() {
		return historyServiceProvider;
	}

	public void setHistoryServiceProvider(
			HistoryServiceProvider historyServiceProvider) {
		this.historyServiceProvider = historyServiceProvider;
	}

	public CountryServiceProvider getCountryServiceProvider() {
		return countryServiceProvider;
	}

	public void setCountryServiceProvider(
			CountryServiceProvider countryServiceProvider) {
		this.countryServiceProvider = countryServiceProvider;
	}

	public PropertiesDAO getPropertiesDAO() {
		return propertiesDAO;
	}

	public void setPropertiesDAO(PropertiesDAO propertiesDAO) {
		this.propertiesDAO = propertiesDAO;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		list(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		List<SimpleItem> dl = propertiesDAO.list(SimpleItem.class);
		mav.addObject("list", dl);
		mav.setViewName("/view/jsp/properties/device/deviceList1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
	listItem(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_01.jpg";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
		int codeAgeId = Function.nullChk(request.getParameter("codeAge"), 0);
		int meaClassNoLevelId = Function.nullChk(request.getParameter("meaClassNoLevel"), 0);
		
		Mea_class_no_view2 target2 = new Mea_class_no_view2();
		SimpleItemCodeType codeAge = new SimpleItemCodeType();
		codeAge.setId(codeAgeId);
		target2.setCode_age_id(codeAgeId);
		codeAge = propertiesDAO.read(SimpleItemCodeType.class, codeAgeId);
		target2.setCode_age(codeAge);
	
		SimpleMeaClassNoLevel meaClassNoLv = new SimpleMeaClassNoLevel();
		meaClassNoLv.setId(meaClassNoLevelId);
		target2.setClass_level(meaClassNoLevelId);
		meaClassNoLv = propertiesDAO.read(SimpleMeaClassNoLevel.class, meaClassNoLevelId);
		target2.setMeaClassNoLevel(meaClassNoLv);
	
		if(!"".equals(searchColumn)){
		
			Method[] methods = Mea_class_no_view2.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(target2, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		}
	
		Page page = new Page();
		String pageString = "";
		setDefaultViewSet(mav, request);
		List<Mea_class_no_view2> dl = null;
 
		List<SimpleMeaClassNoLevel> meaClassNoLevel =  propertiesDAO.list(SimpleMeaClassNoLevel.class);
	
		long total = propertiesDAO.count(target2,0);
		long top = total - (pg-1)*10;
		pageString = page.pageList((int)total,10,pg,"properties.do?action=listItem&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn+"&codeAge="+codeAgeId+"&meaClassNoLevel="+meaClassNoLevelId,"");
		mav.addObject("meaClassNoLevel",meaClassNoLevel);
	
		dl = propertiesDAO.listMeaClassNoView2(target2,pg,10, 1);
		List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
		mav.addObject("list", dl);
		mav.addObject("itemCodeTypes", itemCodeTypes);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
		mav.addObject("contentName","/view/jsp/properties/item/itemList1.jsp");
	 
		return mav;
	}
	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
 	* @param response
 	* @return
 	* 2013. 11. 8. 2013
 	* @description
 	*/
	public org.springframework.web.servlet.ModelAndView
	createItemPage(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	String titleImg = "view/style/images/title/sub03_01.jpg";
	long level = Function.nullChk(request.getParameter("level"), -1);
	
	if(level< 1){
		mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.addObject("message","오류 발생. 품목코드 작성시에 필요한 level 정보가 올바르지않습니다.");
		return mav;
	}
	
	if(level==1){
		
	}else {
		
		List<Mea_class_no> mea_class_no_list =propertiesDAO.listMeaClassNo(null, 1);
		mav.addObject("level1List",mea_class_no_list);
		
	}
	SimpleMeaClassNoLevel meaClassNoLevel =  propertiesDAO.read(SimpleMeaClassNoLevel.class, level);
	mav.addObject("meaClassNoLevel",meaClassNoLevel);
	
	//
	
	//
	List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
	//
	List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
	//등급
	List<SimpleItemGrade> itemGrades =  propertiesDAO.list(SimpleItemGrade.class);
	
	setDefaultViewSet(mav, request);
	
	mav.addObject("itemGrades",itemGrades);
	mav.addObject("itemCodeTypes",itemCodeTypes);
	mav.addObject("isInUse",isInUse);
	mav.addObject("traceabilityList",traceabilityList);
	mav.addObject("historyType",historyType);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
	mav.addObject("contentName","/view/jsp/properties/item/createItem1.jsp");
	
	return mav;
}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		createItem(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String parentItem = Function.nullChk(request.getParameter("parentItem"),"");
		int parentItemGrade = Function.nullChk(request.getParameter("parentItemGrade"), 0);
		int itemGrade = Function.nullChk(request.getParameter("itemGrade"), 0);
		int itemCodeType = Function.nullChk(request.getParameter("itemCodeType"), 0);//코드구분
		String itemNameKr = Function.nullChk(request.getParameter("itemNameKr"), "");
		String itemNameEn = Function.nullChk(request.getParameter("itemNameEn"), "");
		String itemDesc = Function.nullChk(request.getParameter("itemDesc"), "");
		int traceability = Function.nullChk(request.getParameter("traceability"), 0);
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		int level = Function.nullChk(request.getParameter("level"), 0);
		String udiCode = Function.nullChk(request.getParameter("udiCode"), ""); //고유식별코드
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);

	if(privilegeId>1){
		if(level==3){
			parentItem = Function.nullChk(request.getParameter("parentItem2"), "");
			parentItemGrade = Function.nullChk(request.getParameter("parentItemGrade2"), 0);
		}
		 
		String mea_class_no_code= Function.nullChk(request.getParameter("mea_class_no_code"), "");
		 
		if(level==1){
			parentItem = mea_class_no_code;
			parentItemGrade = itemGrade;
		}
		
		Mea_class_no si = new  Mea_class_no();
		MeaClassNoCompositeKey key = new MeaClassNoCompositeKey();
		key.setGrade(itemGrade);
		key.setMea_class_no(mea_class_no_code);
		si.setCompositeKey(key);
		si.setGrade(Integer.toString(itemGrade));
		si.setMea_class_no(mea_class_no_code);
		si.setClass_kor_name(itemNameKr);
		si.setClass_eng_name(itemNameEn);
		si.setClass_cont(itemDesc);
		si.setUdi_code(udiCode);
		si.setHigher_class_no(parentItem);
		si.setClass_level(level);
		si.setHigher_class_grade(parentItemGrade);
		//traceability
		SimpleTraceability st = new SimpleTraceability();
		st.setId(traceability);
		si.setTraceability(st);
		SimpleIsInUse siiu = new SimpleIsInUse();
		siiu.setId(isInUse);
		si.setIsInUse(siiu);
		si.setUse_yn(isInUse==1?"Y":"N");
		si.setTrace_manage_target_yn(traceability==1?"Y":"N");
		
		//신/구 코드 구분
		SimpleItemCodeType sict = new SimpleItemCodeType();
		sict.setId(itemCodeType);
		si.setCode_age(sict);
		
		Mea_class_no inserted = propertiesDAO.create(si);
		
		//이력 새로 추가 
				History sih = historyServiceProvider.buildOrEmpty(request, SimpleItemHistory.class);
				if(sih!=null){
					sih = propertiesDAO.create(sih);
					SimpleJuncItemMCN jnc = new SimpleJuncItemMCN();
					MeaClassNoCompositeKey compositeKey = new MeaClassNoCompositeKey();
					compositeKey.setMea_class_no(inserted.getMea_class_no());
					compositeKey.setGrade(Integer.parseInt(inserted.getGrade()));
					jnc.setGrade(Integer.parseInt(inserted.getGrade()));
					jnc.setMea_class_no(inserted.getMea_class_no());
					jnc.setHistoryId(sih.getId());
					jnc.setCompositeKey(compositeKey);
					propertiesDAO.create(jnc, true);
				}
		
		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.addObject("message","create succeess");*/
		mav.addObject("returnUrl","properties.do?action=listItem");
		mav.setViewName("properties.do?action=listItem");
	}else mav.setViewName("properties.do?action=listItem");
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description 품목코드 읽기/뷰
	 */
	public org.springframework.web.servlet.ModelAndView
		readItem(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_01.jpg";
		String grade = Function.nullChk(request.getParameter("grade"), "-1");
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		//Level1 
		//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
		//
		List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		
		// 
		List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
		
		//
		List<SimpleItemGrade> itemGrades =  propertiesDAO.list(SimpleItemGrade.class);
			
		
		setDefaultViewSet(mav, request);
		
		//mav.addObject("level1List",dl);
		mav.addObject("itemGrades",itemGrades);
		mav.addObject("itemCodeTypes",itemCodeTypes);
		mav.addObject("isInUse",isInUse);
		mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		Mea_class_no target = new Mea_class_no();
		target.setGrade(grade);
		target.setMea_class_no(mea_class_no);
		Mea_class_no found = (Mea_class_no) propertiesDAO.readMeaClassNo(target) ;
		
		
		if (found.getClass_level() == 3) {
			IDValuePair foundParent = propertiesDAO.getParent(found.getHigher_class_no());
			mav.addObject("parent", foundParent);
		}
		/*Mea_class_no parent = new Mea_class_no();
		parent.setMea_class_no(found.getHigher_class_no());
		parent.setHigher_class_grade(found.getHigher_class_grade());
		System.out.println("trying to find a parent with " + found.getHigher_class_no() + " / " + found.getHigher_class_grade());
		IDValuePair foundParent = propertiesDAO.getParent(found.getHigher_class_no());//get parent
*/		mav.addObject("article", found);
		/*mav.addObject("parent", foundParent);*/
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
		mav.addObject("contentName","/view/jsp/properties/item/readItem1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description 품목코드 업데이트
	 */
	@SuppressWarnings("deprecation")
	public org.springframework.web.servlet.ModelAndView
		updateItem(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		int grade = Function.nullChk(request.getParameter("grade"), -1);
		String stringGrade = Function.nullChk(request.getParameter("grade"), "-1");
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		
		System.out.println("updateMea : " + grade + " / " + mea_class_no);
		
		Mea_class_no target = new Mea_class_no();
		MeaClassNoCompositeKey key = new MeaClassNoCompositeKey();
		key.setGrade(grade);
		key.setMea_class_no(mea_class_no);
		//target.setCompositeKey(key);
		target.setGrade(stringGrade);
		target.setMea_class_no(mea_class_no);
		Mea_class_no  found = null; // propertiesDAO.readMeaClassNo(Mea_class_no.class, grade, mea_class_no) ;
		String class_kor_name = Function.nullChk(request.getParameter("class_kor_name"), "");
		String class_eng_name = Function.nullChk(request.getParameter("class_eng_name"), "");
		String class_cont = Function.nullChk(request.getParameter("class_cont"), "");
		//추적관리대상여부
		int traceability = Function.nullChk(request.getParameter("traceability"), 0);
		int parentItem = Function.nullChk(request.getParameter("parentItem"), 0);
		int itemCodeType = Function.nullChk(request.getParameter("itemCodeType"), 0);//코드구분
		//사용중/
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		//고유식별코드
		String udiCode = Function.nullChk(request.getParameter("udiCode"), "");
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
	if(privilegeId>1){
		//이력 새로 추가 
		History sih = historyServiceProvider.buildOrEmpty(request, SimpleItemHistory.class);
		found = new Mea_class_no();
		SimpleTraceability sit = new SimpleTraceability();
		sit.setId(traceability);
		found.setCompositeKey(key);
		found.setTraceability(sit);
		SimpleIsInUse siiu = new SimpleIsInUse();
		siiu.setId(isInUse);
		found.setIsInUse(siiu);
		found.setClass_kor_name(class_kor_name);
		found.setClass_cont(class_cont);
		found.setClass_eng_name(class_eng_name);
		found.setMea_class_no(mea_class_no);
		found.setGrade(stringGrade);
		found.setUdi_code(udiCode);
		SimpleItemCodeType sct = new SimpleItemCodeType();
		sct.setId(itemCodeType);
		found.setCode_age(sct);
		
		//found.setTraceability(traceability);
		
		System.out.println(found);
		MeaClassNoHandler.updateMeaClassNo(found);
		//Mea_class_no updated = propertiesDAO.updateMeaClassNo(found);
		if(sih!=null){
			sih = propertiesDAO.create(sih);
			SimpleJuncItemMCN jnc = new SimpleJuncItemMCN();
			MeaClassNoCompositeKey compositeKey = new MeaClassNoCompositeKey();
			compositeKey.setMea_class_no(mea_class_no);
			compositeKey.setGrade(grade);
			jnc.setGrade(grade);
			jnc.setMea_class_no(mea_class_no);
			jnc.setHistoryId(sih.getId());
			jnc.setCompositeKey(compositeKey);
			propertiesDAO.create(jnc, true);
		}
		
		found = (Mea_class_no) propertiesDAO.readMeaClassNo(found);
		
		mav.addObject("article", found);
		mav.setViewName( "properties.do?action=readItem&articleId="+found.getId());
	}else mav.setViewName("properties.do?action=listItem");	
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description 품목코드 업데이트 페이지로 이동함.
	 */
	public org.springframework.web.servlet.ModelAndView
		updateItemPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String stringGrade = Function.nullChk(request.getParameter("grade"), "-1");
		String titleImg = "view/style/images/title/sub03_01.jpg";
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		int grade = Function.nullChk(request.getParameter("grade"), -1);
		Mea_class_no target = new Mea_class_no();
		target.setMea_class_no(mea_class_no);
		target.setGrade(stringGrade);
		Mea_class_no found = (Mea_class_no) propertiesDAO.readMeaClassNo(target);
		
		//Level1 
		//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class,1,10);
		//
		List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
		//
		List<SimpleItemGrade> itemGrades =  propertiesDAO.list(SimpleItemGrade.class);
		
		setDefaultViewSet(mav, request);
		SimpleMeaClassNoLevel meaClassNoLevel =  propertiesDAO.read(SimpleMeaClassNoLevel.class, found.getClass_level());
		
		//get parent
		if (found.getClass_level() == 3) {
			IDValuePair foundParent = propertiesDAO.getParent(found.getHigher_class_no());
			mav.addObject("parent", foundParent);
		}
		/*parent.setMea_class_no(found.getHigher_class_no());
		parent.setHigher_class_grade(found.getHigher_class_grade());
		IDValuePair foundParent = propertiesDAO.getParent(found.getHigher_class_no());*/
		
		/*mav.addObject("parent", foundParent);*/
		//mav.addObject("level1List",dl);
		mav.addObject("itemGrades",itemGrades);
		mav.addObject("itemCodeTypes",itemCodeTypes);
		mav.addObject("isInUse",isInUse);
		mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		mav.addObject("meaClassNoLevel",meaClassNoLevel);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
		mav.addObject("contentName","/view/jsp/properties/item/updateItem1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteItem(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
		setDefaultViewSet(mav, request);	
		Mea_class_no target = new Mea_class_no();
	
		String grade = Function.nullChk(request.getParameter("grade"), "-1");
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
	if(privilegeId>1){
		MeaClassNoCompositeKey key = new MeaClassNoCompositeKey();
		key.setGrade(Integer.parseInt(grade));
		key.setMea_class_no(mea_class_no);
		target.setGrade(grade);
		target.setMea_class_no(mea_class_no);
		target.setCompositeKey(key);
		//Mea_class_no found = propertiesDAO.readMeaClassNo(target) ;
		//propertiesDAO.deleteMeaClassNo( target);
		/*MeaClassNoHandler.deleteMeaClassNo(target);*/
		//propertiesDAO.delete( target);
	
		IDValuePair found = propertiesDAO.readMeaClassNo(target);
	
		Set<SimpleItemHistory> history = ((Mea_class_no) found).getHistory();
	
		for(History h : history){
			propertiesDAO.delete(h);
		
		}
		propertiesDAO.delete(target);
		/*MeaClassNoHandler.deleteMeaClassNoJunc(target);
		MeaClassNoHandler.deleteMeaClassNo(target);*/
	
		/*mav.addObject("message","job done");
		mav.addObject("returnUrl","properties.do?action=listItem");
		mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");*/
		mav.addObject("returnUrl","properties.do?action=listItem");
		mav.setViewName("properties.do?action=listItem");
	}else mav.setViewName("properties.do?action=listItem");
		return mav;
	}
	
	public ModelAndView checkItemUsage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		String grade = Function.nullChk(request.getParameter("grade"), "-1");
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		
		return mav;
	}
	
	public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request){
	
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/properties/propertiesLeft.jsp");
		mav.addObject("rightName", request.getAttribute("rightName"));
		return mav;
	}

	public org.springframework.web.servlet.ModelAndView
		deleteItemHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleItemHistory();
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
	if(privilegeId>1){
		target.setId(articleId);
		propertiesDAO.delete( target);
		propertiesDAO.clearJunction("JUNC_ITEM_HISTORY","HISTORY_ID",articleId);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
	}else mav.setViewName("properties.do?action=listItem");
		return mav;
	}

	public org.springframework.web.servlet.ModelAndView
		listCountry(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		String titleImg = "view/style/images/title/sub03_07.jpg";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		setDefaultViewSet(mav, request);
		SimpleCountry sc = new SimpleCountry();
		String name = request.getParameter("searchKeyword")==""?null:request.getParameter("searchKeyword");
		sc.setPropertyValue(name);
		List<SimpleCountry> dl = propertiesDAO.list(sc,pg,10);
		long total = propertiesDAO.count(sc);
		long top = total - (pg-1)*10;
		Page page = new Page();
		String pageString = page.pageList((int)total,10,pg,"properties.do?action=listCountry","", request);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_07.jpg");
		mav.addObject("contentName","/view/jsp/properties/country/countryList1.jsp");
		
		return mav;
	}

	public org.springframework.web.servlet.ModelAndView
		createCountryPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		
		
		String titleImg = "view/style/images/title/sub03_07.jpg";
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		
		
			
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("isInUse",isInUse);
		mav.addObject("historyType",historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_07.jpg");
		mav.addObject("contentName","/view/jsp/properties/country/createCountry1.jsp");
		
		return mav;
	}

	public org.springframework.web.servlet.ModelAndView
		createCountry(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		 
		
		setDefaultViewSet(mav, request);
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String historyManager = Function.nullChk(request.getParameter("history_manager"), "");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("history_description"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		SimpleCountry insertTarget = new SimpleCountry();
		insertTarget.setIsInUse(isInUse);
		insertTarget.setPropertyValue(propertyValue);
		String dateString  = activeFrom.replaceAll("/",":") + " 00:00:00";
		System.out.println("lastModified : " + dateString);
		insertTarget.setLastModified(new Date());
		insertTarget.setActiveFrom(new Date());
		
		SimpleCountry found = propertiesDAO.create(insertTarget);
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimpleCountryHistory.class);
		 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getCountryHistory().add(childObject);
			propertiesDAO.update(found);
		}
		
		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");*/
		/*mav.addObject("message","create succeess");*/
		mav.addObject("returnUrl","properties.do?action=listCountry");
		mav.setViewName("properties.do?action=listCountry");
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/�꾪븳��
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 6. 2013
	 * @description 援��肄붾뱶 �곸꽭蹂닿린
	 */
	public org.springframework.web.servlet.ModelAndView
		readCountry(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_07.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		//Level1 
		//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
		//
		List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		IDValuePair found = propertiesDAO.read(SimpleCountry.class, articleId);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_07.jpg");
		mav.addObject("contentName","/view/jsp/properties/country/readCountry1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 6. 2013
	 * @description 공통코드->국가코드 수정작업 
	 */
	public org.springframework.web.servlet.ModelAndView
		updateCountry(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		SimpleCountry targetObject = new SimpleCountry();
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
		
		System.out.println("lastModified:=" + lastModified);
		System.out.println("activeFrom:=" + activeFrom);
		System.out.println("historyManager:=" + historyManager);
		System.out.println("historyType:=" + historyType);
		System.out.println("historyDescription:=" + historyDescription);
		
		
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		SimpleCountry found = propertiesDAO.read(SimpleCountry.class, articleId);
		found.setPropertyValue(propertyValue);
		found.setIsInUse(isInUse);
		System.out.println(":::0");
		History childObject = historyServiceProvider.buildOrEmpty(request, SimpleCountryHistory.class);
		System.out.println(":::");
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getCountryHistory().add(childObject);
			System.out.println(":::1");
		}
		SimpleCountry updated = propertiesDAO.update(found);
		mav.setViewName("properties.do?action=readCountry&articleId="+articleId);
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/�꾪븳��
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 6. 2013
	 * @description 援��肄붾뱶 �섏젙 �섏씠吏�줈 蹂대궡��硫붿냼��
	 */
	public org.springframework.web.servlet.ModelAndView
		updateCountryPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		
		String titleImg = "view/style/images/title/sub03_07.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		IDValuePair found = propertiesDAO.read(SimpleCountry.class, articleId);
		
		
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("isInUse",isInUse);
		mav.addObject("historyType",historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_07.jpg");
		mav.addObject("contentName","/view/jsp/properties/country/updateCountry1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/�꾪븳��
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 6. 2013
	 * @description	援��肄붾뱶 ��젣 硫붿냼��
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteCountry(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		System.out.println("Deleting country ");
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleCountry();
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		target.setId(articleId);
		//IDValuePair found = 
		SimpleCountry read = propertiesDAO.read(SimpleCountry.class, articleId);
		Set<History> history = read.getCountryHistory();
		for(History h : history){
			propertiesDAO.clearJunction("JUNC_COUNTRY_HISTORY","COUNTRY_ID",articleId);
			propertiesDAO.delete(h);
		}
		propertiesDAO.delete( target);
		
		//mav.addObject("article", found);
		/*mav.addObject("message","job done");*/
		mav.addObject("returnUrl","properties.do?action=listCountry");
		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");*/
		mav.setViewName("properties.do?action=listCountry");
	}else{
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
	return mav;
	}
	
	//report type
	public org.springframework.web.servlet.ModelAndView
	listReportType(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		String titleImg = "view/style/images/title/sub03_08.jpg";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		setDefaultViewSet(mav, request);
		SimpleReportType sr = new SimpleReportType();
		String name = request.getParameter("searchKeyword")==""?null:request.getParameter("searchKeyword");
		sr.setPropertyValue(name);
		List<SimpleReportType> dl = propertiesDAO.list(sr,pg,10);
		long total = propertiesDAO.count(sr);
		long top = total - (pg-1)*10;
		Page page = new Page();
		String pageString = page.pageList((int)total,10,pg,"properties.do?action=listReportType","",request);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_08.jpg");
		mav.addObject("contentName","/view/jsp/properties/reportType/reportTypeList1.jsp");
	
		return mav;
	}
		
	public org.springframework.web.servlet.ModelAndView 
		readReportType(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_08.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		//
		List<SimpleHistoryType> historyType = propertiesDAO.list(SimpleHistoryType.class);

		//
		List<SimpleIsInUse> isInUse = propertiesDAO.list(SimpleIsInUse.class);

		setDefaultViewSet(mav, request);

		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		IDValuePair found = propertiesDAO.read(SimpleReportType.class,articleId);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_08.jpg");
		mav.addObject("contentName","/view/jsp/properties/reportType/readReportType1.jsp");

		return mav;
	}

	public org.springframework.web.servlet.ModelAndView 
		createReportTypePage(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		String titleImg = "view/style/images/title/sub03_08.jpg";
		//
		List<SimpleHistoryType> historyType = propertiesDAO.list(SimpleHistoryType.class);

		//
		List<SimpleIsInUse> isInUse = propertiesDAO.list(SimpleIsInUse.class);

		setDefaultViewSet(mav, request);

		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_08.jpg");
		mav.addObject("contentName","/view/jsp/properties/reportType/createReportType1.jsp");

		return mav;
	}

		public org.springframework.web.servlet.ModelAndView 
		createReportType(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		Enumeration<String> headernames = request.getHeaderNames();
		String headerName = "";
		String headerVal = "";
		while (headernames.hasMoreElements()) {

			headerName = headernames.nextElement();
			headerVal = request.getHeader(headerName);

			System.out.println(headerName + "/" + headerVal);
		}

		try {
			request.setCharacterEncoding("8859_1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultViewSet(mav, request);

		String lastModified = Function.nullChk(request.getParameter("lastModified"), "");
		String manager = Function.nullChk(request.getParameter("history_manager"), "");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		String historyDescription = Function.nullChk(request.getParameter("history_description"), "");
		/*try {
			propertyValue = new String(propertyValue.getBytes("8859_1"),"utf-8");
			manager = new String(manager.getBytes("8859_1"), "utf-8");
			historyDescription = new String(historyDescription.getBytes("8859_1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int historyType = Function.nullChk(request.getParameter("historyType"),0);
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		SimpleReportType insertTarget = new SimpleReportType();
		SimpleReportTypeHistory insertTarget2 = new SimpleReportTypeHistory();
		SimpleJuncReportType insertTarget3 = new SimpleJuncReportType();

		insertTarget2.setHistoryType(historyType);
		insertTarget2.setManager(manager);
		insertTarget2.setHistoryDescription(historyDescription);

		insertTarget.setIsInUse(isInUse);
		insertTarget.setPropertyValue(propertyValue);

		int addReportTypeId = propertiesDAO.add(insertTarget); // add = s.save
		if(activeFrom!=""){
			try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date af = new Date();
				af = sdf.parse(activeFrom);
				insertTarget2.setActiveFrom(af);
			} catch (ParseException e) {
				// 	TODO Auto-generated catch block
			}
		}else {insertTarget2.setActiveFrom(new Date());}
		insertTarget2.setLastModified(new Date());
		insertTarget2.setReportTypeId(addReportTypeId);
		int addHistoryId = propertiesDAO.add(insertTarget2);
	
		insertTarget3.setReport_Type_id(addReportTypeId);
		insertTarget3.setHistory_id(addHistoryId);

		propertiesDAO.create(insertTarget3);

		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.addObject("message", "create succeess");*/
		mav.addObject("returnUrl", "properties.do?action=listReportType");
		mav.setViewName("properties.do?action=listReportType");
		
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
		return mav;

	}

	public org.springframework.web.servlet.ModelAndView 
	updateReportType(HttpServletRequest request, HttpServletResponse response) {
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
		setDefaultViewSet(mav, request);
		int articleId = Function.nullChk(request.getParameter("articleId"), -1);

		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		/*String lastModified = Function.nullChk(request.getParameter("lastModified"),"");*/
		String manager = Function.nullChk(request.getParameter("historyManager"), "");
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		SimpleReportType insertTarget = propertiesDAO.read(SimpleReportType.class,articleId);
		SimpleReportTypeHistory insertTarget2 = new SimpleReportTypeHistory();
		SimpleJuncReportType insertTarget3 = new SimpleJuncReportType();
		if(activeFrom!=""){
			try {
			String dateString = activeFrom.replaceAll("/", "-");
			String year1=dateString.substring(6,10);
			String month=dateString.substring(0,5);
			String date1=year1+("-")+month;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date af = new Date();
				af = sdf.parse(date1);
				insertTarget2.setActiveFrom(af);
				insertTarget2.setHistoryType(historyType);
				insertTarget2.setManager(manager);
				insertTarget2.setHistoryDescription(historyDescription);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
			}
		}/*else {insertTarget2.setActiveFrom(new Date());}*/
	

		insertTarget.setIsInUse(isInUse);
		insertTarget.setPropertyValue(propertyValue);
		/*insertTarget.setLastModified(new Date());
		insertTarget.setActiveFrom(new Date());*/
		propertiesDAO.update(insertTarget);

		insertTarget2.setReportTypeId(articleId);

		insertTarget2.setLastModified(new Date());
		if(activeFrom!="" && historyDescription!=""){
			int addHistoryId = propertiesDAO.add(insertTarget2);
			insertTarget3.setReport_Type_id(articleId);
			insertTarget3.setHistory_id(addHistoryId);
			propertiesDAO.create(insertTarget3);
		}
		mav.addObject("returnUrl", "properties.do?action=listReportType");
		mav.setViewName("properties.do?action=readReportType&articleId="+articleId);
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
		return mav;
	}


	/**
	 * 
	 * @author 
	 * @param request
	 * @param response
	 * @return 2013. 11. 19. 2013
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView 
		updateReportTypePage(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		setDefaultViewSet(mav, request);
		
		String titleImg = "view/style/images/title/sub03_08.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);

		IDValuePair found = propertiesDAO.read(SimpleReportType.class,articleId);

		//
		List<SimpleHistoryType> historyType = propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse = propertiesDAO.list(SimpleIsInUse.class);
		//

		setDefaultViewSet(mav, request);

		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_08.jpg");
		mav.addObject("contentName","/view/jsp/properties/reportType/updateReportType1.jsp");

		return mav;
	}

	/**
	 * 
	 * @author 
	 * @param request
	 * @param response
	 * @return 2013. 11. 19. 2013
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView 
		deleteReportType(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		System.out.println("Deleting reportType ");
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleReportType();

		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		target.setId(articleId);
		// IDValuePair found =
		// history delete
		SimpleReportType master = propertiesDAO.read(SimpleReportType.class,articleId);

		Set<ReportTypeHistory> historySet = master.getReportTypeHistory();

		for (ReportTypeHistory h : historySet) {
			propertiesDAO.clearJunction("JUNC_REPORT_TYPE_HISTORY","REPORT_TYPE_ID",articleId);
			propertiesDAO.delete(h);
		}

		propertiesDAO.delete(target);
	
		mav.addObject("returnUrl", "properties.do?action=listReportType");
		mav.setViewName("properties.do?action=listReportType");
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		deleteReportTypeHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleReportTypeHistory();
	
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		System.out.println("reportHistory111"+privilegeId);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		System.out.println("reportHistory111"+privilegeId);
		target.setId(articleId);
		propertiesDAO.clearJunction("JUNC_REPORT_TYPE_HISTORY","HISTORY_ID",articleId);
		propertiesDAO.delete( target);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}
		return mav;
	}

	public org.springframework.web.servlet.ModelAndView 
		listCountryPop(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		System.out.println("[SimplePropertiesDelegate].listCountryPop().");
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		// setDefaultViewSet(mav, request);
		SimpleCountry sc = new SimpleCountry();
		List<SimpleCountry> dl = propertiesDAO.list(sc, pg, 10);
		long total = propertiesDAO.count(new SimpleCountry());
		long top = total - (pg - 1) * 10;
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg,"properties.do?action=listCountryPop", "");
		mav.addObject("titleImage", "view/style/images/title/sub03_05_02.jpg");
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.setViewName("/view/jsp/properties/country/countryListPop1.jsp");

		return mav;
	}


	//////////////////////////////////////제품코드//////////////////////////////////////////
	public org.springframework.web.servlet.ModelAndView
	listProduct(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	String titleImg = "view/style/images/title/sub03_06.jpg";
	int pg = Function.nullChk(request.getParameter("pg"), 1);
	setDefaultViewSet(mav, request);
	
	String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
	String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
	
	SimpleProduct sc = new SimpleProduct();
	
	List<SimpleProduct> dl = null;
	long total =0L;
	
	if(!"".equals(searchColumn)){
		
		Method[] methods = SimpleProduct.class.getDeclaredMethods();
		//업체명
		if(searchColumn.equals("setEntp_name")){
			dl = propertiesDAO.listEntpProduct(pg,10,searchKeyword);
			total = propertiesDAO.listEntpProductCnt(searchKeyword);
		} // 품목명 
		else if(searchColumn.equals("setClass_kor_name")){
			dl = propertiesDAO.listItemProduct(pg,10,searchKeyword);
			total = propertiesDAO.listItemProductCnt(searchKeyword);
		} else {
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(sc, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}// for End
			List<SimpleProduct> set2 = propertiesDAO.list(sc,pg,10);
			dl =new ArrayList(set2);
			total = propertiesDAO.count(sc);
		}
			
	}else{
		List<SimpleProduct> set2 = propertiesDAO.list(sc,pg,10);
		dl =new ArrayList(set2);
		total = propertiesDAO.count(sc);
	}
	setDefaultViewSet(mav, request);
	
	//long total = propertiesDAO.count(new SimpleProduct());
	//long total = 100;
	long top = total - (pg-1)*10;
	Page page = new Page();
	//String pageString = page.pageList((int)total,10,pg,"properties.do?action=listProduct","");
	String pageString = page.pageList((int)total,10,pg,"properties.do?action=listProduct&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn,"");
	
	mav.addObject("top", top);
	mav.addObject("total", total);
	mav.addObject("list", dl);
	mav.addObject("pageString", pageString);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
	mav.addObject("contentName","/view/jsp/properties/product/productList1.jsp");
	
	return mav;
	}

public org.springframework.web.servlet.ModelAndView
	createProductPage(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	String titleImg = "view/style/images/title/sub03_06.jpg";
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
	
	// 업종코드
	List<SimpleProductCodeType> codeType =  propertiesDAO.list(SimpleProductCodeType.class);
	
	setDefaultViewSet(mav, request);
	
	mav.addObject("codeType",codeType);
	mav.addObject("isInUse",isInUse);
	mav.addObject("historyType",historyType);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
	mav.addObject("contentName","/view/jsp/properties/product/createProduct1.jsp");
	
	return mav;
}

public org.springframework.web.servlet.ModelAndView
	createProduct(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = null;
	try {
		out = response.getWriter();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// 회원번호
	HttpSession session = request.getSession(true);
	member.Member objMember ;
	long privilegeId = -1;
	if (session.getAttribute("user")!= null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} else {
		out.println("<script language=javascript>");
		out.println("alert('로그인 후 등록이 가능합니다');");
		out.println("location.href = 'members.do?action=authenticateMemberPage'");
		out.println("</script>");
		return null;
	}
		
	if(privilegeId != 2){
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'properties.do?action=listProduct'");
		out.println("</script>");
		return null;
	}
	
	 Enumeration<String> headernames = request.getHeaderNames();
	 String headerName = "";
	 String headerVal = "";
	 while(headernames.hasMoreElements()){
		 
		 headerName = headernames.nextElement();
		 headerVal = request.getHeader(headerName);
		 
		 System.out.println(headerName + "/" + headerVal );
	 }
	
	try {
		request.setCharacterEncoding("8859_1");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	setDefaultViewSet(mav, request);
	
	// 형명, Lot번호
	String[] name = request.getParameterValues("name[]");
	//String[] manuf_lot = request.getParameterValues("manuf_lot[]"); //Lot번호

	String arr = "";
	String arr2 = "";
	
	String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
	String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");

	int company_id = Function.nullChk(request.getParameter("company_id"), 0);// 업체정보
	String item_id = Function.nullChk(request.getParameter("item_id"), "");// 품목정보
	String item_grade = Function.nullChk(request.getParameter("item_grade"), "");// 품목정보 등급
	String cob_flag_code = Function.nullChk(request.getParameter("codeType"), "");// 품목허가번호 - 업체정보
	String meddev_item_no = Function.nullChk(request.getParameter("meddev_item_no"), "");// 품목허가번호 - 허가번호
	String manuf_import_name = Function.nullChk(request.getParameter("manuf_import_name"), "");// 제조원(수입의 경우)
	int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
	
	/*try {
		//propertyValue = new String (propertyValue.getBytes("8859_1"), "utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

	SimpleProduct insertTarget = new SimpleProduct();
	
	insertTarget.setDefault_in_use_id(isInUse);
	insertTarget.setMeddev_entp_seq(company_id);
	insertTarget.setMea_class_no(item_id);
	insertTarget.setGrade(item_grade);
	insertTarget.setCob_flag_code(cob_flag_code);
	insertTarget.setMeddev_item_no(meddev_item_no);
	insertTarget.setManuf_import_name(manuf_import_name);
	insertTarget.setPropertyValue(propertyValue);
	
	
	String dateString  = activeFrom.replaceAll("/",":") + " 00:00:00";
	
	SimpleProduct found = propertiesDAO.create(insertTarget);
	
	History childObject = historyServiceProvider.buildOrEmpty(request, SimpleProductHistory.class);
	
	//이력
	if(childObject!=null){
		propertiesDAO.create(childObject);
		found.getProductHistory().add(childObject);
		propertiesDAO.update(found);
	}
	
	//형명, Lot번호
	
			SimpleProductType insertTarget2 =new SimpleProductType();
			SimpleJuncProductType insertTarget3 =new SimpleJuncProductType();
			//SimpleProductLot insertTarget4 =new SimpleProductLot();
			//SimpleJuncProductLot insertTarget5 =new SimpleJuncProductLot();
			
			for (int i =0; i < name.length; i++ ){
				arr =  name[i];
				System.out.println("name: "+ arr);
				insertTarget2.setMeddev_item_seq(found.getMeddev_item_seq());
				insertTarget2.setType_name(arr);
				propertiesDAO.create(insertTarget2);
				//found.getProduct_type().add(insertTarget2);
				
				insertTarget3.setType_id(insertTarget2.getId());
				insertTarget3.setMeddev_item_seq(found.getMeddev_item_seq());
				propertiesDAO.create(insertTarget3);
			}
			
			/*for (int i =0; i < manuf_lot.length; i++ ){
				arr2 =  manuf_lot[i];
				System.out.println("manuf_lot: "+ arr2);
			
				insertTarget4.setMeddev_item_seq(found.getMeddev_item_seq());
				insertTarget4.setLot_no(arr2);	
				propertiesDAO.create(insertTarget4);
				//found.getProduct_lot().add(insertTarget4);
				
				insertTarget5.setLot_id(insertTarget4.getId());
				insertTarget5.setMeddev_item_seq(found.getMeddev_item_seq());
				propertiesDAO.create(insertTarget5);
			}*/
	
	/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
	mav.addObject("message","create succeess");*/
	mav.addObject("returnUrl","properties.do?action=listProduct");
	mav.setViewName("properties.do?action=listProduct");
	
	return mav;
}

/**
 * 
 * @author 김혜민
 * @param request
 * @param response
 * @return
 * 2013. 11. 6. 2013
 * @description 援��肄붾뱶 �곸꽭蹂닿린
 */
public org.springframework.web.servlet.ModelAndView
	readProduct(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	setDefaultViewSet(mav, request);
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
	//Level1 
	//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
	//
	List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	
	List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);

	
	setDefaultViewSet(mav, request);
	
	mav.addObject("isInUse",isInUse);
	mav.addObject("itemCodeTypes",itemCodeTypes);
	mav.addObject("traceabilityList",traceabilityList);
	mav.addObject("historyType",historyType);
	IDValuePair found = propertiesDAO.readProduct(SimpleProduct.class, articleId);
	mav.addObject("article", found);
	mav.addObject("titleImg", "view/style/images/title/sub03_06.jpg");
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
	mav.addObject("contentName","/view/jsp/properties/product/readProduct1.jsp");
	
	return mav;
}

/**
 * 
 * @author 김혜민
 * @param request
 * @param response
 * @return
 * 2013. 11. 6. 2013
 * @description 援��肄붾뱶 �섏젙 硫붿냼�� Database�먯꽌 吏�슫��
 */
public org.springframework.web.servlet.ModelAndView
	updateProduct(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = null;
	try {
		out = response.getWriter();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// 회원번호
	HttpSession session = request.getSession(true);
	member.Member objMember ;
	long privilegeId = -1;
	if (session.getAttribute("user")!= null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} else {
		out.println("<script language=javascript>");
		out.println("alert('로그인 후 등록이 가능합니다');");
		out.println("location.href = 'members.do?action=authenticateMemberPage'");
		out.println("</script>");
		return null;
	}
		
	if(privilegeId != 2){
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'properties.do?action=listProduct'");
		out.println("</script>");
		return null;
	}
	
	setDefaultViewSet(mav, request);
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
	
	SimpleProduct targetObject = new SimpleProduct();
	
	// 형명, Lot번호
	String[] name = request.getParameterValues("name[]");
	//String[] manuf_lot = request.getParameterValues("manuf_lot[]"); //Lot번호
	
	String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
	int company_id = Function.nullChk(request.getParameter("company_id"), 0);// 업체정보
	String item_id = Function.nullChk(request.getParameter("item_id"), "");// 품목정보
	String item_grade = Function.nullChk(request.getParameter("item_grade"), "");// 품목정보 등급
	String cob_flag_code = Function.nullChk(request.getParameter("codeType"), "");// 품목허가번호 - 업체정보
	String meddev_item_no = Function.nullChk(request.getParameter("meddev_item_no"), "");// 품목허가번호 - 허가번호
	String manuf_import_name = Function.nullChk(request.getParameter("manuf_import_name"), "");// 제조원(수입의 경우)
	int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);//사용여부
	
	SimpleProduct found = propertiesDAO.readProduct(SimpleProduct.class, articleId);
	found.setIsInUse(isInUse);
	found.setMeddev_entp_seq(company_id);
	found.setMea_class_no(item_id);
	found.setGrade(item_grade);
	found.setCob_flag_code(cob_flag_code);
	found.setMeddev_item_no(meddev_item_no);
	found.setManuf_import_name(manuf_import_name);
	found.setPropertyValue(propertyValue);	
	found.setDefault_in_use_id(isInUse);
	
	History childObject = historyServiceProvider.buildOrEmpty(request, SimpleProductHistory.class);
	 
	if(childObject!=null){
		propertiesDAO.create(childObject);
		found.getProductHistory().add(childObject);
	}
	
	//형명, Lot번호
	
		SimpleProductType insertTarget2 =new SimpleProductType();
		SimpleJuncProductType insertTarget3 =new SimpleJuncProductType();
		//SimpleProductLot insertTarget4 =new SimpleProductLot();
		//SimpleJuncProductLot insertTarget5 =new SimpleJuncProductLot();
		
		
		String arr ="";
		String arr2 ="";
		
		for (int i =0; i < name.length; i++ ){
			arr =  name[i];
			System.out.println("name: "+ arr);
			insertTarget2.setMeddev_item_seq(found.getMeddev_item_seq());
			insertTarget2.setType_name(arr);
			propertiesDAO.create(insertTarget2);
			//found.getProduct_type().add(insertTarget2);
			
			insertTarget3.setType_id(insertTarget2.getId());
			insertTarget3.setMeddev_item_seq(found.getMeddev_item_seq());
			propertiesDAO.create(insertTarget3);
		}
		
		
		/*for (int i =0; i < manuf_lot.length; i++ ){
			arr2 =  manuf_lot[i];
			System.out.println("manuf_lot: "+ arr2);
		
			insertTarget4.setMeddev_item_seq(found.getMeddev_item_seq());
			insertTarget4.setLot_no(arr2);	
			propertiesDAO.create(insertTarget4);
			//found.getProduct_lot().add(insertTarget4);
			
			insertTarget5.setLot_id(insertTarget4.getId());
			insertTarget5.setMeddev_item_seq(found.getMeddev_item_seq());
			propertiesDAO.create(insertTarget5);
		}*/
		
	SimpleProduct updated = propertiesDAO.update(found);
	mav.setViewName("properties.do?action=readProduct&articleId="+articleId);
	return mav;
}
/**
 * 
 * @author 김혜민
 * @param request
 * @param response
 * @return
 * 2013. 11. 6. 2013
 * @description 援��肄붾뱶 �섏젙 �섏씠吏�줈 蹂대궡��硫붿냼��
 */
public org.springframework.web.servlet.ModelAndView
	updateProductPage(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	setDefaultViewSet(mav, request);
	
	String titleImg = "view/style/images/title/sub03_06.jpg";
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
	
	IDValuePair found = propertiesDAO.readProduct(SimpleProduct.class, articleId);
	
	
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
	//업종코드
	List<SimpleProductCodeType> codeType =  propertiesDAO.list(SimpleProductCodeType.class);
	
	setDefaultViewSet(mav, request);
	
	mav.addObject("codeType",codeType);
	mav.addObject("isInUse",isInUse);
	mav.addObject("historyType",historyType);
	mav.addObject("article", found);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
	mav.addObject("contentName","/view/jsp/properties/product/updateProduct1.jsp");
	
	return mav;
}

/**
 * 
 * @author 김혜민
 * @param request
 * @param response
 * @return
 * 2013. 11. 6. 2013
 * @description	
 */
public org.springframework.web.servlet.ModelAndView
	deleteProduct(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	System.out.println("Deleting Product ");
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = null;
	try {
		out = response.getWriter();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// 회원번호
	HttpSession session = request.getSession(true);
	member.Member objMember ;
	long privilegeId = -1;
	if (session.getAttribute("user")!= null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} else {
		out.println("<script language=javascript>");
		out.println("alert('로그인 후 등록이 가능합니다');");
		out.println("location.href = 'members.do?action=authenticateMemberPage'");
		out.println("</script>");
		return null;
	}
		
	if(privilegeId != 2){
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'properties.do?action=listProduct'");
		out.println("</script>");
		return null;
	}
	
	setDefaultViewSet(mav, request);
	IDValuePair target = new SimpleProduct();
	
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
	
	target.setId(articleId);
	//IDValuePair found = 
	SimpleProduct read = propertiesDAO.readProduct(SimpleProduct.class, articleId);
	Set<History> history = read.getProductHistory();
	for(History h : history){
		propertiesDAO.delete(h);
	}
	propertiesDAO.delete( target);
	//mav.addObject("article", found);
	/*mav.addObject("message","job done");
	mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");*/
	mav.addObject("returnUrl","properties.do?action=listProduct");
	mav.setViewName("properties.do?action=listProduct");

	return mav;
}
	//업체 pop
	public org.springframework.web.servlet.ModelAndView 
	listCompanyPop(HttpServletRequest request, HttpServletResponse response) {
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	System.out.println("[SimplePropertiesDelegate].listCompanyPop().");
	
	String titleImg = "view/style/images/title/sub03_05.jpg";
	int pg = Function.nullChk(request.getParameter("pg"), 1);
	//setDefaultViewSet(mav, request);
	
	String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
	String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
	
	SimpleCompany sc = new SimpleCompany();
	
	if(!"".equals(searchColumn)){
		
		Method[] methods = SimpleCompany.class.getDeclaredMethods();
		for(Method m : methods){
			if(m.getName().equals(searchColumn)){
				try {
					//m.invoke(target, searchKeyword);
					m.invoke(sc, searchKeyword);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("searchColumn" + searchColumn);
		System.out.println("searchKeyword" + searchKeyword);
		System.out.println("methods" + methods);
	}
	
	List<SimpleCompany> dl = propertiesDAO.list(SimpleCompany.class,pg,10);
	long total = propertiesDAO.count(sc);
	//long total = propertiesDAO.count(new SimpleCompany());
	//long total = 100;
	long top = total - (pg-1)*10;
	Page page = new Page();
	// pageString = page.pageList((int)total,10,pg,"properties.do?action=listCompanyPop","");
	String pageString = page.pageList((int)total,10,pg,"properties.do?action=listCompanyPop&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn,"");
	
	List<SimpleCompany> set2 = propertiesDAO.list(sc,pg,10);
	dl =new ArrayList(set2);
	mav.addObject("top", top);
	mav.addObject("total", total);
	mav.addObject("list", dl);
	mav.addObject("pageString", pageString);
	mav.addObject("titleImg", titleImg);
	mav.setViewName("/view/jsp/properties/company/companyListPop1.jsp");
	
	return mav;
	}
	
	/**
	 * 
	 * @author 김혜민
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 11. 2013
	 * @description 제품코드의 이력 한건을 삭제 하는 method. AJAX request 대한 response 이다.
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteProductHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listProduct'");
			out.println("</script>");
			return null;
		}
		
		IDValuePair target = new SimpleProductHistory();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);

		target.setId(articleId);
		SimpleProductHistory history  = propertiesDAO.read(SimpleProductHistory.class, articleId);
		SimpleJuncProduct sjc = new SimpleJuncProduct();
		sjc.setHistory_id(new Long(articleId).intValue());
		propertiesDAO.delete( target);
		propertiesDAO.clearJunction("JUNC_PRODUCT_HISTORY","HISTORY_ID",articleId);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");

		return mav;
	}
	
	/**
	 * 
	 * @author 김혜민
	 * @param request
	 * @param response
	 * @return
	 * 2013. 12. 09.
	 * @description 제품코드의 형명을 한건 삭제
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteProductType(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		IDValuePair target = new SimpleProductType();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		target.setId(articleId);
		SimpleProductType type  = propertiesDAO.read(SimpleProductType.class, articleId);
		SimpleJuncProductType sjc = new SimpleJuncProductType();
		sjc.setType_id(new Long(articleId).intValue());
		propertiesDAO.delete( target);
		propertiesDAO.clearJunction("JUNC_MEB_ITEM_TYPE","TYPE_ID",articleId);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		
		return mav;
	}
	
	// 품목 pop
	public org.springframework.web.servlet.ModelAndView 
	listItemPop(HttpServletRequest request, HttpServletResponse response) {
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	System.out.println("[SimplePropertiesDelegate].listItemPop().");
	
	String titleImg = "view/style/images/title/sub03_01.jpg";
	int pg = Function.nullChk(request.getParameter("pg"), 1);
	String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
	String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
	int codeAgeId = Function.nullChk(request.getParameter("codeAge"), 0);
	Mea_class_no target = new Mea_class_no();
	SimpleItemCodeType codeAge = new SimpleItemCodeType();
	codeAge.setId(codeAgeId);
	Mea_class_no_view2 target2 = new Mea_class_no_view2();
	target2.setCode_age_id(codeAgeId);
	codeAge = propertiesDAO.read(SimpleItemCodeType.class, codeAgeId);
	target2.setCode_age(codeAge);
	if(!"".equals(searchColumn)){
		
		Method[] methods = Mea_class_no_view2.class.getDeclaredMethods();
		for(Method m : methods){
			if(m.getName().equals(searchColumn)){
				try {
					//m.invoke(target, searchKeyword);
					m.invoke(target2, searchKeyword);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	Page page = new Page();
	String pageString = "";
	setDefaultViewSet(mav, request);
	List<Mea_class_no_view2> dl = null;
 
	List<SimpleMeaClassNoLevel> meaClassNoLevel =  propertiesDAO.list(SimpleMeaClassNoLevel.class);
	
	long total = propertiesDAO.count(target2,0);
	long top = total - (pg-1)*10;
	pageString = page.pageList((int)total,10,pg,"properties.do?action=listItemPop&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn,"");
	mav.addObject("meaClassNoLevel",meaClassNoLevel);
	
	dl = propertiesDAO.listMeaClassNoView2(target2,pg,10, 1);
	List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
	mav.addObject("list", dl);
	mav.addObject("itemCodeTypes", itemCodeTypes);
	mav.addObject("top", top);
	mav.addObject("titleImg", titleImg);
	mav.addObject("total", total);
	mav.addObject("pageString", pageString);
	mav.setViewName("/view/jsp/properties/item/itemListPop1.jsp");
	
	return mav;
	
	}
	
	
	public org.springframework.web.servlet.ModelAndView
	listCompany(HttpServletRequest request, HttpServletResponse response) {
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	String titleImg = "view/style/images/title/sub03_05.jpg";
	int pg = Function.nullChk(request.getParameter("pg"), 1);
	setDefaultViewSet(mav, request);
	
	String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
	String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");

	SimpleCompany sc = new SimpleCompany();
	
	if(searchColumn.equals("setCob_flag_code")){
		if(searchKeyword.equals("제조업") || searchKeyword.equals("제조")){
			searchKeyword = "1";
		}else if(searchKeyword.equals("수입업") || searchKeyword.equals("수입")){
			searchKeyword = "2";
		}
	}
	
	if(!"".equals(searchColumn)){
		
		Method[] methods = SimpleCompany.class.getDeclaredMethods();
		
		for(Method m : methods){
			if(m.getName().equals(searchColumn)){
				try {
					//m.invoke(target, searchKeyword);
					m.invoke(sc, searchKeyword);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("searchColumn" + searchColumn);
		System.out.println("searchKeyword" + searchKeyword);
		System.out.println("methods" + methods);
	}
	
	List<SimpleCompany> dl = propertiesDAO.list(SimpleCompany.class,pg,10);
	long total = propertiesDAO.count(sc);
	//long total = propertiesDAO.count(new SimpleCompany());
	//long total = 100;
	long top = total - (pg-1)*10;
	Page page = new Page();
	//String pageString = page.pageList((int)total,10,pg,"properties.do?action=listCompany","");
	String pageString = page.pageList((int)total,10,pg,"properties.do?action=listCompany&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn,"");
	
	List<SimpleCompany> set2 = propertiesDAO.list(sc,pg,10);
	dl =new ArrayList(set2);
	mav.addObject("top", top);
	mav.addObject("total", total);
	mav.addObject("list", dl);
	mav.addObject("pageString", pageString);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
	mav.addObject("contentName","/view/jsp/properties/company/companyList1.jsp");
	
	return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
	createCompanyPage(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	String titleImg = "view/style/images/title/sub03_05.jpg";
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);	
	//상태
	List<SimpleCloseType> closeType =  propertiesDAO.list(SimpleCloseType.class);
	//업구분
	List<SimpleFlagType> flagType =  propertiesDAO.list(SimpleFlagType.class);
	
	
	setDefaultViewSet(mav, request);
	
	mav.addObject("isInUse",isInUse);
	mav.addObject("closeType",closeType);
	mav.addObject("flagType",flagType);
	mav.addObject("historyType",historyType);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
	mav.addObject("contentName","/view/jsp/properties/company/createCompany1.jsp");
	
	return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
	createCompany(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = null;
	try {
		out = response.getWriter();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// 회원번호
	HttpSession session = request.getSession(true);
	member.Member objMember ;
	long privilegeId = -1;
	if (session.getAttribute("user")!= null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} else {
		out.println("<script language=javascript>");
		out.println("alert('로그인 후 등록이 가능합니다');");
		out.println("location.href = 'members.do?action=authenticateMemberPage'");
		out.println("</script>");
		return null;
	}
		
	if(privilegeId != 2){
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'properties.do?action=listCompany'");
		out.println("</script>");
		return null;
	}
	
	 Enumeration<String> headernames = request.getHeaderNames();
	 String headerName = "";
	 String headerVal = "";
	 while(headernames.hasMoreElements()){
		 
		 headerName = headernames.nextElement();
		 headerVal = request.getHeader(headerName);
		 
		 System.out.println(headerName + "/" + headerVal );
	 }
	
	try {
		request.setCharacterEncoding("8859_1");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	setDefaultViewSet(mav, request);
	
	String entp_name = Function.nullChk(request.getParameter("entp_name"), ""); //업체명
	String meddev_entp_no = Function.nullChk(request.getParameter("meddev_entp_no"), ""); //업허가번호
	String cob_flag_code = Function.nullChk(request.getParameter("flagType"), ""); //업구분
	String shutdown_close_reopen_code = Function.nullChk(request.getParameter("closeType"), ""); //상태
	String permit_date = Function.nullChk(request.getParameter("permit_date"), ""); // 업허가일자
	String entp_zip_no = Function.nullChk(request.getParameter("entp_zip_no"), ""); // 우편번호
	String entp_addr1 = Function.nullChk(request.getParameter("entp_addr1"), ""); // 주소1
	String entp_addr2 = Function.nullChk(request.getParameter("entp_addr2"), ""); // 주소2
	String propertyValue = Function.nullChk(request.getParameter("propertyValue"), ""); 
	int isInUse = Function.nullChk(request.getParameter("isInUse"), 0); //사용여부
	

	SimpleCompany insertTarget = new SimpleCompany();
	
	// Date format
	if(permit_date!=""){
		try {
		String dateString = permit_date.replaceAll("/", "-");
		String date1= dateString;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date af = new Date();
			af = sdf.parse(date1);
			insertTarget.setPermit_date(af);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
	}else {insertTarget.setPermit_date(new Date());}
	
	insertTarget.setEntp_name(entp_name);
	insertTarget.setMeddev_entp_no(meddev_entp_no);
	insertTarget.setCob_flag_code(cob_flag_code);
	insertTarget.setShutdown_close_reopen_code(shutdown_close_reopen_code);
	insertTarget.setEntp_zip_no(entp_zip_no);
	insertTarget.setEntp_addr1(entp_addr1);
	insertTarget.setEntp_addr2(entp_addr2);
	insertTarget.setDefault_in_use_id(isInUse);
	insertTarget.setPropertyValue(propertyValue);
	insertTarget.setRegist_ts(new Date());
	
	SimpleCompany found = propertiesDAO.create(insertTarget);
	
	History childObject = historyServiceProvider.buildOrEmpty(request, SimpleCompanyHistory.class);
	 
	if(childObject!=null){
		propertiesDAO.create(childObject);
		found.getCompanyHistory().add(childObject);
		propertiesDAO.update(found);
	}
	
	/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
	mav.addObject("message","create succeess");*/
	mav.addObject("returnUrl","properties.do?action=listCompany");
	mav.setViewName("properties.do?action=listCompany");

	return mav;
}
	
	/**
	* 
	* @author 김혜민
	* @param request
	* @param response
	* @return
	* 2013. 11. 6. 2013
	* @description 援��肄붾뱶 �곸꽭蹂닿린
	*/
	public org.springframework.web.servlet.ModelAndView
	readCompany(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	setDefaultViewSet(mav, request);
	String titleImg = "view/style/images/title/sub03_05.jpg";
	int articleId = Function.nullChk(request.getParameter("articleId"), -1);
	//Level1 
	//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
	//
	List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	
	List<SimpleCloseType> closeType =  propertiesDAO.list(SimpleCloseType.class);
	//업구분
	List<SimpleFlagType> flagType =  propertiesDAO.list(SimpleFlagType.class);
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
	
	
	setDefaultViewSet(mav, request);
	
	//mav.addObject("level1List",dl);
	mav.addObject("isInUse",isInUse);
	mav.addObject("closeType",closeType);
	mav.addObject("flagType",flagType);
	mav.addObject("traceabilityList",traceabilityList);
	mav.addObject("historyType",historyType);
	IDValuePair found = propertiesDAO.readCompany(SimpleCompany.class, articleId);
	mav.addObject("article", found);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
	mav.addObject("contentName","/view/jsp/properties/company/readCompany1.jsp");
	
	return mav;
	}
	
	/**
	* 
	* @author 김혜민
	* @param request
	* @param response
	* @return
	* 2013. 11. 6. 2013
	* @description 援��肄붾뱶 �섏젙 硫붿냼�� Database�먯꽌 吏�슫��
	*/
	public org.springframework.web.servlet.ModelAndView
	updateCompany(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = null;
	try {
		out = response.getWriter();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// 회원번호
	HttpSession session = request.getSession(true);
	member.Member objMember ;
	long privilegeId = -1;
	if (session.getAttribute("user")!= null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} else {
		out.println("<script language=javascript>");
		out.println("alert('로그인 후 등록이 가능합니다');");
		out.println("location.href = 'members.do?action=authenticateMemberPage'");
		out.println("</script>");
		return null;
	}
		
	if(privilegeId != 2){
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'properties.do?action=listCompany'");
		out.println("</script>");
		return null;
	}
	
	setDefaultViewSet(mav, request);
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
	
	SimpleCompany targetObject = new SimpleCompany();
	
	String entp_name = Function.nullChk(request.getParameter("entp_name"), ""); //업체명
	String meddev_entp_no = Function.nullChk(request.getParameter("meddev_entp_no"), ""); //업허가번호
	String cob_flag_code = Function.nullChk(request.getParameter("flagType"), ""); //업구분
	String shutdown_close_reopen_code = Function.nullChk(request.getParameter("closeType"), ""); //상태
	String permit_date = Function.nullChk(request.getParameter("permit_date"), ""); // 업허가일자
	String entp_zip_no = Function.nullChk(request.getParameter("entp_zip_no"), ""); // 우편번호
	String entp_addr1 = Function.nullChk(request.getParameter("entp_addr1"), ""); // 주소1
	String entp_addr2 = Function.nullChk(request.getParameter("entp_addr2"), ""); // 주소2
	String propertyValue = Function.nullChk(request.getParameter("propertyValue"), ""); // 사용여부
	int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		

	SimpleCompany found = propertiesDAO.readCompany(SimpleCompany.class, articleId);
	
		// Date format
		if(permit_date!=""){
			try {
			String dateString = permit_date.replaceAll("/", "-");
			String date1= dateString;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date af = new Date();
				af = sdf.parse(date1);
				found.setPermit_date(af);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
			}
		}else {found.setPermit_date(new Date());}
	
	found.setPropertyValue(propertyValue);
	found.setIsInUse(isInUse);
	found.setEntp_name(entp_name);
	found.setMeddev_entp_no(meddev_entp_no);
	found.setCob_flag_code(cob_flag_code);
	found.setShutdown_close_reopen_code(shutdown_close_reopen_code);
	found.setEntp_zip_no(entp_zip_no);
	found.setEntp_addr1(entp_addr1);
	found.setEntp_addr2(entp_addr2);
	found.setDefault_in_use_id(isInUse);
	found.setRegist_ts(new Date());
	
	History childObject = historyServiceProvider.buildOrEmpty(request, SimpleCompanyHistory.class);
	 
	if(childObject!=null){
		propertiesDAO.create(childObject);
		found.getCompanyHistory().add(childObject);
	}
	SimpleCompany updated = propertiesDAO.update(found);
	mav.setViewName("properties.do?action=readCompany&articleId="+articleId);

	return mav;
}
	
	/**
	* 
	* @author 김혜민
	* @param request
	* @param response
	* @return
	* 2013. 11. 6. 2013
	* @description 援��肄붾뱶 �섏젙 �섏씠吏�줈 蹂대궡��硫붿냼��
	*/
	public org.springframework.web.servlet.ModelAndView
	updateCompanyPage(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	setDefaultViewSet(mav, request);
	
	String titleImg = "view/style/images/title/sub03_05.jpg";
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
	
	IDValuePair found = propertiesDAO.readCompany(SimpleCompany.class, articleId);
	
	
	//
	List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
	//
	List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);	
	//상태
	List<SimpleCloseType> closeType =  propertiesDAO.list(SimpleCloseType.class);
	//업구분
	List<SimpleFlagType> flagType =  propertiesDAO.list(SimpleFlagType.class);
	
	setDefaultViewSet(mav, request);
	
	mav.addObject("isInUse",isInUse);
	mav.addObject("closeType",closeType);
	mav.addObject("flagType",flagType);
	mav.addObject("historyType",historyType);
	mav.addObject("article", found);
	mav.addObject("titleImg", titleImg);
	mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
	mav.addObject("contentName","/view/jsp/properties/country/updateCountry1.jsp");
	
	
	setDefaultViewSet(mav, request);
	
	mav.addObject("isInUse",isInUse);
	mav.addObject("historyType",historyType);
	mav.addObject("article", found);
	mav.addObject("contentName","/view/jsp/properties/company/updateCompany1.jsp");
	
	return mav;
	}
	
	/**
	* 
	* @author 김혜민
	* @param request
	* @param response
	* @return
	* 2013. 11. 6. 2013
	* @description	援��肄붾뱶 ��젣 硫붿냼��
	*/
	public org.springframework.web.servlet.ModelAndView
	deleteCompany(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = null;
	try {
		out = response.getWriter();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	// 회원번호
	HttpSession session = request.getSession(true);
	member.Member objMember ;
	long privilegeId = -1;
	if (session.getAttribute("user")!= null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} else {
		out.println("<script language=javascript>");
		out.println("alert('로그인 후 등록이 가능합니다');");
		out.println("location.href = 'members.do?action=authenticateMemberPage'");
		out.println("</script>");
		return null;
	}
		
	if(privilegeId != 2){
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'properties.do?action=listCompany'");
		out.println("</script>");
		return null;
	}
	
	System.out.println("Deleting Company ");
	setDefaultViewSet(mav, request);
	IDValuePair target = new SimpleCompany();
	
	long articleId = Function.nullChk(request.getParameter("articleId"), -1);
			

	target.setId(articleId);
	//IDValuePair found = 
	SimpleCompany read = propertiesDAO.readCompany(SimpleCompany.class, articleId);
	Set<History> history = read.getCompanyHistory();
	for(History h : history){
		propertiesDAO.delete(h);
	}
	propertiesDAO.delete( target);
	//mav.addObject("article", found);
	/*mav.addObject("message","job done");
	mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");*/
	mav.addObject("returnUrl","properties.do?action=listCompany");
	mav.setViewName("properties.do?action=listCompany");
	return mav;
}

	/**
	 * 
	 * @author 김혜민
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 11. 2013
	 * @description 업체코드의 이력 한건을 삭제 하는 method. AJAX request 대한 response 이다.
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteCompanyHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listCompany'");
			out.println("</script>");
			return null;
		}
		
		IDValuePair target = new SimpleCompanyHistory();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
	
		target.setId(articleId);
		SimpleCompanyHistory history  = propertiesDAO.read(SimpleCompanyHistory.class, articleId);
		SimpleJuncCompany sjc = new SimpleJuncCompany();
		sjc.setHistory_id(new Long(articleId).intValue());
		propertiesDAO.delete( target);
		propertiesDAO.clearJunction("JUNC_COMPANY_HISTORY","HISTORY_ID",articleId);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");	
	return mav;
}
	
	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 11. 2013
	 * @description 국가코드의 이력 한건을 삭제 하는 method. AJAX request 대한 response 이다.
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteCountryHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		IDValuePair target = new SimpleCountryHistory();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(privilegeId>1){
		target.setId(articleId);
		SimpleCountryHistory history  = propertiesDAO.read(SimpleCountryHistory.class, articleId);
		SimpleJuncCountry sjc = new SimpleJuncCountry();
		sjc.setHistory_id(new Long(articleId).intValue());
		propertiesDAO.clearJunction("JUNC_COUNTRY_HISTORY","HISTORY_ID",articleId);
		propertiesDAO.delete( target);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
	}else {
		out.println("<script language=javascript>");
		out.println("alert('권한이 없습니다.');");
		out.println("location.href = 'safetyItem.do?action=listItem'");
		out.println("</script>");
		return null;
	}	
		return mav;
	}

	//***************부작용코드 - 환자문제 코드 - START*****************//
	public org.springframework.web.servlet.ModelAndView
		listPatientCondition(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		

		String titleImg = "view/style/images/title/sub03_02.jpg";
	
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
		int depthLevel = Function.nullChk(request.getParameter("patientConditionLevel"), 0);
		System.out.println("depthLevel =: " + depthLevel);
		SimplePatientCondition sp = new SimplePatientCondition();
		sp.setDepthLevel(depthLevel);
		
		// 한글 검색어 인코딩 깨짐방지
		//searchKeyword = new String(searchKeyword.getBytes("8859_1"),"UTF-8");
		
		if(!"".equals(searchColumn)){
			
			Method[] methods = SimplePatientCondition.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(sp, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("searchColumn" + searchColumn);
			System.out.println("searchKeyword" + searchKeyword);
			System.out.println("methods" + methods);
		}
		
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		Page page = new Page();
		setDefaultViewSet(mav, request);
		
		List<SimplePatientConditionLevel> patientConditionLevel =  propertiesDAO.list(SimplePatientConditionLevel.class);
		List<SimplePatientCondition> dl = propertiesDAO.list(SimplePatientCondition.class,pg,10);
		//long total = propertiesDAO.count(new SimplePatientCondition());
		long total = propertiesDAO.count(sp);
		long top = total - (pg-1)*10;
		/*String pageString = page.pageList((int)total,10,pg,"properties.do?action=listPatientCondition","");*/
		String pageString = page.pageList((int)total,10,pg,"properties.do?action=listPatientCondition&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn+"&patientConditionLevel="+depthLevel,"");
		
		List<SimplePatientCondition> set2 = propertiesDAO.list(sp,pg,10);
		if(set2!=null){
			dl =new ArrayList(set2);
		}
		
		
		mav.addObject("patientConditionLevel",patientConditionLevel);
		mav.addObject("list", dl);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_02.jpg");
		mav.addObject("contentName","/view/jsp/properties/patientCondition/patientConditionList1.jsp");
		
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		createPatientConditionPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		String titleImg = "view/style/images/title/sub03_02.jpg";
		long level = Function.nullChk(request.getParameter("level"), -1);
		
		if(level< 1){
			mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
			mav.addObject("message","오류 발생. 환자문제 코드 작성시에 필요한 level 정보가 올바르지않습니다.");
			return mav;
		}
		
		if(level==1){
			
		}else if(level==2){
			List<SimplePatientCondition> patientCondition_list = propertiesDAO.listPatientCondition(null, 1);
			mav.addObject("level1List",patientCondition_list);
		}else if(level==3){
			List<SimplePatientCondition> patientCondition_list = propertiesDAO.byLevel1PatientCondition(3);
			mav.addObject("level1List",patientCondition_list);
		}else if(level==4){
			List<SimplePatientCondition> patientCondition_list = propertiesDAO.byLevel1PatientCondition(4);
			mav.addObject("level1List",patientCondition_list);
		}
		else if(level==5){
			List<SimplePatientCondition> patientCondition_list = propertiesDAO.byLevel1PatientCondition(5);
			mav.addObject("level1List",patientCondition_list);
		}
		else if(level==6){
			List<SimplePatientCondition> patientCondition_list = propertiesDAO.byLevel1PatientCondition(6);
			mav.addObject("level1List",patientCondition_list);
		}
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("isInUse",isInUse);
		mav.addObject("historyType",historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_02.jpg");
		mav.addObject("contentName","/view/jsp/properties/patientCondition/createPatientCondition1.jsp");
		
		return mav;
	}

	public org.springframework.web.servlet.ModelAndView
		createPatientCondition(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listPatientCondition'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		
		String parentItem = Function.nullChk(request.getParameter("parentItem"),"");
		String parentItem2 = Function.nullChk(request.getParameter("parentItem2"),"");
		String parentItem3 = Function.nullChk(request.getParameter("parentItem3"),"");
		String parentItem4 = Function.nullChk(request.getParameter("parentItem4"),"");
		String parentItem5 = Function.nullChk(request.getParameter("parentItem5"),"");
		int level = Function.nullChk(request.getParameter("level"), 0);
		int articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"),"");
		String fdaSourcePtKor = Function.nullChk(request.getParameter("fdaSourcePtKor"), "");
		String name = Function.nullChk(request.getParameter("name"), "");
		String fdaSourceDefinitionKor = Function.nullChk(request.getParameter("fdaSourceDefinitionKor"), "");
		String fdaSourceDefinition = Function.nullChk(request.getParameter("fdaSourceDefinition"), "");
		String nciCode = Function.nullChk(request.getParameter("nciCode"), "");
		String ncitDefinitionKor = Function.nullChk(request.getParameter("ncitDefinitionKor"), "");
		String ncitDefinition = Function.nullChk(request.getParameter("ncitDefinition"), "");
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String historyManager = Function.nullChk(request.getParameter("history_manager"), "");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		//String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("history_description"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		
		//System.out.println("createPatientCondition.propertyValue : " + propertyValue);
		
		SimplePatientCondition insertTarget = new SimplePatientCondition();
		
		if (level == 2) {
			insertTarget.setParentId(Integer.parseInt(parentItem));
		}
		else if (level == 3) {
			insertTarget.setParentId(Integer.parseInt(parentItem2));
		}
		else if (level == 4) {
			insertTarget.setParentId(Integer.parseInt(parentItem3));
		}
		else if (level == 5) {
			insertTarget.setParentId(Integer.parseInt(parentItem4));
		}
		else if (level == 6) {
			insertTarget.setParentId(Integer.parseInt(parentItem5));
		}
		insertTarget.setDepthLevel(level);
		insertTarget.setFdaCode(fdaCode);
		insertTarget.setFdaSourcePtKor(fdaSourcePtKor);
		insertTarget.setName(name);
		insertTarget.setFdaSourceDefinitionKor(fdaSourceDefinitionKor);
		insertTarget.setFdaSourceDefinition(fdaSourceDefinition);
		insertTarget.setNciCode(nciCode);
		insertTarget.setNcitDefinitionKor(ncitDefinitionKor);
		insertTarget.setNcitDefinition(ncitDefinition);
		
		insertTarget.setIsInUse(isInUse);
		//insertTarget.setPropertyValue(propertyValue);
		
		//String dateString  = activeFrom.replaceAll("/",":") + " 00:00:00";
		//System.out.println("lastModified : " + dateString);
		//insertTarget.setLastModified(new Date());
		//insertTarget.setActiveFrom(new Date());
		
		SimplePatientCondition found = propertiesDAO.create(insertTarget);
		SimplePatientCondition next = propertiesDAO.read(SimplePatientCondition.class, found.getId());
		
		if (next.getDepthLevel() == 1) {
			found.setParentId( Integer.parseInt( Long.toString(next.getId())));
			found.getParentId();
			propertiesDAO.update(found);
		}
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimplePatientConditionHistory.class);
		 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getPatientConditionHistory().add(childObject);
			
			
			propertiesDAO.update(found);
		}
		
		
		
		
		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.addObject("message","create succeess");*/
		mav.addObject("returnUrl","properties.do?action=listPatientCondition");
		mav.setViewName("properties.do?action=listPatientCondition");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView
		readPatientCondition(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_02.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		//Level1 
		//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
		
		//List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		
		setDefaultViewSet(mav, request);
		
		//mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		IDValuePair found = propertiesDAO.read(SimplePatientCondition.class, articleId);
		
		SimplePatientCondition found2 = propertiesDAO.read(SimplePatientCondition.class, articleId);
		SimplePatientCondition foundParent2 = propertiesDAO.getPatientConditionParent(found2.getParentId());//get parent
		
		if (found2.getDepthLevel() == 3) {
			SimplePatientCondition found3 = propertiesDAO.read(SimplePatientCondition.class, foundParent2.getId());
			SimplePatientCondition foundParent3 = propertiesDAO.getPatientConditionParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
		}
		else if (found2.getDepthLevel() == 4) {
			SimplePatientCondition found3 = propertiesDAO.read(SimplePatientCondition.class, foundParent2.getId());
			SimplePatientCondition foundParent3 = propertiesDAO.getPatientConditionParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimplePatientCondition found4 = propertiesDAO.read(SimplePatientCondition.class, foundParent3.getId());
			SimplePatientCondition foundParent4 = propertiesDAO.getPatientConditionParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
		}
		else if (found2.getDepthLevel() == 5) {
			SimplePatientCondition found3 = propertiesDAO.read(SimplePatientCondition.class, foundParent2.getId());
			SimplePatientCondition foundParent3 = propertiesDAO.getPatientConditionParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimplePatientCondition found4 = propertiesDAO.read(SimplePatientCondition.class, foundParent3.getId());
			SimplePatientCondition foundParent4 = propertiesDAO.getPatientConditionParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
			
			SimplePatientCondition found5 = propertiesDAO.read(SimplePatientCondition.class, foundParent4.getId());
			SimplePatientCondition foundParent5 = propertiesDAO.getPatientConditionParent(found5.getParentId());//get parent
			mav.addObject("parent5", foundParent5);
		}
		else if (found2.getDepthLevel() == 6) {
			SimplePatientCondition found3 = propertiesDAO.read(SimplePatientCondition.class, foundParent2.getId());
			SimplePatientCondition foundParent3 = propertiesDAO.getPatientConditionParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimplePatientCondition found4 = propertiesDAO.read(SimplePatientCondition.class, foundParent3.getId());
			SimplePatientCondition foundParent4 = propertiesDAO.getPatientConditionParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
			
			SimplePatientCondition found5 = propertiesDAO.read(SimplePatientCondition.class, foundParent4.getId());
			SimplePatientCondition foundParent5 = propertiesDAO.getPatientConditionParent(found5.getParentId());//get parent
			mav.addObject("parent5", foundParent5);
			
			SimplePatientCondition found6 = propertiesDAO.read(SimplePatientCondition.class, foundParent5.getId());
			SimplePatientCondition foundParent6 = propertiesDAO.getPatientConditionParent(found6.getParentId());//get parent
			mav.addObject("parent6", foundParent6);
		}
		mav.addObject("article", found);
		mav.addObject("parent2", foundParent2);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_02.jpg");
		mav.addObject("contentName","/view/jsp/properties/patientCondition/readPatientCondition1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		updatePatientConditionPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listPatientCondition'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		
		String titleImg = "view/style/images/title/sub03_02.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		IDValuePair found = propertiesDAO.read(SimplePatientCondition.class, articleId);
		
		
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("isInUse",isInUse);
		mav.addObject("historyType",historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_02.jpg");
		mav.addObject("contentName","/view/jsp/properties/patientCondition/updatePatientCondition1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView
		updatePatientCondition(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listPatientCondition'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		SimplePatientCondition targetObject = new SimplePatientCondition();
		
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"),"");
		String fdaSourcePtKor = Function.nullChk(request.getParameter("fdaSourcePtKor"), "");
		String name = Function.nullChk(request.getParameter("name"), "");
		String fdaSourceDefinitionKor = Function.nullChk(request.getParameter("fdaSourceDefinitionKor"), "");
		String fdaSourceDefinition = Function.nullChk(request.getParameter("fdaSourceDefinition"), "");
		String nciCode = Function.nullChk(request.getParameter("nciCode"), "");
		String ncitDefinitionKor = Function.nullChk(request.getParameter("ncitDefinitionKor"), "");
		String ncitDefinition = Function.nullChk(request.getParameter("ncitDefinition"), "");
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
		
		System.out.println("name:=" + name);
		System.out.println("lastModified:=" + lastModified);
		System.out.println("activeFrom:=" + activeFrom);
		System.out.println("historyManager:=" + historyManager);
		System.out.println("historyType:=" + historyType);
		System.out.println("historyDescription:=" + historyDescription);
		
		
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		//String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		SimplePatientCondition found = propertiesDAO.read(SimplePatientCondition.class, articleId);
		//found.setPropertyValue(propertyValue);
		found.setIsInUse(isInUse);
		found.setFdaCode(fdaCode);
		found.setFdaSourcePtKor(fdaSourcePtKor);
		found.setName(name);
		found.setFdaSourceDefinitionKor(fdaSourceDefinitionKor);
		found.setFdaSourceDefinition(fdaSourceDefinition);
		found.setNciCode(nciCode);
		found.setNcitDefinitionKor(ncitDefinitionKor);
		found.setNcitDefinition(ncitDefinition);
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimplePatientConditionHistory.class);
	 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getPatientConditionHistory().add(childObject);
		}
		SimplePatientCondition updated = propertiesDAO.update(found);
		mav.setViewName("properties.do?action=readPatientCondition&articleId="+articleId);
		return mav;
	}
	
	/**
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description	
	 */
	public org.springframework.web.servlet.ModelAndView
		deletePatientCondition(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		System.out.println("Deleting patientCondition ");
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 삭제가 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listPatientCondition'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimplePatientCondition();
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		target.setId(articleId);
		//IDValuePair found = 
		SimplePatientCondition read = propertiesDAO.read(SimplePatientCondition.class, articleId);
		Set<History> history = read.getPatientConditionHistory();
		for(History h : history){
			propertiesDAO.clearJunction("JUNC_PATIENT_CONDITION_HISTORY","PATIENT_CONDITION_ID",articleId);
			propertiesDAO.delete(h);
		}
		propertiesDAO.delete( target);
		
		//mav.addObject("article", found);
		//mav.addObject("message","job done");
		mav.addObject("returnUrl","properties.do?action=listPatientCondition");
		//mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.setViewName("properties.do?action=listPatientCondition");
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description 환자문제코드의 이력 한건을 삭제 하는 method. AJAX request 대한 response 이다.
	 */
	public org.springframework.web.servlet.ModelAndView
		deletePatientConditionHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		IDValuePair target = new SimplePatientConditionHistory();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		target.setId(articleId);
		SimplePatientConditionHistory history  = propertiesDAO.read(SimplePatientConditionHistory.class, articleId);
		SimpleJuncPatientCondition sjc = new SimpleJuncPatientCondition();
		sjc.setHistory_id(new Long(articleId).intValue());
		propertiesDAO.clearJunction("JUNC_PATIENT_CONDITION_HISTORY","HISTORY_ID",articleId);
		propertiesDAO.delete( target);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		
		return mav;
	}
	
	public ModelAndView getChildPatientCondition(HttpServletRequest request, HttpServletResponse respnse){
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("getChildPatientCondition Receiving...");
		String parentItem;
		String parentItem2;
		String parentItem3;
		String parentItem4;
		int paramLevel;
		int paramStep;
		
		parentItem = Function.nullChk(request.getParameter("parentItem"));
		parentItem2 = Function.nullChk(request.getParameter("parentItem2"));
		parentItem3 = Function.nullChk(request.getParameter("parentItem3"));
		parentItem4 = Function.nullChk(request.getParameter("parentItem4"));
		paramLevel = Function.nullChk(request.getParameter("level"),0);
		paramStep = Function.nullChk(request.getParameter("step"),0);
		System.out.println("parentItem:=" + parentItem);
		System.out.println("parentItem2:=" + parentItem2);
		System.out.println("level:=" + paramLevel);
	
		/*if (parentItem != "") {
			List<SimplePatientCondition> list = propertiesDAO.listPatientCondition(Integer.parseInt(parentItem), 2);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
			mav.addObject("level2List", list);
			System.out.println("list :  " + list.size());
		}
		if (parentItem2 != "") {
			List<SimplePatientCondition> list2 = propertiesDAO.listPatientCondition(Integer.parseInt(parentItem2), 3);
			System.out.println("list2 :  " + list2.size());
			mav.addObject("level3List", list2);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
		}
		if (parentItem3 != "") {
			List<SimplePatientCondition> list3 = propertiesDAO.listPatientCondition(Integer.parseInt(parentItem3), 4);
			System.out.println("list3 :  " + list3.size());
			mav.addObject("level4List", list3);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
		}
		if (parentItem4 != "") {
			List<SimplePatientCondition> list4 = propertiesDAO.listPatientCondition(Integer.parseInt(parentItem4), 5);
			System.out.println("list4 :  " + list4.size());
			mav.addObject("level5List", list4);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level6.jsp");
		}*/
		if (paramLevel == 3) {
			switch(paramStep) 
			{
				case 2 :
					List<SimplePatientCondition> level2List = propertiesDAO.byLevelPatientConditionStep(3, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
			}
		}
		if (paramLevel == 4) {
			switch(paramStep) 
			{
				case 2 :
					List<SimplePatientCondition> level2List = propertiesDAO.byLevelPatientConditionStep(4, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimplePatientCondition> level3List = propertiesDAO.byLevelPatientConditionStep(4, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
			}
		}
		if (paramLevel == 5) {
			switch(paramStep) 
			{
				case 2 :
					List<SimplePatientCondition> level2List = propertiesDAO.byLevelPatientConditionStep(5, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimplePatientCondition> level3List = propertiesDAO.byLevelPatientConditionStep(5, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
				case 4 :
					List<SimplePatientCondition> level4List = propertiesDAO.byLevelPatientConditionStep(5, 4, Integer.parseInt(parentItem3));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
					mav.addObject("level4List", level4List);
					break;
			}
		}
		if (paramLevel == 6) {
			switch(paramStep) 
			{
				case 2 :
					List<SimplePatientCondition> level2List = propertiesDAO.byLevelPatientConditionStep(6, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimplePatientCondition> level3List = propertiesDAO.byLevelPatientConditionStep(6, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
				case 4 :
					List<SimplePatientCondition> level4List = propertiesDAO.byLevelPatientConditionStep(6, 4, Integer.parseInt(parentItem3));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
					mav.addObject("level4List", level4List);
					break;
				case 5 :
					List<SimplePatientCondition> level5List = propertiesDAO.byLevelPatientConditionStep(6, 5, Integer.parseInt(parentItem4));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level6.jsp");
					mav.addObject("level5List", level5List);
					break;
			}
		}
	
		return mav;
	}
	//***************부작용코드 - 환자문제 코드 - END*******************//
	
	//***************부작용코드 - 의료기기문제 코드 - START*****************//
	public org.springframework.web.servlet.ModelAndView
		listMedicalDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		String titleImg = "view/style/images/title/sub03_03.jpg";
		
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
		int depthLevel = Function.nullChk(request.getParameter("medicalDeviceLevel"), 0);
		System.out.println("depthLevel =: " + depthLevel);
		SimpleMedicalDeviceMalfunctionCode smd = new SimpleMedicalDeviceMalfunctionCode();
		smd.setDepthLevel(depthLevel);
		
		// 한글 검색어 인코딩 깨짐방지
		//searchKeyword = new String(searchKeyword.getBytes("8859_1"),"UTF-8");
		
		if(!"".equals(searchColumn)){
			
			Method[] methods = SimpleMedicalDeviceMalfunctionCode.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(smd, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("searchColumn" + searchColumn);
			System.out.println("searchKeyword" + searchKeyword);
			System.out.println("methods" + methods);
		}
		
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		Page page = new Page();
		setDefaultViewSet(mav, request);
		List<SimpleMedicalDeviceLevel> medicalDeviceLevel =  propertiesDAO.list(SimpleMedicalDeviceLevel.class);
		List<SimpleMedicalDeviceMalfunctionCode> dl = propertiesDAO.list(SimpleMedicalDeviceMalfunctionCode.class,pg,10);
		//long total = propertiesDAO.count(new SimpleMedicalDeviceMalfunctionCode());
		long total = propertiesDAO.count(smd);
		long top = total - (pg-1)*10;
		/*String pageString = page.pageList((int)total,10,pg,"properties.do?action=listMedicalDevice","");*/
		String pageString = page.pageList((int)total,10,pg,"properties.do?action=listMedicalDevice&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn+"&medicalDeviceLevel="+depthLevel,"");
		
		List<SimpleMedicalDeviceMalfunctionCode> set2 = propertiesDAO.list(smd,pg,10);
		dl =new ArrayList(set2);
		
		mav.addObject("medicalDeviceLevel",medicalDeviceLevel);
		mav.addObject("list", dl);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_03.jpg");
		mav.addObject("contentName","/view/jsp/properties/medicalDevice/medicalDeviceList1.jsp");
		
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		createMedicalDevicePage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
		String titleImg = "view/style/images/title/sub03_03.jpg";
		long level = Function.nullChk(request.getParameter("level"), -1);
		
		if(level< 1){
			mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
			mav.addObject("message","오류 발생. 의료기기문제 코드 작성시에 필요한 level 정보가 올바르지않습니다.");
			return mav;
		}
		
		if(level==1){
			
		}else if(level==2){
			List<SimpleMedicalDeviceMalfunctionCode> medicalDevice_list = propertiesDAO.listMedicalDevice(null, 1);
			mav.addObject("level1List",medicalDevice_list);
		}else if(level==3){
			List<SimpleMedicalDeviceMalfunctionCode> medicalDevice_list = propertiesDAO.byLevel1MedicalDevice(3);
			mav.addObject("level1List",medicalDevice_list);
		}else if(level==4){
			List<SimpleMedicalDeviceMalfunctionCode> medicalDevice_list = propertiesDAO.byLevel1MedicalDevice(4);
			mav.addObject("level1List",medicalDevice_list);
		}
		else if(level==5){
			List<SimpleMedicalDeviceMalfunctionCode> medicalDevice_list = propertiesDAO.byLevel1MedicalDevice(5);
			mav.addObject("level1List",medicalDevice_list);
		}
		else if(level==6){
			List<SimpleMedicalDeviceMalfunctionCode> medicalDevice_list = propertiesDAO.byLevel1MedicalDevice(6);
			mav.addObject("level1List",medicalDevice_list);
		}
		
		
		//
		
		//
		//List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		//List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
		//등급
		//List<SimpleItemGrade> itemGrades =  propertiesDAO.list(SimpleItemGrade.class);
		
		setDefaultViewSet(mav, request);
		
		//mav.addObject("itemGrades",itemGrades);
		//mav.addObject("itemCodeTypes",itemCodeTypes);
		mav.addObject("isInUse",isInUse);
		//mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_03.jpg");
		mav.addObject("contentName","/view/jsp/properties/medicalDevice/createMedicalDevice1.jsp");
		
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		createMedicalDevice(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listMedicalDevice'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		
		String parentItem = Function.nullChk(request.getParameter("parentItem"),"");
		String parentItem2 = Function.nullChk(request.getParameter("parentItem2"),"");
		String parentItem3 = Function.nullChk(request.getParameter("parentItem3"),"");
		String parentItem4 = Function.nullChk(request.getParameter("parentItem4"),"");
		String parentItem5 = Function.nullChk(request.getParameter("parentItem5"),"");
		int level = Function.nullChk(request.getParameter("level"), 0);
		
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"),"");
		String fdaSourcePtKor = Function.nullChk(request.getParameter("fdaSourcePtKor"), "");
		String name = Function.nullChk(request.getParameter("name"), "");
		String fdaSourceDefinitionKor = Function.nullChk(request.getParameter("fdaSourceDefinitionKor"), "");
		String fdaSourceDefinition = Function.nullChk(request.getParameter("fdaSourceDefinition"), "");
		String nciCode = Function.nullChk(request.getParameter("nciCode"), "");
		String ncitDefinitionKor = Function.nullChk(request.getParameter("ncitDefinitionKor"), "");
		String ncitDefinition = Function.nullChk(request.getParameter("ncitDefinition"), "");
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String historyManager = Function.nullChk(request.getParameter("history_manager"), "");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		//String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("history_description"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		
		//System.out.println("createMedicalDevice.propertyValue : " + propertyValue);
		
		SimpleMedicalDeviceMalfunctionCode insertTarget = new SimpleMedicalDeviceMalfunctionCode();
		
		if (level == 2) {
			insertTarget.setParentId(Integer.parseInt(parentItem));
		}
		else if (level == 3) {
			insertTarget.setParentId(Integer.parseInt(parentItem2));
		}
		else if (level == 4) {
			insertTarget.setParentId(Integer.parseInt(parentItem3));
		}
		else if (level == 5) {
			insertTarget.setParentId(Integer.parseInt(parentItem4));
		}
		else if (level == 6) {
			insertTarget.setParentId(Integer.parseInt(parentItem5));
		}
		insertTarget.setDepthLevel(level);
		insertTarget.setFdaCode(fdaCode);
		insertTarget.setFdaSourcePtKor(fdaSourcePtKor);
		insertTarget.setName(name);
		insertTarget.setFdaSourceDefinitionKor(fdaSourceDefinitionKor);
		insertTarget.setFdaSourceDefinition(fdaSourceDefinition);
		insertTarget.setNciCode(nciCode);
		insertTarget.setNcitDefinitionKor(ncitDefinitionKor);
		insertTarget.setNcitDefinition(ncitDefinition);
		
		insertTarget.setIsInUse(isInUse);
		//insertTarget.setPropertyValue(propertyValue);
		//String dateString  = activeFrom.replaceAll("/",":") + " 00:00:00";
		//System.out.println("lastModified : " + dateString);
		//insertTarget.setLastModified(new Date());
		//insertTarget.setActiveFrom(new Date());
		
		SimpleMedicalDeviceMalfunctionCode found = propertiesDAO.create(insertTarget);
		SimpleMedicalDeviceMalfunctionCode next = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, found.getId());
		if (level == 1) {
			found.setParentId((int) next.getId());
			propertiesDAO.update(found);
		}
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimpleMedicalDeviceMalfunctionHistory.class);
		 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getMedicalDeviceMalfunctionHistory().add(childObject);
			propertiesDAO.update(found);
		}
		
		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.addObject("message","create succeess");*/
		mav.addObject("returnUrl","properties.do?action=listMedicalDevice");
		mav.setViewName("properties.do?action=listMedicalDevice");
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		readMedicalDevice(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_03.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		//Level1 
		//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
		
		//List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		
		setDefaultViewSet(mav, request);
		
		//mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		IDValuePair found = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, articleId);
		
		SimpleMedicalDeviceMalfunctionCode found2 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, articleId);
		SimpleMedicalDeviceMalfunctionCode foundParent2 = propertiesDAO.getMedicalDeviceParent(found2.getParentId());//get parent
		
		if (found2.getDepthLevel() == 3) {
			SimpleMedicalDeviceMalfunctionCode found3 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent2.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent3 = propertiesDAO.getMedicalDeviceParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
		}
		else if (found2.getDepthLevel() == 4) {
			SimpleMedicalDeviceMalfunctionCode found3 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent2.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent3 = propertiesDAO.getMedicalDeviceParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimpleMedicalDeviceMalfunctionCode found4 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent3.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent4 = propertiesDAO.getMedicalDeviceParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
		}
		else if (found2.getDepthLevel() == 5) {
			SimpleMedicalDeviceMalfunctionCode found3 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent2.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent3 = propertiesDAO.getMedicalDeviceParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimpleMedicalDeviceMalfunctionCode found4 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent3.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent4 = propertiesDAO.getMedicalDeviceParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
			
			SimpleMedicalDeviceMalfunctionCode found5 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent4.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent5 = propertiesDAO.getMedicalDeviceParent(found5.getParentId());//get parent
			mav.addObject("parent5", foundParent5);
		}
		else if (found2.getDepthLevel() == 6) {
			SimpleMedicalDeviceMalfunctionCode found3 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent2.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent3 = propertiesDAO.getMedicalDeviceParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimpleMedicalDeviceMalfunctionCode found4 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent3.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent4 = propertiesDAO.getMedicalDeviceParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
			
			SimpleMedicalDeviceMalfunctionCode found5 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent4.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent5 = propertiesDAO.getMedicalDeviceParent(found5.getParentId());//get parent
			mav.addObject("parent5", foundParent5);
			
			SimpleMedicalDeviceMalfunctionCode found6 = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, foundParent5.getId());
			SimpleMedicalDeviceMalfunctionCode foundParent6 = propertiesDAO.getMedicalDeviceParent(found6.getParentId());//get parent
			mav.addObject("parent6", foundParent6);
		}
		
		mav.addObject("article", found);
		mav.addObject("parent2", foundParent2);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_03.jpg");
		mav.addObject("contentName","/view/jsp/properties/medicalDevice/readMedicalDevice1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView
		updateMedicalDevicePage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listMedicalDevice'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_03.jpg";
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		IDValuePair found = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, articleId);
		
		
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("isInUse",isInUse);
		mav.addObject("historyType",historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_03.jpg");
		mav.addObject("contentName","/view/jsp/properties/medicalDevice/updateMedicalDevice1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView
		updateMedicalDevice(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listMedicalDevice'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		SimpleMedicalDeviceMalfunctionCode targetObject = new SimpleMedicalDeviceMalfunctionCode();
		
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"),"");
		String fdaSourcePtKor = Function.nullChk(request.getParameter("fdaSourcePtKor"), "");
		String name = Function.nullChk(request.getParameter("name"), "");
		String fdaSourceDefinitionKor = Function.nullChk(request.getParameter("fdaSourceDefinitionKor"), "");
		String fdaSourceDefinition = Function.nullChk(request.getParameter("fdaSourceDefinition"), "");
		String nciCode = Function.nullChk(request.getParameter("nciCode"), "");
		String ncitDefinitionKor = Function.nullChk(request.getParameter("ncitDefinitionKor"), "");
		String ncitDefinition = Function.nullChk(request.getParameter("ncitDefinition"), "");
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
		
		System.out.println("lastModified:=" + lastModified);
		System.out.println("activeFrom:=" + activeFrom);
		System.out.println("historyManager:=" + historyManager);
		System.out.println("historyType:=" + historyType);
		System.out.println("historyDescription:=" + historyDescription);
		
		
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		SimpleMedicalDeviceMalfunctionCode found = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, articleId);
		found.setPropertyValue(propertyValue);
		found.setIsInUse(isInUse);
		found.setFdaCode(fdaCode);
		found.setFdaSourcePtKor(fdaSourcePtKor);
		found.setName(name);
		found.setFdaSourceDefinitionKor(fdaSourceDefinitionKor);
		found.setFdaSourceDefinition(fdaSourceDefinition);
		found.setNciCode(nciCode);
		found.setNcitDefinitionKor(ncitDefinitionKor);
		found.setNcitDefinition(ncitDefinition);
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimpleMedicalDeviceMalfunctionHistory.class);
	 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getMedicalDeviceMalfunctionHistory().add(childObject);
		}
		SimpleMedicalDeviceMalfunctionCode updated = propertiesDAO.update(found);
		mav.setViewName("properties.do?action=readMedicalDevice&articleId="+articleId);
		return mav;
	}
	
	/**
	 * 
	 * @author 
	 * @param request
	 * @param response
	 * @return
	 * @description	
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteMedicalDevice(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		System.out.println("Deleting medicalDevice ");
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 삭제가 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listMedicalDevice'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleMedicalDeviceMalfunctionCode();
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		target.setId(articleId);
		//IDValuePair found = 
		SimpleMedicalDeviceMalfunctionCode read = propertiesDAO.read(SimpleMedicalDeviceMalfunctionCode.class, articleId);
		Set<History> history = read.getMedicalDeviceMalfunctionHistory();
		for(History h : history){
			propertiesDAO.clearJunction("JUNC_MEDICAL_DEVICE_MALFUNCTION_HISTORY","MEDICAL_DEVICE_MALFUNCTION_ID",articleId);
			propertiesDAO.delete(h);
		}
		propertiesDAO.delete( target);
		
		//mav.addObject("article", found);
		//mav.addObject("message","job done");
		mav.addObject("returnUrl","properties.do?action=listMedicalDevice");
		//mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.setViewName("properties.do?action=listMedicalDevice");
		
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		deleteMedicalDeviceHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		IDValuePair target = new SimpleMedicalDeviceMalfunctionHistory();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		target.setId(articleId);
		SimpleMedicalDeviceMalfunctionHistory history  = propertiesDAO.read(SimpleMedicalDeviceMalfunctionHistory.class, articleId);
		SimpleJuncMedicalDeviceMalfunction sjc = new SimpleJuncMedicalDeviceMalfunction();
		sjc.setHistory_id(new Long(articleId).intValue());
		propertiesDAO.clearJunction("JUNC_MEDICAL_DEVICE_MALFUNCTION_HISTORY","HISTORY_ID",articleId);
		propertiesDAO.delete( target);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		
		return mav;
	}
	
	public ModelAndView getChildMedicalDevice(HttpServletRequest request, HttpServletResponse respnse){
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("getChildMedicalDevice Receiving...");
		String parentItem;
		String parentItem2;
		String parentItem3;
		String parentItem4;
		int paramLevel;
		int paramStep;
		
		parentItem = Function.nullChk(request.getParameter("parentItem"));
		parentItem2 = Function.nullChk(request.getParameter("parentItem2"));
		parentItem3 = Function.nullChk(request.getParameter("parentItem3"));
		parentItem4 = Function.nullChk(request.getParameter("parentItem4"));
		paramLevel = Function.nullChk(request.getParameter("level"),0);
		paramStep = Function.nullChk(request.getParameter("step"),0);
		System.out.println("parentItem:=" + parentItem);
		System.out.println("parentItem2:=" + parentItem2);
		System.out.println("level:=" + paramLevel);
	
		/*if (parentItem != "") {
			List<SimpleMedicalDeviceMalfunctionCode> list = propertiesDAO.listMedicalDevice(Integer.parseInt(parentItem), 2);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
			mav.addObject("level2List", list);
			System.out.println("list :  " + list.size());
		}
		if (parentItem2 != "") {
			List<SimpleMedicalDeviceMalfunctionCode> list2 = propertiesDAO.listMedicalDevice(Integer.parseInt(parentItem2), 3);
			System.out.println("list2 :  " + list2.size());
			mav.addObject("level3List", list2);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
		}
		if (parentItem3 != "") {
			List<SimpleMedicalDeviceMalfunctionCode> list3 = propertiesDAO.listMedicalDevice(Integer.parseInt(parentItem3), 4);
			System.out.println("list3 :  " + list3.size());
			mav.addObject("level4List", list3);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
		}
		if (parentItem4 != "") {
			List<SimpleMedicalDeviceMalfunctionCode> list4 = propertiesDAO.listMedicalDevice(Integer.parseInt(parentItem4), 5);
			System.out.println("list4 :  " + list4.size());
			mav.addObject("level5List", list4);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level6.jsp");
		}*/
		
		if (paramLevel == 3) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleMedicalDeviceMalfunctionCode> level2List = propertiesDAO.byLevelMedicalDeviceStep(3, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
			}
		}
		if (paramLevel == 4) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleMedicalDeviceMalfunctionCode> level2List = propertiesDAO.byLevelMedicalDeviceStep(4, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimpleMedicalDeviceMalfunctionCode> level3List = propertiesDAO.byLevelMedicalDeviceStep(4, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
			}
		}
		if (paramLevel == 5) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleMedicalDeviceMalfunctionCode> level2List = propertiesDAO.byLevelMedicalDeviceStep(5, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimpleMedicalDeviceMalfunctionCode> level3List = propertiesDAO.byLevelMedicalDeviceStep(5, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
				case 4 :
					List<SimpleMedicalDeviceMalfunctionCode> level4List = propertiesDAO.byLevelMedicalDeviceStep(5, 4, Integer.parseInt(parentItem3));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
					mav.addObject("level4List", level4List);
					break;
			}
		}
		if (paramLevel == 6) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleMedicalDeviceMalfunctionCode> level2List = propertiesDAO.byLevelMedicalDeviceStep(6, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimpleMedicalDeviceMalfunctionCode> level3List = propertiesDAO.byLevelMedicalDeviceStep(6, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
				case 4 :
					List<SimpleMedicalDeviceMalfunctionCode> level4List = propertiesDAO.byLevelMedicalDeviceStep(6, 4, Integer.parseInt(parentItem3));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
					mav.addObject("level4List", level4List);
					break;
				case 5 :
					List<SimpleMedicalDeviceMalfunctionCode> level5List = propertiesDAO.byLevelMedicalDeviceStep(6, 5, Integer.parseInt(parentItem4));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level6.jsp");
					mav.addObject("level5List", level5List);
					break;
			}
		}
		
		return mav;
	}
	//***************부작용코드 - 의료기기문제 코드 - END*******************//
	
	//***************부작용코드 - 구성요소 코드 - START*****************//
	public org.springframework.web.servlet.ModelAndView
		listComponent(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		String titleImg = "view/style/images/title/sub03_04.jpg";
		
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
		int depthLevel = Function.nullChk(request.getParameter("componentLevel"), 0);
		System.out.println("depthLevel =: " + depthLevel);
		SimpleComponentCode sc = new SimpleComponentCode();
		sc.setDepthLevel(depthLevel);
		
		// 한글 검색어 인코딩 깨짐방지
		//searchKeyword = new String(searchKeyword.getBytes("8859_1"),"UTF-8");
		
		if(!"".equals(searchColumn)){
			
			Method[] methods = SimpleComponentCode.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(sc, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("searchColumn" + searchColumn);
			System.out.println("searchKeyword" + searchKeyword);
			System.out.println("methods" + methods);
		}
		
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		Page page = new Page();
		setDefaultViewSet(mav, request);
		List<SimpleComponentLevel> componentLevel =  propertiesDAO.list(SimpleComponentLevel.class);
		List<SimpleComponentCode> dl = propertiesDAO.list(SimpleComponentCode.class,pg,10);
		//long total = propertiesDAO.count(new SimpleComponentCode());
		long total = propertiesDAO.count(sc);
		long top = total - (pg-1)*10;
		/*String pageString = page.pageList((int)total,10,pg,"properties.do?action=listComponent","");*/
		String pageString = page.pageList((int)total,10,pg,"properties.do?action=listComponent&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn+"&componentLevel="+depthLevel,"");
		
		List<SimpleComponentCode> set2 = propertiesDAO.list(sc,pg,10);
		dl =new ArrayList(set2);
		
		mav.addObject("componentLevel",componentLevel);
		mav.addObject("list", dl);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_04.jpg");
		mav.addObject("contentName","/view/jsp/properties/component/componentList1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		createComponentPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		String titleImg = "view/style/images/title/sub03_04.jpg";
		long level = Function.nullChk(request.getParameter("level"), -1);
		
		if(level< 1){
			mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
			mav.addObject("message","오류 발생. 환자문제 코드 작성시에 필요한 level 정보가 올바르지않습니다.");
			return mav;
		}
		
		if(level==1){
			
		}
		else if(level==2){
			List<SimpleComponentCode> component_list =propertiesDAO.listComponent(null, 1);
			//List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component();
			mav.addObject("level1List",component_list);
		}else if(level==3){
			//List<SimpleComponentCode> component_list =propertiesDAO.listComponent(null, 1);
			//List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component();
			List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component(3);
			mav.addObject("level1List",component_list);
			
			//List<SimpleComponentCode> component_list2 =propertiesDAO.byLevelComponent(2);
			//mav.addObject("level2List",component_list2);
		}else if(level==4){
			//List<SimpleComponentCode> component_list =propertiesDAO.listComponent(null, 1);
			//List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component();
			List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component(4);
			mav.addObject("level1List",component_list);
			
			//List<SimpleComponentCode> component_list2 =propertiesDAO.listComponent(null, 2);
			//List<SimpleComponentCode> component_list2 =propertiesDAO.byLevelComponent(2);
			//List<SimpleComponentCode> component_list2 = propertiesDAO.byLevelComponentTest(4, 2, 1);
			//mav.addObject("level2List",component_list2);
			
			//List<SimpleComponentCode> component_list3 =propertiesDAO.listComponent(null, 3);
			//List<SimpleComponentCode> component_list3 =propertiesDAO.byLevelComponent(3);
			//mav.addObject("level3List",component_list3);
		}
		else if(level==5){
			//List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component();
			//mav.addObject("level1List",component_list);
			List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component(5);
			mav.addObject("level1List",component_list);
			
			//List<SimpleComponentCode> component_list2 =propertiesDAO.byLevelComponent(2);
			//mav.addObject("level2List",component_list2);
			
			//List<SimpleComponentCode> component_list3 =propertiesDAO.byLevelComponent(3);
			//mav.addObject("level3List",component_list3);
			
			//List<SimpleComponentCode> component_list4 =propertiesDAO.byLevelComponent(4);
			//mav.addObject("level4List",component_list4);
		}
		else if(level==6){
			//List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component();
			//mav.addObject("level1List",component_list);
			List<SimpleComponentCode> component_list = propertiesDAO.byLevel1Component(6);
			mav.addObject("level1List",component_list);
			
			//List<SimpleComponentCode> component_list2 =propertiesDAO.byLevelComponent(2);
			//mav.addObject("level2List",component_list2);
			
			//List<SimpleComponentCode> component_list3 =propertiesDAO.byLevelComponent(3);
			//mav.addObject("level3List",component_list3);
			
			//List<SimpleComponentCode> component_list4 =propertiesDAO.byLevelComponent(4);
			//mav.addObject("level4List",component_list4);
			
			//List<SimpleComponentCode> component_list5 =propertiesDAO.byLevelComponent(5);
			//mav.addObject("level5List",component_list5);
		}
		
		//
		
		//
		//List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		//List<SimpleItemCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCodeType.class);
		//등급
		//List<SimpleItemGrade> itemGrades =  propertiesDAO.list(SimpleItemGrade.class);
		
		setDefaultViewSet(mav, request);
		
		//mav.addObject("itemGrades",itemGrades);
		//mav.addObject("itemCodeTypes",itemCodeTypes);
		mav.addObject("isInUse",isInUse);
		//mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_04.jpg");
		mav.addObject("contentName","/view/jsp/properties/component/createComponent1.jsp");
		
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		createComponent(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();

		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listComponent'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		
		String parentItem = Function.nullChk(request.getParameter("parentItem"),"");
		String parentItem2 = Function.nullChk(request.getParameter("parentItem2"),"");
		String parentItem3 = Function.nullChk(request.getParameter("parentItem3"),"");
		String parentItem4 = Function.nullChk(request.getParameter("parentItem4"),"");
		String parentItem5 = Function.nullChk(request.getParameter("parentItem5"),"");
		int level = Function.nullChk(request.getParameter("level"), 0);
		
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"),"");
		String fdaSourcePtKor = Function.nullChk(request.getParameter("fdaSourcePtKor"), "");
		String name = Function.nullChk(request.getParameter("name"), "");
		String fdaSourceDefinitionKor = Function.nullChk(request.getParameter("fdaSourceDefinitionKor"), "");
		String fdaSourceDefinition = Function.nullChk(request.getParameter("fdaSourceDefinition"), "");
		String nciCode = Function.nullChk(request.getParameter("nciCode"), "");
		String ncitDefinitionKor = Function.nullChk(request.getParameter("ncitDefinitionKor"), "");
		String ncitDefinition = Function.nullChk(request.getParameter("ncitDefinition"), "");
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String historyManager = Function.nullChk(request.getParameter("history_manager"), "");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		//String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("history_description"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		
		System.out.println("createComponent.propertyValue : " );
		
		SimpleComponentCode insertTarget = new SimpleComponentCode();
		
		if (level == 2) {
			insertTarget.setParentId(Integer.parseInt(parentItem));
		}
		else if (level == 3) {
			insertTarget.setParentId(Integer.parseInt(parentItem2));
		}
		else if (level == 4) {
			insertTarget.setParentId(Integer.parseInt(parentItem3));
		}
		else if (level == 5) {
			insertTarget.setParentId(Integer.parseInt(parentItem4));
		}
		else if (level == 6) {
			insertTarget.setParentId(Integer.parseInt(parentItem5));
		}
		insertTarget.setDepthLevel(level);
		insertTarget.setFdaCode(fdaCode);
		insertTarget.setFdaSourcePtKor(fdaSourcePtKor);
		insertTarget.setName(name);
		insertTarget.setFdaSourceDefinitionKor(fdaSourceDefinitionKor);
		insertTarget.setFdaSourceDefinition(fdaSourceDefinition);
		insertTarget.setNciCode(nciCode);
		insertTarget.setNcitDefinitionKor(ncitDefinitionKor);
		insertTarget.setNcitDefinition(ncitDefinition);
		
		insertTarget.setIsInUse(isInUse);
		//insertTarget.setPropertyValue(propertyValue);
		//String dateString  = activeFrom.replaceAll("/",":") + " 00:00:00";
		//System.out.println("lastModified : " + dateString);
		//insertTarget.setLastModified(new Date());
		//insertTarget.setActiveFrom(new Date());
		
		SimpleComponentCode found = propertiesDAO.create(insertTarget);
		SimpleComponentCode next = propertiesDAO.read(SimpleComponentCode.class, found.getId());
		if (level == 1) {
			found.setParentId((int) next.getId());
			propertiesDAO.update(found);
		}
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimpleComponentHistory.class);
		 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getComponentHistory().add(childObject);
			propertiesDAO.update(found);
		}
		
		/*mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.addObject("message","create succeess");*/
		mav.addObject("returnUrl","properties.do?action=listComponent");
		mav.setViewName("properties.do?action=listComponent");
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description 
	 */
	public org.springframework.web.servlet.ModelAndView
		readComponent(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_04.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		//Level1 
		//List<SimpleItem> dl =propertiesDAO.list(SimpleItem.class);
		
		//List<SimpleTraceability> traceabilityList =   propertiesDAO.list(SimpleTraceability.class);
		
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		
		setDefaultViewSet(mav, request);
		
		//mav.addObject("traceabilityList",traceabilityList);
		mav.addObject("historyType",historyType);
		IDValuePair found = propertiesDAO.read(SimpleComponentCode.class, articleId);
		
		SimpleComponentCode found2 = propertiesDAO.read(SimpleComponentCode.class, articleId);
		SimpleComponentCode foundParent2 = propertiesDAO.getComponentParent(found2.getParentId());//get parent
		
		if (found2.getDepthLevel() == 3) {
			SimpleComponentCode found3 = propertiesDAO.read(SimpleComponentCode.class, foundParent2.getId());
			SimpleComponentCode foundParent3 = propertiesDAO.getComponentParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
		}
		else if (found2.getDepthLevel() == 4) {
			SimpleComponentCode found3 = propertiesDAO.read(SimpleComponentCode.class, foundParent2.getId());
			SimpleComponentCode foundParent3 = propertiesDAO.getComponentParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimpleComponentCode found4 = propertiesDAO.read(SimpleComponentCode.class, foundParent3.getId());
			SimpleComponentCode foundParent4 = propertiesDAO.getComponentParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
		}
		else if (found2.getDepthLevel() == 5) {
			SimpleComponentCode found3 = propertiesDAO.read(SimpleComponentCode.class, foundParent2.getId());
			SimpleComponentCode foundParent3 = propertiesDAO.getComponentParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimpleComponentCode found4 = propertiesDAO.read(SimpleComponentCode.class, foundParent3.getId());
			SimpleComponentCode foundParent4 = propertiesDAO.getComponentParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
			
			SimpleComponentCode found5 = propertiesDAO.read(SimpleComponentCode.class, foundParent4.getId());
			SimpleComponentCode foundParent5 = propertiesDAO.getComponentParent(found5.getParentId());//get parent
			mav.addObject("parent5", foundParent5);
		}
		else if (found2.getDepthLevel() == 6) {
			SimpleComponentCode found3 = propertiesDAO.read(SimpleComponentCode.class, foundParent2.getId());
			SimpleComponentCode foundParent3 = propertiesDAO.getComponentParent(found3.getParentId());//get parent
			mav.addObject("parent3", foundParent3);
			
			SimpleComponentCode found4 = propertiesDAO.read(SimpleComponentCode.class, foundParent3.getId());
			SimpleComponentCode foundParent4 = propertiesDAO.getComponentParent(found4.getParentId());//get parent
			mav.addObject("parent4", foundParent4);
			
			SimpleComponentCode found5 = propertiesDAO.read(SimpleComponentCode.class, foundParent4.getId());
			SimpleComponentCode foundParent5 = propertiesDAO.getComponentParent(found5.getParentId());//get parent
			mav.addObject("parent5", foundParent5);
			
			SimpleComponentCode found6 = propertiesDAO.read(SimpleComponentCode.class, foundParent5.getId());
			SimpleComponentCode foundParent6 = propertiesDAO.getComponentParent(found6.getParentId());//get parent
			mav.addObject("parent6", foundParent6);
		}
		
		mav.addObject("article", found);
		mav.addObject("parent2", foundParent2);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_04.jpg");
		mav.addObject("contentName","/view/jsp/properties/component/readComponent1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
		updateComponentPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listComponent'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_04.jpg";
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		IDValuePair found = propertiesDAO.read(SimpleComponentCode.class, articleId);
		
		
		//
		List<SimpleHistoryType> historyType =  propertiesDAO.list(SimpleHistoryType.class);
		//
		List<SimpleIsInUse> isInUse =  propertiesDAO.list(SimpleIsInUse.class);
		//
		
		setDefaultViewSet(mav, request);
		
		mav.addObject("isInUse",isInUse);
		mav.addObject("historyType",historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_04.jpg");
		mav.addObject("contentName","/view/jsp/properties/component/updateComponent1.jsp");
		
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 *  @description 
	 */
	public org.springframework.web.servlet.ModelAndView
		updateComponent(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 등록이 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listComponent'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		SimpleComponentCode targetObject = new SimpleComponentCode();
		
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"),"");
		String fdaSourcePtKor = Function.nullChk(request.getParameter("fdaSourcePtKor"), "");
		String name = Function.nullChk(request.getParameter("name"),"");
		String fdaSourceDefinitionKor = Function.nullChk(request.getParameter("fdaSourceDefinitionKor"), "");
		String fdaSourceDefinition = Function.nullChk(request.getParameter("fdaSourceDefinition"), "");
		String nciCode = Function.nullChk(request.getParameter("nciCode"), "");
		String ncitDefinitionKor = Function.nullChk(request.getParameter("ncitDefinitionKor"), "");
		String ncitDefinition = Function.nullChk(request.getParameter("ncitDefinition"), "");
		
		String lastModified = Function.nullChk(request.getParameter("lastModified"),"");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
		int historyType = Function.nullChk(request.getParameter("historyType"), 0);
		String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
		
		System.out.println("lastModified:=" + lastModified);
		System.out.println("activeFrom:=" + activeFrom);
		System.out.println("historyManager:=" + historyManager);
		System.out.println("historyType:=" + historyType);
		System.out.println("historyDescription:=" + historyDescription);
		
		
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		
		SimpleComponentCode found = propertiesDAO.read(SimpleComponentCode.class, articleId);
		found.setPropertyValue(propertyValue);
		found.setIsInUse(isInUse);
		found.setFdaCode(fdaCode);
		found.setFdaSourcePtKor(fdaSourcePtKor);
		found.setName(name);
		found.setFdaSourceDefinitionKor(fdaSourceDefinitionKor);
		found.setFdaSourceDefinition(fdaSourceDefinition);
		found.setNciCode(nciCode);
		found.setNcitDefinitionKor(ncitDefinitionKor);
		found.setNcitDefinition(ncitDefinition);
		
		History childObject = historyServiceProvider.buildOrEmpty(request, SimpleComponentHistory.class);
	 
		if(childObject!=null){
			propertiesDAO.create(childObject);
			found.getComponentHistory().add(childObject);
		}
		SimpleComponentCode updated = propertiesDAO.update(found);
		mav.setViewName("properties.do?action=readComponent&articleId="+articleId);
		return mav;
	}
	
	/**
	 * 
	 * @author yujin
	 * @param request
	 * @param response
	 * @return
	 * @description	
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteComponent(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		System.out.println("Deleting component ");
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 회원번호
		HttpSession session = request.getSession(true);
		member.Member objMember ;
		long privilegeId = -1;
		if (session.getAttribute("user")!= null){
			objMember = (member.Member) session.getAttribute("user");
			privilegeId = objMember.getPrivilegeId();
		} else {
			out.println("<script language=javascript>");
			out.println("alert('로그인 후 삭제가 가능합니다');");
			out.println("location.href = 'members.do?action=authenticateMemberPage'");
			out.println("</script>");
			return null;
		}
			
		if(privilegeId != 2){
			out.println("<script language=javascript>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href = 'properties.do?action=listComponent'");
			out.println("</script>");
			return null;
		} 
		
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleComponentCode();
		
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		
		target.setId(articleId);
		//IDValuePair found = 
		SimpleComponentCode read = propertiesDAO.read(SimpleComponentCode.class, articleId);
		Set<History> history = read.getComponentHistory();
		for(History h : history){
			propertiesDAO.clearJunction("JUNC_COMPONENT_CODE_HISTORY","COMPONENT_CODE_ID",articleId);
			propertiesDAO.delete(h);
		}
		propertiesDAO.delete( target);
		
		//mav.addObject("article", found);
		//mav.addObject("message","job done");
		mav.addObject("returnUrl","properties.do?action=listComponent");
		//mav.addObject("contentName","/view/jsp/common/defaultMessageDisplay1.jsp");
		mav.setViewName("properties.do?action=listComponent");
		
		return mav;
	}
	
	public org.springframework.web.servlet.ModelAndView
		deleteComponentHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		IDValuePair target = new SimpleComponentHistory();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		target.setId(articleId);
		SimpleComponentHistory history  = propertiesDAO.read(SimpleComponentHistory.class, articleId);
		SimpleJuncComponent sjc = new SimpleJuncComponent();
		sjc.setHistory_id(new Long(articleId).intValue());
		propertiesDAO.clearJunction("JUNC_COMPONENT_CODE_HISTORY","HISTORY_ID",articleId);
		propertiesDAO.delete( target);
		mav.addObject("message","1");
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		
		return mav;
	}
	
	public ModelAndView getChildComponent(HttpServletRequest request, HttpServletResponse respnse){
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("getChildComponent Receiving...");
		String parentItem;
		String parentItem2;
		String parentItem3;
		String parentItem4;
		int paramLevel;
		int paramStep;
		
		parentItem = Function.nullChk(request.getParameter("parentItem"));
		parentItem2 = Function.nullChk(request.getParameter("parentItem2"));
		parentItem3 = Function.nullChk(request.getParameter("parentItem3"));
		parentItem4 = Function.nullChk(request.getParameter("parentItem4"));
		paramLevel = Function.nullChk(request.getParameter("level"),0);
		paramStep = Function.nullChk(request.getParameter("step"),0);
		System.out.println("parentItem:=" + parentItem);
		System.out.println("parentItem2:=" + parentItem2);
		System.out.println("level:=" + paramLevel);
	
		/*if (parentItem != "") {
			//List<SimpleComponentCode> list = propertiesDAO.listComponent(Integer.parseInt(parentItem), 2);
			List<SimpleComponentCode> list = propertiesDAO.byLevelComponentTest(paramLevel, 2, Integer.parseInt(parentItem));
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
			mav.addObject("level2List", list);
			System.out.println("list :  " + list.size());
		}
		if (parentItem2 != "") {
			//List<SimpleComponentCode> list2 = propertiesDAO.listComponent(Integer.parseInt(parentItem2), 3);
			List<SimpleComponentCode> list2 = propertiesDAO.byLevelComponentTest(4, 3, Integer.parseInt(parentItem2));
			System.out.println("list2 :  " + list2.size());
			mav.addObject("level3List", list2);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
		}
		if (parentItem3 != "") {
			List<SimpleComponentCode> list3 = propertiesDAO.listComponent(Integer.parseInt(parentItem3), 4);
			System.out.println("list3 :  " + list3.size());
			mav.addObject("level4List", list3);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
		}
		if (parentItem4 != "") {
			List<SimpleComponentCode> list4 = propertiesDAO.listComponent(Integer.parseInt(parentItem4), 5);
			System.out.println("list4 :  " + list4.size());
			mav.addObject("level5List", list4);
			mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level6.jsp");
		}*/
		
		if (paramLevel == 3) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleComponentCode> level2List = propertiesDAO.byLevelComponentStep(3, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
			}
		}
		if (paramLevel == 4) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleComponentCode> level2List = propertiesDAO.byLevelComponentStep(4, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimpleComponentCode> level3List = propertiesDAO.byLevelComponentStep(4, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
			}
		}
		if (paramLevel == 5) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleComponentCode> level2List = propertiesDAO.byLevelComponentStep(5, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimpleComponentCode> level3List = propertiesDAO.byLevelComponentStep(5, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
				case 4 :
					List<SimpleComponentCode> level4List = propertiesDAO.byLevelComponentStep(5, 4, Integer.parseInt(parentItem3));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
					mav.addObject("level4List", level4List);
					break;
			}
		}
		if (paramLevel == 6) {
			switch(paramStep) 
			{
				case 2 :
					List<SimpleComponentCode> level2List = propertiesDAO.byLevelComponentStep(6, 2, Integer.parseInt(parentItem));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level3.jsp");
					mav.addObject("level2List", level2List);
					break;
				case 3 :
					List<SimpleComponentCode> level3List = propertiesDAO.byLevelComponentStep(6, 3, Integer.parseInt(parentItem2));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level4.jsp");
					mav.addObject("level3List", level3List);
					break;
				case 4 :
					List<SimpleComponentCode> level4List = propertiesDAO.byLevelComponentStep(6, 4, Integer.parseInt(parentItem3));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level5.jsp");
					mav.addObject("level4List", level4List);
					break;
				case 5 :
					List<SimpleComponentCode> level5List = propertiesDAO.byLevelComponentStep(6, 5, Integer.parseInt(parentItem4));
					mav.setViewName("/view/jsp/common/dynamic/patient_medical_component_Level6.jsp");
					mav.addObject("level5List", level5List);
					break;
			}
		}
	
		return mav;
	}
	//***************부작용코드 - 구성요소 코드 - END*******************//
	
	public ModelAndView getChildMCN(HttpServletRequest request, HttpServletResponse respnse){
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("getChildMCN Receiving...");
		String parentItem;
		int parentItemGrade;
		
		parentItem = Function.nullChk(request.getParameter("parentItem"));
		parentItemGrade = Function.nullChk(request.getParameter("parentItemGrade"), 0);
		int level = 2;
		List<Mea_class_no> list = propertiesDAO.listMeaClassNo(parentItem, parentItemGrade, level);
		mav.setViewName("/view/jsp/common/dynamic/meaClassNoLevel3.jsp");
		mav.addObject("list", list);
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description 신구품목매칭관리 리스트. 
	 * PropertiesDelegate 가 너무 방대해 져서 ServiceProvider 단위로 자르기
	 * 시도 함. 
	 */
	public org.springframework.web.servlet.ModelAndView
		listMatchingItems(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		
		mav = matchServiceProvider.listMatchingItems(request, response);
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		readMatch(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.readMatch(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
	listMasterCodes(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
		int codeAgeId = Function.nullChk(request.getParameter("codeAge"), 1);
		
		/*SimpleItemCodeType codeAge2 = new SimpleItemCodeType();
		codeAge2.setId(codeAgeId);
		Mea_class_no_view2 target3 = new Mea_class_no_view2();
		target3.setCode_age_id(codeAgeId);
		codeAge2 = propertiesDAO.read(SimpleItemCodeType.class, codeAgeId);
		target3.setCode_age(codeAge2);*/
		
		
		SimpleItemCategoryCodeType codeAge = new SimpleItemCategoryCodeType();
		codeAge.setId(codeAgeId);
		SimpleItemCategory target2 = new SimpleItemCategory();
		target2.setCodeAge(codeAgeId);
		codeAge = propertiesDAO.read(SimpleItemCategoryCodeType.class, codeAgeId);
		target2.setCode_age(codeAge);
		
		
		if(!"".equals(searchColumn)){
			
			Method[] methods = SimpleItemCategory.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(target2, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
		Page page = new Page();
		String pageString = "";
		//setDefaultViewSet(mav, request);
		List<SimpleItemCategory> dl = null;
	
		List<SimpleItemCategoryLevel> meaClassNoLevel =  propertiesDAO.list(SimpleItemCategoryLevel.class);
		
		long total = propertiesDAO.count(target2,0);
		long top = total - (pg-1)*10;
		pageString = page.pageList((int)total,10,pg,"properties.do?action=listMasterCodes&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn,"");
		mav.addObject("meaClassNoLevel",meaClassNoLevel);
		Set<SimpleItemCategory> set2 = propertiesDAO.listItemCategory(target2,pg,10);
		dl =new ArrayList(set2);
		List<SimpleItemCategoryCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCategoryCodeType.class);
		mav.addObject("list", dl);
		mav.addObject("itemCodeTypes", itemCodeTypes);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("pageString", pageString);
		String titleImg = "view/style/images/title/sub03_01.jpg";
		mav.addObject("titleImg", titleImg);
		mav.setViewName("/view/jsp/properties/category/listOldItems1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		updateMatchingItemPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.updateMatchPage(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		updateMatch(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.updateMatch(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		createMatchPage(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.createMatchPage(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		createMatchingItem(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.createMatch(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 8. 2013
	 * @description
	 */
	public org.springframework.web.servlet.ModelAndView
	listSlaveCodes(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"),"");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"),"");
		int codeAgeId = Function.nullChk(request.getParameter("codeAge"), 2);
		/*SimpleItemCodeType codeAge = new SimpleItemCodeType();
		codeAge.setId(codeAgeId);
		Mea_class_no_view2 target2 = new Mea_class_no_view2();
		target2.setCode_age_id(codeAgeId);
		codeAge = propertiesDAO.read(SimpleItemCodeType.class, codeAgeId);
		target2.setCode_age(codeAge);*/
		
		SimpleItemCategoryCodeType codeAge = new SimpleItemCategoryCodeType();
		codeAge.setId(codeAgeId);
		SimpleItemCategory target2 = new SimpleItemCategory();
		target2.setCodeAge(codeAgeId);
		codeAge = propertiesDAO.read(SimpleItemCategoryCodeType.class, codeAgeId);
		target2.setCode_age(codeAge);
		
		if(!"".equals(searchColumn)){
			
			Method[] methods = SimpleItemCategory.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().equals(searchColumn)){
					try {
						//m.invoke(target, searchKeyword);
						m.invoke(target2, searchKeyword);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
		Page page = new Page();
		String pageString = "";
		//setDefaultViewSet(mav, request);
		List<SimpleItemCategory> dl = null;
	
		List<SimpleItemCategoryLevel> meaClassNoLevel =  propertiesDAO.list(SimpleItemCategoryLevel.class);
		
		long total = propertiesDAO.count(target2,0);
		long top = total - (pg-1)*10;
		pageString = page.pageList((int)total,10,pg,"properties.do?action=listSlaveCodes&searchKeyword="+searchKeyword+"&searchColumn="+searchColumn,"");
		mav.addObject("meaClassNoLevel",meaClassNoLevel);
		Set<SimpleItemCategory> set2 = propertiesDAO.listItemCategory(target2,pg,10);
		dl =new ArrayList(set2);
		List<SimpleItemCategoryCodeType> itemCodeTypes =  propertiesDAO.list(SimpleItemCategoryCodeType.class);
		mav.addObject("list", dl);
		mav.addObject("itemCodeTypes", itemCodeTypes);
		mav.addObject("top", top);
		mav.addObject("total", total);
		mav.addObject("pageString", pageString);
		String titleImg = "view/style/images/title/sub03_01.jpg";
		mav.addObject("titleImg", titleImg);
		mav.setViewName("/view/jsp/properties/category/listNewItems1.jsp");
		
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteMatch(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.deleteMatch(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteSlave(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.deleteSlave(request, response);
		return mav;
	}

	/**
	 * 
	 * @author Adam Hun/현한영
	 * @param request
	 * @param response
	 * @return
	 * 2013. 11. 19. 2013
	 * @description	신구품목 매칭 글 읽기
	 */
	public org.springframework.web.servlet.ModelAndView
		deleteHistory(HttpServletRequest request, HttpServletResponse response){
		org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
		mav = matchServiceProvider.deleteHistory(request, response);
		return mav;
	}

	// 업체 중복검색
	public org.springframework.web.servlet.ModelAndView
	CompanyChk(HttpServletRequest request, HttpServletResponse response) throws IOException{
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	PrintWriter out = response.getWriter();
	SimpleCompany company = new SimpleCompany();
	
	String meddev_entp_no = Function.nullChk(request.getParameter("meddev_entp_no"), ""); //업허가번호
	String cob_flag_code = Function.nullChk(request.getParameter("cob_flag_code"), ""); //업종코드
	System.out.println("company.meddev_entp_no      ==" + meddev_entp_no);
	System.out.println("company.cob_flag_code      ==" + cob_flag_code);
	//List<SimpleCompany> dl = propertiesDAO.list(SimpleCompany.class, pg, 10);
	
	long total = propertiesDAO.countCompany(meddev_entp_no,cob_flag_code);
	
	System.out.println("company.total      ==" + total);
	
    out.println(total);
    out.close();

	return mav;

	}



	// 제품 중복검색	
	public org.springframework.web.servlet.ModelAndView
	ProductChk(HttpServletRequest request, HttpServletResponse response) throws IOException{
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
	PrintWriter out = response.getWriter();
	SimpleProduct product = new SimpleProduct();
	
	String meddev_item_no = Function.nullChk(request.getParameter("meddev_item_no"), ""); //업허가번호
	String cob_flag_code = Function.nullChk(request.getParameter("cob_flag_code"), ""); //업허가번호
	
	System.out.println("company.meddev_item_no      ==" + meddev_item_no);
	System.out.println("company.cob_flag_code      ==" + cob_flag_code);
	//List<SimpleCompany> dl = propertiesDAO.list(SimpleCompany.class, pg, 10);
	
	long total = propertiesDAO.countProduct(meddev_item_no,cob_flag_code);
	
	System.out.println("company.total      ==" + total);
	
    out.println(total);
    out.close();

	return mav;
	}
	
	public ModelAndView isUniqueItemCode(HttpServletRequest request, HttpServletResponse response){
		//"properties.do?action=isUniqueItemCode&itemCode="+itemCode+"&itemGrade="+itemGrade;
		ModelAndView mav = new ModelAndView();
		int returnValue = -1;
		
		String stringGrade = Function.nullChk(request.getParameter("itemGrade"), "");
		int grade =0;
		if(!"".equals(stringGrade)){
			grade = Integer.parseInt(stringGrade);
		}
		String mea_class_no = Function.nullChk(request.getParameter("itemCode"), "");
		Mea_class_no target = new Mea_class_no();
		target.setGrade(stringGrade);
		target.setMea_class_no(mea_class_no);
		Mea_class_no found = (Mea_class_no) propertiesDAO.readMeaClassNo(target) ;
		
		if(grade>-1 && !"".equals(mea_class_no)){
			if(found!=null && found.getMea_class_no()!=null ){
				
			}else{
				returnValue = 1;
			}
		}
		//setDefaultViewSet(mav, request);
		mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		mav.addObject("message",returnValue);
		return mav;
	}
	
	
	
	
	
	public ModelAndView documentNumberCheck(HttpServletRequest request, HttpServletResponse response) throws IOException{

		ModelAndView mav = new ModelAndView();
		
		long document_number = Function.nullChk((request.getParameter("document_number")), 0);
		System.out.println("[SimplePropertiesSelegate].documentNumberCheck().document_number  " + document_number);
		
		long sideEffecrResult = propertiesDAO.sideEffectDocumentNumberCheck(document_number);
		System.out.println("[SimplePropertiesSelegate].documentNumberCheck().sideEffecrResult  " + sideEffecrResult);
		
		long safetyResult = propertiesDAO.safetyDocumentNumberCheck(document_number);
		System.out.println("[SimplePropertiesSelegate].documentNumberCheck().safetyResult  " + safetyResult);
		
		int result = 0;
		
		if(sideEffecrResult > 0 || safetyResult > 0){
			result = 1;
		}
		
		//result = (int) (sideEffecrResult + safetyResult);
		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();
		
		return mav;
	}
	
	
	
	public ModelAndView codeDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{

		ModelAndView mav = new ModelAndView();

		long report_id = Function.nullChk((request.getParameter("report_id")), 0);
		System.out.println("[SimplePropertiesSelegate].codeDelete().report_id  " + report_id);
		
		long code_id = Function.nullChk((request.getParameter("code_id")), 0);
		System.out.println("[SimplePropertiesSelegate].codeDelete().code_id  " + code_id);
		
		long gubun = Function.nullChk((request.getParameter("gubun")), 0);
		System.out.println("[SimplePropertiesSelegate].codeDelete().gubun  " + gubun);
		
		int gubun2 = Function.nullChk((request.getParameter("gubun2")), 0);
		System.out.println("[SimplePropertiesSelegate].codeDelete().gubun2" + gubun2 );
		
		long result = 0;
		
		result = propertiesDAO.codeDelete(report_id, code_id, gubun, gubun2);
		
		PrintWriter out = response.getWriter();
		
		out.println(result);
		out.close();
		
		return mav;
	}	
	
	
	public org.springframework.web.servlet.ModelAndView
	fdaCodeDuplicate(HttpServletRequest request, HttpServletResponse response){
	org.springframework.web.servlet.ModelAndView mav = new ModelAndView();
	
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		String fdaCode = Function.nullChk(request.getParameter("fdaCode"), "");
		String table = Function.nullChk(request.getParameter("table"), "");
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		
		long count = propertiesDAO.search(fdaCode, table);
		
		out.println(count);
		out.flush();
		out.close();
		
		return mav;
	}
	
	
}

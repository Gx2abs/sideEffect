// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemServiceProvider.java

package safety.renewal.sgi.item;

import abstraction.IDValuePair;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;
import properties.SimpleIsInUse;
import properties.history.History;
import properties.item.SimpleHistoryType;
import properties.product.SimpleProduct;
import report.SimpleSideeffectReport;
import safety.SimpleSafetyReport;
import safety.renewal.company.SimpleCompany1;
import safety.renewal.sgi.category.*;

// Referenced classes of package safety.renewal.sgi.item:
//            ItemServiceProvider, SimpleItemHistory1, ItemHistory1, SimpleItem1, 
//            ItemDAO, SimpleItemCodeType1, SimpleItemType, SimpleJuncItemType, 
//            ItemLot, ItemType, SimpleJuncItem

public class SimpleItemServiceProvider implements ItemServiceProvider {

	public SimpleItemServiceProvider() {
	}

	public ItemDAO getItemDAO() {
		return ItemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		ItemDAO = itemDAO;
	}

	public History buildOrEmpty(HttpServletRequest request, Class class1) {
		ItemHistory1 history = null;
		try {
			if (validate(request)) {
				String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
				String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
				int historyType = Function.nullChk(request.getParameter("historyType"), 0);
				String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
				history = new SimpleItemHistory1();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date lm = new Date();
				Date af = new Date();
				af = sdf.parse(activeFrom);
				history.setLastModified(lm);
				history.setActiveFrom(af);
				history.setManager(historyManager);
				history.setHistoryDescription(historyDescription);
				history.setHistoryType(historyType);
			} else {
				System.out.println("empty history !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return history;
	}

	public boolean validate(HttpServletRequest request) {
		boolean result = false;
		try {
			String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
			String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
			int historyType = Function.nullChk(request.getParameter("historyType"), 0);
			String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
			if ("".equals(activeFrom) || historyType == 0) {
				System.out.println((new StringBuilder("activeFrom : ")).append(activeFrom).toString());
				System.out.println((new StringBuilder("historyType : ")).append(historyType).toString());
			} else {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println((new StringBuilder("SimpleItemServiceProvicer.validate : ")).append(result).toString());
		return result;
	}

	public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/properties/propertiesLeft.jsp");
		mav.addObject("rightName", request.getAttribute("rightName"));
		return mav;
	}

	public ModelAndView listItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_06.jpg";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		setDefaultViewSet(mav, request);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
		SimpleItem1 si = new SimpleItem1();
		List dl = null;
		Method methods[] = SimpleItem1.class.getDeclaredMethods();
		long total = 0L;
		if (!"".equals(searchColumn)) {
			if (searchColumn.equals("setEntp_name")) {
				dl = ItemDAO.listEntpItem(pg, 10, searchKeyword);
				total = ItemDAO.listEntpItemCnt(searchKeyword).longValue();
			} else if (searchColumn.equals("setClass_kor_name")) {
				dl = ItemDAO.listCategoryItem(pg, 10, searchKeyword);
				total = ItemDAO.listCategoryItemCnt(searchKeyword).longValue();
			} else if (searchColumn.equals("setMea_class_no")) {
				dl = ItemDAO.listCategoryItemNo(pg, 10, searchKeyword);
				total = ItemDAO.listCategoryItemCntNo(searchKeyword).longValue();
			} else {
				Method amethod[];
				int j = (amethod = methods).length;
				for (int i = 0; i < j; i++) {
					Method m = amethod[i];
					if (m.getName().equals(searchColumn))
						try {
							m.invoke(si, new Object[] { searchKeyword });
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
				}

				List set2 = ItemDAO.list(si, pg, 10);
				dl = new ArrayList(set2);
				total = ItemDAO.count(si);
			}
		} else {
			List set2 = ItemDAO.list(si, pg, 10);
			if (set2 != null)
				dl = new ArrayList(set2);
			total = ItemDAO.count(si);
		}
		setDefaultViewSet(mav, request);
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		searchKeyword = Function.URLEncoderUTF8(searchKeyword);
		String pageString = page.pageList((int) total, 10, pg, (new StringBuilder("safetyItem.do?action=listItem&searchKeyword="))
				.append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
		mav.addObject("contentName", "/view/jsp/properties/item/productList1.jsp");
		return mav;
	}

	public ModelAndView createItemPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_06.jpg";
		List historyType = ItemDAO.list(SimpleHistoryType.class);
		List isInUse = ItemDAO.list(SimpleIsInUse.class);
		List codeType = ItemDAO.list(SimpleItemCodeType1.class);
		setDefaultViewSet(mav, request);
		mav.addObject("codeType", codeType);
		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
		mav.addObject("contentName", "/view/jsp/properties/item/createProduct1.jsp");
		return mav;
	}

	public ModelAndView createItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Enumeration headernames = request.getHeaderNames();
		String headerName = "";
		String headerVal = "";
		for (; headernames.hasMoreElements(); System.out
				.println((new StringBuilder(String.valueOf(headerName))).append("/").append(headerVal).toString())) {
			headerName = (String) headernames.nextElement();
			headerVal = request.getHeader(headerName);
		}

		try {
			request.setCharacterEncoding("8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setDefaultViewSet(mav, request);
		String name[] = request.getParameterValues("name[]");
		String manuf_lot[] = request.getParameterValues("manuf_lot[]");
		String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		int company_id = Function.nullChk(request.getParameter("company_id"), 0);
		String item_id = Function.nullChk(request.getParameter("item_id"), "");
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		String item_grade = Function.nullChk(request.getParameter("item_grade"), "");
		String cob_flag_code = Function.nullChk(request.getParameter("codeType"), "");
		String meddev_item_no = Function.nullChk(request.getParameter("meddev_item_no"), "");
		String manuf_import_name = Function.nullChk(request.getParameter("manuf_import_name"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		System.out.println((new StringBuilder("privilegeId111")).append(privilegeId).toString());
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			SimpleItem1 insertTarget = new SimpleItem1();
			insertTarget.setIsInUse(Integer.valueOf(isInUse));
			insertTarget.setMea_class_no(mea_class_no);
			insertTarget.setMeddev_entp_seq(company_id);
			insertTarget.setCompany_id(Integer.valueOf(company_id));
			insertTarget.setItem_category_number(Integer.valueOf(Integer.parseInt(item_id)));
			insertTarget.setGrade(item_grade);
			insertTarget.setCob_flag_code(cob_flag_code);
			insertTarget.setMeddev_item_no(meddev_item_no);
			insertTarget.setManuf_import_name(manuf_import_name);
			insertTarget.setPropertyValue(propertyValue);
			SimpleItem1 found = (SimpleItem1) ItemDAO.create(insertTarget);
			History childObject = buildOrEmpty(request, SimpleItemHistory1.class);
			if (childObject != null) {
				ItemDAO.create(childObject);
				found.getItemHistory().add(childObject);
				ItemDAO.update(found);
			}
			SimpleItemType insertTarget2 = new SimpleItemType();
			SimpleJuncItemType insertTarget3 = new SimpleJuncItemType();
			String arr = "";
			String arr2 = "";
			for (int i = 0; i < name.length; i++) {
				arr = name[i];
				System.out.println((new StringBuilder("name: ")).append(arr).toString());
				insertTarget2.setItem_id((int) found.getId());
				insertTarget2.setType_name(arr);
				ItemDAO.create(insertTarget2);
				insertTarget3.setType_id(insertTarget2.getId());
				insertTarget3.setItem_id((int) found.getId());
				ItemDAO.create(insertTarget3);
			}

			mav.setViewName("safetyItem.do?action=listItem");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'safetyItem.do?action=listItem'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView readItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		List historyType = ItemDAO.list(SimpleHistoryType.class);
		setDefaultViewSet(mav, request);
		mav.addObject("historyType", historyType);
		IDValuePair found = ItemDAO.read(SimpleItem1.class, articleId);
		mav.addObject("article", found);
		mav.addObject("titleImg", "view/style/images/title/sub03_06.jpg");
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
		mav.addObject("contentName", "/view/jsp/properties/item/readProduct1.jsp");
		return mav;
	}

	public ModelAndView updateItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		SimpleProduct targetObject = new SimpleProduct();
		String name[] = request.getParameterValues("name[]");
		String manuf_lot[] = request.getParameterValues("manuf_lot[]");
		String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
		int company_id = Function.nullChk(request.getParameter("company_id"), 0);
		int item_id = Function.nullChk(request.getParameter("item_id"), 0);
		String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
		String item_grade = Function.nullChk(request.getParameter("item_grade"), "");
		String cob_flag_code = Function.nullChk(request.getParameter("codeType"), "");
		String meddev_item_no = Function.nullChk(request.getParameter("meddev_item_no"), "");
		String manuf_import_name = Function.nullChk(request.getParameter("manuf_import_name"), "");
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			SimpleItem1 found = (SimpleItem1) ItemDAO.read(SimpleItem1.class, articleId);
			found.setIsInUse(Integer.valueOf(isInUse));
			found.setCompany_id(Integer.valueOf(company_id));
			found.setItem_category_number(Integer.valueOf(item_id));
			found.setMea_class_no(mea_class_no);
			found.setGrade(item_grade);
			found.setCob_flag_code(cob_flag_code);
			found.setMeddev_item_no(meddev_item_no);
			found.setManuf_import_name(manuf_import_name);
			found.setPropertyValue(propertyValue);
			found.setIsInUse(Integer.valueOf(isInUse));
			History childObject = buildOrEmpty(request, SimpleItemHistory1.class);
			if (childObject != null) {
				ItemDAO.create(childObject);
				found.getItemHistory().add(childObject);
				ItemDAO.update(found);
			}
			SimpleItemType insertTarget2 = new SimpleItemType();
			SimpleJuncItemType insertTarget3 = new SimpleJuncItemType();
			boolean isValid = checkLength(new String[][] { name });
			if (isValid) {
				String arr = "";
				String arr2 = "";
				for (int i = 0; i < name.length; i++)
					if (name[i] != "") {
						arr = name[i];
						System.out.println((new StringBuilder("name: ")).append(arr).toString());
						insertTarget2.setItem_id((int) found.getId());
						insertTarget2.setType_name(arr);
						ItemDAO.create(insertTarget2);
						insertTarget3.setType_id(insertTarget2.getId());
						insertTarget3.setItem_id((int) found.getId());
						ItemDAO.create(insertTarget3);
					}

			}
			ItemDAO.update(found);
			mav.setViewName((new StringBuilder("safetyItem.do?action=readItem&articleId=")).append(articleId).toString());
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'safetyItem.do?action=listItem'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public boolean checkLength(String[]... args) {

		System.out.println("checkLength.args.length := " + args.length);

		boolean result = true;
		int length = 0;
		int lengthN_1 = 0;
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				String[] set1 = args[i];

				if (set1 != null) {
					System.out.println("set1.length := " + set1.length);
					if (i == 0) {
						lengthN_1 = set1.length;
					}

					length = set1.length;

					if (length != lengthN_1) {
						result = false;
					}
					lengthN_1 = length;
				} else {
					result = false;
				}

			}
		}

		return result;
	}

	public ModelAndView updateItemPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_06.jpg";
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		IDValuePair found = ItemDAO.read(SimpleItem1.class, articleId);
		List historyType = ItemDAO.list(SimpleHistoryType.class);
		List isInUse = ItemDAO.list(SimpleIsInUse.class);
		List codeType = ItemDAO.list(SimpleItemCodeType1.class);
		setDefaultViewSet(mav, request);
		mav.addObject("codeType", codeType);
		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_06.jpg");
		mav.addObject("contentName", "/view/jsp/properties/item/updateProduct1.jsp");
		return mav;
	}

	public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		System.out.println("Deleting Product ");
		setDefaultViewSet(mav, request);
		IDValuePair target = new SimpleItem1();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			target.setId(articleId);
			SimpleItem1 master = (SimpleItem1) ItemDAO.read(SimpleItem1.class, articleId);
			SimpleSideeffectReport siRefer = new SimpleSideeffectReport();
			siRefer.setMeb_item_id(Integer.valueOf((int) master.getId()));
			long refer = ItemDAO.count(siRefer);
			if (refer > 0L) {
				out.println("<script language=javascript>");
				out.println(
						"alert('\uBD80\uC791\uC6A9\uBCF4\uACE0\uC5D0\uC11C \uD574\uB2F9 \uCF54\uB4DC\uAC00 \uC0AC\uC6A9\uC911\uC785\uB2C8\uB2E4.');");
				out.println((new StringBuilder("location.href = 'safetyItem.do?action=readItem&menu=6&articleId=")).append(articleId).append("'")
						.toString());
				out.println("</script>");
				return null;
			}
			SimpleSafetyReport saRefer = new SimpleSafetyReport();
			saRefer.setMeb_item_id(Integer.valueOf((int) master.getId()));
			refer = ItemDAO.count(saRefer);
			if (refer > 0L) {
				out.println("<script language=javascript>");
				out.println(
						"alert('\uC548\uC804\uC131\uC815\uBCF4\uC5D0\uC11C \uD574\uB2F9 \uCF54\uB4DC\uAC00 \uC0AC\uC6A9 \uC911\uC785\uB2C8\uB2E4.');");
				out.println((new StringBuilder("location.href = 'safetyItem.do?action=readItem&menu=6&articleId=")).append(articleId).append("'")
						.toString());
				out.println("</script>");
				return null;
			}
			Set<ItemLot> lotset = master.getItem_lot();
			Set<ItemType> itemset = master.getItem_type();
			ItemDAO.clearJunction("JUNC_ITEM_TYPE", "ITEM_ID", articleId);
			for (ItemLot h : lotset) {
				ItemDAO.delete(h);
			}
			for (ItemType h : itemset) {
				ItemDAO.delete(h);
			}
			ItemDAO.delete(target);

			Set<History> history = master.getItemHistory();
			for (History h : history) {
				ItemDAO.delete(h);
			}

			mav.setViewName("safetyItem.do?action=listItem");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'safetyItem.do?action=listItem'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView deleteItemHistory(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		IDValuePair target = new SimpleItemHistory1();
		long articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			target.setId(articleId);
			SimpleItemHistory1 history = (SimpleItemHistory1) ItemDAO.read(SimpleItemHistory1.class, articleId);
			SimpleJuncItem sji = new SimpleJuncItem();
			sji.setHistory_id((new Long(articleId)).intValue());
			ItemDAO.clearJunction("JUNC_SGI_ITEM_HISTORY", "HISTORY_ID", articleId);
			ItemDAO.delete(target);
			mav.addObject("message", "1");
			mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'safetyItem.do?action=listItem'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView deleteItemType(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int articleId = Function.nullChk(request.getParameter("articleId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			SimpleItemType target = new SimpleItemType();
			SimpleJuncItemType sji = new SimpleJuncItemType();
			target.setId(articleId);
			ItemDAO.clearJunction("JUNC_ITEM_TYPE", "TYPE_ID", articleId);
			ItemDAO.delete(target);
			mav.addObject("message", "1");
			mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'safetyItem.do?action=listItem'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView listItemPopup(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
		SimpleItem1 si = new SimpleItem1();
		List dl = null;
		Method methods[] = SimpleItem1.class.getDeclaredMethods();
		long total = 0L;
		if (!"".equals(searchColumn)) {
			if (searchColumn.equals("setEntp_name")) {
				dl = ItemDAO.listEntpItem(pg, 10, searchKeyword);
				total = ItemDAO.listEntpItemCnt(searchKeyword).longValue();
			} else if (searchColumn.equals("setClass_kor_name")) {
				dl = ItemDAO.listCategoryItem(pg, 10, searchKeyword);
				total = ItemDAO.listCategoryItemCnt(searchKeyword).longValue();
			} else {
				Method amethod[];
				int j = (amethod = methods).length;
				for (int i = 0; i < j; i++) {
					Method m = amethod[i];
					if (m.getName().equals(searchColumn))
						try {
							m.invoke(si, new Object[] { searchKeyword });
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
				}

				dl = ItemDAO.listItem(SimpleItem1.class, pg, 10);
				total = ItemDAO.count(si);
			}
		} else {
			List set2 = ItemDAO.list(si, pg, 10);
			dl = new ArrayList(set2);
			total = ItemDAO.count(si);
		}
		System.out.println((new StringBuilder("searchColumn")).append(searchColumn).toString());
		System.out.println((new StringBuilder("searchKeyword")).append(searchKeyword).toString());
		System.out.println((new StringBuilder("methods")).append(methods).toString());
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg, (new StringBuilder("safetyItem.do?action=listItemPopup&searchKeyword="))
				.append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.setViewName("/view/jsp/properties/item/listItemPopup1.jsp");
		return mav;
	}

	public ModelAndView listCompanyPop(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_05.jpg";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
		SimpleCompany1 sc = new SimpleCompany1();
		if (searchColumn.equals("setCob_flag_code"))
			if (searchKeyword.equals("\uC81C\uC870\uC5C5") || searchKeyword.equals("\uC81C\uC870") || searchKeyword.equals("\uC81C")
					|| searchKeyword.equals("\uC870") || searchKeyword.equals("\uC870\uC5C5"))
				searchKeyword = "1";
			else if (searchKeyword.equals("\uC218\uC785\uC5C5") || searchKeyword.equals("\uC218\uC785") || searchKeyword.equals("\uC218")
					|| searchKeyword.equals("\uC785") || searchKeyword.equals("\uC785\uC5C5"))
				searchKeyword = "2";
		if (!"".equals(searchColumn)) {
			Method methods[] = SimpleCompany1.class.getDeclaredMethods();
			Method amethod[];
			int j = (amethod = methods).length;
			for (int i = 0; i < j; i++) {
				Method m = amethod[i];
				if (m.getName().equals(searchColumn))
					try {
						m.invoke(sc, new Object[] { searchKeyword });
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
			}

			System.out.println((new StringBuilder("searchColumn")).append(searchColumn).toString());
			System.out.println((new StringBuilder("searchKeyword")).append(searchKeyword).toString());
			System.out.println((new StringBuilder("methods")).append(methods).toString());
		}
		List dl = ItemDAO.list(SimpleCompany1.class, pg, 10);
		long total = ItemDAO.count(sc);
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg, (new StringBuilder("safetyItem.do?action=listCompanyPop&searchKeyword="))
				.append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
		List set2 = ItemDAO.list(sc, pg, 10);
		dl = new ArrayList(set2);
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.setViewName("/view/jsp/properties/company/companyListPop1.jsp");
		return mav;
	}

	public ModelAndView listItemCategoryPop(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		int codeAge = Function.nullChk(request.getParameter("codeAge"), 0);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
		SimpleItemCategory target = new SimpleItemCategory();
		target.setCodeAge(Integer.valueOf(codeAge));
		try {
			Method method = SimpleItemCategory.class.getDeclaredMethod(searchColumn, String.class);
			method.invoke(target, new Object[] { searchKeyword });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Page page = new Page();
		String pageString = "";
		List dl = null;
		List itemLevelList = ItemDAO.list(SimpleItemCategoryLevel.class);
		List itemCodeTypes = ItemDAO.list(SimpleItemCategoryCodeType.class);
		long total = ItemDAO.count(target);
		long top = total - (long) ((pg - 1) * 10);
		pageString = page.pageList((int) total, 10, pg, "safetyItem.do?action=listItemCategoryPop", "");
		dl = ItemDAO.list(target, pg, 10);
		mav.addObject("itemLevelList", itemLevelList);
		mav.addObject("itemCodeTypes", itemCodeTypes);
		mav.addObject("list", dl);
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", "view/style/images/title/sub03_01.jpg");
		mav.setViewName("/view/jsp/properties/category/itemListPop1.jsp");
		return mav;
	}

	public ModelAndView ProductChk(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		PrintWriter out = response.getWriter();
		String meddev_item_no = Function.nullChk(request.getParameter("meddev_item_no"), "");
		System.out.println((new StringBuilder("company.meddev_item_no      ==")).append(meddev_item_no).toString());
		long total = ItemDAO.countProduct(meddev_item_no);
		System.out.println((new StringBuilder("company.total      ==")).append(total).toString());
		out.println(total);
		out.close();
		return mav;
	}

	public ModelAndView listItemPop(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_06.jpg";
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
		SimpleItem1 si = new SimpleItem1();
		List dl = null;
		Method methods[] = SimpleItem1.class.getDeclaredMethods();
		long total = 0L;
		if (!"".equals(searchColumn)) {
			if (searchColumn.equals("setEntp_name")) {
				dl = ItemDAO.listEntpItem(pg, 10, searchKeyword);
				total = ItemDAO.listEntpItemCnt(searchKeyword).longValue();
				System.out.println((new StringBuilder("::::::")).append(total).toString());
			} else if (searchColumn.equals("setClass_kor_name")) {
				dl = ItemDAO.listCategoryItem(pg, 10, searchKeyword);
				total = ItemDAO.listCategoryItemCnt(searchKeyword).longValue();
			} else if (searchColumn.equals("setMea_class_no")) {
				dl = ItemDAO.listCategoryItemNo(pg, 10, searchKeyword);
				total = ItemDAO.listCategoryItemCntNo(searchKeyword).longValue();
			} else {
				Method amethod[];
				int j = (amethod = methods).length;
				for (int i = 0; i < j; i++) {
					Method m = amethod[i];
					if (m.getName().equals(searchColumn))
						try {
							m.invoke(si, new Object[] { searchKeyword });
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
				}

				List set2 = ItemDAO.list(si, pg, 10);
				dl = new ArrayList(set2);
				total = ItemDAO.count(si);
			}
		} else {
			List set2 = ItemDAO.list(si, pg, 10);
			if (set2 != null)
				dl = new ArrayList(set2);
			total = ItemDAO.count(si);
		}
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg, (new StringBuilder("safetyItem.do?action=listItemPop&searchKeyword="))
				.append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.setViewName("/view/jsp/risk/importOutput/itemListPop1.jsp");
		return mav;
	}

	private ItemDAO ItemDAO;
}

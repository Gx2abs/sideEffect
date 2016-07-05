// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMatchServiceProvider.java

package properties.match;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;
import properties.PropertiesDAO;
import properties.SimpleIsInUse;
import properties.history.History;
import properties.history.HistoryServiceProvider;
import properties.item.SimpleHistoryType;

// Referenced classes of package properties.match:
//            MatchServiceProvider, SimpleMatchMaster, SimpleMatchHistory, MatchHistory, 
//            SimpleMatchSlave

public class SimpleMatchServiceProvider implements MatchServiceProvider {

	public SimpleMatchServiceProvider() {
	}

	public PropertiesDAO getPropertiesDAO() {
		return propertiesDAO;
	}

	public void setPropertiesDAO(PropertiesDAO propertiesDAO) {
		this.propertiesDAO = propertiesDAO;
	}

	public ModelAndView listMatchingItems(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_09.jpg";
		setDefaultViewSet(mav, request);
		String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
		String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
		int pg = Function.nullChk(request.getParameter("pg"), 1);
		SimpleMatchMaster sm = new SimpleMatchMaster();
		if (!"".equals(searchColumn)) {
			Method methods[] = SimpleMatchMaster.class.getDeclaredMethods();
			Method amethod[];
			int j = (amethod = methods).length;
			for (int i = 0; i < j; i++) {
				Method m = amethod[i];
				if (m.getName().equals(searchColumn))
					try {
						m.invoke(sm, new Object[] { searchKeyword });
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
			}

		}
		java.util.List dl = propertiesDAO.list(sm, pg, 10);
		long total = propertiesDAO.count(sm);
		long top = total - (long) ((pg - 1) * 10);
		Page page = new Page();
		String pageString = page.pageList((int) total, 10, pg, (new StringBuilder("properties.do?action=listMatchingItems&searchKeyword="))
				.append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
		mav.addObject("top", Long.valueOf(top));
		mav.addObject("total", Long.valueOf(total));
		mav.addObject("list", dl);
		mav.addObject("pageString", pageString);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_09.jpg");
		mav.addObject("contentName", "/view/jsp/properties/matchingItemCodes/listMatchingItems1.jsp");
		return mav;
	}

	public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request) {
		mav.setViewName("/view/jsp/template/defaultView.jsp");
		mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
		mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
		mav.addObject("leftName", "/view/jsp/properties/propertiesLeft.jsp");
		mav.addObject("rightName", request.getAttribute("rightName"));
		return mav;
	}

	public ModelAndView readMatch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_09.jpg";
		int articleId = Function.nullChk(request.getParameter("articleId"), -1);
		SimpleMatchMaster target = new SimpleMatchMaster();
		target.setId(articleId);
		abstraction.IDValuePair found = propertiesDAO.read(SimpleMatchMaster.class, (new Long(articleId)).longValue());
		java.util.List historyType = propertiesDAO.list(SimpleHistoryType.class);
		java.util.List isInUse = propertiesDAO.list(SimpleIsInUse.class);
		java.util.List dl = null;
		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_09.jpg");
		mav.addObject("contentName", "/view/jsp/properties/matchingItemCodes/readMatchingItem1.jsp");
		return mav;
	}

	public ModelAndView updateMatchPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		String titleImg = "view/style/images/title/sub03_09.jpg";
		int articleId = Function.nullChk(request.getParameter("articleId"), -1);
		SimpleMatchMaster target = new SimpleMatchMaster();
		target.setId(articleId);
		abstraction.IDValuePair found = propertiesDAO.read(SimpleMatchMaster.class, (new Long(articleId)).longValue());
		java.util.List historyType = propertiesDAO.list(SimpleHistoryType.class);
		java.util.List isInUse = propertiesDAO.list(SimpleIsInUse.class);
		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("article", found);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_09.jpg");
		mav.addObject("contentName", "/view/jsp/properties/matchingItemCodes/updateMatchingItem1.jsp");
		return mav;
	}

	public ModelAndView updateMatch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		int masterId = Function.nullChk(request.getParameter("masterId"), -1);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			if (masterId > -1) {
				System.out.println(
						(new StringBuilder("request.getParameter(\"lastModified\")")).append(request.getParameter("lastModified")).toString());
				String masterCode = Function.nullChk(request.getParameter("masterCode"));
				String masterName = Function.nullChk(request.getParameter("masterName"));
				int masterGrade = Function.nullChk(request.getParameter("masterGrade"), -1);
				int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
				int master_category_id = Function.nullChk(request.getParameter("masterId"), 0);
				SimpleMatchMaster newMatch = new SimpleMatchMaster();
				newMatch = (SimpleMatchMaster) propertiesDAO.read(SimpleMatchMaster.class, masterId);
				newMatch.setMasterCode(masterCode);
				newMatch.setMasterGrade(masterGrade);
				newMatch.setMasterName(masterName);
				newMatch.setMaster_category_id(master_category_id);
				newMatch.setIsInUse(isInUse);
				String slaveCodes[] = request.getParameterValues("slaveCode");
				String slaveGrades[] = request.getParameterValues("slaveGrade");
				String slaveIds[] = request.getParameterValues("slaveId");
				String slave_category_id[] = request.getParameterValues("slaveCategoryId");
				System.out.println((new StringBuilder("slaveCategoryId")).append(slave_category_id).toString());
				if (slaveCodes != null && slaveGrades != null) {
					System.out.println((new StringBuilder("slaveCodes.length :=  ")).append(slaveCodes.length).toString());
					System.out.println((new StringBuilder("slaveGrades.length :=  ")).append(slaveGrades.length).toString());
				}
				boolean isValid = checkLength(new String[][] { slaveCodes, slaveGrades });
				System.out.println((new StringBuilder("valid ? ")).append(isValid).toString());
				History childObject = historyServiceProvider.buildOrEmpty(request, SimpleMatchHistory.class);
				if (childObject != null) {
					if (childObject instanceof MatchHistory) {
						MatchHistory newHistory = (MatchHistory) childObject;
						newMatch.getMatchHistory().add(newHistory);
					}
				} else {
					System.out.println("Null history ! ");
				}
				Set slaveList = new HashSet();
				for (int i = 0; i < slaveCodes.length; i++)
					try {
						SimpleMatchSlave slave = new SimpleMatchSlave();
						slave.setSlaveCode(slaveCodes[i]);
						slave.setSlaveGrade(Integer.parseInt(slaveGrades[i]));
						slave.setSlave_category_id(Integer.parseInt(slave_category_id[i]));
						slaveList.add(slave);
					} catch (Exception e) {
						e.printStackTrace();
					}

				newMatch.setMatchSlaves(slaveList);
				propertiesDAO.update(newMatch);
				if (isValid) {
					for (int i = 0; i < slaveCodes.length; i++) {
						System.out.println((new StringBuilder("slaveCodes.length")).append(slaveCodes.length).toString());
						try {
							SimpleMatchSlave slave = new SimpleMatchSlave();
							slave.setSlaveCode(slaveCodes[i]);
							slave.setSlaveGrade(Integer.parseInt(slaveGrades[i]));
							slave.setSlave_category_id(Integer.parseInt(slave_category_id[i]));
							slaveList.add(slave);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					mav.addObject("message", "successfully created");
				}
			} else {
				mav.addObject("message", "invalid data ! ");
			}
			mav.setViewName((new StringBuilder("properties.do?action=readMatch&menu=9&articleId=")).append(masterId).toString());
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'properties.do?action=listMatchingItems'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView createMatchPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String titleImg = "view/style/images/title/sub03_09.jpg";
		java.util.List historyType = propertiesDAO.list(SimpleHistoryType.class);
		java.util.List isInUse = propertiesDAO.list(SimpleIsInUse.class);
		mav.addObject("isInUse", isInUse);
		mav.addObject("historyType", historyType);
		mav.addObject("titleImg", titleImg);
		mav.addObject("titleImage", "view/style/images/title/sign/top_img3_09.jpg");
		setDefaultViewSet(mav, request);
		mav.addObject("contentName", "/view/jsp/properties/matchingItemCodes/createMatchingItem1.jsp");
		return mav;
	}

	public ModelAndView createMatch(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[SimpleMatchServiceProvider].createMatch()");
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
		String masterCode = Function.nullChk(request.getParameter("masterCode"));
		String masterName = Function.nullChk(request.getParameter("masterName"));
		int masterGrade = Function.nullChk(request.getParameter("masterGrade"), 0);
		int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
		int master_category_id = Function.nullChk(request.getParameter("masterId"), 0);
		int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (privilegeId > 1) {
			SimpleMatchMaster newMatch = new SimpleMatchMaster();
			newMatch.setMasterCode(masterCode);
			newMatch.setMasterGrade(masterGrade);
			newMatch.setMasterName(masterName);
			newMatch.setMaster_category_id(master_category_id);
			newMatch.setIsInUse(isInUse);
			String slaveCodes[] = request.getParameterValues("slaveCode");
			String slaveGrades[] = request.getParameterValues("slaveGrade");
			String slave_category_id[] = request.getParameterValues("slaveCategoryId");
			if (slaveCodes != null && slaveGrades != null) {
				System.out.println((new StringBuilder("slaveCodes.length :=  ")).append(slaveCodes.length).toString());
				System.out.println((new StringBuilder("slaveGrades.length :=  ")).append(slaveGrades.length).toString());
			}
			boolean isValid = checkLength(new String[][] { slaveCodes, slaveGrades });
			System.out.println((new StringBuilder("valid ? ")).append(isValid).toString());
			if (isValid) {
				History childObject = historyServiceProvider.buildOrEmpty(request, SimpleMatchHistory.class);
				if (childObject != null) {
					System.out.println("createMatch.history not null");
					if (childObject instanceof MatchHistory) {
						MatchHistory newHistory = (MatchHistory) childObject;
						Set newHistorySet = new HashSet();
						newHistorySet.add(newHistory);
						newMatch.setMatchHistory(newHistorySet);
					}
				} else {
					System.out.println("createMatch.history is null");
				}
				Set slaveList = new HashSet();
				for (int i = 0; i < slaveCodes.length; i++)
					try {
						SimpleMatchSlave slave = new SimpleMatchSlave();
						slave.setSlaveCode(slaveCodes[i]);
						slave.setSlaveGrade(Integer.parseInt(slaveGrades[i]));
						slave.setSlave_category_id(Integer.parseInt(slave_category_id[i]));
						slaveList.add(slave);
					} catch (Exception e) {
						e.printStackTrace();
					}

				newMatch.setMasterGrade(masterGrade);
				newMatch.setMatchSlaves(slaveList);
				propertiesDAO.create(newMatch);
				mav.addObject("message", "successfully created");
			} else {
				mav.addObject("message", "invalid data ! ");
			}
			mav.addObject("returnUrl", "properties.do?action=listMatchingItems");
			mav.setViewName("properties.do?action=listMatchingItems&menu=9");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'properties.do?action=listMatchingItems'");
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

	public HistoryServiceProvider getHistoryServiceProvider() {
		return historyServiceProvider;
	}

	public void setHistoryServiceProvider(HistoryServiceProvider historyServiceProvider) {
		this.historyServiceProvider = historyServiceProvider;
	}

	public ModelAndView deleteMatch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		setDefaultViewSet(mav, request);
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
			SimpleMatchMaster target = new SimpleMatchMaster();
			target.setId(articleId);
			SimpleMatchMaster found = (SimpleMatchMaster) propertiesDAO.read(SimpleMatchMaster.class, articleId);
			if (found != null && found.getMatchHistory() != null) {
				MatchHistory mh;
				for (Iterator iterator = found.getMatchHistory().iterator(); iterator.hasNext(); propertiesDAO.delete(mh)) {
					mh = (MatchHistory) iterator.next();
					propertiesDAO.clearJunction("JNC_MATCH_HISTORY", "MASTER_ID", articleId);
				}

			}
			if (found != null && found.getMatchSlaves() != null) {
				SimpleMatchSlave ms;
				for (Iterator iterator1 = found.getMatchSlaves().iterator(); iterator1.hasNext(); propertiesDAO.delete(ms)) {
					ms = (SimpleMatchSlave) iterator1.next();
					propertiesDAO.clearJunction("JNC_MATCH_MASTER_SLAVE", "MASTER_ID", articleId);
				}

			}
			propertiesDAO.delete(target);
			mav.setViewName("properties.do?action=listMatchingItems&menu=9");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'properties.do?action=listMatchingItems'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView deleteSlave(HttpServletRequest request, HttpServletResponse response) {
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
			SimpleMatchSlave target = new SimpleMatchSlave();
			target.setId(articleId);
			propertiesDAO.clearJunction("JNC_MATCH_MASTER_SLAVE", "SLAVE_ID", articleId);
			propertiesDAO.delete(target);
			mav.addObject("message", "1");
			mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'properties.do?action=listMatchingItems'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	public ModelAndView deleteHistory(HttpServletRequest request, HttpServletResponse response) {
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
			SimpleMatchHistory target = new SimpleMatchHistory();
			target.setId(articleId);
			propertiesDAO.clearJunction("JNC_MATCH_HISTORY", "HISTORY_ID", articleId);
			propertiesDAO.delete(target);
			mav.addObject("message", "1");
			mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
		} else {
			out.println("<script language=javascript>");
			out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
			out.println("location.href = 'properties.do?action=listMatchingItems'");
			out.println("</script>");
			return null;
		}
		return mav;
	}

	private PropertiesDAO propertiesDAO;
	private HistoryServiceProvider historyServiceProvider;
}

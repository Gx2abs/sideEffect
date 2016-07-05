// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryGroupServiceProvider.java

package risk.category;

import abstraction.IDValuePair;
import com.tobesoft.xplatform.data.*;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
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
import risk.category.possibility.Possibility;
import risk.category.possibility.PossibilityCompositeKey;
import risk.category.possibility.SimpleJuncPossibility;
import risk.category.possibility.SimplePossibility;
import risk.category.risk.Risk;
import risk.category.risk.RiskCompositeKey;
import risk.category.risk.SimpleJuncRisk;
import risk.category.risk.SimpleRisk;
import safety.renewal.sgi.category.*;
import sideeffect.SimpleSideeffectResultHistory;

// Referenced classes of package risk.category:
//            ItemCategoryGroupServiceProvider, SimpleItemCategoryGroupHistory, SimpleItemCategoryGroup, ItemCategoryGroupDAO, 
//            SimpleItemCategoryGroupType, SimpleItemCategoryGroupBack, SimpleJuncItemCategoryGroupHistory, SimpleJuncItemCategoryGroup

public class SimpleItemCategoryGroupServiceProvider
    implements ItemCategoryGroupServiceProvider
{

    public SimpleItemCategoryGroupServiceProvider()
    {
    }

    public ItemCategoryGroupDAO getItemCategoryGroupDAO()
    {
        return itemCategoryGroupDAO;
    }

    public void setItemCategoryGroupDAO(ItemCategoryGroupDAO itemCategoryGroupDAO)
    {
        this.itemCategoryGroupDAO = itemCategoryGroupDAO;
    }

    public History buildOrEmpty(HttpServletRequest request, Class class1)
    {
        SimpleItemCategoryGroupHistory history = null;
        try
        {
            if(validate(request))
            {
                String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
                String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                int historyType = Function.nullChk(request.getParameter("historyType"), 0);
                String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = new SimpleItemCategoryGroupHistory();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date lm = new Date();
                Date af = new Date();
                af = sdf.parse(activeFrom);
                history.setLastModified(lm);
                history.setActiveFrom(af);
                history.setManager(historyManager);
                history.setHistoryDescription(historyDescription);
                history.setHistoryType(historyType);
            } else
            {
                System.out.println("empty history !");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return history;
    }

    public boolean validate(HttpServletRequest request)
    {
        boolean result = false;
        try
        {
            String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
            String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
            int historyType = Function.nullChk(request.getParameter("historyType"), 0);
            String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
            if("".equals(activeFrom) || historyType == 0)
            {
                System.out.println((new StringBuilder("activeFrom : ")).append(activeFrom).toString());
                System.out.println((new StringBuilder("historyType : ")).append(historyType).toString());
            } else
            {
                result = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/properties/propertiesLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        mav.addObject("titleImg", "view/style/images/title/sub03_11.gif");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_11.gif");
        return mav;
    }

    public ModelAndView listItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        SimpleItemCategoryGroup si = new SimpleItemCategoryGroup();
        String name = request.getParameter("searchKeyword") != "" ? request.getParameter("searchKeyword") : null;
        si.setPropertyValue(name);
        List dl = itemCategoryGroupDAO.list(si, pg, 10);
        long total = itemCategoryGroupDAO.count(si);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "risk.category.do?action=listItemCategoryGroup", "", request);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", dl);
        mav.addObject("pageString", pageString);
        mav.addObject("contentName", "/view/jsp/risk/properties/itemCategoryGroupList1.jsp");
        return mav;
    }

    public ModelAndView createItemCategoryGroupPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        List historyType = itemCategoryGroupDAO.list(SimpleHistoryType.class);
        List isInUse = itemCategoryGroupDAO.list(SimpleIsInUse.class);
        List typeStatus = itemCategoryGroupDAO.list(SimpleItemCategoryGroupType.class);
        if(itemCategoryGroupDAO.chechkType(SimpleItemCategoryGroup.class))
            mav.addObject("checkType", Integer.valueOf(1));
        mav.addObject("isInUse", isInUse);
        mav.addObject("historyType", historyType);
        mav.addObject("typeStatus", typeStatus);
        mav.addObject("contentName", "/view/jsp/risk/properties/createItemCategoryGroup1.jsp");
        return mav;
    }

    public ModelAndView createItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        int type = Function.nullChk(request.getParameter("type"), 0);
        String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
        String revisionName = Function.nullChk(request.getParameter("revisionName"), "");
        String startDate = Function.nullChk(request.getParameter("startDate"), "");
        String pvalue[] = request.getParameterValues("pvalue[]");
        String pclassName[] = request.getParameterValues("pclassName[]");
        String likelihoodFm[] = request.getParameterValues("likelihoodFm[]");
        String likelihoodTo[] = request.getParameterValues("likelihoodTo[]");
        String rvalue[] = request.getParameterValues("rvalue[]");
        String riskGradeFm[] = request.getParameterValues("riskGradeFm[]");
        String riskGradeTo[] = request.getParameterValues("riskGradeTo[]");
        String rclassName[] = request.getParameterValues("rclassName[]");
        String correctiveMeasure[] = request.getParameterValues("correctiveMeasure[]");
        String color[] = request.getParameterValues("color[]");
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sd = new Date();
        try
        {
            sd = sdf.parse((new StringBuilder(String.valueOf(startDate))).append("-01").toString());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        if(privilegeId > 1)
        {
            SimpleItemCategoryGroup insertTarget = new SimpleItemCategoryGroup();
            insertTarget.setType(type);
            insertTarget.setPropertyValue(propertyValue);
            insertTarget.setIsInUse(isInUse);
            insertTarget.setLastModified(new Date());
            SimpleItemCategoryGroup found = (SimpleItemCategoryGroup)itemCategoryGroupDAO.create(insertTarget);
            SimpleItemCategoryGroupBack sib = new SimpleItemCategoryGroupBack();
            sib.setRevisionName(revisionName);
            sib.setStartDate(sd);
            sib.setGroup_id(found.getId());
            SimpleItemCategoryGroupBack bfound = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.create(sib);
            SimplePossibility sp = new SimplePossibility();
            SimpleJuncPossibility sjp = new SimpleJuncPossibility();
            PossibilityCompositeKey pcompositeKey = new PossibilityCompositeKey();
            for(int i = 0; i < pvalue.length; i++)
            {
                sp.setValue(Integer.parseInt(pvalue[i]));
                sp.setClassName(pclassName[i]);
                sp.setLikelihoodFm(Float.valueOf(likelihoodFm[i]).floatValue());
                sp.setLikelihoodTo(Float.valueOf(likelihoodTo[i]).floatValue());
                SimplePossibility pfound = (SimplePossibility)itemCategoryGroupDAO.create(sp);
                pcompositeKey.setGroup_id(bfound.getId());
                pcompositeKey.setPossibility_id(pfound.getId());
                sjp.setCompositeKey(pcompositeKey);
                itemCategoryGroupDAO.create(sjp);
            }

            SimpleRisk sr = new SimpleRisk();
            SimpleJuncRisk sjr = new SimpleJuncRisk();
            RiskCompositeKey rcompositeKey = new RiskCompositeKey();
            for(int i = 0; i < rvalue.length; i++)
            {
                sr.setValue(Integer.parseInt(rvalue[i]));
                sr.setClassName(rclassName[i]);
                sr.setRiskGradeFm(Float.valueOf(riskGradeFm[i]).floatValue());
                sr.setRiskGradeTo(Float.valueOf(riskGradeTo[i]).floatValue());
                sr.setCorrectiveMeasure(correctiveMeasure[i]);
                sr.setColor(color[i]);
                SimpleRisk rfound = (SimpleRisk)itemCategoryGroupDAO.create(sr);
                rcompositeKey.setGroup_id(bfound.getId());
                rcompositeKey.setRisk_id(rfound.getId());
                sjr.setCompositeKey(rcompositeKey);
                itemCategoryGroupDAO.create(sjr);
            }

            History childObject = buildOrEmpty(request, SimpleItemCategoryGroupHistory.class);
            if(childObject != null)
            {
                itemCategoryGroupDAO.create(childObject);
                found.getItemCategoryGroupHistory().add(childObject);
                itemCategoryGroupDAO.update(found);
            }
            mav.addObject("returnUrl", "risk.category.do?action=listItemCategoryGroup");
            mav.setViewName("risk.category.do?action=listItemCategoryGroup");
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView readItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        List historyType = itemCategoryGroupDAO.list(SimpleHistoryType.class);
        IDValuePair found = itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, articleId);
        SimpleItemCategoryGroupBack sib = new SimpleItemCategoryGroupBack();
        List backupList = itemCategoryGroupDAO.backupList(sib, articleId);
        SimpleItemCategoryGroupBack backup = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, ((SimpleItemCategoryGroupBack)backupList.get(0)).getId());
        backupList.remove(0);
        mav.addObject("historyType", historyType);
        mav.addObject("backup", backup);
        mav.addObject("backupList", backupList);
        mav.addObject("article", found);
        mav.addObject("contentName", "/view/jsp/risk/properties/readItemCategoryGroup1.jsp");
        return mav;
    }

    public ModelAndView updateItemCategoryGroupPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        IDValuePair found = itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, articleId);
        SimpleItemCategoryGroupBack sib = new SimpleItemCategoryGroupBack();
        List backupList = itemCategoryGroupDAO.backupList(sib, articleId);
        SimpleItemCategoryGroupBack backup = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, ((SimpleItemCategoryGroupBack)backupList.get(0)).getId());
        List historyType = itemCategoryGroupDAO.list(SimpleHistoryType.class);
        List isInUse = itemCategoryGroupDAO.list(SimpleIsInUse.class);
        mav.addObject("isInUse", isInUse);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("backup", backup);
        mav.addObject("contentName", "/view/jsp/risk/properties/updateItemCategoryGroup1.jsp");
        return mav;
    }

    public ModelAndView updateItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        long backupId = Function.nullChk(request.getParameter("backupId"), -1);
        int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
        String revisionName = Function.nullChk(request.getParameter("revisionName"), "");
        String startDate = Function.nullChk(request.getParameter("startDate"), "");
        String old_possibilityId[] = request.getParameterValues("old_possibilityId[]");
        String old_pvalue[] = request.getParameterValues("old_pvalue[]");
        String old_pclassName[] = request.getParameterValues("old_pclassName[]");
        String old_likelihoodFm[] = request.getParameterValues("old_likelihoodFm[]");
        String old_likelihoodTo[] = request.getParameterValues("old_likelihoodTo[]");
        String old_pvalueDeleteChk[] = request.getParameterValues("old_pvalueDeleteChk[]");
        String pvalue[] = request.getParameterValues("pvalue[]");
        String pclassName[] = request.getParameterValues("pclassName[]");
        String likelihoodFm[] = request.getParameterValues("likelihoodFm[]");
        String likelihoodTo[] = request.getParameterValues("likelihoodTo[]");
        String old_riskId[] = request.getParameterValues("old_riskId[]");
        String old_rvalue[] = request.getParameterValues("old_rvalue[]");
        String old_riskGradeFm[] = request.getParameterValues("old_riskGradeFm[]");
        String old_riskGradeTo[] = request.getParameterValues("old_riskGradeTo[]");
        String old_rclassName[] = request.getParameterValues("old_rclassName[]");
        String old_correctiveMeasure[] = request.getParameterValues("old_correctiveMeasure[]");
        String old_color[] = request.getParameterValues("old_color[]");
        String old_rvalueDeleteChk[] = request.getParameterValues("old_rvalueDeleteChk[]");
        String rvalue[] = request.getParameterValues("rvalue[]");
        String riskGradeFm[] = request.getParameterValues("riskGradeFm[]");
        String riskGradeTo[] = request.getParameterValues("riskGradeTo[]");
        String rclassName[] = request.getParameterValues("rclassName[]");
        String correctiveMeasure[] = request.getParameterValues("correctiveMeasure[]");
        String color[] = request.getParameterValues("color[]");
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sd = new Date();
        try
        {
            sd = sdf.parse((new StringBuilder(String.valueOf(startDate))).append("-01").toString());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        if(privilegeId > 1)
        {
            SimpleItemCategoryGroup found = (SimpleItemCategoryGroup)itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, articleId);
            found.setPropertyValue(propertyValue);
            found.setIsInUse(isInUse);
            found.setLastModified(new Date());
            itemCategoryGroupDAO.update(found);
            SimpleItemCategoryGroupBack sib = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, backupId);
            sib.setGroup_id(articleId);
            sib.setRevisionName(revisionName);
            sib.setStartDate(sd);
            itemCategoryGroupDAO.update(sib);
            SimplePossibility sp = new SimplePossibility();
            SimpleJuncPossibility sjp = new SimpleJuncPossibility();
            PossibilityCompositeKey pcompositeKey = new PossibilityCompositeKey();
            for(int i = 0; i < old_possibilityId.length; i++)
            {
                sp = (SimplePossibility)itemCategoryGroupDAO.read(SimplePossibility.class, Integer.parseInt(old_possibilityId[i]));
                if(old_pvalueDeleteChk[i].isEmpty())
                {
                    sp.setValue(Integer.parseInt(old_pvalue[i]));
                    sp.setClassName(old_pclassName[i]);
                    sp.setLikelihoodFm(Float.valueOf(old_likelihoodFm[i]).floatValue());
                    sp.setLikelihoodTo(Float.valueOf(old_likelihoodTo[i]).floatValue());
                    itemCategoryGroupDAO.update(sp);
                } else
                {
                    pcompositeKey.setGroup_id(backupId);
                    pcompositeKey.setPossibility_id(sp.getId());
                    sjp.setCompositeKey(pcompositeKey);
                    itemCategoryGroupDAO.delete(sjp);
                    itemCategoryGroupDAO.delete(sp);
                }
            }

            if(pvalue != null)
            {
                for(int i = 0; i < pvalue.length; i++)
                {
                    sp.setValue(Integer.parseInt(pvalue[i]));
                    sp.setClassName(pclassName[i]);
                    sp.setLikelihoodFm(Float.valueOf(likelihoodFm[i]).floatValue());
                    sp.setLikelihoodTo(Float.valueOf(likelihoodTo[i]).floatValue());
                    SimplePossibility pfound = (SimplePossibility)itemCategoryGroupDAO.create(sp);
                    pcompositeKey.setGroup_id(backupId);
                    pcompositeKey.setPossibility_id(pfound.getId());
                    sjp.setCompositeKey(pcompositeKey);
                    itemCategoryGroupDAO.create(sjp);
                }

            }
            SimpleRisk sr = new SimpleRisk();
            SimpleJuncRisk sjr = new SimpleJuncRisk();
            RiskCompositeKey rcompositeKey = new RiskCompositeKey();
            for(int i = 0; i < old_riskId.length; i++)
            {
                sr = (SimpleRisk)itemCategoryGroupDAO.read(SimpleRisk.class, Integer.parseInt(old_riskId[i]));
                if(old_rvalueDeleteChk[i].isEmpty())
                {
                    sr.setValue(Integer.parseInt(old_rvalue[i]));
                    sr.setClassName(old_rclassName[i]);
                    sr.setRiskGradeFm(Float.valueOf(old_riskGradeFm[i]).floatValue());
                    sr.setRiskGradeTo(Float.valueOf(old_riskGradeTo[i]).floatValue());
                    sr.setCorrectiveMeasure(old_correctiveMeasure[i]);
                    sr.setColor(old_color[i]);
                    itemCategoryGroupDAO.update(sr);
                } else
                {
                    rcompositeKey.setGroup_id(backupId);
                    rcompositeKey.setRisk_id(sr.getId());
                    sjp.setCompositeKey(pcompositeKey);
                    itemCategoryGroupDAO.delete(sjr);
                    itemCategoryGroupDAO.delete(sr);
                }
            }

            if(rvalue != null)
            {
                for(int i = 0; i < rvalue.length; i++)
                {
                    sr.setValue(Integer.parseInt(rvalue[i]));
                    sr.setClassName(rclassName[i]);
                    sr.setRiskGradeFm(Float.valueOf(riskGradeFm[i]).floatValue());
                    sr.setRiskGradeTo(Float.valueOf(riskGradeTo[i]).floatValue());
                    sr.setCorrectiveMeasure(correctiveMeasure[i]);
                    sr.setColor(color[i]);
                    SimpleRisk rfound = (SimpleRisk)itemCategoryGroupDAO.create(sr);
                    rcompositeKey.setGroup_id(backupId);
                    rcompositeKey.setRisk_id(rfound.getId());
                    sjr.setCompositeKey(rcompositeKey);
                    itemCategoryGroupDAO.create(sjr);
                }

            }
            History childObject = buildOrEmpty(request, SimpleItemCategoryGroupHistory.class);
            if(childObject != null)
            {
                itemCategoryGroupDAO.create(childObject);
                found.getItemCategoryGroupHistory().add(childObject);
                itemCategoryGroupDAO.update(found);
            }
            mav.addObject("returnUrl", "risk.category.do?action=listItemCategoryGroup");
            mav.setViewName((new StringBuilder("risk.category.do?action=readItemCategoryGroup&menu=11&articleId=")).append(articleId).toString());
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView deleteItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        IDValuePair target = new SimpleItemCategoryGroup();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        if(privilegeId > 1)
        {
            target.setId(articleId);
            SimpleItemCategoryGroup read = (SimpleItemCategoryGroup)itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, articleId);
            Set history = read.getItemCategoryGroupHistory();
            History h;
            for(Iterator iterator = history.iterator(); iterator.hasNext(); itemCategoryGroupDAO.delete(h))
            {
                h = (History)iterator.next();
                itemCategoryGroupDAO.clearJunction("JUNC_ITEM_CATEGORY_GROUP_HISTORY", "GROUP_ID", articleId);
            }

            SimpleItemCategoryGroupBack back = new SimpleItemCategoryGroupBack();
            List listBack = itemCategoryGroupDAO.backupList(back, articleId);
            if(listBack.size() > 0)
            {
                for(int i = 0; i < listBack.size(); i++)
                {
                    SimpleItemCategoryGroupBack readBack = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, ((SimpleItemCategoryGroupBack)listBack.get(i)).getId());
                    List possibilityB = readBack.getPossibility();
                    Possibility pb;
                    for(Iterator iterator1 = possibilityB.iterator(); iterator1.hasNext(); itemCategoryGroupDAO.delete(pb))
                    {
                        pb = (Possibility)iterator1.next();
                        itemCategoryGroupDAO.clearJunction("JUNC_POSSIBILITY_CLASS", "GROUP_ID", readBack.getId());
                    }

                    List riskB = readBack.getRisk();
                    Risk rb;
                    for(Iterator iterator2 = riskB.iterator(); iterator2.hasNext(); itemCategoryGroupDAO.delete(rb))
                    {
                        rb = (Risk)iterator2.next();
                        itemCategoryGroupDAO.clearJunction("JUNC_RISK_CLASS", "GROUP_ID", readBack.getId());
                    }

                    back.setId(readBack.getId());
                    itemCategoryGroupDAO.delete(back);
                }

                itemCategoryGroupDAO.delete(target);
            }
            mav.addObject("returnUrl", "risk.category.do?action=listItemCategoryGroup");
            mav.setViewName("risk.category.do?action=listItemCategoryGroup");
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView deleteItemCategoryGroupHistory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        if(privilegeId > 1)
        {
            SimpleJuncItemCategoryGroupHistory sji = new SimpleJuncItemCategoryGroupHistory();
            IDValuePair target = new SimpleSideeffectResultHistory();
            sji.setHistory_id((new Long(articleId)).intValue());
            itemCategoryGroupDAO.clearJunction("JUNC_ITEM_CATEGORY_GROUP_HISTORY", "HISTORY_ID", articleId);
            itemCategoryGroupDAO.delete(target);
            mav.addObject("message", "1");
            mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView ItemCategoryGroupPopup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "setClass_kor_name");
        String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
        SimpleItemCategory target = new SimpleItemCategory();
        if(!"".equals(searchColumn))
        {
            Method methods[] = SimpleItemCategory.class.getDeclaredMethods();
            Method amethod[];
            int j = (amethod = methods).length;
            for(int i = 0; i < j; i++)
            {
                Method m = amethod[i];
                if(m.getName().equals(searchColumn))
                    try
                    {
                        m.invoke(target, new Object[] {
                            searchKeyword
                        });
                    }
                    catch(IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch(IllegalArgumentException e)
                    {
                        e.printStackTrace();
                    }
                    catch(InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
            }

        }
        Page page = new Page();
        String pageString = "";
        setDefaultViewSet(mav, request);
        List dl = null;
        List itemLevelList = itemCategoryGroupDAO.list(SimpleItemCategoryLevel.class);
        List itemCodeTypes = itemCategoryGroupDAO.list(SimpleItemCategoryCodeType.class);
        long total = itemCategoryGroupDAO.count(target);
        long top = total - (long)((pg - 1) * 10);
        pageString = page.pageList((int)total, 10, pg, (new StringBuilder("risk.category.do?action=ItemCategoryGroupPopup&articleId=")).append(articleId).append("&searchKeyword=").append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
        dl = itemCategoryGroupDAO.ItemCateogoryList(SimpleItemCategory.class, pg, 10, searchColumn, searchKeyword);
        mav.addObject("meaClassNoLevel", itemLevelList);
        mav.addObject("itemCodeTypes", itemCodeTypes);
        mav.addObject("list", dl);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.setViewName("/view/jsp/risk/properties/itemCategoryGroupPopup1.jsp");
        return mav;
    }

    public ModelAndView ItemCategoryGroupPopupC(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        long groupId = Function.nullChk(request.getParameter("groupId"), -1);
        String act = Function.nullChk(request.getParameter("act"), "");
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        boolean chk = false;
        if(privilegeId > 1)
        {
            if(articleId > 0L)
            {
                chk = true;
                SimpleJuncItemCategoryGroup found = new SimpleJuncItemCategoryGroup();
                found.setCategory_id(articleId);
                found.setGroup_id(groupId);
                if(act.equals("I"))
                    itemCategoryGroupDAO.create(found);
                else
                if(act.equals("D"))
                    itemCategoryGroupDAO.delete(found);
                else
                    chk = false;
                out.println(chk);
                out.close();
            }
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView revisionItemCategoryGroupPop(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        IDValuePair found = itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, articleId);
        SimpleItemCategoryGroupBack sib = new SimpleItemCategoryGroupBack();
        List backupList = itemCategoryGroupDAO.backupList(sib, articleId);
        SimpleItemCategoryGroupBack backup = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, ((SimpleItemCategoryGroupBack)backupList.get(0)).getId());
        mav.addObject("article", found);
        mav.addObject("backup", backup);
        mav.addObject("contentName", "/view/jsp/risk/properties/revisionItemCategoryGroup1.jsp");
        return mav;
    }

    public ModelAndView revisionItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        String revisionName = Function.nullChk(request.getParameter("revisionName"), "");
        String startDate = Function.nullChk(request.getParameter("startDate"), "");
        String pvalue[] = request.getParameterValues("pvalue[]");
        String pclassName[] = request.getParameterValues("pclassName[]");
        String likelihoodFm[] = request.getParameterValues("likelihoodFm[]");
        String likelihoodTo[] = request.getParameterValues("likelihoodTo[]");
        String rvalue[] = request.getParameterValues("rvalue[]");
        String riskGradeFm[] = request.getParameterValues("riskGradeFm[]");
        String riskGradeTo[] = request.getParameterValues("riskGradeTo[]");
        String rclassName[] = request.getParameterValues("rclassName[]");
        String correctiveMeasure[] = request.getParameterValues("correctiveMeasure[]");
        String color[] = request.getParameterValues("color[]");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sd = new Date();
        try
        {
            sd = sdf.parse((new StringBuilder(String.valueOf(startDate))).append("-01").toString());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        SimpleItemCategoryGroupBack insertTarget = new SimpleItemCategoryGroupBack();
        insertTarget.setRevisionName(revisionName);
        insertTarget.setStartDate(sd);
        insertTarget.setGroup_id(articleId);
        SimpleItemCategoryGroupBack found = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.create(insertTarget);
        SimplePossibility sp = new SimplePossibility();
        SimpleJuncPossibility sjp = new SimpleJuncPossibility();
        PossibilityCompositeKey pcompositeKey = new PossibilityCompositeKey();
        if(pvalue != null)
        {
            for(int i = 0; i < pvalue.length; i++)
            {
                sp.setValue(Integer.parseInt(pvalue[i]));
                sp.setClassName(pclassName[i]);
                sp.setLikelihoodFm(Float.valueOf(likelihoodFm[i]).floatValue());
                sp.setLikelihoodTo(Float.valueOf(likelihoodTo[i]).floatValue());
                SimplePossibility pfound = (SimplePossibility)itemCategoryGroupDAO.create(sp);
                pcompositeKey.setGroup_id(found.getId());
                pcompositeKey.setPossibility_id(pfound.getId());
                sjp.setCompositeKey(pcompositeKey);
                itemCategoryGroupDAO.create(sjp);
            }

        }
        SimpleRisk sr = new SimpleRisk();
        SimpleJuncRisk sjr = new SimpleJuncRisk();
        RiskCompositeKey rcompositeKey = new RiskCompositeKey();
        if(rvalue != null)
        {
            for(int i = 0; i < rvalue.length; i++)
            {
                sr.setValue(Integer.parseInt(rvalue[i]));
                sr.setClassName(rclassName[i]);
                sr.setRiskGradeFm(Float.valueOf(riskGradeFm[i]).floatValue());
                sr.setRiskGradeTo(Float.valueOf(riskGradeTo[i]).floatValue());
                sr.setCorrectiveMeasure(correctiveMeasure[i]);
                sr.setColor(color[i]);
                SimpleRisk rfound = (SimpleRisk)itemCategoryGroupDAO.create(sr);
                rcompositeKey.setGroup_id(found.getId());
                rcompositeKey.setRisk_id(rfound.getId());
                sjr.setCompositeKey(rcompositeKey);
                itemCategoryGroupDAO.create(sjr);
            }

        }
        mav.setViewName((new StringBuilder("risk.category.do?action=readItemCategoryGroup&menu=11&articleId=")).append(insertTarget.getGroup_id()).toString());
        return mav;
    }

    public ModelAndView readItemCategoryGroupBack(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        SimpleItemCategoryGroupBack backup = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, articleId);
        SimpleItemCategoryGroup found = (SimpleItemCategoryGroup)itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, backup.getGroup_id());
        mav.addObject("backup", backup);
        mav.addObject("article", found);
        mav.addObject("titleImg", "view/style/images/title/sub03_11_back.gif");
        mav.setViewName("/view/jsp/risk/properties/readItemCategoryGroupBack1.jsp");
        return mav;
    }

    public ModelAndView updateItemCategoryGroupBackPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        SimpleItemCategoryGroupBack backup = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, articleId);
        SimpleItemCategoryGroup found = (SimpleItemCategoryGroup)itemCategoryGroupDAO.read(SimpleItemCategoryGroup.class, backup.getGroup_id());
        mav.addObject("backup", backup);
        mav.addObject("article", found);
        mav.addObject("titleImg", "view/style/images/title/sub03_11_back.gif");
        mav.setViewName("/view/jsp/risk/properties/updateItemCategoryGroupBack1.jsp");
        return mav;
    }

    public ModelAndView updateItemCategoryGroupBack(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        String revisionName = Function.nullChk(request.getParameter("revisionName"), "");
        String startDate = Function.nullChk(request.getParameter("startDate"), "");
        String old_possibilityId[] = request.getParameterValues("old_possibilityId[]");
        String old_pvalue[] = request.getParameterValues("old_pvalue[]");
        String old_pclassName[] = request.getParameterValues("old_pclassName[]");
        String old_likelihoodFm[] = request.getParameterValues("old_likelihoodFm[]");
        String old_likelihoodTo[] = request.getParameterValues("old_likelihoodTo[]");
        String old_pvalueDeleteChk[] = request.getParameterValues("old_pvalueDeleteChk[]");
        String pvalue[] = request.getParameterValues("pvalue[]");
        String pclassName[] = request.getParameterValues("pclassName[]");
        String likelihoodFm[] = request.getParameterValues("likelihoodFm[]");
        String likelihoodTo[] = request.getParameterValues("likelihoodTo[]");
        String old_riskId[] = request.getParameterValues("old_riskId[]");
        String old_rvalue[] = request.getParameterValues("old_rvalue[]");
        String old_riskGradeFm[] = request.getParameterValues("old_riskGradeFm[]");
        String old_riskGradeTo[] = request.getParameterValues("old_riskGradeTo[]");
        String old_rclassName[] = request.getParameterValues("old_rclassName[]");
        String old_correctiveMeasure[] = request.getParameterValues("old_correctiveMeasure[]");
        String old_color[] = request.getParameterValues("old_color[]");
        String old_rvalueDeleteChk[] = request.getParameterValues("old_rvalueDeleteChk[]");
        String rvalue[] = request.getParameterValues("rvalue[]");
        String riskGradeFm[] = request.getParameterValues("riskGradeFm[]");
        String riskGradeTo[] = request.getParameterValues("riskGradeTo[]");
        String rclassName[] = request.getParameterValues("rclassName[]");
        String correctiveMeasure[] = request.getParameterValues("correctiveMeasure[]");
        String color[] = request.getParameterValues("color[]");
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sd = new Date();
        try
        {
            sd = sdf.parse((new StringBuilder(String.valueOf(startDate))).append("-01").toString());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        if(privilegeId > 1)
        {
            SimpleItemCategoryGroupBack found = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, articleId);
            found.setRevisionName(revisionName);
            found.setStartDate(sd);
            itemCategoryGroupDAO.update(found);
            SimplePossibility sp = new SimplePossibility();
            SimpleJuncPossibility sjp = new SimpleJuncPossibility();
            PossibilityCompositeKey pcompositeKey = new PossibilityCompositeKey();
            for(int i = 0; i < old_possibilityId.length; i++)
            {
                sp = (SimplePossibility)itemCategoryGroupDAO.readBack(SimplePossibility.class, Integer.parseInt(old_possibilityId[i]));
                if(old_pvalueDeleteChk[i].isEmpty())
                {
                    sp.setValue(Integer.parseInt(old_pvalue[i]));
                    sp.setClassName(old_pclassName[i]);
                    sp.setLikelihoodFm(Float.valueOf(old_likelihoodFm[i]).floatValue());
                    sp.setLikelihoodTo(Float.valueOf(old_likelihoodTo[i]).floatValue());
                    itemCategoryGroupDAO.update(sp);
                } else
                {
                    pcompositeKey.setGroup_id(found.getId());
                    pcompositeKey.setPossibility_id(sp.getId());
                    sjp.setCompositeKey(pcompositeKey);
                    itemCategoryGroupDAO.delete(sjp);
                    itemCategoryGroupDAO.delete(sp);
                }
            }

            if(pvalue != null)
            {
                for(int i = 0; i < pvalue.length; i++)
                {
                    sp.setValue(Integer.parseInt(pvalue[i]));
                    sp.setClassName(pclassName[i]);
                    sp.setLikelihoodFm(Float.valueOf(likelihoodFm[i]).floatValue());
                    sp.setLikelihoodTo(Float.valueOf(likelihoodTo[i]).floatValue());
                    SimplePossibility pfound = (SimplePossibility)itemCategoryGroupDAO.create(sp);
                    pcompositeKey.setGroup_id(found.getId());
                    pcompositeKey.setPossibility_id(pfound.getId());
                    sjp.setCompositeKey(pcompositeKey);
                    itemCategoryGroupDAO.create(sjp);
                }

            }
            SimpleRisk sr = new SimpleRisk();
            SimpleJuncRisk sjr = new SimpleJuncRisk();
            RiskCompositeKey rcompositeKey = new RiskCompositeKey();
            for(int i = 0; i < old_riskId.length; i++)
            {
                sr = (SimpleRisk)itemCategoryGroupDAO.readBack(SimpleRisk.class, Integer.parseInt(old_riskId[i]));
                if(old_rvalueDeleteChk[i].isEmpty())
                {
                    sr.setValue(Integer.parseInt(old_rvalue[i]));
                    sr.setClassName(old_rclassName[i]);
                    sr.setRiskGradeFm(Float.valueOf(old_riskGradeFm[i]).floatValue());
                    sr.setRiskGradeTo(Float.valueOf(old_riskGradeTo[i]).floatValue());
                    sr.setCorrectiveMeasure(old_correctiveMeasure[i]);
                    sr.setColor(old_color[i]);
                    itemCategoryGroupDAO.update(sr);
                } else
                {
                    rcompositeKey.setGroup_id(found.getId());
                    rcompositeKey.setRisk_id(sr.getId());
                    sjp.setCompositeKey(pcompositeKey);
                    itemCategoryGroupDAO.delete(sjr);
                    itemCategoryGroupDAO.delete(sr);
                }
            }

            if(rvalue != null)
            {
                for(int i = 0; i < rvalue.length; i++)
                {
                    sr.setValue(Integer.parseInt(rvalue[i]));
                    sr.setClassName(rclassName[i]);
                    sr.setRiskGradeFm(Float.valueOf(riskGradeFm[i]).floatValue());
                    sr.setRiskGradeTo(Float.valueOf(riskGradeTo[i]).floatValue());
                    sr.setCorrectiveMeasure(correctiveMeasure[i]);
                    sr.setColor(color[i]);
                    SimpleRisk rfound = (SimpleRisk)itemCategoryGroupDAO.create(sr);
                    rcompositeKey.setGroup_id(found.getId());
                    rcompositeKey.setRisk_id(rfound.getId());
                    sjr.setCompositeKey(rcompositeKey);
                    itemCategoryGroupDAO.create(sjr);
                }

            }
            mav.addObject("returnUrl", "risk.category.do?action=listItemCategoryGroup");
            mav.setViewName((new StringBuilder("risk.category.do?action=readItemCategoryGroupBack&menu=11&articleId=")).append(articleId).toString());
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView deleteItemCategoryGroupBack(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        SimpleItemCategoryGroupBack target = new SimpleItemCategoryGroupBack();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        PrintWriter out = null;
        try
        {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        if(privilegeId > 1)
        {
            target.setId(articleId);
            SimpleItemCategoryGroupBack read = (SimpleItemCategoryGroupBack)itemCategoryGroupDAO.readBack(SimpleItemCategoryGroupBack.class, articleId);
            List possibility = read.getPossibility();
            Possibility p;
            for(Iterator iterator = possibility.iterator(); iterator.hasNext(); itemCategoryGroupDAO.delete(p))
            {
                p = (Possibility)iterator.next();
                itemCategoryGroupDAO.clearJunction("JUNC_POSSIBILITY_CLASS", "GROUP_ID", articleId);
            }

            List risk = read.getRisk();
            Risk r;
            for(Iterator iterator1 = risk.iterator(); iterator1.hasNext(); itemCategoryGroupDAO.delete(r))
            {
                r = (Risk)iterator1.next();
                itemCategoryGroupDAO.clearJunction("JUNC_RISK_CLASS", "GROUP_ID", articleId);
            }

            itemCategoryGroupDAO.delete(target);
            mav.addObject("returnUrl", "risk.category.do?action=listItemCategoryGroup");
            mav.setViewName((new StringBuilder("risk.category.do?action=readItemCategoryGroup&menu=11&articleId=")).append(read.getGroup_id()).toString());
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'safetyItem.do?action=listItem'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView startDateCheck(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        String startDate = Function.nullChk(request.getParameter("startDate"), "");
        long groupId = Function.nullChk(request.getParameter("groupId"), -1);
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sd = new Date();
        try
        {
            sd = sdf.parse(startDate);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            PrintWriter out = response.getWriter();
            SimpleItemCategoryGroupBack found = new SimpleItemCategoryGroupBack();
            found.setStartDate(sd);
            found.setGroup_id(groupId);
            result = itemCategoryGroupDAO.startDateCheck(found, articleId);
            out.println(result);
            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        mav.addObject("message", Boolean.valueOf(result));
        mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
        return mav;
    }

    public ModelAndView getItemCategoryGroup(HttpServletRequest request, HttpServletResponse response)
    {
        List list = itemCategoryGroupDAO.list(SimpleItemCategoryGroup.class);
        DataSet ds = new DataSet("ds_itemCategoryGroup");
        ds.addColumn("id", 3, 10);
        ds.addColumn("property_value", 2, 256);
        ds.addColumn("type", 3, 10);
        int row = 0;
        int id = -1;
        int type = -1;
        String propertyValue = "";
        for(int i = 0; i < list.size(); i++)
        {
            row = ds.newRow();
            id = (int)((SimpleItemCategoryGroup)list.get(i)).getId();
            if(id <= -1)
                id = 0;
            propertyValue = Function.nullChk(((SimpleItemCategoryGroup)list.get(i)).getPropertyValue(), "");
            type = ((SimpleItemCategoryGroup)list.get(i)).getType();
            ds.set(row, "id", id);
            ds.set(row, "property_value", propertyValue);
            ds.set(row, "type", type);
        }

        int nErrorCode = 0;
        String strErrorMsg = "START";
        PlatformData pdata = new PlatformData();
        pdata.addDataSet(ds);
        VariableList varList = pdata.getVariableList();
        varList.add("ErrorCode", nErrorCode);
        varList.add("ErrorMsg", strErrorMsg);
        HttpPlatformResponse res = new HttpPlatformResponse(response);
        res.setData(pdata);
        try
        {
            res.sendData();
        }
        catch(PlatformException e)
        {
            varList.add("ErrorCode", 1);
            varList.add("ErrorMsg", e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private ItemCategoryGroupDAO itemCategoryGroupDAO;
}

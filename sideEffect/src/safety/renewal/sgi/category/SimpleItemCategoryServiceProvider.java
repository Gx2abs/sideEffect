// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryServiceProvider.java

package safety.renewal.sgi.category;

import abstraction.IDValuePair;
import abstraction.SimpleDelegate;
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
import safety.renewal.sgi.item.SimpleItem1;

// Referenced classes of package safety.renewal.sgi.category:
//            ItemCategoryServiceProvider, SimpleItemCategoryHistory.class, SimpleItemCategory, SimpleItemCategoryCodeType, 
//            ItemCategoryDAO, SimpleItemCategoryLevel, SimpleItemCategoryTraceability, SimpleItemCategoryGrade, 
//            SimpleJncItemCategoryHistory

public class SimpleItemCategoryServiceProvider extends SimpleDelegate
    implements ItemCategoryServiceProvider
{

    public SimpleItemCategoryServiceProvider()
    {
    }

    public ItemCategoryDAO getItemCategoryDAO()
    {
        return itemCategoryDAO;
    }

    public void setItemCategoryDAO(ItemCategoryDAO meb_itemDAO)
    {
        itemCategoryDAO = meb_itemDAO;
    }

    public History buildOrEmpty(HttpServletRequest request, Class class1)
    {
        SimpleItemCategoryHistory history = null;
        try
        {
            if(validate(request))
            {
                String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
                String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                int historyType = Function.nullChk(request.getParameter("historyType"), 0);
                String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = new SimpleItemCategoryHistory();
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

    public ModelAndView listItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub03_01.jpg";
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
        String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
        int codeAgeId = Function.nullChk(request.getParameter("codeAge"), 0);
        int meaClassNoLevelId = Function.nullChk(request.getParameter("meaClassNoLevel"), 0);
        System.out.println((new StringBuilder("[SimpleItemCategoryServiceProvider.java].listItemCategory().searchColumn  ")).append(searchColumn).toString());
        System.out.println((new StringBuilder("[SimpleItemCategoryServiceProvider.java].listItemCategory().searchKeyword  ")).append(searchKeyword).toString());
        SimpleItemCategory target = new SimpleItemCategory();
        SimpleItemCategoryCodeType codeAge = new SimpleItemCategoryCodeType();
        codeAge.setId(codeAgeId);
        target.setCodeAge(Integer.valueOf(codeAgeId));
        codeAge = (SimpleItemCategoryCodeType)itemCategoryDAO.read(SimpleItemCategoryCodeType.class, codeAgeId);
        target.setCode_age(codeAge);
        SimpleItemCategoryLevel meaClassNoLv = new SimpleItemCategoryLevel();
        meaClassNoLv.setId(meaClassNoLevelId);
        target.setClass_level(meaClassNoLevelId);
        meaClassNoLv = (SimpleItemCategoryLevel)itemCategoryDAO.read(SimpleItemCategoryLevel.class, meaClassNoLevelId);
        target.setMeaClassNoLevel(meaClassNoLv);
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
        List itemLevelList = itemCategoryDAO.list(SimpleItemCategoryLevel.class);
        List itemCodeTypes = itemCategoryDAO.list(SimpleItemCategoryCodeType.class);
        long total = itemCategoryDAO.count(target);
        long top = total - (long)((pg - 1) * 10);
        searchKeyword = Function.URLEncoderUTF8(searchKeyword);
        pageString = page.pageList((int)total, 10, pg, (new StringBuilder("itemCategory.do?action=listItemCategory&searchKeyword=")).append(searchKeyword).append("&searchColumn=").append(searchColumn).append("&codeAge=").append(codeAgeId).append("&meaClassNoLevel=").append(meaClassNoLevelId).toString(), "");
        dl = itemCategoryDAO.list(target, pg, 10);
        mav.addObject("meaClassNoLevel", itemLevelList);
        mav.addObject("itemCodeTypes", itemCodeTypes);
        mav.addObject("list", dl);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
        mav.addObject("contentName", "/view/jsp/properties/category/listItemCategory1.jsp");
        return mav;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/properties/propertiesLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        return mav;
    }

    public ModelAndView popItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        SimpleItemCategory target = new SimpleItemCategory();
        Page page = new Page();
        String pageString = "";
        setDefaultViewSet(mav, request);
        List dl = null;
        List itemLevelList = itemCategoryDAO.list(SimpleItemCategoryLevel.class);
        List itemCodeTypes = itemCategoryDAO.list(SimpleItemCategoryCodeType.class);
        long total = itemCategoryDAO.count(target);
        long top = total - (long)((pg - 1) * 10);
        pageString = page.pageList((int)total, 10, pg, "itemCategory.do?action=popItemCategory", "");
        dl = itemCategoryDAO.list(target, pg, 10);
        mav.addObject("itemLevelList", itemLevelList);
        mav.addObject("itemCodeTypes", itemCodeTypes);
        mav.addObject("list", dl);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("pageString", pageString);
        mav.setViewName("/view/jsp/properties/category/popItemCategory1.jsp");
        return mav;
    }

    public ModelAndView createItemCategoryPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub03_01.jpg";
        long level = Function.nullChk(request.getParameter("level"), -1);
        if(level < 1L)
        {
            mav.addObject("contentName", "/view/jsp/common/defaultMessageDisplay1.jsp");
            mav.addObject("message", "\uC624\uB958 \uBC1C\uC0DD. \uD488\uBAA9\uCF54\uB4DC \uC791\uC131\uC2DC\uC5D0 \uD544\uC694\uD55C level \uC815\uBCF4\uAC00 \uC62C\uBC14\uB974\uC9C0\uC54A\uC2B5\uB2C8\uB2E4.");
            return mav;
        }
        if(level != 1L)
        {
            List itemCategory_no_list = itemCategoryDAO.listItemCategoryNo(null, 1);
            mav.addObject("level1List", itemCategory_no_list);
        }
        SimpleItemCategoryLevel meaClassNoLevel = (SimpleItemCategoryLevel)itemCategoryDAO.read(SimpleItemCategoryLevel.class, level);
        mav.addObject("meaClassNoLevel", meaClassNoLevel);
        List traceabilityList = itemCategoryDAO.list(SimpleItemCategoryTraceability.class);
        List historyType = itemCategoryDAO.list(SimpleHistoryType.class);
        List isInUse = itemCategoryDAO.list(SimpleIsInUse.class);
        List itemCodeTypes = itemCategoryDAO.list(SimpleItemCategoryCodeType.class);
        List itemGrades = itemCategoryDAO.list(SimpleItemCategoryGrade.class);
        setDefaultViewSet(mav, request);
        mav.addObject("itemGrades", itemGrades);
        mav.addObject("itemCodeTypes", itemCodeTypes);
        mav.addObject("isInUse", isInUse);
        mav.addObject("traceabilityList", traceabilityList);
        mav.addObject("historyType", historyType);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
        mav.addObject("contentName", "/view/jsp/properties/category/createItemCategory1.jsp");
        return mav;
    }

    public ModelAndView createItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int parentItem = Function.nullChk(request.getParameter("parentItem"), 0);
        System.out.println((new StringBuilder("parentItem : ")).append(parentItem).toString());
        int itemGrade = Function.nullChk(request.getParameter("itemGrade"), 0);
        System.out.println((new StringBuilder("itemGrade : ")).append(itemGrade).toString());
        int itemCodeType = Function.nullChk(request.getParameter("itemCodeType"), 0);
        String itemNameKr = Function.nullChk(request.getParameter("itemNameKr"), "");
        String itemNameEn = Function.nullChk(request.getParameter("itemNameEn"), "");
        String itemDesc = Function.nullChk(request.getParameter("itemDesc"), "");
        int traceability = Function.nullChk(request.getParameter("traceability"), 0);
        int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
        int level = Function.nullChk(request.getParameter("level"), 0);
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
        System.out.println((new StringBuilder("level : ")).append(level).toString());
        String udiCode = Function.nullChk(request.getParameter("udiCode"), "");
        if(privilegeId > 1)
        {
            if(level == 3)
                parentItem = Function.nullChk(request.getParameter("parentItem2"), 0);
            String mea_class_no_code = Function.nullChk(request.getParameter("mea_class_no_code"), "");
            SimpleItemCategory si = new SimpleItemCategory();
            SimpleItemCategoryGrade sicg = new SimpleItemCategoryGrade();
            sicg.setId(itemGrade);
            si.setCategoryGrade(sicg);
            si.setMea_class_no(mea_class_no_code);
            si.setClass_kor_name(itemNameKr);
            si.setClass_eng_name(itemNameEn);
            si.setClass_cont(itemDesc);
            si.setUdi_code(udiCode);
            si.setParentId(Long.valueOf(parentItem));
            SimpleItemCategoryLevel sicl = new SimpleItemCategoryLevel();
            sicl.setId(level);
            si.setMeaClassNoLevel(sicl);
            SimpleItemCategoryTraceability st = new SimpleItemCategoryTraceability();
            st.setId(traceability);
            si.setTraceability(st);
            SimpleIsInUse siiu = new SimpleIsInUse();
            siiu.setId(isInUse);
            si.setIsInUse(siiu);
            si.setUse_yn(isInUse != 1 ? "N" : "Y");
            si.setTrace_manage_target_yn(traceability != 1 ? "N" : "Y");
            si.setParentId(Long.valueOf(parentItem));
            SimpleItemCategoryCodeType sict = new SimpleItemCategoryCodeType();
            sict.setId(itemCodeType);
            si.setCode_age(sict);
            SimpleItemCategory inserted = (SimpleItemCategory)itemCategoryDAO.create(si);
            if(level == 1)
            {
                si.setParentId(Long.valueOf(si.getId()));
                itemCategoryDAO.update(si);
            }
            History sih = buildOrEmpty(request, SimpleItemCategoryHistory.class);
            if(sih != null)
            {
                sih = (History)itemCategoryDAO.create(sih);
                SimpleJncItemCategoryHistory jnc = new SimpleJncItemCategoryHistory();
                jnc.setCategoryId(inserted.getId());
                jnc.setHistoryId(sih.getId());
                itemCategoryDAO.create(jnc, true);
            }
            mav.setViewName("itemCategory.do?action=listItemCategory&menu=1&subMenu=1");
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'itemCategory.do?action=listItemCategory'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView readItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub03_01.jpg";
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        List traceabilityList = itemCategoryDAO.list(SimpleItemCategoryTraceability.class);
        List historyType = itemCategoryDAO.list(SimpleHistoryType.class);
        List isInUse = itemCategoryDAO.list(SimpleIsInUse.class);
        List itemCodeTypes = itemCategoryDAO.list(SimpleItemCategoryCodeType.class);
        List itemGrades = itemCategoryDAO.list(SimpleItemCategoryGrade.class);
        System.out.println((new StringBuilder("[SimpleItemCategoryServiceProvider].articleId  ")).append(articleId).toString());
        SimpleItemCategory found = (SimpleItemCategory)itemCategoryDAO.read(SimpleItemCategory.class, articleId);
        if(found.getClass_level() == 2)
        {
            IDValuePair foundParent = itemCategoryDAO.getParent(found.getParentId().longValue());
            mav.addObject("parent", foundParent);
        } else
        if(found.getClass_level() == 3)
        {
            IDValuePair foundParent = itemCategoryDAO.getParent(found.getParentId().longValue());
            mav.addObject("parent", foundParent);
            SimpleItemCategory foundParent2 = (SimpleItemCategory)itemCategoryDAO.getParent(((SimpleItemCategory)foundParent).getParentId().longValue());
            mav.addObject("parent2", foundParent2);
        }
        setDefaultViewSet(mav, request);
        mav.addObject("itemGrades", itemGrades);
        mav.addObject("itemCodeTypes", itemCodeTypes);
        mav.addObject("isInUse", isInUse);
        mav.addObject("traceabilityList", traceabilityList);
        mav.addObject("historyType", historyType);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
        mav.addObject("article", found);
        mav.addObject("contentName", "/view/jsp/properties/category/readItemCategory1.jsp");
        return mav;
    }

    public ModelAndView updateItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int grade = Function.nullChk(request.getParameter("grade"), -1);
        String stringGrade = Function.nullChk(request.getParameter("grade"), "-1");
        String mea_class_no = Function.nullChk(request.getParameter("mea_class_no"), "");
        System.out.println((new StringBuilder("updateMea : ")).append(grade).append(" / ").append(mea_class_no).toString());
        SimpleItemCategory found = null;
        String class_kor_name = Function.nullChk(request.getParameter("class_kor_name"), "");
        String class_eng_name = Function.nullChk(request.getParameter("class_eng_name"), "");
        String class_cont = Function.nullChk(request.getParameter("class_cont"), "");
        int traceability = Function.nullChk(request.getParameter("traceability"), 0);
        int parentItem = Function.nullChk(request.getParameter("parentItem"), 0);
        int itemCodeType = Function.nullChk(request.getParameter("itemCodeType"), 0);
        int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
        String udiCode = Function.nullChk(request.getParameter("udiCode"), "");
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
            found = (SimpleItemCategory)itemCategoryDAO.read(SimpleItemCategory.class, articleId);
            SimpleItemCategoryTraceability sit = new SimpleItemCategoryTraceability();
            sit.setId(traceability);
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
            SimpleItemCategoryCodeType sct = new SimpleItemCategoryCodeType();
            sct.setId(itemCodeType);
            found.setCode_age(sct);
            History sih = buildOrEmpty(request, SimpleItemCategoryHistory.class);
            SimpleItemCategory updated = (SimpleItemCategory)itemCategoryDAO.update(found);
            if(sih != null)
            {
                sih = (History)itemCategoryDAO.create(sih);
                SimpleJncItemCategoryHistory jnc = new SimpleJncItemCategoryHistory();
                jnc.setHistoryId(sih.getId());
                jnc.setCategoryId(found.getId());
                itemCategoryDAO.create(jnc);
            }
            mav.setViewName((new StringBuilder("itemCategory.do?action=readItemCategory&articleId=")).append(found.getId()).toString());
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'itemCategory.do?action=listItemCategory'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView updateItemCategoryPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub03_01.jpg";
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        SimpleItemCategory found = (SimpleItemCategory)itemCategoryDAO.read(SimpleItemCategory.class, articleId);
        if(found.getClass_level() == 2)
        {
            IDValuePair foundParent = itemCategoryDAO.getParent(found.getParentId().longValue());
            mav.addObject("parent", foundParent);
        } else
        if(found.getClass_level() == 3)
        {
            IDValuePair foundParent = itemCategoryDAO.getParent(found.getParentId().longValue());
            mav.addObject("parent", foundParent);
            SimpleItemCategory foundParent2 = (SimpleItemCategory)itemCategoryDAO.getParent(((SimpleItemCategory)foundParent).getParentId().longValue());
            mav.addObject("parent2", foundParent2);
        }
        List traceabilityList = itemCategoryDAO.list(SimpleItemCategoryTraceability.class);
        List historyType = itemCategoryDAO.list(SimpleHistoryType.class);
        List isInUse = itemCategoryDAO.list(SimpleIsInUse.class);
        List itemCodeTypes = itemCategoryDAO.list(SimpleItemCategoryCodeType.class);
        List itemGrades = itemCategoryDAO.list(SimpleItemCategoryGrade.class);
        setDefaultViewSet(mav, request);
        mav.addObject("itemGrades", itemGrades);
        mav.addObject("itemCodeTypes", itemCodeTypes);
        mav.addObject("isInUse", isInUse);
        mav.addObject("traceabilityList", traceabilityList);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_01.jpg");
        mav.addObject("contentName", "/view/jsp/properties/category/updateItemCategory1.jsp");
        return mav;
    }

    public ModelAndView deleteItemCategory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        IDValuePair target = new SimpleItemCategory();
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
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        if(privilegeId > 1)
        {
            target.setId(articleId);
            SimpleItemCategory read = (SimpleItemCategory)itemCategoryDAO.read(SimpleItemCategory.class, articleId);
            int childClassLevel = read.getClass_level();
            SimpleItemCategory sp = new SimpleItemCategory();
            sp.setParentId(Long.valueOf(articleId));
            sp.setClass_level(++childClassLevel);
            long numberOfChildren = itemCategoryDAO.count(sp);
            if(numberOfChildren == 0L)
            {
                SimpleItem1 iRefer = new SimpleItem1();
                iRefer.setItem_category_number(Integer.valueOf((int)articleId));
                long refer = itemCategoryDAO.count(iRefer);
                if(refer > 0L)
                {
                    out.println("<script language=javascript>");
                    out.println("alert('\uC81C\uD488\uCF54\uB4DC\uC5D0\uC11C \uD574\uB2F9 \uCF54\uB4DC\uAC00 \uC0AC\uC6A9\uC911\uC785\uB2C8\uB2E4.');");
                    out.println((new StringBuilder("location.href = 'itemCategory.do?action=readItemCategory&menu=1&articleId=")).append(articleId).append("'").toString());
                    out.println("</script>");
                    return null;
                }
                Set history = read.getItemCategoryHistory();
                History h;
                for(Iterator iterator = history.iterator(); iterator.hasNext(); itemCategoryDAO.delete(h))
                    h = (History)iterator.next();

                itemCategoryDAO.delete(target);
            } else
            {
                out.println("<script language=javascript>");
                out.println("alert('\uD558\uC704\uB808\uBCA8\uC758 \uCF54\uB4DC\uAC00 \uC874\uC7AC\uD569\uB2C8\uB2E4.');");
                out.println((new StringBuilder("location.href = 'itemCategory.do?action=readItemCategory&menu=1&articleId=")).append(articleId).append("'").toString());
                out.println("</script>");
                return null;
            }
            mav.setViewName("itemCategory.do?action=listItemCategory&menu=1&subMenu=1");
        } else
        {
            out.println("<script language=javascript>");
            out.println("alert('\uAD8C\uD55C\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.');");
            out.println("location.href = 'itemCategory.do?action=listItemCategory'");
            out.println("</script>");
            return null;
        }
        return mav;
    }

    public ModelAndView deleteItemHistory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        IDValuePair target = new SimpleItemCategoryHistory();
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        System.out.println((new StringBuilder("articleId:")).append(articleId).toString());
        if(privilegeId > 1)
        {
            System.out.println((new StringBuilder("articleId:")).append(articleId).toString());
            itemCategoryDAO.clearJunction("SGI_JNC_CATEGORY_HISTORY", "HISTORY_ID", articleId);
            target.setId(articleId);
            itemCategoryDAO.delete(target);
            mav.addObject("message", "1");
            mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
        } else
        {
            mav.setViewName("itemCategory.do?action=listItemCategory");
        }
        return mav;
    }

    public ModelAndView getChildMCN(HttpServletRequest request, HttpServletResponse respnse)
    {
        ModelAndView mav = new ModelAndView();
        System.out.println("getChildMCN Receiving...");
        int parentItem = Function.nullChk(request.getParameter("parentItem"), 0);
        int level = 2;
        List list = itemCategoryDAO.listMeaClassNo(parentItem, level);
        mav.setViewName("/view/jsp/properties/category/meaClassNoLevel3.jsp");
        mav.addObject("list", list);
        return mav;
    }

    public ModelAndView isUniqueItemCode(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int returnValue = -1;
        String stringGrade = Function.nullChk(request.getParameter("itemGrade"), "");
        int grade = 0;
        if(!"".equals(stringGrade))
            grade = Integer.parseInt(stringGrade);
        String mea_class_no = Function.nullChk(request.getParameter("itemCode"), "");
        SimpleItemCategory target = new SimpleItemCategory();
        target.setGrade(stringGrade);
        target.setMea_class_no(mea_class_no);
        SimpleItemCategory found = (SimpleItemCategory)itemCategoryDAO.readMeaClassNo(target);
        if(grade > -1 && !"".equals(mea_class_no) && (found == null || found.getMea_class_no() == null))
            returnValue = 1;
        mav.setViewName("/view/jsp/common/defaultAjaxMessage.jsp");
        mav.addObject("message", Integer.valueOf(returnValue));
        return mav;
    }

    private ItemCategoryDAO itemCategoryDAO;
}

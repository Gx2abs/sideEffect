// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleImportOutputServiceProvider.java

package risk.item.importoutput;

import abstraction.IDValuePair;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import org.springframework.web.servlet.ModelAndView;
import properties.history.History;
import properties.item.SimpleHistoryType;
import risk.category.SimpleItemCategoryGroupType;

// Referenced classes of package risk.item.importoutput:
//            ImportOutputServiceProvider, SimpleItemImport, ImportOutputDAO, SimpleItemImportHistory, 
//            SimpleItemOutput, SimpleItemOutputHistory

public class SimpleImportOutputServiceProvider
    implements ImportOutputServiceProvider
{

    public SimpleImportOutputServiceProvider()
    {
    }

    public ImportOutputDAO getImportOutputDAO()
    {
        return importOutputDAO;
    }

    public void setImportOutputDAO(ImportOutputDAO importOutputDAO)
    {
        this.importOutputDAO = importOutputDAO;
    }

    public History buildOrEmpty(HttpServletRequest request, Class clas)
    {
        History history = null;
        try
        {
            if(validate(request))
            {
                String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
                String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                int historyType = Function.nullChk(request.getParameter("historyType"), 0);
                String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = (History)clas.newInstance();
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
        mav.addObject("titleImg", "view/style/images/title/sub03_12_output.gif");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_12_importoutput.gif");
        return mav;
    }

    public ModelAndView listImport(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
        String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
        SimpleItemImport target = new SimpleItemImport();
        java.util.List dl = importOutputDAO.listEntpImportOutput(target, pg, 10, searchColumn, searchKeyword);
        long total = importOutputDAO.listEntpImportOutputCount(target, searchColumn, searchKeyword);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        searchKeyword = Function.URLEncoderUTF8(searchKeyword);
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("importoutput.do?action=listImport&searchKeyword=")).append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", dl);
        mav.addObject("pageString", pageString);
        mav.addObject("titleImg", "view/style/images/title/sub03_12_import.gif");
        mav.addObject("contentName", "/view/jsp/risk/importOutput/importList1.jsp");
        return mav;
    }

    public ModelAndView readImport(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        java.util.List historyType = importOutputDAO.list(SimpleHistoryType.class);
        IDValuePair found = importOutputDAO.read(SimpleItemImport.class, articleId);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("titleImg", "view/style/images/title/sub03_12_import.gif");
        mav.addObject("contentName", "/view/jsp/risk/importOutput/readImport1.jsp");
        return mav;
    }

    public ModelAndView createImportOutputPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        java.util.List historyType = importOutputDAO.list(SimpleHistoryType.class);
        java.util.List typeStatus = importOutputDAO.list(SimpleItemCategoryGroupType.class);
        mav.addObject("historyType", historyType);
        mav.addObject("typeStatus", typeStatus);
        mav.addObject("titleImg", "view/style/images/title/sub03_12_importoutput.gif");
        mav.addObject("contentName", "/view/jsp/risk/importOutput/createImport1.jsp");
        return mav;
    }

    public ModelAndView createImport(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long item_id = Function.nullChk(request.getParameter("item_id"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        String itemImport = Function.nullChk(request.getParameter("itemImport"), "");
        String importDate = Function.nullChk(request.getParameter("importDate"), "");
        String typeName = Function.nullChk(request.getParameter("typeName"), "");
        String date[] = importDate.split("-");
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
            SimpleItemImport insertTarget = new SimpleItemImport();
            insertTarget.setItemId(item_id);
            insertTarget.setItemImport(Float.valueOf(itemImport).floatValue());
            insertTarget.setImportYear(date[0]);
            insertTarget.setImportMonth(date[1]);
            insertTarget.setTypeName(typeName);
            SimpleItemImport found = (SimpleItemImport)importOutputDAO.create(insertTarget);
            History childObject = buildOrEmpty(request, SimpleItemImportHistory.class);
            if(childObject != null)
            {
                importOutputDAO.create(childObject);
                found.getItemImportHistory().add(childObject);
                importOutputDAO.update(found);
            }
            mav.addObject("returnUrl", "importoutput.do?action=listImport");
            mav.setViewName("importoutput.do?action=listImport");
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

    public ModelAndView updateImportPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        SimpleItemImport found = (SimpleItemImport)importOutputDAO.read(SimpleItemImport.class, articleId);
        java.util.List historyType = importOutputDAO.list(SimpleHistoryType.class);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("titleImg", "view/style/images/title/sub03_12_import.gif");
        mav.addObject("contentName", "/view/jsp/risk/importOutput/updateImport1.jsp");
        return mav;
    }

    public ModelAndView updateImport(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        String itemImport = Function.nullChk(request.getParameter("itemImport"), "");
        String importDate = Function.nullChk(request.getParameter("importDate"), "");
        String typeName = Function.nullChk(request.getParameter("typeName"), "");
        String date[] = importDate.split("-");
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
            SimpleItemImport found = (SimpleItemImport)importOutputDAO.read(SimpleItemImport.class, articleId);
            found.setItemImport(Float.valueOf(itemImport).floatValue());
            found.setImportYear(date[0]);
            found.setImportMonth(date[1]);
            found.setTypeName(typeName);
            History childObject = buildOrEmpty(request, SimpleItemImportHistory.class);
            if(childObject != null)
            {
                importOutputDAO.create(childObject);
                found.getItemImportHistory().add(childObject);
            }
            importOutputDAO.update(found);
            mav.setViewName((new StringBuilder("importoutput.do?action=readImport&articleId=")).append(articleId).toString());
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

    public ModelAndView deleteImport(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        SimpleItemImport target = new SimpleItemImport();
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
            SimpleItemImport read = (SimpleItemImport)importOutputDAO.read(SimpleItemImport.class, articleId);
            Set history = read.getItemImportHistory();
            History h;
            for(Iterator iterator = history.iterator(); iterator.hasNext(); importOutputDAO.delete(h))
            {
                h = (History)iterator.next();
                importOutputDAO.clearJunction("JUNC_ITEM_IMPORT_HISTORY", "IMPORT_ID", articleId);
            }

            importOutputDAO.delete(target);
            mav.addObject("returnUrl", "importoutput.do?action=listImport");
            mav.setViewName("importoutput.do?action=listImport");
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

    public ModelAndView deleteImportHistory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        IDValuePair target = new SimpleItemImportHistory();
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
            importOutputDAO.delete(target);
            importOutputDAO.clearJunction("JUNC_ITEM_IMPORT_HISTORY", "HISTORY_ID", articleId);
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

    public ModelAndView listOutput(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
        String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
        SimpleItemOutput target = new SimpleItemOutput();
        java.util.List dl = importOutputDAO.listEntpImportOutput(target, pg, 10, searchColumn, searchKeyword);
        long total = importOutputDAO.listEntpImportOutputCount(target, searchColumn, searchKeyword);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        searchKeyword = Function.URLEncoderUTF8(searchKeyword);
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("importoutput.do?action=listOutput&searchKeyword=")).append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", dl);
        mav.addObject("pageString", pageString);
        mav.addObject("contentName", "/view/jsp/risk/importOutput/outputList1.jsp");
        return mav;
    }

    public ModelAndView readOutput(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        java.util.List historyType = importOutputDAO.list(SimpleHistoryType.class);
        IDValuePair found = importOutputDAO.read(SimpleItemOutput.class, articleId);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("contentName", "/view/jsp/risk/importOutput/readOutput1.jsp");
        return mav;
    }

    public ModelAndView createOutput(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long item_id = Function.nullChk(request.getParameter("item_id"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        String itemOutput = Function.nullChk(request.getParameter("itemImport"), "");
        String outputDate = Function.nullChk(request.getParameter("importDate"), "");
        String typeName = Function.nullChk(request.getParameter("typeName"), "");
        String date[] = outputDate.split("-");
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
            SimpleItemOutput insertTarget = new SimpleItemOutput();
            insertTarget.setItemId(item_id);
            insertTarget.setItemOutput(Float.valueOf(itemOutput).floatValue());
            insertTarget.setOutputYear(date[0]);
            insertTarget.setOutputMonth(date[1]);
            insertTarget.setTypeName(typeName);
            SimpleItemOutput found = (SimpleItemOutput)importOutputDAO.create(insertTarget);
            History childObject = buildOrEmpty(request, SimpleItemOutputHistory.class);
            if(childObject != null)
            {
                importOutputDAO.create(childObject);
                found.getItemOutputHistory().add(childObject);
                importOutputDAO.update(found);
            }
            mav.addObject("returnUrl", "importoutput.do?action=listOutput");
            mav.setViewName("importoutput.do?action=listOutput");
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

    public ModelAndView updateOutputPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        SimpleItemOutput found = (SimpleItemOutput)importOutputDAO.read(SimpleItemOutput.class, articleId);
        java.util.List historyType = importOutputDAO.list(SimpleHistoryType.class);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("contentName", "/view/jsp/risk/importOutput/updateOutput1.jsp");
        return mav;
    }

    public ModelAndView updateOutput(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        int privilegeId = Function.nullChk(request.getParameter("privilegeId"), 0);
        String itemOutput = Function.nullChk(request.getParameter("itemOutput"), "");
        String outputDate = Function.nullChk(request.getParameter("outputDate"), "");
        String typeName = Function.nullChk(request.getParameter("typeName"), "");
        String date[] = outputDate.split("-");
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
            SimpleItemOutput found = (SimpleItemOutput)importOutputDAO.read(SimpleItemOutput.class, articleId);
            found.setItemOutput(Float.valueOf(itemOutput).floatValue());
            found.setOutputYear(date[0]);
            found.setOutputMonth(date[1]);
            found.setTypeName(typeName);
            History childObject = buildOrEmpty(request, SimpleItemOutputHistory.class);
            if(childObject != null)
            {
                importOutputDAO.create(childObject);
                found.getItemOutputHistory().add(childObject);
            }
            importOutputDAO.update(found);
            mav.setViewName((new StringBuilder("importoutput.do?action=readOutput&articleId=")).append(articleId).toString());
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

    public ModelAndView deleteOutput(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        SimpleItemOutput target = new SimpleItemOutput();
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
            SimpleItemOutput read = (SimpleItemOutput)importOutputDAO.read(SimpleItemOutput.class, articleId);
            Set history = read.getItemOutputHistory();
            History h;
            for(Iterator iterator = history.iterator(); iterator.hasNext(); importOutputDAO.delete(h))
            {
                h = (History)iterator.next();
                importOutputDAO.clearJunction("JUNC_ITEM_OUTPUT_HISTORY", "OUTPUT_ID", articleId);
            }

            importOutputDAO.delete(target);
            mav.addObject("returnUrl", "importoutput.do?action=listOutput");
            mav.setViewName("importoutput.do?action=listOutput");
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

    public ModelAndView deleteOutputHistory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        IDValuePair target = new SimpleItemOutputHistory();
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
            importOutputDAO.delete(target);
            importOutputDAO.clearJunction("JUNC_ITEM_OUTPUT_HISTORY", "HISTORY_ID", articleId);
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

    private ImportOutputDAO importOutputDAO;
}

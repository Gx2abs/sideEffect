// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCompanyServiceProvider.java

package safety.renewal.company;

import abstraction.IDValuePair;
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
import properties.company.SimpleJuncCompany;
import properties.history.History;
import properties.item.SimpleHistoryType;
import properties.item.SimpleTraceability;
import safety.renewal.sgi.item.SimpleItem1;

// Referenced classes of package safety.renewal.company:
//            CompanyServiceProvider, SimpleCompanyHistory1, CompanyHistory1, SimpleCompany1, 
//            CompanyDAO, SimpleCloseType1, SimpleFlagType1

public class SimpleCompanyServiceProvider
    implements CompanyServiceProvider
{

    public SimpleCompanyServiceProvider()
    {
    }

    public CompanyDAO getCompanyDAO()
    {
        return CompanyDAO;
    }

    public void setCompanyDAO(CompanyDAO companyDAO)
    {
        CompanyDAO = companyDAO;
    }

    public History buildOrEmpty(HttpServletRequest request, Class class1)
    {
        CompanyHistory1 history = null;
        try
        {
            if(validate(request))
            {
                String activeFrom = Function.nullChk(request.getParameter("activeFrom"), "");
                String historyManager = Function.nullChk(request.getParameter("historyManager"), "");
                int historyType = Function.nullChk(request.getParameter("historyType"), 0);
                String historyDescription = Function.nullChk(request.getParameter("historyDescription"), "");
                history = new SimpleCompanyHistory1();
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
        System.out.println((new StringBuilder("SimpleCompany1ServiceProvicer.validate : ")).append(result).toString());
        return result;
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

    public ModelAndView listCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub03_05.jpg";
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        setDefaultViewSet(mav, request);
        String searchKeyword = Function.nullChk(request.getParameter("searchKeyword"), "");
        String searchColumn = Function.nullChk(request.getParameter("searchColumn"), "");
        SimpleCompany1 sc = new SimpleCompany1();
        if(searchColumn.equals("setCob_flag_code"))
            if(searchKeyword.equals("\uC81C\uC870\uC5C5") || searchKeyword.equals("\uC81C\uC870"))
                searchKeyword = "1";
            else
            if(searchKeyword.equals("\uC218\uC785\uC5C5") || searchKeyword.equals("\uC218\uC785"))
                searchKeyword = "2";
        if(!"".equals(searchColumn))
        {
            Method methods[] = SimpleCompany1.class.getDeclaredMethods();
            Method amethod[];
            int j = (amethod = methods).length;
            for(int i = 0; i < j; i++)
            {
                Method m = amethod[i];
                if(m.getName().equals(searchColumn))
                    try
                    {
                        m.invoke(sc, new Object[] {
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

            System.out.println((new StringBuilder("searchColumn")).append(searchColumn).toString());
            System.out.println((new StringBuilder("searchKeyword")).append(searchKeyword).toString());
            System.out.println((new StringBuilder("methods")).append(methods).toString());
        }
        List dl = CompanyDAO.list(SimpleCompany1.class, pg, 10);
        long total = CompanyDAO.count(sc);
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        searchKeyword = Function.URLEncoderUTF8(searchKeyword);
        String pageString = page.pageList((int)total, 10, pg, (new StringBuilder("safetyCompany.do?action=listCompany&searchKeyword=")).append(searchKeyword).append("&searchColumn=").append(searchColumn).toString(), "");
        List set2 = CompanyDAO.list(sc, pg, 10);
        if(set2 != null)
            dl = new ArrayList(set2);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", dl);
        mav.addObject("pageString", pageString);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
        mav.addObject("contentName", "/view/jsp/properties/company/companyList1.jsp");
        return mav;
    }

    public ModelAndView createCompanyPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        String titleImg = "view/style/images/title/sub03_05.jpg";
        List historyType = CompanyDAO.list(SimpleHistoryType.class);
        List isInUse = CompanyDAO.list(SimpleIsInUse.class);
        List closeType = CompanyDAO.list(SimpleCloseType1.class);
        List flagType = CompanyDAO.list(SimpleFlagType1.class);
        setDefaultViewSet(mav, request);
        mav.addObject("isInUse", isInUse);
        mav.addObject("closeType", closeType);
        mav.addObject("flagType", flagType);
        mav.addObject("historyType", historyType);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
        mav.addObject("contentName", "/view/jsp/properties/company/createCompany1.jsp");
        return mav;
    }

    public ModelAndView createCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        Enumeration headernames = request.getHeaderNames();
        String headerName = "";
        String headerVal = "";
        for(; headernames.hasMoreElements(); System.out.println((new StringBuilder(String.valueOf(headerName))).append("/").append(headerVal).toString()))
        {
            headerName = (String)headernames.nextElement();
            headerVal = request.getHeader(headerName);
        }

        try
        {
            request.setCharacterEncoding("8859_1");
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        setDefaultViewSet(mav, request);
        String entp_name = Function.nullChk(request.getParameter("entp_name"), "");
        String meddev_entp_no = Function.nullChk(request.getParameter("meddev_entp_no"), "");
        String cob_flag_code = Function.nullChk(request.getParameter("flagType"), "");
        String shutdown_close_reopen_code = Function.nullChk(request.getParameter("closeType"), "");
        String permit_date = Function.nullChk(request.getParameter("permit_date"), "");
        String entp_zip_no = Function.nullChk(request.getParameter("entp_zip_no"), "");
        String entp_addr1 = Function.nullChk(request.getParameter("entp_addr1"), "");
        String entp_addr2 = Function.nullChk(request.getParameter("entp_addr2"), "");
        String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
        int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
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
        SimpleCompany1 insertTarget = new SimpleCompany1();
        if(privilegeId > 1)
        {
            if(permit_date != "")
                try
                {
                    String dateString = permit_date.replaceAll("/", "-");
                    String date1 = dateString;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date af = new Date();
                    af = sdf.parse(date1);
                    insertTarget.setPermit_date(af);
                }
                catch(ParseException parseexception) { }
            else
                insertTarget.setPermit_date(new Date());
            insertTarget.setEntp_name(entp_name);
            insertTarget.setMeddev_entp_no(meddev_entp_no);
            insertTarget.setCob_flag_code(cob_flag_code);
            insertTarget.setShutdown_close_reopen_code(shutdown_close_reopen_code);
            insertTarget.setEntp_zip_no(entp_zip_no);
            insertTarget.setEntp_addr1(entp_addr1);
            insertTarget.setEntp_addr2(entp_addr2);
            insertTarget.setDefault_in_use_id(Integer.valueOf(isInUse));
            insertTarget.setPropertyValue(propertyValue);
            insertTarget.setRegist_ts(new Date());
            SimpleCompany1 found = (SimpleCompany1)CompanyDAO.create(insertTarget);
            History childObject = buildOrEmpty(request, SimpleCompanyHistory1.class);
            if(childObject != null)
            {
                CompanyDAO.create(childObject);
                found.getCompanyHistory().add(childObject);
                CompanyDAO.update(found);
            }
            mav.addObject("returnUrl", "safetyCompany.do?action=listCompany");
            mav.setViewName("safetyCompany.do?action=listCompany");
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

    public ModelAndView readCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub03_05.jpg";
        int articleId = Function.nullChk(request.getParameter("articleId"), -1);
        List traceabilityList = CompanyDAO.list(SimpleTraceability.class);
        List historyType = CompanyDAO.list(SimpleHistoryType.class);
        List closeType = CompanyDAO.list(SimpleCloseType1.class);
        List flagType = CompanyDAO.list(SimpleFlagType1.class);
        List isInUse = CompanyDAO.list(SimpleIsInUse.class);
        setDefaultViewSet(mav, request);
        mav.addObject("isInUse", isInUse);
        mav.addObject("closeType", closeType);
        mav.addObject("flagType", flagType);
        mav.addObject("traceabilityList", traceabilityList);
        mav.addObject("historyType", historyType);
        IDValuePair found = CompanyDAO.read(SimpleCompany1.class, articleId);
        mav.addObject("article", found);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
        mav.addObject("contentName", "/view/jsp/properties/company/readCompany1.jsp");
        return mav;
    }

    public ModelAndView updateCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        SimpleCompany1 targetObject = new SimpleCompany1();
        String entp_name = Function.nullChk(request.getParameter("entp_name"), "");
        String meddev_entp_no = Function.nullChk(request.getParameter("meddev_entp_no"), "");
        String cob_flag_code = Function.nullChk(request.getParameter("flagType"), "");
        String shutdown_close_reopen_code = Function.nullChk(request.getParameter("closeType"), "");
        String permit_date = Function.nullChk(request.getParameter("permit_date"), "");
        String entp_zip_no = Function.nullChk(request.getParameter("entp_zip_no"), "");
        String entp_addr1 = Function.nullChk(request.getParameter("entp_addr1"), "");
        String entp_addr2 = Function.nullChk(request.getParameter("entp_addr2"), "");
        String propertyValue = Function.nullChk(request.getParameter("propertyValue"), "");
        int isInUse = Function.nullChk(request.getParameter("isInUse"), 0);
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
            SimpleCompany1 found = (SimpleCompany1)CompanyDAO.read(SimpleCompany1.class, articleId);
            if(permit_date != "")
                try
                {
                    String dateString = permit_date.replaceAll("/", "-");
                    String date1 = dateString;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date af = new Date();
                    af = sdf.parse(date1);
                    found.setPermit_date(af);
                }
                catch(ParseException parseexception) { }
            else
                found.setPermit_date(new Date());
            found.setPropertyValue(propertyValue);
            found.setIsInUse(Integer.valueOf(isInUse));
            found.setEntp_name(entp_name);
            found.setMeddev_entp_no(meddev_entp_no);
            found.setCob_flag_code(cob_flag_code);
            found.setShutdown_close_reopen_code(shutdown_close_reopen_code);
            found.setEntp_zip_no(entp_zip_no);
            found.setEntp_addr1(entp_addr1);
            found.setEntp_addr2(entp_addr2);
            found.setDefault_in_use_id(Integer.valueOf(isInUse));
            found.setRegist_ts(new Date());
            History childObject = buildOrEmpty(request, SimpleCompanyHistory1.class);
            if(childObject != null)
            {
                CompanyDAO.create(childObject);
                found.getCompanyHistory().add(childObject);
                CompanyDAO.update(found);
            }
            SimpleCompany1 updated = (SimpleCompany1)CompanyDAO.update(found);
            mav.setViewName((new StringBuilder("safetyCompany.do?action=readCompany&articleId=")).append(articleId).toString());
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

    public ModelAndView updateCompanyPage(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        setDefaultViewSet(mav, request);
        String titleImg = "view/style/images/title/sub03_05.jpg";
        long articleId = Function.nullChk(request.getParameter("articleId"), -1);
        IDValuePair found = CompanyDAO.read(SimpleCompany1.class, articleId);
        List historyType = CompanyDAO.list(SimpleHistoryType.class);
        List isInUse = CompanyDAO.list(SimpleIsInUse.class);
        List closeType = CompanyDAO.list(SimpleCloseType1.class);
        List flagType = CompanyDAO.list(SimpleFlagType1.class);
        setDefaultViewSet(mav, request);
        mav.addObject("isInUse", isInUse);
        mav.addObject("closeType", closeType);
        mav.addObject("flagType", flagType);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("titleImg", titleImg);
        mav.addObject("titleImage", "view/style/images/title/sign/top_img3_05.jpg");
        mav.addObject("contentName", "/view/jsp/properties/company/updateCompany1.jsp");
        setDefaultViewSet(mav, request);
        mav.addObject("isInUse", isInUse);
        mav.addObject("historyType", historyType);
        mav.addObject("article", found);
        mav.addObject("contentName", "/view/jsp/properties/company/updateCompany1.jsp");
        return mav;
    }

    public ModelAndView deleteCompany(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        System.out.println("Deleting Company ");
        setDefaultViewSet(mav, request);
        IDValuePair target = new SimpleCompany1();
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
            SimpleCompany1 read = (SimpleCompany1)CompanyDAO.read(SimpleCompany1.class, articleId);
            SimpleItem1 iRefer = new SimpleItem1();
            iRefer.setCompany_id(Integer.valueOf((int)articleId));
            long refer = CompanyDAO.count(iRefer);
            if(refer > 0L)
            {
                out.println("<script language=javascript>");
                out.println("alert('\uC81C\uD488\uCF54\uB4DC\uC5D0\uC11C \uD574\uB2F9 \uCF54\uB4DC\uAC00 \uC0AC\uC6A9\uC911\uC785\uB2C8\uB2E4.');");
                out.println((new StringBuilder("location.href = 'safetyCompany.do?action=readCompany&menu=5&articleId=")).append(articleId).append("'").toString());
                out.println("</script>");
                return null;
            }
            Set history = read.getCompanyHistory();
            History h;
            for(Iterator iterator = history.iterator(); iterator.hasNext(); CompanyDAO.delete(h))
            {
                h = (History)iterator.next();
                CompanyDAO.clearJunction("SGI_JUNC_COMPANY_HISTORY", "COMPANY_ID", articleId);
            }

            CompanyDAO.delete(target);
            mav.addObject("returnUrl", "safetyCompany.do?action=listCompany");
            mav.setViewName("safetyCompany.do?action=listCompany");
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

    public ModelAndView deleteCompanyHistory(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        IDValuePair target = new SimpleCompanyHistory1();
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
            SimpleCompanyHistory1 history = (SimpleCompanyHistory1)CompanyDAO.read(SimpleCompanyHistory1.class, articleId);
            SimpleJuncCompany sjc = new SimpleJuncCompany();
            sjc.setHistory_id((new Long(articleId)).intValue());
            CompanyDAO.clearJunction("SGI_JUNC_COMPANY_HISTORY", "HISTORY_ID", articleId);
            CompanyDAO.delete(target);
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

    public ModelAndView CompanyChk(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        ModelAndView mav = new ModelAndView();
        PrintWriter out = response.getWriter();
        SimpleCompany1 company = new SimpleCompany1();
        String meddev_entp_no = Function.nullChk(request.getParameter("meddev_entp_no"), "");
        String cob_flag_code = Function.nullChk(request.getParameter("cob_flag_code"), "");
        System.out.println((new StringBuilder("company.meddev_entp_no      ==")).append(meddev_entp_no).toString());
        System.out.println((new StringBuilder("company.cob_flag_code      ==")).append(cob_flag_code).toString());
        long total = CompanyDAO.countCompany(meddev_entp_no, cob_flag_code);
        System.out.println((new StringBuilder("company.total      ==")).append(total).toString());
        out.println(total);
        out.close();
        return mav;
    }

    private CompanyDAO CompanyDAO;
}

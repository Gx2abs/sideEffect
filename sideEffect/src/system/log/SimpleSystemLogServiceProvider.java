// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemLogServiceProvider.java

package system.log;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;
import kr.co.sgis.legacy.common.Function;
import kr.co.sgis.legacy.common.Page;
import member.Member;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package system.log:
//            SystemLogService, SimpleSystemLog, SystemLog, SystemLogDAO

public class SimpleSystemLogServiceProvider
    implements SystemLogService
{

    public SimpleSystemLogServiceProvider()
    {
    }

    public SystemLogDAO getSystemLogDAO()
    {
        return systemLogDAO;
    }

    public void setSystemLogDAO(SystemLogDAO systemLogDAO)
    {
        this.systemLogDAO = systemLogDAO;
    }

    public String buildLogString(ServletRequest servletRequest)
    {
        StringBuilder sb = new StringBuilder();
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        sb.append((new StringBuilder("request URL := ")).append(httpServletRequest.getRequestURL()).toString());
        sb.append((new StringBuilder("queryString := ")).append(httpServletRequest.getQueryString()).toString());
        return sb.toString();
    }

    public SystemLog buildSystemLog(ServletRequest servletRequest)
    {
        SystemLog log = new SimpleSystemLog();
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        log.setRequestURL(httpServletRequest.getRequestURL().toString());
        log.setQueryString(httpServletRequest.getQueryString());
        log.setLogTime(new Date());
        HttpSession session = httpServletRequest.getSession();
        Object tmp = session.getAttribute("user");
        if(tmp != null && (tmp instanceof Member))
            log.setUserId(((Member)tmp).getId());
        else
            System.out.println("user not found in session");
        return log;
    }

    public SystemLog doLog(ServletRequest servletRequest)
    {
        SystemLog log = buildSystemLog(servletRequest);
        SystemLog justCreated = null;
        if(log != null)
        {
            justCreated = (SystemLog)systemLogDAO.create(log);
            System.out.println(log);
        }
        System.out.println(justCreated);
        return justCreated;
    }

    public ModelAndView listSystemLog(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new ModelAndView();
        int pg = Function.nullChk(request.getParameter("pg"), 1);
        setDefaultViewSet(mav, request);
        SimpleSystemLog log = new SimpleSystemLog();
        String name = request.getParameter("searchKeyword") != "" ? request.getParameter("searchKeyword") : null;
        log.setPropertyValue(name);
        long total = systemLogDAO.count(log).longValue();
        List dl = null;
        if(total > 0L)
        {
            System.out.println((new StringBuilder("nb of found items : ")).append(total).append(", proceeding to list").toString());
            dl = systemLogDAO.list(log, pg, 10);
        }
        long top = total - (long)((pg - 1) * 10);
        Page page = new Page();
        String pageString = page.pageList((int)total, 10, pg, "system.do?action=listBatchJobGroups", "", request);
        mav.addObject("top", Long.valueOf(top));
        mav.addObject("total", Long.valueOf(total));
        mav.addObject("list", dl);
        mav.addObject("pageString", pageString);
        mav.addObject("titleImg", "view/style/images/title/sub01_03.gif");
        mav.addObject("titleImage", "view/style/images/title/sign/top_img5.jpg");
        mav.addObject("contentName", "/view/jsp/system/logging/listSystemLog1.jsp");
        return mav;
    }

    public ModelAndView setDefaultViewSet(ModelAndView mav, HttpServletRequest request)
    {
        mav.setViewName("/view/jsp/template/defaultView.jsp");
        mav.addObject("footerName", "/view/jsp/common/defaultFooter.jsp");
        mav.addObject("headName", "/view/jsp/common/defaultHead.jsp");
        mav.addObject("leftName", "/view/jsp/system/systemLeft.jsp");
        mav.addObject("rightName", request.getAttribute("rightName"));
        return mav;
    }

    private SystemLogDAO systemLogDAO;
}

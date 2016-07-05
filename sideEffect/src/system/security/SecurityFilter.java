// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SecurityFilter.java

package system.security;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.*;
import javax.servlet.http.*;
import member.Member;
import member.MemberDAO;

// Referenced classes of package system.security:
//            UserTracer

public class SecurityFilter
    implements Filter
{

    public SecurityFilter()
    {
    }

    public MemberDAO getMemberDAO()
    {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO)
    {
        this.memberDAO = memberDAO;
    }

    public UserTracer getUserTracer()
    {
        return userTracer;
    }

    public void setUserTracer(UserTracer userTracer)
    {
        this.userTracer = userTracer;
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        System.out.println("Spring filter :)");
        userTracer.doTrace((HttpServletRequest)servletRequest);
        String id = "";
        String password = "";
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        if(isLogin(servletRequest))
        {
            httpServletRequest.setAttribute("rightName", "/view/jsp/members/loginBox.jsp");
            filterChain.doFilter(servletRequest, servletResponse);
        } else
        {
            httpServletRequest.setAttribute("rightName", "/view/jsp/common/defaultRight.jsp");
            httpServletRequest.getRequestDispatcher("members.do?action=authenticateMemberPage").forward(httpServletRequest, httpServletResponse);
        }
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    private boolean isLogin(ServletRequest servletRequest)
    {
        boolean isLogin = false;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("isLogin ? ");
        String servletPath = request.getServletPath();
        String url = request.getQueryString();
        if(url == null)
            url = "";
        System.out.println((new StringBuilder("  ")).append(servletPath).append(" ").append(url).toString());
        if("/authenticator.do".equals(servletPath) || "/statistics.do".equals(servletPath) || "/sideEffectReport.do".equals(servletPath) || "/safetyReport.do".equals(servletPath) || "/risk.do".equals(servletPath) || "/risk.category.do".equals(servletPath))
        {
            if("/sideEffectReport.do".equals(servletPath) && url.equals("action=list"))
                return sessionCheck(servletRequest);
            if("/safetyReport.do".equals(servletPath) && url.equals("action=list"))
                return sessionCheck(servletRequest);
            if("/statistics.do".equals(servletPath) && url.equals("action=xpSideEffectStatisticsAndReport"))
                return sessionCheck(servletRequest);
            if("/statistics.do".equals(servletPath) && url.equals("action=xpSideEffectStatisticsAndReport"))
                return sessionCheck(servletRequest);
            if("/statistics.do".equals(servletPath) && url.equals("action=xpSideEffectItemStatistics"))
                return sessionCheck(servletRequest);
            if("/statistics.do".equals(servletPath) && url.equals("action=xpSafetyStatisticsAndReport"))
                return sessionCheck(servletRequest);
            if("/statistics.do".equals(servletPath) && url.equals("action=xpSafetyItemStatistics"))
                return sessionCheck(servletRequest);
            if("/risk.do".equals(servletPath) && url.equals("action=xpRiskLink"))
            {
                return sessionCheck(servletRequest);
            } else
            {
                System.out.println("bypassing security filter to authenticate first");
                return true;
            }
        } else
        {
            isLogin = sessionCheck(servletRequest);
            return isLogin;
        }
    }

    public boolean sessionCheck(ServletRequest servletRequest)
    {
        boolean result = false;
        String sessionUserId = "";
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            System.out.println("isLogin ? session!=null ");
            Object obj = session.getAttribute("user");
            if(obj != null && (obj instanceof Member))
            {
                System.out.println("isLogin ? obj!=null ");
                Member m = (Member)obj;
                sessionUserId = m.getAccountName();
                if(!"".equals(sessionUserId.trim()))
                    result = true;
            }
        }
        return result;
    }

    private UserTracer userTracer;
    private MemberDAO memberDAO;
}

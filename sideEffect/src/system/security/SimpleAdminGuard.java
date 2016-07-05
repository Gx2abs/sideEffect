// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAdminGuard.java

package system.security;

import common.Function;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.*;
import javax.servlet.http.*;
import member.Member;

// Referenced classes of package system.security:
//            AllowedService

public class SimpleAdminGuard
    implements Filter
{

    public SimpleAdminGuard()
    {
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        String action = Function.nullChk(httpServletRequest.getParameter("action"), "unauthorised");
        if(isLogin(servletRequest))
        {
            httpServletRequest.setAttribute("rightName", "/view/jsp/members/loginBox.jsp");
            filterChain.doFilter(servletRequest, servletResponse);
        } else
        if(isAllowed(action))
        {
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

    private boolean isAllowed(String action)
    {
        boolean isAllowed = false;
        AllowedService aallowedservice[];
        int j = (aallowedservice = AllowedService.values()).length;
        for(int i = 0; i < j; i++)
        {
            AllowedService service = aallowedservice[i];
            if(action.equalsIgnoreCase(service.toString()))
                isAllowed = true;
        }

        return isAllowed;
    }

    private boolean isLogin(ServletRequest servletRequest)
    {
        boolean isLogin = false;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("isLogin ? ");
        HttpSession session = request.getSession(false);
        String sessionUserId = "";
        String servletPath = request.getServletPath();
        if("/authenticator.do".equals(servletPath) || "/statistics.do".equals(servletPath) || "/sideEffectReport.do".equals(servletPath) || "/safetyReport.do".equals(servletPath))
        {
            System.out.println("bypassing security filter to authenticate first");
            return true;
        }
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
                {
                    Long privId = Long.valueOf(m.getPrivilegeId());
                    if(privId != null)
                        if(privId.longValue() == 2L)
                            isLogin = true;
                        else
                            isLogin = false;
                }
            }
        }
        return isLogin;
    }
}

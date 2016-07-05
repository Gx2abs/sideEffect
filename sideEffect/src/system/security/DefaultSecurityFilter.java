// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultSecurityFilter.java

package system.security;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class DefaultSecurityFilter
    implements Filter
{

    public DefaultSecurityFilter()
    {
        testParam = "";
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        System.out.println("DefaultSecurityFilter.doFilter()   : )");
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        javax.servlet.http.HttpSession session = httpServletRequest.getSession();
        System.out.println((new StringBuilder("DefaultSecurityFilter.doFilter()testParam : ")).append(testParam).toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        testParam = filterConfig.getInitParameter("testParam1");
        encoding = filterConfig.getInitParameter("encoding");
    }

    private String testParam;
    String encoding;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XSSFilter.java

package system.security;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

// Referenced classes of package system.security:
//            RequestWrapper

public class XSSFilter
    implements Filter
{

    public XSSFilter()
    {
    }

    public void destroy()
    {
        System.out.println("[XSSFilter.java].destroy().run");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        System.out.println("[XSSFilter.java].doFilter().run");
        RequestWrapper wrapper = new RequestWrapper((HttpServletRequest)servletRequest);
        filterChain.doFilter(wrapper, servletResponse);
    }

    public void init(FilterConfig arg0)
        throws ServletException
    {
        System.out.println("[XSSFilter.java].init().run");
    }
}

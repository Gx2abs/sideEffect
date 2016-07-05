// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemLogFilter.java

package system.log;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.*;

// Referenced classes of package system.log:
//            SystemLogService

public class SimpleSystemLogFilter
    implements Filter
{

    public SimpleSystemLogFilter()
    {
    }

    public SystemLogService getSystemLogService()
    {
        return systemLogService;
    }

    public void setSystemLogService(SystemLogService systemLogService)
    {
        this.systemLogService = systemLogService;
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        try
        {
            System.out.println("SimpleSystemLogFilter :)");
            systemLogService.doLog(servletRequest);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }finally{
			filterChain.doFilter(servletRequest, servletResponse);
		}
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    private SystemLogService systemLogService;
}

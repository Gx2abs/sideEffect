// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemPropertyDelegate.java

package system.property;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package system.property:
//            SystemPropertyServiceProvider

public class SimpleSystemPropertyDelegate
{

    public SimpleSystemPropertyDelegate()
    {
    }

    public SystemPropertyServiceProvider getSystemPropertyServiceProvider()
    {
        return systemPropertyServiceProvider;
    }

    public void setSystemPropertyServiceProvider(SystemPropertyServiceProvider systemPropertyServiceProvider)
    {
        this.systemPropertyServiceProvider = systemPropertyServiceProvider;
    }

    public ModelAndView listSystemProperties(HttpServletRequest request, HttpServletResponse response)
    {
        return systemPropertyServiceProvider.listSystemProperties(request, response);
    }

    public ModelAndView readSystemProperty(HttpServletRequest request, HttpServletResponse response)
    {
        return systemPropertyServiceProvider.readSystemProperty(request, response);
    }

    public ModelAndView updateSystemProperty(HttpServletRequest request, HttpServletResponse response)
    {
        return systemPropertyServiceProvider.updateSystemProperty(request, response);
    }

    public ModelAndView updateSystemPropertyPage(HttpServletRequest request, HttpServletResponse response)
    {
        return systemPropertyServiceProvider.updateSystemPropertyPage(request, response);
    }

    SystemPropertyServiceProvider systemPropertyServiceProvider;
}

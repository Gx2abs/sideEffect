// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemPropertyServiceProvider.java

package system.property;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package system.property:
//            SystemPropertyDAO

public interface SystemPropertyServiceProvider
{

    public abstract SystemPropertyDAO getSystemPropertyDAO();

    public abstract void setSystemPropertyDAO(SystemPropertyDAO systempropertydao);

    public abstract ModelAndView listSystemProperties(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView readSystemProperty(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateSystemProperty(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateSystemPropertyPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompanyServiceProvider.java

package safety.renewal.company;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface CompanyServiceProvider
{

    public abstract ModelAndView listCompany(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createCompanyPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView createCompany(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView readCompany(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateCompanyPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView updateCompany(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteCompany(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView deleteCompanyHistory(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse);

    public abstract ModelAndView CompanyChk(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException;
}

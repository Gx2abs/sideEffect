// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCompanyDelegate.java

package safety.renewal.company;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package safety.renewal.company:
//            CompanyServiceProvider

public class SimpleCompanyDelegate
{

    public SimpleCompanyDelegate()
    {
    }

    public CompanyServiceProvider getCompanyServiceProvider()
    {
        return CompanyServiceProvider;
    }

    public void setCompanyServiceProvider(CompanyServiceProvider publicationServiceProvider)
    {
        CompanyServiceProvider = publicationServiceProvider;
    }

    public ModelAndView test(HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }

    public ModelAndView listCompany(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.listCompany(request, response);
    }

    public ModelAndView createCompanyPage(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.createCompanyPage(request, response);
    }

    public ModelAndView createCompany(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.createCompany(request, response);
    }

    public ModelAndView readCompany(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.readCompany(request, response);
    }

    public ModelAndView updateCompany(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.updateCompany(request, response);
    }

    public ModelAndView updateCompanyPage(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.updateCompanyPage(request, response);
    }

    public ModelAndView deleteCompany(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.deleteCompany(request, response);
    }

    public ModelAndView deleteCompanyHistory(HttpServletRequest request, HttpServletResponse response)
    {
        return CompanyServiceProvider.deleteCompanyHistory(request, response);
    }

    public ModelAndView CompanyChk(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        return CompanyServiceProvider.CompanyChk(request, response);
    }

    private CompanyServiceProvider CompanyServiceProvider;
}

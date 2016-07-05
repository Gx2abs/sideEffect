// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleImportOutputDelegate.java

package risk.item.importoutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

// Referenced classes of package risk.item.importoutput:
//            ImportOutputServiceProvider

public class SimpleImportOutputDelegate
{

    public SimpleImportOutputDelegate()
    {
    }

    public ImportOutputServiceProvider getImportOutputServiceProvider()
    {
        return ImportOutputServiceProvider;
    }

    public void setImportOutputServiceProvider(ImportOutputServiceProvider importOutputServiceProvider)
    {
        ImportOutputServiceProvider = importOutputServiceProvider;
    }

    public ModelAndView listImport(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.listImport(request, response);
    }

    public ModelAndView readImport(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.readImport(request, response);
    }

    public ModelAndView createImportOutputPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.createImportOutputPage(request, response);
    }

    public ModelAndView createImport(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.createImport(request, response);
    }

    public ModelAndView updateImportPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.updateImportPage(request, response);
    }

    public ModelAndView updateImport(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.updateImport(request, response);
    }

    public ModelAndView deleteImport(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.deleteImport(request, response);
    }

    public ModelAndView deleteImportHistory(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.deleteImportHistory(request, response);
    }

    public ModelAndView listOutput(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.listOutput(request, response);
    }

    public ModelAndView readOutput(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.readOutput(request, response);
    }

    public ModelAndView createOutput(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.createOutput(request, response);
    }

    public ModelAndView updateOutputPage(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.updateOutputPage(request, response);
    }

    public ModelAndView updateOutput(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.updateOutput(request, response);
    }

    public ModelAndView deleteOutput(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.deleteOutput(request, response);
    }

    public ModelAndView deleteOutputHistory(HttpServletRequest request, HttpServletResponse response)
    {
        return ImportOutputServiceProvider.deleteOutputHistory(request, response);
    }

    private ImportOutputServiceProvider ImportOutputServiceProvider;
}

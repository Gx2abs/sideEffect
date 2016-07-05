// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemDelegate.java

package system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import system.batch.SimpleBatchJobServiceProvider;
import system.log.SimpleSystemLogServiceProvider;
import system.property.SimpleSystemPropertyServiceProvider;

public class SimpleSystemDelegate
{

    public SimpleSystemDelegate()
    {
    }

    public SimpleSystemLogServiceProvider getSystemLogServiceProvider()
    {
        return systemLogServiceProvider;
    }

    public void setSystemLogServiceProvider(SimpleSystemLogServiceProvider systemLogServiceProvider)
    {
        this.systemLogServiceProvider = systemLogServiceProvider;
    }

    public SimpleSystemPropertyServiceProvider getSystemPropertyServiceProvider()
    {
        return systemPropertyServiceProvider;
    }

    public void setSystemPropertyServiceProvider(SimpleSystemPropertyServiceProvider systemPropertyServiceProvider)
    {
        this.systemPropertyServiceProvider = systemPropertyServiceProvider;
    }

    public SimpleBatchJobServiceProvider getBatchJobServiceProvider()
    {
        return batchJobServiceProvider;
    }

    public void setBatchJobServiceProvider(SimpleBatchJobServiceProvider batchJobHistoryServiceProvider)
    {
        batchJobServiceProvider = batchJobHistoryServiceProvider;
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

    public ModelAndView listBatchJobGroups(HttpServletRequest request, HttpServletResponse response)
    {
        return batchJobServiceProvider.listBatchJobGroups(request, response);
    }

    public ModelAndView createBatchJob(HttpServletRequest request, HttpServletResponse response)
    {
        return batchJobServiceProvider.createBatchJob(request, response);
    }

    public ModelAndView createBatchJobPage(HttpServletRequest request, HttpServletResponse response)
    {
        return batchJobServiceProvider.createBatchJobPage(request, response);
    }

    public ModelAndView openBatchJobListPopup(HttpServletRequest request, HttpServletResponse response)
    {
        return batchJobServiceProvider.openBatchJobListPopup(request, response);
    }

    public ModelAndView applyBatchJob(HttpServletRequest request, HttpServletResponse response)
    {
        return batchJobServiceProvider.applyBatchJob(request, response);
    }

    public ModelAndView listSystemLog(HttpServletRequest request, HttpServletResponse response)
    {
        return systemLogServiceProvider.listSystemLog(request, response);
    }

    public ModelAndView downloadFormat(HttpServletRequest request, HttpServletResponse response)
    {
        return batchJobServiceProvider.downloadFormat(request, response);
    }

    private SimpleSystemPropertyServiceProvider systemPropertyServiceProvider;
    private SimpleBatchJobServiceProvider batchJobServiceProvider;
    private SimpleSystemLogServiceProvider systemLogServiceProvider;
}

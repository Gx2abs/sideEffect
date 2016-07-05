// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleReportType.java

package properties.report.type;

import java.util.*;
import properties.SimpleIsInUse;

// Referenced classes of package properties.report.type:
//            ReportType

public class SimpleReportType
    implements ReportType
{

    public SimpleReportType()
    {
        reportTypeHistory = new HashSet();
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public Set getReportTypeHistory()
    {
        return reportTypeHistory;
    }

    public void setReportTypeHistory(Set reportTypeHistory)
    {
        this.reportTypeHistory = reportTypeHistory;
    }

    public Date getFirstModified()
    {
        return firstModified;
    }

    public void setFirstModified(Date firstModified)
    {
        this.firstModified = firstModified;
    }

    public int getFirstResult()
    {
        return firstResult;
    }

    public void setFirstResult(int firstResult)
    {
        this.firstResult = firstResult;
    }

    public int getMaxResults()
    {
        return maxResults;
    }

    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(int isInUse)
    {
        this.isInUse = isInUse;
    }

    private long id;
    private String propertyValue;
    private Set reportTypeHistory;
    private Date firstModified;
    private int firstResult;
    private int maxResults;
    private int page;
    private int isInUse;
    private SimpleIsInUse useStatus;
}

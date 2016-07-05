// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCountry.java

package properties.country;

import java.util.*;
import properties.SimpleIsInUse;

// Referenced classes of package properties.country:
//            Country

public class SimpleCountry
    implements Country
{

    public SimpleCountry()
    {
        countryHistory = new HashSet();
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public int getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(int isInUse)
    {
        this.isInUse = isInUse;
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

    public Set getCountryHistory()
    {
        return countryHistory;
    }

    public void setCountryHistory(Set countryHistory)
    {
        this.countryHistory = countryHistory;
    }

    public Date getFirstModified()
    {
        return firstModified;
    }

    public void setFirstModified(Date firstModified)
    {
        this.firstModified = firstModified;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
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

    public Date getActiveFrom()
    {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom)
    {
        this.activeFrom = activeFrom;
    }

    private long id;
    private String propertyValue;
    private Set countryHistory;
    private Date firstModified;
    private Date lastModified;
    private Date activeFrom;
    private int firstResult;
    private int maxResults;
    private int page;
    private int isInUse;
    private SimpleIsInUse useStatus;
}

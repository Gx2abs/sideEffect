// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideeffectResult.java

package sideeffect;

import java.util.*;
import properties.SimpleIsInUse;

// Referenced classes of package sideeffect:
//            SideeffectResult

public class SimpleSideeffectResult
    implements SideeffectResult
{

    public SimpleSideeffectResult()
    {
        severityHistory = new HashSet();
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

    public String getSeverityName()
    {
        return severityName;
    }

    public void setSeverityName(String severityName)
    {
        this.severityName = severityName;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public int getIsFromExcel()
    {
        return isFromExcel;
    }

    public void setIsFromExcel(int isFromExcel)
    {
        this.isFromExcel = isFromExcel;
    }

    public int getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(int isInUse)
    {
        this.isInUse = isInUse;
    }

    public Integer getDepthLevel()
    {
        return depthLevel;
    }

    public void setDepthLevel(Integer depthLevel)
    {
        this.depthLevel = depthLevel;
    }

    public long getParentId()
    {
        return parentId;
    }

    public void setParentId(long parentId)
    {
        this.parentId = parentId;
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public Set getSeverityHistory()
    {
        return severityHistory;
    }

    public void setSeverityHistory(Set severityHistory)
    {
        this.severityHistory = severityHistory;
    }

    private long id;
    private String propertyValue;
    private String severityName;
    private int value;
    private Date startDate;
    private Date lastModified;
    private int isFromExcel;
    private int isInUse;
    private long parentId;
    private Integer depthLevel;
    private SimpleIsInUse useStatus;
    private Set severityHistory;
}

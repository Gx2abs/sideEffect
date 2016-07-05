// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleTmpMedicalDeviceMalfunctionCode.java

package system.batch;

import java.util.*;
import properties.MedicalDeviceMalfunctionCode;
import properties.SimpleIsInUse;

public class SimpleTmpMedicalDeviceMalfunctionCode
    implements MedicalDeviceMalfunctionCode
{

    public SimpleTmpMedicalDeviceMalfunctionCode()
    {
        medicalDeviceMalfunctionHistory = new HashSet();
    }

    public Long getBatchGroupId()
    {
        return batchGroupId;
    }

    public void setBatchGroupId(Long batchGroupId)
    {
        this.batchGroupId = batchGroupId;
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFdaCode()
    {
        return fdaCode;
    }

    public void setFdaCode(String fdaCode)
    {
        this.fdaCode = fdaCode;
    }

    public String getFdaSourcePtKor()
    {
        return fdaSourcePtKor;
    }

    public void setFdaSourcePtKor(String fdaSourcePtKor)
    {
        this.fdaSourcePtKor = fdaSourcePtKor;
    }

    public String getFdaSourceDefinition()
    {
        return fdaSourceDefinition;
    }

    public void setFdaSourceDefinition(String fdaSourceDefinition)
    {
        this.fdaSourceDefinition = fdaSourceDefinition;
    }

    public String getFdaSourceDefinitionKor()
    {
        return fdaSourceDefinitionKor;
    }

    public void setFdaSourceDefinitionKor(String fdaSourceDefinitionKor)
    {
        this.fdaSourceDefinitionKor = fdaSourceDefinitionKor;
    }

    public String getNcitDefinition()
    {
        return ncitDefinition;
    }

    public void setNcitDefinition(String ncitDefinition)
    {
        this.ncitDefinition = ncitDefinition;
    }

    public String getNcitDefinitionKor()
    {
        return ncitDefinitionKor;
    }

    public void setNcitDefinitionKor(String ncitDefinitionKor)
    {
        this.ncitDefinitionKor = ncitDefinitionKor;
    }

    public String getNciCode()
    {
        return nciCode;
    }

    public void setNciCode(String nciCode)
    {
        this.nciCode = nciCode;
    }

    public int getDepthLevel()
    {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel)
    {
        this.depthLevel = depthLevel;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public Set getMedicalDeviceMalfunctionHistory()
    {
        return medicalDeviceMalfunctionHistory;
    }

    public void setMedicalDeviceMalfunctionHistory(Set medicalDeviceMalfunctionHistory)
    {
        this.medicalDeviceMalfunctionHistory = medicalDeviceMalfunctionHistory;
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

    public Date getActiveFrom()
    {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom)
    {
        this.activeFrom = activeFrom;
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
    private Set medicalDeviceMalfunctionHistory;
    private Date firstModified;
    private Date lastModified;
    private Date activeFrom;
    private int firstResult;
    private int maxResults;
    private int page;
    private int isInUse;
    private String name;
    private String fdaCode;
    private String fdaSourcePtKor;
    private String fdaSourceDefinition;
    private String fdaSourceDefinitionKor;
    private String ncitDefinition;
    private String ncitDefinitionKor;
    private String nciCode;
    private int parentId;
    private int depthLevel;
    private Long batchGroupId;
    private SimpleIsInUse useStatus;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMedicalDeviceMalfunctionHistory.java

package properties.medical.device;

import abstraction.AbstractIDValuePair;
import java.util.Date;

// Referenced classes of package properties.medical.device:
//            MedicalDeviceMalfunctionHistory

public class SimpleMedicalDeviceMalfunctionHistory extends AbstractIDValuePair
    implements MedicalDeviceMalfunctionHistory
{

    public SimpleMedicalDeviceMalfunctionHistory()
    {
    }

    public long getMedicalDeviceMalfunctionId()
    {
        return medicalDeviceMalfunctionId;
    }

    public void setMedicalDeviceMalfunctionId(long medicalDeviceMalfunctionId)
    {
        this.medicalDeviceMalfunctionId = medicalDeviceMalfunctionId;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getActiveFrom()
    {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom)
    {
        this.activeFrom = activeFrom;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public int getHistoryType()
    {
        return historyType;
    }

    public void setHistoryType(int historyType)
    {
        this.historyType = historyType;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getHistoryDescription()
    {
        return historyDescription;
    }

    public void setHistoryDescription(String historyDescription)
    {
        this.historyDescription = historyDescription;
    }

    public long getParentId()
    {
        return getMedicalDeviceMalfunctionId();
    }

    public void setParentId(long parentId)
    {
        setMedicalDeviceMalfunctionId(parentId);
    }

    private long id;
    private Date activeFrom;
    private Date lastModified;
    private int historyType;
    private String manager;
    private String historyDescription;
    private long medicalDeviceMalfunctionId;
}

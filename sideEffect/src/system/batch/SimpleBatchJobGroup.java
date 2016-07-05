// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleBatchJobGroup.java

package system.batch;

import java.util.Date;
import java.util.List;

// Referenced classes of package system.batch:
//            BatchJobGroup, SimpleBatchJobStatus, SimpleBatchJobTableType, SimpleMoveJobStatus

public class SimpleBatchJobGroup
    implements BatchJobGroup
{

    public SimpleBatchJobGroup()
    {
    }

    public Long getMoveJobStatus()
    {
        return moveJobStatus;
    }

    public void setMoveJobStatus(Long moveJobStatus)
    {
        this.moveJobStatus = moveJobStatus;
    }

    public SimpleMoveJobStatus getMoveJobStatusObject()
    {
        return moveJobStatusObject;
    }

    public void setMoveJobStatusObject(SimpleMoveJobStatus moveJobStatusObject)
    {
        this.moveJobStatusObject = moveJobStatusObject;
    }

    public SimpleBatchJobTableType getTableType()
    {
        return tableType;
    }

    public void setTableType(SimpleBatchJobTableType tableType)
    {
        this.tableType = tableType;
    }

    public Long getTableTypeId()
    {
        return tableTypeId;
    }

    public void setTableTypeId(Long tableTypeId)
    {
        this.tableTypeId = tableTypeId;
    }

    public SimpleBatchJobStatus getJobStatusObject()
    {
        return jobStatusObject;
    }

    public void setJobStatusObject(SimpleBatchJobStatus jobStatusObject)
    {
        this.jobStatusObject = jobStatusObject;
    }

    public Long getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(Long jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public Long getNumberOfSuccess()
    {
        return numberOfSuccess;
    }

    public void setNumberOfSuccess(Long numberOfSuccess)
    {
        this.numberOfSuccess = numberOfSuccess;
    }

    public Long getNumberOfFailure()
    {
        return numberOfFailure;
    }

    public void setNumberOfFailure(Long numberOfFailure)
    {
        this.numberOfFailure = numberOfFailure;
    }

    public List getBatchList()
    {
        return batchList;
    }

    public void setBatchList(List batchList)
    {
        this.batchList = batchList;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Long getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(Long serviceId)
    {
        this.serviceId = serviceId;
    }

    public Long getIsActive()
    {
        return isActive;
    }

    public void setIsActive(Long isActive)
    {
        this.isActive = isActive;
    }

    public Date getActiveFrom()
    {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom)
    {
        this.activeFrom = activeFrom;
    }

    public String getPhysicalSourcePath()
    {
        return physicalSourcePath;
    }

    public void setPhysicalSourcePath(String physicalSourcePath)
    {
        this.physicalSourcePath = physicalSourcePath;
    }

    public String getLogicalSourcePath()
    {
        return logicalSourcePath;
    }

    public void setLogicalSourcePath(String logicalSourcePath)
    {
        this.logicalSourcePath = logicalSourcePath;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private Long serviceId;
    private Long isActive;
    private Date activeFrom;
    private String physicalSourcePath;
    private String logicalSourcePath;
    private Long numberOfSuccess;
    private Long numberOfFailure;
    private Long jobStatus;
    private SimpleBatchJobStatus jobStatusObject;
    private List batchList;
    private SimpleBatchJobTableType tableType;
    private Long tableTypeId;
    private Long moveJobStatus;
    private SimpleMoveJobStatus moveJobStatusObject;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleBatchJob.java

package system.batch;


// Referenced classes of package system.batch:
//            SimpleBatchJobStatus

public class SimpleBatchJob
{

    public SimpleBatchJob()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getBatchGroupId()
    {
        return batchGroupId;
    }

    public void setBatchGroupId(long batchGrouId)
    {
        batchGroupId = batchGrouId;
    }

    public long getJobStatusId()
    {
        return jobStatusId;
    }

    public void setJobStatusId(long jobStatusId)
    {
        this.jobStatusId = jobStatusId;
    }

    public SimpleBatchJobStatus getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(SimpleBatchJobStatus jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private long batchGroupId;
    private long jobStatusId;
    private SimpleBatchJobStatus jobStatus;
}

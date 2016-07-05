// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSafetyReportMebTypeInfo.java

package properties;


// Referenced classes of package properties:
//            safetyReportMebTypeInfo

public class SimpleSafetyReportMebTypeInfo
    implements safetyReportMebTypeInfo
{

    public SimpleSafetyReportMebTypeInfo()
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

    public int getReport_id()
    {
        return report_id;
    }

    public void setReport_id(int report_id)
    {
        this.report_id = report_id;
    }

    public int getMeddev_item_seq()
    {
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(int meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    private long id;
    private int report_id;
    private int meddev_item_seq;
}

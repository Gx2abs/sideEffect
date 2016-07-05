// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSafetyMedicalCode.java

package safety;


public class JuncSafetyMedicalCode
{

    public JuncSafetyMedicalCode()
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

    public long getReport_id()
    {
        return report_id;
    }

    public void setReport_id(long report_id)
    {
        this.report_id = report_id;
    }

    public long getMedical_id()
    {
        return medical_id;
    }

    public void setMedical_id(long medical_id)
    {
        this.medical_id = medical_id;
    }

    private long id;
    private long report_id;
    private long medical_id;
}

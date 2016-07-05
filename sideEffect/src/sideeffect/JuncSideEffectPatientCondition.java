// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSideEffectPatientCondition.java

package sideeffect;


public class JuncSideEffectPatientCondition
{

    public JuncSideEffectPatientCondition()
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

    public long getPatient_id()
    {
        return patient_id;
    }

    public void setPatient_id(long patient_id)
    {
        this.patient_id = patient_id;
    }

    public String toString()
    {
        return (new StringBuilder("JuncSideEffectPatientCondition [id=")).append(id).append(", report_id=").append(report_id).append(", patient_id=").append(patient_id).append(", getId()=").append(getId()).append(", getReport_id()=").append(getReport_id()).append(", getPatient_id()=").append(getPatient_id()).append(", getClass()=").append(getClass()).append(", hashCode()=").append(hashCode()).append(", toString()=").append(super.toString()).append("]").toString();
    }

    private long id;
    private long report_id;
    private long patient_id;
}

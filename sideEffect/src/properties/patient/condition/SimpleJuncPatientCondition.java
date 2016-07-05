// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncPatientCondition.java

package properties.patient.condition;


// Referenced classes of package properties.patient.condition:
//            JuncPatientCondition

public class SimpleJuncPatientCondition
    implements JuncPatientCondition
{

    public SimpleJuncPatientCondition()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPatientCondition_id()
    {
        return patientCondition_id;
    }

    public void setPatientCondition_id(int patientCondition_id)
    {
        this.patientCondition_id = patientCondition_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private int id;
    private int patientCondition_id;
    private int history_id;
}

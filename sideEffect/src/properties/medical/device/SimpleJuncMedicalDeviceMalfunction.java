// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncMedicalDeviceMalfunction.java

package properties.medical.device;


// Referenced classes of package properties.medical.device:
//            JuncMedicalDeviceMalfunction

public class SimpleJuncMedicalDeviceMalfunction
    implements JuncMedicalDeviceMalfunction
{

    public SimpleJuncMedicalDeviceMalfunction()
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

    public int getMedicalDeviceMalfunction_id()
    {
        return medicalDeviceMalfunction_id;
    }

    public void setMedicalDeviceMalfunction_id(int medicalDeviceMalfunction_id)
    {
        this.medicalDeviceMalfunction_id = medicalDeviceMalfunction_id;
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
    private int medicalDeviceMalfunction_id;
    private int history_id;
}

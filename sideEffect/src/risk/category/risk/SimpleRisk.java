// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleRisk.java

package risk.category.risk;


// Referenced classes of package risk.category.risk:
//            Risk

public class SimpleRisk
    implements Risk
{

    public SimpleRisk()
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

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public float getRiskGradeFm()
    {
        return riskGradeFm;
    }

    public void setRiskGradeFm(float riskGradeFm)
    {
        this.riskGradeFm = riskGradeFm;
    }

    public float getRiskGradeTo()
    {
        return riskGradeTo;
    }

    public void setRiskGradeTo(float riskGradeTo)
    {
        this.riskGradeTo = riskGradeTo;
    }

    public String getCorrectiveMeasure()
    {
        return correctiveMeasure;
    }

    public void setCorrectiveMeasure(String correctiveMeasure)
    {
        this.correctiveMeasure = correctiveMeasure;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public String getRevisionName()
    {
        return revisionName;
    }

    public void setRevisionName(String revisionName)
    {
        this.revisionName = revisionName;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private String className;
    private float riskGradeFm;
    private float riskGradeTo;
    private String correctiveMeasure;
    private int value;
    private String revisionName;
    private String color;
}

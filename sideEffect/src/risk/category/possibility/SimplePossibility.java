// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimplePossibility.java

package risk.category.possibility;


// Referenced classes of package risk.category.possibility:
//            Possibility

public class SimplePossibility
    implements Possibility
{

    public SimplePossibility()
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

    public float getLikelihoodFm()
    {
        return likelihoodFm;
    }

    public void setLikelihoodFm(float likelihoodFm)
    {
        this.likelihoodFm = likelihoodFm;
    }

    public float getLikelihoodTo()
    {
        return likelihoodTo;
    }

    public void setLikelihoodTo(float likelihoodTo)
    {
        this.likelihoodTo = likelihoodTo;
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

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private String className;
    private float likelihoodFm;
    private float likelihoodTo;
    private int value;
    private String revisionName;
}

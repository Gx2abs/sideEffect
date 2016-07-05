// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Risk.java

package risk.category.risk;

import abstraction.IDValuePair;

public interface Risk
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getClassName();

    public abstract void setClassName(String s);

    public abstract float getRiskGradeFm();

    public abstract void setRiskGradeFm(float f);

    public abstract float getRiskGradeTo();

    public abstract void setRiskGradeTo(float f);

    public abstract String getCorrectiveMeasure();

    public abstract void setCorrectiveMeasure(String s);

    public abstract int getValue();

    public abstract void setValue(int i);

    public abstract String getRevisionName();

    public abstract void setRevisionName(String s);

    public abstract String getColor();

    public abstract void setColor(String s);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Possibility.java

package risk.category.possibility;

import abstraction.IDValuePair;

public interface Possibility
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getClassName();

    public abstract void setClassName(String s);

    public abstract float getLikelihoodFm();

    public abstract void setLikelihoodFm(float f);

    public abstract float getLikelihoodTo();

    public abstract void setLikelihoodTo(float f);

    public abstract int getValue();

    public abstract void setValue(int i);

    public abstract String getRevisionName();

    public abstract void setRevisionName(String s);
}

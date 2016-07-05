// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemProperty.java

package system.property;

import abstraction.IDValuePair;

public interface SystemProperty
    extends IDValuePair
{

    public abstract String getPropertyName();

    public abstract void setPropertyName(String s);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MatchSlave.java

package properties.match;

import foreign.Interface_mea_class_no;

public interface MatchSlave
{

    public abstract int getId();

    public abstract void setId(int i);

    public abstract String getValue();

    public abstract void setValue(String s);

    public abstract Interface_mea_class_no[] getMatchingNewItems();

    public abstract void setMatchingNewItems(Interface_mea_class_no ainterface_mea_class_no[]);
}

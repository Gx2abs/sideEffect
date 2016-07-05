// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeList.java

package abstraction;


public interface TreeList
{

    public abstract void setParentId(Integer integer);

    public abstract Integer getParentId();

    public abstract void setLevelDepth(Integer integer);

    public abstract Integer getLevelDepth();
}

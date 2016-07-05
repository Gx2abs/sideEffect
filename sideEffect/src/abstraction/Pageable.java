// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pageable.java

package abstraction;


public interface Pageable
{

    public abstract int getMaxResults();

    public abstract void setMaxResults(int i);

    public abstract int getFirstResult();

    public abstract void setFirstResult(int i);

    public abstract void setPage(int i);

    public abstract int getPage();
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncRepoerAttachment.java

package system.io;

import abstraction.IDValuePair;

public interface JuncRepoerAttachment
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(int i);

    public abstract int getType();

    public abstract void setType(int i);

    public abstract int getTable_id();

    public abstract void setTable_id(int i);

    public abstract int getAttachment_id();

    public abstract void setAttachment_id(int i);
}

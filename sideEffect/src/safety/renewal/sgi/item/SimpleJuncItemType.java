// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemType.java

package safety.renewal.sgi.item;

import abstraction.AbstractIDValuePair;

// Referenced classes of package safety.renewal.sgi.item:
//            JuncItemType

public class SimpleJuncItemType extends AbstractIDValuePair
    implements JuncItemType
{

    public SimpleJuncItemType()
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

    public long getType_id()
    {
        return type_id;
    }

    public void setType_id(long type_id)
    {
        this.type_id = type_id;
    }

    public int getItem_id()
    {
        return item_id;
    }

    public void setItem_id(int item_id)
    {
        this.item_id = item_id;
    }

    private long id;
    private long type_id;
    private int item_id;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncProductType.java

package properties.product;

import abstraction.AbstractIDValuePair;

// Referenced classes of package properties.product:
//            JuncProductType

public class SimpleJuncProductType extends AbstractIDValuePair
    implements JuncProductType
{

    public SimpleJuncProductType()
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

    public int getMeddev_item_seq()
    {
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(int meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    private long id;
    private long type_id;
    private int meddev_item_seq;
}

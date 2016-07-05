// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleProductType.java

package properties.product;

import abstraction.AbstractIDValuePair;

// Referenced classes of package properties.product:
//            ProductType

public class SimpleProductType extends AbstractIDValuePair
    implements ProductType
{

    public SimpleProductType()
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

    public int getMeddev_item_seq()
    {
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(int meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    public String getType_name()
    {
        return type_name;
    }

    public void setType_name(String type_name)
    {
        this.type_name = type_name;
    }

    public int getDelete_yn()
    {
        return delete_yn;
    }

    public void setDelete_yn(int delete_yn)
    {
        this.delete_yn = delete_yn;
    }

    private long id;
    private int meddev_item_seq;
    private String type_name;
    private int delete_yn;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemType.java

package safety.renewal.sgi.item;

import abstraction.AbstractIDValuePair;

// Referenced classes of package safety.renewal.sgi.item:
//            ItemType

public class SimpleItemType extends AbstractIDValuePair
    implements ItemType
{

    public SimpleItemType()
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

    public int getItem_id()
    {
        return item_id;
    }

    public void setItem_id(int item_id)
    {
        this.item_id = item_id;
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
    private int item_id;
    private String type_name;
    private int delete_yn;
}

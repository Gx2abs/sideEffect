// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemOutput.java

package risk.item.importoutput;

import java.util.HashSet;
import java.util.Set;
import safety.renewal.sgi.item.SimpleItem1;

// Referenced classes of package risk.item.importoutput:
//            ItemOutput

public class SimpleItemOutput
    implements ItemOutput
{

    public SimpleItemOutput()
    {
        itemOutputHistory = new HashSet();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public float getItemOutput()
    {
        return ItemOutput;
    }

    public void setItemOutput(float itemOutput)
    {
        ItemOutput = itemOutput;
    }

    public float getOutputAmount()
    {
        return OutputAmount;
    }

    public void setOutputAmount(float outputAmount)
    {
        OutputAmount = outputAmount;
    }

    public String getOutputYear()
    {
        return OutputYear;
    }

    public void setOutputYear(String outputYear)
    {
        OutputYear = outputYear;
    }

    public String getOutputMonth()
    {
        return OutputMonth;
    }

    public void setOutputMonth(String outputMonth)
    {
        OutputMonth = outputMonth;
    }

    public long getItemId()
    {
        return itemId;
    }

    public void setItemId(long itemId)
    {
        this.itemId = itemId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public SimpleItem1 getItem()
    {
        return item;
    }

    public void setItem(SimpleItem1 item)
    {
        this.item = item;
    }

    public Set getItemOutputHistory()
    {
        return itemOutputHistory;
    }

    public void setItemOutputHistory(Set itemOutputHistory)
    {
        this.itemOutputHistory = itemOutputHistory;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private float ItemOutput;
    private float OutputAmount;
    private String OutputYear;
    private String OutputMonth;
    private long itemId;
    private String typeName;
    private SimpleItem1 item;
    private Set itemOutputHistory;
}

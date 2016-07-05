// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemImport.java

package risk.item.importoutput;

import java.util.HashSet;
import java.util.Set;
import safety.renewal.sgi.item.SimpleItem1;

// Referenced classes of package risk.item.importoutput:
//            ItemImport

public class SimpleItemImport
    implements ItemImport
{

    public SimpleItemImport()
    {
        itemImportHistory = new HashSet();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public float getItemImport()
    {
        return ItemImport;
    }

    public float getImportAmount()
    {
        return ImportAmount;
    }

    public void setImportAmount(float importAmount)
    {
        ImportAmount = importAmount;
    }

    public String getImportYear()
    {
        return ImportYear;
    }

    public void setImportYear(String importYear)
    {
        ImportYear = importYear;
    }

    public String getImportMonth()
    {
        return ImportMonth;
    }

    public void setImportMonth(String importMonth)
    {
        ImportMonth = importMonth;
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

    public void setItemImport(float itemImport)
    {
        ItemImport = itemImport;
    }

    public SimpleItem1 getItem()
    {
        return item;
    }

    public void setItem(SimpleItem1 item)
    {
        this.item = item;
    }

    public Set getItemImportHistory()
    {
        return itemImportHistory;
    }

    public void setItemImportHistory(Set itemImportHistory)
    {
        this.itemImportHistory = itemImportHistory;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private float ItemImport;
    private float ImportAmount;
    private String ImportYear;
    private String ImportMonth;
    private long itemId;
    private String typeName;
    private SimpleItem1 item;
    private Set itemImportHistory;
}

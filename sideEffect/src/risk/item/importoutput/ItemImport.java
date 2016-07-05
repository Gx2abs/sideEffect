// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemImport.java

package risk.item.importoutput;

import abstraction.IDValuePair;
import java.util.Set;
import safety.renewal.sgi.item.SimpleItem1;

public interface ItemImport
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract float getItemImport();

    public abstract float getImportAmount();

    public abstract void setImportAmount(float f);

    public abstract String getImportYear();

    public abstract void setImportYear(String s);

    public abstract String getImportMonth();

    public abstract void setImportMonth(String s);

    public abstract long getItemId();

    public abstract void setItemId(long l);

    public abstract void setItemImport(float f);

    public abstract String getTypeName();

    public abstract void setTypeName(String s);

    public abstract SimpleItem1 getItem();

    public abstract void setItem(SimpleItem1 simpleitem1);

    public abstract Set getItemImportHistory();

    public abstract void setItemImportHistory(Set set);
}

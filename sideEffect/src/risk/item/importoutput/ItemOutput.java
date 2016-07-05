// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemOutput.java

package risk.item.importoutput;

import abstraction.IDValuePair;
import java.util.Set;
import safety.renewal.sgi.item.SimpleItem1;

public interface ItemOutput
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract float getItemOutput();

    public abstract void setItemOutput(float f);

    public abstract float getOutputAmount();

    public abstract void setOutputAmount(float f);

    public abstract String getOutputYear();

    public abstract void setOutputYear(String s);

    public abstract String getOutputMonth();

    public abstract void setOutputMonth(String s);

    public abstract long getItemId();

    public abstract void setItemId(long l);

    public abstract String getTypeName();

    public abstract void setTypeName(String s);

    public abstract SimpleItem1 getItem();

    public abstract void setItem(SimpleItem1 simpleitem1);

    public abstract Set getItemOutputHistory();

    public abstract void setItemOutputHistory(Set set);
}

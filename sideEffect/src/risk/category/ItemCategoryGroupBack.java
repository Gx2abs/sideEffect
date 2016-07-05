// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategoryGroupBack.java

package risk.category;

import java.util.Date;
import java.util.List;

public interface ItemCategoryGroupBack
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getRevisionName();

    public abstract void setRevisionName(String s);

    public abstract Date getStartDate();

    public abstract void setStartDate(Date date);

    public abstract long getGroup_id();

    public abstract void setGroup_id(long l);

    public abstract List getPossibility();

    public abstract void setPossibility(List list);

    public abstract List getRisk();

    public abstract void setRisk(List list);
}

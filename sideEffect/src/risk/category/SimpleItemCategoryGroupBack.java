// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryGroupBack.java

package risk.category;

import java.util.Date;
import java.util.List;

import risk.category.possibility.Possibility;
import risk.category.risk.Risk;
import risk.category.risk.SimpleRisk;

// Referenced classes of package risk.category:
//            ItemCategoryGroupBack

public class SimpleItemCategoryGroupBack
    implements ItemCategoryGroupBack
{

    public SimpleItemCategoryGroupBack()
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

    public String getRevisionName()
    {
        return revisionName;
    }

    public void setRevisionName(String revisionName)
    {
        this.revisionName = revisionName;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public long getGroup_id()
    {
        return group_id;
    }

    public void setGroup_id(long group_id)
    {
        this.group_id = group_id;
    }

    public List<Possibility> getPossibility()
    {
        return possibility;
    }

    public void setPossibility(List possibility)
    {
        this.possibility = possibility;
    }

    public List<Risk> getRisk()
    {
        return risk;
    }

    public void setRisk(List risk)
    {
        this.risk = risk;
    }

    private long id;
    private String revisionName;
    private Date startDate;
    private long group_id;
    private List<Possibility> possibility;
    private List<Risk> risk;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMatchMaster.java

package properties.match;

import abstraction.IDValuePair;
import java.io.PrintStream;
import java.util.Set;
import properties.SimpleIsInUse;
import safety.renewal.sgi.category.SimpleItemCategory;
import safety.renewal.sgi.category.SimpleItemCategoryGrade;

public class SimpleMatchMaster
    implements IDValuePair
{

    public SimpleMatchMaster()
    {
    }

    public int getMaster_category_id()
    {
        return master_category_id;
    }

    public void setMaster_category_id(int master_category_id)
    {
        this.master_category_id = master_category_id;
    }

    public SimpleItemCategoryGrade getCategoryGrade()
    {
        return categoryGrade;
    }

    public void setCategoryGrade(SimpleItemCategoryGrade categoryGrade)
    {
        this.categoryGrade = categoryGrade;
    }

    public int getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(int isInUse)
    {
        this.isInUse = isInUse;
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public int getMasterGrade()
    {
        System.out.println((new StringBuilder("getMasterGrade=")).append(masterGrade).toString());
        return masterGrade;
    }

    public void setMasterGrade(int masterGrade)
    {
        System.out.println((new StringBuilder("setMasterGrade=")).append(masterGrade).toString());
        this.masterGrade = masterGrade;
    }

    public SimpleItemCategory getMasterObject()
    {
        return masterObject;
    }

    public void setMasterObject(SimpleItemCategory masterObject)
    {
        this.masterObject = masterObject;
    }

    public Set getSlaveClassNo()
    {
        return slaveClassNo;
    }

    public void setSlaveClassNo(Set slaveClassNo)
    {
        this.slaveClassNo = slaveClassNo;
    }

    public String getMasterName()
    {
        return masterName;
    }

    public void setMasterName(String masterName)
    {
        this.masterName = masterName;
    }

    public Set getMatchHistory()
    {
        return matchHistory;
    }

    public void setMatchHistory(Set matchHistory)
    {
        this.matchHistory = matchHistory;
    }

    public String getMasterCode()
    {
        return masterCode;
    }

    public void setMasterCode(String masterCode)
    {
        this.masterCode = masterCode;
    }

    public String getSlaveCode()
    {
        return slaveCode;
    }

    public void setSlaveCode(String slaveCode)
    {
        this.slaveCode = slaveCode;
    }

    public Set getMatchSlaves()
    {
        return matchSlaves;
    }

    public void setMatchSlaves(Set slaveList)
    {
        matchSlaves = slaveList;
    }

    public void setId(long newID)
    {
        id = newID;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    public long getId()
    {
        return id;
    }

    private long id;
    private String masterCode;
    private String masterName;
    private int masterGrade;
    private String slaveCode;
    private Set matchSlaves;
    private Set matchHistory;
    private SimpleItemCategory masterObject;
    private Set slaveClassNo;
    private int isInUse;
    private SimpleIsInUse useStatus;
    private SimpleItemCategoryGrade categoryGrade;
    private int master_category_id;
}

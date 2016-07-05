// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMatchSlave.java

package properties.match;

import abstraction.IDValuePair;
import safety.renewal.sgi.category.SimpleItemCategory;

public class SimpleMatchSlave
    implements IDValuePair
{

    public SimpleMatchSlave()
    {
    }

    public int getSlave_category_id()
    {
        return slave_category_id;
    }

    public void setSlave_category_id(int slave_category_id)
    {
        this.slave_category_id = slave_category_id;
    }

    public SimpleItemCategory getSlaveObject()
    {
        return slaveObject;
    }

    public void setSlaveObject(SimpleItemCategory slaveObject)
    {
        this.slaveObject = slaveObject;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public long getMasterId()
    {
        return masterId;
    }

    public void setMasterId(long masterId)
    {
        this.masterId = masterId;
    }

    public String getSlaveCode()
    {
        return slaveCode;
    }

    public void setSlaveCode(String slaveCode)
    {
        this.slaveCode = slaveCode;
    }

    public int getSlaveGrade()
    {
        return slaveGrade;
    }

    public void setSlaveGrade(int slaveGrade)
    {
        this.slaveGrade = slaveGrade;
    }

    private long id;
    private String propertyValue;
    private long masterId;
    private String slaveCode;
    private int slaveGrade;
    private int slave_category_id;
    private SimpleItemCategory slaveObject;
}

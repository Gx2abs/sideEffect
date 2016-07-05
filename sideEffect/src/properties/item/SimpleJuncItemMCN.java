// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemMCN.java

package properties.item;

import foreign.MeaClassNoCompositeKey;

public class SimpleJuncItemMCN
{

    public SimpleJuncItemMCN()
    {
    }

    public MeaClassNoCompositeKey getCompositeKey()
    {
        return compositeKey;
    }

    public void setCompositeKey(MeaClassNoCompositeKey compositeKey)
    {
        this.compositeKey = compositeKey;
    }

    public String getMea_class_no()
    {
        return mea_class_no;
    }

    public void setMea_class_no(String mea_class_no)
    {
        this.mea_class_no = mea_class_no;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    public long getHistoryId()
    {
        return historyId;
    }

    public void setHistoryId(long l)
    {
        historyId = l;
    }

    private MeaClassNoCompositeKey compositeKey;
    private String mea_class_no;
    private int grade;
    private long historyId;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncMCNParent.java

package properties.item;

import foreign.MeaClassNoCompositeKey;

public class SimpleJuncMCNParent
{

    public SimpleJuncMCNParent()
    {
    }

    public String getParent_mea_class_no()
    {
        return parent_mea_class_no;
    }

    public void setParent_mea_class_no(String parent_mea_class_no)
    {
        this.parent_mea_class_no = parent_mea_class_no;
    }

    public int getParent_grade()
    {
        return parent_grade;
    }

    public void setParent_grade(int parent_grade)
    {
        this.parent_grade = parent_grade;
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

    private MeaClassNoCompositeKey compositeKey;
    private String mea_class_no;
    private int grade;
    private String parent_mea_class_no;
    private int parent_grade;
}

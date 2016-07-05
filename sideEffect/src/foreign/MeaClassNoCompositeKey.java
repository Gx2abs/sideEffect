// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MeaClassNoCompositeKey.java

package foreign;

import java.io.PrintStream;
import java.io.Serializable;

public class MeaClassNoCompositeKey
    implements Serializable
{

    public MeaClassNoCompositeKey()
    {
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

    public int hashCode()
    {
        System.out.println((new StringBuilder("CompositeKey.hashCode() : ")).append(mea_class_no).append(" / ").append(grade).toString());
        int prime = 31;
        int result = 1;
        result = 31 * result + grade;
        result = 31 * result + (mea_class_no != null ? mea_class_no.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj)
    {
        System.out.println((new StringBuilder("CompositeKey.equals() : ")).append(mea_class_no).append(" / ").append(grade).toString());
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        MeaClassNoCompositeKey other = (MeaClassNoCompositeKey)obj;
        if(grade != other.grade)
            return false;
        if(mea_class_no == null)
        {
            if(other.mea_class_no != null)
                return false;
        } else
        if(!mea_class_no.equals(other.mea_class_no))
            return false;
        return true;
    }

    public static long getSerialversionuid()
    {
        return 0xda806c8d16d8cd7dL;
    }

    private static final long serialVersionUID = 0xda806c8d16d8cd7dL;
    private String mea_class_no;
    private int grade;
}

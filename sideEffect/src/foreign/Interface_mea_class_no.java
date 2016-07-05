// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Interface_mea_class_no.java

package foreign;

import java.util.Date;
import java.util.Set;
import properties.SimpleMeaClassNoLevel;

public interface Interface_mea_class_no
{

    public abstract String getMea_class_no();

    public abstract void setMea_class_no(String s);

    public abstract String getGrade();

    public abstract void setGrade(String s);

    public abstract String getHigher_class_no();

    public abstract void setHigher_class_no(String s);

    public abstract int getClass_level();

    public abstract void setClass_level(int i);

    public abstract String getClass_kor_name();

    public abstract void setClass_kor_name(String s);

    public abstract String getClass_eng_name();

    public abstract void setClass_eng_name(String s);

    public abstract String getClass_cont();

    public abstract void setClass_cont(String s);

    public abstract String getItem_grp_code();

    public abstract void setItem_grp_code(String s);

    public abstract Date getRegist_ts();

    public abstract void setRegist_ts(Date date);

    public abstract String getRegist_id();

    public abstract void setRegist_id(String s);

    public abstract Date getUpdate_ts();

    public abstract void setUpdate_ts(Date date);

    public abstract String getUpdate_id();

    public abstract void setUpdate_id(String s);

    public abstract String getTrace_manage_target_yn();

    public abstract void setTrace_manage_target_yn(String s);

    public abstract String getUse_yn();

    public abstract void setUse_yn(String s);

    public abstract String getUdi_code();

    public abstract void setUdi_code(String s);

    public abstract Set getHistory();

    public abstract void setHistory(Set set);

    public abstract SimpleMeaClassNoLevel getMeaClassNoLevel();

    public abstract void setMeaClassNoLevel(SimpleMeaClassNoLevel simplemeaclassnolevel);
}

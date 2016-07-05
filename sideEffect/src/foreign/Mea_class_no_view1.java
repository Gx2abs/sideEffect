// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Mea_class_no_view1.java

package foreign;

import abstraction.IDValuePair;
import java.util.*;
import properties.SimpleIsInUse;
import properties.SimpleMeaClassNoLevel;
import properties.item.SimpleItemCodeType;
import properties.item.SimpleTraceability;

// Referenced classes of package foreign:
//            Interface_mea_class_no, SimpleForeignGrade, MeaClassNoCompositeKey

public class Mea_class_no_view1
    implements Interface_mea_class_no, IDValuePair
{

    public Mea_class_no_view1()
    {
    }

    public String getUdi_code()
    {
        return udi_code;
    }

    public void setUdi_code(String udi_code)
    {
        this.udi_code = udi_code;
    }

    public int getHigher_class_grade()
    {
        return higher_class_grade;
    }

    public void setHigher_class_grade(int higher_class_grade)
    {
        this.higher_class_grade = higher_class_grade;
    }

    public List getParents()
    {
        return parents;
    }

    public void setParents(List parents)
    {
        this.parents = parents;
    }

    public SimpleTraceability getTraceability()
    {
        return traceability;
    }

    public void setTraceability(SimpleTraceability traceability)
    {
        this.traceability = traceability;
    }

    public SimpleIsInUse getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(SimpleIsInUse isInUse)
    {
        this.isInUse = isInUse;
    }

    public SimpleItemCodeType getCode_age()
    {
        return code_age;
    }

    public void setCode_age(SimpleItemCodeType code_age)
    {
        this.code_age = code_age;
    }

    public Set getHistory()
    {
        return history;
    }

    public void setHistory(Set history)
    {
        this.history = history;
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

    public String getGrade()
    {
        return grade;
    }

    public SimpleForeignGrade getGradeObj()
    {
        return gradeObj;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public void setGradeObj(SimpleForeignGrade grd)
    {
        gradeObj = grd;
    }

    public String getHigher_class_no()
    {
        return higher_class_no;
    }

    public void setHigher_class_no(String higher_class_no)
    {
        this.higher_class_no = higher_class_no;
    }

    public int getClass_level()
    {
        return class_level;
    }

    public void setClass_level(int class_level)
    {
        this.class_level = class_level;
    }

    public String getClass_kor_name()
    {
        return class_kor_name;
    }

    public void setClass_kor_name(String class_kor_name)
    {
        this.class_kor_name = class_kor_name;
    }

    public String getClass_eng_name()
    {
        return class_eng_name;
    }

    public void setClass_eng_name(String class_eng_name)
    {
        this.class_eng_name = class_eng_name;
    }

    public String getClass_cont()
    {
        return class_cont;
    }

    public void setClass_cont(String class_cont)
    {
        this.class_cont = class_cont;
    }

    public String getItem_grp_code()
    {
        return item_grp_code;
    }

    public void setItem_grp_code(String item_grp_code)
    {
        this.item_grp_code = item_grp_code;
    }

    public Date getRegist_ts()
    {
        return regist_ts;
    }

    public void setRegist_ts(Date regist_ts)
    {
        this.regist_ts = regist_ts;
    }

    public String getRegist_id()
    {
        return regist_id;
    }

    public void setRegist_id(String regist_id)
    {
        this.regist_id = regist_id;
    }

    public Date getUpdate_ts()
    {
        return update_ts;
    }

    public void setUpdate_ts(Date update_ts)
    {
        this.update_ts = update_ts;
    }

    public String getUpdate_id()
    {
        return update_id;
    }

    public void setUpdate_id(String update_id)
    {
        this.update_id = update_id;
    }

    public String getTrace_manage_target_yn()
    {
        return trace_manage_target_yn;
    }

    public void setTrace_manage_target_yn(String trace_manage_target_yn)
    {
        this.trace_manage_target_yn = trace_manage_target_yn;
    }

    public String getUse_yn()
    {
        return use_yn;
    }

    public void setUse_yn(String use_yn)
    {
        this.use_yn = use_yn;
    }

    public long getId()
    {
        return 0L;
    }

    public void setId(long l)
    {
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    public SimpleMeaClassNoLevel getMeaClassNoLevel()
    {
        return null;
    }

    public void setMeaClassNoLevel(SimpleMeaClassNoLevel simplemeaclassnolevel)
    {
    }

    private String mea_class_no;
    private String grade;
    private SimpleForeignGrade gradeObj;
    private String higher_class_no;
    private int higher_class_grade;
    private int class_level;
    private String class_kor_name;
    private String class_eng_name;
    private String class_cont;
    private String item_grp_code;
    private Date regist_ts;
    private String regist_id;
    private Date update_ts;
    private String update_id;
    private String trace_manage_target_yn;
    private String use_yn;
    private MeaClassNoCompositeKey compositeKey;
    private Set history;
    private SimpleItemCodeType code_age;
    private SimpleTraceability traceability;
    private SimpleIsInUse isInUse;
    private List parents;
    private String udi_code;
}

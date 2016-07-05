// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategory.java

package safety.renewal.sgi.category;

import foreign.MeaClassNoCompositeKey;
import foreign.SimpleForeignGrade;
import java.util.*;
import properties.SimpleIsInUse;
import risk.category.SimpleJuncItemCategoryGroup;

// Referenced classes of package safety.renewal.sgi.category:
//            SimpleItemCategoryTraceability, SimpleItemCategoryCodeType, SimpleItemCategoryLevel, SimpleItemCategoryGrade

public interface ItemCategory
{

    public abstract String getUdi_code();

    public abstract void setUdi_code(String s);

    public abstract String getOlder_class_no();

    public abstract void setOlder_class_no(String s);

    public abstract Set getNewer_class_no();

    public abstract void setNewer_class_no(Set set);

    public abstract int getHigher_class_grade();

    public abstract void setHigher_class_grade(int i);

    public abstract List getParents();

    public abstract void setParents(List list);

    public abstract SimpleItemCategoryTraceability getTraceability();

    public abstract void setTraceability(SimpleItemCategoryTraceability simpleitemcategorytraceability);

    public abstract SimpleIsInUse getIsInUse();

    public abstract void setIsInUse(SimpleIsInUse simpleisinuse);

    public abstract SimpleItemCategoryCodeType getCode_age();

    public abstract void setCode_age(SimpleItemCategoryCodeType simpleitemcategorycodetype);

    public abstract Set getHistory();

    public abstract void setHistory(Set set);

    public abstract MeaClassNoCompositeKey getCompositeKey();

    public abstract void setCompositeKey(MeaClassNoCompositeKey meaclassnocompositekey);

    public abstract String getMea_class_no();

    public abstract void setMea_class_no(String s);

    public abstract String getGrade();

    public abstract SimpleForeignGrade getGradeObj();

    public abstract void setGrade(String s);

    public abstract void setGradeObj(SimpleForeignGrade simpleforeigngrade);

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

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getPropertyValue();

    public abstract void setPropertyValue(String s);

    public abstract Integer getCodeAge();

    public abstract void setCodeAge(Integer integer);

    public abstract SimpleItemCategoryLevel getMeaClassNoLevel();

    public abstract void setMeaClassNoLevel(SimpleItemCategoryLevel simpleitemcategorylevel);

    public abstract SimpleItemCategoryGrade getCategoryGrade();

    public abstract void setCategoryGrade(SimpleItemCategoryGrade simpleitemcategorygrade);

    public abstract SimpleJuncItemCategoryGroup getJuncItemCategoryGroup();

    public abstract void setJuncItemCategoryGroup(SimpleJuncItemCategoryGroup simplejuncitemcategorygroup);
}

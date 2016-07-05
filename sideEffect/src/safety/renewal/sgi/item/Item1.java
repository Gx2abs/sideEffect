// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item1.java

package safety.renewal.sgi.item;

import abstraction.IDValuePair;
import foreign.Mea_class_no;
import java.util.List;
import java.util.Set;
import properties.SimpleIsInUse;
import safety.renewal.company.SimpleCompany1;
import safety.renewal.sgi.category.SimpleItemCategory;

// Referenced classes of package safety.renewal.sgi.item:
//            SimpleItemCodeType1

public interface Item1
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract Integer getMeddev_item_seq();

    public abstract void setMeddev_item_seq(Integer integer);

    public abstract String getCob_flag_code();

    public abstract void setCob_flag_code(String s);

    public abstract String getMeddev_item_no();

    public abstract void setMeddev_item_no(String s);

    public abstract String getManuf_import_name();

    public abstract void setManuf_import_name(String s);

    public abstract Integer getIsInUse();

    public abstract void setIsInUse(Integer integer);

    public abstract Set getItem_lot();

    public abstract void setItem_lot(Set set);

    public abstract Set getItem_type();

    public abstract void setItem_type(Set set);

    public abstract Set getItemHistory();

    public abstract void setItemHistory(Set set);

    public abstract Integer getItem_category_number();

    public abstract void setItem_category_number(Integer integer);

    public abstract Integer getCompany_id();

    public abstract void setCompany_id(Integer integer);

    public abstract SimpleCompany1 getCompany();

    public abstract void setCompany(SimpleCompany1 simplecompany1);

    public abstract SimpleItemCategory getMea_item();

    public abstract void setMea_item(SimpleItemCategory simpleitemcategory);

    public abstract SimpleIsInUse getUseStatus();

    public abstract void setUseStatus(SimpleIsInUse simpleisinuse);

    public abstract SimpleItemCodeType1 getCobFlagType();

    public abstract void setCobFlagType(SimpleItemCodeType1 simpleitemcodetype1);

    public abstract int getMeddev_entp_seq();

    public abstract void setMeddev_entp_seq(int i);

    public abstract Mea_class_no getMea_class_no_obj();

    public abstract void setMea_class_no_obj(Mea_class_no mea_class_no);

    public abstract String getGrade();

    public abstract void setGrade(String s);

    public abstract String getMea_class_no();

    public abstract void setMea_class_no(String s);

    public abstract String getManuf_name();

    public abstract void setManuf_name(String s);

    public abstract String getItem_name();

    public abstract void setItem_name(String s);

    public abstract String getShape();

    public abstract void setShape(String s);

    public abstract List getItemImport();

    public abstract void setItemImport(List list);

    public abstract List getItemOutput();

    public abstract void setItemOutput(List list);
}

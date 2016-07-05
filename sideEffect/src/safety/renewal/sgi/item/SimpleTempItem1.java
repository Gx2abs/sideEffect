// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleTempItem1.java

package safety.renewal.sgi.item;

import foreign.Mea_class_no;
import java.util.*;
import properties.SimpleIsInUse;
import safety.renewal.company.SimpleCompany1;
import safety.renewal.sgi.category.SimpleItemCategory;

// Referenced classes of package safety.renewal.sgi.item:
//            Item1, SimpleItemCodeType1

public class SimpleTempItem1
    implements Item1
{

    public SimpleTempItem1()
    {
        itemHistory = new HashSet();
        item_lot = new HashSet();
        item_type = new HashSet();
    }

    public Long getBatchGroupId()
    {
        return batchGroupId;
    }

    public void setBatchGroupId(Long batchGroupId)
    {
        this.batchGroupId = batchGroupId;
    }

    public String getShape()
    {
        return shape;
    }

    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public String getItem_name()
    {
        return item_name;
    }

    public void setItem_name(String item_name)
    {
        this.item_name = item_name;
    }

    public String getManuf_name()
    {
        return manuf_name;
    }

    public void setManuf_name(String manuf_name)
    {
        this.manuf_name = manuf_name;
    }

    public SimpleItemCodeType1 getCobFlagType()
    {
        return cobFlagType;
    }

    public void setCobFlagType(SimpleItemCodeType1 cobFlagType)
    {
        this.cobFlagType = cobFlagType;
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public SimpleCompany1 getCompany()
    {
        return company;
    }

    public void setCompany(SimpleCompany1 company)
    {
        this.company = company;
    }

    public SimpleItemCategory getMea_item()
    {
        return mea_item;
    }

    public void setMea_item(SimpleItemCategory mea_item)
    {
        this.mea_item = mea_item;
    }

    public Integer getItem_category_number()
    {
        return item_category_number;
    }

    public void setItem_category_number(Integer item_category_number)
    {
        this.item_category_number = item_category_number;
    }

    public Integer getCompany_id()
    {
        return company_id;
    }

    public void setCompany_id(Integer company_id)
    {
        this.company_id = company_id;
    }

    public String getMea_class_no()
    {
        return mea_class_no;
    }

    public void setMea_class_no(String mea_class_no)
    {
        this.mea_class_no = mea_class_no;
    }

    public int getMeddev_entp_seq()
    {
        return meddev_entp_seq;
    }

    public void setMeddev_entp_seq(int meddev_entp_seq)
    {
        this.meddev_entp_seq = meddev_entp_seq;
    }

    public Mea_class_no getMea_class_no_obj()
    {
        return mea_class_no_obj;
    }

    public void setMea_class_no_obj(Mea_class_no mea_class_no_obj)
    {
        this.mea_class_no_obj = mea_class_no_obj;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public Set getItemHistory()
    {
        return itemHistory;
    }

    public void setItemHistory(Set itemHistory)
    {
        this.itemHistory = itemHistory;
    }

    public Set getItem_type()
    {
        return item_type;
    }

    public void setItem_type(Set item_type)
    {
        this.item_type = item_type;
    }

    public Set getItem_lot()
    {
        return item_lot;
    }

    public void setItem_lot(Set item_lot)
    {
        this.item_lot = item_lot;
    }

    public String getCob_flag_code()
    {
        return cob_flag_code;
    }

    public void setCob_flag_code(String cob_flag_code)
    {
        this.cob_flag_code = cob_flag_code;
    }

    public String getMeddev_item_no()
    {
        return meddev_item_no;
    }

    public void setMeddev_item_no(String meddev_item_no)
    {
        this.meddev_item_no = meddev_item_no;
    }

    public String getManuf_import_name()
    {
        return manuf_import_name;
    }

    public void setManuf_import_name(String manuf_import_name)
    {
        this.manuf_import_name = manuf_import_name;
    }

    public Integer getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(Integer isInUse)
    {
        this.isInUse = isInUse;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Integer getMeddev_item_seq()
    {
        if(meddev_item_seq == null)
            meddev_item_seq = Integer.valueOf(0);
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(Integer meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    public List getItemImport()
    {
        return itemImport;
    }

    public void setItemImport(List itemImport)
    {
        this.itemImport = itemImport;
    }

    public List getItemOutput()
    {
        return itemOutput;
    }

    public void setItemOutput(List itemOutput)
    {
        this.itemOutput = itemOutput;
    }

    private long id;
    private Set itemHistory;
    private Set item_lot;
    private Set item_type;
    private Integer meddev_item_seq;
    private String cob_flag_code;
    private SimpleItemCodeType1 cobFlagType;
    private String meddev_item_no;
    private String manuf_import_name;
    private Integer isInUse;
    private Integer item_category_number;
    private Integer company_id;
    private SimpleCompany1 company;
    private SimpleItemCategory mea_item;
    private SimpleIsInUse useStatus;
    private String manuf_name;
    private String item_name;
    private String shape;
    private Long batchGroupId;
    private List itemImport;
    private List itemOutput;
    private int meddev_entp_seq;
    private String mea_class_no;
    private Mea_class_no mea_class_no_obj;
    private String grade;
}

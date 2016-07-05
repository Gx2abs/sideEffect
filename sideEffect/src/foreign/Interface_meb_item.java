// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Interface_meb_item.java

package foreign;

import abstraction.IDValuePair;
import java.sql.Date;
import java.util.List;
import properties.company.SimpleCompany;

// Referenced classes of package foreign:
//            Interface_meb_item2, Mea_class_no

public interface Interface_meb_item
    extends Interface_meb_item2, IDValuePair
{

    public abstract int getMeddev_item_seq();

    public abstract void setMeddev_item_seq(int i);

    public abstract String getMachinery_area_code();

    public abstract void setMachinery_area_code(String s);

    public abstract String getCob_flag_code();

    public abstract void setCob_flag_code(String s);

    public abstract String getPermit_state_code();

    public abstract void setPermit_state_code(String s);

    public abstract String getMeddev_item_no();

    public abstract void setMeddev_item_no(String s);

    public abstract String getTax_no();

    public abstract void setTax_no(String s);

    public abstract String getItem_name();

    public abstract void setItem_name(String s);

    public abstract Date getPermit_date();

    public abstract void setPermit_date(Date date);

    public abstract String getManuf_country_code();

    public abstract void setManuf_country_code(String s);

    public abstract String getManuf_name();

    public abstract void setManuf_name(String s);

    public abstract String getManuf_place();

    public abstract void setManuf_place(String s);

    public abstract String getManuf_req_country_code();

    public abstract void setManuf_req_country_code(String s);

    public abstract String getManuf_req_name();

    public abstract void setManuf_req_name(String s);

    public abstract String getManuf_req_place();

    public abstract void setManuf_req_place(String s);

    public abstract String getSeller_country_code();

    public abstract void setSeller_country_code(String s);

    public abstract String getSeller_name();

    public abstract void setSeller_name(String s);

    public abstract String getSeller_place();

    public abstract void setSeller_place(String s);

    public abstract String getManuf_import_entp_name();

    public abstract void setManuf_import_entp_name(String s);

    public abstract String getManuf_import_zip_no();

    public abstract void setManuf_import_zip_no(String s);

    public abstract String getManuf_import_addr1();

    public abstract void setManuf_import_addr1(String s);

    public abstract String getManuf_import_addr2();

    public abstract void setManuf_import_addr2(String s);

    public abstract String getCancel_withdw_code();

    public abstract void setCancel_withdw_code(String s);

    public abstract Date getCancel_withdw_date();

    public abstract void setCancel_withdw_date(Date date);

    public abstract String getShape();

    public abstract void setShape(String s);

    public abstract String getSumr_name();

    public abstract void setSumr_name(String s);

    public abstract String getSize_cnt();

    public abstract void setSize_cnt(String s);

    public abstract String getChart_name();

    public abstract void setChart_name(String s);

    public abstract String getMake_method();

    public abstract void setMake_method(String s);

    public abstract String getUse_purpose();

    public abstract void setUse_purpose(String s);

    public abstract String getUse_method();

    public abstract void setUse_method(String s);

    public abstract String getNb();

    public abstract void setNb(String s);

    public abstract String getValid_term();

    public abstract void setValid_term(String s);

    public abstract String getExam_std();

    public abstract void setExam_std(String s);

    public abstract String getPermit_cond();

    public abstract void setPermit_cond(String s);

    public abstract String getRemark();

    public abstract void setRemark(String s);

    public abstract int getMeddev_entp_seq();

    public abstract void setMeddev_entp_seq(int i);

    public abstract String getMea_class_no();

    public abstract void setMea_class_no(String s);

    public abstract String getGrade();

    public abstract void setGrade(String s);

    public abstract String getReceipt_no();

    public abstract void setReceipt_no(String s);

    public abstract String getBef_doc_id();

    public abstract void setBef_doc_id(String s);

    public abstract String getSafe_exam_yn();

    public abstract void setSafe_exam_yn(String s);

    public abstract String getExport_dev_yn();

    public abstract void setExport_dev_yn(String s);

    public abstract String getSame_goods_yn();

    public abstract void setSame_goods_yn(String s);

    public abstract String getSame_item_no();

    public abstract void setSame_item_no(String s);

    public abstract String getStorage_method();

    public abstract void setStorage_method(String s);

    public abstract String getMaterial();

    public abstract void setMaterial(String s);

    public abstract String getMention_cont();

    public abstract void setMention_cont(String s);

    public abstract Date getRegist_ts();

    public abstract void setRegist_ts(Date date);

    public abstract String getRegist_id();

    public abstract void setRegist_id(String s);

    public abstract Date getUpdate_ts();

    public abstract void setUpdate_ts(Date date);

    public abstract String getUpdate_id();

    public abstract void setUpdate_id(String s);

    public abstract String getServ_target();

    public abstract void setServ_target(String s);

    public abstract String getTrace_manage_target_yn();

    public abstract void setTrace_manage_target_yn(String s);

    public abstract String getReexam_yn();

    public abstract void setReexam_yn(String s);

    public abstract Date getReexam_valid_start_date();

    public abstract void setReexam_valid_start_date(Date date);

    public abstract Date getReexam_valid_end_date();

    public abstract void setReexam_valid_end_date(Date date);

    public abstract String getServ_target2();

    public abstract void setServ_target2(String s);

    public abstract String getBef_cancel_withdw_code();

    public abstract void setBef_cancel_withdw_code(String s);

    public abstract String getIssue_org_code();

    public abstract void setIssue_org_code(String s);

    public abstract String getClass_change_yn();

    public abstract void setClass_change_yn(String s);

    public abstract String getDelete_yn();

    public abstract void setDelete_yn(String s);

    public abstract String getAmterial_ver();

    public abstract void setAmterial_ver(String s);

    public abstract String getMaterial_ver();

    public abstract void setMaterial_ver(String s);

    public abstract String getDisposable_emed_yn();

    public abstract void setDisposable_emed_yn(String s);

    public abstract Mea_class_no getMea_class_no_obj();

    public abstract void setMea_class_no_obj(Mea_class_no mea_class_no);

    public abstract String getManuf_lot();

    public abstract void setManuf_lot(String s);

    public abstract String getH_name();

    public abstract void setH_name(String s);

    public abstract SimpleCompany getCompany();

    public abstract void setCompany(SimpleCompany simplecompany);

    public abstract List getMebTypeInfo();

    public abstract void setMebTypeInfo(List list);

    public abstract List getMebLotNo();

    public abstract void setMebLotNo(List list);

    public abstract Mea_class_no getMea_item();

    public abstract void setMea_item(Mea_class_no mea_class_no);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Company1.java

package safety.renewal.company;

import abstraction.IDValuePair;
import java.util.Date;

public interface Company1
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getItem_permit_code();

    public abstract void setItem_permit_code(String s);

    public abstract String getDesct_doc_code();

    public abstract void setDesct_doc_code(String s);

    public abstract String getPermit_area_code();

    public abstract void setPermit_area_code(String s);

    public abstract String getMeddev_entp_no();

    public abstract void setMeddev_entp_no(String s);

    public abstract String getTax_no();

    public abstract void setTax_no(String s);

    public abstract String getEntp_name();

    public abstract void setEntp_name(String s);

    public abstract Date getPermit_date();

    public abstract void setPermit_date(Date date);

    public abstract String getPermit_state_code();

    public abstract void setPermit_state_code(String s);

    public abstract String getShutdown_close_reopen_code();

    public abstract void setShutdown_close_reopen_code(String s);

    public abstract Date getClose_date();

    public abstract void setClose_date(Date date);

    public abstract Date getShotdown_plan_start_date();

    public abstract void setShotdown_plan_start_date(Date date);

    public abstract Date getShotdown_plan_end_date();

    public abstract void setShotdown_plan_end_date(Date date);

    public abstract Date getReopen_date();

    public abstract void setReopen_date(Date date);

    public abstract String getPermit_cond();

    public abstract void setPermit_cond(String s);

    public abstract String getDiff_bywork();

    public abstract void setDiff_bywork(String s);

    public abstract String getEntp_tel_no();

    public abstract void setEntp_tel_no(String s);

    public abstract String getEntp_fax_no();

    public abstract void setEntp_fax_no(String s);

    public abstract String getEntp_zip_no();

    public abstract void setEntp_zip_no(String s);

    public abstract String getEntp_addr1();

    public abstract void setEntp_addr1(String s);

    public abstract String getEntp_addr2();

    public abstract void setEntp_addr2(String s);

    public abstract String getRemark();

    public abstract void setRemark(String s);

    public abstract String getReceipt_no();

    public abstract void setReceipt_no(String s);

    public abstract String getChange_reason();

    public abstract void setChange_reason(String s);

    public abstract String getApply_reason();

    public abstract void setApply_reason(String s);

    public abstract String getSample_entp_yn();

    public abstract void setSample_entp_yn(String s);

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

    public abstract String getServ_target2();

    public abstract void setServ_target2(String s);

    public abstract String getKfda_manage_code();

    public abstract void setKfda_manage_code(String s);

    public abstract int getMeddev_entp_seq();

    public abstract void setMeddev_entp_seq(int i);

    public abstract String getCob_flag_code();

    public abstract void setCob_flag_code(String s);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SafetyReportView.java

package safety;

import java.util.Date;

public interface SafetyReportView
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Long getDocument_number();

    public abstract void setDocument_number(Long long1);

    public abstract String getCompany_name();

    public abstract void setCompany_name(String s);

    public abstract String getReporter_type();

    public abstract void setReporter_type(String s);

    public abstract String getEntp_name();

    public abstract void setEntp_name(String s);

    public abstract String getGrade();

    public abstract void setGrade(String s);

    public abstract String getClass_kor_name();

    public abstract void setClass_kor_name(String s);

    public abstract String getProduct_cob_code();

    public abstract void setProduct_cob_code(String s);

    public abstract String getMedicalcode();

    public abstract void setMedicalcode(String s);

    public abstract String getPatientcode();

    public abstract void setPatientcode(String s);

    public abstract String getComponentcode();

    public abstract void setComponentcode(String s);

    public abstract String getReport_type();

    public abstract void setReport_type(String s);

    public abstract String getSafety_cause_content();

    public abstract void setSafety_cause_content(String s);

    public abstract String getReport_year();

    public abstract void setReport_year(String s);

    public abstract String getReport_month();

    public abstract void setReport_month(String s);

    public abstract Date getFirst_modified();

    public abstract void setFirst_modified(Date date);

    public abstract String getGubun();

    public abstract void setGubun(String s);

    public abstract String getReport_type2();

    public abstract void setReport_type2(String s);

    public abstract String getReport_date();

    public abstract void setReport_date(String s);

    public abstract Date getMreport_date();

    public abstract void setMreport_date(Date date);

    public abstract String getRepresentative();

    public abstract void setRepresentative(String s);

    public abstract String getManager();

    public abstract void setManager(String s);

    public abstract String getTelephone();

    public abstract void setTelephone(String s);

    public abstract String getFax();

    public abstract void setFax(String s);

    public abstract String getEmail();

    public abstract void setEmail(String s);

    public abstract String getMeddev_entp_no();

    public abstract void setMeddev_entp_no(String s);

    public abstract String getMea_class_no();

    public abstract void setMea_class_no(String s);

    public abstract String getCountry_manufactured();

    public abstract void setCountry_manufactured(String s);

    public abstract String getManufacturer();

    public abstract void setManufacturer(String s);

    public abstract String getMeb_type();

    public abstract void setMeb_type(String s);

    public abstract String getSerial_number();

    public abstract void setSerial_number(String s);

    public abstract String getFollowup();

    public abstract void setFollowup(String s);

    public abstract String getFollow_up_action();

    public abstract void setFollow_up_action(String s);

    public abstract String getReport_status();

    public abstract void setReport_status(String s);

    public abstract String getAddress();

    public abstract void setAddress(String s);

    public abstract String getExtra_info();

    public abstract void setExtra_info(String s);

    public abstract String getAttachment();

    public abstract void setAttachment(String s);

    public abstract String getMeddev_item_no();

    public abstract void setMeddev_item_no(String s);

    public abstract Integer getMeddev_item_no1();

    public abstract void setMeddev_item_no1(Integer integer);

    public abstract Integer getMeddev_item_no2();

    public abstract void setMeddev_item_no2(Integer integer);

    public abstract String getProduct_value();

    public abstract void setProduct_value(String s);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SafetyReport.java

package safety;

import java.util.Date;
import java.util.List;
import properties.*;
import properties.country.SimpleCountry;
import safety.renewal.sgi.item.SimpleItem1;
import statistics.property.SimpleReportDate;

public interface SafetyReport
{

    public abstract List getSafetyReportMebTypeInfo();

    public abstract void setSafetyReportMebTypeInfo(List list);

    public abstract String getFollowUpActionEtc();

    public abstract void setFollowUpActionEtc(String s);

    public abstract SimpleReportFollowUpAction getObj_follow_up_action();

    public abstract void setObj_follow_up_action(SimpleReportFollowUpAction simplereportfollowupaction);

    public abstract Integer getFollow_up_action();

    public abstract void setFollow_up_action(Integer integer);

    public abstract String getSafety_report_type_etc();

    public abstract void setSafety_report_type_etc(String s);

    public abstract String getSerial_number();

    public abstract void setSerial_number(String s);

    public abstract String getExtra_info();

    public abstract void setExtra_info(String s);

    public abstract String getReporter_etc();

    public abstract void setReporter_etc(String s);

    public abstract List getSafetyReportTypeDate();

    public abstract void setSafetyReportTypeDate(List list);

    public abstract Date getReportDate();

    public abstract void setReportDate(Date date);

    public abstract ReportStatus getReportStatus();

    public abstract void setReportStatus(ReportStatus reportstatus);

    public abstract Integer getCause();

    public abstract void setCause(Integer integer);

    public abstract Integer getMeb_item_id();

    public abstract void setMeb_item_id(Integer integer);

    public abstract PatientCondition getPatientCondition();

    public abstract void setPatientCondition(PatientCondition patientcondition);

    public abstract ComponentCode getComponentCode();

    public abstract void setComponentCode(ComponentCode componentcode);

    public abstract Integer getId();

    public abstract void setId(Integer integer);

    public abstract CountryReportedIn getCountryReportedIn();

    public abstract void setCountryReportedIn(CountryReportedIn countryreportedin);

    public abstract List getReporterTypes();

    public abstract void setReporterTypes(List list);

    public abstract SafetyReportType getSafetyReportType();

    public abstract void setSafetyReportType(SafetyReportType safetyreporttype);

    public abstract CountryManufacturedIn getCountryManufacturedIn();

    public abstract void setCountryManufacturedIn(CountryManufacturedIn countrymanufacturedin);

    public abstract SimpleItem1 getMeb_item();

    public abstract void setMeb_item(SimpleItem1 simpleitem1);

    public abstract String getManager();

    public abstract void setManager(String s);

    public abstract String getRepresentative();

    public abstract void setRepresentative(String s);

    public abstract String getOrganisation();

    public abstract void setOrganisation(String s);

    public abstract String getFax();

    public abstract void setFax(String s);

    public abstract String getAddress();

    public abstract void setAddress(String s);

    public abstract String getTelephone();

    public abstract void setTelephone(String s);

    public abstract String getEmail();

    public abstract void setEmail(String s);

    public abstract List getPatientCodeCondition();

    public abstract void setPatientCodeCondition(List list);

    public abstract List getMedicalCode();

    public abstract void setMedicalCode(List list);

    public abstract List getJuncComponentCode();

    public abstract void setJuncComponentCode(List list);

    public abstract String getSafety_cause_content();

    public abstract void setSafety_cause_content(String s);

    public abstract String getFollowup();

    public abstract void setFollowup(String s);

    public abstract List getReport_type();

    public abstract void setReport_type(List list);

    public abstract Integer getCol_patient_condition_code();

    public abstract void setCol_patient_condition_code(Integer integer);

    public abstract String getCompany_name();

    public abstract void setCompany_name(String s);

    public abstract String getManufacturer();

    public abstract void setManufacturer(String s);

    public abstract int getCountry_manufactured_id();

    public abstract void setCountry_manufactured_id(int i);

    public abstract SimpleCountry getCountry_manufactured();

    public abstract void setCountry_manufactured(SimpleCountry simplecountry);

    public abstract List getReportType();

    public abstract void setReportType(List list);

    public abstract Date getMreport_date();

    public abstract void setMreport_date(Date date);

    public abstract Date getFirst_modified();

    public abstract void setFirst_modified(Date date);

    public abstract Long getVarId();

    public abstract void setVarId(Long long1);

    public abstract String getVarValue1();

    public abstract void setVarValue1(String s);

    public abstract String getVarValue2();

    public abstract void setVarValue2(String s);

    public abstract String getVarValue3();

    public abstract void setVarValue3(String s);

    public abstract String getVarValue4();

    public abstract void setVarValue4(String s);

    public abstract String getVarValue5();

    public abstract void setVarValue5(String s);

    public abstract Long getVarCnt();

    public abstract void setVarCnt(Long long1);

    public abstract Long getVarCnt1();

    public abstract void setVarCnt1(Long long1);

    public abstract Long getVarCnt2();

    public abstract void setVarCnt2(Long long1);

    public abstract Long getVarCnt3();

    public abstract void setVarCnt3(Long long1);

    public abstract String getVarYear();

    public abstract void setVarYear(String s);

    public abstract String getVarMonth();

    public abstract void setVarMonth(String s);

    public abstract String getReport_year();

    public abstract void setReport_year(String s);

    public abstract String getReport_month();

    public abstract void setReport_month(String s);

    public abstract SimpleReportDate getReportYM();

    public abstract void setReportYM(SimpleReportDate simplereportdate);

    public abstract Double getDocument_number();

    public abstract void setDocument_number(Double double1);

    public abstract Integer getCountry_reported_in();

    public abstract void setCountry_reported_in(Integer integer);

    public abstract Integer getReport_status();

    public abstract void setReport_status(Integer integer);

    public abstract String getKfda_followup();

    public abstract void setKfda_followup(String s);
}

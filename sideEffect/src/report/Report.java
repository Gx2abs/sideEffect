// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Report.java

package report;

import java.util.Date;
import java.util.List;
import properties.*;
import properties.country.SimpleCountry;
import safety.renewal.sgi.item.SimpleItem1;
import statistics.property.SimpleReportDate;

public interface Report
{

    public abstract List getSideEffectReportMebTypeInfo();

    public abstract void setSideEffectReportMebTypeInfo(List list);

    public abstract String getFollowUpActionEtc();

    public abstract void setFollowUpActionEtc(String s);

    public abstract SimpleReportFollowUpAction getObj_follow_up_action();

    public abstract void setObj_follow_up_action(SimpleReportFollowUpAction simplereportfollowupaction);

    public abstract Integer getFollow_up_action();

    public abstract void setFollow_up_action(Integer integer);

    public abstract String getExtra_info2();

    public abstract void setExtra_info2(String s);

    public abstract String getSerial_number();

    public abstract void setSerial_number(String s);

    public abstract String getResult_etc();

    public abstract void setResult_etc(String s);

    public abstract String getReporter_etc();

    public abstract void setReporter_etc(String s);

    public abstract String getCause_etc();

    public abstract void setCause_etc(String s);

    public abstract Integer getReport_type();

    public abstract void setReport_type(Integer integer);

    public abstract Double getDocument_number();

    public abstract void setDocument_number(Double double1);

    public abstract List getReportType();

    public abstract void setReportType(List list);

    public abstract List getSideEffectReportTypeDate();

    public abstract void setSideEffectReportTypeDate(List list);

    public abstract int getId();

    public abstract void setId(int i);

    public abstract Integer getColCountryReportedIn();

    public abstract void setColCountryReportedIn(Integer integer);

    public abstract Integer getColReportType();

    public abstract void setColReportType(Integer integer);

    public abstract Date getReportDate();

    public abstract void setReportDate(Date date);

    public abstract ReporterType getReporterType();

    public abstract void setReporterType(ReporterType reportertype);

    public abstract String getCompany_name();

    public abstract void setCompany_name(String s);

    public abstract String getRepresentatives();

    public abstract void setRepresentatives(String s);

    public abstract String getManager();

    public abstract void setManager(String s);

    public abstract String getReport_tel();

    public abstract void setReport_tel(String s);

    public abstract String getFax();

    public abstract void setFax(String s);

    public abstract String getEmail();

    public abstract void setEmail(String s);

    public abstract String getReport_address();

    public abstract void setReport_address(String s);

    public abstract Integer getMeb_item_id();

    public abstract void setMeb_item_id(Integer integer);

    public abstract SimpleItem1 getMeb_item();

    public abstract void setMeb_item(SimpleItem1 simpleitem1);

    public abstract Integer getCountry_manufactured_id();

    public abstract void setCountry_manufactured_id(Integer integer);

    public abstract CountryManufacturedIn getCountryManufacturedIn();

    public abstract void setCountryManufacturedIn(CountryManufacturedIn countrymanufacturedin);

    public abstract String getManufacturer();

    public abstract void setManufacturer(String s);

    public abstract String getPatient_name();

    public abstract void setPatient_name(String s);

    public abstract Integer getPatient_gender();

    public abstract void setPatient_gender(Integer integer);

    public abstract Gender getGender();

    public abstract void setGender(Gender gender);

    public abstract Integer getPatient_age();

    public abstract void setPatient_age(Integer integer);

    public abstract String getPatient_extra_info();

    public abstract void setPatient_extra_info(String s);

    public abstract Date getSide_effect_first_date();

    public abstract void setSide_effect_first_date(Date date);

    public abstract Date getSide_effect_generation_date();

    public abstract void setSide_effect_generation_date(Date date);

    public abstract Date getSide_effect_last_date();

    public abstract void setSide_effect_last_date(Date date);

    public abstract List getSideeffectResult();

    public abstract void setSideeffectResult(List list);

    public abstract List getSideeffectCause();

    public abstract void setSideeffectCause(List list);

    public abstract Integer getCausality_id();

    public abstract void setCausality_id(Integer integer);

    public abstract Causality getCausality();

    public abstract void setCausality(Causality causality);

    public abstract String getFollowup();

    public abstract void setFollowup(String s);

    public abstract Integer getReport_status();

    public abstract void setReport_status(Integer integer);

    public abstract ReportStatus getReportStatus();

    public abstract void setReportStatus(ReportStatus reportstatus);

    public abstract String getOrganization();

    public abstract void setOrganization(String s);

    public abstract String getOrganization_tel();

    public abstract void setOrganization_tel(String s);

    public abstract String getOrganization_address();

    public abstract void setOrganization_address(String s);

    public abstract String getExtra_info();

    public abstract void setExtra_info(String s);

    public abstract CountryReportedIn getGubun();

    public abstract void setGubun(CountryReportedIn countryreportedin);

    public abstract List getPatientCodeCondition();

    public abstract void setPatientCodeCondition(List list);

    public abstract List getMedicalCode();

    public abstract void setMedicalCode(List list);

    public abstract List getJuncComponentCode();

    public abstract void setJuncComponentCode(List list);

    public abstract Date getMreport_date();

    public abstract void setMreport_date(Date date);

    public abstract SimpleCountry getCountry_manufactured();

    public abstract void setCountry_manufactured(SimpleCountry simplecountry);

    public abstract List getAttachments();

    public abstract void setAttachments(List list);

    public abstract Severity getSeveritiy();

    public abstract void setSeveritiy(Severity severity);

    public abstract CauseOfSideeffect getCauseOfSideeffect();

    public abstract void setCauseOfSideeffect(CauseOfSideeffect causeofsideeffect);

    public abstract PatientCondition getPatientCondition();

    public abstract void setPatientCondition(PatientCondition patientcondition);

    public abstract void setStringRepresentation(String s);

    public abstract String getStringRepresentation();

    public abstract Integer getCountry_reported_id();

    public abstract void setCountry_reported_id(Integer integer);

    public abstract Date getReport_date();

    public abstract void setReport_date(Date date);

    public abstract Date getKfda_report_date();

    public abstract void setKfda_report_date(Date date);

    public abstract Integer getSafety_report_type();

    public abstract void setSafety_report_type(Integer integer);

    public abstract List getReporterTypes();

    public abstract void setReporterTypes(List list);

    public abstract ComponentCode getComponentCode();

    public abstract void setComponentCode(ComponentCode componentcode);

    public abstract String getReport_fulltext_address();

    public abstract void setReport_fulltext_address(String s);

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

    public abstract Integer getVarIntId();

    public abstract void setVarIntId(Integer integer);

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

    public abstract Date getFirst_modified();

    public abstract void setFirst_modified(Date date);

    public abstract String getReport_year();

    public abstract void setReport_year(String s);

    public abstract String getReport_month();

    public abstract void setReport_month(String s);

    public abstract SimpleReportDate getReportYM();

    public abstract void setReportYM(SimpleReportDate simplereportdate);

    public abstract String getSide_result_from_reporter();

    public abstract void setSide_result_from_reporter(String s);

    public abstract String getSide_cause_from_reporter();

    public abstract void setSide_cause_from_reporter(String s);

    public abstract String getPatient_condition_from_reporter();

    public abstract void setPatient_condition_from_reporter(String s);

    public abstract String getMeddev_code_from_reporter();

    public abstract void setMeddev_code_from_reporter(String s);

    public abstract String getOmponent_code_from_reporter();

    public abstract void setOmponent_code_from_reporter(String s);

    public abstract String getCausality_from_reporter();

    public abstract void setCausality_from_reporter(String s);

    public abstract String getKfda_followup();

    public abstract void setKfda_followup(String s);

    public abstract Long getMedicalCount();

    public abstract void setMedicalCount(Long long1);

    public abstract Long getPatientCount();

    public abstract void setPatientCount(Long long1);

    public abstract Long getComponentCount();

    public abstract void setComponentCount(Long long1);
}

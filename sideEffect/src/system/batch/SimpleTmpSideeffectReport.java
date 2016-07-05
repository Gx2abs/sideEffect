// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleTmpSideeffectReport.java

package system.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import properties.*;
import properties.country.SimpleCountry;
import report.Report;
import safety.renewal.sgi.item.SimpleItem1;
import sideeffect.SideeffectResult;
import statistics.property.SimpleReportDate;

public class SimpleTmpSideeffectReport
    implements Report, Serializable
{

    public SimpleTmpSideeffectReport()
    {
    }

    public Integer getPatient_code1()
    {
        return patient_code1;
    }

    public void setPatient_code1(Integer patient_code1)
    {
        this.patient_code1 = patient_code1;
    }

    public SideeffectResult getSideeffectResult1()
    {
        return sideeffectResult1;
    }

    public void setSideeffectResult1(SideeffectResult sideeffectResult1)
    {
        this.sideeffectResult1 = sideeffectResult1;
    }

    public SideeffectResult getSideeffectResult2()
    {
        return sideeffectResult2;
    }

    public void setSideeffectResult2(SideeffectResult sideeffectResult2)
    {
        this.sideeffectResult2 = sideeffectResult2;
    }

    public SideeffectResult getSideeffectResult3()
    {
        return sideeffectResult3;
    }

    public void setSideeffectResult3(SideeffectResult sideeffectResult3)
    {
        this.sideeffectResult3 = sideeffectResult3;
    }

    public SideeffectResult getSideeffectResult4()
    {
        return sideeffectResult4;
    }

    public void setSideeffectResult4(SideeffectResult sideeffectResult4)
    {
        this.sideeffectResult4 = sideeffectResult4;
    }

    public SideeffectResult getSideeffectResult5()
    {
        return sideeffectResult5;
    }

    public void setSideeffectResult5(SideeffectResult sideeffectResult5)
    {
        this.sideeffectResult5 = sideeffectResult5;
    }

    public SideeffectResult getSideeffectResult6()
    {
        return sideeffectResult6;
    }

    public void setSideeffectResult6(SideeffectResult sideeffectResult6)
    {
        this.sideeffectResult6 = sideeffectResult6;
    }

    public SideeffectResult getSideeffectResult7()
    {
        return sideeffectResult7;
    }

    public void setSideeffectResult7(SideeffectResult sideeffectResult7)
    {
        this.sideeffectResult7 = sideeffectResult7;
    }

    public String getReportDate1()
    {
        return reportDate1;
    }

    public void setReportDate1(String reportDate1)
    {
        this.reportDate1 = reportDate1;
    }

    public String getReportDate2()
    {
        return reportDate2;
    }

    public void setReportDate2(String reportDate2)
    {
        this.reportDate2 = reportDate2;
    }

    public String getReportDate3()
    {
        return reportDate3;
    }

    public void setReportDate3(String reportDate3)
    {
        this.reportDate3 = reportDate3;
    }

    public String getReportDate4()
    {
        return reportDate4;
    }

    public void setReportDate4(String reportDate4)
    {
        this.reportDate4 = reportDate4;
    }

    public String getReportDate5()
    {
        return reportDate5;
    }

    public void setReportDate5(String reportDate5)
    {
        this.reportDate5 = reportDate5;
    }

    public Integer getMeddev_item_no()
    {
        return meddev_item_no;
    }

    public void setMeddev_item_no(Integer meddev_item_no)
    {
        this.meddev_item_no = meddev_item_no;
    }

    public Integer getCode_type()
    {
        return code_type;
    }

    public void setCode_type(Integer code_type)
    {
        this.code_type = code_type;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public Long getBatchGroupId()
    {
        return batchGroupId;
    }

    public void setBatchGroupId(Long batchGroupId)
    {
        this.batchGroupId = batchGroupId;
    }

    public List getSideEffectReportMebTypeInfo()
    {
        return sideEffectReportMebTypeInfo;
    }

    public void setSideEffectReportMebTypeInfo(List sideEffectReportMebTypeInfo)
    {
        this.sideEffectReportMebTypeInfo = sideEffectReportMebTypeInfo;
    }

    public String getFollowUpActionEtc()
    {
        return followUpActionEtc;
    }

    public void setFollowUpActionEtc(String followUpActionEtc)
    {
        this.followUpActionEtc = followUpActionEtc;
    }

    public SimpleReportFollowUpAction getObj_follow_up_action()
    {
        return obj_follow_up_action;
    }

    public void setObj_follow_up_action(SimpleReportFollowUpAction obj_follow_up_action)
    {
        this.obj_follow_up_action = obj_follow_up_action;
    }

    public Integer getFollow_up_action()
    {
        return Integer.valueOf(follow_up_action);
    }

    public void setFollow_up_action(int follow_up_action)
    {
        this.follow_up_action = follow_up_action;
    }

    public String getExtra_info2()
    {
        return extra_info2;
    }

    public void setExtra_info2(String extra_info2)
    {
        this.extra_info2 = extra_info2;
    }

    public String getSerial_number()
    {
        return serial_number;
    }

    public void setSerial_number(String serial_number)
    {
        this.serial_number = serial_number;
    }

    public String getResult_etc()
    {
        return result_etc;
    }

    public void setResult_etc(String result_etc)
    {
        this.result_etc = result_etc;
    }

    public String getReporter_etc()
    {
        return reporter_etc;
    }

    public void setReporter_etc(String reporter_etc)
    {
        this.reporter_etc = reporter_etc;
    }

    public String getCause_etc()
    {
        return cause_etc;
    }

    public void setCause_etc(String cause_etc)
    {
        this.cause_etc = cause_etc;
    }

    public Double getDocument_number()
    {
        return document_number;
    }

    public void setDocument_number(Double document_number)
    {
        this.document_number = document_number;
    }

    public Date getFirst_modified()
    {
        return first_modified;
    }

    public void setFirst_modified(Date first_modified)
    {
        this.first_modified = first_modified;
    }

    public List getSideEffectReportTypeDate()
    {
        return sideEffectReportTypeDate;
    }

    public void setSideEffectReportTypeDate(List sideEffectReportTypeDate)
    {
        this.sideEffectReportTypeDate = sideEffectReportTypeDate;
    }

    public SimpleCountry getCountry_manufactured()
    {
        return country_manufactured;
    }

    public void setCountry_manufactured(SimpleCountry country_manufactured)
    {
        this.country_manufactured = country_manufactured;
    }

    public Date getMreport_date()
    {
        return mreport_date;
    }

    public void setMreport_date(Date mreport_date)
    {
        this.mreport_date = mreport_date;
    }

    public Long getVarCnt()
    {
        return varCnt;
    }

    public void setVarCnt(Long varCnt)
    {
        this.varCnt = varCnt;
    }

    public String getVarYear()
    {
        return varYear;
    }

    public void setVarYear(String varYear)
    {
        this.varYear = varYear;
    }

    public String getVarMonth()
    {
        return varMonth;
    }

    public void setVarMonth(String varMonth)
    {
        this.varMonth = varMonth;
    }

    public CountryReportedIn getGubun()
    {
        return gubun;
    }

    public void setGubun(CountryReportedIn gubun)
    {
        this.gubun = gubun;
    }

    public String getExtra_info()
    {
        return extra_info;
    }

    public void setExtra_info(String extra_info)
    {
        this.extra_info = extra_info;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Integer getColCountryReportedIn()
    {
        return colCountryReportedIn;
    }

    public void setColCountryReportedIn(Integer colCountryReportedIn)
    {
        this.colCountryReportedIn = colCountryReportedIn;
    }

    public List getReportType()
    {
        return reportType;
    }

    public void setReportType(List reportType)
    {
        this.reportType = reportType;
    }

    public Integer getColReportType()
    {
        return Integer.valueOf(colReportType);
    }

    public void setColReportType(int colReportType)
    {
        this.colReportType = colReportType;
    }

    public Date getReportDate()
    {
        return reportDate;
    }

    public void setReportDate(Date reportDate)
    {
        this.reportDate = reportDate;
    }

    public ReporterType getReporterType()
    {
        return reporterType;
    }

    public void setReporterType(ReporterType reporterType)
    {
        this.reporterType = reporterType;
    }

    public String getCompany_name()
    {
        return company_name;
    }

    public void setCompany_name(String company_name)
    {
        this.company_name = company_name;
    }

    public String getRepresentatives()
    {
        return representatives;
    }

    public void setRepresentatives(String representatives)
    {
        this.representatives = representatives;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getReport_tel()
    {
        return report_tel;
    }

    public void setReport_tel(String report_tel)
    {
        this.report_tel = report_tel;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getReport_address()
    {
        return report_address;
    }

    public void setReport_address(String report_address)
    {
        this.report_address = report_address;
    }

    public Integer getMeb_item_id()
    {
        return meb_item_id;
    }

    public void setMeb_item_id(Integer meb_item_id)
    {
        this.meb_item_id = meb_item_id;
    }

    public SimpleItem1 getMeb_item()
    {
        return meb_item;
    }

    public void setMeb_item(SimpleItem1 meb_item)
    {
        this.meb_item = meb_item;
    }

    public Integer getCountry_manufactured_id()
    {
        return country_manufactured_id;
    }

    public void setCountry_manufactured_id(Integer country_manufactured_id)
    {
        this.country_manufactured_id = country_manufactured_id;
    }

    public CountryManufacturedIn getCountryManufacturedIn()
    {
        return countryManufacturedIn;
    }

    public void setCountryManufacturedIn(CountryManufacturedIn countryManufacturedIn)
    {
        this.countryManufacturedIn = countryManufacturedIn;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public String getPatient_name()
    {
        return patient_name;
    }

    public void setPatient_name(String patient_name)
    {
        this.patient_name = patient_name;
    }

    public Integer getPatient_gender()
    {
        return Integer.valueOf(patient_gender);
    }

    public void setPatient_gender(int patient_gender)
    {
        this.patient_gender = patient_gender;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Integer getPatient_age()
    {
        return Integer.valueOf(patient_age);
    }

    public void setPatient_age(int patient_age)
    {
        this.patient_age = patient_age;
    }

    public String getPatient_extra_info()
    {
        return patient_extra_info;
    }

    public void setPatient_extra_info(String patient_extra_info)
    {
        this.patient_extra_info = patient_extra_info;
    }

    public Date getSide_effect_first_date()
    {
        return side_effect_first_date;
    }

    public void setSide_effect_first_date(Date side_effect_first_date)
    {
        this.side_effect_first_date = side_effect_first_date;
    }

    public Date getSide_effect_generation_date()
    {
        return side_effect_generation_date;
    }

    public void setSide_effect_generation_date(Date side_effect_generation_date)
    {
        this.side_effect_generation_date = side_effect_generation_date;
    }

    public Date getSide_effect_last_date()
    {
        return side_effect_last_date;
    }

    public void setSide_effect_last_date(Date side_effect_last_date)
    {
        this.side_effect_last_date = side_effect_last_date;
    }

    public List getSideeffectResult()
    {
        return sideeffectResult;
    }

    public void setSideeffectResult(List sideeffectResult)
    {
        this.sideeffectResult = sideeffectResult;
    }

    public List getSideeffectCause()
    {
        return sideeffectCause;
    }

    public void setSideeffectCause(List sideeffectCause)
    {
        this.sideeffectCause = sideeffectCause;
    }

    public Integer getCausality_id()
    {
        return Integer.valueOf(causality_id);
    }

    public void setCausality_id(int causality_id)
    {
        this.causality_id = causality_id;
    }

    public Causality getCausality()
    {
        return causality;
    }

    public void setCausality(Causality causality)
    {
        this.causality = causality;
    }

    public String getFollowup()
    {
        return followup;
    }

    public void setFollowup(String followup)
    {
        this.followup = followup;
    }

    public Integer getReport_status()
    {
        return Integer.valueOf(report_status);
    }

    public void setReport_status(int report_status)
    {
        this.report_status = report_status;
    }

    public ReportStatus getReportStatus()
    {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus)
    {
        this.reportStatus = reportStatus;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public String getOrganization_tel()
    {
        return organization_tel;
    }

    public void setOrganization_tel(String organization_tel)
    {
        this.organization_tel = organization_tel;
    }

    public String getOrganization_address()
    {
        return organization_address;
    }

    public void setOrganization_address(String organization_address)
    {
        this.organization_address = organization_address;
    }

    public List getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List attachments)
    {
        this.attachments = attachments;
    }

    public Severity getSeveritiy()
    {
        return severitiy;
    }

    public void setSeveritiy(Severity severitiy)
    {
        this.severitiy = severitiy;
    }

    public CauseOfSideeffect getCauseOfSideeffect()
    {
        return causeOfSideeffect;
    }

    public void setCauseOfSideeffect(CauseOfSideeffect causeOfSideeffect)
    {
        this.causeOfSideeffect = causeOfSideeffect;
    }

    public PatientCondition getCond()
    {
        return cond;
    }

    public void setCond(PatientCondition cond)
    {
        this.cond = cond;
    }

    public String getNewRepresentation()
    {
        return newRepresentation;
    }

    public void setNewRepresentation(String newRepresentation)
    {
        this.newRepresentation = newRepresentation;
    }

    public Integer getCountry_reported_id()
    {
        return Integer.valueOf(country_reported_id);
    }

    public void setCountry_reported_id(int country_reported_id)
    {
        this.country_reported_id = country_reported_id;
    }

    public Date getReport_date()
    {
        return report_date;
    }

    public void setReport_date(Date report_date)
    {
        this.report_date = report_date;
    }

    public Date getKfda_report_date()
    {
        return kfda_report_date;
    }

    public void setKfda_report_date(Date kfda_report_date)
    {
        this.kfda_report_date = kfda_report_date;
    }

    public Integer getSafety_report_type()
    {
        return Integer.valueOf(safety_report_type);
    }

    public void setSafety_report_type(int safety_report_type)
    {
        this.safety_report_type = safety_report_type;
    }

    public List getReporterTypes()
    {
        return reporterTypes;
    }

    public void setReporterTypes(List reporterTypes)
    {
        this.reporterTypes = reporterTypes;
    }

    public ComponentCode getComponentCode()
    {
        return componentCode;
    }

    public void setComponentCode(ComponentCode componentCode)
    {
        this.componentCode = componentCode;
    }

    public String getReport_fulltext_address()
    {
        return report_fulltext_address;
    }

    public void setReport_fulltext_address(String report_fulltext_address)
    {
        this.report_fulltext_address = report_fulltext_address;
    }

    public PatientCondition getPatientCondition()
    {
        return null;
    }

    public void setPatientCondition(PatientCondition patientcondition)
    {
    }

    public void setStringRepresentation(String s)
    {
    }

    public String getStringRepresentation()
    {
        return null;
    }

    public List getPatientCodeCondition()
    {
        return patientCodeCondition;
    }

    public void setPatientCodeCondition(List patientCodeCondition)
    {
        this.patientCodeCondition = patientCodeCondition;
    }

    public List getMedicalCode()
    {
        return medicalCode;
    }

    public void setMedicalCode(List medicalCode)
    {
        this.medicalCode = medicalCode;
    }

    public List getJuncComponentCode()
    {
        return juncComponentCode;
    }

    public void setJuncComponentCode(List juncComponentCode)
    {
        this.juncComponentCode = juncComponentCode;
    }

    public Integer getReport_type()
    {
        return Integer.valueOf(report_type);
    }

    public void setReport_type(int report_type)
    {
        this.report_type = report_type;
    }

    public String getReport_year()
    {
        return report_year;
    }

    public void setReport_year(String report_year)
    {
        this.report_year = report_year;
    }

    public String getReport_month()
    {
        return report_month;
    }

    public void setReport_month(String report_month)
    {
        this.report_month = report_month;
    }

    public SimpleReportDate getReportYM()
    {
        return reportYM;
    }

    public void setReportYM(SimpleReportDate reportYM)
    {
        this.reportYM = reportYM;
    }

    public void setFollow_up_action(Integer integer)
    {
    }

    public void setReport_type(Integer integer)
    {
    }

    public void setColReportType(Integer integer)
    {
    }

    public void setPatient_gender(Integer integer)
    {
    }

    public void setPatient_age(Integer integer)
    {
    }

    public void setCausality_id(Integer integer)
    {
    }

    public void setReport_status(Integer integer)
    {
    }

    public void setCountry_reported_id(Integer integer)
    {
    }

    public void setSafety_report_type(Integer integer)
    {
    }

    public Long getVarId()
    {
        return null;
    }

    public void setVarId(Long long1)
    {
    }

    public String getVarValue1()
    {
        return null;
    }

    public void setVarValue1(String s)
    {
    }

    public String getVarValue2()
    {
        return null;
    }

    public void setVarValue2(String s)
    {
    }

    public String getVarValue3()
    {
        return null;
    }

    public void setVarValue3(String s)
    {
    }

    public String getVarValue4()
    {
        return null;
    }

    public void setVarValue4(String s)
    {
    }

    public String getVarValue5()
    {
        return null;
    }

    public void setVarValue5(String s)
    {
    }

    public Long getVarCnt1()
    {
        return null;
    }

    public void setVarCnt1(Long long1)
    {
    }

    public Long getVarCnt2()
    {
        return null;
    }

    public void setVarCnt2(Long long1)
    {
    }

    public Long getVarCnt3()
    {
        return null;
    }

    public void setVarCnt3(Long long1)
    {
    }

    public ComponentCode getComponentCode1()
    {
        return componentCode1;
    }

    public void setComponentCode1(ComponentCode componentCode1)
    {
        this.componentCode1 = componentCode1;
    }

    public ComponentCode getComponentCode2()
    {
        return componentCode2;
    }

    public void setComponentCode2(ComponentCode componentCode2)
    {
        this.componentCode2 = componentCode2;
    }

    public ComponentCode getComponentCode3()
    {
        return componentCode3;
    }

    public void setComponentCode3(ComponentCode componentCode3)
    {
        this.componentCode3 = componentCode3;
    }

    public SimplePatientCondition getPatientCondition1()
    {
        return patientCondition1;
    }

    public void setPatientCondition1(SimplePatientCondition patientCondition1)
    {
        this.patientCondition1 = patientCondition1;
    }

    public SimplePatientCondition getPatientCondition2()
    {
        return patientCondition2;
    }

    public void setPatientCondition2(SimplePatientCondition patientCondition2)
    {
        this.patientCondition2 = patientCondition2;
    }

    public SimplePatientCondition getPatientCondition3()
    {
        return patientCondition3;
    }

    public void setPatientCondition3(SimplePatientCondition patientCondition3)
    {
        this.patientCondition3 = patientCondition3;
    }

    public SimpleMedicalDeviceMalfunctionCode getDeviceCode1()
    {
        return deviceCode1;
    }

    public void setDeviceCode1(SimpleMedicalDeviceMalfunctionCode deviceCode1)
    {
        this.deviceCode1 = deviceCode1;
    }

    public SimpleMedicalDeviceMalfunctionCode getDeviceCode2()
    {
        return deviceCode2;
    }

    public void setDeviceCode2(SimpleMedicalDeviceMalfunctionCode deviceCode2)
    {
        this.deviceCode2 = deviceCode2;
    }

    public SimpleMedicalDeviceMalfunctionCode getDeviceCode3()
    {
        return deviceCode3;
    }

    public void setDeviceCode3(SimpleMedicalDeviceMalfunctionCode deviceCode3)
    {
        this.deviceCode3 = deviceCode3;
    }

    public SimpleReportType2 getReportType1()
    {
        return reportType1;
    }

    public void setReportType1(SimpleReportType2 reportType1)
    {
        this.reportType1 = reportType1;
    }

    public SimpleReportType2 getReportType2()
    {
        return reportType2;
    }

    public void setReportType2(SimpleReportType2 reportType2)
    {
        this.reportType2 = reportType2;
    }

    public SimpleReportType2 getReportType3()
    {
        return reportType3;
    }

    public void setReportType3(SimpleReportType2 reportType3)
    {
        this.reportType3 = reportType3;
    }

    public SimpleReportType2 getReportType4()
    {
        return reportType4;
    }

    public void setReportType4(SimpleReportType2 reportType4)
    {
        this.reportType4 = reportType4;
    }

    public SimpleReportType2 getReportType5()
    {
        return reportType5;
    }

    public void setReportType5(SimpleReportType2 reportType5)
    {
        this.reportType5 = reportType5;
    }

    public String getKfda_followup()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getKfda_followup()\n");
    }

    public String getSide_cause_from_reporter()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getSide_cause_from_reporter()\n");
    }

    public String getOmponent_code_from_reporter()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getOmponent_code_from_reporter()\n");
    }

    public String getMeddev_code_from_reporter()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getMeddev_code_from_reporter()\n");
    }

    public Long getComponentCount()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getComponentCount()\n");
    }

    public String getPatient_condition_from_reporter()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getPatient_condition_from_reporter()\n");
    }

    public Long getPatientCount()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getPatientCount()\n");
    }

    public String getSide_result_from_reporter()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getSide_result_from_reporter()\n");
    }

    public String getCausality_from_reporter()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getCausality_from_reporter()\n");
    }

    public void setKfda_followup(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setKfda_followup(String)\n");
    }

    public Long getMedicalCount()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getMedicalCount()\n");
    }

    public void setComponentCount(Long long1)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setComponentCount(Long)\n");
    }

    public void setMeddev_code_from_reporter(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setMeddev_code_from_reporter(String)\n");
    }

    public void setOmponent_code_from_reporter(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setOmponent_code_from_reporter(String)\n");
    }

    public void setSide_cause_from_reporter(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setSide_cause_from_reporter(String)\n");
    }

    public void setPatientCount(Long long1)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setPatientCount(Long)\n");
    }

    public void setPatient_condition_from_reporter(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setPatient_condition_from_reporter(String)\n");
    }

    public Integer getVarIntId()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.getVarIntId()\n");
    }

    public void setSide_result_from_reporter(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setSide_result_from_reporter(String)\n");
    }

    public void setVarIntId(Integer integer)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setVarIntId(Integer)\n");
    }

    public void setCausality_from_reporter(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setCausality_from_reporter(String)\n");
    }

    public void setMedicalCount(Long long1)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSideeffectReport must implement the inherited abstract method Report.setMedicalCount(Long)\n");
    }

    private int id;
    private Integer colCountryReportedIn;
    private List reportType;
    private int colReportType;
    private Date reportDate;
    private ReporterType reporterType;
    private String company_name;
    private String representatives;
    private String manager;
    private String report_tel;
    private String fax;
    private String email;
    private String report_address;
    private Integer meb_item_id;
    private SimpleItem1 meb_item;
    private Integer country_manufactured_id;
    private CountryManufacturedIn countryManufacturedIn;
    private CountryReportedIn gubun;
    private String manufacturer;
    private String patient_name;
    private int patient_gender;
    private Gender gender;
    private int patient_age;
    private String patient_extra_info;
    private Date side_effect_first_date;
    private Date side_effect_generation_date;
    private Date side_effect_last_date;
    private List sideeffectResult;
    private List sideeffectCause;
    private int causality_id;
    private Causality causality;
    private String followup;
    private int report_status;
    private ReportStatus reportStatus;
    private String organization;
    private String organization_tel;
    private String organization_address;
    private List attachments;
    private Severity severitiy;
    private CauseOfSideeffect causeOfSideeffect;
    private PatientCondition cond;
    private String newRepresentation;
    private int country_reported_id;
    private Date report_date;
    private Date kfda_report_date;
    private int safety_report_type;
    private List reporterTypes;
    private ComponentCode componentCode;
    private String report_fulltext_address;
    private String extra_info;
    private List patientCodeCondition;
    private List medicalCode;
    private List juncComponentCode;
    private Date mreport_date;
    private SimpleCountry country_manufactured;
    private List sideEffectReportTypeDate;
    private int report_type;
    private Date first_modified;
    private Double document_number;
    private String report_year;
    private String report_month;
    private SimpleReportDate reportYM;
    private String reporter_etc;
    private String cause_etc;
    private String result_etc;
    private String serial_number;
    private String extra_info2;
    private int follow_up_action;
    private SimpleReportFollowUpAction obj_follow_up_action;
    private String followUpActionEtc;
    private List sideEffectReportMebTypeInfo;
    private Long batchGroupId;
    private String propertyValue;
    private Integer meddev_item_no;
    private Integer code_type;
    private SimpleReportType2 reportType1;
    private SimpleReportType2 reportType2;
    private SimpleReportType2 reportType3;
    private SimpleReportType2 reportType4;
    private SimpleReportType2 reportType5;
    private String reportDate1;
    private String reportDate2;
    private String reportDate3;
    private String reportDate4;
    private String reportDate5;
    private SideeffectResult sideeffectResult1;
    private SideeffectResult sideeffectResult2;
    private SideeffectResult sideeffectResult3;
    private SideeffectResult sideeffectResult4;
    private SideeffectResult sideeffectResult5;
    private SideeffectResult sideeffectResult6;
    private SideeffectResult sideeffectResult7;
    private SimplePatientCondition patientCondition1;
    private SimplePatientCondition patientCondition2;
    private SimplePatientCondition patientCondition3;
    private Integer patient_code1;
    private SimpleMedicalDeviceMalfunctionCode deviceCode1;
    private SimpleMedicalDeviceMalfunctionCode deviceCode2;
    private SimpleMedicalDeviceMalfunctionCode deviceCode3;
    private Long varCnt;
    private String varYear;
    private String varMonth;
    private ComponentCode componentCode1;
    private ComponentCode componentCode2;
    private ComponentCode componentCode3;
}

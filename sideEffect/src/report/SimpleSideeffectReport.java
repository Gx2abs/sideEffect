// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideeffectReport.java

package report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import properties.*;
import properties.country.SimpleCountry;
import safety.renewal.sgi.item.SimpleItem1;
import statistics.property.SimpleReportDate;

// Referenced classes of package report:
//            Report

public class SimpleSideeffectReport
    implements Report, Serializable
{

    public SimpleSideeffectReport()
    {
    }

    public Double getDocument_number()
    {
        return document_number;
    }

    public void setDocument_number(Double document_number)
    {
        this.document_number = document_number;
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
        return follow_up_action;
    }

    public void setFollow_up_action(Integer follow_up_action)
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

    public Long getVarCnt1()
    {
        return varCnt1;
    }

    public void setVarCnt1(Long varCnt1)
    {
        this.varCnt1 = varCnt1;
    }

    public Long getVarCnt2()
    {
        return varCnt2;
    }

    public void setVarCnt2(Long varCnt2)
    {
        this.varCnt2 = varCnt2;
    }

    public Long getVarCnt3()
    {
        return varCnt3;
    }

    public void setVarCnt3(Long varCnt3)
    {
        this.varCnt3 = varCnt3;
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

    public Integer getVarIntId()
    {
        return varIntId;
    }

    public void setVarIntId(Integer varIntId)
    {
        this.varIntId = varIntId;
    }

    public Long getVarId()
    {
        return varId;
    }

    public void setVarId(Long varId)
    {
        this.varId = varId;
    }

    public String getVarValue1()
    {
        return varValue1;
    }

    public void setVarValue1(String varValue1)
    {
        this.varValue1 = varValue1;
    }

    public String getVarValue2()
    {
        return varValue2;
    }

    public void setVarValue2(String varValue2)
    {
        this.varValue2 = varValue2;
    }

    public String getVarValue3()
    {
        return varValue3;
    }

    public void setVarValue3(String varValue3)
    {
        this.varValue3 = varValue3;
    }

    public String getVarValue4()
    {
        return varValue4;
    }

    public void setVarValue4(String varValue4)
    {
        this.varValue4 = varValue4;
    }

    public String getVarValue5()
    {
        return varValue5;
    }

    public void setVarValue5(String varValue5)
    {
        this.varValue5 = varValue5;
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
        return colReportType;
    }

    public void setColReportType(Integer colReportType)
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
        return patient_gender;
    }

    public void setPatient_gender(Integer patient_gender)
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
        return patient_age;
    }

    public void setPatient_age(Integer patient_age)
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
        return causality_id;
    }

    public void setCausality_id(Integer causality_id)
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
        return report_status;
    }

    public void setReport_status(Integer report_status)
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
        return country_reported_id;
    }

    public void setCountry_reported_id(Integer country_reported_id)
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
        return safety_report_type;
    }

    public void setSafety_report_type(Integer safety_report_type)
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
        return report_type;
    }

    public void setReport_type(Integer report_type)
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

    public String getSide_result_from_reporter()
    {
        return side_result_from_reporter;
    }

    public void setSide_result_from_reporter(String side_result_from_reporter)
    {
        this.side_result_from_reporter = side_result_from_reporter;
    }

    public String getSide_cause_from_reporter()
    {
        return side_cause_from_reporter;
    }

    public void setSide_cause_from_reporter(String side_cause_from_reporter)
    {
        this.side_cause_from_reporter = side_cause_from_reporter;
    }

    public String getPatient_condition_from_reporter()
    {
        return patient_condition_from_reporter;
    }

    public void setPatient_condition_from_reporter(String patient_condition_from_reporter)
    {
        this.patient_condition_from_reporter = patient_condition_from_reporter;
    }

    public String getMeddev_code_from_reporter()
    {
        return meddev_code_from_reporter;
    }

    public void setMeddev_code_from_reporter(String meddev_code_from_reporter)
    {
        this.meddev_code_from_reporter = meddev_code_from_reporter;
    }

    public String getOmponent_code_from_reporter()
    {
        return omponent_code_from_reporter;
    }

    public void setOmponent_code_from_reporter(String omponent_code_from_reporter)
    {
        this.omponent_code_from_reporter = omponent_code_from_reporter;
    }

    public String getCausality_from_reporter()
    {
        return causality_from_reporter;
    }

    public void setCausality_from_reporter(String causality_from_reporter)
    {
        this.causality_from_reporter = causality_from_reporter;
    }

    public String getKfda_followup()
    {
        return kfda_followup;
    }

    public void setKfda_followup(String kfda_followup)
    {
        this.kfda_followup = kfda_followup;
    }

    public Long getMedicalCount()
    {
        return medicalCount;
    }

    public void setMedicalCount(Long medicalCount)
    {
        this.medicalCount = medicalCount;
    }

    public Long getPatientCount()
    {
        return patientCount;
    }

    public void setPatientCount(Long patientCount)
    {
        this.patientCount = patientCount;
    }

    public Long getComponentCount()
    {
        return componentCount;
    }

    public void setComponentCount(Long componentCount)
    {
        this.componentCount = componentCount;
    }

    private int id;
    private Integer colCountryReportedIn;
    private List reportType;
    private Integer colReportType;
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
    private Integer patient_gender;
    private Gender gender;
    private Integer patient_age;
    private String patient_extra_info;
    private Date side_effect_first_date;
    private Date side_effect_generation_date;
    private Date side_effect_last_date;
    private List sideeffectResult;
    private List sideeffectCause;
    private Integer causality_id;
    private Causality causality;
    private String followup;
    private Integer report_status;
    private ReportStatus reportStatus;
    private String organization;
    private String organization_tel;
    private String organization_address;
    private List attachments;
    private Severity severitiy;
    private CauseOfSideeffect causeOfSideeffect;
    private PatientCondition cond;
    private String newRepresentation;
    private Integer country_reported_id;
    private Date report_date;
    private Date kfda_report_date;
    private Integer safety_report_type;
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
    private Integer report_type;
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
    private Integer follow_up_action;
    private SimpleReportFollowUpAction obj_follow_up_action;
    private String followUpActionEtc;
    private List sideEffectReportMebTypeInfo;
    private String side_result_from_reporter;
    private String side_cause_from_reporter;
    private String patient_condition_from_reporter;
    private String meddev_code_from_reporter;
    private String omponent_code_from_reporter;
    private String causality_from_reporter;
    private String kfda_followup;
    private Long varCnt;
    private Long varCnt1;
    private Long varCnt2;
    private Long varCnt3;
    private Integer varIntId;
    private Long varId;
    private String varValue1;
    private String varValue2;
    private String varValue3;
    private String varValue4;
    private String varValue5;
    private String varYear;
    private String varMonth;
    private Long medicalCount;
    private Long patientCount;
    private Long componentCount;
}

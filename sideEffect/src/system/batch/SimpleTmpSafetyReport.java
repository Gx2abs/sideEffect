// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleTmpSafetyReport.java

package system.batch;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import properties.*;
import properties.country.SimpleCountry;
import safety.SafetyReport;
import safety.renewal.sgi.item.SimpleItem1;
import statistics.property.SimpleReportDate;

public class SimpleTmpSafetyReport
    implements SafetyReport
{

    public SimpleTmpSafetyReport()
    {
    }

    public List getSafetyReportTypeDate()
    {
        return safetyReportTypeDate;
    }

    public void setSafetyReportTypeDate(List safetyReportTypeDate)
    {
        this.safetyReportTypeDate = safetyReportTypeDate;
    }

    public List getReportType()
    {
        return reportType;
    }

    public void setReportType(List reportType)
    {
        this.reportType = reportType;
    }

    public Long getBatchGroupId()
    {
        return batchGroupId;
    }

    public void setBatchGroupId(Long batchGroupId)
    {
        this.batchGroupId = batchGroupId;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public List getReport_type()
    {
        return report_type;
    }

    public void setReport_type(List report_type)
    {
        this.report_type = report_type;
    }

    public List getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List attachments)
    {
        this.attachments = attachments;
    }

    public String getKfdaReportDate()
    {
        return kfdaReportDate;
    }

    public void setKfdaReportDate(String kfdaReportDate)
    {
        this.kfdaReportDate = kfdaReportDate;
    }

    public Date getReportDate()
    {
        return reportDate;
    }

    public void setReportDate(Date reportDate)
    {
        this.reportDate = reportDate;
    }

    public ReportStatus getReportStatus()
    {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus)
    {
        System.out.println("SimpleSafetyReport.setReportStatus() has been invoked");
        try
        {
            this.reportStatus = reportStatus;
        }
        catch(Exception e)
        {
            System.out.println("SimpleSafetyReport.setReportStatus() exception stackTrace () : ");
            e.printStackTrace();
        }
    }

    public Integer getCause()
    {
        return cause;
    }

    public void setCause(Integer cause)
    {
        this.cause = cause;
    }

    public Integer getMeb_item_id()
    {
        return meb_item_id;
    }

    public void setMeb_item_id(Integer meb_item_id)
    {
        this.meb_item_id = meb_item_id;
    }

    public PatientCondition getPatientCondition()
    {
        return patientCondition;
    }

    public void setPatientCondition(PatientCondition patientCondition)
    {
        this.patientCondition = patientCondition;
    }

    public ComponentCode getComponentCode()
    {
        return componentCode;
    }

    public void setComponentCode(ComponentCode componentCode)
    {
        this.componentCode = componentCode;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public CountryReportedIn getCountryReportedIn()
    {
        return countryReportedIn;
    }

    public void setCountryReportedIn(CountryReportedIn countryReportedIn)
    {
        this.countryReportedIn = countryReportedIn;
    }

    public List getReporterTypes()
    {
        return reporterTypes;
    }

    public void setReporterTypes(List reporterTypes)
    {
        this.reporterTypes = reporterTypes;
    }

    public SafetyReportType getSafetyReportType()
    {
        return safetyReportType;
    }

    public void setSafetyReportType(SafetyReportType safetyReportType)
    {
        this.safetyReportType = safetyReportType;
    }

    public CountryManufacturedIn getCountryManufacturedIn()
    {
        return countryManufacturedIn;
    }

    public void setCountryManufacturedIn(CountryManufacturedIn countryManufacturedIn)
    {
        this.countryManufacturedIn = countryManufacturedIn;
    }

    public SimpleItem1 getMeb_item()
    {
        return meb_item;
    }

    public void setMeb_item(SimpleItem1 meb_item)
    {
        this.meb_item = meb_item;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getRepresentative()
    {
        return representative;
    }

    public void setRepresentative(String representative)
    {
        this.representative = representative;
    }

    public String getOrganisation()
    {
        return organisation;
    }

    public void setOrganisation(String organisation)
    {
        this.organisation = organisation;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getExtra_info()
    {
        return extra_info;
    }

    public void setExtra_info(String extra_info)
    {
        this.extra_info = extra_info;
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

    public String getSafety_cause_content()
    {
        return safety_cause_content;
    }

    public void setSafety_cause_content(String safety_cause_content)
    {
        this.safety_cause_content = safety_cause_content;
    }

    public String getFollowup()
    {
        return followup;
    }

    public void setFollowup(String followup)
    {
        this.followup = followup;
    }

    public Integer getCol_patient_condition_code()
    {
        return col_patient_condition_code;
    }

    public void setCol_patient_condition_code(Integer col_patient_condition_code)
    {
        this.col_patient_condition_code = col_patient_condition_code;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public String getCompany_name()
    {
        return company_name;
    }

    public void setCompany_name(String company_name)
    {
        this.company_name = company_name;
    }

    public int getCountry_manufactured_id()
    {
        return country_manufactured_id;
    }

    public void setCountry_manufactured_id(int country_manufactured_id)
    {
        this.country_manufactured_id = country_manufactured_id;
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

    public Date getFirst_modified()
    {
        return first_modified;
    }

    public void setFirst_modified(Date first_modified)
    {
        this.first_modified = first_modified;
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

    public Double getDocument_number()
    {
        return Double.valueOf(document_number);
    }

    public void setDocument_number(Double document_number)
    {
        this.document_number = document_number.doubleValue();
    }

    public String getReporter_etc()
    {
        return reporter_etc;
    }

    public void setReporter_etc(String reporter_etc)
    {
        this.reporter_etc = reporter_etc;
    }

    public String getSerial_number()
    {
        return serial_number;
    }

    public void setSerial_number(String serial_number)
    {
        this.serial_number = serial_number;
    }

    public String getSafety_report_type_etc()
    {
        return safety_report_type_etc;
    }

    public void setSafety_report_type_etc(String safety_report_type_etc)
    {
        this.safety_report_type_etc = safety_report_type_etc;
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

    public List getSafetyReportMebTypeInfo()
    {
        return safetyReportMebTypeInfo;
    }

    public void setSafetyReportMebTypeInfo(List safetyReportMebTypeInfo)
    {
        this.safetyReportMebTypeInfo = safetyReportMebTypeInfo;
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

    public Integer getCountry_reported_in()
    {
        return Integer.valueOf(0);
    }

    public void setCountry_reported_in(Integer integer)
    {
    }

    public Integer getReport_status()
    {
        return Integer.valueOf(0);
    }

    public void setReport_status(Integer integer)
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

    public String getKfda_followup()
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSafetyReport must implement the inherited abstract method SafetyReport.getKfda_followup()\n");
    }

    public void setKfda_followup(String s)
    {
        throw new Error("Unresolved compilation problem: \n\tThe type SimpleTmpSafetyReport must implement the inherited abstract method SafetyReport.setKfda_followup(String)\n");
    }

    private List reportType;
    private List safetyReportTypeDate;
    private CountryReportedIn countryReportedIn;
    private List reporterTypes;
    private SafetyReportType safetyReportType;
    private CountryManufacturedIn countryManufacturedIn;
    private SimpleItem1 meb_item;
    private String manager;
    private String representative;
    private String organisation;
    private String fax;
    private String address;
    private String telephone;
    private String email;
    private Date reportDate;
    private PatientCondition patientCondition;
    private ComponentCode componentCode;
    private Integer id;
    private Integer meb_item_id;
    private ReportStatus reportStatus;
    private Integer cause;
    private String kfdaReportDate;
    private List attachments;
    private String extra_info;
    private String safety_cause_content;
    private String followup;
    private List patientCodeCondition;
    private List medicalCode;
    private List juncComponentCode;
    private List report_type;
    private Integer col_patient_condition_code;
    private Long batchGroupId;
    private String propertyValue;
    private String company_name;
    private String manufacturer;
    private int country_manufactured_id;
    private SimpleCountry country_manufactured;
    private Date mreport_date;
    private Date first_modified;
    private Long varCnt;
    private String varYear;
    private String varMonth;
    private String report_year;
    private String report_month;
    private SimpleReportDate reportYM;
    private double document_number;
    private String reporter_etc;
    private String serial_number;
    private String safety_report_type_etc;
    private Integer follow_up_action;
    private SimpleReportFollowUpAction obj_follow_up_action;
    private String followUpActionEtc;
    private List safetyReportMebTypeInfo;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSafetyReportView.java

package safety;

import java.util.Date;

// Referenced classes of package safety:
//            SafetyReportView

public class SimpleSafetyReportView
    implements SafetyReportView
{

    public SimpleSafetyReportView()
    {
    }

    public String getAttachment()
    {
        return attachment;
    }

    public void setAttachment(String attachment)
    {
        this.attachment = attachment;
    }

    public String getExtra_info()
    {
        return extra_info;
    }

    public void setExtra_info(String extra_info)
    {
        this.extra_info = extra_info;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getDocument_number()
    {
        return document_number;
    }

    public void setDocument_number(Long document_number)
    {
        this.document_number = document_number;
    }

    public String getCompany_name()
    {
        return company_name;
    }

    public void setCompany_name(String company_name)
    {
        this.company_name = company_name;
    }

    public String getReporter_type()
    {
        return reporter_type;
    }

    public void setReporter_type(String reporter_type)
    {
        this.reporter_type = reporter_type;
    }

    public String getEntp_name()
    {
        return entp_name;
    }

    public void setEntp_name(String entp_name)
    {
        this.entp_name = entp_name;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getClass_kor_name()
    {
        return class_kor_name;
    }

    public void setClass_kor_name(String class_kor_name)
    {
        this.class_kor_name = class_kor_name;
    }

    public String getProduct_cob_code()
    {
        return product_cob_code;
    }

    public void setProduct_cob_code(String product_cob_code)
    {
        this.product_cob_code = product_cob_code;
    }

    public String getMedicalcode()
    {
        return medicalcode;
    }

    public void setMedicalcode(String medicalcode)
    {
        this.medicalcode = medicalcode;
    }

    public String getPatientcode()
    {
        return patientcode;
    }

    public void setPatientcode(String patientcode)
    {
        this.patientcode = patientcode;
    }

    public String getComponentcode()
    {
        return componentcode;
    }

    public void setComponentcode(String componentcode)
    {
        this.componentcode = componentcode;
    }

    public String getReport_type()
    {
        return report_type;
    }

    public void setReport_type(String report_type)
    {
        this.report_type = report_type;
    }

    public String getSafety_cause_content()
    {
        return safety_cause_content;
    }

    public void setSafety_cause_content(String safety_cause_content)
    {
        this.safety_cause_content = safety_cause_content;
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

    public Date getFirst_modified()
    {
        return first_modified;
    }

    public void setFirst_modified(Date first_modified)
    {
        this.first_modified = first_modified;
    }

    public String getGubun()
    {
        return gubun;
    }

    public void setGubun(String gubun)
    {
        this.gubun = gubun;
    }

    public String getReport_type2()
    {
        return report_type2;
    }

    public void setReport_type2(String report_type2)
    {
        this.report_type2 = report_type2;
    }

    public String getReport_date()
    {
        return report_date;
    }

    public void setReport_date(String report_date)
    {
        this.report_date = report_date;
    }

    public Date getMreport_date()
    {
        return mreport_date;
    }

    public void setMreport_date(Date mreport_date)
    {
        this.mreport_date = mreport_date;
    }

    public String getRepresentative()
    {
        return representative;
    }

    public void setRepresentative(String representative)
    {
        this.representative = representative;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
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

    public String getMeddev_entp_no()
    {
        return meddev_entp_no;
    }

    public void setMeddev_entp_no(String meddev_entp_no)
    {
        this.meddev_entp_no = meddev_entp_no;
    }

    public String getMea_class_no()
    {
        return mea_class_no;
    }

    public void setMea_class_no(String mea_class_no)
    {
        this.mea_class_no = mea_class_no;
    }

    public String getCountry_manufactured()
    {
        return country_manufactured;
    }

    public void setCountry_manufactured(String country_manufactured)
    {
        this.country_manufactured = country_manufactured;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public String getMeb_type()
    {
        return meb_type;
    }

    public void setMeb_type(String meb_type)
    {
        this.meb_type = meb_type;
    }

    public String getSerial_number()
    {
        return serial_number;
    }

    public void setSerial_number(String serial_number)
    {
        this.serial_number = serial_number;
    }

    public String getFollowup()
    {
        return followup;
    }

    public void setFollowup(String followup)
    {
        this.followup = followup;
    }

    public String getFollow_up_action()
    {
        return follow_up_action;
    }

    public void setFollow_up_action(String follow_up_action)
    {
        this.follow_up_action = follow_up_action;
    }

    public String getReport_status()
    {
        return report_status;
    }

    public void setReport_status(String report_status)
    {
        this.report_status = report_status;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getMeddev_item_no()
    {
        return meddev_item_no;
    }

    public void setMeddev_item_no(String meddev_item_no)
    {
        this.meddev_item_no = meddev_item_no;
    }

    public Integer getMeddev_item_no1()
    {
        return meddev_item_no1;
    }

    public void setMeddev_item_no1(Integer meddev_item_no1)
    {
        this.meddev_item_no1 = meddev_item_no1;
    }

    public Integer getMeddev_item_no2()
    {
        return meddev_item_no2;
    }

    public void setMeddev_item_no2(Integer meddev_item_no2)
    {
        this.meddev_item_no2 = meddev_item_no2;
    }

    public String getProduct_value()
    {
        return product_value;
    }

    public void setProduct_value(String product_value)
    {
        this.product_value = product_value;
    }

    public String getKfda_followup()
    {
        return kfda_followup;
    }

    public void setKfda_followup(String kfda_followup)
    {
        this.kfda_followup = kfda_followup;
    }

    private Long id;
    private Long document_number;
    private String company_name;
    private String reporter_type;
    private String entp_name;
    private String grade;
    private String class_kor_name;
    private String product_cob_code;
    private String medicalcode;
    private String patientcode;
    private String componentcode;
    private String report_type;
    private String safety_cause_content;
    private String report_year;
    private String report_month;
    private Date first_modified;
    private String gubun;
    private String report_type2;
    private String report_date;
    private Date mreport_date;
    private String representative;
    private String manager;
    private String telephone;
    private String fax;
    private String email;
    private String address;
    private String meddev_entp_no;
    private String mea_class_no;
    private String country_manufactured;
    private String manufacturer;
    private String meb_type;
    private String serial_number;
    private String followup;
    private String follow_up_action;
    private String report_status;
    private String extra_info;
    private String attachment;
    private String meddev_item_no;
    private Integer meddev_item_no1;
    private Integer meddev_item_no2;
    private String product_value;
    private String kfda_followup;
}

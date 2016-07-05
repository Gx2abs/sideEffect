// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleProduct.java

package properties.product;

import foreign.Mea_class_no;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import properties.company.SimpleCompany;

// Referenced classes of package properties.product:
//            Product

public class SimpleProduct
    implements Product
{

    public SimpleProduct()
    {
        productHistory = new HashSet();
        product_lot = new HashSet();
        product_type = new HashSet();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getManuf_import_name()
    {
        return manuf_import_name;
    }

    public void setManuf_import_name(String manuf_import_name)
    {
        this.manuf_import_name = manuf_import_name;
    }

    public String getManuf_lot()
    {
        return manuf_lot;
    }

    public void setManuf_lot(String manuf_lot)
    {
        this.manuf_lot = manuf_lot;
    }

    public String getShutdown_close_reopen_code()
    {
        return shutdown_close_reopen_code;
    }

    public void setShutdown_close_reopen_code(String shutdown_close_reopen_code)
    {
        this.shutdown_close_reopen_code = shutdown_close_reopen_code;
    }

    public int getMeddev_item_seq()
    {
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(int meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    public String getMachinery_area_code()
    {
        return machinery_area_code;
    }

    public void setMachinery_area_code(String machinery_area_code)
    {
        this.machinery_area_code = machinery_area_code;
    }

    public String getCob_flag_code()
    {
        return cob_flag_code;
    }

    public void setCob_flag_code(String cob_flag_code)
    {
        this.cob_flag_code = cob_flag_code;
    }

    public String getPermit_state_code()
    {
        return permit_state_code;
    }

    public void setPermit_state_code(String permit_state_code)
    {
        this.permit_state_code = permit_state_code;
    }

    public String getMeddev_item_no()
    {
        return meddev_item_no;
    }

    public void setMeddev_item_no(String meddev_item_no)
    {
        this.meddev_item_no = meddev_item_no;
    }

    public String getTax_no()
    {
        return tax_no;
    }

    public void setTax_no(String tax_no)
    {
        this.tax_no = tax_no;
    }

    public String getItem_name()
    {
        return item_name;
    }

    public void setItem_name(String item_name)
    {
        this.item_name = item_name;
    }

    public Date getPermit_date()
    {
        return permit_date;
    }

    public void setPermit_date(Date permit_date)
    {
        this.permit_date = permit_date;
    }

    public String getManuf_country_code()
    {
        return manuf_country_code;
    }

    public void setManuf_country_code(String manuf_country_code)
    {
        this.manuf_country_code = manuf_country_code;
    }

    public String getManuf_name()
    {
        return manuf_name;
    }

    public void setManuf_name(String manuf_name)
    {
        this.manuf_name = manuf_name;
    }

    public String getManuf_place()
    {
        return manuf_place;
    }

    public void setManuf_place(String manuf_place)
    {
        this.manuf_place = manuf_place;
    }

    public String getManuf_req_country_code()
    {
        return manuf_req_country_code;
    }

    public void setManuf_req_country_code(String manuf_req_country_code)
    {
        this.manuf_req_country_code = manuf_req_country_code;
    }

    public String getManuf_req_name()
    {
        return manuf_req_name;
    }

    public void setManuf_req_name(String manuf_req_name)
    {
        this.manuf_req_name = manuf_req_name;
    }

    public String getManuf_req_place()
    {
        return manuf_req_place;
    }

    public void setManuf_req_place(String manuf_req_place)
    {
        this.manuf_req_place = manuf_req_place;
    }

    public String getSeller_country_code()
    {
        return seller_country_code;
    }

    public void setSeller_country_code(String seller_country_code)
    {
        this.seller_country_code = seller_country_code;
    }

    public String getSeller_name()
    {
        return seller_name;
    }

    public void setSeller_name(String seller_name)
    {
        this.seller_name = seller_name;
    }

    public String getSeller_place()
    {
        return seller_place;
    }

    public void setSeller_place(String seller_place)
    {
        this.seller_place = seller_place;
    }

    public String getManuf_import_entp_name()
    {
        return manuf_import_entp_name;
    }

    public void setManuf_import_entp_name(String manuf_import_entp_name)
    {
        this.manuf_import_entp_name = manuf_import_entp_name;
    }

    public String getManuf_import_zip_no()
    {
        return manuf_import_zip_no;
    }

    public void setManuf_import_zip_no(String manuf_import_zip_no)
    {
        this.manuf_import_zip_no = manuf_import_zip_no;
    }

    public String getManuf_import_addr1()
    {
        return manuf_import_addr1;
    }

    public void setManuf_import_addr1(String manuf_import_addr1)
    {
        this.manuf_import_addr1 = manuf_import_addr1;
    }

    public String getManuf_import_addr2()
    {
        return manuf_import_addr2;
    }

    public void setManuf_import_addr2(String manuf_import_addr2)
    {
        this.manuf_import_addr2 = manuf_import_addr2;
    }

    public String getCancel_withdw_code()
    {
        return cancel_withdw_code;
    }

    public void setCancel_withdw_code(String cancel_withdw_code)
    {
        this.cancel_withdw_code = cancel_withdw_code;
    }

    public Date getCancel_withdw_date()
    {
        return cancel_withdw_date;
    }

    public void setCancel_withdw_date(Date cancel_withdw_date)
    {
        this.cancel_withdw_date = cancel_withdw_date;
    }

    public String getShape()
    {
        return shape;
    }

    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public String getSumr_name()
    {
        return sumr_name;
    }

    public void setSumr_name(String sumr_name)
    {
        this.sumr_name = sumr_name;
    }

    public String getSize_cnt()
    {
        return size_cnt;
    }

    public void setSize_cnt(String size_cnt)
    {
        this.size_cnt = size_cnt;
    }

    public String getChart_name()
    {
        return chart_name;
    }

    public void setChart_name(String chart_name)
    {
        this.chart_name = chart_name;
    }

    public String getMake_method()
    {
        return make_method;
    }

    public void setMake_method(String make_method)
    {
        this.make_method = make_method;
    }

    public String getUse_purpose()
    {
        return use_purpose;
    }

    public void setUse_purpose(String use_purpose)
    {
        this.use_purpose = use_purpose;
    }

    public String getUse_method()
    {
        return use_method;
    }

    public void setUse_method(String use_method)
    {
        this.use_method = use_method;
    }

    public String getNb()
    {
        return nb;
    }

    public void setNb(String nb)
    {
        this.nb = nb;
    }

    public String getValid_term()
    {
        return valid_term;
    }

    public void setValid_term(String valid_term)
    {
        this.valid_term = valid_term;
    }

    public String getExam_std()
    {
        return exam_std;
    }

    public void setExam_std(String exam_std)
    {
        this.exam_std = exam_std;
    }

    public String getPermit_cond()
    {
        return permit_cond;
    }

    public void setPermit_cond(String permit_cond)
    {
        this.permit_cond = permit_cond;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public int getMeddev_entp_seq()
    {
        return meddev_entp_seq;
    }

    public void setMeddev_entp_seq(int meddev_entp_seq)
    {
        this.meddev_entp_seq = meddev_entp_seq;
    }

    public String getMea_class_no()
    {
        return mea_class_no;
    }

    public void setMea_class_no(String mea_class_no)
    {
        this.mea_class_no = mea_class_no;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getReceipt_no()
    {
        return receipt_no;
    }

    public void setReceipt_no(String receipt_no)
    {
        this.receipt_no = receipt_no;
    }

    public String getBef_doc_id()
    {
        return bef_doc_id;
    }

    public void setBef_doc_id(String bef_doc_id)
    {
        this.bef_doc_id = bef_doc_id;
    }

    public String getSafe_exam_yn()
    {
        return safe_exam_yn;
    }

    public void setSafe_exam_yn(String safe_exam_yn)
    {
        this.safe_exam_yn = safe_exam_yn;
    }

    public String getExport_dev_yn()
    {
        return export_dev_yn;
    }

    public void setExport_dev_yn(String export_dev_yn)
    {
        this.export_dev_yn = export_dev_yn;
    }

    public String getSame_goods_yn()
    {
        return same_goods_yn;
    }

    public void setSame_goods_yn(String same_goods_yn)
    {
        this.same_goods_yn = same_goods_yn;
    }

    public String getSame_item_no()
    {
        return same_item_no;
    }

    public void setSame_item_no(String same_item_no)
    {
        this.same_item_no = same_item_no;
    }

    public String getStorage_method()
    {
        return storage_method;
    }

    public void setStorage_method(String storage_method)
    {
        this.storage_method = storage_method;
    }

    public String getMaterial()
    {
        return material;
    }

    public void setMaterial(String material)
    {
        this.material = material;
    }

    public String getMention_cont()
    {
        return mention_cont;
    }

    public void setMention_cont(String mention_cont)
    {
        this.mention_cont = mention_cont;
    }

    public Date getRegist_ts()
    {
        return regist_ts;
    }

    public void setRegist_ts(Date regist_ts)
    {
        this.regist_ts = regist_ts;
    }

    public String getRegist_id()
    {
        return regist_id;
    }

    public void setRegist_id(String regist_id)
    {
        this.regist_id = regist_id;
    }

    public Date getUpdate_ts()
    {
        return update_ts;
    }

    public void setUpdate_ts(Date update_ts)
    {
        this.update_ts = update_ts;
    }

    public String getUpdate_id()
    {
        return update_id;
    }

    public void setUpdate_id(String update_id)
    {
        this.update_id = update_id;
    }

    public String getServ_target()
    {
        return serv_target;
    }

    public void setServ_target(String serv_target)
    {
        this.serv_target = serv_target;
    }

    public String getTrace_manage_target_yn()
    {
        return trace_manage_target_yn;
    }

    public void setTrace_manage_target_yn(String trace_manage_target_yn)
    {
        this.trace_manage_target_yn = trace_manage_target_yn;
    }

    public String getReexam_yn()
    {
        return reexam_yn;
    }

    public void setReexam_yn(String reexam_yn)
    {
        this.reexam_yn = reexam_yn;
    }

    public Date getReexam_valid_start_date()
    {
        return reexam_valid_start_date;
    }

    public void setReexam_valid_start_date(Date reexam_valid_start_date)
    {
        this.reexam_valid_start_date = reexam_valid_start_date;
    }

    public Date getReexam_valid_end_date()
    {
        return reexam_valid_end_date;
    }

    public void setReexam_valid_end_date(Date reexam_valid_end_date)
    {
        this.reexam_valid_end_date = reexam_valid_end_date;
    }

    public String getServ_target2()
    {
        return serv_target2;
    }

    public void setServ_target2(String serv_target2)
    {
        this.serv_target2 = serv_target2;
    }

    public String getBef_cancel_withdw_code()
    {
        return bef_cancel_withdw_code;
    }

    public void setBef_cancel_withdw_code(String bef_cancel_withdw_code)
    {
        this.bef_cancel_withdw_code = bef_cancel_withdw_code;
    }

    public String getIssue_org_code()
    {
        return issue_org_code;
    }

    public void setIssue_org_code(String issue_org_code)
    {
        this.issue_org_code = issue_org_code;
    }

    public String getClass_change_yn()
    {
        return class_change_yn;
    }

    public void setClass_change_yn(String class_change_yn)
    {
        this.class_change_yn = class_change_yn;
    }

    public String getDelete_yn()
    {
        return delete_yn;
    }

    public void setDelete_yn(String delete_yn)
    {
        this.delete_yn = delete_yn;
    }

    public String getAmterial_ver()
    {
        return amterial_ver;
    }

    public void setAmterial_ver(String amterial_ver)
    {
        this.amterial_ver = amterial_ver;
    }

    public String getDisposable_emed_yn()
    {
        return disposable_emed_yn;
    }

    public void setDisposable_emed_yn(String disposable_emed_yn)
    {
        this.disposable_emed_yn = disposable_emed_yn;
    }

    public String getMaterial_ver()
    {
        return material_ver;
    }

    public void setMaterial_ver(String material_ver)
    {
        this.material_ver = material_ver;
    }

    public Mea_class_no getMea_class_no_obj()
    {
        return mea_class_no_obj;
    }

    public void setMea_class_no_obj(Mea_class_no mea_class_no_obj)
    {
        this.mea_class_no_obj = mea_class_no_obj;
    }

    public int getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(int isInUse)
    {
        this.isInUse = isInUse;
    }

    public long getId()
    {
        return (long)meddev_item_seq;
    }

    public void setId(long id)
    {
        meddev_item_seq = (int)id;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public Set getProductHistory()
    {
        return productHistory;
    }

    public void setProductHistory(Set productHistory)
    {
        this.productHistory = productHistory;
    }

    public Date getFirstModified()
    {
        return firstModified;
    }

    public void setFirstModified(Date firstModified)
    {
        this.firstModified = firstModified;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public int getFirstResult()
    {
        return firstResult;
    }

    public void setFirstResult(int firstResult)
    {
        this.firstResult = firstResult;
    }

    public int getMaxResults()
    {
        return maxResults;
    }

    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public Date getActiveFrom()
    {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom)
    {
        this.activeFrom = activeFrom;
    }

    public int getDefault_in_use_id()
    {
        return default_in_use_id;
    }

    public void setDefault_in_use_id(int default_in_use_id)
    {
        this.default_in_use_id = default_in_use_id;
    }

    public SimpleCompany getCompany()
    {
        return company;
    }

    public void setCompany(SimpleCompany company)
    {
        this.company = company;
    }

    public Mea_class_no getMea_item()
    {
        return mea_item;
    }

    public void setMea_item(Mea_class_no mea_item)
    {
        this.mea_item = mea_item;
    }

    public Set getProduct_lot()
    {
        return product_lot;
    }

    public void setProduct_lot(Set product_lot)
    {
        this.product_lot = product_lot;
    }

    public Set getProduct_type()
    {
        return product_type;
    }

    public void setProduct_type(Set product_type)
    {
        this.product_type = product_type;
    }

    private long id;
    private String propertyValue;
    private Set productHistory;
    private Set product_lot;
    private Set product_type;
    private Date firstModified;
    private Date lastModified;
    private Date activeFrom;
    private int firstResult;
    private int maxResults;
    private int page;
    private int isInUse;
    private int meddev_item_seq;
    private String machinery_area_code;
    private String cob_flag_code;
    private String permit_state_code;
    private String meddev_item_no;
    private String tax_no;
    private String item_name;
    private Date permit_date;
    private String manuf_country_code;
    private String manuf_name;
    private String manuf_place;
    private String manuf_req_country_code;
    private String manuf_req_name;
    private String manuf_req_place;
    private String seller_country_code;
    private String seller_name;
    private String seller_place;
    private String manuf_import_entp_name;
    private String manuf_import_zip_no;
    private String manuf_import_addr1;
    private String manuf_import_addr2;
    private String cancel_withdw_code;
    private Date cancel_withdw_date;
    private String shape;
    private String sumr_name;
    private String size_cnt;
    private String chart_name;
    private String make_method;
    private String use_purpose;
    private String use_method;
    private String nb;
    private String valid_term;
    private String exam_std;
    private String permit_cond;
    private String remark;
    private int meddev_entp_seq;
    private String mea_class_no;
    private String grade;
    private String receipt_no;
    private String bef_doc_id;
    private String safe_exam_yn;
    private String export_dev_yn;
    private String same_goods_yn;
    private String same_item_no;
    private String storage_method;
    private String material;
    private String mention_cont;
    private Date regist_ts;
    private String regist_id;
    private Date update_ts;
    private String update_id;
    private String serv_target;
    private String trace_manage_target_yn;
    private String reexam_yn;
    private Date reexam_valid_start_date;
    private Date reexam_valid_end_date;
    private String serv_target2;
    private String bef_cancel_withdw_code;
    private String issue_org_code;
    private String class_change_yn;
    private String delete_yn;
    private String amterial_ver;
    private String disposable_emed_yn;
    private String material_ver;
    private Mea_class_no mea_class_no_obj;
    private String name;
    private String manuf_import_name;
    private String manuf_lot;
    private int default_in_use_id;
    private SimpleCompany company;
    private Mea_class_no mea_item;
    private String shutdown_close_reopen_code;
}

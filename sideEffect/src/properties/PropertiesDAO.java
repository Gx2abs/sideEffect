// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertiesDAO.java

package properties;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import foreign.Mea_class_no;
import foreign.Mea_class_no_view2;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import properties.report.type.SimpleReportType;
import properties.report.type.SimpleReportTypeHistory;
import safety.renewal.sgi.category.SimpleItemCategory;
import sideeffect.JuncSideEffectResult;

// Referenced classes of package properties:
//            SimplePatientCondition, SimpleMedicalDeviceMalfunctionCode, SimpleComponentCode

public interface PropertiesDAO
    extends CRUDable
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract long count(IDValuePair idvaluepair);

    public abstract long count(Object obj);

    public abstract long countCompany(String s, String s1);

    public abstract long countProduct(String s, String s1);

    public abstract long count(SimpleItemCategory simpleitemcategory, int i);

    public abstract List listProduct(Class class1, int i, int j);

    public abstract List listCompany(Class class1, int i, int j);

    public abstract List listEntpProduct(int i, int j, String s);

    public abstract Long listEntpProductCnt(String s);

    public abstract List listItemProduct(int i, int j, String s);

    public abstract Long listItemProductCnt(String s);

    public abstract IDValuePair saveOrUpdate(IDValuePair idvaluepair);

    public abstract Set listMeaClassNo(Mea_class_no mea_class_no, int i, int j);

    public abstract long count(Mea_class_no mea_class_no, int i);

    public abstract long count(Mea_class_no_view2 mea_class_no_view2, int i);

    public abstract List listMeaClassNo(Mea_class_no mea_class_no, int i, int j, int k);

    public abstract boolean deleteMeaClassNo(Mea_class_no mea_class_no);

    public abstract Mea_class_no readMeaClassNo(Class class1, int i, String s);

    public abstract Mea_class_no updateMeaClassNo(Mea_class_no mea_class_no);

    public abstract List listMeaClassNo(Mea_class_no mea_class_no);

    public abstract Object create(Object obj, boolean flag);

    public abstract List listPatientCondition(SimplePatientCondition simplepatientcondition, int i);

    public abstract List listPatientCondition(int i, int j);

    public abstract SimplePatientCondition getPatientConditionParent(long l);

    public abstract List byLevel1PatientCondition(int i);

    public abstract List byLevelPatientConditionStep(int i, int j, int k);

    public abstract List listMedicalDevice(SimpleMedicalDeviceMalfunctionCode simplemedicaldevicemalfunctioncode, int i);

    public abstract List listMedicalDevice(int i, int j);

    public abstract SimpleMedicalDeviceMalfunctionCode getMedicalDeviceParent(long l);

    public abstract List byLevel1MedicalDevice(int i);

    public abstract List byLevelMedicalDeviceStep(int i, int j, int k);

    public abstract List listComponent(SimpleComponentCode simplecomponentcode, int i);

    public abstract List listComponent(int i, int j);

    public abstract SimpleComponentCode getComponentParent(long l);

    public abstract List byLevel1Component(int i);

    public abstract List byLevelComponentStep(int i, int j, int k);

    public abstract List listMeaClassNo(Mea_class_no mea_class_no, int i);

    public abstract int add(SimpleReportType simplereporttype);

    public abstract int add(SimpleReportTypeHistory simplereporttypehistory);

    public abstract int clearJunction(String s, String s1, long l);

    public abstract List listPopupPatient(String s);

    public abstract List listPopupMedical(String s);

    public abstract List listPopupComponent(String s);

    public abstract List codeList(Class class1, String s, String s1);

    public abstract List sideeffectCodeList(Class class1, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, String s8);

    public abstract List sideeffectLevelCodeList(Class class1, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, Integer integer, String s8);

    public abstract List safetyCodeList(Class class1, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, String s8);

    public abstract List safetyLevelCodeList(Class class1, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, Integer integer, String s8);

    public abstract List riskSideeffectResultList(String s, String s1, Integer integer, long l);

    public abstract List listMeaClassNo(String s, int i, int j);

    public abstract Mea_class_no getParent(String s);

    public abstract Set listItemCategory(SimpleItemCategory simpleitemcategory, int i, int j);

    public abstract List list(Class class1, int i, int j);

    public abstract List listMeaClassNoView2(Mea_class_no_view2 mea_class_no_view2, int i, int j, int k);

    public abstract long sideEffectDocumentNumberCheck(long l);

    public abstract long safetyDocumentNumberCheck(long l);

    public abstract long codeDelete(long l, long l1, long l2, int i);

    public abstract Object sideEffectMreportCount(Object obj);

    public abstract Object safetyMreportCount(Object obj);

    public abstract long search(String s, String s1);

    public abstract long count(JuncSideEffectResult juncsideeffectresult);

    public abstract List resultList(Class class1, int i);
    
    public abstract IDValuePair readMeaClassNo(Mea_class_no mea_class_no);
}

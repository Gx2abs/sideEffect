// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SafetyDAO.java

package safety;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import org.hibernate.SessionFactory;
import properties.SimpleSafetyReportMebTypeInfo;

// Referenced classes of package safety:
//            SafetyReport, SafetyReportView, SimpleSafetyReport, SafetyReportVO, 
//            JuncSafetyReportType, JuncSafetyReporterTypes, JuncSafetyPatientCondition, JuncSafetyMedicalCode, 
//            JuncSafetyComponentCode, SimpleJuncSafetyReportType2, SimpleSafetyReportTypeDate, SimpleSafetyReportView

public interface SafetyDAO
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List list(SafetyReport safetyreport);

    public abstract List list(SafetyReport safetyreport, int i, int j);

    public abstract List list(SafetyReport safetyreport, int i, String s, String s1, String s2, String s3);

    public abstract List list(SafetyReport safetyreport, List list1, List list3, List list4, int i, String s, String s1, 
            String s2, String s3, String s4, int j, int k);

    public abstract List list2(SafetyReportView safetyreportview, List list1, List list3, List list4, List list5, int i, String s, 
            String s1, String s2, String s3, String s4, int j, int k, int l, 
            String s5, String s6, String s7);

    public abstract List searchList(SafetyReport safetyreport, int i, int j, HashSet hashset);

    public abstract List searchList(SafetyReport safetyreport, HashSet hashset);

    public abstract List searchList(SafetyReport safetyreport, HashSet hashset, String s);

    public abstract int add(SimpleSafetyReport simplesafetyreport);

    public abstract int add(SafetyReportVO safetyreportvo);

    public abstract int update(SimpleSafetyReport simplesafetyreport);

    public abstract SafetyReport read(int i);

    public abstract int delete(int i);

    public abstract long reportTypeDateDelete(int i);

    public abstract long juncReportTypeDateDelete(int i);

    public abstract long juncReporterTypeDateDelete(int i);

    public abstract long juncSafetyReportTypeDateDelete(int i);

    public abstract long safetyPatientDelete(int i);

    public abstract long safetyMedicalCodeDelete(int i);

    public abstract long safetyComponentCodeDelete(int i);

    public abstract long reporterTypesAdd(JuncSafetyReportType juncsafetyreporttype);

    public abstract long reporterTypesAdd(JuncSafetyReporterTypes juncsafetyreportertypes);

    public abstract long safetyPatientAdd(JuncSafetyPatientCondition juncsafetypatientcondition);

    public abstract long safetyMedicalCodeAdd(JuncSafetyMedicalCode juncsafetymedicalcode);

    public abstract long safetyComponentCodeAdd(JuncSafetyComponentCode juncsafetycomponentcode);

    public abstract long reportTypesAdd(SimpleJuncSafetyReportType2 simplejuncsafetyreporttype2);

    public abstract long reportTypeDateAdd(SimpleSafetyReportTypeDate simplesafetyreporttypedate);

    public abstract int reporterTypesDel(int i);

    public abstract List get(String s);

    public abstract long count(SimpleSafetyReport simplesafetyreport);

    public abstract List countryReportedInSearch(String s);

    public abstract List reportTypeSearch(String s);

    public abstract List reporterTypeSearch(String s);

    public abstract List mebItemSearch(String s);

    public abstract List reportStatusSearch(String s);

    public abstract List safetyCnt(String s, Integer integer, String s1, String s2, String s3, String s4);

    public abstract List safetyCnt(String s, Integer integer, String s1, String s2, String s3);

    public abstract List safetyCnt(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7);

    public abstract List safetyCnt(String s, String s1, String s2, String s3, String s4, String s5, String s6);

    public abstract List ItemSafetyList(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, int i, int j, String s9);

    public abstract long ItemSafetyCnt(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9);

    public abstract List ItemSafetyCodeList(long l, String s, String s1, String s2, String s3, String s4, 
            String s5, String s6);

    public abstract List safetyCntStep(String as[][], String s, String s1, String s2, Integer integer);

    public abstract long mebTypeInfoSelectAdd(SimpleSafetyReportMebTypeInfo simplesafetyreportmebtypeinfo);

    public abstract long mebTypeInfoSelectDelete(int i);

    public abstract BigDecimal reportCount(SimpleSafetyReportView simplesafetyreportview, List list1, List list3, List list4, List list5, int i, String s, 
            String s1, String s2, String s3, String s4, int j, int k);
}

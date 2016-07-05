// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SideeffectReportDAO.java

package report;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import org.hibernate.SessionFactory;
import properties.*;
import sideeffect.*;

// Referenced classes of package report:
//            SimpleSideeffectReport, SideeffectReportVO, Report

public interface SideeffectReportDAO
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List list(SimpleSideeffectReport simplesideeffectreport);

    public abstract List list(SimpleSideeffectReport simplesideeffectreport, int i, int j);

    public abstract List list(SimpleSideeffectReport simplesideeffectreport, String s);

    public abstract List searchList(SimpleSideeffectReport simplesideeffectreport, int i, int j, HashSet hashset);

    public abstract List searchList(SimpleSideeffectReport simplesideeffectreport, HashSet hashset, String s);

    public abstract List details_search(SimpleSideeffectReport simplesideeffectreport, List list1, List list2, List list3, int i, String s, String s1, 
            String s2, String s3, String s4, int j, int k);

    public abstract List details_search2(SideeffectReportVO sideeffectreportvo, List list1, List list2, List list3, List list4, int i, String s, 
            String s1, String s2, String s3, String s4, int j, int k, int l, 
            int i1, int j1, String s5, String s6, String s7);

    public abstract BigDecimal countReportList(SimpleSideeffectReport simplesideeffectreport, List list1, List list2, List list3, List list4, int i, String s, 
            String s1, String s2, String s3, String s4, int j);

    public abstract List list(SimpleGender simplegender);

    public abstract List list(SimpleSideeffectResult simplesideeffectresult);

    public abstract List list(SimpleSideeffectCause simplesideeffectcause);

    public abstract Report read(int i);

    public abstract int delete(int i);

    public abstract int fileDelete(int i);

    public abstract int juncReportAttachmentDelete(int i);

    public abstract int add(SimpleSideeffectReport simplesideeffectreport);

    public abstract int update(SimpleSideeffectReport simplesideeffectreport);

    public abstract long reportTypesAdd(JuncSideEffectReportTypes2 juncsideeffectreporttypes2);

    public abstract long reporterTypesAdd(JuncSideEffectReportTypes juncsideeffectreporttypes);

    public abstract long sideEffectResultAdd(JuncSideEffectResult juncsideeffectresult);

    public abstract long sideEffectCauseAdd(JuncSideEffectCause juncsideeffectcause);

    public abstract long patientCodeAdd(JuncSideEffectPatientCondition juncsideeffectpatientcondition);

    public abstract long medicalCodeAdd(JuncSideEffectMedicalCode juncsideeffectmedicalcode);

    public abstract long componentCodeAdd(JuncSideEffectComponentCode juncsideeffectcomponentcode);

    public abstract long mebTypeInfoSelectAdd(SimpleSideEffectReportMebTypeInfo simplesideeffectreportmebtypeinfo);

    public abstract long reportTypeDateAdd(SimpleSideEffectReportTypeDate simplesideeffectreporttypedate);

    public abstract int reportTypeDateDelete(int i);

    public abstract List countryReportedInSearch(String s);

    public abstract List reportTypeSearch(String s);

    public abstract List reporterTypeSearch(String s);

    public abstract List mebItemSearch(String s);

    public abstract List patientSearch(String s);

    public abstract List sideeffectResultSearch(String s);

    public abstract List sideeffectCauseSearch(String s);

    public abstract List causalitySearch(String s);

    public abstract List reportStatusSearch(String s);

    public abstract List sideeffectCnt(String s, Integer integer, String s1, String s2, String s3, String s4);

    public abstract List sideeffectCnt(String s, Integer integer, String s1, String s2, String s3);

    public abstract List sideeffectCnt(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8);

    public abstract List sideeffectCnt(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7);

    public abstract List ItemSideeffectList(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, int i, int j, String s9);

    public abstract long ItemSideeffectCnt(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9);

    public abstract List ItemSideeffectCodeList(long l, String s, String s1, String s2, String s3, String s4, 
            String s5, String s6);

    public abstract List sideeffectCntStep(String as[][], String s, String s1, String s2, Integer integer);

    public abstract SimpleSideeffectReport itemFrequency(String s, String s1, String s2, String s3, String s4, String s5, long l, Integer integer, long l1);

    public abstract long itemLikelihoodCnt(String s, String s1, String s2, String s3, String s4, String s5);

    public abstract List itemLikelihoodList(String s, String s1, String s2, String s3, String s4, int i, int j, 
            String s5);

    public abstract List itemCatagoryMatrix(String s, String s1, String s2, String s3, long l, String s4, 
            long l1);

    public abstract List list(SimpleSideeffectReport simplesideeffectreport, int i, String s, String s1, String s2, String s3);

    public abstract List excelList(SimpleSideeffectReport simplesideeffectreport, List list1, List list2, List list3, int i, String s, String s1, 
            String s2, String s3, String s4, int j, int k, int l, int i1, 
            String s5, int j1, String s6, String s7);

    public abstract List getMatrix(String s, String s1, String s2, String s3, long l, long l1, Long long1);

    public abstract List getItemList(String s, String s1, String s2, String s3, long l, long l1, List list1, Long long1);

    public abstract List selectByItemId(String s, String s1, String s2, String s3, long l, long l1, Long long1, List list1);
}

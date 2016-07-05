// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideeffectReportDAO.java

package report;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import properties.*;
import sideeffect.*;
import system.io.SimpleAttachment;

// Referenced classes of package report:
//            SideeffectReportDAO, SimpleSideeffectReport, Report, SideeffectReportVO

public class SimpleSideeffectReportDAO
    implements SideeffectReportDAO
{

    public SimpleSideeffectReportDAO()
    {
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public List list(SimpleSideeffectReport report)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.addOrder(Order.desc("first_modified"));
        c.addOrder(Order.desc("id"));
        c.setMaxResults(100);
        c.setFirstResult(0);
        List l = c.list();
        return l;
    }

    public List list(SimpleGender simpleGender)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleGender.class);
        List l = c.list();
        return l;
    }

    public List list(SimpleSideeffectResult simpleSideeffectResult)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectResult.class);
        List l = c.list();
        return l;
    }

    public List list(SimpleSideeffectCause simpleSideeffectCause)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectCause.class);
        List l = c.list();
        return l;
    }

    public int add(SimpleSideeffectReport simpleSideeffectReport)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Integer)s.save(simpleSideeffectReport)).intValue();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reportTypeDateAdd(SimpleSideEffectReportTypeDate simpleSideEffectReportTypeDate)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(simpleSideEffectReportTypeDate)).longValue();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reportTypesAdd(JuncSideEffectReportTypes2 juncSideEffectReportTypes2)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectReportTypes2)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reporterTypesAdd(JuncSideEffectReportTypes juncSideEffectReportTypes)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectReportTypes)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long patientCodeAdd(JuncSideEffectPatientCondition juncSideEffectPatientCondition)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectPatientCondition)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long medicalCodeAdd(JuncSideEffectMedicalCode juncSideEffectMedicalCode)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectMedicalCode)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long componentCodeAdd(JuncSideEffectComponentCode juncSideEffectComponentCode)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectComponentCode)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long sideEffectResultAdd(JuncSideEffectResult juncSideEffectResult)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectResult)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long sideEffectCauseAdd(JuncSideEffectCause juncSideEffectCause)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSideEffectCause)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public Report read(int id)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSideeffectReport.class);
        c.add(Restrictions.eq("id", Integer.valueOf(id)));
        c.createCriteria("attachments", JoinType.LEFT_OUTER_JOIN);
        Report sr = (Report)c.uniqueResult();
        return sr;
    }

    public int update(SimpleSideeffectReport report)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.update(report);
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            SimpleSideeffectReport report = new SimpleSideeffectReport();
            report.setId(id);
            s.delete(report);
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int reportTypeDateDelete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE sideeffect.SimpleSideEffectReportTypeDate WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int fileDelete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            SimpleAttachment attachment = new SimpleAttachment();
            attachment.setId(id);
            s.delete(attachment);
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int juncReportAttachmentDelete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = Integer.valueOf(s.createQuery("DELETE FROM system.io.JuncRepoerAttachment WHERE ATTACHMENT_ID = ? ").setInteger(0, id).executeUpdate()).intValue();
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List list(SimpleSideeffectReport report, int pg, int limit)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.setMaxResults(limit);
        c.setFirstResult(limit - 10);
        List l = c.list();
        return l;
    }

    public List list(SimpleSideeffectReport report, String sv)
    {
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SIDE_EFFECCT_REPORT this_ ");
        if(sv.length() > 0)
        {
            str.append((new StringBuilder(" WHERE this_.CAUSALITY_ID like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.COMPANY_NAME like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.COMPONENT_CODE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.COUNTRY_MANUFACTURED_ID like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.COUNTRY_REPORTED_ID like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.EMAIL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.EXTA_INFO like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.FAX like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.FOLLOWUP like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MANAGER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MANUFACTURER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MEB_ITEM_ID like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MREPORT_DATE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANIZATION like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANIZATION_ADDRESS like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANIZATION_TEL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_AGE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_CONDITION_ID like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_EXTRA_INFO like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_GENDER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_NAME like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_ADDRESS like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_DATE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_STATUS like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_TEL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_TYPE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_YYMM like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPRESENTATIVES like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.SIDE_EFFECT_FIRST_DATE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.SIDE_EFFECT_GENERATION_DATE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.SIDE_EFFECT_LAST_DATE like '%")).append(sv).append("%' ").toString());
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSideeffectReport.class);
        List l = query.list();
        return l;
    }

    public List searchList(SimpleSideeffectReport report, int pg, int limit, HashSet hs)
    {
        Iterator itr = hs.iterator();
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SIDE_EFFECCT_REPORT this_ WHERE this_.id = 0 ");
        for(; itr.hasNext(); str.append((new StringBuilder(" or this_.id = ")).append(itr.next()).toString()));
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSideeffectReport.class);
        List l = query.list();
        return l;
    }

    public List searchList(SimpleSideeffectReport report, HashSet hs, String sv)
    {
        Iterator itr = hs.iterator();
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SIDE_EFFECCT_REPORT this_ WHERE this_.id = 0 ");
        for(; itr.hasNext(); str.append((new StringBuilder(" or this_.id = ")).append(itr.next()).toString()));
        if(sv.length() > 0)
        {
            str.append((new StringBuilder(" or this_.COMPANY_NAME like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPRESENTATIVES like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MANAGER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_TEL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.FAX like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.EMAIL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORT_ADDRESS like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MANUFACTURER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_NAME like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_AGE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_EXTRA_INFO like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.FOLLOWUP like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANIZATION like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANIZATION_TEL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANIZATION_ADDRESS like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.EXTA_INFO like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.DOCUMENT_NUMBER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPORTER_ETC like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.CAUSE_ETC like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.RESULT_ETC like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.SERIAL_NUMBER like '%")).append(sv).append("%' ").toString());
        }
        str.append("order by first_modified desc");
        str.append(" , id desc");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSideeffectReport.class);
        List l = query.list();
        return l;
    }

    public long count(SimpleSideeffectReport report)
    {
        long result = 0L;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.setProjection(Projections.count("id"));
        result = ((Long)c.uniqueResult()).longValue();
        return result;
    }

    public List countryReportedInSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("gubun").add(Restrictions.like("propertyValue", sv));
        List l = c.list();
        return l;
    }

    public List reportTypeSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("reportType").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List reporterTypeSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("reporterTypes").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List mebItemSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("meb_item").add(Restrictions.or(Restrictions.like("manuf_import_name", (new StringBuilder("%")).append(sv).append("%").toString()), Restrictions.or(Restrictions.like("meddev_item_seq", Integer.valueOf(isNumber(sv))), Restrictions.or(new Criterion[] {
            Restrictions.like("mea_class_no", (new StringBuilder("%")).append(sv).append("%").toString())
        }))));
        List l = c.list();
        return l;
    }

    public List patientSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Query c = session.createSQLQuery((new StringBuilder(" SELECT * FROM SIDE_EFFECCT_REPORT this_  inner join  gender simplegend1_ on this_.patient_gender=simplegend1_.id where  this_.patient_age=")).append(isNumber(sv)).append(" or  this_.patient_name like '%").append(sv).append("%' or simplegend1_.property_value like '%").append(sv).append("%'").toString()).addEntity(SimpleSideeffectReport.class);
        List l = c.list();
        return l;
    }

    public List sideeffectResultSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("sideeffectResult").add(Restrictions.like("property_value", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List sideeffectCauseSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("sideeffectCause").add(Restrictions.like("property_value", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List causalitySearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("causality").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List reportStatusSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("reportStatus").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public int isNumber(String str)
    {
        int result = -1;
        boolean isnumber = false;
        if(str.equals(""))
            str = "-1";
        isnumber = StringUtils.isNumeric(str);
        if(isnumber)
            result = Integer.parseInt(str);
        else
            result = -1;
        return result;
    }

    public List sideeffectCnt(String colName, Integer code, String fmDate, String toDate, String reportType, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class).createCriteria("sideEffectReportTypeDate").createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(SELECT COUNT(a.ID) FROM SIDE_EFFECCT_REPORT a JOIN SIDE_EFFECT_REPORT_TYPE_DATE b  ON (a.ID = b.REPORT_ID) JOIN JUNC_SIDEEFFECT_REPORT_TYPE reportType ON (a.ID = reportType.REPORT_ID AND reportType.REPORT_TYPE_ID IN (")).append(reporterTypes).append("))").append(" WHERE ").append(colName).append(" = ").append(code).append(" AND b.REPORT_YEAR = date2_.REPORT_YEAR and b.REPORT_MONTH = date2_.REPORT_MONTH AND b.REPORT_TYPE_ID = ").append(reportType).append(" ) as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List sideeffectCnt(String colName, Integer code, String fmDate, String toDate, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class).createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(SELECT COUNT(SIDE_EFFECCT_REPORT.ID) FROM SIDE_EFFECCT_REPORT  JOIN JUNC_SIDEEFFECT_REPORT_TYPE reportType ON (SIDE_EFFECCT_REPORT.ID = reportType.REPORT_ID AND reportType.REPORT_TYPE_ID IN (")).append(reporterTypes).append(")) ").append(" WHERE ").append(colName).append(" = ").append(code).append(" AND REPORT_YEAR = date1_.REPORT_YEAR and REPORT_MONTH = date1_.REPORT_MONTH ) as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List sideeffectCnt(String tabName, String JoinColName, String ColName, String code, String fmDate, String toDate, String reportType, 
            String TreeYN, String reporterTypes)
    {
        List l = null;
        String eqOrIn = "= ";
        if(TreeYN.equals("Y"))
            eqOrIn = " in ";
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class).createCriteria("sideEffectReportTypeDate").createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(select count(*) from ")).append(tabName).append(" ").append(" join SIDE_EFFECCT_REPORT b on ").append(JoinColName).append(" join SIDE_EFFECT_REPORT_TYPE_DATE c on (b.id = c.report_id)").append(" JOIN JUNC_SIDEEFFECT_REPORT_TYPE reportType ON (b.ID = reportType.REPORT_ID AND reportType.REPORT_TYPE_ID IN (").append(reporterTypes).append("))").append(" where ").append(ColName).append(eqOrIn).append(" (").append(code).append(") and  c.report_year = date2_.report_year and c.report_month = date2_.report_month and c.report_type_id = ").append(reportType).append(") as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List sideeffectCnt(String tabName, String JoinColName, String ColName, String code, String fmDate, String toDate, String TreeYN, 
            String reporterTypes)
    {
        List l = null;
        String eqOrIn = " = ";
        if(TreeYN.equals("Y"))
            eqOrIn = " in ";
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class).createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(select count(*) from ")).append(tabName).append(" ").append(" join SIDE_EFFECCT_REPORT b  on ").append(JoinColName).append(" JOIN JUNC_SIDEEFFECT_REPORT_TYPE reportType ON (b.ID = reportType.REPORT_ID AND reportType.REPORT_TYPE_ID IN (").append(reporterTypes).append("))").append(" where ").append(ColName).append(eqOrIn).append(" (").append(code).append(") and  b.report_year = date1_.report_year and b.report_month = date1_.report_month ) as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List list(SimpleSideeffectReport report, int sc, String year1, String year2, String month1, String month2)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        if(sc == 0)
        {
            c.add(Restrictions.between("report_year", year1, year2));
            c.add(Restrictions.between("report_month", month1, month2));
        }
        if(sc != 0)
            c.createCriteria("sideEffectReportTypeDate", "sideEffectReportTypeDate_", JoinType.INNER_JOIN).add(Restrictions.between("sideEffectReportTypeDate_.report_year", year1, year2)).add(Restrictions.between("sideEffectReportTypeDate_.report_month", month1, month2)).add(Restrictions.eq("report_type_id", Integer.valueOf(sc)));
        c.addOrder(Order.desc("first_modified"));
        c.addOrder(Order.desc("id"));
        List l = c.list();
        return l;
    }

    public BigDecimal countReportList(SimpleSideeffectReport report, List sc, List sv, List logicalOperator, List arrayIdOrName, int intSc, String year1, 
            String year2, String month1, String month2, String PopYN, int pgSize)
    {
        Session session = sessionFactory.getCurrentSession();
        StringBuffer str = new StringBuffer();
        str.append("SELECT COUNT(this_.ID) FROM sideeffect.SIDE_EFFECCT_REPORT_VIEW SERV    ");
        str.append(" INNER JOIN sideeffect.SIDE_EFFECCT_REPORT this_  ");
        str.append(" ON SERV.ID = this_.id WHERE 1=1 AND ");
        String strLogicalOperator = "";
        for(int i = 0; i < sc.size(); i++)
        {
            if(i == sc.size() - 1)
                strLogicalOperator = "AND";
            else
                strLogicalOperator = logicalOperator.get(i).toString();
            if(sc.get(i).equals("1"))
            {
                str.append("  this_.COUNTRY_REPORTED_ID =   \n ");
                str.append("  (SELECT cri.id FROM COUNTRY_REPORTED_IN cri \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE cri.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM JUNC_SIDE_EFFECT_REPORT_TYPES2 jsert2 \n ");
                str.append("   JOIN REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE RT.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.REPORT_ID FROM JUNC_SIDEEFFECT_REPORT_TYPE jsrt \n ");
                str.append("   JOIN REPORTER_TYPE RT ON jsrt.REPORT_TYPE_ID = RT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE RT.ID IN ( ")).append(sv.get(i)).append(") ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVES LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("6"))
                str.append((new StringBuilder("   this_.MANAGER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("7"))
                str.append((new StringBuilder("   this_.REPORT_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("8"))
                str.append((new StringBuilder("   this_.FAX LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("9"))
                str.append((new StringBuilder("   UPPER(this_.EMAIL) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("10"))
                str.append((new StringBuilder("   UPPER(this_.REPORT_ADDRESS) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("11"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   JOIN sideeffect.SGI_SE_COMPANY MC ON MI.COMPANY_ID = MC.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MC.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(MC.ENTP_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.MEB_ITEM_ID in  \n ");
                str.append("  (SELECT MI.id FROM sideeffect.SGI_SE_COMPANY MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE mi.ID =")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE mi.MEDDEV_ENTP_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   JOIN sgi_se_item_category ssic ON MI.item_category_number = ssic.id  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE ssic.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.CLASS_KOR_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("14"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEA_CLASS_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("15"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   join sgi_se_item_category ssic on MI.item_category_number = ssic.id \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE ssic.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.GRADE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("16"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEDDEV_ITEM_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("17"))
            {
                str.append("  this_.COUNTRY_MANUFACTURED_ID IN   \n ");
                str.append("  (SELECT CC.ID FROM COUNTRY_CODE CC \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE CC.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(CC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("18"))
                str.append((new StringBuilder("   UPPER(this_.MANUFACTURER) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("20"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   join SIDE_EFFECT_REPORT_MEB_TYPE_INFO sermti ON MI.id = sermti.report_id \n ");
                str.append("   join SGI_SE_ITEM_TYPE_INFO ssiti on sermti.meddev_item_seq = ssiti.item_id \n ");
                str.append((new StringBuilder("   WHERE UPPER(ssiti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
                str.append((new StringBuilder("   this_.SERIAL_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.PATIENT_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
                str.append((new StringBuilder("   this_.PATIENT_GENDER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("24"))
                str.append((new StringBuilder("   this_.PATIENT_AGE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("25"))
                str.append((new StringBuilder("   UPPER(this_.PATIENT_EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("26"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSR.REPORT_ID FROM JUNC_SIDEEFFECT_RESULT JSR \n ");
                str.append("   JOIN SIDEEFFECT_RESULT SR ON JSR.RESULT_ID = SR.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE SR.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SR.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSC.REPORT_ID FROM JUNC_SIDEEFFECT_CAUSE JSC \n ");
                str.append("   JOIN SIDEEFFECT_CAUSE SC ON JSC.CAUSE_ID = SC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE SC.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM JUNC_SIDE_EFFECT_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN PATIENT_CONDITION PC ON JSEPC.PATIENT_ID = PC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                {
                    str.append((new StringBuilder("   WHERE PC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") ) ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(PC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("29"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM JUNC_SIDE_EFFECT_MEDICAL_CODE JSEMC \n ");
                str.append("   JOIN MEDICAL_DEVICE_MALFUNCTION_CODE MDMC ON JSEMC.MEDICAL_ID = MDMC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                {
                    str.append((new StringBuilder("   WHERE MDMC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(MDMC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("30"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM JUNC_SIDE_EFFECT_COMPONENT_CODE JSEC \n ");
                str.append("   JOIN COMPONENT_CODE CC ON JSEC.COMPONENT_ID = CC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                {
                    str.append((new StringBuilder("   WHERE CC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("   \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(CC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("31"))
            {
                str.append("  this_.CAUSALITY_ID IN   \n ");
                str.append("  (SELECT C.ID FROM CAUSALITY C \n ");
                str.append((new StringBuilder("   WHERE UPPER(C.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("32"))
                str.append((new StringBuilder("   UPPER(this_.ORGANIZATION) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("33"))
                str.append((new StringBuilder("   this_.ORGANIZATION_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("34"))
                str.append((new StringBuilder("   UPPER(this_.EXTA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("35"))
            {
                str.append("   this_.CAUSALITY_ID = (select c.id from CAUSALITY c \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   where c.ID = ")).append(sv.get(i).toString()).append(" )  ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   where c.PROPERTY_VALUE like '%")).append(sv.get(i).toString()).append("%' )  ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("36"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER = ")).append(sv.get(i).toString()).append("  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("37"))
                str.append((new StringBuilder(" SERV.PRODUCT_COB_CODE LIKE '%")).append(sv.get(i).toString()).append("%'   ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        System.out.println((new StringBuilder("[SimpleSideffectReportDAO].countReportList.intSc  ")).append(intSc).toString());
        if(intSc == 0)
            str.append((new StringBuilder(" this_.REPORT_YEAR || this_.REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM SIDE_EFFECT_REPORT_TYPE_DATE jsertd \n ");
            str.append(" where this_.id = jsertd.report_id \n ");
            str.append((new StringBuilder(" and jsertd.report_type_id =")).append(intSc).append(" \n ").toString());
            str.append((new StringBuilder(" and jsertd.report_year || jsertd.report_month between '")).append(year1).append(month1).append("' and '").append(year2).append(month2).append("') \n ").toString());
        }
        if(intSc == 4)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year2), Integer.parseInt(month2), 1);
            String lastDay = String.valueOf(calendar.getActualMaximum(5));
            str.append((new StringBuilder(" this_.first_modified between '")).append(year1).append("-").append(month1).append("-01'").append(" and '").append(year2).append("-").append(month2).append("-").append(lastDay).append("'").toString());
        }
        Query query = session.createSQLQuery(str.toString());
        BigDecimal result = null;
        result = (BigDecimal)query.uniqueResult();
        return result;
    }

    public List details_search(SimpleSideeffectReport report, List sc, List sv, List logicalOperator, int intSc, String year1, String year2, 
            String month1, String month2, String PopYN, int pgSize, int firstResult)
    {
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SIDE_EFFECCT_REPORT this_  WHERE 1=1 AND ");
        String strLogicalOperator = "";
        for(int i = 0; i < sc.size(); i++)
        {
            if(i == sc.size() - 1)
                strLogicalOperator = "AND";
            else
                strLogicalOperator = logicalOperator.get(i).toString();
            if(sc.get(i).equals("1"))
            {
                str.append("  this_.COUNTRY_REPORTED_ID =   \n ");
                str.append("  (SELECT cri.id FROM COUNTRY_REPORTED_IN cri \n ");
                str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM JUNC_SIDE_EFFECT_REPORT_TYPES2 jsert2 \n ");
                str.append("   JOIN REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.REPORT_ID FROM JUNC_SIDEEFFECT_REPORT_TYPE jsrt \n ");
                str.append("   JOIN REPORTER_TYPE RT ON jsrt.REPORT_TYPE_ID = RT.ID  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE RT.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVES LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("6"))
                str.append((new StringBuilder("   this_.MANAGER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("7"))
                str.append((new StringBuilder("   this_.REPORT_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("8"))
                str.append((new StringBuilder("   this_.FAX LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("9"))
                str.append((new StringBuilder("   UPPER(this_.EMAIL) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("10"))
                str.append((new StringBuilder("   UPPER(this_.REPORT_ADDRESS) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("11"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   JOIN SGI_SE_COMPANY MC ON MI.id = MC.MEDDEV_ENTP_SEQ  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE MC.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(MC.ENTP_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.MEB_ITEM_ID in  \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE mi.id LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   JOIN sgi_se_item_category ssic ON MI.item_category_number = ssic.id  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE ssic.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.CLASS_KOR_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("14"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE MI.MEA_CLASS_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("15"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   join sgi_se_item_category ssic on MI.item_category_number = ssic.id \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE ssic.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.GRADE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("16"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                if(PopYN.equals("Y") && i == 1)
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEDDEV_ITEM_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("17"))
            {
                str.append("  this_.COUNTRY_MANUFACTURED_ID IN   \n ");
                str.append("  (SELECT CC.ID FROM COUNTRY_CODE CC \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE CC.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(CC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("18"))
                str.append((new StringBuilder("   UPPER(this_.MANUFACTURER) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("20"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   join SIDE_EFFECT_REPORT_MEB_TYPE_INFO sermti ON MI.id = sermti.report_id \n ");
                str.append("   join SGI_SE_ITEM_TYPE_INFO ssiti on sermti.meddev_item_seq = ssiti.item_id \n ");
                str.append((new StringBuilder("   WHERE UPPER(ssiti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
                str.append((new StringBuilder("   this_.SERIAL_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.PATIENT_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
                str.append((new StringBuilder("   this_.PATIENT_GENDER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("24"))
                str.append((new StringBuilder("   this_.PATIENT_AGE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("25"))
                str.append((new StringBuilder("   UPPER(this_.PATIENT_EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("26"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSR.REPORT_ID FROM JUNC_SIDEEFFECT_RESULT JSR \n ");
                str.append("   JOIN SIDEEFFECT_RESULT SR ON JSR.RESULT_ID = SR.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE SR.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SR.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSC.REPORT_ID FROM JUNC_SIDEEFFECT_CAUSE JSC \n ");
                str.append("   JOIN SIDEEFFECT_CAUSE SC ON JSC.CAUSE_ID = SC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE SC.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM JUNC_SIDE_EFFECT_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN PATIENT_CONDITION PC ON JSEPC.PATIENT_ID = PC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                {
                    str.append((new StringBuilder("   WHERE PC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") ) ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(PC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("29"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM JUNC_SIDE_EFFECT_MEDICAL_CODE JSEMC \n ");
                str.append("   JOIN MEDICAL_DEVICE_MALFUNCTION_CODE MDMC ON JSEMC.MEDICAL_ID = MDMC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                {
                    str.append((new StringBuilder("   WHERE MDMC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(MDMC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("30"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM JUNC_SIDE_EFFECT_COMPONENT_CODE JSEC \n ");
                str.append("   JOIN COMPONENT_CODE CC ON JSEC.COMPONENT_ID = CC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                {
                    str.append((new StringBuilder("   WHERE CC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("   \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(CC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("31"))
            {
                str.append("  this_.CAUSALITY_ID IN   \n ");
                str.append("  (SELECT C.ID FROM CAUSALITY C \n ");
                str.append((new StringBuilder("   WHERE UPPER(C.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("32"))
                str.append((new StringBuilder("   UPPER(this_.ORGANIZATION) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("33"))
                str.append((new StringBuilder("   this_.ORGANIZATION_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("34"))
                str.append((new StringBuilder("   UPPER(this_.EXTA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("35"))
            {
                str.append("   this_.CAUSALITY_ID = (select c.id from CAUSALITY c \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   where c.ID = ")).append(sv.get(i).toString()).append(" )  ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   where c.PROPERTY_VALUE like '%")).append(sv.get(i).toString()).append("%' )  ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("36"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER = ")).append(sv.get(i).toString()).append("  ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        if(intSc == 0)
            str.append((new StringBuilder(" this_.REPORT_YEAR || this_.REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM SIDE_EFFECT_REPORT_TYPE_DATE jsertd \n ");
            str.append(" where this_.id = jsertd.report_id \n ");
            str.append((new StringBuilder(" and jsertd.report_type_id =")).append(intSc).append(" \n ").toString());
            str.append((new StringBuilder(" and jsertd.report_year || jsertd.report_month between '")).append(year1).append(month1).append("' and '").append(year2).append(month2).append("' ) \n ").toString());
        }
        if(intSc == 4)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year2), Integer.parseInt(month2), 1);
            String lastDay = String.valueOf(calendar.getActualMaximum(5));
            str.append((new StringBuilder(" this_.first_modified between '")).append(year1).append("-").append(month1).append("-01'").append(" and '").append(year2).append("-").append(month2).append("-").append(lastDay).append("'").toString());
        }
        str.append(" order by this_.first_modified desc");
        str.append(" , this_.id desc");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSideeffectReport.class);
        if(pgSize > 0)
            query.setFirstResult(firstResult).setMaxResults(pgSize);
        List l = query.list();
        return l;
    }

    public String createDetailsSearch2(SideeffectReportVO report, List sc, List sv, List logicalOperator, List arrayIdOrName, int intSc, String year1, 
            String year2, String month1, String month2, String PopYN, int pgSize, int firstResult, int rowCount, 
            int rowMaxValue, int pg, String orderText, String order1, String paging, String queryType)
    {
        String subquery = "";
        int first_result = 0;
        int max_results = 0;
        if(pg == 1)
        {
            first_result = 1;
            max_results = 50;
        } else
        {
            first_result = (pg * 50 - 50) + 1;
            max_results = (first_result + 50) - 1;
        }
        String orderText2 = "";
        String orderText3 = "";
        if(orderText.equals("product_cob_code") || orderText.equals("product_value"))
        {
            orderText = "product_value";
            orderText2 = "A.meddev_item_no1";
            orderText3 = "A.meddev_item_no2";
        }
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].pgSize  ")).append(pgSize).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].max_results  ")).append(max_results).toString());
        if(max_results <= 1)
            max_results = pgSize;
        StringBuffer str = new StringBuffer();
        if(queryType.equals("checkDuplicate"))
        {
            str.append("SELECT count(*) \n");
            str.append("FROM ( \n");
            str.append("SELECT count(document_number) as numberDuplicate ");
            str.append(" FROM ( ");
        } else
        {
            str.append("SELECT * FROM ( ");
        }
        str.append("SELECT * FROM ( ");
        if(!orderText.equals("") || orderText.length() > 0)
        {
            if(orderText.equals("product_cob_code") || orderText.equals("product_value"))
                str.append((new StringBuilder("SELECT  ROW_NUMBER() OVER(ORDER BY A.")).append(orderText).append(" ").append(order1).append(", ").append(orderText2).append(" ").append(order1).append(", ").append(orderText3).append(" ").append(order1).append(") RANK1, A.*, A.RNUM1 AS RNUM2 FROM ( ").toString());
            else
                str.append((new StringBuilder("SELECT  ROW_NUMBER() OVER(ORDER BY A.")).append(orderText).append(" ").append(order1).append(") RANK1, A.*, A.RNUM1 AS RNUM2 FROM ( ").toString());
        } else
        {
            str.append("SELECT  ROW_NUMBER() OVER(ORDER BY A.first_modified DESC) RANK1, A.*, A.RNUM1 AS RNUM2 FROM ( ");
        }
        str.append("SELECT SERV.*, ROWNUM AS RNUM1 FROM sideeffect.SIDE_EFFECCT_REPORT_VIEW SERV    ");
        str.append(" INNER JOIN sideeffect.SIDE_EFFECCT_REPORT this_  ");
        str.append(" ON SERV.ID = this_.id WHERE 1=1 AND  ");
        String strLogicalOperator = "AND";
        for(int i = 0; i < sc.size(); i++)
        {
            if(i == sc.size() - 1)
                strLogicalOperator = "AND";
            else
                strLogicalOperator = logicalOperator.get(i).toString();
            if(sc.get(i).equals("1"))
            {
                str.append("  this_.COUNTRY_REPORTED_ID =   \n ");
                str.append("  (SELECT cri.id FROM COUNTRY_REPORTED_IN cri \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE cri.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM JUNC_SIDE_EFFECT_REPORT_TYPES2 jsert2 \n ");
                str.append("   JOIN REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE RT.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.REPORT_ID FROM JUNC_SIDEEFFECT_REPORT_TYPE jsrt \n ");
                str.append("   JOIN REPORTER_TYPE RT ON jsrt.REPORT_TYPE_ID = RT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE RT.ID IN (")).append(sv.get(i)).append(") ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVES LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("6"))
                str.append((new StringBuilder("   this_.MANAGER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("7"))
                str.append((new StringBuilder("   this_.REPORT_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("8"))
                str.append((new StringBuilder("   this_.FAX LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("9"))
                str.append((new StringBuilder("   UPPER(this_.EMAIL) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("10"))
                str.append((new StringBuilder("   UPPER(this_.REPORT_ADDRESS) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("11"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   JOIN sideeffect.SGI_SE_COMPANY MC ON MI.COMPANY_ID = MC.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MC.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(MC.ENTP_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.MEB_ITEM_ID in  \n ");
                str.append("  (SELECT MI.id FROM sideeffect.SGI_SE_COMPANY MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE mi.ID =")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE mi.MEDDEV_ENTP_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   JOIN sgi_se_item_category ssic ON MI.item_category_number = ssic.id  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE ssic.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.CLASS_KOR_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("14"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEA_CLASS_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("15"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   join sgi_se_item_category ssic on MI.item_category_number = ssic.id \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE ssic.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.GRADE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("16"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEDDEV_ITEM_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("17"))
            {
                str.append("  this_.COUNTRY_MANUFACTURED_ID IN   \n ");
                str.append("  (SELECT CC.ID FROM COUNTRY_CODE CC \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE CC.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(CC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("18"))
                str.append((new StringBuilder("   UPPER(this_.MANUFACTURER) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("20"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sgi_se_item MI \n ");
                str.append("   join SIDE_EFFECT_REPORT_MEB_TYPE_INFO sermti ON MI.id = sermti.report_id \n ");
                str.append("   join SGI_SE_ITEM_TYPE_INFO ssiti on sermti.meddev_item_seq = ssiti.item_id \n ");
                str.append((new StringBuilder("   WHERE UPPER(ssiti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
                str.append((new StringBuilder("   this_.SERIAL_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.PATIENT_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
                str.append((new StringBuilder("   this_.PATIENT_GENDER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("24"))
                str.append((new StringBuilder("   this_.PATIENT_AGE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("25"))
                str.append((new StringBuilder("   UPPER(this_.PATIENT_EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("26"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSR.REPORT_ID FROM JUNC_SIDEEFFECT_RESULT JSR \n ");
                str.append("   JOIN SIDEEFFECT_RESULT SR ON JSR.RESULT_ID = SR.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE SR.ID = ")).append(sv.get(i).toString().toUpperCase()).append(subquery).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SR.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ").append(subquery).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSC.REPORT_ID FROM JUNC_SIDEEFFECT_CAUSE JSC \n ");
                str.append("   JOIN SIDEEFFECT_CAUSE SC ON JSC.CAUSE_ID = SC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE SC.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM JUNC_SIDE_EFFECT_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN PATIENT_CONDITION PC ON JSEPC.PATIENT_ID = PC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                {
                    str.append((new StringBuilder("   WHERE PC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") ) ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(PC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("29"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM JUNC_SIDE_EFFECT_MEDICAL_CODE JSEMC \n ");
                str.append("   JOIN MEDICAL_DEVICE_MALFUNCTION_CODE MDMC ON JSEMC.MEDICAL_ID = MDMC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                {
                    str.append((new StringBuilder("   WHERE MDMC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(MDMC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("30"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM JUNC_SIDE_EFFECT_COMPONENT_CODE JSEC \n ");
                str.append("   JOIN COMPONENT_CODE CC ON JSEC.COMPONENT_ID = CC.ID \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                {
                    str.append((new StringBuilder("   WHERE CC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("   \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(CC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("31"))
            {
                str.append("  this_.CAUSALITY_ID IN   \n ");
                str.append("  (SELECT C.ID FROM CAUSALITY C \n ");
                str.append((new StringBuilder("   WHERE UPPER(C.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("32"))
                str.append((new StringBuilder("   UPPER(this_.ORGANIZATION) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("33"))
                str.append((new StringBuilder("   this_.ORGANIZATION_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("34"))
                str.append((new StringBuilder("   UPPER(this_.EXTA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("35"))
            {
                str.append("   this_.CAUSALITY_ID = (select c.id from CAUSALITY c \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   where c.ID = ")).append(sv.get(i).toString()).append(" )  ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   where c.PROPERTY_VALUE like '%")).append(sv.get(i).toString()).append("%' )  ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("36"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER = ")).append(sv.get(i).toString()).append("  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("37"))
                str.append((new StringBuilder(" SERV.PRODUCT_COB_CODE LIKE '%")).append(sv.get(i).toString()).append("%'   ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        if(intSc == 0)
            str.append((new StringBuilder(" this_.REPORT_YEAR || this_.REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM sideeffect.SIDE_EFFECT_REPORT_TYPE_DATE jsertd \n ");
            str.append(" where this_.id = jsertd.report_id \n ");
            str.append((new StringBuilder(" and jsertd.report_type_id =")).append(intSc).append(" \n ").toString());
            str.append((new StringBuilder(" and jsertd.report_year || jsertd.report_month between '")).append(year1).append(month1).append("' and '").append(year2).append(month2).append("') \n ").toString());
        }
        if(intSc == 4)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year2), Integer.parseInt(month2), 1);
            String lastDay = String.valueOf(calendar.getActualMaximum(5));
            str.append((new StringBuilder(" this_.first_modified between '")).append(year1).append("-").append(month1).append("-01'").append(" and '").append(year2).append("-").append(month2).append("-").append(lastDay).append("'").toString());
        }
        if(orderText.equals(""))
            str.append((new StringBuilder(" order by first_modified ")).append(order1).toString());
        else
            str.append((new StringBuilder(" order by ")).append(orderText).append(" ").append(order1).toString());
        str.append(" ) AS A");
        if(pgSize > 0)
        {
            if(!paging.equals("n"))
            {
                str.append(" ) ");
                if(queryType.equals("checkDuplicate"))
                    str.append(" ) ");
                else
                    str.append((new StringBuilder(" ) WHERE RANK1 >=")).append(first_result).append(" AND RANK1 <=").append(max_results).toString());
            } else
            {
                str.append(" ) ");
                str.append(" ) ");
            }
        } else
        {
            str.append(" ) ");
        }
        if(queryType.equals("checkDuplicate"))
        {
            str.append(" \n GROUP BY (document_number) \n");
            str.append("HAVING COUNT(document_number) > 1 \n");
            str.append(" ) as B \n");
        }
        return str.toString();
    }

    public List details_search2(SideeffectReportVO report, List sc, List sv, List logicalOperator, List arrayIdOrName, int intSc, String year1, 
            String year2, String month1, String month2, String PopYN, int pgSize, int firstResult, int rowCount, 
            int rowMaxValue, int pg, String orderText, String order1, String paging)
    {
        String queryType = "search";
        String str = "";
        str = createDetailsSearch2(report, sc, sv, logicalOperator, arrayIdOrName, intSc, year1, year2, month1, month2, PopYN, pgSize, firstResult, rowCount, rowMaxValue, pg, orderText, order1, paging, queryType);
        if(str == null)
            str = "";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SideeffectReportVO.class);
        List l = query.list();
        return l;
    }

    public BigDecimal countNmberDuplicate(SideeffectReportVO report, List sc, List sv, List logicalOperator, List arrayIdOrName, int intSc, String year1, 
            String year2, String month1, String month2, String PopYN, int pgSize, int firstResult, int rowCount, 
            int rowMaxValue, int pg, String orderText, String order1, String paging)
    {
        BigDecimal count = null;
        String queryType = "checkDuplicate";
        String str = "";
        str = createDetailsSearch2(report, sc, sv, logicalOperator, arrayIdOrName, intSc, year1, year2, month1, month2, PopYN, pgSize, firstResult, rowCount, rowMaxValue, pg, orderText, order1, paging, queryType);
        if(str == null)
            str = "";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString());
        count = (BigDecimal)query.uniqueResult();
        if(count == null)
            count = new BigDecimal(0);
        return count;
    }

    public long mebTypeInfoSelectAdd(SimpleSideEffectReportMebTypeInfo simpleSideEffectReportMebTypeInfo)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(simpleSideEffectReportMebTypeInfo)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List ItemSideeffectList(String classKorName, String meaClassNo, String cobFlag, String meddevItemNo, String reportType, String fmYY, String fmMM, 
            String toYY, String toMM, int pgSize, int startPg, String classKorNameEqual)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
        c.createCriteria("meb_item", "item").createCriteria("company", "company").createCriteria("item.mea_item", "item_category").createCriteria("item.cobFlagType", "cobflag").add(Restrictions.like("item_category.mea_class_no", (new StringBuilder("%")).append(meaClassNo).append("%").toString())).add(Restrictions.like("item.meddev_item_no", (new StringBuilder("%")).append(meddevItemNo).append("%").toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection("sum( (select count(com.id) from sgi_se_item se_item join side_effecct_report report on ( se_item.id = report.meb_item_id) join junc_side_effect_component_code com on (com.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt1 ", new String[] {
            "varCnt1"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt1").add(Projections.sqlProjection("sum( (select count(med.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_medical_code med on (med.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt2 ", new String[] {
            "varCnt2"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt2").add(Projections.sqlProjection("sum((select count(pat.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_patient_condition pat on (pat.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt3 ", new String[] {
            "varCnt3"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt3").add(Projections.sqlProjection("sum((select count(report.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt ", new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("sideeffect.meb_item_id").as("meb_item_id")).add(Projections.groupProperty("item_category.id").as("varId")).add(Projections.groupProperty("item_category.mea_class_no").as("varValue1")).add(Projections.groupProperty("cobflag.propertyValue").as("varValue2")).add(Projections.groupProperty("item.meddev_item_no").as("varValue3")).add(Projections.groupProperty("item_category.class_kor_name").as("varValue4")).add(Projections.groupProperty("company.entp_name").as("varValue5"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.asc("item_category.class_kor_name"));
        if(classKorNameEqual.equals("Y"))
            c.add(Restrictions.eq("item_category.class_kor_name", classKorName));
        else
            c.add(Restrictions.like("item_category.class_kor_name", (new StringBuilder("%")).append(classKorName).append("%").toString()));
        if(pgSize > 0)
            c.setFirstResult(startPg).setMaxResults(pgSize);
        if(!cobFlag.equals("-1"))
            c.add(Restrictions.eq("item.cob_flag_code", cobFlag));
        if(!reportType.equals("0"))
            c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date6_.report_year ||  date6_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date5_.report_year ||  date5_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        l = c.list();
        return l;
    }

    public long ItemSideeffectCnt(String classKorName, String meaClassNo, String cobFlag, String meddevItemNo, String reportType, String fmYY, String fmMM, 
            String toYY, String toMM, String classKorNameEqual)
    {
        long totalCnt = 0L;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("meb_item", "item").createCriteria("company", "company").createCriteria("item.mea_item", "item_category").createCriteria("item.cobFlagType", "cobflag").setProjection(Projections.countDistinct("item.id")).add(Restrictions.like("item_category.mea_class_no", (new StringBuilder("%")).append(meaClassNo).append("%").toString())).add(Restrictions.like("item.meddev_item_no", (new StringBuilder("%")).append(meddevItemNo).append("%").toString()));
        if(classKorNameEqual.equals("Y"))
            c.add(Restrictions.eq("item_category.class_kor_name", classKorName));
        else
            c.add(Restrictions.like("item_category.class_kor_name", (new StringBuilder("%")).append(classKorName).append("%").toString()));
        if(!cobFlag.equals("-1"))
            c.add(Restrictions.eq("item.cob_flag_code", cobFlag));
        if(!reportType.equals("0"))
            c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date6_.report_year ||  date6_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date5_.report_year ||  date5_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        totalCnt = ((Long)c.uniqueResult()).longValue();
        return totalCnt;
    }

    public List ItemSideeffectCodeList(long itemId, String joinTable, String reportType, String fmYY, String fmMM, String toYY, 
            String toMM, String tabGB)
    {
        List l = null;
        String btn_date = "";
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        if(tabGB.equals("I"))
        {
            c.createCriteria("meb_item", "item").add(Restrictions.eq("item.id", Long.valueOf(itemId)));
            if(!reportType.equals("0"))
                c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
            else
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        } else
        {
            c.createCriteria("meb_item", "item").createCriteria("item.mea_item", "category").add(Restrictions.eq("category.id", Long.valueOf(itemId)));
            if(!reportType.equals("0"))
                c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date4_.report_year ||  date4_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
            else
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        }
        c.createCriteria(joinTable, "code").setProjection(Projections.projectionList().add(Projections.groupProperty("code.id").as("varId")).add(Projections.groupProperty("code.fdaCode").as("varValue1")).add(Projections.groupProperty("code.fdaSourcePtKor").as("varValue2")).add(Projections.groupProperty("code.name").as("varValue3")).add(Projections.groupProperty("code.parentId")).add(Projections.groupProperty("code.depthLevel")).add(Projections.count("code.id").as("varCnt"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.asc("code.fdaSourcePtKor"));
        l = c.list();
        return l;
    }

    public List excelList(SimpleSideeffectReport report, List sc, List sv, List logicalOperator, int intSc, String year1, String year2, 
            String month1, String month2, String PopYN, int pgSize, int firstResult, int rowCount, int rowMaxValue, 
            String popGB, int pg, String orderText, String order1)
    {
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM ( ");
        str.append("SELECT A.*, A.RNUM1 AS RNUM2 FROM ( ");
        str.append("SELECT this_.*, ROWNUM AS RNUM1 FROM sideeffect.SIDE_EFFECCT_REPORT this_ WHERE 1=1 AND    ");
        String strLogicalOperator = "AND";
        for(int i = 0; i < sc.size(); i++)
        {
            if(i == sc.size() - 1)
                strLogicalOperator = "AND";
            else
                strLogicalOperator = logicalOperator.get(i).toString();
            if(sc.get(i).equals("1"))
            {
                str.append("  this_.COUNTRY_REPORTED_ID =   \n ");
                str.append("  (SELECT cri.id FROM sideeffect.COUNTRY_REPORTED_IN cri \n ");
                str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM sideeffect.JUNC_SIDE_EFFECT_REPORT_TYPES2 jsert2 \n ");
                str.append("   JOIN sideeffect.REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.REPORT_ID FROM sideeffect.JUNC_SIDEEFFECT_REPORT_TYPE jsrt \n ");
                str.append("   JOIN sideeffect.REPORTER_TYPE RT ON jsrt.REPORT_TYPE_ID = RT.ID  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE RT.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVES LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("6"))
                str.append((new StringBuilder("   this_.MANAGER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("7"))
                str.append((new StringBuilder("   this_.REPORT_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("8"))
                str.append((new StringBuilder("   this_.FAX LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("9"))
                str.append((new StringBuilder("   UPPER(this_.EMAIL) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("10"))
                str.append((new StringBuilder("   UPPER(this_.REPORT_ADDRESS) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("11"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   JOIN sideeffect.SGI_SE_COMPANY MC ON MI.id = MC.MEDDEV_ENTP_SEQ  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE MC.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(MC.ENTP_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.MEB_ITEM_ID in  \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE mi.id LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   JOIN sideeffect.sgi_se_item_category ssic ON MI.item_category_number = ssic.id  \n ");
                if(PopYN.equals("Y") && i == 0 && !popGB.equals("C"))
                    str.append((new StringBuilder("   WHERE ssic.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                if(PopYN.equals("Y") && i == 1 && popGB.equals("C"))
                    str.append((new StringBuilder("   WHERE ssic.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.CLASS_KOR_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("14"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE MI.MEA_CLASS_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("15"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   join sideeffect.sgi_se_item_category ssic on MI.item_category_number = ssic.id \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE ssic.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.GRADE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("16"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                if(PopYN.equals("Y") && i == 1)
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEDDEV_ITEM_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("17"))
            {
                str.append("  this_.COUNTRY_MANUFACTURED_ID IN   \n ");
                str.append("  (SELECT CC.ID FROM sideeffect.COUNTRY_CODE CC \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE CC.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(CC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("18"))
                str.append((new StringBuilder("   UPPER(this_.MANUFACTURER) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("20"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   join sideeffect.SIDE_EFFECT_REPORT_MEB_TYPE_INFO sermti ON MI.id = sermti.report_id \n ");
                str.append("   join sideeffect.SGI_SE_ITEM_TYPE_INFO ssiti on sermti.meddev_item_seq = ssiti.item_id \n ");
                str.append((new StringBuilder("   WHERE UPPER(ssiti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
                str.append((new StringBuilder("   this_.SERIAL_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.PATIENT_NAME LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
                str.append((new StringBuilder("   this_.PATIENT_GENDER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("24"))
                str.append((new StringBuilder("   this_.PATIENT_AGE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("25"))
                str.append((new StringBuilder("   UPPER(this_.PATIENT_EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("26"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSR.REPORT_ID FROM sideeffect.JUNC_SIDEEFFECT_RESULT JSR \n ");
                str.append("   JOIN sideeffect.SIDEEFFECT_RESULT SR ON JSR.RESULT_ID = SR.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE SR.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SR.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSC.REPORT_ID FROM sideeffect.JUNC_SIDEEFFECT_CAUSE JSC \n ");
                str.append("   JOIN sideeffect.SIDEEFFECT_CAUSE SC ON JSC.CAUSE_ID = SC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE SC.ID = ")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(SC.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM sideeffect.JUNC_SIDE_EFFECT_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN sideeffect.PATIENT_CONDITION PC ON JSEPC.PATIENT_ID = PC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                {
                    str.append((new StringBuilder("   WHERE PC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") ) ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(PC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(PC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("29"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM sideeffect.JUNC_SIDE_EFFECT_MEDICAL_CODE JSEMC \n ");
                str.append("   JOIN sideeffect.MEDICAL_DEVICE_MALFUNCTION_CODE MDMC ON JSEMC.MEDICAL_ID = MDMC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                {
                    str.append((new StringBuilder("   WHERE MDMC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("  \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(MDMC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(MDMC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("30"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM sideeffect.JUNC_SIDE_EFFECT_COMPONENT_CODE JSEC \n ");
                str.append("   JOIN sideeffect.COMPONENT_CODE CC ON JSEC.COMPONENT_ID = CC.ID \n ");
                if(PopYN.equals("Y") && i == 0)
                {
                    str.append((new StringBuilder("   WHERE CC.ID IN (")).append(sv.get(i).toString().toUpperCase()).append(") )  ").append(strLogicalOperator).append("   \n ").toString());
                } else
                {
                    str.append((new StringBuilder("   WHERE UPPER(CC.FDA_CODE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.FDA_SOURCE_PT_KOR) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' \n ").toString());
                    str.append((new StringBuilder("   OR UPPER(CC.NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
                }
            } else
            if(sc.get(i).equals("31"))
            {
                str.append("  this_.CAUSALITY_ID IN   \n ");
                str.append("  (SELECT C.ID FROM sideeffect.CAUSALITY C \n ");
                str.append((new StringBuilder("   WHERE UPPER(C.PROPERTY_VALUE) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("32"))
                str.append((new StringBuilder("   UPPER(this_.ORGANIZATION) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("33"))
                str.append((new StringBuilder("   this_.ORGANIZATION_TEL LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("34"))
                str.append((new StringBuilder("   UPPER(this_.EXTA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("35"))
            {
                str.append("   this_.CAUSALITY_ID = (select c.id from CAUSALITY c \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   where c.ID = ")).append(sv.get(i).toString()).append(" )  ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   where c.PROPERTY_VALUE like '%")).append(sv.get(i).toString()).append("%' )  ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("36"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER = ")).append(sv.get(i).toString()).append("  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("37"))
                str.append((new StringBuilder(" SERV.PRODUCT_COB_CODE LIKE '%")).append(sv.get(i).toString()).append("%'   ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        if(intSc == 0)
            str.append((new StringBuilder(" REPORT_YEAR || REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM sideeffect.SIDE_EFFECT_REPORT_TYPE_DATE jsertd \n ");
            str.append(" where this_.id = jsertd.report_id \n ");
            str.append((new StringBuilder(" and jsertd.report_type_id =")).append(intSc).append(" \n ").toString());
            str.append((new StringBuilder(" and jsertd.report_year || jsertd.report_month between '")).append(year1).append(month1).append("' and '").append(year2).append(month2).append("') \n ").toString());
        }
        if(intSc == 4)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year2), Integer.parseInt(month2), 1);
            String lastDay = String.valueOf(calendar.getActualMaximum(5));
            str.append((new StringBuilder(" this_.first_modified between '")).append(year1).append("-").append(month1).append("-01'").append(" and '").append(year2).append("-").append(month2).append("-").append(lastDay).append("'").toString());
        }
        str.append(" ) AS A");
        if(pgSize > 0)
        {
            System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].pg+9  ")).append(pg + 4).toString());
            str.append(" WHERE ROWNUM <=10");
            str.append((new StringBuilder(" ) WHERE RNUM2 >=")).append(pg).toString());
        } else
        {
            str.append(" ) ");
        }
        if(orderText.equals(""))
        {
            str.append((new StringBuilder(" order by first_modified ")).append(order1).toString());
            str.append((new StringBuilder(" , id ")).append(order1).toString());
        } else
        {
            str.append((new StringBuilder(" order by ")).append(orderText).append(" ").append(order1).toString());
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSideeffectReport.class);
        List l = query.list();
        return l;
    }

    public List sideeffectCntStep(String stepCodeArr[][], String fmDate, String toDate, String reportType, Integer curStep)
    {
        List l = null;
        Integer num = Integer.valueOf(0);
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class, "report");
        if(reportType.equals("0"))
        {
            c.add(Restrictions.sqlRestriction((new StringBuilder(" this_.report_year ||  this_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        } else
        {
            num = Integer.valueOf(num.intValue() + 1);
            c.createCriteria("sideEffectReportTypeDate", "typedate").add(Restrictions.sqlRestriction((new StringBuilder(" typedate")).append(num).append("_.report_year ||  typedate").append(num).append("_.report_month between ").append(fmDate).append(" and ").append(toDate).toString())).add(Restrictions.eq("report_type_id", Integer.valueOf(Integer.parseInt(reportType))));
        }
        for(int a = 0; a <= curStep.intValue(); a++)
        {
            String joinTable = stepCodeArr[a][1];
            if(!joinTable.equals(null) && !joinTable.equals("0"))
            {
                String stepID = stepCodeArr[a][3];
                String joinTableArr[] = stepCodeArr[a][1].split(" ");
                String table = "";
                String mebItem = "";
                if(a == curStep.intValue() - 1)
                {
                    for(int b = 0; b < joinTableArr.length; b++)
                    {
                        num = Integer.valueOf(num.intValue() + 1);
                        table = (new StringBuilder(String.valueOf(table))).append(joinTableArr[b]).append(".").toString();
                        if(b == joinTableArr.length - 1)
                        {
                            c.createCriteria(table.substring(0, table.length() - 1), "maincode");
                            if(joinTableArr[b].equals("meb_item"))
                            {
                                mebItem = "Y";
                                num = Integer.valueOf(num.intValue() + 1);
                            }
                        } else
                        {
                            c.createCriteria(table.substring(0, table.length() - 1), (new StringBuilder(String.valueOf(a))).append("_").append(b).append("code").toString());
                        }
                    }

                    if(stepCodeArr[a][5].equals("L"))
                    {
                        if(mebItem.equals("Y"))
                        {
                            c.createCriteria((new StringBuilder(String.valueOf(table))).append("cobFlagType").toString(), "cobcode");
                            c.setProjection(Projections.projectionList().add(Projections.groupProperty((new StringBuilder("maincode.")).append(stepCodeArr[a][2]).toString()).as("varId")).add(Projections.groupProperty((new StringBuilder("maincode.")).append(stepCodeArr[a][4]).toString()).as("varValue1")).add(Projections.groupProperty("cobcode.propertyValue").as("varValue2")).add(Projections.sqlProjection("count(this_.id) as varCnt", new String[] {
                                "varCnt"
                            }, new Type[] {
                                StandardBasicTypes.LONG
                            }), "varCnt")).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
                        } else
                        {
                            c.setProjection(Projections.projectionList().add(Projections.groupProperty((new StringBuilder("maincode.")).append(stepCodeArr[a][2]).toString()).as("varId")).add(Projections.groupProperty((new StringBuilder("maincode.")).append(stepCodeArr[a][4]).toString()).as("varValue1")).add(Projections.sqlProjection("count(this_.id) as varCnt", new String[] {
                                "varCnt"
                            }, new Type[] {
                                StandardBasicTypes.LONG
                            }), "varCnt")).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
                        }
                    } else
                    {
                        c.setProjection(Projections.projectionList().add(Projections.groupProperty((new StringBuilder("maincode.")).append(stepCodeArr[a][2]).toString()).as("varIntId")).add(Projections.groupProperty((new StringBuilder("maincode.")).append(stepCodeArr[a][4]).toString()).as("varValue1")).add(Projections.sqlProjection("count(this_.id) as varCnt", new String[] {
                            "varCnt"
                        }, new Type[] {
                            StandardBasicTypes.LONG
                        }), "varCnt")).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
                    }
                } else
                {
                    for(int b = 0; b < joinTableArr.length; b++)
                        if(!joinTableArr[b].equals(null) && !joinTableArr[b].equals("0"))
                        {
                            num = Integer.valueOf(num.intValue() + 1);
                            table = (new StringBuilder(String.valueOf(table))).append(joinTableArr[b]).append(".").toString();
                            if(b == joinTableArr.length - 1)
                                c.createCriteria(table.substring(0, table.length() - 1), (new StringBuilder(String.valueOf(a))).append("_").append(b).append("code").toString()).add(Restrictions.sqlRestriction((new StringBuilder("code")).append(num).append("_.").append(stepCodeArr[a][2]).append(" in (").append(stepID).append(")").toString()));
                            else
                                c.createCriteria(table.substring(0, table.length() - 1), (new StringBuilder(String.valueOf(a))).append("_").append(b).append("code").toString());
                        }

                }
            }
        }

        l = c.list();
        return l;
    }

    public SimpleSideeffectReport itemFrequency(String fmDate, String toDate, String reportType, String target, String whereCol, String codeGB, long itemSeq, Integer categorySeq, long codeSeq)
    {
        SimpleSideeffectReport sideeffect = new SimpleSideeffectReport();
        Session session = sessionFactory.getCurrentSession();
        String sumSqlEq = "";
        String sumSeqNe = "";
        String andCol_P = "sum((select count(pat.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_patient_condition pat on (pat.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id ";
        String andCol_M = "sum( (select count(med.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_medical_code med on (med.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id";
        String andCol_C = "sum( (select count(com.id) from sgi_se_item se_item join side_effecct_report report on ( se_item.id = report.meb_item_id) join junc_side_effect_component_code com on (com.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id ";
        if(codeGB.equals("P"))
        {
            sumSqlEq = (new StringBuilder(String.valueOf(andCol_P))).append(whereCol).append(" = ").append(codeSeq).toString();
            sumSeqNe = (new StringBuilder(String.valueOf(andCol_P))).append(whereCol).append(" <> ").append(codeSeq).toString();
        } else
        if(codeGB.equals("M"))
        {
            sumSqlEq = (new StringBuilder(String.valueOf(andCol_M))).append(whereCol).append(" = ").append(codeSeq).toString();
            sumSeqNe = (new StringBuilder(String.valueOf(andCol_M))).append(whereCol).append(" <> ").append(codeSeq).toString();
        } else
        if(codeGB.equals("C"))
        {
            sumSqlEq = (new StringBuilder(String.valueOf(andCol_C))).append(whereCol).append(" = ").append(codeSeq).toString();
            sumSeqNe = (new StringBuilder(String.valueOf(andCol_C))).append(whereCol).append(" <> ").append(codeSeq).toString();
        }
        Criteria c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
        c.createCriteria("meb_item", "item").createCriteria("company", "company").createCriteria("item.mea_item", "item_category").createCriteria("item.cobFlagType", "cobflag").setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("NVL(")).append(sumSqlEq).append(")),0) as varCnt1 ").toString(), new String[] {
            "varCnt1"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt1").add(Projections.sqlProjection((new StringBuilder("NVL(")).append(sumSeqNe).append(")),0) as varCnt2 ").toString(), new String[] {
            "varCnt2"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt2")).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
        c.add(Restrictions.eq("item.item_category_number", categorySeq));
        if(target.equals("EQ"))
            c.add(Restrictions.eq("item.id", Long.valueOf(itemSeq)));
        else
            c.add(Restrictions.ne("item.id", Long.valueOf(itemSeq)));
        if(!reportType.equals("0"))
            c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date6_.report_year ||  date6_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date5_.report_year ||  date5_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        sideeffect = (SimpleSideeffectReport)c.uniqueResult();
        return sideeffect;
    }

    public long itemLikelihoodCnt(String classKorName, String meaClassNo, String reportType, String fmDate, String toDate, String classKorNameEqual)
    {
        long totalCnt = 0L;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.createCriteria("meb_item", "item").createCriteria("item.mea_item", "item_category").setProjection(Projections.countDistinct("item.item_category_number")).add(Restrictions.like("item_category.mea_class_no", (new StringBuilder("%")).append(meaClassNo).append("%").toString()));
        if(classKorNameEqual.equals("Y"))
            c.add(Restrictions.eq("item_category.class_kor_name", classKorName));
        else
            c.add(Restrictions.like("item_category.class_kor_name", (new StringBuilder("%")).append(classKorName).append("%").toString()));
        if(!reportType.equals("0"))
            c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date4_.report_year ||  date4_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        totalCnt = ((Long)c.uniqueResult()).longValue();
        return totalCnt;
    }

    public List itemLikelihoodList(String classKorName, String meaClassNo, String reportType, String fmDate, String toDate, int pgSize, int startPg, 
            String classKorNameEqual)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
        c.createCriteria("meb_item", "item").createCriteria("item.mea_item", "item_category").add(Restrictions.like("item_category.mea_class_no", (new StringBuilder("%")).append(meaClassNo).append("%").toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection("sum( (select count(med.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_medical_code med on (med.report_id = report.id) where se_item.item_category_number = item1_.item_category_number and report.id = this_.id) ) as varCnt1 ", new String[] {
            "varCnt1"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt1").add(Projections.sqlProjection("sum((select count(pat.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_patient_condition pat on (pat.report_id = report.id) where se_item.item_category_number = item1_.item_category_number and report.id = this_.id) ) as varCnt2 ", new String[] {
            "varCnt2"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt2").add(Projections.sqlProjection("sum((select count(report.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) where se_item.item_category_number = item1_.item_category_number and report.id = this_.id) ) as varCnt ", new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.sqlProjection("sum( (select count(com.id) from sgi_se_item se_item join side_effecct_report report on ( se_item.id = report.meb_item_id) join junc_side_effect_component_code com on (com.report_id = report.id) where se_item.item_category_number = item1_.item_category_number and report.id = this_.id) ) as varCnt3 ", new String[] {
            "varCnt3"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt3").add(Projections.groupProperty("item_category.id").as("varId")).add(Projections.groupProperty("item_category.mea_class_no").as("varValue1")).add(Projections.groupProperty("item_category.class_kor_name").as("varValue2")).add(Projections.groupProperty("item_category.grade").as("varValue3"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class)).addOrder(Order.asc("item_category.class_kor_name"));
        if(classKorNameEqual.equals("Y"))
            c.add(Restrictions.eq("item_category.class_kor_name", classKorName));
        else
            c.add(Restrictions.like("item_category.class_kor_name", (new StringBuilder("%")).append(classKorName).append("%").toString()));
        if(pgSize > 0)
            c.setFirstResult(startPg).setMaxResults(pgSize);
        if(!reportType.equals("0"))
            c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date4_.report_year ||  date4_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        l = c.list();
        return l;
    }

    public List itemCatagoryMatrix(String fmDate, String toDate, String verDate, String reportType, long categorySeq, String codeGB, 
            long parentId)
    {
        String bfCountSeq = "";
        String codeTable = "";
        String juncCodeTable = "";
        String groupDate = "";
        String groupYear = "";
        if(codeGB.equals("M"))
        {
            codeTable = "medicalCode";
            juncCodeTable = " JOIN JUNC_SIDE_EFFECT_MEDICAL_CODE JUNC ON (REPORT.ID = JUNC.REPORT_ID)  JOIN MEDICAL_DEVICE_MALFUNCTION_CODE JUNCCODE ON (JUNCCODE.ID = JUNC.MEDICAL_ID) ";
        } else
        if(codeGB.equals("P"))
        {
            codeTable = "patientCodeCondition";
            juncCodeTable = " JOIN JUNC_SIDE_EFFECT_PATIENT_CONDITION JUNC ON (REPORT.ID = JUNC.REPORT_ID)  JOIN PATIENT_CONDITION JUNCCODE ON (JUNCCODE.ID = JUNC.PATIENT_ID) ";
        } else
        if(codeGB.equals("C"))
        {
            codeTable = "juncComponentCode";
            juncCodeTable = " JOIN JUNC_SIDE_EFFECT_COMPONENT_CODE JUNC ON (REPORT.ID = JUNC.REPORT_ID)  JOIN COMPONENT_CODE JUNCCODE ON (JUNCCODE.ID = JUNC.COMPONENT_ID) ";
        }
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
        c.createCriteria(codeTable, "code");
        if(!reportType.equals("0"))
        {
            c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
            bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT ")).append(juncCodeTable).append(" JOIN SIDE_EFFECT_REPORT_TYPE_DATE TYPE_DATE ").append(" ON(REPORT.ID = TYPE_DATE.REPORT_ID)").append(" WHERE TYPE_DATE.REPORT_TYPE_ID = ").append(reportType).append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH >= '").append(verDate).append("'").append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH < '").append(fmDate).append("' ").toString();
            groupDate = " date3_.report_year||date3_.report_month||'01' ";
            groupYear = " date3_.report_year ";
        } else
        {
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
            bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT ")).append(juncCodeTable).append(" WHERE REPORT.REPORT_YEAR || REPORT.REPORT_MONTH >= '").append(verDate).append("' ").append(" AND REPORT.REPORT_YEAR || REPORT.REPORT_MONTH <'").append(fmDate).append("' ").toString();
            groupDate = " date2_.report_year||date2_.report_month||'01' ";
            groupYear = " date2_.report_year ";
        }
        c.createCriteria("sideeffect.meb_item", "item").createCriteria("item.mea_item", "item_category").add(Restrictions.eq("item_category.id", Long.valueOf(categorySeq)));
        c.createCriteria("sideeffect.sideeffectResult", "result").add(Restrictions.eq("result.parentId", Long.valueOf(parentId)));
        c.createCriteria("sideeffect.meb_item.cobFlagType", "cobFlag").createCriteria("sideeffect.meb_item.company", "company");
        c.setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder(" NVL((")).append(bfCountSeq).append(" AND REPORT.MEB_ITEM_ID=this_.meb_item_id").append(" AND JUNCCODE.ID = code1_.id),0) AS varCnt1 ").toString(), new String[] {
            "varCnt1"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt1").add(Projections.sqlProjection((new StringBuilder(" NVL((SELECT SUM(CASE WHEN IMPORT_YEAR < ")).append(groupYear).append(" THEN ITEM_IMPORT ").append(" ELSE ITEM_IMPORT/12 * (MONTHS_BETWEEN( IMPORT_YEAR || IMPORT_MONTH || '01',").append(groupDate).append(")+1) END) ").append("FROM ITEM_IMPORT ").append("WHERE ITEM_ID = this_.meb_item_id ").append("AND IMPORT_YEAR  BETWEEN ").append(verDate.substring(0, 4)).append(" AND ").append(fmDate.substring(0, 4)).append("),0) AS varCnt2 ").toString(), new String[] {
            "varCnt2"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt2").add(Projections.sqlProjection((new StringBuilder(" NVL((SELECT SUM(CASE WHEN OUTPUT_YEAR < ")).append(groupYear).append(" THEN ITEM_OUTPUT ").append(" ELSE ITEM_OUTPUT/12 * (MONTHS_BETWEEN( OUTPUT_YEAR || OUTPUT_MONTH || '01',").append(groupDate).append(")+1) END) ").append("FROM ITEM_OUTPUT ").append("WHERE ITEM_ID = this_.meb_item_id ").append("AND OUTPUT_YEAR   BETWEEN ").append(verDate.substring(0, 4)).append(" AND ").append(fmDate.substring(0, 4)).append(" ),0) AS varCnt3 ").toString(), new String[] {
            "varCnt3"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt3").add(Projections.groupProperty("sideeffect.meb_item_id")).add(Projections.alias(Projections.groupProperty("code.id"), "varId")).add(Projections.count("sideeffect.id"), "varCnt").add(Projections.alias(Projections.groupProperty("cobFlag.propertyValue"), "varValue1")).add(Projections.alias(Projections.groupProperty("item.meddev_item_no"), "varValue2")).add(Projections.alias(Projections.groupProperty("company.entp_name"), "varValue3")).add(Projections.alias(Projections.groupProperty("code.fdaCode"), "varValue4")).add(Projections.alias(Projections.groupProperty("code.fdaSourcePtKor"), "varValue5")).add(Projections.alias(Projections.groupProperty("date.report_year"), "varYear")).add(Projections.alias(Projections.groupProperty("date.report_month"), "varMonth")).add(Projections.alias(Projections.groupProperty("sideeffect.meb_item_id"), "meb_item_id"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
        c.addOrder(Order.desc("cobFlag.propertyValue")).addOrder(Order.desc("item.meddev_item_no")).addOrder(Order.desc("code.fdaCode")).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List resultList(SimpleSideeffectResult simpleSideeffectResult, Date rd)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectResult.class);
        c.add(Restrictions.ge("startDate", rd));
        c.addOrder(Order.asc("parentId")).addOrder(Order.asc("id"));
        List l = c.list();
        return l;
    }

    public List getMatrix(String fmDate, String toDate, String verDate, String reportType, long group_id, long parentId, Long category_type)
    {
        List l = null;
        String sgi_se_item_category_alias = "item_categ4_";
        try
        {
            String bfCountSeq = "";
            String groupDate = "";
            String groupYear = "";
            Session session = sessionFactory.getCurrentSession();
            Criteria c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
            if(!reportType.equals("0"))
            {
                c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
                bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT  JOIN SIDE_EFFECT_REPORT_TYPE_DATE TYPE_DATE  ON(REPORT.ID = TYPE_DATE.REPORT_ID) WHERE TYPE_DATE.REPORT_TYPE_ID = ")).append(reportType).append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH >= '").append(verDate).append("'").append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH < '").append(fmDate).append("' ").toString();
                groupDate = " date2_.report_year||date2_.report_month||'01' ";
                groupYear = " date2_.report_year ";
            } else
            {
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
                bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT  WHERE REPORT.REPORT_YEAR || REPORT.REPORT_MONTH >= '")).append(verDate).append("' ").append(" AND REPORT.REPORT_YEAR || REPORT.REPORT_MONTH <'").append(fmDate).append("' ").toString();
                groupDate = " date1_.report_year||date1_.report_month||'01' ";
                groupYear = " date1_.report_year ";
                sgi_se_item_category_alias = "item_categ3_";
            }
            c.createCriteria("sideeffect.meb_item", "item").createCriteria("item.mea_item", "item_category");
            if(category_type.longValue() != 1L)
                c.createCriteria("item_category.juncItemCategoryGroup", "jicg").add(Restrictions.eq("jicg.group_id", Long.valueOf(group_id)));
            else
                c.add(Restrictions.sqlRestriction((new StringBuilder(String.valueOf(sgi_se_item_category_alias))).append(".id not in((select category_id from junc_item_category_group))").toString()));
            c.createCriteria("sideeffect.sideeffectResult", "result").add(Restrictions.eq("result.parentId", Long.valueOf(parentId)));
            c.createCriteria("sideeffect.meb_item.cobFlagType", "cobFlag").createCriteria("sideeffect.meb_item.company", "company");
            c.setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder(" NVL((")).append(bfCountSeq).append(" AND REPORT.MEB_ITEM_ID=this_.meb_item_id),0) AS varCnt1 ").toString(), new String[] {
                "varCnt1"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt1").add(Projections.sqlProjection((new StringBuilder(" NVL((SELECT SUM(CASE WHEN IMPORT_YEAR < ")).append(groupYear).append(" THEN ITEM_IMPORT ").append(" ELSE ITEM_IMPORT/12 * (MONTHS_BETWEEN( IMPORT_YEAR || IMPORT_MONTH || '01',").append(groupDate).append(")+1) END) ").append("FROM ITEM_IMPORT ").append("WHERE ITEM_ID = this_.meb_item_id ").append("AND IMPORT_YEAR  BETWEEN ").append(verDate.substring(0, 4)).append(" AND ").append(fmDate.substring(0, 4)).append("),0) AS varCnt2 ").toString(), new String[] {
                "varCnt2"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt2").add(Projections.sqlProjection((new StringBuilder(" NVL((SELECT SUM(CASE WHEN OUTPUT_YEAR < ")).append(groupYear).append(" THEN ITEM_OUTPUT ").append(" ELSE ITEM_OUTPUT/12 * (MONTHS_BETWEEN( OUTPUT_YEAR || OUTPUT_MONTH || '01',").append(groupDate).append(")+1) END) ").append("FROM ITEM_OUTPUT ").append("WHERE ITEM_ID = this_.meb_item_id ").append("AND OUTPUT_YEAR   BETWEEN ").append(verDate.substring(0, 4)).append(" AND ").append(fmDate.substring(0, 4)).append(" ),0) AS varCnt3 ").toString(), new String[] {
                "varCnt3"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt3").add(Projections.count("sideeffect.id"), "varCnt").add(Projections.alias(Projections.groupProperty("cobFlag.propertyValue"), "varValue1")).add(Projections.alias(Projections.groupProperty("item.meddev_item_no"), "varValue2")).add(Projections.alias(Projections.groupProperty("company.entp_name"), "varValue3")).add(Projections.alias(Projections.groupProperty("date.report_year"), "varYear")).add(Projections.alias(Projections.groupProperty("date.report_month"), "varMonth")).add(Projections.alias(Projections.groupProperty("sideeffect.meb_item_id"), "meb_item_id"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
            c.addOrder(Order.desc("cobFlag.propertyValue")).addOrder(Order.desc("item.meddev_item_no")).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
            System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getMatrix().reportType  ")).append(reportType).toString());
            System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getMatrix().parentId  ")).append(parentId).toString());
            System.out.println("[SimpleSideeffectReportDAO].getMatrix().query");
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return l;
    }

    public List getItemList(String fmDate, String toDate, String verDate, String reportType, long group_id, long parentId, List itemIdList, Long category_type)
    {
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().fmDate  ")).append(fmDate).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().toDate  ")).append(toDate).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().verDate  ")).append(verDate).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().reportType  ")).append(reportType).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().group_id  ")).append(group_id).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().parentId  ")).append(parentId).toString());
        String sgi_se_item_category_alias = "item_categ4_";
        List l = null;
        StringBuffer str = new StringBuffer();
        String or = " or ";
        int itemIdSize = itemIdList.size();
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().itemIdList.size() ")).append(itemIdList.size()).toString());
        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().itemIdSize ")).append(itemIdSize).toString());
        for(int i = 0; i < itemIdSize; i++)
        {
            if(i == itemIdSize - 1)
                or = " ";
            System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().itemIdList  ")).append(itemIdList.get(i)).toString());
            str.append((new StringBuilder("this_.meb_item_id=")).append(itemIdList.get(i)).append(or).toString());
            System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().i ")).append(i).toString());
            System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().itemIdSize ")).append(itemIdSize).toString());
        }

        System.out.println((new StringBuilder("[SimpleSideeffectReportDAO].getItemList().str ")).append(str).toString());
        String subQuery = "";
        subQuery = str.toString();
        try
        {
            String bfCountSeq = "";
            String groupDate = "";
            String groupYear = "";
            Session session = sessionFactory.getCurrentSession();
            Criteria c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
            if(!reportType.equals("0"))
            {
                c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
                bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT  JOIN SIDE_EFFECT_REPORT_TYPE_DATE TYPE_DATE  ON(REPORT.ID = TYPE_DATE.REPORT_ID) WHERE TYPE_DATE.REPORT_TYPE_ID = ")).append(reportType).append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH >= '").append(verDate).append("'").append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH < '").append(fmDate).append("' ").toString();
                groupDate = " date2_.report_year||date2_.report_month||'01' ";
                groupYear = " date2_.report_year ";
            } else
            {
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
                bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT  WHERE REPORT.REPORT_YEAR || REPORT.REPORT_MONTH >= '")).append(verDate).append("' ").append(" AND REPORT.REPORT_YEAR || REPORT.REPORT_MONTH <'").append(fmDate).append("' ").toString();
                groupDate = " date1_.report_year||date1_.report_month||'01' ";
                groupYear = " date1_.report_year ";
                sgi_se_item_category_alias = "item_categ3_";
            }
            c.add(Restrictions.sqlRestriction((new StringBuilder(" ( ")).append(subQuery).append(" )").toString()));
            c.createCriteria("sideeffect.meb_item", "item").createCriteria("item.mea_item", "item_category");
            if(category_type.longValue() != 1L)
            {
                c.createCriteria("item_category.juncItemCategoryGroup", "jicg").add(Restrictions.eq("jicg.group_id", Long.valueOf(group_id)));
                c.createCriteria("jicg.itemCategoryGroup", "icg");
            } else
            {
                c.add(Restrictions.sqlRestriction((new StringBuilder(String.valueOf(sgi_se_item_category_alias))).append(".id not in (select category_id from junc_item_category_group)").toString()));
            }
            c.createCriteria("sideeffect.sideeffectResult", "result").add(Restrictions.eq("result.parentId", Long.valueOf(parentId)));
            c.createCriteria("sideeffect.meb_item.cobFlagType", "cobFlag").createCriteria("sideeffect.meb_item.company", "company");
            c.setProjection(Projections.projectionList().add(Projections.sqlProjection("sum( (select count(med.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_medical_code med on (med.report_id = report.id) where se_item.item_category_number = item_category_number and report.id = this_.id) ) as varCnt1 ", new String[] {
                "varCnt1"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt1").add(Projections.sqlProjection("sum((select count(pat.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_patient_condition pat on (pat.report_id = report.id) where se_item.item_category_number = item_category_number and report.id = this_.id) ) as varCnt2 ", new String[] {
                "varCnt2"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt2").add(Projections.sqlProjection("sum( (select count(com.id) from sgi_se_item se_item join side_effecct_report report on ( se_item.id = report.meb_item_id) join junc_side_effect_component_code com on (com.report_id = report.id) where se_item.item_category_number = item_category_number and report.id = this_.id) ) as varCnt3 ", new String[] {
                "varCnt3"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt3").add(Projections.count("sideeffect.id"), "varCnt").add(Projections.alias(Projections.groupProperty("item_category.mea_class_no"), "varValue1")).add(Projections.alias(Projections.groupProperty("item_category.class_kor_name"), "varValue2")).add(Projections.alias(Projections.groupProperty("item_category.grade"), "varValue3"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
            System.out.println("[SimpleSideeffectReportDAO].getItemList().query");
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return l;
    }

    public List selectByItemId(String fmDate, String toDate, String verDate, String reportType, long group_id, long parentId, Long category_type, List itemIdList)
    {
        Session session = null;
        Criteria c = null;
        List l = null;
        String bfCountSeq = "";
        String groupDate = "";
        String groupYear = "";
        String sgi_se_item_category_alias = "item_categ4_";
        try
        {
            session = sessionFactory.getCurrentSession();
            c = session.createCriteria(SimpleSideeffectReport.class, "sideeffect");
            c.add(Restrictions.in("meb_item_id", itemIdList));
            if(!reportType.equals("0"))
            {
                c.createCriteria("sideEffectReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
                bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT  JOIN SIDE_EFFECT_REPORT_TYPE_DATE TYPE_DATE  ON(REPORT.ID = TYPE_DATE.REPORT_ID) WHERE TYPE_DATE.REPORT_TYPE_ID = ")).append(reportType).append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH >= '").append(verDate).append("'").append(" AND TYPE_DATE.REPORT_YEAR || TYPE_DATE.REPORT_MONTH < '").append(fmDate).append("' ").toString();
                groupDate = " date2_.report_year||date2_.report_month||'01' ";
                groupYear = " date2_.report_year ";
            } else
            {
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
                bfCountSeq = (new StringBuilder("SELECT COUNT(*)  FROM SIDE_EFFECCT_REPORT REPORT  WHERE REPORT.REPORT_YEAR || REPORT.REPORT_MONTH >= '")).append(verDate).append("' ").append(" AND REPORT.REPORT_YEAR || REPORT.REPORT_MONTH <'").append(fmDate).append("' ").toString();
                groupDate = " date1_.report_year||date1_.report_month||'01' ";
                groupYear = " date1_.report_year ";
                sgi_se_item_category_alias = "item_categ3_";
            }
            c.createCriteria("sideeffect.meb_item", "item").createCriteria("item.mea_item", "item_category");
            if(category_type.longValue() != 1L)
                c.createCriteria("item_category.juncItemCategoryGroup", "jicg").add(Restrictions.eq("jicg.group_id", Long.valueOf(group_id)));
            else
                c.add(Restrictions.sqlRestriction((new StringBuilder(String.valueOf(sgi_se_item_category_alias))).append(".id not in((select category_id from junc_item_category_group))").toString()));
            c.createCriteria("sideeffect.sideeffectResult", "result").add(Restrictions.eq("result.parentId", Long.valueOf(parentId)));
            c.createCriteria("sideeffect.meb_item.cobFlagType", "cobFlag").createCriteria("sideeffect.meb_item.company", "company");
            c.setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder(" NVL((")).append(bfCountSeq).append(" AND REPORT.MEB_ITEM_ID=this_.meb_item_id),0) AS varCnt1 ").toString(), new String[] {
                "varCnt1"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt1").add(Projections.sqlProjection((new StringBuilder(" NVL((SELECT SUM(CASE WHEN IMPORT_YEAR < ")).append(groupYear).append(" THEN ITEM_IMPORT ").append(" ELSE ITEM_IMPORT/12 * (MONTHS_BETWEEN( IMPORT_YEAR || IMPORT_MONTH || '01',").append(groupDate).append(")+1) END) ").append("FROM ITEM_IMPORT ").append("WHERE ITEM_ID = this_.meb_item_id ").append("AND IMPORT_YEAR  BETWEEN ").append(verDate.substring(0, 4)).append(" AND ").append(fmDate.substring(0, 4)).append("),0) AS varCnt2 ").toString(), new String[] {
                "varCnt2"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt2").add(Projections.sqlProjection((new StringBuilder(" NVL((SELECT SUM(CASE WHEN OUTPUT_YEAR < ")).append(groupYear).append(" THEN ITEM_OUTPUT ").append(" ELSE ITEM_OUTPUT/12 * (MONTHS_BETWEEN( OUTPUT_YEAR || OUTPUT_MONTH || '01',").append(groupDate).append(")+1) END) ").append("FROM ITEM_OUTPUT ").append("WHERE ITEM_ID = this_.meb_item_id ").append("AND OUTPUT_YEAR   BETWEEN ").append(verDate.substring(0, 4)).append(" AND ").append(fmDate.substring(0, 4)).append(" ),0) AS varCnt3 ").toString(), new String[] {
                "varCnt3"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "varCnt3").add(Projections.sqlProjection("sum( (select count(med.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_medical_code med on (med.report_id = report.id) where se_item.item_category_number = item_category_number and report.id = this_.id) ) as medicalCount ", new String[] {
                "medicalCount"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "medicalCount").add(Projections.sqlProjection("sum((select count(pat.id) from sgi_se_item se_item join side_effecct_report report on (se_item.id = report.meb_item_id) join junc_side_effect_patient_condition pat on (pat.report_id = report.id) where se_item.item_category_number = item_category_number and report.id = this_.id) ) as patientCount ", new String[] {
                "patientCount"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "patientCount").add(Projections.sqlProjection("sum( (select count(com.id) from sgi_se_item se_item join side_effecct_report report on ( se_item.id = report.meb_item_id) join junc_side_effect_component_code com on (com.report_id = report.id) where se_item.item_category_number = item_category_number and report.id = this_.id) ) as componentCount ", new String[] {
                "componentCount"
            }, new Type[] {
                StandardBasicTypes.LONG
            }), "componentCount").add(Projections.count("sideeffect.id"), "varCnt").add(Projections.alias(Projections.groupProperty("item_category.mea_class_no"), "varValue1")).add(Projections.alias(Projections.groupProperty("item.meddev_item_no"), "varValue2")).add(Projections.alias(Projections.groupProperty("item_category.grade"), "varValue3")).add(Projections.alias(Projections.groupProperty("date.report_year"), "varYear")).add(Projections.alias(Projections.groupProperty("date.report_month"), "varMonth")).add(Projections.alias(Projections.groupProperty("sideeffect.meb_item_id"), "meb_item_id"))).setResultTransformer(Transformers.aliasToBean(SimpleSideeffectReport.class));
            c.addOrder(Order.desc("item.meddev_item_no")).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
            System.out.println("[SimpleSideeffectReportDAO].selectByItemId().query");
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return l;
    }

    private SessionFactory sessionFactory;
}

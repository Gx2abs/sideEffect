// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSafetyReportDAO.java

package safety;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import properties.SimpleSafetyReportMebTypeInfo;
import report.SimpleSideeffectReport;

// Referenced classes of package safety:
//            SafetyDAO, SimpleSafetyReport, SafetyReport, SafetyReportVO, 
//            JuncSafetyReporterTypes, JuncSafetyReportType, JuncSafetyPatientCondition, JuncSafetyMedicalCode, 
//            JuncSafetyComponentCode, SimpleJuncSafetyReportType2, SimpleSafetyReportTypeDate, SimpleSafetyReportView, 
//            SafetyReportView

public class SimpleSafetyReportDAO
    implements SafetyDAO
{

    public SimpleSafetyReportDAO()
    {
        sessionFactory = null;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public List list(SafetyReport report)
    {
        List list = null;
        Class cls = report.getClass();
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        c.addOrder(Order.desc("first_modified"));
        c.addOrder(Order.desc("id"));
        c.setFirstResult(0).setMaxResults(100);
        list = c.list();
        if(list != null)
            list.size();
        return list;
    }

    public List list(SafetyReport report, int pg, int limit)
    {
        List list = null;
        Class cls = report.getClass();
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        c.setMaxResults(limit);
        c.setFirstResult(limit - 10);
        list = c.list();
        if(list != null)
            list.size();
        return list;
    }

    public int add(SimpleSafetyReport report)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Integer)s.save(report)).intValue();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int add(SafetyReportVO report)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Integer)s.save(report)).intValue();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public SafetyReport read(int id)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        c.add(Restrictions.eq("id", Integer.valueOf(id)));
        c.createCriteria("reporterTypes", JoinType.LEFT_OUTER_JOIN);
        SafetyReport sr = (SafetyReport)c.uniqueResult();
        return sr;
    }

    public int delete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            SimpleSafetyReport report = new SimpleSafetyReport();
            report.setId(Integer.valueOf(id));
            s.delete(report);
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reportTypeDateDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.SimpleSafetyReportTypeDate WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long juncReportTypeDateDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.SimpleJuncSafetyReportType2 WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long juncReporterTypeDateDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.JuncSafetyReporterTypes WHERE safety_report = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long juncSafetyReportTypeDateDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.JuncSafetyReportType WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int update(SimpleSafetyReport report)
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

    public long reporterTypesAdd(JuncSafetyReporterTypes juncSafetyReporterTypes)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSafetyReporterTypes)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reporterTypesAdd(JuncSafetyReportType juncSafetyReportType)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSafetyReportType)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int reporterTypesDel(int safety_report)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.JuncSafetyReporterTypes WHERE safety_report = ? ").setInteger(0, safety_report).executeUpdate();
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List get(String search)
    {
        return null;
    }

    public List searchList(SafetyReport report, int pg, int limit, HashSet hs)
    {
        Iterator itr = hs.iterator();
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SAFETY_REPORT this_ WHERE this_.id = 0");
        for(; itr.hasNext(); str.append((new StringBuilder(" or this_.id = ")).append(itr.next()).toString()));
        str.append("order by first_modified desc");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSafetyReport.class);
        query.setMaxResults(limit);
        query.setFirstResult(limit - 10);
        List l = query.list();
        return l;
    }

    public List searchList(SafetyReport report, HashSet hs)
    {
        Iterator itr = hs.iterator();
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SAFETY_REPORT this_ WHERE this_.id = 0");
        for(; itr.hasNext(); str.append((new StringBuilder(" or this_.id = ")).append(itr.next()).toString()));
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSafetyReport.class);
        List l = query.list();
        return l;
    }

    public List searchList(SafetyReport report, HashSet hs, String sv)
    {
        Iterator itr = hs.iterator();
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM SAFETY_REPORT this_ WHERE this_.id = 0");
        for(; itr.hasNext(); str.append((new StringBuilder(" or this_.id = ")).append(itr.next()).toString()));
        if(sv.length() > 1)
        {
            str.append((new StringBuilder(" or this_.ADDRESS like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.CAUSE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.COMPANY_NAME like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.COMPONENT_CODE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.DOCUMENT_NUMBER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.EMAIL like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.EXTRA_INFO like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.FAX like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.FOLLOWUP like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MANAGER like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.ORGANISATION like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.PATIENT_CONDITION_CODE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.REPRESENTATIVE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.SAFETY_CAUSE_CONTENT like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.TELEPHONE like '%")).append(sv).append("%' ").toString());
            str.append((new StringBuilder(" or this_.MANUFACTURER like '%")).append(sv).append("%' ").toString());
        }
        str.append("order by first_modified desc");
        str.append("  , id desc");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSafetyReport.class);
        List l = query.list();
        return l;
    }

    public List countryReportedInSearch(String sv)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        c.createCriteria("countryReportedIn").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List reportTypeSearch(String sv)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        c.createCriteria("reportType").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public List reporterTypeSearch(String sv)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        c.createCriteria("reporterTypes").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString())).add(Restrictions.sqlRestriction("reporterty3_.type = 2"));
        List l = c.list();
        return l;
    }

    public List mebItemSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        c.createCriteria("meb_item").add(Restrictions.or(Restrictions.like("manuf_import_name", (new StringBuilder("%")).append(sv).append("%").toString()), Restrictions.or(Restrictions.eq("meddev_item_seq", Integer.valueOf(isNumber(sv))), Restrictions.or(new Criterion[] {
            Restrictions.like("mea_class_no", (new StringBuilder("%")).append(sv).append("%").toString())
        }))));
        List l = c.list();
        return l;
    }

    public List reportStatusSearch(String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        c.createCriteria("reportStatus").add(Restrictions.like("propertyValue", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = c.list();
        return l;
    }

    public long count(SimpleSafetyReport report)
    {
        long result = 0L;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        c.setProjection(Projections.count("id"));
        result = ((Long)c.uniqueResult()).longValue();
        return result;
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

    public long safetyPatientAdd(JuncSafetyPatientCondition juncSafetyPatientCondition)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSafetyPatientCondition)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long safetyMedicalCodeAdd(JuncSafetyMedicalCode juncSafetyMedicalCode)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSafetyMedicalCode)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long safetyComponentCodeAdd(JuncSafetyComponentCode juncSafetyComponentCode)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(juncSafetyComponentCode)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reportTypesAdd(SimpleJuncSafetyReportType2 simpleJuncSafetyReportType2)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(simpleJuncSafetyReportType2)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long reportTypeDateAdd(SimpleSafetyReportTypeDate simpleSafetyReportTypeDate)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(simpleSafetyReportTypeDate)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List safetyCnt(String colName, Integer code, String fmDate, String toDate, String reportType, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class).createCriteria("safetyReportTypeDate").createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(select count(a.id) from SAFETY_REPORT a  join SAFETY_REPORT_TYPE_DATE b on (a.id = b.report_id) JOIN JUNC_SAFETY_REPORTER_TYPES reportType ON (a.ID = reportType.SAFETY_REPORT AND reportType.REPORTER_TYPE IN (")).append(reporterTypes).append("))").append(" where ").append(colName).append(" = ").append(code).append(" and  b.report_year = date2_.report_year and b.report_month = date2_.report_month and b.report_type_id = ").append(reportType).append(") as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSafetyReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List safetyCnt(String colName, Integer code, String fmDate, String toDate, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class).createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(select count(a.id) from SAFETY_REPORT a  JOIN JUNC_SAFETY_REPORTER_TYPES reportType ON (a.ID = reportType.SAFETY_REPORT AND reportType.REPORTER_TYPE IN (")).append(reporterTypes).append("))").append(" where ").append(colName).append(" = ").append(code).append(" and  report_year = date1_.report_year and report_month = date1_.report_month ) as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSafetyReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List safetyCnt(String tabName, String JoinColName, String ColName, String code, String fmDate, String toDate, String reportType, 
            String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class).createCriteria("safetyReportTypeDate").createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(select count(*) from ")).append(tabName).append(" ").append(" join SAFETY_REPORT b on ").append(JoinColName).append(" join SAFETY_REPORT_TYPE_DATE c on (b.id = c.report_id)").append(" JOIN JUNC_SAFETY_REPORTER_TYPES reportType ON (b.ID = reportType.SAFETY_REPORT AND reportType.REPORTER_TYPE IN (").append(reporterTypes).append("))").append(" where ").append(ColName).append(" = (").append(code).append(") and  c.report_year = date2_.report_year and c.report_month = date2_.report_month and c.report_type_id = ").append(reportType).append(") as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSafetyReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List safetyCnt(String tabName, String JoinColName, String ColName, String code, String fmDate, String toDate, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class).createCriteria("reportYM", "date", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.sqlRestriction((new StringBuilder(" date1_.report_year ||  date1_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection((new StringBuilder("(select count(*) from ")).append(tabName).append(" ").append(" join SAFETY_REPORT b on ").append(JoinColName).append(" JOIN JUNC_SAFETY_REPORTER_TYPES reportType ON (b.ID = reportType.SAFETY_REPORT AND reportType.REPORTER_TYPE IN (").append(reporterTypes).append("))").append(" where ").append(ColName).append(" = (").append(code).append(") and  b.report_year = date1_.report_year and b.report_month = date1_.report_month ) as varCnt ").toString(), new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("date.report_year").as("varYear")).add(Projections.groupProperty("date.report_month").as("varMonth"))).setResultTransformer(Transformers.aliasToBean(SimpleSafetyReport.class)).addOrder(Order.desc("date.report_year")).addOrder(Order.desc("date.report_month"));
        l = c.list();
        return l;
    }

    public List list(SafetyReport report, int sc, String date1, String date2, String reportType)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date parsDate1 = null;
        Date parsDate2 = null;
        try
        {
            parsDate1 = dateFormat.parse((new StringBuilder(String.valueOf(date1))).append("-01-01").toString());
            parsDate2 = dateFormat.parse((new StringBuilder(String.valueOf(date2))).append("-12-31").toString());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        List list = null;
        Class cls = report.getClass();
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleSafetyReport.class);
        if(sc == 1)
        {
            c.createCriteria("safetyReportTypeDate", "safetyReportTypeDate_");
            c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            c.add(Restrictions.between("safetyReportTypeDate_.report_date", parsDate1, parsDate2));
        } else
        if(sc == 2)
            c.add(Restrictions.between("mreport_date", parsDate1, parsDate2));
        c.addOrder(Order.desc("first_modified"));
        c.addOrder(Order.desc("id"));
        list = c.list();
        if(list != null)
            list.size();
        return list;
    }

    public List list(SafetyReport report, int sc, String year1, String year2, String month1, String month2)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        if(sc == 0)
        {
            c.add(Restrictions.between("report_year", year1, year2));
            c.add(Restrictions.between("report_month", month1, month2));
        }
        if(sc != 0)
            c.createCriteria("safetyReportTypeDate", "safetyReportTypeDate_", JoinType.INNER_JOIN).add(Restrictions.between("safetyReportTypeDate_.report_year", year1, year2)).add(Restrictions.between("safetyReportTypeDate_.report_month", month1, month2)).add(Restrictions.eq("report_type_id", Integer.valueOf(sc)));
        c.addOrder(Order.desc("first_modified"));
        c.addOrder(Order.desc("id"));
        c.setFirstResult(0);
        c.setMaxResults(100);
        List l = c.list();
        return l;
    }

    public long safetyPatientDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.JuncSafetyPatientCondition WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long safetyMedicalCodeDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.JuncSafetyMedicalCode WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long safetyComponentCodeDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE safety.JuncSafetyComponentCode WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List list(SafetyReport report, List sc, List sv, List logicalOperator, int intSc, String year1, String year2, 
            String month1, String month2, String PopYN, int pgSize, int firstResult)
    {
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM ( ");
        str.append("SELECT A.*, A.RNUM1 AS RNUM2 FROM ( ");
        str.append("SELECT SERV.*, ROWNUM AS RNUM1 FROM sideeffect.SAFETY_REPORT_VIEW SERV    ");
        str.append(" INNER JOIN sideeffect.SAFETY_REPORT this_  ");
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
                str.append("  this_.COUNTRY_REPORTED_IN =   \n ");
                str.append("  (SELECT cri.id FROM sideeffect.COUNTRY_REPORTED_IN cri \n ");
                str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM sideeffect.JUNC_SAFETY_REPORT_TYPE2 jsert2 \n ");
                str.append("   JOIN sideeffect.REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.SAFETY_REPORT FROM sideeffect.JUNC_SAFETY_REPORTER_TYPES jsrt \n ");
                str.append("   JOIN sideeffect.REPORTER_TYPE RT ON jsrt.REPORTER_TYPE = RT.ID  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE RT.ID IN (")).append(sv.get(i)).append(") ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
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
                str.append("   JOIN sideeffect.sgi_se_item_category ssic ON MI.item_category_number = ssic.id \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE ssic.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(ssic.CLASS_KOR_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.ID MEB_ITEM_ID   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE MI.MEDDEV_ENTP_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   JOIN sgi_se_item_category ssic ON MI.item_category_number = ssic.id   \n ");
                if(PopYN.equals("Y") && i == 0)
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
                    str.append((new StringBuilder("   WHERE MI.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
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
                str.append("   join sideeffect.safety_report_meb_type_info srmti ON MI.id = srmti.report_id \n ");
                str.append("   join sideeffect.MEB_TYPE_INFO mti on srmti.meddev_item_seq = mti.meddev_item_seq \n ");
                str.append((new StringBuilder("   WHERE UPPER(mti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.MEDDEV_ITEM_SEQ FROM sideeffect.MEB_ITEM MI \n ");
                str.append("   JOIN sideeffect.MEB_LOT_NO MLN ON MI.MEDDEV_ITEM_SEQ = MLN.MEDDEV_ITEM_SEQ \n ");
                str.append((new StringBuilder("   WHERE UPPER(MLN.LOT_NO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.safety_cause_content LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM sideeffect.JUNC_SAFETY_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN sideeffect.PATIENT_CONDITION PC ON JSEPC.CONDITION_ID = PC.ID \n ");
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
            if(sc.get(i).equals("24"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM sideeffect.JUNC_SAFETY_MEDICAL_CODE JSEMC \n ");
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
            if(sc.get(i).equals("25"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM sideeffect.JUNC_SAFETY_COMPONENT_CODE JSEC \n ");
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
            if(sc.get(i).equals("26"))
                str.append((new StringBuilder("   UPPER(this_.followup) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSRT.REPORT_ID FROM sideeffect.JUNC_SAFETY_REPORT_TYPE JSRT \n ");
                str.append("   JOIN sideeffect.SIDEEFFECT_REPORT_TYPE SRT ON JSRT.REPORT_TYPE_ID = SRT.ID  \n ");
                if(PopYN.equals("Y") && i == 0)
                    str.append((new StringBuilder("   WHERE SRT.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE SRT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.REPORT_STATUS IN   \n ");
                str.append("  (SELECT RS.ID FROM sideeffect.REPORT_STATUS RS \n ");
                str.append((new StringBuilder("   WHERE RS.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("29"))
                str.append((new StringBuilder("   UPPER(this_.EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("30"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        year1 = "2012";
        year2 = year1;
        if(intSc == 0)
            str.append((new StringBuilder(" this_.REPORT_YEAR || this_.REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM sideeffect.SAFETY_REPORT_TYPE_DATE jsertd \n ");
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
        str.append(" ) AS A");
        if(pgSize > 0)
        {
            str.append(" WHERE ROWNUM <=10");
            str.append(" ) WHERE RNUM2 >=1");
        } else
        {
            str.append(" ) ");
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSafetyReport.class);
        List l = query.list();
        return l;
    }

    public List list2(SafetyReportView report, List sc, List sv, List logicalOperator, List arrayIdOrName, int intSc, String year1, 
            String year2, String month1, String month2, String PopYN, int pgSize, int firstResult, int pg, 
            String orderText, String order, String paging)
    {
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
        System.out.println((new StringBuilder("[SimpleSafetyReportDAO]list2.orderText  ")).append(orderText).toString());
        if(orderText.equals("product_cob_code") || orderText.equals("product_value"))
        {
            orderText = "product_value";
            orderText2 = "A.meddev_item_no1";
            orderText3 = "A.meddev_item_no2";
        }
        StringBuffer str = new StringBuffer();
        str.append("SELECT * FROM ( ");
        str.append("SELECT * FROM ( ");
        if(!orderText.equals("") || orderText.length() > 0)
        {
            if(orderText.equals("product_cob_code") || orderText.equals("product_value"))
                str.append((new StringBuilder("SELECT  ROW_NUMBER() OVER(ORDER BY A.")).append(orderText).append(" ").append(order).append(", ").append(orderText2).append(" ").append(order).append(", ").append(orderText3).append(" ").append(order).append(") RANK1, A.*, A.RNUM1 AS RNUM2 FROM ( ").toString());
            else
                str.append((new StringBuilder("SELECT ROW_NUMBER() OVER(ORDER BY A.")).append(orderText).append(" ").append(order).append(") RANK1, A.*, A.RNUM1 AS RNUM2 FROM ( ").toString());
        } else
        {
            str.append("SELECT ROW_NUMBER() OVER(ORDER BY A.first_modified desc ) RANK1, A.*, A.RNUM1 AS RNUM2 FROM ( ");
        }
        str.append("SELECT SERV.*, ROWNUM AS RNUM1 FROM sideeffect.SAFETY_REPORT_VIEW SERV    ");
        str.append(" INNER JOIN sideeffect.SAFETY_REPORT this_  ");
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
                str.append("  this_.COUNTRY_REPORTED_IN =   \n ");
                str.append("  (SELECT cri.id FROM sideeffect.COUNTRY_REPORTED_IN cri \n ");
                str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM sideeffect.JUNC_SAFETY_REPORT_TYPE2 jsert2 \n ");
                str.append("   JOIN sideeffect.REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.SAFETY_REPORT FROM sideeffect.JUNC_SAFETY_REPORTER_TYPES jsrt \n ");
                str.append("   JOIN sideeffect.REPORTER_TYPE RT ON jsrt.REPORTER_TYPE = RT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE RT.ID IN  (")).append(sv.get(i)).append(") ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
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
                str.append("   JOIN sideeffect.SGI_SE_COMPANY MC ON MI.COMPANY_ID = MC.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MC.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(MC.ENTP_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.ID MEB_ITEM_ID   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE MI.MEDDEV_ENTP_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   JOIN sgi_se_item_category ssic ON MI.item_category_number = ssic.id   \n ");
                if(arrayIdOrName.get(i).equals("ID"))
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
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE ssic.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.GRADE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("16"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEDDEV_ITEM_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("17"))
            {
                str.append("  this_.COUNTRY_MANUFACTURED_ID IN   \n ");
                str.append("  (SELECT CC.ID FROM sideeffect.COUNTRY_CODE CC \n ");
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
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   join sideeffect.safety_report_meb_type_info srmti ON MI.id = srmti.report_id \n ");
                str.append("   join sideeffect.MEB_TYPE_INFO mti on srmti.meddev_item_seq = mti.meddev_item_seq \n ");
                str.append((new StringBuilder("   WHERE UPPER(mti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.MEDDEV_ITEM_SEQ FROM sideeffect.MEB_ITEM MI \n ");
                str.append("   JOIN sideeffect.MEB_LOT_NO MLN ON MI.MEDDEV_ITEM_SEQ = MLN.MEDDEV_ITEM_SEQ \n ");
                str.append((new StringBuilder("   WHERE UPPER(MLN.LOT_NO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.safety_cause_content LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM sideeffect.JUNC_SAFETY_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN sideeffect.PATIENT_CONDITION PC ON JSEPC.CONDITION_ID = PC.ID \n ");
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
            if(sc.get(i).equals("24"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM sideeffect.JUNC_SAFETY_MEDICAL_CODE JSEMC \n ");
                str.append("   JOIN sideeffect.MEDICAL_DEVICE_MALFUNCTION_CODE MDMC ON JSEMC.MEDICAL_ID = MDMC.ID \n ");
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
            if(sc.get(i).equals("25"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM sideeffect.JUNC_SAFETY_COMPONENT_CODE JSEC \n ");
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
            if(sc.get(i).equals("26"))
                str.append((new StringBuilder("   UPPER(this_.followup) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSRT.REPORT_ID FROM sideeffect.JUNC_SAFETY_REPORT_TYPE JSRT \n ");
                str.append("   JOIN sideeffect.SIDEEFFECT_REPORT_TYPE SRT ON JSRT.REPORT_TYPE_ID = SRT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE SRT.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE SRT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.REPORT_STATUS IN   \n ");
                str.append("  (SELECT RS.ID FROM sideeffect.REPORT_STATUS RS \n ");
                str.append((new StringBuilder("   WHERE RS.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("29"))
                str.append((new StringBuilder("   UPPER(this_.EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("30"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        if(intSc == 0)
            str.append((new StringBuilder(" this_.REPORT_YEAR || this_.REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM sideeffect.SAFETY_REPORT_TYPE_DATE jsertd \n ");
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
        str.append(" ) AS A");
        if(pgSize > 0)
        {
            if(!paging.equals("n"))
            {
                str.append(" ) ");
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
        if(!orderText.equals(""))
            str.append((new StringBuilder(" order by ")).append(orderText).append(" ").append(order).toString());
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleSafetyReportView.class);
        List l = query.list();
        return l;
    }

    public long mebTypeInfoSelectAdd(SimpleSafetyReportMebTypeInfo simpleSafetyReportMebTypeInfo)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            result = ((Long)s.save(simpleSafetyReportMebTypeInfo)).longValue();
            result = result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long mebTypeInfoSelectDelete(int id)
    {
        long result = -1L;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            s.createQuery("DELETE FROM properties.SimpleSafetyReportMebTypeInfo WHERE report_id = ? ").setInteger(0, id).executeUpdate();
            result = 1L;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List ItemSafetyList(String classKorName, String meaClassNo, String cobFlag, String meddevItemNo, String reportType, String fmYY, String fmMM, 
            String toYY, String toMM, int pgSize, int startPg, String classKorNameEqual)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class, "safety");
        c.createCriteria("meb_item", "item").createCriteria("company", "company").createCriteria("item.mea_item", "item_category").createCriteria("item.cobFlagType", "cobflag").add(Restrictions.like("item_category.mea_class_no", (new StringBuilder("%")).append(meaClassNo).append("%").toString())).add(Restrictions.like("item.meddev_item_no", (new StringBuilder("%")).append(meddevItemNo).append("%").toString())).setProjection(Projections.projectionList().add(Projections.sqlProjection("sum( (select count(com.id) from sgi_se_item se_item join safety_report report on ( se_item.id = report.meb_item_id) join junc_safety_component_code com on (com.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt1 ", new String[] {
            "varCnt1"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt1").add(Projections.sqlProjection("sum( (select count(med.id) from sgi_se_item se_item join safety_report report on (se_item.id = report.meb_item_id) join junc_safety_medical_code med on (med.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt2 ", new String[] {
            "varCnt2"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt2").add(Projections.sqlProjection("sum( (select count(pat.id) from sgi_se_item se_item join safety_report report on (se_item.id = report.meb_item_id) join junc_safety_patient_condition pat on (pat.report_id = report.id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt3 ", new String[] {
            "varCnt3"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt3").add(Projections.sqlProjection("sum((select count(report.id) from sgi_se_item se_item join safety_report report on (se_item.id = report.meb_item_id) where se_item.id = item1_.id and report.id = this_.id) ) as varCnt ", new String[] {
            "varCnt"
        }, new Type[] {
            StandardBasicTypes.LONG
        }), "varCnt").add(Projections.groupProperty("safety.meb_item_id").as("meb_item_id")).add(Projections.groupProperty("item_category.id").as("varId")).add(Projections.groupProperty("item_category.mea_class_no").as("varValue1")).add(Projections.groupProperty("cobflag.propertyValue").as("varValue2")).add(Projections.groupProperty("item.meddev_item_no").as("varValue3")).add(Projections.groupProperty("item_category.class_kor_name").as("varValue4")).add(Projections.groupProperty("company.entp_name").as("varValue5"))).setResultTransformer(Transformers.aliasToBean(SimpleSafetyReport.class)).addOrder(Order.asc("item_category.class_kor_name"));
        if(classKorNameEqual.equals("Y"))
            c.add(Restrictions.eq("item_category.class_kor_name", classKorName));
        else
            c.add(Restrictions.like("item_category.class_kor_name", (new StringBuilder("%")).append(classKorName).append("%").toString()));
        if(pgSize > 0)
            c.setFirstResult(startPg).setMaxResults(pgSize);
        if(!cobFlag.equals("-1"))
            c.add(Restrictions.eq("item.cob_flag_code", cobFlag));
        if(!reportType.equals("0"))
            c.createCriteria("safetyReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date6_.report_year ||  date6_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date5_.report_year ||  date5_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        l = c.list();
        return l;
    }

    public long ItemSafetyCnt(String classKorName, String meaClassNo, String cobFlag, String meddevItemNo, String reportType, String fmYY, String fmMM, 
            String toYY, String toMM, String classKorNameEqual)
    {
        long totalCnt = 0L;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        c.createCriteria("meb_item", "item").createCriteria("company", "company").createCriteria("item.mea_item", "item_category").createCriteria("item.cobFlagType", "cobflag").setProjection(Projections.countDistinct("item.id")).add(Restrictions.like("item_category.mea_class_no", (new StringBuilder("%")).append(meaClassNo).append("%").toString())).add(Restrictions.like("item.meddev_item_no", (new StringBuilder("%")).append(meddevItemNo).append("%").toString()));
        if(classKorNameEqual.equals("Y"))
            c.add(Restrictions.eq("item_category.class_kor_name", classKorName));
        else
            c.add(Restrictions.like("item_category.class_kor_name", (new StringBuilder("%")).append(classKorName).append("%").toString()));
        if(!cobFlag.equals("-1"))
            c.add(Restrictions.eq("item.cob_flag_code", cobFlag));
        if(!reportType.equals("0"))
            c.createCriteria("safetyReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date6_.report_year ||  date6_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        else
            c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date5_.report_year ||  date5_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        totalCnt = ((Long)c.uniqueResult()).longValue();
        return totalCnt;
    }

    public List ItemSafetyCodeList(long itemId, String joinTable, String reportType, String fmYY, String fmMM, String toYY, 
            String toMM, String tabGB)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        if(tabGB.equals("I"))
        {
            c.createCriteria("meb_item", "item").add(Restrictions.eq("item.id", Long.valueOf(itemId)));
            if(!reportType.equals("0"))
                c.createCriteria("safetyReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
            else
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date2_.report_year ||  date2_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        } else
        {
            c.createCriteria("meb_item", "item").createCriteria("item.mea_item", "category").add(Restrictions.eq("category.id", Long.valueOf(itemId)));
            if(!reportType.equals("0"))
                c.createCriteria("safetyReportTypeDate", "reporttype", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("reporttype.report_type_id", Integer.valueOf(reportType))).createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date4_.report_year ||  date4_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
            else
                c.createCriteria("reportYM", "date").add(Restrictions.sqlRestriction((new StringBuilder(" date3_.report_year ||  date3_.report_month between ")).append(fmYY).append(fmMM).append(" and ").append(toYY).append(toMM).toString()));
        }
        c.createCriteria(joinTable, "code").setProjection(Projections.projectionList().add(Projections.groupProperty("code.id").as("varId")).add(Projections.groupProperty("code.fdaCode").as("varValue1")).add(Projections.groupProperty("code.fdaSourcePtKor").as("varValue2")).add(Projections.groupProperty("code.name").as("varValue3")).add(Projections.groupProperty("code.parentId")).add(Projections.groupProperty("code.depthLevel")).add(Projections.count("code.id").as("varCnt"))).setResultTransformer(Transformers.aliasToBean(SimpleSafetyReport.class)).addOrder(Order.asc("code.parentId")).addOrder(Order.asc("code.depthLevel"));
        l = c.list();
        return l;
    }

    public BigDecimal reportCount(SimpleSafetyReportView report, List sc, List sv, List logicalOperator, List arrayIdOrName, int intSc, String year1, 
            String year2, String month1, String month2, String PopYN, int pgSize, int firstResult)
    {
        StringBuffer str = new StringBuffer();
        str.append("SELECT COUNT(this_.ID) FROM  ");
        str.append(" sideeffect.SAFETY_REPORT this_ WHERE 1=1 AND  ");
        String strLogicalOperator = "";
        for(int i = 0; i < sc.size(); i++)
        {
            if(i == sc.size() - 1)
                strLogicalOperator = "AND";
            else
                strLogicalOperator = logicalOperator.get(i).toString();
            if(sc.get(i).equals("1"))
            {
                str.append("  this_.COUNTRY_REPORTED_IN =   \n ");
                str.append("  (SELECT cri.id FROM sideeffect.COUNTRY_REPORTED_IN cri \n ");
                str.append((new StringBuilder("   WHERE cri.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase().toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("2"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsert2.REPORT_ID FROM sideeffect.JUNC_SAFETY_REPORT_TYPE2 jsert2 \n ");
                str.append("   JOIN sideeffect.REPORT_TYPE RT ON jsert2.REPORT_TYPE_ID = RT.ID  \n ");
                str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("3"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT jsrt.SAFETY_REPORT FROM sideeffect.JUNC_SAFETY_REPORTER_TYPES jsrt \n ");
                str.append("   JOIN sideeffect.REPORTER_TYPE RT ON jsrt.REPORTER_TYPE = RT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE RT.ID IN (")).append(sv.get(i)).append(") ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE RT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("4"))
                str.append((new StringBuilder("   UPPER(this_.COMPANY_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("5"))
                str.append((new StringBuilder("   this_.REPRESENTATIVE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
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
                str.append("   JOIN sideeffect.SGI_SE_COMPANY MC ON MI.COMPANY_ID = MC.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MC.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE UPPER(MC.ENTP_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("12"))
            {
                str.append("  this_.ID MEB_ITEM_ID   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append((new StringBuilder("   WHERE MI.MEDDEV_ENTP_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("13"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   JOIN sgi_se_item_category ssic ON MI.item_category_number = ssic.id   \n ");
                if(arrayIdOrName.get(i).equals("ID"))
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
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE ssic.GRADE = '")).append(sv.get(i).toString().toUpperCase()).append("' ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE ssic.GRADE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("16"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE MI.ID = ")).append(sv.get(i)).append(" ) ").append(strLogicalOperator).append(" \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE MI.MEDDEV_ITEM_NO LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("17"))
            {
                str.append("  this_.COUNTRY_MANUFACTURED_ID IN   \n ");
                str.append("  (SELECT CC.ID FROM sideeffect.COUNTRY_CODE CC \n ");
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
                str.append("  (SELECT MI.id FROM sideeffect.sgi_se_item MI \n ");
                str.append("   join sideeffect.safety_report_meb_type_info srmti ON MI.id = srmti.report_id \n ");
                str.append("   join sideeffect.MEB_TYPE_INFO mti on srmti.meddev_item_seq = mti.meddev_item_seq \n ");
                str.append((new StringBuilder("   WHERE UPPER(mti.TYPE_NAME) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("21"))
            {
                str.append("  this_.MEB_ITEM_ID IN   \n ");
                str.append("  (SELECT MI.MEDDEV_ITEM_SEQ FROM sideeffect.MEB_ITEM MI \n ");
                str.append("   JOIN sideeffect.MEB_LOT_NO MLN ON MI.MEDDEV_ITEM_SEQ = MLN.MEDDEV_ITEM_SEQ \n ");
                str.append((new StringBuilder("   WHERE UPPER(MLN.LOT_NO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("22"))
                str.append((new StringBuilder("   this_.safety_cause_content LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("23"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEPC.REPORT_ID FROM sideeffect.JUNC_SAFETY_PATIENT_CONDITION JSEPC \n ");
                str.append("   JOIN sideeffect.PATIENT_CONDITION PC ON JSEPC.CONDITION_ID = PC.ID \n ");
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
            if(sc.get(i).equals("24"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEMC.REPORT_ID FROM sideeffect.JUNC_SAFETY_MEDICAL_CODE JSEMC \n ");
                str.append("   JOIN sideeffect.MEDICAL_DEVICE_MALFUNCTION_CODE MDMC ON JSEMC.MEDICAL_ID = MDMC.ID \n ");
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
            if(sc.get(i).equals("25"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSEC.REPORT_ID FROM sideeffect.JUNC_SAFETY_COMPONENT_CODE JSEC \n ");
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
            if(sc.get(i).equals("26"))
                str.append((new StringBuilder("   UPPER(this_.followup) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("27"))
            {
                str.append("  this_.ID IN   \n ");
                str.append("  (SELECT JSRT.REPORT_ID FROM sideeffect.JUNC_SAFETY_REPORT_TYPE JSRT \n ");
                str.append("   JOIN sideeffect.SIDEEFFECT_REPORT_TYPE SRT ON JSRT.REPORT_TYPE_ID = SRT.ID  \n ");
                if(arrayIdOrName.get(i).equals("ID"))
                    str.append((new StringBuilder("   WHERE SRT.ID =")).append(sv.get(i).toString().toUpperCase()).append(" ) ").append(strLogicalOperator).append("  \n ").toString());
                else
                    str.append((new StringBuilder("   WHERE SRT.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("28"))
            {
                str.append("  this_.REPORT_STATUS IN   \n ");
                str.append("  (SELECT RS.ID FROM sideeffect.REPORT_STATUS RS \n ");
                str.append((new StringBuilder("   WHERE RS.PROPERTY_VALUE LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%' ) ").append(strLogicalOperator).append("  \n ").toString());
            } else
            if(sc.get(i).equals("29"))
                str.append((new StringBuilder("   UPPER(this_.EXTRA_INFO) LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
            else
            if(sc.get(i).equals("30"))
                str.append((new StringBuilder("   this_.DOCUMENT_NUMBER LIKE '%")).append(sv.get(i).toString().toUpperCase()).append("%'  ").append(strLogicalOperator).append("  \n ").toString());
        }

        str.append(" 1=1 and \n ");
        if(intSc == 0)
            str.append((new StringBuilder(" this_.REPORT_YEAR || this_.REPORT_MONTH BETWEEN '")).append(year1).append(month1).append("' AND '").append(year2).append(month2).append("' \n ").toString());
        if(intSc != 0 && intSc != 4)
        {
            str.append(" this_.ID in (SELECT jsertd.REPORT_ID \n ");
            str.append(" FROM sideeffect.SAFETY_REPORT_TYPE_DATE jsertd \n ");
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
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString());
        BigDecimal result = null;
        result = (BigDecimal)query.uniqueResult();
        return result;
    }

    public List safetyCntStep(String stepCodeArr[][], String fmDate, String toDate, String reportType, Integer curStep)
    {
        List l = null;
        Integer num = Integer.valueOf(0);
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSafetyReport.class, "report");
        if(reportType.equals("0"))
        {
            c.add(Restrictions.sqlRestriction((new StringBuilder(" this_.report_year ||  this_.report_month between ")).append(fmDate).append(" and ").append(toDate).toString()));
        } else
        {
            num = Integer.valueOf(num.intValue() + 1);
            c.createCriteria("safetyReportTypeDate", "typedate").add(Restrictions.sqlRestriction((new StringBuilder(" typedate")).append(num).append("_.report_year ||  typedate").append(num).append("_.report_month between ").append(fmDate).append(" and ").append(toDate).toString())).add(Restrictions.eq("report_type_id", Integer.valueOf(Integer.parseInt(reportType))));
        }
        for(int a = 0; a <= curStep.intValue(); a++)
        {
            String joinTable = stepCodeArr[a][1];
            if(!joinTable.equals("0"))
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

                    System.out.println((new StringBuilder("mebItem===")).append(mebItem).toString());
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

    private SessionFactory sessionFactory;
}

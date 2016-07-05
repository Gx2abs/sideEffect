// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryDAO.java

package safety.renewal.sgi.category;

import abstraction.IDValuePair;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import safety.renewal.company.Company1;

// Referenced classes of package safety.renewal.sgi.category:
//            ItemCategoryDAO, SimpleItemCategory

public class SimpleItemCategoryDAO
    implements ItemCategoryDAO
{

    public SimpleItemCategoryDAO()
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

    public List list()
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleItemCategory.class);
        l = c.list();
        return l;
    }

    public List namedList()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("testQuery2").list();
    }

    public List namedList2()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("testQuery21").list();
    }

    public List getManufImportEntpNameList(int pg, int limit)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategory.class);
        c.createCriteria("company", JoinType.LEFT_OUTER_JOIN);
        c.setMaxResults(10);
        c.setFirstResult(limit - 10);
        List l = c.list();
        return l;
    }

    public List manufImportEntpNameList(int pg, int limit)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategory.class).setMaxResults(10).setFirstResult(limit);
        List l = c.list();
        return l;
    }

    public List manufImportEntpNameSearch1(int pg, int limit, String sv)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategory.class);
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.setMaxResults(limit);
        c.setFirstResult(limit - 10);
        System.out.println((new StringBuilder("[SimpleMeb_itemDelegate].manufImportEntpNameSearch.query  ")).append(limit).toString());
        List l = c.list();
        return l;
    }

    public List manufImportEntpNameSearch2(int pg, int limit, String sv, String sv2)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategory.class);
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.add(Restrictions.like("item_name", (new StringBuilder("%")).append(sv2).append("%").toString()));
        c.setMaxResults(limit);
        c.setFirstResult(limit - 10);
        System.out.println("[SimpleMeb_itemDelegate].manufImportEntpNameSearch2.query");
        List l = c.list();
        return l;
    }

    public List manufImportEntpNameSearch3(int pg, int limit, String sv, String sv2, String sv3)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategory.class);
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.add(Restrictions.like("item_name", (new StringBuilder("%")).append(sv2).append("%").toString()));
        c.setMaxResults(limit);
        c.setFirstResult(limit - 10);
        System.out.println("[SimpleMeb_itemDelegate].manufImportEntpNameSearch3.query");
        List l = c.list();
        return l;
    }

    public List sideeffectMeaCompany(String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, String toMM, 
            String orderColName, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Company1.class);
        if(!reportType.equals("0"))
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join SIDE_EFFECT_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
        else
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString()));
        c.addOrder(Order.asc(orderColName));
        l = c.list();
        return l;
    }

    public List safetyMeaCompany(String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, String toMM, 
            String orderColName, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Company1.class);
        if(!reportType.equals("0"))
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join SAFETY_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
        else
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString()));
        c.addOrder(Order.asc(orderColName));
        l = c.list();
        return l;
    }

    public List sideeffectMeaClassNo(String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, String toMM, 
            String orderColName, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleItemCategory.class);
        if(!reportType.equals("0"))
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join SIDE_EFFECT_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
        else
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString()));
        c.addOrder(Order.asc(orderColName));
        l = c.list();
        return l;
    }

    public List safetyMeaClassNo(String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, String toMM, 
            String orderColName, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleItemCategory.class);
        if(!reportType.equals("0"))
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join SAFETY_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
        else
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString()));
        c.addOrder(Order.asc(orderColName));
        l = c.list();
        return l;
    }

    public long getManufCount(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long manufCount(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long manufCount1(IDValuePair t, String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long manufCount2(IDValuePair t, String sv, String sv2)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.add(Restrictions.like("item_name", (new StringBuilder("%")).append(sv2).append("%").toString()));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long manufCount3(IDValuePair t, String sv, String sv2, String sv3)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.add(Restrictions.like("item_name", (new StringBuilder("%")).append(sv2).append("%").toString()));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List list(IDValuePair t)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        l = c.list();
        return l;
    }

    public List list(Class t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Criteria c = session.createCriteria(t);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimpleItemCategoryDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List list(Class t)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t);
        l = c.list();
        return l;
    }

    public IDValuePair readCompany(Class clas, long id)
    {
        return null;
    }

    public IDValuePair readProduct(Class clas, long id)
    {
        return null;
    }

    public IDValuePair create(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    public IDValuePair update(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return t;
    }

    public boolean delete(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(t);
        return true;
    }

    public long count(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setProjection(Projections.count("id"));
        Example e = Example.create(t);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public IDValuePair merge(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.merge(t);
        return t;
    }

    public SimpleItemCategory getParent(long parent_id)
    {
        SimpleItemCategory found = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from sgi_se_item_category where  ");
        sb.append("\n id = :parent  ");
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleItemCategory.class);
        q.setParameter("parent", Long.valueOf(parent_id));
        found = (SimpleItemCategory)q.uniqueResult();
        return found;
    }

    public IDValuePair read(Class clas, long id)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        long intId = l.intValue();
        c.add(Restrictions.eq("id", Long.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
    }

    public int clearJunction(String table, String masterColumn, long masterId)
    {
        int affected = -1;
        Session session = sessionFactory.getCurrentSession();
        String queryString = (new StringBuilder("DELETE FROM ")).append(table).append(" where ").append(masterColumn).append(" = :masterId").toString();
        Query query = session.createSQLQuery(queryString);
        query.setParameter("masterId", Long.valueOf(masterId));
        affected = query.executeUpdate();
        return affected;
    }

    public List list(IDValuePair t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Criteria c = session.createCriteria(t.getClass());
            Example e = Example.create(t);
            e.enableLike(MatchMode.ANYWHERE);
            e.excludeZeroes();
            e.ignoreCase();
            c.add(e);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimpleItemCategoryDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List listItemCategoryNo(SimpleItemCategory target, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from sgi_se_item_category where class_level= :class_level");
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleItemCategory.class);
        q.setParameter("class_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public Object create(Object obj, boolean isGeneral)
    {
        Session session = sessionFactory.getCurrentSession();
        obj = session.save(obj);
        return obj;
    }

    public List listMeaClassNo(int parent_id, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from sgi_se_item_category where class_level= :class_level");
        sb.append("\n and parent_id = :parent_id");
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleItemCategory.class);
        q.setParameter("class_level", Integer.valueOf(level));
        q.setParameter("parent_id", Integer.valueOf(parent_id));
        list = q.list();
        return list;
    }

    public IDValuePair readMeaClassNo(SimpleItemCategory target)
    {
        String signature = "SimpleItemCategoryDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(target.getGrade()).append(" / ").append(target.getMea_class_no()).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        c.add(Restrictions.eq("grade", target.getGrade()));
        c.add(Restrictions.like("mea_class_no", target.getMea_class_no()));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        System.out.println((new StringBuilder("found : ")).append(found).toString());
        return found;
    }

    private SessionFactory sessionFactory;
}

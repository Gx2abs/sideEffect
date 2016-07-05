// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMeb_itemDAO.java

package foreign;

import abstraction.IDValuePair;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import safety.renewal.company.SimpleCompany1;
import safety.renewal.sgi.item.SimpleItem1;

// Referenced classes of package foreign:
//            Meb_itemDAO, Meb_item, Mea_company, Mea_class_no

public class SimpleMeb_itemDAO
    implements Meb_itemDAO
{

    public SimpleMeb_itemDAO()
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
        Criteria c = session.createCriteria(Meb_item.class);
        l = c.list();
        return l;
    }

    public List namedList()
    {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }

    public List namedList2()
    {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }

    public List getManufImportEntpNameList(int pg, int limit)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleCompany1.class);
        c.setMaxResults(10);
        c.setFirstResult(limit - 10);
        c.addOrder(Order.asc("entp_name"));
        List l = c.list();
        return l;
    }

    public List entpNameSearchList(int pg, int limit, String sv)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleCompany1.class);
        c.add(Restrictions.like("entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.setMaxResults(10);
        c.setFirstResult(limit - 10);
        c.addOrder(Order.asc("entp_name"));
        List l = c.list();
        return l;
    }

    public List search(int pg, int limit, int itemSeq, String sv3)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItem1.class);
        c.add(Restrictions.eq("id", Long.valueOf(itemSeq)));
        c.createCriteria("item_type", "item_type_", JoinType.INNER_JOIN);
        if(sv3.length() > 0)
            c.add(Restrictions.like("item_type_.type_name", (new StringBuilder("%")).append(sv3).append("%").toString()));
        c.setMaxResults(10);
        c.setFirstResult(limit - 10);
        List l = c.list();
        return l;
    }

    public List manufImportEntpNameList(int pg, int limit)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(Meb_item.class).setMaxResults(10).setFirstResult(limit);
        List l = c.list();
        return l;
    }

    public List manufImportEntpNameSearch1(int pg, int limit, String sv)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(Meb_item.class);
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
        Criteria c = s.createCriteria(Meb_item.class);
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
        Criteria c = s.createCriteria(Meb_item.class);
        c.createCriteria("company", "company1_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("company1_.entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.add(Restrictions.like("item_name", (new StringBuilder("%")).append(sv2).append("%").toString()));
        c.setMaxResults(limit);
        c.setFirstResult(limit - 10);
        System.out.println("[SimpleMeb_itemDelegate].manufImportEntpNameSearch3.query");
        List l = c.list();
        return l;
    }

    public List listPopupMeaCompany(String search)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Mea_company.class).add(Restrictions.disjunction().add(Restrictions.like("entp_name", search))).addOrder(Order.asc("meddev_entp_seq"));
        l = c.list();
        return l;
    }

    public List listPopupMeaClassNo(String search)
    {
        List l = null;
        StringBuffer str = new StringBuffer();
        str.append("SELECT this_.MEA_CLASS_NO, this_.GRADE, this_.HIGHER_CLASS_NO, this_.CLASS_LEVEL, this_.CLASS_KOR_NAME, this_.CLASS_ENG_NAME, this_.CLASS_CONT,    ");
        str.append(" this_.ITEM_GRP_CODE, this_.REGIST_TS, this_.REGIST_ID, this_.UPDATE_TS, this_.UPDATE_ID, this_.TRACE_MANAGE_TARGET_YN, ");
        str.append(" this_.CODE_AGE, this_.TRACEABILITY, this_.IS_IN_USE, this_.HIGHER_CLASS_GRADE, this_.OLDER_CLASS_NO, this_.UDI_CODE, this_.USE_YN, ");
        str.append(" CASE WHEN this_.CLASS_LEVEL = '1' THEN this_.MEA_CLASS_NO ");
        str.append(" WHEN this_.CLASS_LEVEL = '2' THEN (SELECT MIN(MEA_CLASS_NO) FROM SIDEEFFECT.MEA_CLASS_NO WHERE MEA_CLASS_NO = this_.HIGHER_CLASS_NO AND HIGHER_CLASS_NO = this_.HIGHER_CLASS_NO ) ");
        str.append(" WHEN this_.CLASS_LEVEL = '3' THEN (SELECT MIN(MEA_CLASS_NO) FROM SIDEEFFECT.MEA_CLASS_NO WHERE MEA_CLASS_NO = this_.HIGHER_CLASS_NO AND HIGHER_CLASS_NO = this_.HIGHER_CLASS_NO ) ");
        str.append(" END AS HIGHER_CLASS_NO ");
        str.append(" FROM MEA_CLASS_NO this_");
        str.append(" WHERE this_.IS_IN_USE = 1 ");
        str.append((new StringBuilder(" AND ( this_.CLASS_KOR_NAME LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or UPPER(this_.CLASS_ENG_NAME) LIKE UPPER('")).append(search).append("')").toString());
        str.append((new StringBuilder(" or this_.MEA_CLASS_NO LIKE '")).append(search).append("' ) ").toString());
        str.append(" ORDER BY this_.HIGHER_CLASS_NO, this_.CLASS_LEVEL, this_.MEA_CLASS_NO ");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(Mea_class_no.class);
        l = query.list();
        return l;
    }

    public long entpNameSearchCount(IDValuePair t, String sv)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.add(Restrictions.like("entp_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long searchCount(IDValuePair t, String sv3, int itemSeq)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.add(Restrictions.eq("id", Long.valueOf(itemSeq)));
        c.createCriteria("item_type", "item_type_", JoinType.INNER_JOIN);
        if(sv3.length() > 0)
            c.add(Restrictions.like("item_type_.type_name", (new StringBuilder("%")).append(sv3).append("%").toString()));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long getManufCount(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
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

    public long item_listCount(IDValuePair t, int pg, int limit, String sv, int seq, String entp_name)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.add(Restrictions.eq("company_id", Integer.valueOf(seq)));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List item_list(int pg, int limit, String sv, int seq, String entp_name)
    {
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItem1.class);
        c.add(Restrictions.eq("company_id", Integer.valueOf(seq)));
        c.setMaxResults(10);
        c.setFirstResult(limit - 10);
        List l = null;
        l = c.list();
        return l;
    }

    public long item_searchCount(IDValuePair t, int pg, int limit, String sv, String sv2, int seq, String entp_name)
    {
        System.out.println((new StringBuilder("[SimpleMeb_itemDAO].item_searchCount().sv2 : ")).append(sv2).toString());
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(t.getClass());
        c.add(Restrictions.eq("company_id", Integer.valueOf(seq)));
        c.createCriteria("mea_item", "mea_item_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("mea_item_.class_kor_name", (new StringBuilder("%")).append(sv2).append("%").toString()));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List item_search(int pg, int limit, String sv, int seq, String entp_name)
    {
        System.out.println((new StringBuilder("[SimpleMeb_itemDAO].item_search().seq : ")).append(seq).toString());
        System.out.println((new StringBuilder("[SimpleMeb_itemDAO].item_search().sv : ")).append(sv).toString());
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItem1.class);
        c.add(Restrictions.eq("company_id", Integer.valueOf(seq)));
        c.createCriteria("mea_item", "mea_item_", JoinType.LEFT_OUTER_JOIN);
        c.add(Restrictions.like("mea_item_.class_kor_name", (new StringBuilder("%")).append(sv).append("%").toString()));
        List l = null;
        l = c.list();
        return l;
    }

    private SessionFactory sessionFactory;
}

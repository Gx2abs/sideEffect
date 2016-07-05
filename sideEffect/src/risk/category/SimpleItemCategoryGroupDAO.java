// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryGroupDAO.java

package risk.category;

import abstraction.IDValuePair;
import foreign.Meb_item;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package risk.category:
//            ItemCategoryGroupDAO, SimpleItemCategoryGroup, SimpleItemCategoryGroupBack

public class SimpleItemCategoryGroupDAO
    implements ItemCategoryGroupDAO
{

    public SimpleItemCategoryGroupDAO()
    {
    }

    private Session getCurrentSession()
    {
        Session session = null;
        try
        {
            System.out.println("getCurrentSession");
            session = sessionFactory.getCurrentSession();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(session == null)
        {
            System.out.println("openSession");
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public IDValuePair read(Class clas, long id)
    {
        String signature = "SimpleItemCategoryGroupDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        long intId = l.intValue();
        c.add(Restrictions.eq("id", Long.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
    }

    public IDValuePair merge(IDValuePair t)
    {
        return null;
    }

    public IDValuePair create(IDValuePair t)
    {
        Session session = getCurrentSession();
        session.save(t);
        return t;
    }

    public Object create(Object t)
    {
        Session session = getCurrentSession();
        session.save(t);
        return t;
    }

    public IDValuePair update(IDValuePair t)
    {
        Session session = getCurrentSession();
        session.update(t);
        return t;
    }

    public Object update(Object t)
    {
        Session session = getCurrentSession();
        session.update(t);
        return t;
    }

    public boolean delete(IDValuePair t)
    {
        Session session = getCurrentSession();
        session.delete(t);
        return true;
    }

    public boolean delete(Object t)
    {
        Session session = getCurrentSession();
        session.delete(t);
        return true;
    }

    public List list(IDValuePair t)
    {
        String signature = "SimpleItemCategoryGroupDAO.list(Class)";
        List l = null;
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setMaxResults(10);
        c.setFirstResult(1);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Object t)
    {
        String signature = "SimpleItemCategoryGroupDAO.list(Class)";
        List l = null;
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Class t)
    {
        String signature = "SimpleItemCategoryGroupDAO.list(Class)";
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(t.getName()).toString());
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(t);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Meb_item meb_item)
    {
        String signature = "SimpleItemCategoryGroupDAO.list(Class)";
        Class target = null;
        List l = null;
        return l;
    }

    public List list(Class t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = getCurrentSession();
            Criteria c = session.createCriteria(t);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimpleItemCategoryGroupDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List list(IDValuePair t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = getCurrentSession();
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
            System.out.println("SimpleItemCategoryGroupDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public long count(IDValuePair t)
    {
        Session session = getCurrentSession();
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

    public long count(Object t)
    {
        Session session = getCurrentSession();
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

    public int clearJunction(String table, String masterColumn, long masterId)
    {
        int affected = -1;
        Session session = getCurrentSession();
        String queryString = (new StringBuilder("DELETE FROM ")).append(table).append(" where ").append(masterColumn).append(" = :masterId").toString();
        Query query = session.createSQLQuery(queryString);
        query.setParameter("masterId", Long.valueOf(masterId));
        affected = query.executeUpdate();
        return affected;
    }

    public IDValuePair readCompany(Class clas, long id)
    {
        return null;
    }

    public IDValuePair readProduct(Class clas, long id)
    {
        return null;
    }

    public List ItemCateogoryList(Class t, int page, int maxResults, String searchColumn, String searchKeyword)
    {
        StringBuffer str = new StringBuffer();
        List l = null;
        Session session = getCurrentSession();
        searchColumn = searchColumn.substring(3);
        str.append("SELECT * FROM  \n");
        str.append("( \n");
        str.append("\tSELECT A.*, ROWNUM AS RNUM, FLOOR((ROWNUM-1)/:maxResults+1) AS PAGE, COUNT(*) OVER() AS TOTCNT FROM \n");
        str.append("\t( \n");
        str.append("\t\tSELECT CATEGORY.ID, CATEGORY.GRADE, CATEGORY.CLASS_LEVEL, CATEGORY.MEA_CLASS_NO, CATEGORY.CLASS_KOR_NAME, CATEGORY.CLASS_ENG_NAME, \n");
        str.append("\t\tCATEGORY.CLASS_CONT, CATEGORY.UPDATE_TS, CATEGORY.REGIST_TS, CATEGORY.CODE_AGE, CATEGORY.TRACEABILITY, CATEGORY.IS_IN_USE, CATEGORY.IS_FROM_EXCEL, \n");
        str.append("\t\t( \n");
        str.append("\t\t\t(SELECT PROPERTY_VALUE \n");
        str.append("\t\t\tFROM JUNC_ITEM_CATEGORY_GROUP AS JUNC INNER JOIN ITEM_CATEGORY_GROUP AS CGROUP \n");
        str.append("\t\t\t\tON JUNC.group_id = CGROUP.id \n");
        str.append("\t\t\tWHERE JUNC.CATEGORY_ID = CATEGORY.id) \n");
        str.append("\t\t) AS UDI_CODE, \n");
        str.append("\t\t( \n");
        str.append("\t\t\t(SELECT ID \n");
        str.append("\t\t\tFROM JUNC_ITEM_CATEGORY_GROUP AS JUNC INNER JOIN ITEM_CATEGORY_GROUP AS CGROUP \n");
        str.append("\t\t\t\tON JUNC.group_id = CGROUP.id \n");
        str.append("\t\t\tWHERE JUNC.CATEGORY_ID = CATEGORY.id) \n");
        str.append("\t\t) AS PARENT_ID \n");
        str.append("\t\tFROM SGI_SE_ITEM_CATEGORY as CATEGORY \n");
        str.append((new StringBuilder("\t\tWHERE ")).append(searchColumn).append(" like '%").append(searchKeyword).append("%' \n").toString());
        str.append("\t\tORDER BY CATEGORY.ID DESC \n");
        str.append("    ) A \n");
        str.append(") WHERE PAGE = :page \n");
        Query query = session.createSQLQuery(str.toString()).addEntity(t).setParameter("page", Integer.valueOf(page)).setParameter("maxResults", Integer.valueOf(maxResults));
        l = query.list();
        return l;
    }

    public boolean chechkType(Class clas)
    {
        boolean check = false;
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(clas);
        c.add(Restrictions.eq("type", Integer.valueOf(1)));
        Object found = c.uniqueResult();
        if(found != null)
            check = true;
        return check;
    }

    public List backupList(Object t, long articleId)
    {
        List l = null;
        try
        {
            Session session = getCurrentSession();
            Criteria c = session.createCriteria(t.getClass());
            Example e = Example.create(t);
            e.enableLike(MatchMode.ANYWHERE);
            e.excludeZeroes();
            e.ignoreCase();
            c.add(e);
            c.setFirstResult(0);
            c.setMaxResults(1000);
            c.add(Restrictions.eq("group_id", Long.valueOf(articleId)));
            c.addOrder(Order.desc("startDate"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimpleItemCategoryGroupDAO.backupList(Class, id).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public Object readBack(Class clas, long id)
    {
        String signature = "SimpleItemCategoryGroupDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        long intId = l.intValue();
        c.add(Restrictions.eq("id", Long.valueOf(intId)));
        Object found = c.uniqueResult();
        return found;
    }

    public boolean startDateCheck(Object t, long articleId)
    {
        boolean result = false;
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setProjection(Projections.count("id"));
        c.add(Restrictions.ne("id", Long.valueOf(articleId)));
        Example e = Example.create(t);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        long count = ((Long)c.uniqueResult()).longValue();
        if(count == 0L)
            result = true;
        return result;
    }

    public long itemCategoryGroupId(long itemCategorySeq)
    {
        SimpleItemCategoryGroup itemCategoryGroup = new SimpleItemCategoryGroup();
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategoryGroup.class).createAlias("itemCategoryGroup", "itemCategory").add(Restrictions.eq("itemCategory.id", Long.valueOf(itemCategorySeq)));
        itemCategoryGroup = (SimpleItemCategoryGroup)c.uniqueResult();
        if(itemCategoryGroup == null)
        {
            itemCategoryGroup = new SimpleItemCategoryGroup();
            Criteria c_sub = s.createCriteria(SimpleItemCategoryGroup.class).add(Restrictions.eq("type", Integer.valueOf(1)));
            itemCategoryGroup = (SimpleItemCategoryGroup)c_sub.uniqueResult();
        }
        return itemCategoryGroup.getId();
    }

    public List groupBackList(String fmDate, String toDate, long groupSeq)
    {
        List groupBackList = null;
        Session s = sessionFactory.getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategoryGroupBack.class, "groupbk").add(Restrictions.eq("group_id", Long.valueOf(groupSeq))).add(Restrictions.sqlRestriction((new StringBuilder(" START_DATE BETWEEN (SELECT MAX(START_DATE) FROM ITEM_CATEGORY_GROUP_BACK WHERE START_DATE <= '")).append(fmDate).append("01' AND \tGROUP_ID = this_.group_id) ").append(" AND TO_CHAR(LAST_DAY('").append(toDate).append("01").append("'),'yyyymmdd')  ").toString()));
        groupBackList = c.list();
        return groupBackList;
    }

    public List groupLatestBackList(String fmDate, String toDate, long groupSeq)
    {
        Session session = sessionFactory.getCurrentSession();
        StringBuffer str = new StringBuffer();
        str.append("SELECT * \n");
        str.append("FROM ( \n");
        str.append("      SELECT ROW_NUMBER() OVER(ORDER BY start_date DESC) as arank1, \n");
        str.append("             id, \n");
        str.append("             revision_name, \n");
        str.append("             start_date, \n");
        str.append("             group_id \n");
        str.append("      FROM ITEM_CATEGORY_GROUP_BACK \n");
        str.append((new StringBuilder("      WHERE group_id = ")).append(groupSeq).append(" \n").toString());
        str.append((new StringBuilder("      AND start_date BETWEEN '")).append(fmDate).append("01' AND TO_CHAR(LAST_DAY('").append(toDate).append("01'), 'yyyymmdd') \n").toString());
        str.append("     ) as a \n");
        str.append("WHERE a.arank1=1 \n");
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleItemCategoryGroupBack.class);
        List groupBackList = query.list();
        System.out.println((new StringBuilder("[SimpleItemCategoryGroupDAO].groupLatestBackList().groupSeq ")).append(groupSeq).toString());
        System.out.println((new StringBuilder("[SimpleItemCategoryGroupDAO].groupLatestBackList().fmDate ")).append(fmDate).toString());
        System.out.println((new StringBuilder("[SimpleItemCategoryGroupDAO].groupLatestBackList().toDate ")).append(toDate).toString());
        if(groupBackList.size() == 0)
            groupBackList = null;
        if(groupBackList == null)
        {
            query = null;
            groupBackList = null;
            str = new StringBuffer();
            str.append("SELECT * \n");
            str.append("FROM ( \n");
            str.append("       SELECT ROW_NUMBER() OVER(ORDER BY start_date DESC) as arank1,  \n");
            str.append("              id, \n");
            str.append("              revision_name, \n");
            str.append("              start_date, \n");
            str.append("              group_id \n");
            str.append("       FROM ITEM_CATEGORY_GROUP_BACK \n");
            str.append((new StringBuilder("       WHERE GROUP_ID = ")).append(groupSeq).append(" \n").toString());
            str.append("     ) as a \n");
            str.append("WHERE a.arank1=1 \n");
            query = session.createSQLQuery(str.toString()).addEntity(SimpleItemCategoryGroupBack.class);
            groupBackList = query.list();
        }
        System.out.println("[SimpleItemCategoryGroupDAO].groupLatestBackList().query");
        return groupBackList;
    }

    public Long getCategoryType(long groupId)
    {
        System.out.println("[SimpleItemCategoryGroupDAO].getCategoryType()");
        Long type = null;
        SimpleItemCategoryGroup categoryGrup = null;
        Session s = getCurrentSession();
        Criteria c = s.createCriteria(SimpleItemCategoryGroup.class);
        c.add(Restrictions.eq("id", Long.valueOf(groupId)));
        categoryGrup = (SimpleItemCategoryGroup)c.uniqueResult();
        if(categoryGrup == null)
            type = Long.valueOf(-1L);
        else
            type = Long.valueOf(categoryGrup.getType());
        return type;
    }

    private SessionFactory sessionFactory;
}

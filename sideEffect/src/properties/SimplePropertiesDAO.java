// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimplePropertiesDAO.java

package properties;

import abstraction.IDValuePair;
import foreign.*;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import kr.co.sgis.legacy.common.DBConnection;
import org.hibernate.*;
import org.hibernate.criterion.*;
import properties.company.SimpleCompany;
import properties.item.SimpleItemCodeType;
import properties.item.SimpleTraceability;
import properties.product.SimpleProduct;
import properties.report.type.SimpleReportType;
import properties.report.type.SimpleReportTypeHistory;
import report.SimpleSideeffectReport;
import safety.SimpleSafetyReport;
import safety.renewal.sgi.category.SimpleItemCategory;
import sideeffect.JuncSideEffectResult;
import sideeffect.SimpleSideeffectResult;

// Referenced classes of package properties:
//            PropertiesDAO, SimpleReporterType, SimpleIsInUse, SimplePatientCondition, 
//            SimpleMedicalDeviceMalfunctionCode, SimpleComponentCode, ReporterType

public class SimplePropertiesDAO
    implements PropertiesDAO
{
	
	public enum Property{
		SIMPLEREPORTERTYPE, SIMPLEREPORTTYPE, SIMPLECOUNTRYREPORTEDIN, SIMPLESIDEEFFECTSEVERITY, SIMPLECAUSALITY, SIMPLECAUSEOFSIDEEFFECT, SIMPLEGENDER, SIMPLECOUNTRYMANUFACTUREDIN
	}

    public SimplePropertiesDAO()
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

    public List typedList(ReporterType type)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleReporterType.class);
        l = c.list();
        return l;
    }

    public IDValuePair create(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    public IDValuePair update(IDValuePair t)
    {
        System.out.println("[SimplePropertiesDAO].update");
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

    public List list(IDValuePair t)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        List l = null;
        Session session = sessionFactory.getCurrentSession();
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

    public List list(Class t)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(t.getName()).toString());
        Session session = sessionFactory.getCurrentSession();
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
        String signature = "SimplePropertiesDAO.list(Class)";
        Class target = null;
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(Meb_item.class.getName()).toString());
        Property aproperty[];
        int j = (aproperty = Property.values()).length;
        for(int i = 0; i < j; i++)
        {
            Property p = aproperty[i];
            p.name().equalsIgnoreCase(Meb_item.class.getName());
            target = Meb_item.class;
        }

        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Class t, int page, int maxResults)
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
            System.out.println("SimplePropertiesDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List listProduct(Class t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Criteria c = session.createCriteria(t);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("meddev_item_seq"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimplePropertiesDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List listCompany(Class t, int page, int maxResults)
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
            System.out.println("SimplePropertiesDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
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
            System.out.println("SimplePropertiesDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
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

    public IDValuePair readProduct(Class clas, long id)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        int intId = l.intValue();
        c.add(Restrictions.eq("meddev_item_seq", Integer.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
    }

    public IDValuePair readCompany(Class clas, long id)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        int intId = l.intValue();
        c.add(Restrictions.eq("id", Integer.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
    }

    public IDValuePair saveOrUpdate(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return t;
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

    public long count(Object t)
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

    public Set listMeaClassNo(Mea_class_no target, int page, int size)
    {
        Set set = new HashSet();
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        String q1 = "select * from mea_class_no order by regist_ts desc";
        Query q = session.createSQLQuery(q1).addEntity(Mea_class_no.class);
        Criteria c = session.createCriteria(target.getClass());
        Example example = Example.create(target);
        example.ignoreCase();
        example.excludeZeroes();
        example.enableLike(MatchMode.ANYWHERE);
        c.add(example);
        q.setFirstResult((page - 1) * size + 1);
        q.setMaxResults(page * size);
        c.setFirstResult((page - 1) * size + 1);
        c.setMaxResults(size);
        c.addOrder(Order.desc("regist_ts"));
        list = c.list();
        set = new HashSet(list);
        return set;
    }

    public IDValuePair readMeaClassNo(Mea_class_no target)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(target.getGrade()).append(" / ").append(target.getMea_class_no()).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        c.add(Restrictions.eq("grade", target.getGrade()));
        c.add(Restrictions.like("mea_class_no", target.getMea_class_no()));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        System.out.println((new StringBuilder("found : ")).append(found).toString());
        return found;
    }

    public long count(Mea_class_no target, int i)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        c.setProjection(Projections.count("mea_class_no"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List listMeaClassNo(Mea_class_no target, int page, int size, int mode)
    {
        List list = null;
        switch(mode)
        {
        case 1: // '\001'
            list = listMeaClassNo1(target, page, size);
            // fall through

        case 2: // '\002'
            list = listMeaClassNo2(target, page, size);
            // fall through

        default:
            list = listMeaClassNo1(target, page, size);
            break;
        }
        return list;
    }

    public boolean deleteMeaClassNo(Mea_class_no target)
    {
        boolean jobStat = false;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM MEA_CLASS_NO \n");
            sb.append("WHERE MEA_CLASS_NO= :mcn  \n");
            sb.append("AND GRADE= :grd");
            Query query = session.createSQLQuery(sb.toString());
            query.setParameter("mcn", target.getCompositeKey().getMea_class_no());
            query.setParameter("grd", Integer.valueOf(target.getCompositeKey().getGrade()));
            int affected = query.executeUpdate();
            System.out.println((new StringBuilder("isDirty ? ")).append(session.isDirty()).toString());
            session.flush();
            if(affected > 0)
                jobStat = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println((new StringBuilder("deleteMeaClassNo().jobStat := ")).append(jobStat).toString());
        return jobStat;
    }

    public Mea_class_no readMeaClassNo(Class t, int grade, String mea_class_no)
    {
        System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t);
        c.add(Restrictions.eq("grade", Integer.valueOf(grade)));
        c.add(Restrictions.like("mea_class_no", mea_class_no));
        Mea_class_no found = (Mea_class_no)c.uniqueResult();
        return found;
    }

    public Mea_class_no updateMeaClassNo(Mea_class_no target)
    {
        Mea_class_no found;
        StringBuilder queryString;
        Connection conn;
        int result;
        found = null;
        System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
        Session session = sessionFactory.getCurrentSession();
        queryString = new StringBuilder();
        conn = DBConnection.getConnection();
        result = 0;
        queryString.append("UPDATE MEA_CLASS_NO SET ");
        queryString.append("CLASS_KOR_NAME = ?");
        queryString.append(", CLASS_ENG_NAME = ?");
        queryString.append(", CLASS_CONT = ?");
        queryString.append(", CODE_AGE= ?");
        queryString.append(", IS_IN_USE= ?");
        queryString.append(", TRACEABILITY= ?");
        queryString.append("\n where grade ='?' and mea_class_no  = '?'");
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(queryString.toString());
            pstmt.setString(1, target.getClass_kor_name());
            pstmt.setString(2, target.getClass_eng_name());
            pstmt.setString(3, target.getClass_cont());
            pstmt.setLong(4, target.getCode_age().getId());
            pstmt.setLong(5, target.getIsInUse().getId());
            pstmt.setLong(6, target.getTraceability().getId());
            pstmt.setString(7, target.getGrade());
            pstmt.setString(8, target.getMea_class_no());
            System.out.println(target.getClass_kor_name());
            System.out.println(target.getClass_eng_name());
            System.out.println(target.getClass_cont());
            System.out.println(target.getCode_age().getId());
            System.out.println(target.getIsInUse().getId());
            System.out.println(target.getTraceability().getId());
            System.out.println(target.getGrade());
            System.out.println(target.getMea_class_no());
            System.out.println(queryString.toString());
            result = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        System.out.println((new StringBuilder("result of  updateMeaClassNo :=  ")).append(result).toString());
        return found;
    }

    public List createItemHistoryJunction(int grade, String mea_class_no, int historyId)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("");
        return l;
    }

    public Object create(Object obj, boolean isGeneral)
    {
        Session session = sessionFactory.getCurrentSession();
        obj = session.save(obj);
        return obj;
    }

    public long count(SimpleProduct target, int i)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List listPatientCondition(SimplePatientCondition target, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from patient_condition where depth_level= :depth_level");
        sb.append("\n order by fda_source_pt_kor ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimplePatientCondition.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List listPatientCondition(int parent_id, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from patient_condition where depth_level= :depth_level");
        sb.append("\n and parent_id = :parent_id");
        sb.append("\n order by id desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimplePatientCondition.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        q.setParameter("parent_id", Integer.valueOf(parent_id));
        list = q.list();
        return list;
    }

    public SimplePatientCondition getPatientConditionParent(long id)
    {
        SimplePatientCondition found = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from patient_condition where  ");
        sb.append("\n id = :parent_id  ");
        sb.append("\n order by id desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimplePatientCondition.class);
        q.setParameter("parent_id", Long.valueOf(id));
        found = (SimplePatientCondition)q.uniqueResult();
        return found;
    }

    public List byLevel1PatientCondition(int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        switch(level)
        {
        case 3: // '\003'
            sb.append("select distinct b.id, b.*   ");
            sb.append("\n from patient_condition as a  ");
            sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 1  ");
            sb.append("\n order by b.fda_source_pt_kor  ");
            break;

        case 4: // '\004'
            sb.append("select distinct c.id, c.*   ");
            sb.append("\n from patient_condition as a  ");
            sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
            sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 1  ");
            sb.append("\n order by c.fda_source_pt_kor  ");
            break;

        case 5: // '\005'
            sb.append("select distinct d.id, d.*   ");
            sb.append("\n from patient_condition as a  ");
            sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
            sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
            sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and d.depth_level = 1  ");
            sb.append("\n order by d.fda_source_pt_kor  ");
            break;

        case 6: // '\006'
            sb.append("select distinct e.id, e.*   ");
            sb.append("\n from patient_condition as a  ");
            sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
            sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
            sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
            sb.append("\n join patient_condition as e on d.parent_id = e.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and e.depth_level = 1  ");
            sb.append("\n order by e.fda_source_pt_kor  ");
            break;
        }
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimplePatientCondition.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List byLevelPatientConditionStep(int level, int step, int id)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        switch(level)
        {
        default:
            break;

        case 3: // '\003'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 2 ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 4: // '\004'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 2 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 3");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 5: // '\005'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct c.id, c.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 2 ");
                sb.append("\n and c.parent_id = :id  ");
                sb.append("\n order by c.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 3 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 4: // '\004'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 4 ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 6: // '\006'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct d.id, d.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n join patient_condition as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and d.depth_level = 2 ");
                sb.append("\n and d.parent_id = :id  ");
                sb.append("\n order by d.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct c.id, c.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n join patient_condition as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 3 ");
                sb.append("\n and c.parent_id = :id  ");
                sb.append("\n order by c.fda_source_pt_kor  ");
                break;

            case 4: // '\004'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n join patient_condition as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 4 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 5: // '\005'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from patient_condition as a  ");
                sb.append("\n join patient_condition as b on a.parent_id = b.id  ");
                sb.append("\n join patient_condition as c on b.parent_id = c.id  ");
                sb.append("\n join patient_condition as d on c.parent_id = d.id  ");
                sb.append("\n join patient_condition as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 5  ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;
        }
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimplePatientCondition.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        q.setParameter("id", Integer.valueOf(id));
        list = q.list();
        return list;
    }

    public List listMedicalDevice(SimpleMedicalDeviceMalfunctionCode target, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from medical_device_malfunction_code where depth_level= :depth_level");
        sb.append("\n order by fda_source_pt_kor ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleMedicalDeviceMalfunctionCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List listMedicalDevice(int parent_id, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from medical_device_malfunction_code where depth_level= :depth_level");
        sb.append("\n and parent_id = :parent_id");
        sb.append("\n order by id desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleMedicalDeviceMalfunctionCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        q.setParameter("parent_id", Integer.valueOf(parent_id));
        list = q.list();
        return list;
    }

    public SimpleMedicalDeviceMalfunctionCode getMedicalDeviceParent(long id)
    {
        SimpleMedicalDeviceMalfunctionCode found = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from medical_device_malfunction_code where  ");
        sb.append("\n id = :parent_id  ");
        sb.append("\n order by id desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleMedicalDeviceMalfunctionCode.class);
        q.setParameter("parent_id", Long.valueOf(id));
        found = (SimpleMedicalDeviceMalfunctionCode)q.uniqueResult();
        return found;
    }

    public List byLevel1MedicalDevice(int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        switch(level)
        {
        case 3: // '\003'
            sb.append("select distinct b.id, b.*   ");
            sb.append("\n from medical_device_malfunction_code as a  ");
            sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 1  ");
            sb.append("\n order by b.fda_source_pt_kor  ");
            break;

        case 4: // '\004'
            sb.append("select distinct c.id, c.*   ");
            sb.append("\n from medical_device_malfunction_code as a  ");
            sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
            sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 1  ");
            sb.append("\n order by c.fda_source_pt_kor  ");
            break;

        case 5: // '\005'
            sb.append("select distinct d.id, d.*   ");
            sb.append("\n from medical_device_malfunction_code as a  ");
            sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
            sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
            sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and d.depth_level = 1  ");
            sb.append("\n order by d.fda_source_pt_kor  ");
            break;

        case 6: // '\006'
            sb.append("select distinct e.id, e.*   ");
            sb.append("\n from medical_device_malfunction_code as a  ");
            sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
            sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
            sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
            sb.append("\n join medical_device_malfunction_code as e on d.parent_id = e.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and e.depth_level = 1  ");
            sb.append("\n order by e.fda_source_pt_kor  ");
            break;
        }
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleMedicalDeviceMalfunctionCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List byLevelMedicalDeviceStep(int level, int step, int id)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        switch(level)
        {
        default:
            break;

        case 3: // '\003'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 2 ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 4: // '\004'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 2 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 3");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 5: // '\005'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct c.id, c.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 2 ");
                sb.append("\n and c.parent_id = :id  ");
                sb.append("\n order by c.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 3 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 4: // '\004'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 4 ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 6: // '\006'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct d.id, d.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n join medical_device_malfunction_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and d.depth_level = 2 ");
                sb.append("\n and d.parent_id = :id  ");
                sb.append("\n order by d.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct c.id, c.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n join medical_device_malfunction_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 3 ");
                sb.append("\n and c.parent_id = :id  ");
                sb.append("\n order by c.fda_source_pt_kor  ");
                break;

            case 4: // '\004'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n join medical_device_malfunction_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 4 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 5: // '\005'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from medical_device_malfunction_code as a  ");
                sb.append("\n join medical_device_malfunction_code as b on a.parent_id = b.id  ");
                sb.append("\n join medical_device_malfunction_code as c on b.parent_id = c.id  ");
                sb.append("\n join medical_device_malfunction_code as d on c.parent_id = d.id  ");
                sb.append("\n join medical_device_malfunction_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 5  ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;
        }
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleMedicalDeviceMalfunctionCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        q.setParameter("id", Integer.valueOf(id));
        list = q.list();
        return list;
    }

    public List listComponent(SimpleComponentCode target, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from component_code where depth_level= :depth_level");
        sb.append("\n order by fda_source_pt_kor ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleComponentCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List listComponent(int parent_id, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from component_code where depth_level= :depth_level");
        sb.append("\n and parent_id = :parent_id");
        sb.append("\n order by id desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleComponentCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        q.setParameter("parent_id", Integer.valueOf(parent_id));
        list = q.list();
        return list;
    }

    public SimpleComponentCode getComponentParent(long id)
    {
        SimpleComponentCode found = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from component_code where  ");
        sb.append("\n id = :parent_id  ");
        sb.append("\n order by id desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleComponentCode.class);
        q.setParameter("parent_id", Long.valueOf(id));
        found = (SimpleComponentCode)q.uniqueResult();
        return found;
    }

    public List byLevel1Component(int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        switch(level)
        {
        case 3: // '\003'
            sb.append("select distinct b.id, b.*   ");
            sb.append("\n from component_code as a  ");
            sb.append("\n join component_code as b on a.parent_id = b.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 1  ");
            sb.append("\n order by b.fda_source_pt_kor  ");
            break;

        case 4: // '\004'
            sb.append("select distinct c.id, c.*   ");
            sb.append("\n from component_code as a  ");
            sb.append("\n join component_code as b on a.parent_id = b.id  ");
            sb.append("\n join component_code as c on b.parent_id = c.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 1  ");
            sb.append("\n order by c.fda_source_pt_kor  ");
            break;

        case 5: // '\005'
            sb.append("select distinct d.id, d.*   ");
            sb.append("\n from component_code as a  ");
            sb.append("\n join component_code as b on a.parent_id = b.id  ");
            sb.append("\n join component_code as c on b.parent_id = c.id  ");
            sb.append("\n join component_code as d on c.parent_id = d.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and d.depth_level = 1  ");
            sb.append("\n order by d.fda_source_pt_kor  ");
            break;

        case 6: // '\006'
            sb.append("select distinct e.id, e.*   ");
            sb.append("\n from component_code as a  ");
            sb.append("\n join component_code as b on a.parent_id = b.id  ");
            sb.append("\n join component_code as c on b.parent_id = c.id  ");
            sb.append("\n join component_code as d on c.parent_id = d.id  ");
            sb.append("\n join component_code as e on d.parent_id = e.id  ");
            sb.append("\n where a.depth_level = :depth_level - 1 and e.depth_level = 1  ");
            sb.append("\n order by e.fda_source_pt_kor  ");
            break;
        }
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleComponentCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List byLevelComponentStep(int level, int step, int id)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        switch(level)
        {
        default:
            break;

        case 3: // '\003'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 2 ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 4: // '\004'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 2 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 3");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 5: // '\005'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct c.id, c.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 2 ");
                sb.append("\n and c.parent_id = :id  ");
                sb.append("\n order by c.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 3 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 4: // '\004'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 4 ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;

        case 6: // '\006'
            switch(step)
            {
            case 2: // '\002'
                sb.append("select distinct d.id, d.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n join component_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and d.depth_level = 2 ");
                sb.append("\n and d.parent_id = :id  ");
                sb.append("\n order by d.fda_source_pt_kor  ");
                break;

            case 3: // '\003'
                sb.append("select distinct c.id, c.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n join component_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and c.depth_level = 3 ");
                sb.append("\n and c.parent_id = :id  ");
                sb.append("\n order by c.fda_source_pt_kor  ");
                break;

            case 4: // '\004'
                sb.append("select distinct b.id, b.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n join component_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and b.depth_level = 4 ");
                sb.append("\n and b.parent_id = :id  ");
                sb.append("\n order by b.fda_source_pt_kor  ");
                break;

            case 5: // '\005'
                sb.append("select distinct a.id, a.*   ");
                sb.append("\n from component_code as a  ");
                sb.append("\n join component_code as b on a.parent_id = b.id  ");
                sb.append("\n join component_code as c on b.parent_id = c.id  ");
                sb.append("\n join component_code as d on c.parent_id = d.id  ");
                sb.append("\n join component_code as e on d.parent_id = e.id  ");
                sb.append("\n where a.depth_level = :depth_level - 1 and a.depth_level = 5  ");
                sb.append("\n and a.parent_id = :id  ");
                sb.append("\n order by a.fda_source_pt_kor  ");
                break;
            }
            break;
        }
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(SimpleComponentCode.class);
        q.setParameter("depth_level", Integer.valueOf(level));
        q.setParameter("id", Integer.valueOf(id));
        list = q.list();
        return list;
    }

    public List listMeaClassNo(Mea_class_no target, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from mea_class_no where class_level= :class_level");
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(Mea_class_no.class);
        q.setParameter("class_level", Integer.valueOf(level));
        list = q.list();
        return list;
    }

    public List listMeaClassNo(Mea_class_no target)
    {
        return null;
    }

    public int add(SimpleReportType insertTarget)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            java.io.Serializable serial = s.save(insertTarget);
            if(serial instanceof Long)
            {
                Long l = (Long)serial;
                result = l.intValue();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int add(SimpleReportTypeHistory insertTarget2)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.getCurrentSession();
            java.io.Serializable serial = s.save(insertTarget2);
            if(serial instanceof Long)
            {
                Long l = (Long)serial;
                result = l.intValue();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List listMeaClassNo1(Mea_class_no target, int page, int size)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ( SELECT  rownum rnum, ");
        sb.append("mea_class_no,grade,higher_class_no,higher_class_grade,class_level,class_kor_name,class_eng_name,class_cont,item_grp_code,regist_ts,regist_id,update_ts,update_id,code_age,trace_manage_target_yn,traceability,is_in_use,use_yn");
        sb.append("\n from ordered_mea_class_no2");
        sb.append((new StringBuilder("\n where rownum <=  ")).append(size * page).append(" ) aa").toString());
        sb.append((new StringBuilder("\n where aa.rnum >= ")).append((page - 1) * size + 1).toString());
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(Mea_class_no.class);
        list = q.list();
        return list;
    }

    public List listMeaClassNo2(Mea_class_no target, int page, int size)
    {
        System.out.println("listMeaClassNo2  :=");
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        String q1 = "select * from ordered_mea_class_no order by regist_ts desc";
        Query q = session.createSQLQuery(q1).addEntity(Mea_class_no.class);
        q.setFirstResult((page - 1) * size);
        q.setMaxResults(page * size);
        list = q.list();
        return list;
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

    public List listPopupPatient(String search)
    {
        List l = null;
        StringBuffer str = new StringBuffer();
        str.append("SELECT this_.ID, this_.NAME, this_.FDA_SOURCE_DEFINITION, this_.FDA_SOURCE_DEFINITION_KOR, this_.NCIT_DEFINITION, this_.NCIT_DEFINITION_KOR, this_.DEPTH_LEVEL,    ");
        str.append(" this_.FDA_SOURCE_PT_KOR, this_.NCI_CODE, this_.FDA_CODE, this_.ACTIVE_FROM, this_.LAST_MODIFIED, this_.DEFAULT_IN_USE_ID, ");
        str.append(" CASE WHEN this_.DEPTH_LEVEL = 1 then this_.ID ");
        str.append(" WHEN this_.DEPTH_LEVEL = 2 then nvl((SELECT MIN(ID) FROM PATIENT_CONDITION WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 3 then nvl((SELECT MIN(ID) FROM PATIENT_CONDITION WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 4 then nvl((SELECT MIN(ID) FROM PATIENT_CONDITION WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 5 then nvl((SELECT MIN(ID) FROM PATIENT_CONDITION WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 6 then nvl((SELECT MIN(ID) FROM PATIENT_CONDITION WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" END as PARENT_ID ");
        str.append(" FROM PATIENT_CONDITION this_");
        str.append(" WHERE this_.DEFAULT_IN_USE_ID = 1 ");
        str.append((new StringBuilder(" AND ( this_.FDA_SOURCE_PT_KOR LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or this_.FDA_CODE LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or UPPER(this_.NAME) LIKE UPPER('")).append(search).append("') ) ").toString());
        str.append(" ORDER BY PARENT_ID, this_.DEPTH_LEVEL, this_.ID ");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimplePatientCondition.class);
        l = query.list();
        return l;
    }

    public List listPopupMedical(String search)
    {
        List l = null;
        StringBuffer str = new StringBuffer();
        str.append("SELECT this_.ID, this_.NAME, this_.FDA_SOURCE_DEFINITION, this_.FDA_SOURCE_DEFINITION_KOR, this_.NCIT_DEFINITION, this_.NCIT_DEFINITION_KOR, this_.DEPTH_LEVEL,    ");
        str.append(" this_.FDA_SOURCE_PT_KOR, this_.NCI_CODE, this_.FDA_CODE, this_.ACTIVE_FROM, this_.LAST_MODIFIED, this_.DEFAULT_IN_USE_ID, ");
        str.append(" CASE WHEN this_.DEPTH_LEVEL = 1 then this_.ID ");
        str.append(" WHEN this_.DEPTH_LEVEL = 2 then nvl((SELECT MIN(ID) FROM MEDICAL_DEVICE_MALFUNCTION_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 3 then nvl((SELECT MIN(ID) FROM MEDICAL_DEVICE_MALFUNCTION_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 4 then nvl((SELECT MIN(ID) FROM MEDICAL_DEVICE_MALFUNCTION_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 5 then nvl((SELECT MIN(ID) FROM MEDICAL_DEVICE_MALFUNCTION_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 6 then nvl((SELECT MIN(ID) FROM MEDICAL_DEVICE_MALFUNCTION_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" END as PARENT_ID ");
        str.append(" FROM MEDICAL_DEVICE_MALFUNCTION_CODE this_");
        str.append(" WHERE this_.DEFAULT_IN_USE_ID = 1 ");
        str.append((new StringBuilder(" AND ( this_.FDA_SOURCE_PT_KOR LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or this_.FDA_CODE LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or UPPER(this_.NAME) LIKE UPPER('")).append(search).append("') ) ").toString());
        str.append(" ORDER BY PARENT_ID, this_.DEPTH_LEVEL, this_.ID ");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleMedicalDeviceMalfunctionCode.class);
        System.out.println("[SimpleSideeffectReportDAO].list.query2 ");
        l = query.list();
        return l;
    }

    public List listPopupComponent(String search)
    {
        List l = null;
        StringBuffer str = new StringBuffer();
        str.append("SELECT this_.ID, this_.NAME, this_.FDA_SOURCE_DEFINITION, this_.FDA_SOURCE_DEFINITION_KOR, this_.NCIT_DEFINITION, this_.NCIT_DEFINITION_KOR, this_.DEPTH_LEVEL,    ");
        str.append(" this_.FDA_SOURCE_PT_KOR, this_.NCI_CODE, this_.FDA_CODE, this_.ACTIVE_FROM, this_.LAST_MODIFIED, this_.DEFAULT_IN_USE_ID, ");
        str.append(" CASE WHEN this_.DEPTH_LEVEL = 1 then this_.ID ");
        str.append(" WHEN this_.DEPTH_LEVEL = 2 then nvl((SELECT MIN(ID) FROM COMPONENT_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 3 then nvl((SELECT MIN(ID) FROM COMPONENT_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 4 then nvl((SELECT MIN(ID) FROM COMPONENT_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 5 then nvl((SELECT MIN(ID) FROM COMPONENT_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" WHEN this_.DEPTH_LEVEL = 6 then nvl((SELECT MIN(ID) FROM COMPONENT_CODE WHERE PARENT_ID = this_.ID ),this_.ID) ");
        str.append(" END as PARENT_ID ");
        str.append(" FROM COMPONENT_CODE this_");
        str.append(" WHERE this_.DEFAULT_IN_USE_ID = 1 ");
        str.append((new StringBuilder(" AND ( this_.FDA_SOURCE_PT_KOR LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or this_.FDA_CODE LIKE '")).append(search).append("'").toString());
        str.append((new StringBuilder(" or UPPER(this_.NAME) LIKE UPPER('")).append(search).append("') ) ").toString());
        str.append(" ORDER BY PARENT_ID, this_.DEPTH_LEVEL, this_.ID ");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(str.toString()).addEntity(SimpleComponentCode.class);
        l = query.list();
        return l;
    }

    public List listMeaClassNo(String higher_class_no, int higher_class_grade, int level)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from mea_class_no where class_level= :class_level");
        sb.append("\n and higher_class_no = :hcn and higher_class_grade = :hcg");
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(Mea_class_no.class);
        q.setParameter("class_level", Integer.valueOf(level));
        q.setParameter("hcn", higher_class_no);
        q.setParameter("hcg", Integer.valueOf(higher_class_grade));
        list = q.list();
        return list;
    }

    public Mea_class_no getParent(String higher_class_no)
    {
        Mea_class_no found = null;
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *  ");
        sb.append("\n from mea_class_no where  ");
        sb.append("\n mea_class_no = :hcn  ");
        sb.append("\n order by update_ts desc ");
        String q1 = "";
        q1 = sb.toString();
        Query q = session.createSQLQuery(q1).addEntity(Mea_class_no.class);
        q.setParameter("hcn", higher_class_no);
        found = (Mea_class_no)q.uniqueResult();
        return found;
    }

    public List codeList(Class type, String colName, String search)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(type).add(Restrictions.like((new StringBuilder()).append(colName).toString(), search));
        l = c.list();
        return l;
    }

    public List sideeffectCodeList(Class type, String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, 
            String toMM, String orderColName, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(type);
        if(!reportType.equals("0"))
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join SIDE_EFFECT_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
        else
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString()));
        c.addOrder(Order.asc(orderColName));
        l = c.list();
        return l;
    }

    public List sideeffectLevelCodeList(Class type, String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, 
            String toMM, String orderColName, Integer level, String reporterTypes)
    {
        List l = null;
        List l_sub = null;
        List l_total = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(type);
        if(!reportType.equals("0"))
        {
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join SIDE_EFFECT_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
            c.add(Restrictions.ge("depthLevel", level));
        } else
        {
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SIDE_EFFECCT_REPORT report ").append(joinTable).append(" join JUNC_SIDEEFFECT_REPORT_TYPE reportType on (report.id = reportType.report_id and reportType.report_type_id in (").append(reporterTypes).append("))").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString())).add(Restrictions.ge("depthLevel", level));
            c.add(Restrictions.ge("depthLevel", level));
        }
        l = c.list();
        ArrayList idArr = new ArrayList();
        ArrayList idArrInt = new ArrayList();
        ArrayList idArrEqLevel = new ArrayList();
        ArrayList idArrParent = new ArrayList();
        List list = l;
        for(int i = 0; i < l.size(); i++)
            if(type.getName().equals("properties.SimpleMedicalDeviceMalfunctionCode"))
            {
                SimpleMedicalDeviceMalfunctionCode bean = (SimpleMedicalDeviceMalfunctionCode)list.get(i);
                if(level.equals(Integer.valueOf(bean.getDepthLevel())))
                {
                    idArrEqLevel.add(Long.valueOf(bean.getId()));
                } else
                {
                    idArr.add(Long.valueOf(bean.getId()));
                    idArrInt.add(Integer.valueOf(Integer.parseInt(Long.toString(bean.getId()))));
                    idArrParent.add(Integer.valueOf(bean.getParentId()));
                }
            } else
            if(type.getName().equals("properties.SimpleComponentCode"))
            {
                SimpleComponentCode bean = (SimpleComponentCode)list.get(i);
                if(level.equals(bean.getDepthLevel()))
                {
                    idArrEqLevel.add(Long.valueOf(bean.getId()));
                } else
                {
                    idArr.add(Long.valueOf(bean.getId()));
                    idArrInt.add(Integer.valueOf(Integer.parseInt(Long.toString(bean.getId()))));
                    idArrParent.add(bean.getParentId());
                }
            } else
            if(type.getName().equals("properties.SimplePatientCondition"))
            {
                SimplePatientCondition bean = (SimplePatientCondition)list.get(i);
                if(level.equals(bean.getDepthLevel()))
                {
                    idArrEqLevel.add(Long.valueOf(bean.getId()));
                } else
                {
                    idArr.add(Long.valueOf(bean.getId()));
                    idArrInt.add(Integer.valueOf(Integer.parseInt(Long.toString(bean.getId()))));
                    idArrParent.add(bean.getParentId());
                }
            }

        for(int i = 5; i >= level.intValue(); i--)
        {
            Criteria c_sub = session.createCriteria(type);
            if(idArrInt.size() > 0)
                c_sub.add(Restrictions.or(Restrictions.in("parentId", idArrParent), Restrictions.in("parentId", idArrInt)));
            else
                c_sub.add(Restrictions.eq("id", Long.valueOf(0L)));
            c_sub.add(Restrictions.eq("depthLevel", Integer.valueOf(i)));
            l_sub = c_sub.list();
            List list_sub = l_sub;
            for(int i_sub = 0; i_sub < l_sub.size(); i_sub++)
                if(type.getName().equals("properties.SimpleComponentCode"))
                {
                    SimpleComponentCode bean_sub = (SimpleComponentCode)list_sub.get(i_sub);
                    if(i == bean_sub.getDepthLevel().intValue())
                    {
                        idArrEqLevel.add(Long.valueOf(bean_sub.getId()));
                        idArrParent.add(bean_sub.getParentId());
                    }
                } else
                if(type.getName().equals("properties.SimpleMedicalDeviceMalfunctionCode"))
                {
                    SimpleMedicalDeviceMalfunctionCode bean_sub = (SimpleMedicalDeviceMalfunctionCode)list_sub.get(i_sub);
                    if(i == bean_sub.getDepthLevel())
                    {
                        idArrEqLevel.add(Long.valueOf(bean_sub.getId()));
                        idArrParent.add(Integer.valueOf(bean_sub.getParentId()));
                    }
                } else
                if(type.getName().equals("properties.SimplePatientCondition"))
                {
                    SimplePatientCondition bean_sub = (SimplePatientCondition)list_sub.get(i_sub);
                    if(i == bean_sub.getDepthLevel().intValue())
                    {
                        idArrEqLevel.add(Long.valueOf(bean_sub.getId()));
                        idArrParent.add(bean_sub.getParentId());
                    }
                }

        }

        l = c.list();
        Criteria c_total = session.createCriteria(type);
        if(idArrEqLevel.size() > 0 && idArr.size() > 0)
            c_total.add(Restrictions.or(Restrictions.in("id", idArrEqLevel), Restrictions.in("id", idArr)));
        else
        if(idArrEqLevel.size() > 0 && idArr.size() == 0)
            c_total.add(Restrictions.in("id", idArrEqLevel));
        else
        if(idArrEqLevel.size() == 0 && idArr.size() > 0)
            c_total.add(Restrictions.in("id", idArr));
        else
            c_total.add(Restrictions.eq("id", Long.valueOf(0L)));
        c_total.addOrder(Order.asc("parentId")).addOrder(Order.asc("depthLevel")).addOrder(Order.asc(orderColName));
        l_total = c_total.list();
        return l_total;
    }

    public List safetyCodeList(Class type, String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, 
            String toMM, String orderColName, String reporterTypes)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(type);
        if(!reportType.equals("0"))
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join SAFETY_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
        else
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString()));
        c.addOrder(Order.asc(orderColName));
        l = c.list();
        return l;
    }

    public List safetyLevelCodeList(Class type, String colName, String joinTable, String reportType, String fmYY, String fmMM, String toYY, 
            String toMM, String orderColName, Integer level, String reporterTypes)
    {
        List l = null;
        List l_sub = null;
        List l_total = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(type);
        if(!reportType.equals("0"))
        {
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join SAFETY_REPORT_TYPE_DATE rdate on (rdate.report_id = report.id) ").append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where rdate.report_type_id = ").append(reportType).append(" and rdate.report_year || rdate.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("') ").toString()));
            c.add(Restrictions.ge("depthLevel", level));
        } else
        {
            c.add(Restrictions.sqlRestriction((new StringBuilder("id in (SELECT ")).append(colName).append(" FROM SAFETY_REPORT report ").append(joinTable).append(" join JUNC_SAFETY_REPORTER_TYPES reportType on (report.id = reportType.safety_report and reportType.reporter_type in (").append(reporterTypes).append(")) ").append(" where report.report_year || report.report_month between '").append(fmYY).append(fmMM).append("' and '").append(toYY).append(toMM).append("')").toString())).add(Restrictions.ge("depthLevel", level));
            c.add(Restrictions.ge("depthLevel", level));
        }
        l = c.list();
        ArrayList idArr = new ArrayList();
        ArrayList idArrInt = new ArrayList();
        ArrayList idArrEqLevel = new ArrayList();
        ArrayList idArrParent = new ArrayList();
        List list = l;
        for(int i = 0; i < l.size(); i++)
            if(type.getName().equals("properties.SimpleMedicalDeviceMalfunctionCode"))
            {
                SimpleMedicalDeviceMalfunctionCode bean = (SimpleMedicalDeviceMalfunctionCode)list.get(i);
                if(level.equals(Integer.valueOf(bean.getDepthLevel())))
                {
                    idArrEqLevel.add(Long.valueOf(bean.getId()));
                } else
                {
                    idArr.add(Long.valueOf(bean.getId()));
                    idArrInt.add(Integer.valueOf(Integer.parseInt(Long.toString(bean.getId()))));
                    idArrParent.add(Integer.valueOf(bean.getParentId()));
                }
            } else
            if(type.getName().equals("properties.SimpleComponentCode"))
            {
                SimpleComponentCode bean = (SimpleComponentCode)list.get(i);
                if(level.equals(bean.getDepthLevel()))
                {
                    idArrEqLevel.add(Long.valueOf(bean.getId()));
                } else
                {
                    idArr.add(Long.valueOf(bean.getId()));
                    idArrInt.add(Integer.valueOf(Integer.parseInt(Long.toString(bean.getId()))));
                    idArrParent.add(bean.getParentId());
                }
            } else
            if(type.getName().equals("properties.SimplePatientCondition"))
            {
                SimplePatientCondition bean = (SimplePatientCondition)list.get(i);
                if(level.equals(bean.getDepthLevel()))
                {
                    idArrEqLevel.add(Long.valueOf(bean.getId()));
                } else
                {
                    idArr.add(Long.valueOf(bean.getId()));
                    idArrInt.add(Integer.valueOf(Integer.parseInt(Long.toString(bean.getId()))));
                    idArrParent.add(bean.getParentId());
                }
            }

        for(int i = 5; i >= level.intValue(); i--)
        {
            Criteria c_sub = session.createCriteria(type);
            if(idArrInt.size() > 0)
                c_sub.add(Restrictions.or(Restrictions.in("parentId", idArrParent), Restrictions.in("parentId", idArrInt)));
            else
                c_sub.add(Restrictions.eq("id", Long.valueOf(0L)));
            c_sub.add(Restrictions.eq("depthLevel", Integer.valueOf(i)));
            l_sub = c_sub.list();
            List list_sub = l_sub;
            for(int i_sub = 0; i_sub < l_sub.size(); i_sub++)
                if(type.getName().equals("properties.SimpleComponentCode"))
                {
                    SimpleComponentCode bean_sub = (SimpleComponentCode)list_sub.get(i_sub);
                    if(i == bean_sub.getDepthLevel().intValue())
                    {
                        idArrEqLevel.add(Long.valueOf(bean_sub.getId()));
                        idArrParent.add(bean_sub.getParentId());
                    }
                } else
                if(type.getName().equals("properties.SimpleMedicalDeviceMalfunctionCode"))
                {
                    SimpleMedicalDeviceMalfunctionCode bean_sub = (SimpleMedicalDeviceMalfunctionCode)list_sub.get(i_sub);
                    if(i == bean_sub.getDepthLevel())
                    {
                        idArrEqLevel.add(Long.valueOf(bean_sub.getId()));
                        idArrParent.add(Integer.valueOf(bean_sub.getParentId()));
                    }
                } else
                if(type.getName().equals("properties.SimplePatientCondition"))
                {
                    SimplePatientCondition bean_sub = (SimplePatientCondition)list_sub.get(i_sub);
                    if(i == bean_sub.getDepthLevel().intValue())
                    {
                        idArrEqLevel.add(Long.valueOf(bean_sub.getId()));
                        idArrParent.add(bean_sub.getParentId());
                    }
                }

        }

        l = c.list();
        Criteria c_total = session.createCriteria(type);
        if(idArrEqLevel.size() > 0 && idArr.size() > 0)
            c_total.add(Restrictions.or(Restrictions.in("id", idArrEqLevel), Restrictions.in("id", idArr)));
        else
        if(idArrEqLevel.size() > 0 && idArr.size() == 0)
            c_total.add(Restrictions.in("id", idArrEqLevel));
        else
        if(idArrEqLevel.size() == 0 && idArr.size() > 0)
            c_total.add(Restrictions.in("id", idArr));
        else
            c_total.add(Restrictions.eq("id", Long.valueOf(0L)));
        c_total.addOrder(Order.asc("parentId")).addOrder(Order.asc("depthLevel")).addOrder(Order.asc(orderColName));
        l_total = c_total.list();
        return l_total;
    }

    public Set listItemCategory(SimpleItemCategory target, int page, int size)
    {
        Set set = new HashSet();
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        Example example = Example.create(target);
        example.ignoreCase();
        example.excludeZeroes();
        example.enableLike(MatchMode.ANYWHERE);
        c.add(example);
        c.setFirstResult((page - 1) * size);
        c.setMaxResults(size);
        list = c.list();
        set = new HashSet(list);
        return set;
    }

    public long count(SimpleItemCategory target, int i)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        c.setProjection(Projections.count("id"));
        Example e = Example.create(target);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long countCompany(String s1, String s2)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleCompany.class);
        c.setProjection(Projections.count("id"));
        c.add(Restrictions.like("meddev_entp_no", s1));
        c.add(Restrictions.like("cob_flag_code", s2));
        System.out.println("query..");
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public long countProduct(String s1, String s2)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleProduct.class);
        c.setProjection(Projections.count("id"));
        c.add(Restrictions.like("meddev_item_no", s1));
        c.add(Restrictions.like("cob_flag_code", s2));
        System.out.println("query..");
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List listMeaClassNoView2(Mea_class_no_view2 target, int page, int size, int mode)
    {
        List list = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target.getClass());
        Example example = Example.create(target);
        example.ignoreCase();
        example.excludeZeroes();
        example.enableLike(MatchMode.ANYWHERE);
        c.add(example);
        c.setFirstResult((page - 1) * size);
        c.setMaxResults(size);
        list = c.list();
        return list;
    }

    public List listEntpProduct(int page, int maxResults, String searchKeyword)
    {
        searchKeyword = (new StringBuilder("%")).append(searchKeyword).append("%").toString();
        Session session = sessionFactory.getCurrentSession();
        List list = null;
        Criteria c = session.createCriteria(SimpleProduct.class).addOrder(Order.desc("meddev_item_seq")).createCriteria("company", "company").add(Restrictions.like("company.entp_name", searchKeyword)).setFirstResult((page - 1) * maxResults).setMaxResults(maxResults);
        list = c.list();
        return list;
    }

    public Long listEntpProductCnt(String searchKeyword)
    {
        searchKeyword = (new StringBuilder("%")).append(searchKeyword).append("%").toString();
        Session session = sessionFactory.getCurrentSession();
        List list = null;
        Long count = Long.valueOf(0L);
        Criteria c = session.createCriteria(SimpleProduct.class).addOrder(Order.desc("meddev_item_seq")).createCriteria("company", "company").add(Restrictions.like("company.entp_name", searchKeyword));
        list = c.list();
        count = Long.valueOf(list.size());
        return count;
    }

    public List listItemProduct(int page, int maxResults, String searchKeyword)
    {
        searchKeyword = (new StringBuilder("%")).append(searchKeyword).append("%").toString();
        Session session = sessionFactory.getCurrentSession();
        List list = null;
        Criteria c = session.createCriteria(SimpleProduct.class).addOrder(Order.desc("meddev_item_seq")).createCriteria("mea_item", "mea_item").add(Restrictions.like("mea_item.class_kor_name", searchKeyword)).setFirstResult((page - 1) * maxResults).setMaxResults(maxResults);
        list = c.list();
        return list;
    }

    public Long listItemProductCnt(String searchKeyword)
    {
        searchKeyword = (new StringBuilder("%")).append(searchKeyword).append("%").toString();
        Session session = sessionFactory.getCurrentSession();
        List list = null;
        Long count = Long.valueOf(0L);
        Criteria c = session.createCriteria(SimpleProduct.class).addOrder(Order.desc("meddev_item_seq")).createCriteria("mea_item", "mea_item").add(Restrictions.like("mea_item.class_kor_name", searchKeyword));
        list = c.list();
        count = Long.valueOf(list.size());
        return count;
    }

    public long sideEffectDocumentNumberCheck(long document_number)
    {
        Session session = sessionFactory.getCurrentSession();
        Long count = Long.valueOf(0L);
        Criteria c = session.createCriteria(SimpleSideeffectReport.class);
        c.setProjection(Projections.count("id")).add(Restrictions.eq("document_number", Long.valueOf(document_number)));
        count = (Long)c.uniqueResult();
        return count.longValue();
    }

    public long safetyDocumentNumberCheck(long document_number)
    {
        Session session = sessionFactory.getCurrentSession();
        Long count = Long.valueOf(0L);
        Criteria c = session.createCriteria(SimpleSafetyReport.class);
        c.setProjection(Projections.count("id")).add(Restrictions.eq("document_number", Long.valueOf(document_number)));
        count = (Long)c.uniqueResult();
        return count.longValue();
    }

    public long codeDelete(long report_id, long code_id, long gubun, int gubun2)
    {
        Session session = sessionFactory.getCurrentSession();
        long result = -1L;
        StringBuffer str = new StringBuffer();
        try
        {
            if(gubun2 == 1)
            {
                if(gubun == 1L)
                {
                    str.append(" DELETE FROM JUNC_SIDE_EFFECT_PATIENT_CONDITION  \n ");
                    str.append("  WHERE  report_id= :report_id  \n ");
                    str.append("  AND  patient_id= :code_id  \n ");
                }
                if(gubun == 2L)
                {
                    str.append(" DELETE FROM JUNC_SIDE_EFFECT_MEDICAL_CODE  \n ");
                    str.append("  WHERE  report_id= :report_id  \n ");
                    str.append("  AND  MEDICAL_ID= :code_id  \n ");
                }
                if(gubun == 3L)
                {
                    str.append(" DELETE FROM JUNC_SIDE_EFFECT_COMPONENT_CODE  \n ");
                    str.append("  WHERE  report_id= :report_id  \n ");
                    str.append("  AND  COMPONENT_ID= :code_id  \n ");
                }
            }
            if(gubun2 == 2)
            {
                if(gubun == 1L)
                {
                    str.append(" DELETE FROM JUNC_SAFETY_PATIENT_CONDITION  \n ");
                    str.append("  WHERE  report_id= :report_id  \n ");
                    str.append("  AND  CONDITION_ID= :code_id  \n ");
                }
                if(gubun == 2L)
                {
                    str.append(" DELETE FROM JUNC_SAFETY_MEDICAL_CODE  \n ");
                    str.append("  WHERE  report_id= :report_id  \n ");
                    str.append("  AND  MEDICAL_ID= :code_id  \n ");
                }
                if(gubun == 3L)
                {
                    str.append(" DELETE FROM JUNC_SAFETY_COMPONENT_CODE  \n ");
                    str.append("  WHERE  report_id= :report_id  \n ");
                    str.append("  AND  COMPONENT_ID= :code_id  \n ");
                }
            }
            Query query = session.createSQLQuery(str.toString());
            query.setParameter("report_id", Long.valueOf(report_id));
            query.setParameter("code_id", Long.valueOf(code_id));
            int temp = query.executeUpdate();
            session.flush();
            if(temp > 0)
                result = temp;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println((new StringBuilder("codeDelete().Exception  ")).append(e.getMessage()).toString());
        }
        return result;
    }

    public Object sideEffectMreportCount(Object i)
    {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder ss = new StringBuilder();
        ss.append("select count(*)");
        ss.append("\n from SIDE_EFFECT_REPORT_TYPE_DATE where REPORT_TYPE_ID=1 and REPORT_DATE between '");
        ss.append(i);
        ss.append("-01-01' and '");
        ss.append(i);
        ss.append("-12-31'");
        String q1 = "";
        q1 = ss.toString();
        Query q = session.createSQLQuery(q1);
        Object count1 = q.uniqueResult();
        return count1;
    }

    public Object safetyMreportCount(Object i)
    {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder ss = new StringBuilder();
        ss.append("select count(*)");
        ss.append("\n from SAFETY_REPORT_TYPE_DATE where REPORT_TYPE_ID=1 AND REPORT_DATE between '");
        ss.append(i);
        ss.append("-01-01' and '");
        ss.append(i);
        ss.append("-12-31'");
        String q1 = "";
        q1 = ss.toString();
        Query q = session.createSQLQuery(q1);
        Object count1 = q.uniqueResult();
        return count1;
    }

    public long count(Mea_class_no_view2 target, int i)
    {
        return 0L;
    }

    public long search(String fdaCode, String table)
    {
        Session session = sessionFactory.getCurrentSession();
        Long count = Long.valueOf(0L);
        Criteria c = null;
        if(table.equals("PATIENT_CONDITION"))
            c = session.createCriteria(SimplePatientCondition.class);
        else
        if(table.equals("MEDICAL_DEVICE_MALFUNCTION_CODE"))
            c = session.createCriteria(SimpleMedicalDeviceMalfunctionCode.class);
        else
        if(table.equals("COMPONENT_CODE"))
            c = session.createCriteria(SimpleComponentCode.class);
        c.setProjection(Projections.count("id")).add(Restrictions.eq("fdaCode", fdaCode));
        count = (Long)c.uniqueResult();
        if(count == null)
            count = Long.valueOf(0L);
        return count.longValue();
    }

    public long count(JuncSideEffectResult searchChildren)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(searchChildren.getClass());
        c.setProjection(Projections.count("id"));
        Example e = Example.create(searchChildren);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public List riskSideeffectResultList(String fmDate, String toDate, Integer depthLevel, long parentId)
    {
        List riskSideeffectResultList = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleSideeffectResult.class).add(Restrictions.sqlRestriction((new StringBuilder(" START_DATE <= TO_CHAR(LAST_DAY('")).append(toDate).append("01").append("'),'yyyymmdd')").toString()));
        if(depthLevel.intValue() == 1)
            c.add(Restrictions.eq("depthLevel", depthLevel));
        else
            c.add(Restrictions.eq("parentId", Long.valueOf(parentId)));
        riskSideeffectResultList = c.list();
        return riskSideeffectResultList;
    }

    public List resultList(Class t, int level)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(t.getName()).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t).add(Restrictions.eq("depthLevel", Integer.valueOf(level)));
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    private SessionFactory sessionFactory;
}

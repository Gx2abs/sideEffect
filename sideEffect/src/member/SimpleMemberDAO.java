// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMemberDAO.java

package member;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package member:
//            MemberDAO, MemberPassword, SimpleMember, Member

public class SimpleMemberDAO
    implements CRUDable, MemberDAO
{

    public SimpleMemberDAO()
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

    public IDValuePair create(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return null;
    }

    public IDValuePair update(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return null;
    }

    public boolean delete(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(t);
        return false;
    }

    public List list(IDValuePair t)
    {
        return null;
    }

    public List list(IDValuePair t, int page, int maxResults)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(t.getClass());
        criteria.setFirstResult((page - 1) * maxResults);
        criteria.setMaxResults(maxResults);
        Example example = Example.create(t);
        example.excludeZeroes();
        example.enableLike(MatchMode.ANYWHERE);
        criteria.add(example);
        criteria.addOrder(Order.desc("firstCreated"));
        List list = criteria.list();
        return list;
    }

    public List list(Class t)
    {
        return null;
    }

    public List list(Class t, int page, int maxResults)
    {
        System.out.println("<T extends IDValuePair> List<T> list(Class<T> t, int page, int maxResults)");
        System.out.println((new StringBuilder("for class : ")).append(t.getClass()).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(t);
        criteria.setFirstResult((page - 1) * maxResults);
        criteria.setMaxResults(maxResults);
        List list = criteria.list();
        return list;
    }

    public IDValuePair read(Class clas, long id)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clas);
        criteria.add(Restrictions.eq("id", Long.valueOf(id)));
        IDValuePair t = (IDValuePair)criteria.uniqueResult();
        return t;
    }

    public IDValuePair readCompany(Class clas, long id)
    {
        return null;
    }

    public IDValuePair readProduct(Class clas, long id)
    {
        return null;
    }

    public IDValuePair merge(IDValuePair t)
    {
        return null;
    }

    public String generateEncrypted(String source)
    {
        String encrypted = "";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT 1 as id , SIDEEFFECT_ENCRYPT( :source ) AS encryptedPassword FROM DUAL").addEntity(MemberPassword.class);
        query.setParameter("source", source);
        Object obj = query.uniqueResult();
        if(obj != null)
        {
            MemberPassword mp = (MemberPassword)obj;
            encrypted = mp.getEncryptedPassword();
        }
        return encrypted;
    }

    public long count(Class t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t);
        c.setProjection(Projections.count("id"));
        Object obj = c.uniqueResult();
        long count = 0L;
        if(obj != null)
            count = ((Long)obj).longValue();
        return count;
    }

    public boolean authenticate(String accountName, String clearPassword)
    {
        StringBuilder sb = new StringBuilder();
        sb.append((new StringBuilder("clear password : ")).append(clearPassword).append(" \n").toString());
        String encrypted = generateEncrypted(clearPassword);
        sb.append((new StringBuilder("encrypted : ")).append(encrypted).append(" \n").toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(SimpleMember.class);
        criteria.add(Restrictions.eq("accountName", accountName));
        sb.append((new StringBuilder("accountName : ")).append(accountName).append(" \n").toString());
        criteria.add(Restrictions.eq("memberPassword", encrypted));
        Object obj = criteria.uniqueResult();
        SimpleMember member = null;
        if(obj != null && (obj instanceof SimpleMember))
        {
            member = (SimpleMember)obj;
            sb.append("login success \n");
            System.out.println(sb.toString());
            return true;
        } else
        {
            sb.append("login failure \n");
            System.out.println(sb.toString());
            return false;
        }
    }

    public long count(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(t.getClass());
        Example example = Example.create(t);
        example.excludeZeroes();
        example.enableLike(MatchMode.ANYWHERE);
        criteria.add(example);
        criteria.setProjection(Projections.count("id"));
        Object obj = criteria.uniqueResult();
        Long returnVal = null;
        if(obj != null && (obj instanceof Long))
            returnVal = (Long)obj;
        return returnVal.longValue();
    }

    public long count(SimpleMember target)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(target.getClass());
        Example example = Example.create(target);
        example.excludeZeroes();
        example.enableLike(MatchMode.ANYWHERE);
        criteria.add(example);
        criteria.setProjection(Projections.count("id"));
        Object obj = criteria.uniqueResult();
        Long returnVal = null;
        if(obj != null && (obj instanceof Long))
            returnVal = (Long)obj;
        return returnVal.longValue();
    }

    public Member authenticate(String accountName, String clearPassword, int authenticationMode)
    {
        Member member = null;
        switch(authenticationMode)
        {
        case 2: // '\002'
            member = AuthenticateAndReturnMember(accountName, clearPassword);
            break;

        default:
            member = AuthenticateAndReturnMember(accountName, clearPassword);
            break;

        case 1: // '\001'
            break;
        }
        return member;
    }

    private Member AuthenticateAndReturnMember(String accountName, String clearPassword)
    {
        StringBuilder sb = new StringBuilder();
        sb.append((new StringBuilder("clear password : ")).append(clearPassword).append(" \n").toString());
        String encrypted = generateEncrypted(clearPassword);
        sb.append((new StringBuilder("encrypted : ")).append(encrypted).append(" \n").toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(SimpleMember.class);
        criteria.add(Restrictions.eq("accountName", accountName));
        sb.append((new StringBuilder("accountName : ")).append(accountName).append(" \n").toString());
        criteria.add(Restrictions.eq("memberPassword", encrypted));
        Object obj = criteria.uniqueResult();
        SimpleMember member = null;
        if(obj != null && (obj instanceof SimpleMember))
        {
            member = (SimpleMember)obj;
            sb.append("login success \n");
            System.out.println(sb.toString());
        }
        sb.append("login failure \n");
        System.out.println(sb.toString());
        return member;
    }

    public int checkUniqueId(String idToCheck)
    {
        int result = -1;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Member.class);
        Member m = new SimpleMember();
        m.setAccountName(idToCheck);
        Example example = Example.create(m);
        example.excludeZeroes();
        criteria.add(example);
        criteria.addOrder(Order.desc("firstCreated"));
        criteria.setProjection(Projections.count("id"));
        Object obj = criteria.uniqueResult();
        if(obj != null)
        {
            if(obj instanceof Integer)
                result = ((Integer)obj).intValue();
            if(obj instanceof Long)
                result = (new Long(((Long)obj).longValue())).intValue();
        }
        return result;
    }

    SessionFactory sessionFactory;
}

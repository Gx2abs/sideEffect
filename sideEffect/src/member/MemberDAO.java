// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MemberDAO.java

package member;

import abstraction.CRUDable;
import org.hibernate.SessionFactory;

// Referenced classes of package member:
//            SimpleMember, Member

public interface MemberDAO
    extends CRUDable
{
    public static class AuthenticationMode
    {

        public static final int BOOLEAN = 1;
        public static final int OBJECT = 2;

        public AuthenticationMode()
        {
        }
    }


    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract String generateEncrypted(String s);

    public abstract long count(Class class1);

    public abstract boolean authenticate(String s, String s1);

    public abstract long count(SimpleMember simplemember);

    public abstract Member authenticate(String s, String s1, int i);

    public abstract int checkUniqueId(String s);
}

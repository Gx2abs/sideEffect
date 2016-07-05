// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Account.java

package account;

import abstraction.IDValuePair;
import member.Member;

// Referenced classes of package account:
//            Role

public interface Account
    extends IDValuePair
{

    public abstract Role getRole();

    public abstract void setRole(Role role);

    public abstract String getEmailUserName();

    public abstract void setEmailUserName(String s);

    public abstract String getEmailDomain();

    public abstract void setEmailDomain(String s);

    public abstract String getPassword();

    public abstract void setPassword(String s);

    public abstract Member getMember();

    public abstract void setMember(Member member);
}

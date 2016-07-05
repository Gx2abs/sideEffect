// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAccount.java

package account;

import member.Member;

// Referenced classes of package account:
//            Account, Role

public class SimpleAccount
    implements Account
{

    public SimpleAccount()
    {
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmailUserName()
    {
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName)
    {
        this.emailUserName = emailUserName;
    }

    public String getEmailDomain()
    {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain)
    {
        this.emailDomain = emailDomain;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public void setId(long newID)
    {
        id = newID;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    public long getId()
    {
        return id;
    }

    private long id;
    private String emailUserName;
    private String emailDomain;
    private String password;
    private Member member;
    private Role role;
}

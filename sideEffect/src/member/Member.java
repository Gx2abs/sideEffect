// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Member.java

package member;

import abstraction.IDValuePair;
import account.Account;
import java.util.Date;

// Referenced classes of package member:
//            MemberPrivilege

public interface Member
    extends IDValuePair
{

    public abstract String getManager();

    public abstract void setManager(String s);

    public abstract String getWholeEmail();

    public abstract void setWholeEmail(String s);

    public abstract String getEmailDomain();

    public abstract void setEmailDomain(String s);

    public abstract String getEmailUserName();

    public abstract void setEmailUserName(String s);

    public abstract String getPhoneNumber();

    public abstract void setPhoneNumber(String s);

    public abstract String getOrganisation();

    public abstract void setOrganisation(String s);

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getName();

    public abstract void setName(String s);

    public abstract Account getAccount();

    public abstract void setAccount(Account account);

    public abstract String getAccountName();

    public abstract void setAccountName(String s);

    public abstract MemberPrivilege getPrivilege();

    public abstract void setPrivilege(MemberPrivilege memberprivilege);

    public abstract void setPrivilegeId(long l);

    public abstract long getPrivilegeId();

    public abstract void setLastModified(Date date);

    public abstract Date getLastModified();

    public abstract void setFirstCreated(Date date);

    public abstract Date getFirstCreated();

    public abstract String getMemberPassword();

    public abstract void setMemberPassword(String s);

    public abstract boolean validate();
}

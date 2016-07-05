// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Changelog.java

package changelog;

import abstraction.IDValuePair;
import account.Account;
import java.util.Date;

public interface Changelog
{

    public abstract Integer getUserId();

    public abstract void setUserId(Integer integer);

    public abstract Account getUser();

    public abstract void setUser(Account account);

    public abstract IDValuePair getChagelogType();

    public abstract void setChagelogType(IDValuePair idvaluepair);

    public abstract Date getActiveSince();

    public abstract void setActiveSince(Date date);

    public abstract Date getLastChange();

    public abstract void setLastChange(Date date);

    public abstract String getComment();

    public abstract void setComment(String s);
}

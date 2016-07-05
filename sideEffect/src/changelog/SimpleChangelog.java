// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleChangelog.java

package changelog;

import abstraction.IDValuePair;
import account.Account;
import java.util.Date;

// Referenced classes of package changelog:
//            Changelog

public class SimpleChangelog
    implements Changelog
{

    public SimpleChangelog()
    {
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Account getUser()
    {
        return user;
    }

    public void setUser(Account user)
    {
        this.user = user;
    }

    public IDValuePair getChagelogType()
    {
        return chagelogType;
    }

    public void setChagelogType(IDValuePair chagelogType)
    {
        this.chagelogType = chagelogType;
    }

    public Date getActiveSince()
    {
        return activeSince;
    }

    public void setActiveSince(Date activeSince)
    {
        this.activeSince = activeSince;
    }

    public Date getLastChange()
    {
        return lastChange;
    }

    public void setLastChange(Date lastChange)
    {
        this.lastChange = lastChange;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    private Integer userId;
    private Account user;
    private IDValuePair chagelogType;
    private Date activeSince;
    private Date lastChange;
    private String comment;
}

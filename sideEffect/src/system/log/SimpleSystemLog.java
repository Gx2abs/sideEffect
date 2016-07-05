// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemLog.java

package system.log;

import java.util.Date;
import member.Member;

// Referenced classes of package system.log:
//            SystemLog

public class SimpleSystemLog
    implements SystemLog
{

    public SimpleSystemLog()
    {
    }

    public String getQueryString()
    {
        return queryString;
    }

    public void setQueryString(String queryString)
    {
        this.queryString = queryString;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public Date getLogTime()
    {
        return logTime;
    }

    public void setLogTime(Date logTime)
    {
        this.logTime = logTime;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getRequestURL()
    {
        return requestURL;
    }

    public void setRequestURL(String requestURL)
    {
        this.requestURL = requestURL;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public String toString()
    {
        String message = "";
        message = (new StringBuilder("log # ")).append(getId()).append(" at ").append(getLogTime()).append(" := ").append(getRequestURL()).append("?").append(getQueryString()).toString();
        if(getMember() != null)
            message = (new StringBuilder(String.valueOf(message))).append(" by ").append(getMember().getAccountName()).append("(user# ").append(getMember().getId()).append(")").toString();
        return message;
    }

    private long id;
    private String propertyValue;
    private Date logTime;
    private long userId;
    private String requestURL;
    private String queryString;
    private Member member;
}

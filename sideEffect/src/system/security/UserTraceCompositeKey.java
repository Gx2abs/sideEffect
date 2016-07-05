// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserTraceCompositeKey.java

package system.security;

import java.io.Serializable;
import java.util.Date;

public class UserTraceCompositeKey
    implements Serializable
{

    public UserTraceCompositeKey()
    {
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Date getTraceTime()
    {
        return traceTime;
    }

    public void setTraceTime(Date traceTime)
    {
        this.traceTime = traceTime;
    }

    private String userId;
    private Date traceTime;
}

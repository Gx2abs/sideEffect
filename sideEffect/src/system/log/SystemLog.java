// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemLog.java

package system.log;

import abstraction.IDValuePair;
import java.util.Date;
import member.Member;

public interface SystemLog
    extends IDValuePair
{

    public abstract Date getLogTime();

    public abstract void setLogTime(Date date);

    public abstract long getUserId();

    public abstract void setUserId(long l);

    public abstract String getRequestURL();

    public abstract void setRequestURL(String s);

    public abstract String getQueryString();

    public abstract void setQueryString(String s);

    public abstract Member getMember();

    public abstract void setMember(Member member);
}

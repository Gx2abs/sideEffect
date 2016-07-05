// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemLogService.java

package system.log;

import javax.servlet.ServletRequest;

// Referenced classes of package system.log:
//            SystemLogDAO, SystemLog

public interface SystemLogService
{

    public abstract SystemLogDAO getSystemLogDAO();

    public abstract void setSystemLogDAO(SystemLogDAO systemlogdao);

    public abstract String buildLogString(ServletRequest servletrequest);

    public abstract SystemLog buildSystemLog(ServletRequest servletrequest);

    public abstract SystemLog doLog(ServletRequest servletrequest);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HistoryServiceProvider.java

package properties.history;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package properties.history:
//            History

public interface HistoryServiceProvider
{

    public abstract History buildOrEmpty(HttpServletRequest httpservletrequest, Class class1);

    public abstract boolean validate(HttpServletRequest httpservletrequest);
}

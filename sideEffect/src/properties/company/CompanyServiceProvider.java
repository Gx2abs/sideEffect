// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompanyServiceProvider.java

package properties.company;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package properties.company:
//            CompanyHistory

public interface CompanyServiceProvider
{

    public abstract CompanyHistory buildOrEmpty(HttpServletRequest httpservletrequest);

    public abstract boolean validate(HttpServletRequest httpservletrequest);
}

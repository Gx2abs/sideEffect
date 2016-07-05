// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentServiceProvider.java

package properties.component;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package properties.component:
//            ComponentHistory

public interface ComponentServiceProvider
{

    public abstract ComponentHistory buildOrEmpty(HttpServletRequest httpservletrequest);

    public abstract boolean validate(HttpServletRequest httpservletrequest);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProductServiceProvider.java

package properties.product;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package properties.product:
//            ProductHistory

public interface ProductServiceProvider
{

    public abstract ProductHistory buildOrEmpty(HttpServletRequest httpservletrequest);

    public abstract boolean validate(HttpServletRequest httpservletrequest);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserTracer.java

package system.security;

import javax.servlet.http.HttpServletRequest;

public interface UserTracer
{

    public abstract void doTrace(HttpServletRequest httpservletrequest);
}

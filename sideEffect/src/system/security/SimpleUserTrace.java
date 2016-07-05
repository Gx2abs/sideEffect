// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleUserTrace.java

package system.security;


// Referenced classes of package system.security:
//            UserTraceCompositeKey

public class SimpleUserTrace
{

    public SimpleUserTrace()
    {
    }

    public UserTraceCompositeKey getKey()
    {
        return key;
    }

    public void setKey(UserTraceCompositeKey key)
    {
        this.key = key;
    }

    public String getRemoteAddr()
    {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr)
    {
        this.remoteAddr = remoteAddr;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getActionTarget()
    {
        return actionTarget;
    }

    public void setActionTarget(String actionTarget)
    {
        this.actionTarget = actionTarget;
    }

    private String remoteAddr;
    private String action;
    private String actionTarget;
    private UserTraceCompositeKey key;
}

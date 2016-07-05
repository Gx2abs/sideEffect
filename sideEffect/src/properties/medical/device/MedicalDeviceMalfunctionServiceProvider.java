// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MedicalDeviceMalfunctionServiceProvider.java

package properties.medical.device;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package properties.medical.device:
//            MedicalDeviceMalfunctionHistory

public interface MedicalDeviceMalfunctionServiceProvider
{

    public abstract MedicalDeviceMalfunctionHistory buildOrEmpty(HttpServletRequest httpservletrequest);

    public abstract boolean validate(HttpServletRequest httpservletrequest);
}

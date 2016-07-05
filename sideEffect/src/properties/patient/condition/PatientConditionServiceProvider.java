// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatientConditionServiceProvider.java

package properties.patient.condition;

import javax.servlet.http.HttpServletRequest;

// Referenced classes of package properties.patient.condition:
//            PatientConditionHistory

public interface PatientConditionServiceProvider
{

    public abstract PatientConditionHistory buildOrEmpty(HttpServletRequest httpservletrequest);

    public abstract boolean validate(HttpServletRequest httpservletrequest);
}

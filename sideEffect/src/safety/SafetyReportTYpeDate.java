// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SafetyReportTYpeDate.java

package safety;

import java.util.Date;
import statistics.property.SimpleReportDate;

public interface SafetyReportTYpeDate
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract long getReport_id();

    public abstract void setReport_id(long l);

    public abstract long getJunc_id();

    public abstract void setJunc_id(long l);

    public abstract Date getReport_date();

    public abstract void setReport_date(Date date);

    public abstract int getReport_type_id();

    public abstract void setReport_type_id(int i);

    public abstract String getReport_year();

    public abstract void setReport_year(String s);

    public abstract String getReport_month();

    public abstract void setReport_month(String s);

    public abstract SimpleReportDate getReportYM();

    public abstract void setReportYM(SimpleReportDate simplereportdate);
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Address.java

package address;


public interface Address
{

    public abstract Integer getId();

    public abstract void setId(Integer integer);

    public abstract String getZipcode();

    public abstract void setZipcode(String s);

    public abstract String getZipcode_seq();

    public abstract void setZipcode_seq(String s);

    public abstract String getSido();

    public abstract void setSido(String s);

    public abstract String getGu();

    public abstract void setGu(String s);

    public abstract String getDoro_code();

    public abstract void setDoro_code(String s);

    public abstract String getDoro_nm();

    public abstract void setDoro_nm(String s);

    public abstract String getBldg1();

    public abstract void setBldg1(String s);

    public abstract String getBldg2();

    public abstract void setBldg2(String s);

    public abstract String getBldg_nm();

    public abstract void setBldg_nm(String s);
}

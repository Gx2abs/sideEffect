// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAddressSejong.java

package address;


// Referenced classes of package address:
//            Address

public class SimpleAddressSejong
    implements Address
{

    public SimpleAddressSejong()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getZipcode_seq()
    {
        return zipcode_seq;
    }

    public void setZipcode_seq(String zipcode_seq)
    {
        this.zipcode_seq = zipcode_seq;
    }

    public String getSido()
    {
        return sido;
    }

    public void setSido(String sido)
    {
        this.sido = sido;
    }

    public String getGu()
    {
        return gu;
    }

    public void setGu(String gu)
    {
        this.gu = gu;
    }

    public String getDoro_code()
    {
        return doro_code;
    }

    public void setDoro_code(String doro_code)
    {
        this.doro_code = doro_code;
    }

    public String getDoro_nm()
    {
        return doro_nm;
    }

    public void setDoro_nm(String doro_nm)
    {
        this.doro_nm = doro_nm;
    }

    public String getBldg1()
    {
        return bldg1;
    }

    public void setBldg1(String bldg1)
    {
        this.bldg1 = bldg1;
    }

    public String getBldg2()
    {
        return bldg2;
    }

    public void setBldg2(String bldg2)
    {
        this.bldg2 = bldg2;
    }

    public String getBldg_nm()
    {
        return bldg_nm;
    }

    public void setBldg_nm(String bldg_nm)
    {
        this.bldg_nm = bldg_nm;
    }

    private Integer id;
    private String zipcode;
    private String zipcode_seq;
    private String sido;
    private String gu;
    private String doro_code;
    private String doro_nm;
    private String bldg1;
    private String bldg2;
    private String bldg_nm;
}

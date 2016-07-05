// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncRisk.java

package risk.category.risk;


// Referenced classes of package risk.category.risk:
//            JuncRisk, RiskCompositeKey

public class SimpleJuncRisk
    implements JuncRisk
{

    public SimpleJuncRisk()
    {
    }

    public RiskCompositeKey getCompositeKey()
    {
        return compositeKey;
    }

    public void setCompositeKey(RiskCompositeKey compositeKey)
    {
        this.compositeKey = compositeKey;
    }

    private RiskCompositeKey compositeKey;
}

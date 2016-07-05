// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncPossibility.java

package risk.category.possibility;


// Referenced classes of package risk.category.possibility:
//            JuncPossibility, PossibilityCompositeKey

public class SimpleJuncPossibility
    implements JuncPossibility
{

    public SimpleJuncPossibility()
    {
    }

    public PossibilityCompositeKey getCompositeKey()
    {
        return compositeKey;
    }

    public void setCompositeKey(PossibilityCompositeKey compositeKey)
    {
        this.compositeKey = compositeKey;
    }

    private PossibilityCompositeKey compositeKey;
}
